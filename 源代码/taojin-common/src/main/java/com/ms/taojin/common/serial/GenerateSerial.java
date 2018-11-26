package com.ms.taojin.common.serial;


import org.springframework.stereotype.Component;

import com.ms.taojin.common.utils.DateUtilSafe;
/** 
 * @author: dwx 
 * @Description: 统一生成单号序列号
 * @date 创建时间：2018年7月25日 下午7:54:54 
 */
@Component
public class GenerateSerial extends SerialGenerate{

	//@Autowired
	//private  SerialGenerate SG;
	
	/**
	 * @Description:生成单号、批次号、唯一码  公用方法
	 * @param param  RK入库单、CK出库单、PD盘点单、TZ调整单、PC批次号、WY库存唯一码、BHG不合格单
	 * @return
	 */
	public String GenSer(String param) {
		switch(param) {
		case "RK":
			return generateNoForDay(param, 4, DateUtilSafe.dtShort2);
			
		case "CK":
			return generateNoForDay(param, 4, DateUtilSafe.dtShort2);
		case "PD":
			return generateNoForDay(param, 4, DateUtilSafe.dtShort2);
			
		case "TZ":
			return generateNoForDay(param, 4, DateUtilSafe.dtShort2);
		case "PC":
			return generateNoForDay("", 5, DateUtilSafe.dtShort2);
		case "WY":
			return generateNoForDay("", 5, DateUtilSafe.dtShort2);
		case "RFID":
			return generateNoForDay(param, 5, DateUtilSafe.dtShort2);
		case "BHG":
			return generateNoForDay(param, 4, DateUtilSafe.dtShort2);
		default:
			return "";
			
			
		}
	}
}
