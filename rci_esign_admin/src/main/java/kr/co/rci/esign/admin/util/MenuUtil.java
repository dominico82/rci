package kr.co.rci.esign.admin.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Maps;

import kr.co.rci.esign.admin.constant.AppConstants;
import kr.co.rci.esign.admin.constant.Url;

/**
 * 메뉴 관리 유틸리티
 * @author Think-Tree Inc.
 * @version 1.0.2
 */
public class MenuUtil {
	private static Map<String, String> urlMap = getUrlMap(Url.class.getClasses());
	static private Map<String, Menu> menuMap = Maps.newLinkedHashMap();

	private static Logger logger = LoggerFactory.getLogger(MenuUtil.class);


	private static Menu menu = new Menu("홈", Url.MAIN.INDEX, null);

	static {
		boolean IS_NOT_MENU_PAGE = true; //
	}

	public static void setMenu() {
		menu = new Menu("홈", Url.MAIN.INDEX, null);

		menu//
		.addMenu(// 컨텐츠관리
				new Menu("컨텐츠관리", Url.CONTENTS.PATH)//
						.addMenu(new Menu("이미지 관리", Url.CONTENTS.VISUAL_SETTING))
						.addMenu(new Menu("공지사항", Url.CONTENTS.NOTICE_LIST))
						.addMenu(new Menu("자주 묻는 질문", Url.CONTENTS.FAQ_LIST))
						.addMenu(new Menu("사용자 가이드", Url.CONTENTS.USER_GUIDE_LIST))
						)
		.addMenu(// SCA관리
				new Menu("SCA관리", Url.USER.PATH)//
						.addMenu(new Menu("가입사용자", Url.USER.USER_LIST))
						.addMenu(new Menu("로그인이력", Url.USER.USER_LOGIN_HIST))
						.addMenu(new Menu("휴면/퇴사사용자", Url.USER.USER_DORMANT_LIST))
						.addMenu(new Menu("전체사용자", Url.USER.USER_TOTAL_LIST))
						)
		.addMenu(// 현황관리
				new Menu("현황관리", Url.STATUS.PATH)//
						.addMenu(new Menu("할부상담신청현황(대면)", Url.STATUS.STATUS_LIST))
//						.addMenu(new Menu("할부금융신청현황(비대면)", ""))
//						.addMenu(new Menu("타임스탬프현황", ""))
//						.addMenu(new Menu("알림발송", ""))
						)
		.addMenu( //서식관리
				new Menu("서식관리", Url.FORM.LIST)
						.addMenu(new Menu("계약서", Url.FORM.LIST + "/" + AppConstants.CODE_FORM_TYPE_CONTRACT))
						.addMenu(new Menu("인수증", Url.FORM.LIST + "/" + AppConstants.CODE_FORM_TYPE_TAKEOVER))
						.addMenu(new Menu("하이패스", Url.FORM.LIST + "/" + AppConstants.CODE_FORM_TYPE_HIPASS))
						.addMenu(new Menu("선포인트", Url.FORM.LIST + "/" + AppConstants.CODE_FORM_TYPE_POINT))
						.addMenu(new Menu("입금증", Url.FORM.LIST + "/" + AppConstants.CODE_FORM_TYPE_RECEIPT))
						.addMenu(new Menu("면세신고서", Url.FORM.LIST + "/" + AppConstants.CODE_FORM_TYPE_TAX))
						.addMenu(new Menu("반품신청서", Url.FORM.LIST + "/" + AppConstants.CODE_FORM_TYPE_DENY))
						.addMenu(new Menu("가망고객", Url.FORM.LIST + "/" + AppConstants.CODE_FORM_TYPE_CUSTOMER))
				)
		.addMenu( //통계
				new Menu("통계", Url.STASTICS.PATH)
						.addMenu(new Menu("접속자 수", Url.STASTICS.ACCESS_LIST ))
				)
		.addMenu( //코드관리
				new Menu("코드관리", Url.CODE.PATH)
						.addMenu(new Menu("코드관리", Url.CODE.CODE_LIST ))
				)
		.addMenu( //관리자관리
				new Menu("관리자관리", Url.MANAGER.PATH)
						.addMenu(new Menu("관리자관리", Url.MANAGER.MANAGER_LIST ))
				)
		;
	}


	private static Map<String, String> getMap(String prefix, Class<?> target) {
		Map<String, String> map = new HashMap<String, String>();
		for (Field fields : target.getFields()) {
			try {
				Object object = fields.get(target);
				if (object instanceof String) {
					String name = fields.getName();
					if (!"NAME".equals(name) && !name.contains("_JSP") && !name.contains("PERMISSION")) {
						map.put(prefix + "." + name, (String) object);
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	private static Map<String, String> getUrlMap(Class[] classes) {
		Map<String, String> urlMap = new HashMap<String, String>();
		for (Class<?> clazz : classes) {
			urlMap.putAll(getMap(clazz.getSimpleName(), clazz));
		}
		return urlMap;
	}

	public static String getUrl(String key, String val1, String val2) {
		if (!urlMap.containsKey(key)) {
			throw new RuntimeException(key + "에 해당하는 URL이 없습니다.");
		}
		String url = urlMap.get(key);
		if (val1 != null) {
			url = url.replaceFirst("\\{[^\\}]*\\}", val1);
		}
		if (val2 != null) {
			url = url.replaceFirst("\\{[^\\}]*\\}", val2);
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		return request.getContextPath() + url;
	}

	public static String getUrl(String key, String val1) {
		return getUrl(key, val1, null);
	}

	public static String getUrl(String key) {
		return getUrl(key, null, null);
	}

	public static Menu getMenu(String url) {
		if(menuMap.containsKey(url)) {
			return menuMap.get(url);
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String ctp = request.getContextPath();

		if(menuMap.containsKey(ctp + url)) {
			return menuMap.get(ctp + url);
		}

		if(url.contains(ctp)) {
			url = url.replaceFirst(ctp, "");
		}
		return menuMap.get(url);
	}
	public static List<Menu> getMenuList() {
		setMenu();
		return menu.getMenuList();
	}

	// Tiles Path
	public static String replaceValue(String url, String val1, String val2) {
		if (val1 != null)
			url = url.replaceFirst("\\{[^\\}]*\\}", val1);
		if (val2 != null)
			url = url.replaceFirst("\\{[^\\}]*\\}", val2);
		return url;
	}

	public static String replaceValue(String url, String val1) {
		return replaceValue(url, val1, null);
	}

	public static void addMenu(String url, Menu menu) {
		if (!menuMap.containsKey(url)) {
			menuMap.put(url, menu);
		}
	}
}
