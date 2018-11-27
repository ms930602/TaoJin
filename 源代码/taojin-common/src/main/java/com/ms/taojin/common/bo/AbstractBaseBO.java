package com.ms.taojin.common.bo;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ms.taojin.common.db.FieldValidate;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.entity.SessionUserUtils;
import com.ms.taojin.common.exception.CenterException;
import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.common.utils.EmptyUtils;
import com.ms.taojin.common.utils.IdGenerateUtils;
import com.ms.taojin.common.utils.ListUtils;
import com.ms.taojin.common.utils.SQLValid;
import com.ms.taojin.common.utils.UUIDUtils;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;

/**
 * 业务处理基类.
 * 
 * @author lansongtao
 * @Date 2015年8月12日
 * @since 1.0
 */
public abstract class AbstractBaseBO<T extends BaseEntity> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 批量处理分批最大记录数 */
	public static final int BATCH_MAX_COUNT = 200;

	/** ID生成工具类 */
	protected IdGenerateUtils idGenerateUtils = new IdGenerateUtils();

	@Autowired
	FieldValidate fieldValidate;

	/**
	 * 获取Mapper.
	 * <p>
	 * 
	 * @return
	 */
	protected abstract IBaseMapper<T> getMapper();

	/**
	 * 新增并校验字段合法性
	 * 
	 * @param entity
	 * @throws CenterException
	 */
	public void createForValidate(T entity) throws CenterException {
		// 校验字段有效性
		// 给字符串作为主键的表加上UUID主键
		UUIDUtils.putIdByEntity(entity);
		// 从上下文中添加用户信息
		SessionUserUtils.putSessionUserForCreate(entity);
		fieldValidate.validateForCreate(entity);
		getMapper().create(entity);
	}

	/**
	 * 修改并校验字段合法性
	 * 
	 * @param entity
	 * @throws CenterException
	 */
	public int updateForValidate(T entity) throws CenterException {
		// 校验字段有效性
		fieldValidate.validateForUpdate(entity);
		return update(entity);
	}

	/**
	 * 根据权限修改并校验字段合法性
	 *
	 * @param entity
	 * @throws CenterException
	 */
	public int updateAuthForValidate(T entity) throws CenterException {
		// 校验字段有效性
		fieldValidate.validateForUpdate(entity);
		return updateAuth(entity);
	}

	public void create(T entity) {
		// 给字符串作为主键的表加上UUID主键
		UUIDUtils.putIdByEntity(entity);
		// 从上下文中添加用户信息
		SessionUserUtils.putSessionUserForCreate(entity);
		getMapper().create(entity);
	}

	public int update(T entity) {
		// 从上下文中添加用户信息
		SessionUserUtils.putSessionUserForUpdate(entity);
		return getMapper().update(entity);
	}

	public int updateAuth(T entity) {
		// 从上下文中添加用户信息
		SessionUserUtils.putSessionUserForCreate(entity);
		SessionUserUtils.putSessionUserForUpdate(entity);
		return getMapper().updateAuth(entity);
	}

	public int batchUpdate(List<T> entitys) {
		int result = 0;
		if (EmptyUtils.isEmpty(entitys)) {
			return result;
		}
		// 从上下文中添加用户信息
		SessionUserUtils.putSessionUserForUpdate(entitys);
		// 校验字段有效性
		fieldValidate.validateForUpdate(entitys);
		List<List<T>> splitList = ListUtils.splitList(entitys, BATCH_MAX_COUNT);
		for (List<T> list : splitList) {
			result = result + getMapper().batchUpdate(list);
		}
		return result;
	}

	public int batchSave(List<T> entitys) {
		int result = 0;
		if (EmptyUtils.isEmpty(entitys)) {
			return result;
		}
		// 从上下文中添加用户信息
		SessionUserUtils.putSessionUserForCreate(entitys);
		// 校验字段有效性
		fieldValidate.validateForCreate(entitys);
		List<List<T>> splitList = ListUtils.splitList(entitys, BATCH_MAX_COUNT);
		for (List<T> list : splitList) {
			result = result + getMapper().batchSave(list);
		}
		return result;
	}

	public int deleteById(long id) {
		return getMapper().deleteById(id);
	}

	public int batchDeleteById(long[] ids) {
		return getMapper().batchDeleteById(ids);
	}

	public int batchDeleteAuthById(Long[] ids,Long userId) {
		if(ids==null || ids.length == 0)return 0;
		Map map = new HashMap();
		List<Long> list = Arrays.asList(ids);
		map.put("ids",list);
		map.put("userId",userId);
		return getMapper().batchDeleteAuthById(map);
	}

	public int batchDeleteById(Long[] ids) {
		return getMapper().batchDeleteById(ids);
	}

	public int deleteById(String id) {
		return getMapper().deleteById(id);
	}

	public int batchDeleteById(String[] ids) {
		return getMapper().batchDeleteById(ids);
	}

	public T queryById(long id) {
		return getMapper().queryById(id);
	}

	public List<T> queryByPage(ListReqVO<?> reqVO) {
		if (reqVO != null && reqVO.getOrderField() != null && !SQLValid.isValid(reqVO.getOrderField())) {
			logger.error("传入参数有SQL注入风险！{}", reqVO.getOrderField());
			reqVO.setOrderField(null);
		}

		if (reqVO != null && 0 != reqVO.getPageNum()) {
			reqVO.setStartNum((reqVO.getPageNum() - 1) * reqVO.getPageSize());
			reqVO.setPageNum((reqVO.getPageNum() - 1) * reqVO.getPageSize());
		}
		return getMapper().queryByPage(reqVO);

	}

	public ListRespVO queryPageAutomatic(ListReqVO<?> reqVO) {
		if (reqVO != null && reqVO.getOrderField() != null && !SQLValid.isValid(reqVO.getOrderField())) {
			logger.error("传入参数有SQL注入风险！{}", reqVO.getOrderField());
			reqVO.setOrderField(null);
		}

		Page<?> page = null;

		if (reqVO != null && 0 != reqVO.getPageNum()) {
			page = PageHelper.startPage(reqVO.getPageNum(), reqVO.getPageSize(), true);
		} else {
			page = PageHelper.startPage(1, 1000, true);
		}

		ListRespVO resp = new ListRespVO();
		resp.setAaData(getMapper().queryByPage(reqVO));
		resp.setDataCount(page.getTotal());
		return resp;
	}

	public int queryCount(ListReqVO<?> reqVO) {
		return getMapper().queryCount(reqVO);
	}

	public T queryByEntity(T entity) {
		PageHelper.startPage(1, 1, false);
		List<T> list = getMapper().queryByEntity(entity);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}

	}

	public List<T> queryListByEntity(T entity) {
		return getMapper().queryByEntity(entity);
	}

}
