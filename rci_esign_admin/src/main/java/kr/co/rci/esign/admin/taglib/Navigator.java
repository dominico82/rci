package kr.co.rci.esign.admin.taglib;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import kr.co.rci.esign.admin.constant.Url;
import kr.co.rci.esign.admin.util.Menu;
import kr.co.rci.esign.admin.util.MenuUtil;


public class Navigator extends SimpleTagSupport {

	private String menuUrl = null;
	private String templateRoot = "<div class='root'><a href='"+Url.MAIN.INDEX+"' class='rootTit'>Home</a>#$subNavi</div>";
	private String templateLink = "<a href='#$mLink'>#$mName</a>";
	private String templateCurrent = "<span>#$mName</span>";
//	<span class="pagePos"><a href="main_setting.html" class="rootTit"></a><a href="contents_list.html" class="list">서식 관리</a><span>계약서</span></span>
    private Writer getWriter() {
        JspWriter out = getJspContext().getOut();
        return out;
    }

    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();
        if(getMenuUrl() == null || getMenuUrl().length() ==0) {
        	PageContext pageContext = (PageContext) getJspContext();
        	HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        	setMenuUrl((String)request.getAttribute("javax.servlet.forward.request_uri"));
        }

        Menu currentMenu = MenuUtil.getMenu(menuUrl);
        String linkStr = "";
        if(currentMenu != null) {
        	int currentIdx = currentMenu.getIndex();
        	boolean isCurrentUrl = true;
        	while (currentIdx > -1) {
				if(isCurrentUrl) {
					linkStr = templateCurrent.replace("#$mName", currentMenu.getName());
					isCurrentUrl = false;

					currentMenu = currentMenu.getParent();
					currentIdx = currentMenu.getIndex();
					continue;
				}

				linkStr = (templateLink.replace("#$mLink", currentMenu.getUrl()).replace("#$mName", currentMenu.getName())) + linkStr;

				currentMenu = currentMenu.getParent();
				currentIdx = currentMenu.getIndex();

			}
        }

        try {
        	if(linkStr != null && linkStr.length() > 0) {
        		out.write(templateRoot.replace("#$subNavi", linkStr));
        	}

		} catch (java.io.IOException ex) {
			throw new JspException("Error in Page Navigator tag", ex);
		}
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
}
