package db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.Object;

import pojo.TiWen;
import pojo.UserInfo;
import pojo.UserJianCeData;
import pojo.UserJianCeTime;
import pojo.XinTiao;
import pojo.ZiXunInfo;

public interface ShouHuanDao {

	public void insertUserInfo(UserInfo userInfo);

	public UserInfo selectUserInfo(@Param("mobile")String mobile) throws Exception;

	public UserJianCeTime selectUserJianCe(@Param("mobile")String mobile);
	
	public void insertUserJianCe(@Param("mobile")String mobile);
	
	public void insertTiWen(@Param("tiWen")Integer tiWen);
	
	public void insertXinTiao(@Param("xinTiao")Integer xinTiao);
	
	public int updateUserJianCe(@Param("mobile")String mobile);

	public TiWen selectTiWen();

	public List<XinTiao> listXinTiao(@Param("mobile")String mobile);

	public List<ZiXunInfo> listZixunByMobile(@Param("mobile")String mobile);

	public List<Map<String, Object>> testMapObject();

	public void insertUserJianCeData(UserJianCeData userJianCeData);
	
	public List<UserJianCeData> selectUserJianCeData(HashMap<String, java.lang.Object> queryMap);
}
