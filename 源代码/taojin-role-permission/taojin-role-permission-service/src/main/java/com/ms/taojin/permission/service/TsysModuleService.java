package com.ms.taojin.permission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;
import com.ms.taojin.permission.bo.TsysModuleBO;
import com.ms.taojin.permission.entity.TsysModuleEntity;

/**
 * 用户详细信息接口实现
 *
 * @author Administrator
 * @Date 2017年3月27日
 * @since 1.0
 */
@Service("tsysModuleService")
public class TsysModuleService extends BaseService {

	@Autowired
	private TsysModuleBO tsysModuleBO;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO reqVO) {
		List<TsysModuleEntity> userDetaliList = tsysModuleBO.queryByPage(reqVO);
		int dataCount = tsysModuleBO.queryCount(reqVO);

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
	public BaseRespVO create(TsysModuleEntity tsysModule) {
		tsysModuleBO.create(tsysModule);
		return new BaseRespVO();
	}

	/**
	 * 修改
	 * 
	 * @param reqVO
	 * @return
	 */
	public BaseRespVO update(TsysModuleEntity tsysModule) {
		tsysModuleBO.update(tsysModule);
		return new BaseRespVO();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public BaseRespVO delete(TsysModuleEntity tsysModule) {
		tsysModuleBO.deleteById(tsysModule.getId());
		return new BaseRespVO();
	}

	
	public List<TsysModuleEntity> query(String sysId) {
		// TODO Auto-generated method stub
		return tsysModuleBO.query(sysId);
	}
	
	public List<TsysModuleEntity> queryMainMenuItem(String sysId) {
		// TODO Auto-generated method stub
		return tsysModuleBO.queryMainMenuItem(sysId);
	}
	public List<TsysModuleEntity> queryNot(String sysId){
		return tsysModuleBO.queryNot(sysId);
	}

}
