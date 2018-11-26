package com.ms.taojin.user.dubboService;

import java.util.Map;

import com.ms.taojin.common.entity.PersoninfoEntity;


public interface PersoninfoService {
	PersoninfoEntity queryByMapParams(Map<String, Object> params);
}
