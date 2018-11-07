package vo;

import java.util.List;

/**
 * ��ҳvo
 * @author mz
 *
 */
public class PageVo<T> {
	private Integer pageNo;	//��ǰҳ��
	private int startIndex; //��ʼ��¼��
	private int totalPageSize;//��ҳ��
	private int pageSize = 4; //ҳ���ݴ�С
	private int totalRecords; //��Ʒ�ܼ�¼��
	private List<T> records; //���ݼ���
	private String url; //��תҳ��url(��ҳ/��һҳ/��һҳ/βҳ)
	
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

