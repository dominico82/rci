package kr.co.rci.esign.admin.taglib;

import kr.co.rci.esign.admin.constant.AppConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class PaginationTag.
 * @author Think-Tree Inc.
 * @Version 1.0.0
 */
public class PaginationTag {

	/** 한 화면에 보여줄 페이지 최대 개수. */
	private static final int MAX_PAGE = 10;

	/** The enabled. */
	private static boolean ENABLED = false;

	/** The disabled. */
	private static boolean DISABLED = true;

	/** The beigin page value. */
	private static BeginPageValue BEIGIN_PAGE_VALUE = BeginPageValue.ZERO;

	/**
	 * The Enum BeginPageValue.
	 */
	public enum BeginPageValue {

		/** The zero. */
		ZERO,
		/** The one. */
		ONE;
	}

	/**
	 * 페이징 처리.
	 *
	 * @param pageNo the page no
	 * @param totalCount the total count
	 * @param listSize the list size
	 * @param jsFunction the js function
	 * @return String 페이징 태그
	 */
	public static String pagination(int pageNo, long totalCount, int listSize, String jsFunction) {
		if (BEIGIN_PAGE_VALUE == BeginPageValue.ZERO) {
			pageNo++;
		}
		// String imgPath = contextPath + "/images";
		if (jsFunction == null || jsFunction.equals(""))
			jsFunction = "goList";
		StringBuffer sb = new StringBuffer();
		// 전체 페이지 개수
		int totalPage = (int) ((totalCount / listSize) + ((totalCount % listSize) == 0 ? 0 : 1));
		// 현재 페이지 방어코드( 전체 페이지 개수 보다 큰 페이지 번호가 들어오면 전체 페이지로 설정)
		pageNo = (pageNo > totalPage) ? totalPage : pageNo;

		// MAX_PAGE 단위의 현재 COUNT 1부터 시작. 예시 ) 값이 2 이면 < 11 12 13 14 15 ...
		int maxPageCurrentCount = (pageNo / MAX_PAGE) + ((pageNo % MAX_PAGE) == 0 ? 0 : 1);
		// MAX_PAGE 단위의 전체 COUNT
		int maxPageTotalCount = (totalPage / MAX_PAGE) + ((totalPage % MAX_PAGE) == 0 ? 0 : 1);
		if (totalCount > 0) { // 데이터가 하나라도 있다면

			if(AppConstants.USE_PAGE_GO_PAGEBLOCK) {
				if (maxPageCurrentCount > 1) {
					// 이전 페이지가 있는 경우
					sb.append(getFirst(ENABLED, getJavascript(jsFunction, 0)));
					sb.append(getPrevious(ENABLED, getJavascript(jsFunction, (maxPageCurrentCount - 1) * MAX_PAGE)));
				} else {
					// 이전 페이지가 없는 경우
					sb.append(getPrevious(DISABLED, ""));
				}
			} else {
				if (pageNo > 1) {
					// 이전 페이지가 있는 경우
					sb.append(getFirst(ENABLED, getJavascript(jsFunction, 1)));
					sb.append(getPrevious(ENABLED, getJavascript(jsFunction, pageNo -1)));
				} else {
					// 이전 페이지가 없는 경우
					sb.append(getPrevious(DISABLED, ""));
				}
			}


			for (int i = ((maxPageCurrentCount - 1) * MAX_PAGE) + 1; i <= (maxPageCurrentCount * MAX_PAGE) && i <= totalPage; i++) {
				if (i != pageNo) {
					sb.append(getPage(ENABLED, getJavascript(jsFunction, i), i));
				} else {
					sb.append(getPage(DISABLED, "", i));
				}
			}


			if(AppConstants.USE_PAGE_GO_PAGEBLOCK) {
				if (maxPageCurrentCount < maxPageTotalCount) {
					sb.append(getNext(ENABLED, getJavascript(jsFunction, (maxPageCurrentCount * MAX_PAGE) + 1)));
					sb.append(getLast(ENABLED, getJavascript(jsFunction, (int) totalCount)));
				} else {
					sb.append(getNext(DISABLED, ""));
				}
			} else {
				if (pageNo < totalPage) {
					sb.append(getNext(ENABLED, getJavascript(jsFunction, pageNo + 1)));
					sb.append(getLast(ENABLED, getJavascript(jsFunction, totalPage)));
				} else {
					sb.append(getNext(DISABLED, ""));
				}
			}

		} else { // 데이터 없는 경우
			sb.append(getPrevious(DISABLED, ""));
			sb.append(getPage(DISABLED, "", 1));
			sb.append(getNext(DISABLED, ""));
		}
		if(sb.length() > 0) {
			return "<span>" + sb.toString() + "</span>";
		}
		return sb.toString();
	}

