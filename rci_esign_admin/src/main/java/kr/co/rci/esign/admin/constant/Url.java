package kr.co.rci.esign.admin.constant;

import org.springframework.http.HttpStatus;

/**
 * API URL 정의
 *
 * @author Think-Tree Inc.
 * @version 1.0.0
 */
public final class Url {
	public static final String TILES_ROOT = "/views";

	// -------------------------------------------------------------------------------------------------------------
	// Renault eBest Admin
	// -------------------------------------------------------------------------------------------------------------

	public static final class COMMON {
		public static final String FILE_UPLOAD = "/file/uploadAjax";
		public static final String FILE_DOWNLOAD = "/file/downloadAjax";
		public static final String FILE_UPLOAD_ANDROID = "/file/uploadAndroidAjax";
		public static final String FILE_UPLOAD_IOS = "/file/uploadIosAjax";
	}

	/** 관리자 메인 화면 */
	public static final class MAIN {
		/** 메인 페이지: {@value #INDEX} */
		public static final String INDEX = "/";
		public static final String INDEX_TILES = TILES_ROOT + "/main";
	}

	/** RSM 사용자 권한 처리 (authentication) */
	public static final class AUTH {
		public static final String PATH = "/auth";
		/** 로그인 페이지: {@value #LOGIN} */
		public static final String LOGIN = PATH + "/login";
		/** The Constant LOGIN_JSP. */
		public static final String LOGIN_JSP = TILES_ROOT + "/auth/login";
		/** 로그인 인증 요청: {@value #LOGIN_PROC} */
		public static final String LOGIN_PROC = PATH + "/loginProc";
		/** 로그아웃 요청: {@value #LOGOUT} */
		public static final String LOGOUT = PATH + "/logout";
	}

	/** 컨텐츠 관리 */
	public static final class CONTENTS {
		public static final String PATH = "/contents";

		/** 이미지 관리 */
		public static final String VISUAL_SETTING = PATH + "/visualSetting";
		public static final String VISUAL_SETTING_JSP = TILES_ROOT + PATH + "/visualSetting";
		/** 이미지 관리 저장 */
		public static final String VISUAL_SETTING_UPDATE = PATH + "/visualSettingUpdate";

		/** 공지사항 목록 */
		public static final String NOTICE_LIST = PATH + "/noticeList";
		public static final String NOTICE_LIST_JSP = TILES_ROOT + PATH + "/noticeList";

		/** 공지사항 등록 & 상세보기 */
		public static final String NOTICE_DETAIL = PATH + "/noticeDetail";
		public static final String NOTICE_DETAIL_JSP = TILES_ROOT + PATH + "/noticeDetail";
		public static final String NOTICE_WRITE = PATH+"/noticeWrite";
		public static final String NOTICE_WRITE_JSP = TILES_ROOT+PATH+"/noticeWrite";
		public static final String NOTICE_VIEW = PATH+"/noticeView";
		public static final String NOTICE_VIEW_JSP = TILES_ROOT+PATH+"/noticeView";
		public static final String NOTICE_UPDATE = PATH+"/noticeUpdate";
		public static final String NOTICE_UPDATE_JSP = TILES_ROOT+PATH+"/noticeUpdate";
		public static final String NOTICE_DELETE = PATH+"/noticeDelete";

		/** 자주묻는질문 */
		public static final String FAQ_LIST = PATH + "/faqList";
		public static final String FAQ_LIST_JSP = TILES_ROOT + PATH + "/faqList";

		/** 자주묻는질문 등록 & 상세보기 **/
		public static final String FAQ_DETAIL = PATH + "/faqDetail";
		public static final String FAQ_DETAIL_JSP = TILES_ROOT + PATH + "/faqDetail";

		/** 사용자 가이드 */
		public static final String USER_GUIDE_LIST = PATH + "/userGuideList";
		public static final String USER_GUIDE_LIST_JSP = TILES_ROOT + PATH + "/userGuideList";

		/** 사용자 가이드 등록 & 상세보기 */
		public static final String USER_GUIDE_DETAIL = PATH + "/userGuideDetail";
		public static final String USER_GUIDE_DETAIL_JSP = TILES_ROOT + PATH + "/userGuideDetail";
	}

	/** 사용자 관리 */
	public static final class USER {
		public static final String PATH = "/user";

