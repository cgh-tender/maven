package com.dw.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 分页标签
 * @author tlk
 */
@SuppressWarnings("serial")
public class PageTag extends TagSupport{
	private int page ,count,pageSize,index;
	private String url ="",param="";
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	private String getParams(){
		if ( null == this.param || "".equals(this.param.trim()) ) return "" ;
		if ( this.param.substring(0,1).indexOf("&") >= 0 ) {
			return this.param ;
		}
		return "&"+this.param ;
	}

	public int doStartTag() throws JspException {
		try{
			HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
			String outStr = "";
			int cPage = Page.getCountPage(pageSize, count) ;
			request.setAttribute("_count",count) ;
			request.setAttribute("_pageSize",pageSize) ;
			request.setAttribute("countPage",cPage);
//			int [] next = Page.getCurrentData(page, pageSize, count,cPage);
//			System.out.println("[当前页],从"+next[0]+"到:"+next[1]);
			
			int [] next = Page.getFirstPage(page, pageSize, count);
			int i = next[0] ;
			outStr = this.url+"?page=1&offset="+i+getParams() ;
			request.setAttribute("firstPage",outStr);
			request.setAttribute("params",getParams());
//			System.out.println("[首页页],从"+next[0]+"到:"+next[1]);
			
			next = Page.getUpPage(page, pageSize, count);
			i = next[0] ;
			outStr = this.url+"?page="+(page-1)+"&offset="+i+getParams() ;
			if ( i < 0 ) {
				outStr = null ;
			}
			request.setAttribute("upPage",outStr);
//			System.out.println("[上一页],从"+next[0]+"到:"+next[1]);
			
			next = Page.getNextPage(page, pageSize, count);
			i = next[0] ;
			outStr = this.url+"?page="+(page+1)+"&offset="+i+getParams() ;
			if ( i < 0 ) {
				outStr = null ;
			}
			request.setAttribute("nextPage",outStr);
//			System.out.println("[下一页],从"+next[0]+"到:"+next[1]);
					
			next = Page.getLastPage(page, pageSize, count);
			i = next[0] ;
			outStr = this.url+"?page="+cPage+"&offset="+i+getParams() ;
			request.setAttribute("lastPage",outStr);
//			System.out.println("[尾页页],从"+next[0]+"到:"+next[1]);
//			out.print(outStr);
		}catch(Exception e){
		}
		return SKIP_BODY;
	}
}
 