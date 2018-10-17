package study.spring.helper;

public class PageHelper {
	/***** GET?��?��미터�? 처리?�� �? *****/
    private int page = 1; 			// ?��?�� ?��?���? 번호

    /***** DB?��?�� 조회?�� 결과 �? *****/
    private int totalCount = 0;		// ?���? �? 개수 조회

    /***** 개발?���? ?��?��?��?�� ?��?�� �? *****/
    private int listCount = 10;		// ?�� ?��?���??�� 보여�? �??�� 목록 ?��
    private int groupCount = 5;		// ?�� 그룹?�� ?��?��?�� ?��?���?번호 개수

	/***** ?��?��처리�? ?��?��?�� �? *****/
	private int totalPage = 0;		// ?���? ?��?���? ?��
    private int startPage = 0;		// ?��?�� 그룹?�� ?��?�� ?��?���? 
    private int endPage = 0;		// ?��?�� 그룹?�� 마�?�? ?��?���?
    private int prevPage = 0;		// ?��?�� 그룹?�� 마�?�? ?��?���?
    private int nextPage = 0;		// ?��?�� 그룹?�� �? ?��?���?
    private int limitStart = 0;		// MySQL?�� Limit ?��?�� ?���?

	/** ?��?���? 구현?�� ?��?��?�� 계산?��?�� 처리?��?�� 메서?�� */
	public void pageProcess(int page, int totalCount, int listCount, int groupCount) {
		this.page = page;
		this.totalCount = totalCount;
		this.listCount = listCount;
		this.groupCount = groupCount;

		// ?���? ?��?���? ?��
	    totalPage = ((totalCount-1)/listCount)+1;

	    // ?��?�� ?��?���??�� ???�� ?���? 조절
	    if (page < 0) {
	    	page = 1;
	    }
	    
	    if (page > totalPage) {
	    	page = totalPage;
	    }

	    // ?��?�� ?��?���? 그룹?�� ?��?�� ?��?���? 번호
	    startPage = ((page - 1) / groupCount) * groupCount + 1;

	    // ?��?�� ?��?���? 그룹?�� ?�� ?��?���? 번호
	    endPage = (((startPage - 1) + groupCount) / groupCount) * groupCount;

	    // ?�� ?��?���? 번호�? ?���? ?��?���??���? 초과?���? ?��차범?�� 조절
	    if (endPage > totalPage) {
	    	endPage = totalPage;
	    }

	    // ?��?�� 그룹?�� 마�?�? ?��?���?
	    if (startPage > groupCount) {
	        prevPage = startPage - 1;
	    } else {
	    	prevPage = 0;
	    }

	    // ?��?�� 그룹?�� �? ?��?���?
	    if (endPage < totalPage) {
	        nextPage = endPage + 1;
	    } else {
	    	nextPage = 0;
	    }

	    // �??�� 범위?�� ?��?�� ?���?
	    limitStart = (page-1) * listCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLimitStart() {
		return limitStart;
	}

	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}

	@Override
	public String toString() {
		return "PageHelper [page=" + page + ", totalCount=" + totalCount
				+ ", listCount=" + listCount + ", groupCount=" + groupCount
				+ ", totalPage=" + totalPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", prevPage=" + prevPage
				+ ", nextPage=" + nextPage + ", limitStart=" + limitStart + "]";
	}
}
