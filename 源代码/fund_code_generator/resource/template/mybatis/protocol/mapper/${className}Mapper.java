<#include "/include/java_copyright.include">

<#assign className = table.className>  
<#assign entityName = table.classNameEntity>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.mapper${modepackage};

import com.ms.taojin.common.mapper.IBaseMapper;

import ${basepackage}.entity${modepackage}.${entityName};

import org.springframework.stereotype.Repository;

<#include "/include/java_classMapper.include">

@Repository
public interface ${className}Mapper extends IBaseMapper<${entityName}>{
	
}
