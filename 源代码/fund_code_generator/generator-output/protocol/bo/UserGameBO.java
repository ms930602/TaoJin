
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.UserGameEntity;
import com.ms.taojin.manage.mapper.UserGameMapper;


/**
 *  bo
 * @author 蒙赛
 * @Date 2018-11-28 10:40:43
 * @since 1.0
 */
@Component
public class UserGameBO extends AbstractBaseBO<UserGameEntity> {
	
	@Autowired
	private UserGameMapper userGameMapper;
	
	@Override
    protected IBaseMapper<UserGameEntity> getMapper() {
	    return userGameMapper;
    }
}
