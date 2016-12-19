package mgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.UserInfo;
import pojo.UserJianCeData;
import util.GsonUtil;

/**
 * 智能手环（安卓端后台）
 */
@Controller
@RequestMapping("/mgr")
public class ShouHuanController {
	
	@Autowired
	private ShouHuanService shouHuanService;
	
	@ResponseBody
	@RequestMapping("/test.do")
	public String testAPI() {
		System.out.println(shouHuanService.testMapObject());
		return "success";
	}
	
	/**
	 * 用户注册
	 * 0为成功，1为账号已存在，
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/register.do",produces={"application/json;charset=UTF-8"})
	public String registerUser(@RequestParam("mobile") String mobile,@RequestParam("pwd") String pwd) throws Exception{
		 HashMap<String, String> resultMap = new HashMap<String, String>();
		//判断用户是否已注册
		if(shouHuanService.isRegister(mobile)){
			resultMap.put("code","1");
			resultMap.put("message","注册失败，该号码已经被注册");
			return GsonUtil.toJson(resultMap);
		}
		shouHuanService.registerUser(mobile,pwd);
		resultMap.put("code","0");
		resultMap.put("message","注册成功");
		return GsonUtil.toJson(resultMap);
	}
	
	/**
	 * 用户登录
	 * 0成功，1用户不存在，2密码错误
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login.do",produces={"application/json;charset=UTF-8"})
	public String login(@RequestParam("mobile") String mobile,@RequestParam("pwd") String pwd) {
		 try {
			 System.out.println(mobile+":登录系统");
			 HashMap<String, String> resultMap = new HashMap<String, String>();
			 UserInfo userInfo = shouHuanService.selectUserInfo(mobile);
				//判断用户是否存在
				if(userInfo == null){
					resultMap.put("code","1");
					resultMap.put("message","用户不存在，请先注册");
					return GsonUtil.toJson(resultMap);
				} else{
					//判断密码是否正确
					if(pwd.equals(userInfo.getPwd())){
						resultMap.put("code","0");
						resultMap.put("message","登录成功");
						resultMap.put("admin",String.valueOf(userInfo.getRole()));
						return GsonUtil.toJson(resultMap);
					}else {
						resultMap.put("code","2");
						resultMap.put("message","密码错误，请重新输入");
						return GsonUtil.toJson(resultMap);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * 检测接口
	 * 0为操作成功，1为操作失败
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/jiance.do",produces={"application/json;charset=UTF-8"})
	public String beginJianCe(@RequestParam("mobile") String mobile) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (shouHuanService.beginJianCe(mobile)) {
			resultMap.put("code","0");
			resultMap.put("message","操作成功");
			return GsonUtil.toJson(resultMap);
		} else {
			resultMap.put("code","1");
			resultMap.put("message","操作失败");
			return GsonUtil.toJson(resultMap);
		}
	}
	
	/**
	 * 取得结果接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getJianCe.do",produces={"application/json;charset=UTF-8"})
	public String getJianCe(@RequestParam("mobile") String mobile) {
		return GsonUtil.toJson(shouHuanService.getJianCe(mobile));
	}
	
	/**
	 * 取得咨询推荐
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/ziXun.do",produces={"application/json;charset=UTF-8"})
	public String getZiXun(@RequestParam("mobile") String mobile) {
		HashMap<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("data", shouHuanService.listZixunByMobile(mobile));
		return GsonUtil.toJson(resultData);
	}
	
	/**
	 * 取得咨询推荐
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/userJianCeData.do",produces={"application/json;charset=UTF-8"})
	public String getUserJianCeData(@RequestParam("mobile") String mobile,@RequestParam("page") Integer page) {
		HashMap<String, Object> resultData = new HashMap<String, Object>();
		HashMap<String, Object> queryMap = new HashMap<String,Object>();
		queryMap.put("mobile", mobile);
		Integer end = page*20;
		queryMap.put("start", (page-1)*20);
		queryMap.put("end",end);
		resultData.put("data", shouHuanService.selectUserJianCeData(queryMap));
		return GsonUtil.toJson(resultData);
	}
	
	/**
	 * 取得咨询推荐
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertJianCeData.do",produces={"application/json;charset=UTF-8"})
	public String insertJianCeData(@RequestParam("tiWen") Integer tiWen,@RequestParam("xinTiao") Integer xinTiao) {
		System.out.println("收到数据="+tiWen+"心跳:"+xinTiao);
		shouHuanService.insertTiWen(tiWen);
		shouHuanService.insertXinTiao(xinTiao);
		return "ok";
	}
	
	public static void main(String[] args) {
		   Set set=new HashSet();
		   set.add("12");
		   set.add("12");
		   set.add("13");
		   set.add("14");
		   set.add("15"); //重复的abc,set会自动将其去掉  
		   System.out.println("size="+ set.size());
		   ArrayList arrayList = new ArrayList<String>(set);
		   System.out.println(GsonUtil.toJson(set));
		   System.out.println(GsonUtil.toJson(arrayList));
	}
	
}
