package kr.co.rci.esign.admin.util;

import java.util.List;

import com.google.common.collect.Lists;
import kr.co.rci.esign.admin.constant.Url;

/**
 * 메뉴 관리 유틸에 사용하는 Menu 클래스
 * @author Think-Tree Inc.
 * @version 1.0.2
 */
public class Menu {
	private String url;
	private String name;
	private String code;
	private Menu parent;
	//private Map<String, Menu> menuMap = Maps.newLinkedHashMap();
	private List<Menu> menuList = Lists.newArrayList();
	private int index = -1;
	private int depth = 0;
	private String imgPath;
	private boolean isNotMenuPage = false;

	public Menu(String name, String url) {
		setName(name);
		setUrl(url);
	}

	public Menu(String name, String url, String code) {
		setName(name);
		setUrl(url);
		setCode(code);
	}

	public Menu(String name, String url, String code, String imgPath) {
		setName(name);
		setUrl(url);
		setCode(code);
		setImgPath(imgPath);
	}

	public Menu(String name, String url, String code, boolean isNotMenuPage) {
		setName(name);
		setUrl(url);
		setCode(code);
		this.isNotMenuPage = isNotMenuPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getMenuList() {
		return this.menuList;
	}

	/**
	 * @param menu
	 * @return {@code Menu}
	 * @since 1.0.0
	 */
	public Menu addMenu(Menu menu) {
		if (!menu.isNotMenuPage) {
			menu.setIndex(this.menuList.size());
			menu.setDepth(this.depth + 1);
			menuList.add(menu);
		}
		//this.menuMap.put(menu.getUrl(), menu);
		menu.setParent(this);
		MenuUtil.addMenu(menu.getUrl(), menu);
		return this;
	}

	private void setParent(Menu parent) {
		this.parent = parent;
	}

	public Menu getParent() {
		if (parent == null) {
			return new Menu("홈", Url.MAIN.INDEX, "");
		}
		return parent;
	}

	private void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
