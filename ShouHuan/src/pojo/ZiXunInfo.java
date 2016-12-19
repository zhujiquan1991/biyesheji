package pojo;

import java.util.Date;

/**
 * 咨询推荐
 */
public class ZiXunInfo {
	
	private String mobile;
	//图片URL
	private String imgUrl;
	//主题
	private String thream;
	//内容
	private String content;
	//页面url
	private String webUrl;
	//创建时间
	private Date createTime;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getThream() {
		return thream;
	}
	public void setThream(String thream) {
		this.thream = thream;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
