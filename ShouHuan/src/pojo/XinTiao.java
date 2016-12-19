package pojo;

import java.util.Date;

/**
 * 心跳 
 */
public class XinTiao {
	
	private Long id;
	
	//心跳
	private Integer xinTiao;
	
	//创建时间
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
