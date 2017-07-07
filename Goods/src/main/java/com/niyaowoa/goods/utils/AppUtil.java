package com.niyaowoa.goods.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.utils.json.JSONArray;
import com.niyaowoa.goods.utils.json.JSONObject;
import com.sun.corba.se.spi.ior.ObjectKey;


public class AppUtil {
	/**
	 * 返回json
	 * @param pd
	 * @param map
	 * @return
	 */
	public static Object returnObject(PageData pd, Map map) {
		if(pd==null)return map;
		if (pd.containsKey("callback")) {
			String callback = pd.get("callback").toString();
			return new JSONObject(map);
		} else {
			return map;
		}
	}

	/**
	 * 返回json数组
	 * @param list
	 * @return String
	 */
	public static String returnObject(List list) throws Exception {
		if(list==null)return null;
		JSONArray jarray = new JSONArray();
		for(Object pd:list){
			JSONObject js = new JSONObject();
			js.put("good_code",((PageData)pd).getString("good_code"));
			js.put("good_name",((PageData)pd).getString("good_name"));
			jarray.put(js);
		}
		return jarray.toString();
	}
}
