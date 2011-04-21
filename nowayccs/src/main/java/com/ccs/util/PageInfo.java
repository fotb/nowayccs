package com.ccs.util;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PageInfo {
    /* 每页显示记录数 */
    public static int PAGE_COUNT = 2;

    /* 当前页码 */
    private int currentPage = 0;

    /* 总记录数 */
    private int totalRecords = 0;

    /**
     * 得到当前页数 当总记录数为0时，返回0
     * 当currentPage <= 0时，返回firstPage
     * 当currentPage > totalPages时，返回totalPages
     *
     * @return Returns the currentPage.
     */
    public int getCurrentPage() {
        //return this.getTotalRecords() == 0 ? 0 : Math.max(Math.min(this
        //            .getTotalPages(), this.currentPage), this.getFirstPage());
        return this.currentPage;
    }

    /**
     * @Functionality: 返回第一页
     * @return :
     */
    public int getFirstPage() {
        return 1;
    }

    /**
     * @Functionality: 返回最后一页
     * @return :
     */
    public int getLastPage() {
        return getTotalPages();
    }

    /**
     * @Functionality: 返回下一页
     * @param :
     * @return :
     */
    public int getNextPage() {
        return Math.min(this.getCurrentPage() + 1, this.getTotalPages());
    }

    /**
     * @Functionality: 返回前一页
     * @param :
     * @return :
     */
    public int getPreviousPage() {
        /*
         * 考虑DAO改动较多，故修改此方法 修改后的PageInfo的如果currentPage是1，则previousPage就成0了
         * 而当totalRecords为0时，previousPage就成-1了 所以，有些不合逻辑
         */
        return this.getCurrentPage() - 1;
        //return Math.max(this.getCurrentPage() - 1, this.getFirstPage());
    }

    /**
     * 得到总页数 当总记录数为0时，返回0 否则，返回满页数 + 余下的一页
     *
     * @return
     */
    public int getTotalPages() {
        if (this.getTotalRecords() <= 0) {
            return 0;
        } else {
            return (this.getTotalRecords() / PAGE_COUNT)
                    + (this.getTotalRecords() % PAGE_COUNT == 0 ? 0 : 1);
        }
    }

    /**
     * 得到总记录数
     *
     * @return Returns the total.
     */
    public int getTotalRecords() {
        return this.totalRecords == 0 ? 1 : this.totalRecords;
    }

    public int getPAGE_COUNT() {
        return PAGE_COUNT;
    }

    /**
     * @Functionality: 判断能否前往第一页
     * @param :
     * @return :
     */
    public boolean isCanFirst() {
        return this.getCurrentPage() > this.getFirstPage();
    }

    /**
     * @Functionality: 判断能否前往最后一页
     * @param :
     * @return :
     */
    public boolean isCanLast() {
        return this.getCurrentPage() < this.getTotalPages();
    }

    /**
     * @Functionality: 判断能否前往后一页
     * @param :
     * @return :
     */
    public boolean isCanNext() {
        return this.isCanLast();
    }

    /**
     * @Functionality: 判断能否前往前一页
     * @param :
     * @return :
     */
    public boolean isCanPrevious() {
        return this.isCanFirst();
    }

    /**
     * @Functionality: 设置当前页
     * @param :
     * @return :
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 设置总记录数
     *
     * @Functionality: 设置总页数
     * @param :
     * @return :
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}
