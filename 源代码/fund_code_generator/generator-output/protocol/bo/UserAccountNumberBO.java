
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.UserAccountNumberEntity;
import com.ms.taojin.manage.mapper.UserAccountNumberMapper;


/**
 *  bo
 * @author 蒙赛
 * @Date 2018-11-28 10:40:46
 * @since 1.0
 */
@Component
public class UserAccountNumberBO extends AbstractBaseBO<UserAccountNumberEntity> {
	
	@Autowired
	private UserAccountNumberMapper userAccountNumberMapper;
	
	@Override
    protected IBaseMapper<UserAccountNumberEntity> getMapper() {
	    return userAccountNumberMapper;
    }
}
