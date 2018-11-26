
package com.ms.taojin.log.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.log.LogBusinessEntity;
import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.log.mapper.LogBusinessMapper;


/**
 * 系统操作日志 bo
 * @author lansongtao
 * @Date 2017-12-18 14:34:42
 * @since 1.0
 */
@Component
public class LogBusinessBO extends AbstractBaseBO<LogBusinessEntity> {
	
	@Autowired
	private LogBusinessMapper logBusinessMapper;
	
	@Override
    protected IBaseMapper<LogBusinessEntity> getMapper() {
	    return logBusinessMapper;
    }
}
