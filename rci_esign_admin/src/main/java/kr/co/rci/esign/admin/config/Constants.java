package kr.co.rci.esign.admin.config;

public interface Constants {
	/** 정적 리소스 기본 패키지 classpath */
	public static final String STATIC_PATH = "classpath:/static";
	
	/** 정적 리소스 종류 */
	public final static String[] STATIC_RES = {
			"/js" // 자바스크립트
			, "/css" // 스타일 시트
			, "/images" // 정적 이미지 리소스
			, "/fonts" // 폰트
			, "/samples" // 샘플
	};
	/* 세션 타임아웃 시간 (30분) */
	public final static int SESSION_TIME_OUT = 30; //분
	
	/**
	 *  정적 리소스 매핑 URL 패턴 ({@code CLASSPATH_RESOURCE_LOCATIONS}와 순서 맞출 것)
	 *  @see #CLASSPATH_RESOURCE_LOCATIONS
	 */
	public final static String[] STATIC_RESOURCES_URL_PATTERNS = {
			STATIC_RES[0] + "/**"
			, STATIC_RES[1] + "/**"
			, STATIC_RES[2] + "/**"
			, STATIC_RES[3] + "/**"
			, STATIC_RES[4] + "/**"
	};
	
	/**
	 * 정적 리소스 위치 ({@code STATIC_RESOURCES_URL_PATTERN}와 순서 맞출 것)
	 * @see #STATIC_RESOURCES_URL_PATTERNS
	 */
	public final static String[] CLASSPATH_RESOURCE_LOCATIONS = {
			STATIC_PATH + STATIC_RES[0] + "/"
			, STATIC_PATH + STATIC_RES[1] + "/"
			, STATIC_PATH + STATIC_RES[2] + "/"
			, STATIC_PATH + STATIC_RES[3] + "/"
			, STATIC_PATH + STATIC_RES[4] + "/"
	};
}
