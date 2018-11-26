<#include "/include/java_copyright.include"/>

<#include "/include/macro.include"/>
package ${basepackage}.bo${modepackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.taojin.common.bo.AbstractBaseBO;
import com.ms.taojin.common.mapper.IBaseMapper;

import ${basepackage}.entity${modepackage}.${table.classNameEntity};
import ${basepackage}.mapper${modepackage}.${table.className}Mapper;

<#assign po = table.classNameFirstLower>

<#include "/include/java_classBo.include">

@Component
public class ${table.classNameBo} extends AbstractBaseBO<${table.classNameEntity}> {
	
	@Autowired
	private ${table.className}Mapper ${table.classNameFirstLower}Mapper;
	
	@Override
    protected IBaseMapper<${table.classNameEntity}> getMapper() {
	    return ${table.classNameFirstLower}Mapper;
    }
}
