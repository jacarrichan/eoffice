package com.palmelf.core.web.paging;

public class PagingBean {
	public static final String PAGING_BEAN = "_paging_bean";
	public static Integer DEFAULT_PAGE_SIZE = Integer.valueOf(25);
	public static final int SHOW_PAGES = 6;
	public Integer start;
	private Integer pageSize;
	private Integer totalItems;

	public PagingBean(int start, int limit) {
		this.pageSize = Integer.valueOf(limit);
		this.start = Integer.valueOf(start);
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = Integer.valueOf(pageSize);
	}

	public int getTotalItems() {
		return this.totalItems.intValue();
	}

	public Integer getStart() {
		return this.start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = Integer.valueOf(totalItems);
	}

	public int getFirstResult() {
		return this.start.intValue();
	}
}
