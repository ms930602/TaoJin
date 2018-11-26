package com.ms.taojin.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.bo.DictionariesBO;
import com.ms.taojin.common.entity.DictionariesEntity;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.Param;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;

/**
 * 数据字典 业务处理
 * 
 * @author lansongtao
 * @Date 2017-12-14 10:56:26
 * @since 1.0
 */
@Service
public class DictionariesService extends BaseService {

	@Autowired
	private DictionariesBO dictionariesBo;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO<DictionariesEntity> reqVO) {
		List<DictionariesEntity> dictionariesList = dictionariesBo.queryByPage(reqVO);
		int dataCount = dictionariesBo.queryCount(reqVO);

		ListRespVO respVO = new ListRespVO();
		respVO.setAaData(dictionariesList);
		respVO.setDataCount(dataCount);

		return respVO;
	}

	/**
	 * 新增
	 * 
	 * @param reqVO
	 * @return
	 */
	public Object create(DictionariesEntity dictionaries) {
		dictionariesBo.create(dictionaries);
		return dictionaries;
	}

	/**
	 * 修改
	 * 
	 * @param reqVO
	 * @return
	 */
	public BaseRespVO update(DictionariesEntity dictionaries) {
		int updateCount = dictionariesBo.update(dictionaries);
		if (updateCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要修改的记录！");
		}

	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public BaseRespVO delete(@Param("id") Long[] id) {
		int deleteCount = dictionariesBo.batchDeleteById(id);
		if (deleteCount > 0) {
			return new BaseRespVO();
		} else {
			return new BaseRespVO(2, "没有要删除的记录！");
		}
	}

}
