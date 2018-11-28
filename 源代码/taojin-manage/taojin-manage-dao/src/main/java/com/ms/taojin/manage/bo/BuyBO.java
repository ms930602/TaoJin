
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.BuyEntity;
import com.ms.taojin.manage.mapper.BuyMapper;


/**
 *  bo
 * @author 蒙赛
 * @Date 2018-11-28 09:30:06
 * @since 1.0
 */
@Component
public class BuyBO extends AbstractBaseBO<BuyEntity> {
	
	@Autowired
	private BuyMapper buyMapper;
	
	@Override
    protected IBaseMapper<BuyEntity> getMapper() {
	    return buyMapper;
    }
}
