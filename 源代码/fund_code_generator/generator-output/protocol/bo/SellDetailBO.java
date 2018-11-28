
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.SellDetailEntity;
import com.ms.taojin.manage.mapper.SellDetailMapper;


/**
 *  bo
 * @author 蒙赛
 * @Date 2018-11-28 10:40:41
 * @since 1.0
 */
@Component
public class SellDetailBO extends AbstractBaseBO<SellDetailEntity> {
	
	@Autowired
	private SellDetailMapper sellDetailMapper;
	
	@Override
    protected IBaseMapper<SellDetailEntity> getMapper() {
	    return sellDetailMapper;
    }
}
