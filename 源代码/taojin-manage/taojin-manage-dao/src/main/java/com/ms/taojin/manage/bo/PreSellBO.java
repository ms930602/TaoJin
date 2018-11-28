
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.PreSellEntity;
import com.ms.taojin.manage.mapper.PreSellMapper;


/**
 * 预卖出 bo
 * @author 蒙赛
 * @Date 2018-11-28 09:30:14
 * @since 1.0
 */
@Component
public class PreSellBO extends AbstractBaseBO<PreSellEntity> {
	
	@Autowired
	private PreSellMapper preSellMapper;
	
	@Override
    protected IBaseMapper<PreSellEntity> getMapper() {
	    return preSellMapper;
    }
}
