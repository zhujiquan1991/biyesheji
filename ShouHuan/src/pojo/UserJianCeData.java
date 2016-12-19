package pojo;

import java.util.Date;

public class UserJianCeData {
	
	private Long id;
	
	private String mobile;
	
	private Integer tiWen;
	
	private Integer xinTiao;
	
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getTiWen() {
		return tiWen;
	}

	public void setTiWen(Integer tiWen) {
		this.tiWen = tiWen;
	}

	public Integer getXinTiao() {
		return xinTiao;
	}

	public void setXinTiao(Integer xinTiao) {
		this.xinTiao = xinTiao;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
