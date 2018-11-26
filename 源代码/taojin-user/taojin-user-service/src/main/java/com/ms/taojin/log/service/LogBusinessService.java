package com.ms.taojin.log.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.log.LogBusinessEntity;
import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.Param;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;
import com.ms.taojin.log.bo.LogBusinessBO;
import com.ms.taojin.user.entity.UserEntity;

/**
 * 系统操作日志 业务处理
 * 
 * @author lansongtao
 * @Date 2017-12-18 14:34:42
 * @since 1.0
 */
@Service
public class LogBusinessService extends BaseService {

	@Autowired
	private LogBusinessBO logBusinessBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<UserEntity> reqVO) {
		List<LogBusinessEntity> logBusinessList = logBusinessBo.queryByPage(reqVO);
		int dataCount = logBusinessBo.queryCount(reqVO);

		ListRespVO respVO = new ListRespVO();
		respVO.setAaData(logBusinessList);
		respVO.setDataCount(dataCount);

		return respVO;
	}

	/**
	 * 新增
	 * 
	 * @param reqVO
	 * @return
	 */
	public Object create(LogBusinessEntity logBusiness) {
		logBusinessBo.create(logBusiness);
		return logBusiness;
	}

	/**
	 * 修改
	 * 
	 * @param reqVO
	 * @return
	 */
	public BaseRespVO update(LogBusinessEntity logBusiness) {
		int updateCount = logBusinessBo.update(logBusiness);
		if (updateCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要修改的记录！");
		}

	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public BaseRespVO delete(@Param("id") Long[] id) {
		int deleteCount = logBusinessBo.batchDeleteById(id);
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
