package com.mx.util;

import java.util.UUID;

/**
 * 字符串处理工具类 
 *
 * @author YuChuanQi
 * @since 2016年5月12日 下午4:31:21
 */
public class StringUtils {
	
	public static boolean isEmpty(String ... obj ) {
		for (String str : obj) {
			if (null == str || "".equals(str.trim()) || "null".equals(str)) {
				return true;
			}
		}
		return false;
	}

	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}


}
