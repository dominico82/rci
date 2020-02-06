package kr.co.rci.esign.admin.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 자주 사용하는 {@link Date} 유틸리티 모음 클래스.
 * <p>자주 사용하는 {@link Date} 메서드를 모아두었으며 최신 자바파일은
 * <a href="http://www.fishingnews.co.kr/">Eddie의 Java 자료실</a>에서 관리 중 입니다.
 * @author Eddie
 * @version 1.0.0
 */
public abstract class DateUtils {


	public static final String DATE_PATTERN_DASH = "yyyy-MM-dd";
	public static final String DATE_PATTERN_DASH_YY = "yy-MM-dd";
	/** 기본 날짜 포맷 : {@value #DT_FORMAT_SIMPLE_DATE} */
	public static final String DT_FORMAT_SIMPLE_DATE = "yyyy-MM-dd HH:mm:ss";
	/** 화면표시 날짜 형식 taglib용 simple format [yyyy-MM-dd] */
	public static final String DISP_FORMAT_DATE_TAG_SIMPLE = "--";
	public static final String DISP_FORMAT_DATE_TAG_SIMPLE_TIME = "-- :";
	/** 화면표시 날짜 형식 taglib용 full format [yyyy-MM-dd hh:mm:ss] */
	public static final String DISP_FORMAT_DATE_TAG_DEFAULT = "-- ::";


	/**
	 * 현재 시점의 시간을 {@value #DT_FORMAT_SIMPLE_DATE} 포맷의 문자열로 반환합니다.
	 * @return `yyyy-MM-dd HH:mm:ss` 포맷의 현재 시간
	 */
	public static String currentDateStr() {
		DateFormat df = new SimpleDateFormat(DT_FORMAT_SIMPLE_DATE);
		return df.format(new Date());
	}
	public static String currentDateStr(String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(new Date());
	}

	public static String currentDateSimpleStr() {
		DateFormat df = new SimpleDateFormat(DATE_PATTERN_DASH);
		return df.format(new Date());
	}

	private DateUtils () {};	// SINGLETON


	public static boolean isDate(String date) {
		return isDate(date, DATE_PATTERN_DASH);
	}

	public static boolean isDate(String date, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		DateTime dt = new DateTime();
		try {
			dt = fmt.parseDateTime(date);
		} catch (Exception e) {
			return false;
		}

		if (!fmt.print(dt).equals(date)) {
			return false;
		}
		return true;
	}

	public static boolean greaterThan(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime()) {
			return true;
		}
		return false;
	}

	public static boolean greaterThan(String date1, String date2, String pattern) {
		if (convertStringToDate(date1, pattern).getTime() > convertStringToDate(date2, pattern).getTime()) {
			return true;
		}
		return false;
	}

	public static Date convertStringToDate(String str, String pattern) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return fmt.parseDateTime(str).toDate();
	}


    /**
     * 날짜형식 변환
     * 20140101 --> 2014-01-01
     * @param dt 날짜
     * @param separator 구분자(형식)
     * @return 4-2-2[-2-2-2] 구분자에 의한 포멧 변환된 날짜
     */
    public static String formatDate(String dt, String separator) {
        StringBuffer ret = new StringBuffer();
        dt = dt.replaceAll("[^\\d]*", "");

        if(dt.length() >= 8) {
            if(DISP_FORMAT_DATE_TAG_SIMPLE.equals(separator)) {
                // yyyyMMdd
                dt = dt.substring(0, 8);
            }
            else if(DISP_FORMAT_DATE_TAG_SIMPLE_TIME.equals(separator)) {
            	dt = dt.substring(0, 12);
            }
            else if(dt.length() >= 14) {
                dt = dt.substring(0, 14);
            }
        }

        if (dt.length() < 4) {
            ret.append(dt);
        } else {
            int i = 0;
            ret.append(dt.substring(0, 4));
            if (separator.length() > i) {
                ret.append(separator.charAt(i++));
            }
            dt = dt.substring(4);
            while (dt.length() >= 2) {
                ret.append(dt.substring(0, 2));
                if (separator.length() > i) {
                    ret.append(separator.charAt(i++));
                }
                dt = dt.substring(2);
            }
        }
        return ret.toString();
    }

    /**
     * @see CoCommonFunc#formatDate(String, String)
     * @param dt 대상 날짜 문자열
     * @return yyyy-mm-dd hh24:mi:ss 포멧 변환된 날짜
     */
    public static String formatDate(String dt) {
        return formatDate(dt, DISP_FORMAT_DATE_TAG_DEFAULT);
    }

    /**
     * @see CoCommonFunc#formatDate(String, String)
     * @param dt 대상 날짜 문자열
     * @return yyyy-mm-dd 포멧 변환된 날짜
     */
    public static String formatDateSimple(String dt) {
        return formatDate(dt, DISP_FORMAT_DATE_TAG_SIMPLE);
    }
    public static String formatDateTime(String dt) {
        return formatDate(dt, DISP_FORMAT_DATE_TAG_SIMPLE_TIME);
    }
}
