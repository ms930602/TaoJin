
package com.ms.taojin.manage.mapper;

import com.ms.taojin.common.mapper.IBaseMapper;
import com.ms.taojin.manage.entity.ClassificateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据分类 Mapper
 * @author dwx
 * @Date 2018-07-09 18:27:45
 * @since 1.0
 */
@Repository
public interface ClassificateMapper extends IBaseMapper<ClassificateEntity>{
	
	List<ClassificateEntity> queryClassificate(String keyword);
}
