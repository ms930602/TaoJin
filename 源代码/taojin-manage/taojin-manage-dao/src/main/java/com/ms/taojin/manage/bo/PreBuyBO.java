
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.PreBuyEntity;
import com.ms.taojin.manage.mapper.PreBuyMapper;


/**
 * 预买入 bo
 * @author 蒙赛
 * @Date 2018-11-28 09:30:11
 * @since 1.0
 */
@Component
public class PreBuyBO extends AbstractBaseBO<PreBuyEntity> {
	
	@Autowired
	private PreBuyMapper preBuyMapper;
	
	@Override
    protected IBaseMapper<PreBuyEntity> getMapper() {
	    return preBuyMapper;
    }
}
