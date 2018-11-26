
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.OpinionEntity;
import com.ms.taojin.manage.mapper.OpinionMapper;


/**
 *  bo
 * @author 蒙赛
 * @Date 2018-11-26 15:03:42
 * @since 1.0
 */
@Component
public class OpinionBO extends AbstractBaseBO<OpinionEntity> {
	
	@Autowired
	private OpinionMapper opinionMapper;
	
	@Override
    protected IBaseMapper<OpinionEntity> getMapper() {
	    return opinionMapper;
    }
}
