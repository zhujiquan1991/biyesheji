package mgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import db.ShouHuanDao;
import pojo.TiWen;
import pojo.UserInfo;
import pojo.UserJianCeData;
import pojo.UserJianCeTime;
import pojo.XinTiao;
import pojo.ZiXunInfo;


@Service
public class ShouHuanService {
	
	@Autowired
	private ShouHuanDao shouHuanDao;
	
	/**
	 * 判断用户是否已注册过
	 * @param mobile
	 * @return
	 */
	public boolean isRegister(String mobile) throws Exception{
		UserInfo register = shouHuanDao.selectUserInfo(mobile);
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
		shouHuanDao.insertUserInfo(userInfo);
	}
	
	/**
	 * 根据手机号获取用户信息对象
	 * @param mobile
	 * @return 0
	 */
	public UserInfo selectUserInfo(String mobile) throws Exception{
		return shouHuanDao.selectUserInfo(mobile);
	}
	
	/**
	 * 记录开始检测时间
	 * @param mobile
	 */
	public boolean beginJianCe(String mobile) {
		UserJianCeTime userJianCeTime = shouHuanDao.selectUserJianCe(mobile);
		if(userJianCeTime == null){
			shouHuanDao.insertUserJianCe(mobile);
		} else {
			shouHuanDao.updateUserJianCe(mobile);
		}
		return true;
	}
	
	/**
	 * 取得结果
	 * @param mobile
	 * @return
	 */
	public HashMap<String, String> getJianCe(String mobile) {
		//温度取最新
		TiWen lastTiWen = shouHuanDao.selectTiWen();
		//获取心跳列表
		List<XinTiao> listXinTiao = shouHuanDao.listXinTiao(mobile);
		//判断是否有数据
		if(lastTiWen != null && listXinTiao != null && listXinTiao.size()>0){
			int xinTiaoSum = 0;
			//心跳去掉无效数据取平均值
			for (XinTiao xinTiao : listXinTiao) {
				xinTiaoSum += xinTiao.getXinTiao().intValue();
			}
			//心跳平均值
			int xinTiaoAvg = xinTiaoSum/listXinTiao.size();
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("tiWen", String.valueOf(lastTiWen.getTiwen()));
			data.put("xinTiao", String.valueOf(xinTiaoAvg));
			//插入管理页面对象
			UserJianCeData userJianCeData = new UserJianCeData();
			userJianCeData.setMobile(mobile);
			userJianCeData.setTiWen(Integer.valueOf(xinTiaoAvg));
			userJianCeData.setXinTiao(Integer.valueOf(lastTiWen.getTiwen()));
			shouHuanDao.insertUserJianCeData(userJianCeData);
			return data;
		}else{
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("tiWen", "0");
			data.put("xinTiao", "0");
			return data;
		}
	}
	
	/**
	 * 插入检测数据
	 * @param userJianCeData
	 */
	public void insertUserJianCeData(UserJianCeData userJianCeData){
		shouHuanDao.insertUserJianCeData(userJianCeData);
	}
	
	/**
	 * 取得咨询列表
	 * @param mobile
	 */
	public List<ZiXunInfo> listZixunByMobile(String mobile) {
		return shouHuanDao.listZixunByMobile(mobile);
	}
	
	/**
	 * 取得用户检测列表
	 * @param mobile
	 */
	public List<UserJianCeData> selectUserJianCeData(HashMap<String, java.lang.Object> queryMap) {
		return shouHuanDao.selectUserJianCeData(queryMap);
	}

	public List<Map<String, Object>> testMapObject() {
		return shouHuanDao.testMapObject();
	}
	
	public void insertTiWen(Integer tiWen) {
		shouHuanDao.insertTiWen(tiWen);
	}
	
	public void insertXinTiao(Integer xinTiao) {
		shouHuanDao.insertXinTiao(xinTiao);
	}
	
	
}
