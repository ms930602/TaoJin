package com.ms.taojin.manage.service;

import com.ms.taojin.common.entity.SessionUser;
import com.ms.taojin.common.exception.CenterException;
import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.service.ThreadContext;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;
import com.ms.taojin.common.vo.Param;
import com.ms.taojin.manage.bo.DictionaryBO;
import com.ms.taojin.manage.entity.DictionaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * 数据字典 业务处理
 * @author dwx
 * @Date 2018-07-09 18:27:45
 * @since 1.0
 */@Service
public class DictionaryService extends BaseService {

	@Autowired
	private DictionaryBO dictionaryBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<DictionaryEntity> reqVO) throws CenterException {
		return dictionaryBo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public Object queryById(@Param("id") Long id) throws CenterException {
		return dictionaryBo.queryById(id);
	}

	/**
	 * 新增
	 * 
	 * @param dictionary
	 * @return
	 */
	public Object create(DictionaryEntity dictionary) throws CenterException {
		DictionaryEntity entity = new DictionaryEntity();
		entity.setClaCode(dictionary.getClaCode());
		entity.setDicCode(dictionary.getDicCode());
		DictionaryEntity findOne = dictionaryBo.queryByEntity(entity);
		if(findOne!=null&&findOne.getId()>0) {
			findOne.setId((long)-1);
			return new BaseRespVO(1, "相同分类下的字典编码重复"); 
		}
		
		dictionary.setCreatedDate(new java.util.Date());
		dictionaryBo.createForValidate(dictionary);
		return dictionary;
	}

	/**
	 * 修改
	 * 
	 * @param dictionary
	 * @return
	 */
	public BaseRespVO update(DictionaryEntity dictionary) throws CenterException {
		DictionaryEntity entity = new DictionaryEntity();
		entity.setClaCode(dictionary.getClaCode());
		entity.setDicCode(dictionary.getDicCode());
		DictionaryEntity findOne = dictionaryBo.queryByEntity(entity);
		if(findOne!=null&&findOne.getId()>0) {
			findOne.setId((long)-1);
			return new BaseRespVO(1, "相同分类下的字典编码重复"); 
		}
		
		dictionary.setUpdatedDate(new java.util.Date());
		int updateCount = dictionaryBo.updateForValidate(dictionary);
		if(updateCount > 0){
			return new BaseRespVO();
		}else{
			return new BaseRespVO(2,"没有要修改的记录！");
		}
		
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public BaseRespVO delete(@Param("id") Long[] id) throws CenterException {
		int deleteCount = dictionaryBo.batchDeleteById(id);
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
