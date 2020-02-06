package kr.co.rci.esign.admin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * google Json을 다루는 소소한 유틸리티
 *
 * @author Eddie Cho
 * @version 1.0.0
 */
public abstract class JsonUtils {
	/**
	 * JsonArray를 List<Map<String, String>>으로 변환한다.
	 *
	 * @param jsonArray JSONArray.
	 * @return List<Map<String, Object>>.
	 */
	public static List<Map<String, Object>> getListMapFromJsonArray(JsonArray jsonArray) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if ( jsonArray != null && jsonArray.size() > 0 ) {
			for(JsonElement je : jsonArray)
				list.add( getMapFromJsonObject(je.getAsJsonObject()) );
		}
		return list;
	}

	/**
	 * JsonObject를 Map<String, String>으로 변환한다.
	 *
	 * @param jsonObj
	 * @return Map<String, Object>.
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapFromJsonObject(JsonObject jsonObject) {
		return new Gson().fromJson(jsonObject, Map.class);
	}
}
