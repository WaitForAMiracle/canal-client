package top.javatool.canal.client.util;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * @author yangpeng
 */
public class StringConvertUtil {


    private static String[] PARSE_PATTERNS = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public StringConvertUtil() {
    }

    static Object convertType(Class<?> type, String columnValue) {
        if(columnValue==null){
            return null;
        }else if (type.equals(Integer.class)) {
            if (StringUtils.isEmpty(columnValue)) {
                return null;
            }
            return Integer.parseInt(columnValue);
        } else if (type.equals(Long.class)) {
            if (StringUtils.isEmpty(columnValue)) {
                return null;
            }
            return Long.parseLong(columnValue);
        } else if (type.equals(Boolean.class)) {
            if (StringUtils.isEmpty(columnValue)) {
                return null;
            }
            return convertToBoolean(columnValue);
        } else if (type.equals(BigDecimal.class)) {
            if (StringUtils.isEmpty(columnValue)) {
                return null;
            }
            return new BigDecimal(columnValue);
        } else if (type.equals(Double.class)) {
            if (StringUtils.isEmpty(columnValue)) {
                return null;
            }
            return Double.parseDouble(columnValue);
        } else if (type.equals(Float.class)) {
            if (StringUtils.isEmpty(columnValue)) {
                return null;
            }
            return Float.parseFloat(columnValue);
        } else if (type.equals(Date.class)) {
            if (StringUtils.isEmpty(columnValue)) {
                return null;
            }
            return parseDate(columnValue);
        } else if (type.equals(java.sql.Date.class)) {
            if (StringUtils.isEmpty(columnValue)) {
                return null;
            }
            return parseDate(columnValue);
        } else {
            return columnValue;
        }
    }

    private static Date parseDate(String str) {
        if (str == null) {
            return null;
        } else {
            try {
                return DateUtils.parseDate(str, PARSE_PATTERNS);
            } catch (ParseException var2) {
                return null;
            }
        }
    }

    private static Date parseSqlDate(String str) {
        if (str == null) {
            return null;
        } else {
            try {
                Date date = DateUtils.parseDate(str, PARSE_PATTERNS);
                return new java.sql.Date(date.getTime());
            } catch (ParseException var2) {
                return null;
            }
        }
    }

    private static boolean convertToBoolean(String value) {
        return "1".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
    }
}
