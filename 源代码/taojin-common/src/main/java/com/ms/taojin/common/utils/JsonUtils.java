package com.ms.taojin.common.utils;

import com.ms.taojin.common.json.IJsonConvertor;
import com.ms.taojin.common.json.INameFilter;
import com.ms.taojin.common.json.impl.FastJsonConvertor;

public class JsonUtils {

    private static IJsonConvertor jsonConvertor = new FastJsonConvertor();

    private JsonUtils() {
    }

    public static String convert(Object value) {
        return jsonConvertor.convert(value);
    }

    public static String convert(Object value, INameFilter nameFilter) {
        return jsonConvertor.convert(value, nameFilter);
    }

    public static <T> T parse(String value, Class<T> clazz) {
        return jsonConvertor.parse(value, clazz);
    }
}
