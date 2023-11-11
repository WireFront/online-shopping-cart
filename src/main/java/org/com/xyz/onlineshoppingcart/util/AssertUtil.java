package org.com.xyz.onlineshoppingcart.util;

import org.apache.commons.lang3.StringUtils;
import org.com.xyz.onlineshoppingcart.exception.CartException;

public class AssertUtil {

    public static void isTrue(boolean expVal, String resultCode, Object... objects) {
        if (expVal) {
            String logString = getLogString(objects);
            throw new CartException(resultCode, logString);
        }
    }

    public static void isBlank(String val, String resultCode, Object... objects) {
        isTrue(StringUtils.isBlank(val), resultCode, objects);
    }

    public static void isNotNull(Object object, String resultCode, Object... objects) {
        isTrue(object != null, resultCode, objects);
    }

    public static void isNull(Object object, String resultCode, Object... objects) {
        isTrue(object == null, resultCode, objects);
    }

    private static String getLogString(Object... objects) {
        StringBuilder builder = new StringBuilder();
        for (Object obj : objects) {
            builder.append(obj);
        }
        return builder.toString();
    }
}