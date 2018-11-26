<#include "/include/java_copyright.include">

<#include "/include/macro.include"/>
<#assign className = table.classNameEntity>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.entity${modepackage};

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.taojin.common.entity.BaseEntity;
import com.ms.taojin.common.vo.TableName;

<#include "/include/java_class.include">

@TableName("${table.sqlName}")
public class ${className} extends BaseEntity {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
    <#--属性-->
	<#list table.columns as column>
	/** ${column.remarks}. */
	private ${column.possibleShortJavaType} ${column.columnNameFirstLower}${column.defaultFormatValue};
	
	</#list>
	
<@generateJavaColumns/>
<@generateConstructor className/>
<@generateJavaCommonMethods/>
}
<#--生成公共方法-->
<#macro generateJavaCommonMethods>
	@Override
	public String toString() {
		return new StringBuilder().append("${className}[")
		<#list table.columns as column>
			.append("${column.columnName}=").append(get${column.columnName}())<#if column_has_next>.append(", ")</#if>
		</#list>
		.append("]").toString();
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if(obj instanceof ${className} == false)
			return false;
		if(this == obj)
			return true;
		${className} other = (${className})obj;
		
		return this.toString().equals(other.toString());
	}
</#macro>
<#--get和set方法-->
<#macro generateJavaColumns>
	<#list table.columns as column>

    /** set ${column.remarks}. */
	public void set${column.columnName}(${column.possibleShortJavaType} ${column.columnNameFirstLower}) {
		this.${column.columnNameFirstLower} = ${column.columnNameFirstLower};
	}
	
	/** get ${column.remarks}. */
	public ${column.possibleShortJavaType} get${column.columnName}() {
		return this.${column.columnNameFirstLower};
	}
	
	@JsonIgnore
	public ${column.possibleShortJavaType} get${column.columnName}ByLike() {
	<#if column.jdbcType=='VARCHAR'>
		return "%"+this.${column.columnNameFirstLower}+"%";
	<#else>
		return this.${column.columnNameFirstLower};
	</#if>
	}
	
	</#list>
</#macro>

<#--根据外键生成一对多方法-->
<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	/** 分录明细 */
	private java.util.List<${fkPojoClass}> ${fkPojoClassVar}s = new java.util.ArrayList<${fkPojoClass}>();
	
	public void set${fkPojoClass}s(java.util.List<${fkPojoClass}> ${fkPojoClassVar}s){
		this.${fkPojoClassVar}s = ${fkPojoClassVar}s;
	}
	
	public java.util.List<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	
	/**
	 * 增加分录.
	 * @param ${fkPojoClassVar}
	 */
	public void add${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}) {
		this.${fkPojoClassVar}s.add(${fkPojoClassVar});
	}
	
	</#list>
</#macro>
<#--根据关联关系生成多对一方法-->
<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
