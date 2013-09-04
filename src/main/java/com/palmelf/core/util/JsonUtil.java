package com.palmelf.core.util;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class JsonUtil {
	public static JSONSerializer getJSONSerializer(String[] dateFields) {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude(new String[] { "class" });
		serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"),
				dateFields);
		return serializer;
	}
}
