package db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.DescStatus;
import pojo.GuangDu;
import pojo.LinJieWenDu;
import pojo.UserInfo;
import pojo.WenShiDu;

public interface JiZhuangXiangDao {

	public void insertUserInfo(UserInfo userInfo);

	public UserInfo selectUserInfo(@Param("mobile")String mobile) throws Exception;
	
//	public void insertWenShiDu(@Param("wenDu")Integer wenDu,@Param("shiDu") Integer shiDu);
//	
//	public void insertGuangDu(@Param("guangDu")Integer guangDu);
	
	public GuangDu selectGuangDu();
	
	public WenShiDu selectWenShiDu();

	public void updateInitDesc();

	public List<DescStatus> selectDescStatus();

	public void updateLinJieWenDu(@Param("wenDu")Integer wenDu);
	
	public LinJieWenDu selectLinJieWenDu();

	public void updateDescStatus(DescStatus descStatus);

}
