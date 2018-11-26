/**
 * All rights reserved by XinGuoDu Inc.
 */
package com.ms.taojin.permission.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.permission.entity.TsysSysEntity;
import com.ms.taojin.permission.mapper.TsysSysMapper;


/**
 * 业务处理.<p>
 * @author lansongtao
 * @Date 2017-04-05 09:13:51
 * @since 1.0
 */
@Component
public class TsysSysBO extends AbstractBaseBO<TsysSysEntity> {
	
	@Autowired
	private TsysSysMapper tsysSysMapper;
	
	@Override
    protected IBaseMapper<TsysSysEntity> getMapper() {
	    return tsysSysMapper;
    }
}
