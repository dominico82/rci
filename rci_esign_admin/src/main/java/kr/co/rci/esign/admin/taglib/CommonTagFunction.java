package kr.co.rci.esign.admin.taglib;

import kr.co.rci.esign.admin.constant.AppConstants;
import kr.co.rci.esign.admin.util.StringUtils;

/**
 * 어플리케이션 속성에 종속되지 않는 공통 태그 라이브러리
 * @author Think-Tree Inc.
 * @version 1.0.0
 */
public final class CommonTagFunction {

	private static final String DEFAULT_ATTR_NAME = "on";

	/**
	 * <pre>
	 * 1. 설명 : element 'on' 처리
	 * 2. 동작 : 현재 URL을 분석하여, 전달받은 pathName과 매칭될 경우 문자열 'on'을 반환한다.
	 * 3. Input :
	 * 4. Output :
	 * 5. 수정내역
	 * ----------------------------------------------------------------
	 * 변경일                 작성자                                            변경내용
	 * ----------------------------------------------------------------
	 * 2018. 2. 19.     Aiden         최초작성
	 * ----------------------------------------------------------------
	 * </pre>
	 */
	public static String menuOn(String currUrl, String pathName) {
		return menuOn(currUrl, pathName, DEFAULT_ATTR_NAME);
	}

	/**
	 * <pre>
	 * 1. 설명 : element 속성값 처리
	 * 2. 동작 : 현재 URL을 분석하여, 전달받은 pathName과 매칭될 경우 입력받은 attrName을 반환한다.
	 * 3. Input :
	 * 4. Output :
	 * 5. 수정내역
	 * ----------------------------------------------------------------
	 * 변경일                 작성자                                            변경내용
	 * ----------------------------------------------------------------
	 * 2018. 2. 19.     Aiden         최초작성
	 * ----------------------------------------------------------------
	 * </pre>
	 */
	public static String menuOn(String currUrl, String pathName, String attrName) {

		if(kr.co.rci.esign.admin.util.StringUtils.hasText(attrName)) {
			throw new NullPointerException("전달받은 'attrName'이 null값이거나 공백입니다.");
		}
		return (currUrl.contains(pathName)) ? attrName : "";
	}

	/**
	 * <pre>
	 * 1. 설명 : element 속성값 처리
	 * 2. 동작 : 현재 URL을 분석하여, 전달받은 pathName과 매칭되지 않을 경우 입력받은 attrName을 반환한다.
	 * 3. Input :
	 * 4. Output :
	 * 5. 수정내역
	 * ----------------------------------------------------------------
	 * 변경일                 작성자                                            변경내용
	 * ----------------------------------------------------------------
	 * 2018. 2. 19.     Aiden         최초작성
	 * ----------------------------------------------------------------
	 * </pre>
	 */
	public static String menuNotOn(String currUrl, String pathName, String attrName) {
//		attrName = StringUtil.isEmptyTrimmed(attrName) ? DEFAULT_ATTR_NAME : attrName;
		if(StringUtils.hasText(attrName)) {
			throw new NullPointerException("전달받은 'attrName'이 null값이거나 공백입니다.");
		}
		return (currUrl.contains(pathName)) ? "" : attrName;
	}
	/**
	 * 전달받은 테이터 크기({@code byte})를 단위로 표현합니다.
	 * <p><pre class="code">
	 * transBytes(1024) = "1.0 KB"
	 * transBytes(5242880) = "5.2 MB"
	 * </pre>
	 * @param bytes 예: 1028
	 * @return 단위가 변환된 데이터 크기
	 * @author Hannibal
	 * @since 1.0.0
	 */
	public static String tranBytes(int bytes){
		int unit = 1000;
		if (bytes < unit) return bytes + " Bytes";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = ("KMGTPE").charAt(exp-1) + "";
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

    /**
     * CoConstDef에 정의된 변수 값 참조
     * @param CoConstDef에 정의된 필드명
     * @return 해당 필드 값
     */
    public static String getCoConstDefVal(String nm) {
        try {
            return (String) AppConstants.class.getField(nm).get(null);
        } catch (Exception e) {
            throw new RuntimeException(nm + ":" + e);
        }
    }

}
