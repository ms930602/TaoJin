package ${basepackage}.service${modepackage};

import com.ms.taojin.common.entity.SessionUser;
import com.ms.taojin.common.service.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.exception.CenterException;
import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.Param;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;

import ${basepackage}.bo${modepackage}.${table.className}BO;
import ${basepackage}.entity${modepackage}.${table.classNameEntity};

import java.util.Date;

<#include "/include/java_classService.include">
@Service
public class ${table.className}Service extends BaseService {

	@Autowired
	private ${table.className}BO ${table.classNameFirstLower}Bo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<${table.className}Entity> reqVO) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		if(reqVO.getWhereCondition()!=null){
		reqVO.getWhereCondition().setCreateUserId(user.getUserId());
		}
		return ${table.classNameFirstLower}Bo.queryPageAutomatic(reqVO);
	}
	
	/**
	 * 根据ID查询单条记录
	 * 
	 * @param ${table.primaryKeyParName}
	 * @return
	 */
	public Object queryById(@Param("${table.primaryKeyParName}") ${table.pkType} ${table.primaryKeyParName}) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		${table.className}Entity query = new ${table.className}Entity();
		query.setId(${table.primaryKeyParName});
		query.setCreateUserId(user.getUserId());
		return ${table.classNameFirstLower}Bo.queryByEntity(query);
	}

	/**
	 * 新增
	 * 
	 * @param ${table.classNameFirstLower}
	 * @return
	 */
	public Object create(${table.className}Entity ${table.classNameFirstLower}) throws CenterException {
		${table.classNameFirstLower}.setCreatetime(new Date());
		${table.classNameFirstLower}Bo.createForValidate(${table.classNameFirstLower});
		return ${table.classNameFirstLower};
	}

	/**
	 * 修改
	 * 
	 * @param ${table.classNameFirstLower}
	 * @return
	 */
	public BaseRespVO update(${table.className}Entity ${table.classNameFirstLower}) throws CenterException {
		int updateCount = ${table.classNameFirstLower}Bo.updateAuthForValidate(${table.classNameFirstLower});
		if(updateCount > 0){
			return new BaseRespVO();
		}else{
			return new BaseRespVO(2,"没有要修改的记录！");
		}
		
	}

	/**
	 * 删除
	 * 
	 * @param ${table.primaryKeyParName}
	 * @return
	 */
	public BaseRespVO delete(@Param("${table.primaryKeyParName}") ${table.pkType}[] ${table.primaryKeyParName}) throws CenterException {
		SessionUser user = ThreadContext.getSessionloginUser();
		int deleteCount = ${table.classNameFirstLower}Bo.batchDeleteAuthById(${table.primaryKeyParName},user.getUserId());
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
