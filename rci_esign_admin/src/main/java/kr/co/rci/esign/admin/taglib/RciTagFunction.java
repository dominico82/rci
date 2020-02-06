package kr.co.rci.esign.admin.taglib;

import kr.co.rci.esign.admin.util.StringUtils;

/**
 * RSM 전자계약 어플리케이션에서 사용하는 태그 라이브러리 function 모음 입니다.
 * @author eddie
 * @author Hannibal Kwon
 * @version 1.0.0
 */
public final class RciTagFunction {

	/**
	 *  공지사항 구분 코드값 -> label로 변경
	 *  @author Hannibal Kwon
	 * @param code
	 * @return
	 */
	public static final String noticeGubn (String code){
		String result = "";

		switch (code){
			case "NO" : result = "일반";break;
			case "UR" : result = "긴급";break;
			default : result = "오류"; break;
		}

		return result;

	}

	/**
	 * 공지사항 카테고리 코드값 -> label로 변경
	 * @author Hannibal Kwon
	 * @param code
	 * @return
	 */
	public static final String noticeCategory (String code){
		String result = "";

		if(code.contains("D") && code.contains("A") && code.contains("I")){
			result = "전체";
		}else{
			if (code.contains("D") && code.contains("A")){
				result = result + "Web / Android";
			}else if(code.contains("D") && code.contains("I")){
				result = result + "Web / IOS";
			}else if(code.contains("D") && code.length() == 1){
				result = result + "Web";
			}else if(code.contains("A") && code.contains("I") ){
				result = result + "Android / IOS";
			}else if( code.contains("A") && code.length() == 1 ){
				result = result + "Android";
			}else if(code.contains("I") && code.length() == 1  ){
				result = result + "IOS";
			}
		}
		return result;
	}

	public static final String dispFileFormat (String fName, long fSize) {
		if(!StringUtils.isEmpty(fName)) {
			return fName + " (" + String.format("%,d", fSize) + ")";
		}
		return "";
	}
}
