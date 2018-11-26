package com.ms.taojin.common.db.page;
//package com.ms.center.common.db.page;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Page<T> extends ArrayList<T> {
//	private static final long serialVersionUID = 1L;
//
//	private int pageNo = 1;// 页码，默认是第一页
//	private int pageSize = 20;// 每页显示的记录数，默认是20
//	private int totalRecord;// 总记录数
//	private int totalPage;// 总页数
//	private List<T> result;
//
//	public Page() {
//
//	}
//
//	public Page(int pageNo, int pageSize, int totalRecord, List<T> results) {
//		this.pageNo = pageNo;
//		this.pageSize = pageSize;
//		this.totalRecord = totalRecord;
//		this.setResult(results);
//		int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
//		this.setTotalPage(totalPage);
//	}
//
//	public int getPageNo() {
//		return pageNo;
//	}
//
//	public void setPageNo(int pageNo) {
//		this.pageNo = pageNo;
//	}
//
//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	public int getTotalRecord() {
//		return totalRecord;
//	}
//
//	public void setTotalRecord(int totalRecord) {
//		this.totalRecord = totalRecord;
//		// 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
//		int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
//		this.setTotalPage(totalPage);
//	}
//
//	public int getTotalPage() {
//		return totalPage;
//	}
//
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
//
//	public List<T> getResult() {
//		return result;
//	}
//
//	public void setResult(List<T> result) {
//		this.result = result;
//	}
//
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("Page [pageNo=").append(pageNo).append(", pageSize=").append(pageSize).append(", results=").append(getResult()).append(", totalPage=")
//		        .append(totalPage).append(", totalRecord=").append(totalRecord).append("]");
//		return builder.toString();
//	}
//}