
package com.ms.taojin.manage.bo;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.manage.entity.ClassificateEntity;
import com.ms.taojin.manage.mapper.ClassificateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 数据分类 bo
 * @author dwx
 * @Date 2018-07-09 18:27:45
 * @since 1.0
 */
@Component
public class ClassificateBO extends AbstractBaseBO<ClassificateEntity> {
	
	@Autowired
	private ClassificateMapper classificateMapper;
	
	@Override
    protected IBaseMapper<ClassificateEntity> getMapper() {
	    return classificateMapper;
    }
	
	/**
	 * 查询字典分类信息
	 * @param keyword
	 * @return
	 */
	public List<ClassificateEntity> queryClassificate(String keyword){
		return classificateMapper.queryClassificate(keyword);
	}
}