	/**
	 * Gets the javascript.
	 *
	 * @param jsFunction the js function
	 * @param param the param
	 * @return the javascript
	 */
	private static String getJavascript(String jsFunction, int param) {
		param = param + (BEIGIN_PAGE_VALUE == BeginPageValue.ZERO ? -1 : 0);
		return jsFunction + "(" + param + ");";
	}

	/**
	 * 페이지 링크 생성.
	 *
	 * @param isActive the is active
	 * @param onclick the onclick
	 * @param page the page
	 * @return the page
	 */
	private static String getPage(boolean isActive, String onclick, int page) {
		String activated = "";
		String strPage = String.valueOf(page);
		if (isActive) {
			activated = " class=\"on\"";
			//strPage += " <span class=\"sr-only\">(current)</span>";
		}
		// return "<li" + activated + "><a href=\"#\" onclick=\"" + onclick +
		// "return false;\">" + strPage + "</a></li>";
		return "<a " + activated + " href=\"javascript:;\" onclick=\"" + onclick + "\" >" + strPage + "</a>";
	}

	/**
	 * 이전 페이지.
	 *
	 * @param isDisabled the is disabled
	 * @param onclick the onclick
	 * @return the previous
	 */
	private static String getPrevious(boolean isDisabled, String onclick) {
		String disabled = "";
		if (isDisabled) {
			// disabled = " class=\"disabled\"";
			return ""; // 이전 페이지가 없는 경우 표시히지 않음
		}
//		return "<li" + disabled + "><a href=\"#\" aria-label=\"Previous\" onclick=\"" + onclick
//				+ "return false;\"><span aria-hidden=\"true\">&laquo;</span></a></li>";

		return "<a class=\"prev\" href=\"javascript:;\" onclick=\""+onclick+"\" >이전</a>";
	}

	/**
	 * Gets the last.
	 *
	 * @param isDisabled the is disabled
	 * @param onclick the onclick
	 * @return the last
	 */
	private static String getLast(boolean isDisabled, String onclick) {
		if(!AppConstants.USE_PAGE_GO_FIRST_LAST) {
			return "";
		}
		String disabled = "";
		if (isDisabled) {
			// disabled = " class=\"disabled\"";
			return ""; // 이전 페이지가 없는 경우 표시히지 않음
		}
		return "<a class=\"last\" href=\"javascript:;\" onclick=\""+onclick+"\" >마지막</a>";
	}

	/**
	 * Gets the first.
	 *
	 * @param isDisabled the is disabled
	 * @param onclick the onclick
	 * @return the first
	 */
	private static String getFirst(boolean isDisabled, String onclick) {
		if(!AppConstants.USE_PAGE_GO_FIRST_LAST) {
			return "";
		}
		String disabled = "";
		if(isDisabled) {
			return "";
		}

		return "<a class=\"first\" href=\"javascript:;\" onclick=\""+onclick+"\" >처음</a>";
	}

	/**
	 * 다음 페이지.
	 *
	 * @param isDisabled the is disabled
	 * @param onclick the onclick
	 * @return the next
	 */
	private static String getNext(boolean isDisabled, String onclick) {
		String disabled = "";
		if (isDisabled) {
			disabled = " class=\"disabled\"";
			return ""; // 다음페이지가 없는 경우 표시하지 않음
		}
//		return "<li" + disabled + "><a href=\"#\" aria-label=\"Next\" onclick=\"" + onclicke
//				+ "return false;\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
		return "<a class=\"next\" href=\"javascript:;\" onclick=\""+onclick+"\" >다음</a>";
	}
}