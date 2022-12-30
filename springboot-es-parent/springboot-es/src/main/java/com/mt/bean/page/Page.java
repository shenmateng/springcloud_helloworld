package com.mt.bean.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {

	private static final long serialVersionUID = 2439137595310411068L;

	public static final int INITED_CURRENT_PAGE = 1;

	public static final int DEFAULT_MAX_RESULTS = 10;

	public static final int DEFAULT_PAGE_LINKS = 10;

	public static final int PAGE_LINKS_STYLE_STATIC = 0;

	public static final int PAGE_LINKS_STYLE_DYNAMIC = 1;

	private boolean inited = false;

	private int currentPage = INITED_CURRENT_PAGE;

	private int maxResults = DEFAULT_MAX_RESULTS;

	private int pageLinks = DEFAULT_PAGE_LINKS;

	private int pageLinksStyle = PAGE_LINKS_STYLE_STATIC;

	private long totalResults = 0;
	
	private Boolean isPage = true;

	public Page() {
		this(DEFAULT_MAX_RESULTS, DEFAULT_PAGE_LINKS, PAGE_LINKS_STYLE_STATIC);
	}

	public Page(int maxResults) {
		this(maxResults, DEFAULT_PAGE_LINKS, PAGE_LINKS_STYLE_STATIC);
	}

	public Page(int maxResults, int pageLinks) {
		this(maxResults, pageLinks, PAGE_LINKS_STYLE_STATIC);
	}

	public Page(int maxResults, int pageLinks, int pageLinkStyle) {
		if (maxResults < 1) {
			throw new IllegalArgumentException("maxResults must not smaller than 1 but " + maxResults);
		}

		if (pageLinks < 1) {
			throw new IllegalArgumentException("pageLinks must not smaller than 1 but " + pageLinks);
		}

		if (pageLinkStyle != PAGE_LINKS_STYLE_STATIC && pageLinkStyle != PAGE_LINKS_STYLE_DYNAMIC) {
			throw new IllegalArgumentException("pageLinkStyle must be " + PAGE_LINKS_STYLE_STATIC + " or " + PAGE_LINKS_STYLE_DYNAMIC + " but " + pageLinkStyle);
		}

		this.maxResults = maxResults;
		this.pageLinks = pageLinks;
		this.pageLinksStyle = pageLinkStyle;
	}

	public int getFirstResult() {
		return Math.max((getCurrentPage() - 1) * maxResults, 0);
	}

	public int getFirstRownum() {
		return getFirstResult() + 1;
	}

	public int getLastRownum() {
		return getFirstResult() + maxResults;
	}

	public long getFirstPage() {
		return Math.min(totalResults, 1);
	}

	public long getCurrentResultNum() {
		return getTotalResults() - ((getCurrentPage() - 1) * getMaxResults());
	}

	public int getLastPage() {
		return (int) Math.ceil((double) totalResults / maxResults);
	}

	public long getStartPageLink() {
		if (pageLinksStyle == PAGE_LINKS_STYLE_DYNAMIC) {
			return Math.max(getCurrentPage() - pageLinks, getFirstPage());
		}

		return (getCurrentPage() - 1) / pageLinks * pageLinks + 1;
	}

	public long getEndPageLink() {
		if (pageLinksStyle == PAGE_LINKS_STYLE_DYNAMIC) {
			return Math.min(getCurrentPage() + pageLinks, getLastPage());
		}

		return Math.min(getStartPageLink() + pageLinks - 1, getLastPage());
	}

	public long getPrelinkPage() {
		return Math.max(getCurrentPage() - 1, getFirstPage());
	}

	public int getPostlinkPage() {
		return Math.min(getCurrentPage() + 1, getLastPage());
	}
	
	public List<Integer> getListPage(){
	    List<Integer> listPage = new ArrayList<Integer>();
	    int start = 1;
	    int size = (int) Math.rint(getTotalResults()/getMaxResults());
	    if(getCurrentPage()>3 && getCurrentPage()+2<=getLastPage()){
	        start = getCurrentPage() - 2;
	        size++;
	    }else if(getCurrentPage()>3 && getCurrentPage()<=getLastPage()){
	        start = getLastPage() - 4;
	        size++;
	    }
	    for(int i=0; i<5 && i<=size; i++){
	        listPage.add(start + i);
	    }
	    return listPage;
	}

	public int getCurrentPage() {
		if (isInited()) {
			return Math.min(currentPage, getLastPage());
		}
		else {
			return currentPage;
		}
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage < 1) {
			return;
		}

		this.currentPage = currentPage;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		if (maxResults < 1) {
			return;
		}

		this.maxResults = maxResults;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long total) {
		this.totalResults = total;
//		this.inited = true;
	}

	public int getpageLinks() {
		return pageLinks;
	}

	protected void setpageLinks(int pageLinks) {
		if (pageLinks < 1) {
			return;
		}

		this.pageLinks = pageLinks;
	}

	public int getpageLinksStyle() {
		return pageLinksStyle;
	}

	public void setpageLinksStyle(int pageLinksStyle) {
		if (pageLinksStyle != PAGE_LINKS_STYLE_STATIC && pageLinksStyle != PAGE_LINKS_STYLE_DYNAMIC) {
			return;
		}

		this.pageLinksStyle = pageLinksStyle;
	}

	public boolean isInited() {
		return inited;
	}

	public void setInited(boolean inited) {
		this.inited = inited;
	}

	public Boolean getIsPage() {
        return isPage;
    }

    public void setIsPage(Boolean isPage) {
        this.isPage = isPage;
    }

    @Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) +
			"(" + 
			"firstPage=" + "'" + getFirstPage() + "'" + ", " + 
			"lastPage=" + "'" + getLastPage() + "'" + ", " + 
			"currentPage=" + "'" + getCurrentPage() + "'" + ", " + 
			"startPageLink=" + "'" + getStartPageLink() + "'" + ", " + 
			"endPageLink=" + "'" + getEndPageLink() + "'" + 
			")";
	}

}
