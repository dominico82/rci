package kr.co.rci.esign.admin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.codec.binary.Base32;

/**
 * 자주 사용하는 {@link String} 유틸리티 모음 클래스.
 * <p>이 클래스는 코어 자바에서 제공하는 {@link String} 과 {@link StringBuilder}
 * 클래스에서 제공하는 기능들을 사용하기 쉽게 재정의 하였습니다.
 * @author Eddie
 * @version 1.0.1
 */
public abstract class StringUtils {

	  private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);
	//---------------------------------------------------------------------
	// String 포맷 관련 메서드(Method) 모음
	//---------------------------------------------------------------------
	/**
	 * 전달받은 문자열{@code String}이 비어있는지 확인합니다.
	 * <p>이 메서드는 Object를 파라미터로 받아, {@code null} 이거나 공백 문자인지 비교합니다.
	 * <p>파라미터로 전달되는 Object가 non-null이거나 non-String일 경우에는 {@code true}를 반환하지 않습니다.
	 * <p><pre class="code">
	 * StringUtils.isEmpty(null) = false;
	 * StringUtils.isEmpty("") = false;
	 *
	 * StringUtils.isEmpty("  ") = true;
	 * StringUtils.isEmpty("text") = true;
	 * </pre>
	 * @param {@link String}으로 사용되는 str
	 * @since 1.0.0
	 */
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}

	/**
	 * 전달받은 {@code CharSequence}이 {@code null}이거나 길이(length)가 0 이상인지 확인합니다.
	 * <p>Note: 이 메서드는 전달받은 str이 완전한 공백({@code null} or {@code ""})인지 확인합니다.
	 * <p><pre class="code">
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * @param {@code String}타입의 str을 받아 {@code null}이거나 공백 문자인지 확인합니다.
	 * @return {@code String}이 {@code null}이 아니거나 length가 0이상일 경우 {@code true}를 반환합니다.
	 * @see #hasText(String)
	 * @since 1.0.0
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 전달받은 {@code CharSequence}이 {@code null}이거나 길이(length)가 0 이상인지 확인합니다.
	 * <p>Note: 이 메서드는 전달받은 str이 완전한 공백({@code null} or {@code ""})인지 확인합니다.
	 * <p><pre class="code">
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * @param {@code String}타입의 str을 받아 {@code null}이거나 공백 문자인지 확인합니다.
	 * @return {@code String}이 {@code null}이 아니거나 length가 0이상일 경우 {@code true}를 반환합니다.
	 * @see #hasLength(CharSequence)
	 * @see #hasText(String)
	 * @since 1.0.0
	 */
	public static boolean hasLength(String str) {
		return (str != null && !str.isEmpty());
	}

	/**
	 * 전달받은 {@code CharSequence}에 <em>text</em>가 포함되어 있는지 확인합니다.
	 * <p>전달 받은 str이 {@code null}이거나 공백문자 이거나, 무의미한 공백({@code ""})으로만 이루어져 있을 경우
	 * {@code false}를 반환합니다.
	 * <p><pre class="code">
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * @param {@code CharSequence}타입의 str({@code null}도 가능합니다).
	 * @return {@code CharSequence}타입이 {@code null}이거나 공백을 제외한 문자열의 길이(length)가 0이상일 경우
	 * {@code true}를 반환합니다. (공백(whitespace)로만 이뤄진 문자열은 {@code false}를 반환합니다)
	 * @see #hasText(String)
	 * @since 1.0.0
	 */
	public static boolean hasText(CharSequence str) {
		return (hasLength(str) && containsText(str));
	}

	/**
	 * 전달받은 {@code CharSequence}에 <em>text</em>가 있는지 확인합니다.
	 * <p>전달 받은 str이 {@code null}이거나 공백문자 이거나, 무의미한 공백({@code ""})으로만 이루어져 있을 경우
	 * {@code false}를 반환합니다.
	 * <p><pre class="code">
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * @param {@code CharSequence}타입의 str({@code null}도 가능합니다).
	 * @return {@code CharSequence}타입이 {@code null}이거나 공백을 제외한 문자열의 길이(length)가 0이상일 경우
	 * {@code true}를 반환합니다. (공백(whitespace)로만 이뤄진 문자열은 {@code false}를 반환합니다)
	 * @see #hasText(CharSequence)
	 * @since 1.0.0
	 */
	public static boolean hasText(String str) {
		return (hasLength(str) && containsText(str));
	}

	/*
	 * !! private Method.
	 * Modifier를 private 레벨 이상으로 설정하지 말아주세요
	 */
	private static boolean containsText(CharSequence str) {
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 전달받은 문자열을 Base32로 인코딩 합니다.
	 * <p>Note: 전달받은 문자열이 {@code null}일 경우 {@code null}을 반환합니다.
	 * <p><pre class="code">
	 * StringUtils.encodeBase32(null) = null;
	 * StringUtils.encodeBase32("") = "";
	 * StringUtils.encodeBase32("thinktree") = "ORUGS3TLORZGKZI=";
	 * </pre>
	 * @param str {@code String}타입의 문자열
	 * @return {@code Base32}로 인코딩된 문자열을 반환합니다. 공백 문자일 경우 공백을 반환합니다.
	 * @author Eddie Cho
	 * @since 1.0.1
	 */
	public static String encodeBase32(String str) {
		if(hasText(str)){
			return new Base32().encodeAsString(str.getBytes());
		}
		else return null;
	}

	//---------------------------------------------------------------------
	// String Array 관련 메서드(Method) 모음
	//---------------------------------------------------------------------

	/**
	 * 전달받은 문자열 배열({@code strArr})에 문자열({@code str})을 요소로 추가합니다.
	 * <p> Note: 파라미터{@code str}에 대한 whitespace를 확인하지 않습니다.
	 * <br>전달받은 문자열 배열이 {@code null}일 경우, 새 문자열 배열을 생성하고 {@code str}을 요소로 추가합니다.
	 *
	 * @param strArr
	 * @param str
	 * @return copy된 새 문자열 배열을 반환합니다({@code null}은 반환하지 않습니다).
	 * @since 1.0.0
	 */
	public static String[] addStringToArray(String[] strArr, String str) {
		if( strArr == null || strArr.length == 0 ) {
			return new String[] {str};
		}

		String[] newArr = new String[strArr.length + 1];
		System.arraycopy(strArr, 0, newArr, 0, strArr.length);
		newArr[strArr.length] = str;
		return newArr;
	}

	/**
	 * 전달받은 String 배열인 {@code strArr}를 List<String>으로 변환합니다.
	 * <p>Note: strArr가 {@code null}이거나 비어있는 배열일 경우 비어있는 {@link List}를 반환합니다.
	 * @param {@link ArrayList}로 변환할 {@link String} 배열
	 * @return {@code ArrayList<String>}을 반환합니다.
	 * 전달받은 strArr이 {@code null}이거나 비어있는 배열일 경우 {@code null}을 반환하지 않고
	 * 비어있는 {@link List}를 반환합니다.
	 * @since 1.0.0
	 */
	public static List<String> convertArrayToList(String[] strArr) {
		List<String> resultList = new ArrayList<>();
		if( strArr == null ) {
			return resultList;
		}

		return Arrays.asList(strArr);
	}

	/**
	 * 전달받은 {@code String} {@code Collection}을 {@code String}배열로 변환합니다..
	 * <p>{@code Collection}에는 반드시 {@code String} 요소만 있어야 합니다.
	 * @param 문자열 배열로 변환할 {@code String} {@code Collection}
	 * @return {@code String} 배열
	 * @since 1.0.0
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}

		return collection.toArray(new String[collection.size()]);
	}


	private StringUtils () {};	// SINGLETON

	/**
	 * trim한 문자열이 null 또는 공백일 경우 참 반환<br><br>
	 *
	 * StringUtils.isEmptyTrimmed(" ") = true
	 *
	 * @param str 문자열
	 * @return trim한 문자열이 null 또는 공백일 경우 true
	 */
	public static boolean isEmptyTrimmed(String str) {
		return (str == null || str.trim().length() == 0);
	}

	public static String avoidNull(String s) {
		return (s == null || s.trim().length() == 0) ? "" : s;
	}
	public static String avoidNull(String s, String defaultStr) {
		return (s == null || s.trim().length() == 0) ? defaultStr : s;
	}

	public static String encodeFileNm(String fileName, String browser) {

	    String encodedFilename = null;
	    // if (browser.equals("MSIE")) { 2017.09.29 보안수정 RH.Jung
	    // PositionLiteralsFirstInComparisons
	    if ("MSIE".equals(browser)) {
	      try {
	        encodedFilename = URLEncoder.encode(fileName, "UTF-8");
	      } catch (UnsupportedEncodingException e) {
	        // e.printStackTrace(); 2017.09.29 보안수정 RH.Jung AvoidPrintStackTrace
	        logger.error(e.getMessage());
	      }
	      // } else if (browser.equals("Firefox")) { 2017.09.29 보안수정 RH.Jung
	      // PositionLiteralsFirstInComparisons
	    } else if ("Firefox".equals(browser)) {
	      try {
	        encodedFilename =

	            "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
	      } catch (UnsupportedEncodingException e) {
	        // e.printStackTrace(); 2017.09.29 보안수정 RH.Jung AvoidPrintStackTrace
	        logger.error(e.getMessage());
	      }
	      // } else if (browser.equals("Opera")) { 2017.09.29 보안수정 RH.Jung
	      // PositionLiteralsFirstInComparisons
	    } else if ("Opera".equals(browser)) {
	      try {
	        encodedFilename =

	            "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
	      } catch (UnsupportedEncodingException e) {
	        // e.printStackTrace(); 2017.09.29 보안수정 RH.Jung AvoidPrintStackTrace
	        logger.error(e.getMessage());
	      }
	      // } else if (browser.equals("Chrome")) { 2017.09.29 보안수정 RH.Jung
	      // PositionLiteralsFirstInComparisons
	    } else if ("Chrome".equals(browser)) {
	      StringBuffer sb = new StringBuffer();
	      for (int i = 0; i < fileName.length(); i++) {
	        char c = fileName.charAt(i);
	        if (c > '~') {
	          try {
	            sb.append(URLEncoder.encode("" + c, "UTF-8"));
	          } catch (UnsupportedEncodingException e) {
	            // e.printStackTrace(); 2017.09.29 보안수정 RH.Jung AvoidPrintStackTrace
	            logger.error(e.getMessage());
	          }
	        } else {
	          sb.append(c);
	        }
	      }
	      encodedFilename = sb.toString();
	      // } else if (browser.equals("Safari")) { 2017.09.29 보안수정 RH.Jung
	      // PositionLiteralsFirstInComparisons
	    } else if ("Safari".equals(browser)) {
	      try {
	        encodedFilename =

	            "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
	      } catch (UnsupportedEncodingException e) {
	        // e.printStackTrace(); 2017.09.29 보안수정 RH.Jung AvoidPrintStackTrace
	        logger.error(e.getMessage());
	      }
	    } else {
	      try {
	        encodedFilename = URLEncoder.encode(fileName, "UTF-8");
	      } catch (UnsupportedEncodingException e) {
	        // e.printStackTrace(); 2017.09.29 보안수정 RH.Jung AvoidPrintStackTrace
	        logger.error(e.getMessage());
	      }
	    }

	    return encodedFilename;

	}
}
