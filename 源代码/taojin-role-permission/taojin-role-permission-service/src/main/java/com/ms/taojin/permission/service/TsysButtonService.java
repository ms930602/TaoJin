package com.ms.taojin.permission.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;
import com.ms.taojin.permission.bo.TsysButtonBO;
import com.ms.taojin.permission.entity.TsysButtonEntity;

/**
 * 用户详细信息接口实现
 *
 * @author Administrator
 * @Date 2017年3月27日
 * @since 1.0
 */
@Service("tsysButtonService")
public class TsysButtonService extends BaseService {

	@Autowired
	private TsysButtonBO tsysButtonBO;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO reqVO) {
		List<TsysButtonEntity> userDetaliList = tsysButtonBO.queryByPage(reqVO);
		int dataCount = tsysButtonBO.queryCount(reqVO);

		ListRespVO respVO = new ListRespVO();
		respVO.setAaData(userDetaliList);
		respVO.setDataCount(dataCount);

		return respVO;
	}

	/**
	 * 新增
	 * 
	 * @param reqVO
	 * @return
	 */
	public BaseRespVO create(TsysButtonEntity tsysButton) {
		tsysButton.setCreatetime(new Date());
		tsysButtonBO.create(tsysButton);
		return new BaseRespVO();
	}

	/**
	 * 修改
	 * 
	 * @param reqVO
	 * @return
	 */
	public BaseRespVO update(TsysButtonEntity tsysButton) {
		tsysButtonBO.update(tsysButton);
		return new BaseRespVO();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public BaseRespVO delete(TsysButtonEntity tsysButton) {
		tsysButtonBO.deleteById(tsysButton.getId());
		return new BaseRespVO();
	}
	/**
	 * 根据模块拿到组件信息
	 * @param moduleId
	 * @return
	 */
	public List<TsysButtonEntity> queryBymoduleId(String moduleId){
		return tsysButtonBO.queryBymoduleId(moduleId);
	}

}
