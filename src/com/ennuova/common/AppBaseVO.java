package com.ennuova.common;

/**
 * 接收页面的page参数
 * @author jimmy(王志明)
 *
 */
public class AppBaseVO {
	//定义每页展示记录数
	public final static int PAGE_SHOW_COUNT = 5;
	private int pageNum = 1;
	private int pageSize = 0;
	private int totalCount = 0;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize > 0 ? pageSize : PAGE_SHOW_COUNT;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getStartIndex() {
		int pageNum = this.getPageNum() > 0 ? this.getPageNum() - 1 : 0;
		return pageNum * this.getPageSize();
	}
}
