package kr.co.rci.esign.admin.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Renault Sys 서비스에 사용되는 상수
 *
 * @author Think-Tree Inc.
 * @version 1.0.0
 */
public final class AppConstants {
	/** Web Application Display Name : {@value #APP_DISPLAY_NAME} */
	public static final String APP_DISPLAY_NAME             = "Renault: Electronic Contract System Management";

	/** Application의 기본 패키지 명: {@value #APP_DEFAULT_PACKAGE_NAME} */
	public static final String APP_DEFAULT_PACKAGE_NAME     = "kr.co.rci.esign.admin";
	/** database와 연결되는 entity 클래스가 들어있는 패키지 명: {@value #ENTITY_PACKAGE_NAME} */
	public static final String ENTITY_PACKAGE_NAME          = APP_DEFAULT_PACKAGE_NAME+".domain";

	/** Application의 기본 패키지 경로: {@value #APP_DEFAULT_PACKAGE_PATH} */
	public static final String APP_DEFAULT_PACKAGE_PATH     = "kr/co/rci/esign/admin";

	public static final int DEFAULT_PAGE_NUMBER = 0;
	public static final int DEFAULT_PAGE_SIZE = 15;
	public static final boolean USE_PAGE_GO_FIRST_LAST = true; // pagination 처음으로 마지막으로 버튼 사용여부
	public static final boolean USE_PAGE_GO_PAGEBLOCK = false; // true: 이전, 다음 버튼시 block 단위로 이동, false: 이전, 다음 버튼시, 페이지 단위로 이동

	public static final String CODE_FORM_TYPE = "formType";
	/** 서식 종류 : 계약서 */
	public static final String CODE_FORM_TYPE_CONTRACT = "02";
	/** 서식 종류 : 인수증 */
	public static final String CODE_FORM_TYPE_TAKEOVER = "07";
	/** 서식 종류 : 하이패스 */
	public static final String CODE_FORM_TYPE_HIPASS = "16";
	/** 서식 종류 : 선포인트 */
	public static final String CODE_FORM_TYPE_POINT = "90";
	/** 서식 종류 : 입금증 */
	public static final String CODE_FORM_TYPE_RECEIPT = "14";
	/** 서식 종류 : 면제신고서 */
	public static final String CODE_FORM_TYPE_TAX = "15";
	/** 서식 종류 : 반품신고서 */
	public static final String CODE_FORM_TYPE_DENY = "91";
	/** 서식 종류 : 가망고객 */
	public static final String CODE_FORM_TYPE_CUSTOMER = "92";

	public static final String CODE_FORM_DOC_TYPE = "formDocType";
	/** 세부서식종류(계약서용) : 직영점 */
	public static final String CODE_FORM_DOC_TYPE_RETAIL = "100";
	/** 세부서식종류(계약서용) : Clio 직영 */
	public static final String CODE_FORM_DOC_TYPE_RETAIL_CLIO = "101";
	/** 세부서식종류(계약서용) : 대리점 */
	public static final String CODE_FORM_DOC_TYPE_AGENCY = "110";
	/** 세부서식종류(계약서용) : Clio 대리점 */
	public static final String CODE_FORM_DOC_TYPE_AGENCY_CLIO = "111";

	/** 세부서식종류(인수증) : 일반 */
	public static final String CODE_FORM_DOC_TYPE_RECEIPT_NORMAL = "300";
	/** 세부서식종류(인수증) : SM/QM */
	public static final String CODE_FORM_DOC_TYPE_RECEIPT_SMQM = "301";
	/** 세부서식종류(인수증) : 트위지 */
	public static final String CODE_FORM_DOC_TYPE_RECEIPT_TWIZY = "302";



	public static final String CODE_FORM_BUYER_TYPE = "formBuyerType";
	/** 세부서식종류(확장) : 매수인 1~2 */
	public static final String CODE_FORM_BUYER_TYPE_1 = "1";
	/** 세부서식종류(확장) : 매수인 3~4 */
	public static final String CODE_FORM_BUYER_TYPE_2 = "3";

	/** E-Best 전용 코드 표시명 변환 */
	public static final Map<String, String> CODE_NAME_MAP = new HashMap<String, String>(){
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		// e-best전용 코드의 경우 코드관리르 별도로 하지 않기 때문에, 임의의 코드 병을 설정한다. 기본적으로 필드명을 코드 값으로 사용
		// 필드명 + 코드 : 표시명


		{
			// 서식관리 ----------------------------
			put(CODE_FORM_TYPE + "-" + CODE_FORM_TYPE_CONTRACT, "계약서");
			put(CODE_FORM_TYPE + "-" + CODE_FORM_TYPE_TAKEOVER, "인수증");
			put(CODE_FORM_TYPE + "-" + CODE_FORM_TYPE_HIPASS, "하이패스");
			put(CODE_FORM_TYPE + "-" + CODE_FORM_TYPE_POINT, "선포인트");
			put(CODE_FORM_TYPE + "-" + CODE_FORM_TYPE_RECEIPT, "입금증");
			put(CODE_FORM_TYPE + "-" + CODE_FORM_TYPE_TAX, "면제신고서");
			put(CODE_FORM_TYPE + "-" + CODE_FORM_TYPE_DENY, "반품신고서");
			put(CODE_FORM_TYPE + "-" + CODE_FORM_TYPE_CUSTOMER, "가망고객");

			put(CODE_FORM_DOC_TYPE + "-" + CODE_FORM_DOC_TYPE_RETAIL, "직영점");
			put(CODE_FORM_DOC_TYPE + "-" + CODE_FORM_DOC_TYPE_RETAIL_CLIO, "Clio 직영점");
			put(CODE_FORM_DOC_TYPE + "-" + CODE_FORM_DOC_TYPE_AGENCY, "대리점");
			put(CODE_FORM_DOC_TYPE + "-" + CODE_FORM_DOC_TYPE_AGENCY_CLIO, "Clio 대리점");

			put(CODE_FORM_DOC_TYPE + "-" + CODE_FORM_DOC_TYPE_RECEIPT_NORMAL, "일반");
			put(CODE_FORM_DOC_TYPE + "-" + CODE_FORM_DOC_TYPE_RECEIPT_SMQM, "SM/QM6");
			put(CODE_FORM_DOC_TYPE + "-" + CODE_FORM_DOC_TYPE_RECEIPT_TWIZY, "트위지");

			put(CODE_FORM_BUYER_TYPE + "-" + CODE_FORM_BUYER_TYPE_1, "매수인 1~2");
			put(CODE_FORM_BUYER_TYPE + "-" + CODE_FORM_BUYER_TYPE_2, "매수인 3~4");

		}
	};

}
