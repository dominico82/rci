package kr.co.rci.esign.admin.constant;

import java.util.Locale;

/**
 * WAS 서버 설정에 사용되는 상수
 * @author Eddie
 */
public class ServerConstants {

	/** jsp property url pattern : {@value #JSP_PROPERTY_URL_PATTERN}*/
	public static final String JSP_PROPERTY_URL_PATTERN    = "*.jsp";
	/** jsp page encoding charset : {@value #JSP_PAGE_ENCODING} */
	public static final String JSP_PAGE_ENCODING            = "UTF-8";
	/** jsp prelude file path : {@value #JSP_PRELUDE_PATH} */
	/** Default Locale : KOREA */
	public static final Locale DEFAULT_LOCALE               = Locale.KOREA;
	/** Locale Change Parameter : {@value #LOCALE_CHANGE_PARAM} */
	public static final String LOCALE_CHANGE_PARAM          = "lang";
	/** Tiles definition xml path */
	public final static String[] TILES_LAYOUT_XML_PATH = {
			"WEB-INF/tiles.xml",
			"WEB-INF/tiles-single.xml"
	};

	/** Runtime에서 JSP의 refresh 적용 여부 : {@value #REFRESH_JSP_ON_RUNTIME} */
	public final static boolean REFRESH_JSP_ON_RUNTIME = true;

	/** 정적 리소스 종류 */
	private final static String[] STATIC_RES = {"/js","/css","/images","/favicon","/template", "/font"};
	/**
	 *  정적 리소스 매핑 URL 패턴 ({@code CLASSPATH_RESOURCE_LOCATIONS}와 순서 맞출 것)
	 *  @see #CLASSPATH_RESOURCE_LOCATIONS
	 */
	public final static String[] STATIC_RESOURCES_URL_PATTERNS = {
			STATIC_RES[0]+"/**",
			STATIC_RES[1]+"/**",
			STATIC_RES[2]+"/**",
			STATIC_RES[3]+"/**",
			STATIC_RES[4]+"/**",
			STATIC_RES[5]+"/**"
	};

	/** 정적 리소스 기본 패키지 classpath */
	private static final String STATIC_PATH = "classpath:/static";
	/**
	 * 정적 리소스 위치 ({@code STATIC_RESOURCES_URL_PATTERN}와 순서 맞출 것)
	 * @see #STATIC_RESOURCES_URL_PATTERNS
	 */
	public final static String[] CLASSPATH_RESOURCE_LOCATIONS = {
			STATIC_PATH+STATIC_RES[0]+"/",
			STATIC_PATH+STATIC_RES[1]+"/",
			STATIC_PATH+STATIC_RES[2]+"/",
			STATIC_PATH+STATIC_RES[3]+"/",
			STATIC_PATH+STATIC_RES[4]+"/",
			STATIC_PATH+STATIC_RES[5]+"/"
	};

	/** mapper 클래스가 있는 패키지 : {@value #RSM_MAPPER_PACKAGE} */
	public final static String MAPPER_PACKAGE		= AppConstants.APP_DEFAULT_PACKAGE_NAME+".dao";
	/** mapper xml이 있는 패키지 : {@value #RSM_MAPPER_XML_PACKAGE} */
//	public final static String MAPPER_XML_PACKAGE	= "classpath*:"+AppConstants.APP_DEFAULT_PACKAGE_PATH+"/dao/mapper/*Mapper.xml";
	public final static String MAPPER_XML_PACKAGE	= AppConstants.APP_DEFAULT_PACKAGE_PATH+"/dao/mapper/*Mapper.xml";
	/** 세션 타임 아웃 시간 ( 7200초: 2시간 ) */
	public static final int SESSION_TIME_OUT = 60 * 60 * 2; // 2시간

	//---------------------------------------------------------------------------------------------
	// Tag Library Informations
	//---------------------------------------------------------------------------------------------
	public static final String COMM_TLD_URI  = "/WEB-INF/tlds/common-taglibs.tld";
	public static final String COMM_TLD_PATH = "/WEB-INF/tlds/common-taglibs.tld";

	public static final String RCI_TLD_URI   = "/WEB-INF/tlds/rci-taglibs.tld";
	public static final String RCI_TLD_PATH  = "/WEB-INF/tlds/rci-taglibs.tld";


	/** Server health check URL */
	public static final String HEALTH_CHECK_URL = "/healthCheck";
}
