package com.ennuova.util;

import java.util.List;

public class PageBean {

	// 指定的或是页面参�?
	private int currentPage; // 当前�?
	private int pageSize; // 每页显示多少�?

	// 查询数据�?
	private int recordCount; // 总记录数
	private List recordList; // 本页的数据列�?

	// 计算
	private int pageCount; // 总页�?
	private int beginPageIndex; // 页码列表的开始索引（包含�?
	private int endPageIndex; // 页码列表的结束索引（包含�?

	/**
	 * 只接受前4个必要的属�?，会自动的计算出其他3个属生的�?
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param recordCount
	 * @param recordList
	 */
	public PageBean(int currentPage, int pageSize, int recordCount, List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;

		// 计算总页�?
		pageCount = (recordCount + pageSize - 1) / pageSize;

		// 计算beginPageIndex、endPageIndex
		// 总页数不多于10页，则全部显�?
		if(pageCount <= 10) {
			// 总页数多�?0页，则显示当前页附近的共10个页�?
			beginPageIndex = 1;
			endPageIndex = pageCount;
		} else {
			// 总页数多�?0页（�?�?当前�?�?个）
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			// 档前的页码不�?个，则显示前10�?
			if(beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 档后面的页码不足5个，则全部显�?
			if(endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1;
			}
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
}