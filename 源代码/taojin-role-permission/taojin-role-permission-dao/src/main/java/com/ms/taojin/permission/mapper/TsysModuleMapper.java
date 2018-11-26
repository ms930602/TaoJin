/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.permission.entity.TsysModuleEntity;

/**
 * Mapper.<p>
 * @author lansongtao
 * @Date 2017-04-05 09:13:50
 * @since 1.0
 */
@Repository
public interface TsysModuleMapper extends IBaseMapper<TsysModuleEntity>{
	

	/**
	 * 按对象查询查询记录.<p>
	 * @param TsysModule		
	 * @return					
	 */	
	java.util.List<TsysModuleEntity> queryByTsysModuleEntity(TsysModuleEntity tsysModule);
	public List<TsysModuleEntity> query(String sysId);
	public List<TsysModuleEntity> queryMainMenuItem(String sysId);
	public List<TsysModuleEntity> queryNot(String sysId);

}
