
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.CurrencyUnitEntity;
import com.ms.taojin.manage.mapper.CurrencyUnitMapper;


/**
 * 游戏货币单位 bo
 * @author 蒙赛
 * @Date 2018-11-27 14:42:24
 * @since 1.0
 */
@Component
public class CurrencyUnitBO extends AbstractBaseBO<CurrencyUnitEntity> {
	
	@Autowired
	private CurrencyUnitMapper currencyUnitMapper;
	
	@Override
    protected IBaseMapper<CurrencyUnitEntity> getMapper() {
	    return currencyUnitMapper;
    }
}
