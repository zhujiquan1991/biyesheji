package pojo;

import java.util.Date;

/**
 * 体温
 */
public class TiWen {
	
	private Long id;
	//体温
	private Integer tiwen;
	//创建时间
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTiwen() {
		return tiwen;
	}

	public void setTiwen(Integer tiwen) {
		this.tiwen = tiwen;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
