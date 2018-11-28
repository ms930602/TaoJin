
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.SellEntity;
import com.ms.taojin.manage.mapper.SellMapper;


/**
 * 卖出 bo
 * @author 蒙赛
 * @Date 2018-11-28 09:30:20
 * @since 1.0
 */
@Component
public class SellBO extends AbstractBaseBO<SellEntity> {
	
	@Autowired
	private SellMapper sellMapper;
	
	@Override
    protected IBaseMapper<SellEntity> getMapper() {
	    return sellMapper;
    }
}