		/** 가입사용자 */
		public static final String USER_LIST = PATH + "/userList";
		public static final String USER_LIST_JSP = TILES_ROOT + PATH + "/userList";

		/** 로그인이력 */
		public static final String USER_LOGIN_HIST = PATH + "/userLoginHist";
		public static final String USER_LOGIN_HIST_JSP = TILES_ROOT + PATH + "/userLoginHist";

		/** 휴면/퇴사자 목록 */
		public static final String USER_DORMANT_LIST = PATH + "/userDormantList";
		public static final String USER_DORMANT_LIST_JSP = TILES_ROOT + PATH + "/userDormantList";
		/** 사용자 상태변경 ajax */
		public static final String USER_STAT_CD_UPDATE_AJAX = PATH + "/userStatCdUpdateAjax";
		/** 사용자 사용여부 업데이트 ajax */
		public static final String USER_IS_USE_UPDATE_AJAX = PATH+"/userIsUseUpdateAjax";

		/** 전체사용자 */
		public static final String USER_TOTAL_LIST = PATH + "/userAllList";
		public static final String USER_TOTAL_LIST_JSP = TILES_ROOT + PATH + "/userAllList";
	}

	/** 현황관리 */
	public static final class STATUS {
		public static final String PATH = "/status";

		/** 할부상담신청현황(대면) */
		public static final String STATUS_LIST = PATH + "/statusList";
		public static final String STATUS_LIST_JSP = TILES_ROOT + PATH + "/statusList";
	}

	/** 서식 정보 관리 */
	public static final class FORM {
		public static final String PATH = "/form";

		public static final String LIST = PATH;
		public static final String LIST_REQ = PATH + "/{formType}";
		public static final String EDIT_REQ = PATH + "/{formType}/edit";

		/** 서식 정보 리스트: {@value #LIST} */
		public static final String LIST_CONTRACT_JSP = TILES_ROOT + PATH + "/form-contract-list";
		/** 서식관리 : 계약서 */
		// public static final String EDIT_CONTRACT =
		// PATH+"/"+AppConstants.CODE_FORM_TYPE_CONTRACT+"/edit";
		public static final String EDIT_CONTRACT_JSP = TILES_ROOT + PATH + "/form-contract-edit";

		/** 서식 정보 리스트: 인수증 {@value #LIST} */
		public static final String LIST_TAKEOVER_JSP = TILES_ROOT + PATH + "/form-takeover-list";
		/** 서식관리 : 인수증 */
		public static final String EDIT_TAKEOVER_JSP = TILES_ROOT + PATH + "/form-takeover-edit";

		/** 서식 정보 리스트: {@value #LIST} */
		public static final String LIST_HIPASS_JSP = TILES_ROOT + PATH + "/form-hipass-list";
		/** 서식관리 : 하이패스 */
		public static final String EDIT_HIPASS = PATH + "/" + AppConstants.CODE_FORM_TYPE_HIPASS + "/edit";
		public static final String EDIT_HIPASS_JSP = TILES_ROOT + PATH + "/form-hipass-edit";

		/** 서식 정보 리스트: {@value #LIST} */
		public static final String LIST_POINT_JSP = TILES_ROOT + PATH + "/form-point-list";
		/** 서식관리 : 선포인트 */
		public static final String EDIT_POINT = PATH + "/" + AppConstants.CODE_FORM_TYPE_POINT + "/edit";
		public static final String EDIT_POINT_JSP = TILES_ROOT + PATH + "/form-point-edit";

		/** 서식 정보 리스트: 입금증 {@value #LIST} */
		public static final String LIST_RECEIPT_JSP = TILES_ROOT + PATH + "/form-receipt-list";
		/** 서식관리 : 입금증 */
		public static final String EDIT_RECEIPT = PATH + "/" + AppConstants.CODE_FORM_TYPE_RECEIPT + "/edit";
		public static final String EDIT_RECEIPT_JSP = TILES_ROOT + PATH + "/form-receipt-edit";

		/** 서식 정보 리스트: {@value #LIST} */
		public static final String LIST_TAX_JSP = TILES_ROOT + PATH + "/form-tax-list";
		/** 서식관리 : 면세신고서 */
		public static final String EDIT_TAX = PATH + "/" + AppConstants.CODE_FORM_TYPE_TAX + "/edit";
		public static final String EDIT_TAX_JSP = TILES_ROOT + PATH + "/form-tax-edit";

