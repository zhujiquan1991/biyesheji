package pojo;

import java.util.Date;

/**
 * 光照强度
 */
public class GuangDu {
	
	private Long id;
	
	private Integer guangDu;
	
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGuangDu() {
		return guangDu;
	}

	public void setGuangDu(Integer guangDu) {
		this.guangDu = guangDu;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
