
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.GameEntity;
import com.ms.taojin.manage.mapper.GameMapper;


/**
 * 游戏 bo
 * @author 蒙赛
 * @Date 2018-11-27 15:29:47
 * @since 1.0
 */
@Component
public class GameBO extends AbstractBaseBO<GameEntity> {
	
	@Autowired
	private GameMapper gameMapper;
	
	@Override
    protected IBaseMapper<GameEntity> getMapper() {
	    return gameMapper;
    }
}
