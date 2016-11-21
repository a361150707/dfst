package com.ennuova.base.impl;

import java.util.List;

public class PaginationSupport {
	public final static int PAGESIZE = 30;
	
	private int pageSize = PAGESIZE;

	private List items;

	private int totalCount;

	private int[] indexes = new int[0];

	private int startIndex = 0;

	public PaginationSupport(List items, int totalCount) {
		this.pageSize = PAGESIZE;
		this.totalCount = totalCount;
		this.items = items;
		this.startIndex = 0;
	}
	
	public PaginationSupport(List items, int totalCount, int startIndex) {
		this.pageSize = PAGESIZE;
		this.totalCount = totalCount;
		this.items = items;
		this.startIndex = startIndex;
	}

	public PaginationSupport(List items, int totalCount, int pageSize,
	   int startIndex) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.items = items;
		this.startIndex = startIndex;
	 }
}