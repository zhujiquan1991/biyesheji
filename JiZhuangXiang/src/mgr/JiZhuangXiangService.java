package mgr;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import db.JiZhuangXiangDao;
import pojo.DescStatus;
import pojo.GuangDu;
import pojo.LinJieWenDu;
import pojo.UserInfo;
import pojo.WenShiDu;
import util.GsonUtil;
import util.HttpClientWebUtil;

@Service
public class JiZhuangXiangService {
	@Autowired
	private JiZhuangXiangDao jiZhuangXiangDao;
	
	/**
	 * 判断用户是否已注册过
	 * @param mobile
	 * @return
	 */
	public boolean isRegister(String mobile) throws Exception{
		UserInfo register = jiZhuangXiangDao.selectUserInfo(mobile);
		if(register == null){
			return false;
		}
		return true;
	}
	
	/**
	 * 用户注册
	 * @param mobile
	 * @param pwd
	 */
	public void registerUser(String mobile, String pwd) {
		UserInfo userInfo = new UserInfo();
		userInfo.setMobile(mobile);
		userInfo.setPwd(pwd);
		jiZhuangXiangDao.insertUserInfo(userInfo);
	}
	
	/**
	 * 根据手机号获取用户信息对象
	 * @param mobile
	 * @return 0
	 */
	public UserInfo selectUserInfo(String mobile) throws Exception{
		return jiZhuangXiangDao.selectUserInfo(mobile);
	}
	
//	public void insertWenShiDu(Integer wenDu,Integer shiDu) {
//		jiZhuangXiangDao.insertWenShiDu(wenDu,shiDu);
//	}
//	
//	public void insertGuangDu(Integer guangDu) {
//		jiZhuangXiangDao.insertGuangDu(guangDu);
//	}

	public String data() {
		HashMap<String, String> data = new HashMap<String,String>();
		GuangDu guangDu = jiZhuangXiangDao.selectGuangDu();
		WenShiDu wenShiDu = jiZhuangXiangDao.selectWenShiDu();
		data.put("guangDu", String.valueOf(guangDu.getGuangDu()));
		data.put("wenDu",String.valueOf(wenShiDu.getWenDu()));
		data.put("shiDu",String.valueOf(wenShiDu.getShiDu()));
		return GsonUtil.toJson(data);
	}
	
	/**
	 * 所有设备状态改为关闭
	 */
	public void initDesc() {
		jiZhuangXiangDao.updateInitDesc();
	}
	
	/**
	 * 加载设备开启状态
	 */
	public List<DescStatus> loadDescStatus() {
		return jiZhuangXiangDao.selectDescStatus();
	}
	
	/**
	 * 改变临界温度
	 */
	public void updateLinJieWenDu(Integer wenDu) {
		jiZhuangXiangDao.updateLinJieWenDu(wenDu);
	}
	
	/**
	 * 获取临界温度
	 */
	public LinJieWenDu selectLinjieWenDu() {
		return jiZhuangXiangDao.selectLinJieWenDu();
	}
	
	/**
	 * 更新设备状态
	 * @param desc
	 * @param status
	 * @throws Exception 
	 */
	public void updateDescStatus(String desc, Integer status) throws Exception {
		Integer switchNumber = null;//开关序号
		String openOrCloes="";//开关状态
		//判断需要打开的开关
		if("paiqishan".equals(desc)){
			switchNumber=1;
		}else if ("led".equals(desc)) {
			switchNumber=2;
		}else if ("huasa".equals(desc)) {
			switchNumber=3;
		}else if ("diguan".equals(desc)) {
			switchNumber=4;
		}else if ("yinxiang".equals(desc)) {
			switchNumber=5;
		}
		
		//判断状态
		if(status==1){
			openOrCloes = "on";
		}else {
			openOrCloes = "off";
		}
		//打开继电器开关
		String jiDianQiUrl = "http://192.168.1.77/ecmd?pin%20set%20k"+switchNumber+"%20"+openOrCloes;
		System.out.println("请求俩件"+jiDianQiUrl);
		HttpClientWebUtil.doHttpGet(jiDianQiUrl);
		
		DescStatus descStatus = new DescStatus();
		descStatus.setDescName(desc);
		descStatus.setStatus(status);
		jiZhuangXiangDao.updateDescStatus(descStatus);
	}
	
}
