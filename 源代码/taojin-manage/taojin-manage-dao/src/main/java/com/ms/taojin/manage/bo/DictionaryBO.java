
package com.ms.taojin.manage.bo;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.manage.entity.DictionaryEntity;
import com.ms.taojin.manage.mapper.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 数据字典 bo
 * @author dwx
 * @Date 2018-07-09 18:27:45
 * @since 1.0
 */
@Component
public class DictionaryBO extends AbstractBaseBO<DictionaryEntity> {
	
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Override
    protected IBaseMapper<DictionaryEntity> getMapper() {
	    return dictionaryMapper;
    }
}
