package com.ccs.util;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PageInfo {
    /* ÿҳ��ʾ��¼�� */
    public static int DEFAULT_PAGE_COUNT = 8;

    /* ��ǰҳ�� */
    private int currentPage = 0;

    /* �ܼ�¼�� */
    private int totalRecords = 0;

    private int rows = 0;
    
    /**
     * �õ���ǰҳ�� ���ܼ�¼��Ϊ0ʱ������0
     * ��currentPage <= 0ʱ������firstPage
     * ��currentPage > totalPagesʱ������totalPages
     *
     * @return Returns the currentPage.
     */
    public int getCurrentPage() {
        //return this.getTotalRecords() == 0 ? 0 : Math.max(Math.min(this
        //            .getTotalPages(), this.currentPage), this.getFirstPage());
        return this.currentPage;
    }

    /**
     * @Functionality: ���ص�һҳ
     * @return :
     */
    public int getFirstPage() {
        return 1;
    }

    /**
     * @Functionality: �������һҳ
     * @return :
     */
    public int getLastPage() {
        return getTotalPages();
    }

    /**
     * @Functionality: ������һҳ
     * @param :
     * @return :
     */
    public int getNextPage() {
        return Math.min(this.getCurrentPage() + 1, this.getTotalPages());
    }

    /**
     * @Functionality: ����ǰһҳ
     * @param :
     * @return :
     */
    public int getPreviousPage() {
        /*
         * ����DAO�Ķ��϶࣬���޸Ĵ˷��� �޸ĺ��PageInfo�����currentPage��1����previousPage�ͳ�0��
         * ��totalRecordsΪ0ʱ��previousPage�ͳ�-1�� ���ԣ���Щ�����߼�
         */
        return this.getCurrentPage() - 1;
        //return Math.max(this.getCurrentPage() - 1, this.getFirstPage());
    }

    /**
     * �õ���ҳ�� ���ܼ�¼��Ϊ0ʱ������0 ���򣬷�����ҳ�� + ���µ�һҳ
     *
     * @return
     */
    public int getTotalPages() {
        if (this.getTotalRecords() <= 0) {
            return 0;
        } else {
            return (this.getTotalRecords() / DEFAULT_PAGE_COUNT)
                    + (this.getTotalRecords() % DEFAULT_PAGE_COUNT == 0 ? 0 : 1);
        }
    }

    /**
     * �õ��ܼ�¼��
     *
     * @return Returns the total.
     */
    public int getTotalRecords() {
        return this.totalRecords == 0 ? 1 : this.totalRecords;
    }

    public int getPAGE_COUNT() {
        return this.getRows() > 0 ? this.getRows() : DEFAULT_PAGE_COUNT;
    }

    /**
     * @Functionality: �ж��ܷ�ǰ���һҳ
     * @param :
     * @return :
     */
    public boolean isCanFirst() {
        return this.getCurrentPage() > this.getFirstPage();
    }

    /**
     * @Functionality: �ж��ܷ�ǰ�����һҳ
     * @param :
     * @return :
     */
    public boolean isCanLast() {
        return this.getCurrentPage() < this.getTotalPages();
    }

    /**
     * @Functionality: �ж��ܷ�ǰ���һҳ
     * @param :
     * @return :
     */
    public boolean isCanNext() {
        return this.isCanLast();
    }

    /**
     * @Functionality: �ж��ܷ�ǰ��ǰһҳ
     * @param :
     * @return :
     */
    public boolean isCanPrevious() {
        return this.isCanFirst();
    }

    /**
     * @Functionality: ���õ�ǰҳ
     * @param :
     * @return :
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * �����ܼ�¼��
     *
     * @Functionality: ������ҳ��
     * @param :
     * @return :
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
    
}