		/** 서식 정보 리스트: {@value #LIST} */
		public static final String LIST_DENY_JSP = TILES_ROOT + PATH + "/form-deny-list";
		/** 서식관리 : 반푼신고서 */
		public static final String EDIT_DENY = PATH + "/" + AppConstants.CODE_FORM_TYPE_DENY + "/edit";
		public static final String EDIT_DENY_JSP = TILES_ROOT + PATH + "/form-deny-edit";

		/** 서식 정보 리스트: {@value #LIST} */
		public static final String LIST_CUSTOMER_JSP = TILES_ROOT + PATH + "/form-customer-list";
		/** 서식관리 : 가망고객 */
		public static final String EDIT_CUSTOMER = PATH + "/" + AppConstants.CODE_FORM_TYPE_CUSTOMER + "/edit";
		public static final String EDIT_CUSTOMER_JSP = TILES_ROOT + PATH + "/form-customer-edit";

		/** 서식 정보 수정: {@value #UPDATE} */
		public static final String REGISTRATION_AJAX = PATH + "/form-reg/{formType}";
		// public static final String REGISTRATION_VALIDATION =
		// PATH+"/form-valid/{formType}";
		/** 서식 정보 관리(사용 유무): {@value #MANAGEMENT} */
		public static final String MANAGEMENT = PATH + "/{formType}/management";
		public static final String PREVIEW = PATH + "/preview";
		public static final String DELETE = PATH + "/delete";
	}

	/** admin 통계 정보 */
	public static final class STASTICS {
		public static final String PATH = "/statistics";
		/** 접속자 수 정보 조회: {@value #ACCESS_LIST} */
		public static final String ACCESS_LIST = PATH + "/accessUserCount";
		public static final String ACCESS_LIST_JSP = TILES_ROOT + PATH + "/accessUserCount";
		public static final String ACCESS_LIST_AJAX = PATH + "/accessUserCounter";

	}

	/** 코드관리 */
	public static final class CODE {
		public static final String PATH = "/code";
		/** 코드그룹조회 */
		public static final String CODE_LIST = PATH + "/codeList";
		public static final String CODE_LIST_JSP = TILES_ROOT + PATH + "/codeList";

		/** 코드조회상세페이지 */
		public static final String CODE_LIST_DETAIL = PATH + "/codeListDetail";
		public static final String CODE_LIST_DETAIL_JSP = TILES_ROOT + PATH + "/codeListDetail";

		/** 코드_GRP인서트 */
		public static final String CODE_GRP_LIST_INSERT_AJAX = PATH + "/codeGrpListInsert";
		/** 코드인서트 */
 		public static final String CODE_LIST_INSERT_AJAX = PATH + "/codeListInsert" ;

	}

	/** 관리자관리 */
	public static final class MANAGER {
		public static final String PATH = "/manager";
		/** 관리자관리-목록페이지 */
		public static final String MANAGER_LIST = PATH + "/managerList";
		public static final String MANAGER_LIST_JSP = TILES_ROOT + PATH + "/managerList";
		/** 관리자관리-등록페이지 */
		public static final String MANAGER_REG = PATH + "/managerReg";
		public static final String MANAGER_REG_JSP = TILES_ROOT + PATH + "/managerReg";
		/** 관리자관리-아이디 중복 체크 ajax */
		public static final String MANAGER_ID_DUP_CHECK_AJAX = PATH + "/managerIdDupCheckAjax";
		/** 관리자관리-등록 처리 ajax */
		public static final String MANAGER_REG_AJAX = PATH + "/managerRegAjax";
		/** 관리자관리-수정페이지 */
		public static final String MANAGER_MOD = PATH + "/managerMod";
		public static final String MANAGER_MOD_JSP = TILES_ROOT + PATH + "/managerMod";
		/** 관리자관리-수정 ajax */
		public static final String MANAGER_MOD_AJAX = PATH + "/managerModAjax";
		/** 관리자관리-삭제 ajax */
		public static final String MANAGER_DELETE_AJAX = PATH + "/managerDeleteAjax";
	}

	/** 에러 페이지 URL */
	public static final class ERROR {
		public static final String PATH = "/error";
		// 404 에러
		public static final String NOT_FOUND = PATH + "/" + HttpStatus.NOT_FOUND.value();
		// 500 에러
		public static final String SERVER_ERROR = PATH + "/" + HttpStatus.INTERNAL_SERVER_ERROR.value();
	}
}
