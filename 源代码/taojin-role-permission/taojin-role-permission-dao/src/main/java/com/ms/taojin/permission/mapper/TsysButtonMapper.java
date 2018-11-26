/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.permission.entity.TsysButtonEntity;

/**
 * Mapper.<p>
 * @author lansongtao
 * @Date 2017-04-05 09:13:50
 * @since 1.0
 */
@Repository
public interface TsysButtonMapper extends IBaseMapper<TsysButtonEntity>{
	

	/**
	 * 按对象查询查询记录.<p>
	 * @param TsysButton		
	 * @return					
	 */	
	java.util.List<TsysButtonEntity> queryByTsysButtonEntity(TsysButtonEntity tsysButton);
	public List<TsysButtonEntity> queryBymoduleId(String moduleId);

}
