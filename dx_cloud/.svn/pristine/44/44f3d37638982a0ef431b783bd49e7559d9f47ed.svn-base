package com.dw.common;

import javax.servlet.http.HttpServletRequest;

/**
 * @time 2012-10-18 
 * @author wuchao
 */
public class Page {
	//	static private int count ;//总记录数
	//	static private int page  ;//当前页
	//	static private int pageSize ;//每页多少条
	//	static private int countPage ;//总页数
	public static final String offset = "do_for_page_goto" ;
	
	/**
	 * 获取总页数
	 * @return
	 */
	public static int getCountPage(int pageSize,int count) {
		int countPage = count % pageSize ;
		if ( countPage > 0 ) {
			countPage = count / pageSize + 1 ;
		} else {
			countPage = count / pageSize ;
		}
		return countPage;
	}
	/**
	 * 计算数据
	 * @param page 当前页
	 * @param pageSize 每页多少条
	 * @param count 总数
	 * @param countPage 总页数
	 */
	public static int[] getCurrentData(int page,int pageSize,int count,int countPage) {
		int[] va = {-1,-1} ;
		//当前页大于总页数返回-1
		if ( page > countPage || page <= 0 ) {
			return va ;
		}
		int jVal = page * pageSize ;
		if ( page == countPage ) {
			if ( jVal == 0 ) {
				va[0] = jVal -pageSize ;
				va[1] = jVal ;
				return va ;
			} else {
				va[0] = jVal - pageSize ;
				va[1] = jVal > count?count:jVal;
				return va ;
			}
		}
		int v = page * pageSize ;
		if ( page == 1) v = 0 ;
		va[0] = v - pageSize < -1 ? 0:v - pageSize ;
		va[1] = va[0] + pageSize ;
		return  va ;
	}
	
	/**
	 * 计算下一页
	 */
	public static int[]  getNextPage(int page,int pageSize,int count){
		int countPage = getCountPage(pageSize,count);
		return getCurrentData(page+1,pageSize,count,countPage);
	}
	/**
	 * 计算上一页
	 */
	public static int[] getUpPage(int page,int pageSize,int count){
		int countPage = getCountPage(pageSize,count);
		return  getCurrentData(page-1,pageSize,count,countPage) ;
	}
	/**
	 * 计算首页
	 */
	public static int[] getFirstPage(int page,int pageSize,int count){
		int countPage = getCountPage(pageSize,count);
		return  getCurrentData(1,pageSize,count,countPage) ;
	}
	/**
	 * 计算最后页
	 */
	public static int[] getLastPage(int page,int pageSize,int count){
		int countPage = getCountPage(pageSize,count);
		return  getCurrentData(countPage,pageSize,count,countPage) ;
	}
	
	public static void main(String[] args) {
		int page = 2 ;
		int pageSize = 20 ;
		int count = 41 ;
		System.out.println("当前页:"+page + ",每页:"+pageSize+",总记录数:"+count);
		System.out.println("总页数:"+Page.getCountPage(pageSize,count));
		System.out.println();
		
		int [] next = Page.getCurrentData(page,pageSize,count,Page.getCountPage(pageSize, count));
		System.out.println("[当前页],从"+next[0]+"到:"+next[1]);
		
		next = Page.getFirstPage(page,pageSize,count);
		System.out.println("[首页页],从"+next[0]+"到:"+next[1]);
		
		next = Page.getUpPage(page,pageSize,count);
		System.out.println("[上一页],从"+next[0]+"到:"+next[1]);
		
		next = Page.getNextPage(page,pageSize,count);
		System.out.println("[下一页],从"+next[0]+"到:"+next[1]);
				
		next = Page.getLastPage(page,pageSize,count);
		System.out.println("[尾页页],从"+next[0]+"到:"+next[1]);
		
	}
	public static int getOffset(HttpServletRequest request) {
		String page = request.getParameter("page");
		if (null == page || "".equals(page.trim())) page = "1";
		String offsetStr = request.getParameter("offset"); // 获取offset的值
		if (null == offsetStr || "".equals(offsetStr)) {
			offsetStr = "0";
		} else {
			if ( offsetStr.equals(offset) ) {
				int count = Integer.parseInt(request.getParameter("_count")+"") ;
				int pageSize = Integer.parseInt(request.getParameter("_pageSize")+"") ;
				int [] next = Page.getCurrentData(Integer.parseInt(page),pageSize,count,Page.getCountPage(pageSize, count));
				offsetStr = next[0] +"" ;
			}
		}
		return Integer.parseInt(offsetStr);
	}
	/**
	 * @param request	作用域
	 * @param pageSize  每页条数
	 * @param url		分页url
	 * @param count		总记录数
	 * @param params    查询参数
	 * @param type_all 
	 */
	public static void setAttribute(HttpServletRequest request,int pageSize, String url, int count,String params) {
		String page = request.getParameter("page");
		if (null == page || "".equals(page.trim())) page = "1";
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("_url",url);
		request.setAttribute("_count", count);
		request.setAttribute("_page", Integer.parseInt(page));
		request.setAttribute("_param", params);
	}
}
