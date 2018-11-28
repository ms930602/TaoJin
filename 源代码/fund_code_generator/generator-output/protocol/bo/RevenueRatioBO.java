
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.RevenueRatioEntity;
import com.ms.taojin.manage.mapper.RevenueRatioMapper;


/**
 *  bo
 * @author 蒙赛
 * @Date 2018-11-28 10:40:36
 * @since 1.0
 */
@Component
public class RevenueRatioBO extends AbstractBaseBO<RevenueRatioEntity> {
	
	@Autowired
	private RevenueRatioMapper revenueRatioMapper;
	
	@Override
    protected IBaseMapper<RevenueRatioEntity> getMapper() {
	    return revenueRatioMapper;
    }
}
