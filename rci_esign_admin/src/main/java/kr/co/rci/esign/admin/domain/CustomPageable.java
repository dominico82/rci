package kr.co.rci.esign.admin.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import kr.co.rci.esign.admin.constant.AppConstants;


public class CustomPageable extends PageRequest implements Pageable {

	private static final long serialVersionUID = -8841888186692528689L;

	@SuppressWarnings("deprecation")
	public CustomPageable() {
		super(AppConstants.DEFAULT_PAGE_NUMBER, AppConstants.DEFAULT_PAGE_SIZE);
	}

	@SuppressWarnings("deprecation")
	public CustomPageable(int page, int size) {
		super(page, size);
	}

	@SuppressWarnings("deprecation")
	public CustomPageable(int page, int size, Direction direction, String... properties) {
		super(page, size, new Sort(direction, properties));
	}

	@SuppressWarnings("deprecation")
	public CustomPageable(int page, int size, Sort sort) {
		super(page, size, sort);
	}

	public long getStart() {
		return getOffset() + 1;
	}

	public long getEnd() {
		return getOffset() + getPageSize();
	}
}
