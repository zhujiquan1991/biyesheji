package pojo;

import java.util.Date;

/**
 * 用户申请检测时间
 */
public class UserJianCeTime {
	
	//手机号码
	private String mobile;
	//检测时间
	private Date jianCeTime;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getJianCeTime() {
		return jianCeTime;
	}

	public void setJianCeTime(Date jianCeTime) {
		this.jianCeTime = jianCeTime;
	}
	
}
