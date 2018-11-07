package vo;

import java.util.List;

/**
 * 分页vo
 * @author mz
 *
 */
public class PageVo<T> {
	private Integer pageNo;	//当前页数
	private int startIndex; //开始记录数
	private int totalPageSize;//总页数
	private int pageSize = 4; //页数据大小
	private int totalRecords; //商品总记录数
	private List<T> records; //数据集合
	private String url; //跳转页面url(首页/上一页/下一页/尾页)
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getStartIndex() {
		return (pageNo-1)*pageSize;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getTotalPageSize() {
		return totalRecords%pageSize==0 ? totalRecords/pageSize : totalRecords/pageSize+1;
	}
	public void setTotalPageSize(int totalPageSize) {
		this.totalPageSize = totalPageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
}

