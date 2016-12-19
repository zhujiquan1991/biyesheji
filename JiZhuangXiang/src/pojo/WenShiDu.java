package pojo;

import java.util.Date;

public class WenShiDu {

	private Long id;
	
	private Integer wenDu;
	
	private Integer shiDu;
	
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getWenDu() {
		return wenDu;
	}

	public void setWenDu(Integer wenDu) {
		this.wenDu = wenDu;
	}

	public Integer getShiDu() {
		return shiDu;
	}

	public void setShiDu(Integer shiDu) {
		this.shiDu = shiDu;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
