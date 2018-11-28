
package com.ms.taojin.manage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import com.ms.taojin.manage.entity.ItemEntity;
import com.ms.taojin.manage.mapper.ItemMapper;


/**
 * 物品信息 bo
 * @author 蒙赛
 * @Date 2018-11-28 09:30:09
 * @since 1.0
 */
@Component
public class ItemBO extends AbstractBaseBO<ItemEntity> {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Override
    protected IBaseMapper<ItemEntity> getMapper() {
	    return itemMapper;
    }
}
