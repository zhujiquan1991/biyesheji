package mgr;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.DescStatus;
import pojo.LinJieWenDu;
import pojo.UserInfo;
import util.GsonUtil;

/**
 * 智能集装箱后台
 */
@Controller
@RequestMapping("/show")
public class JiZhuXiangController {
	
	@Autowired
	private JiZhuangXiangService jiZhuangXiangService;
	
	@ResponseBody
	@RequestMapping("/test.do")
	public String testAPI() {
		//System.out.println(shouHuanService.testMapObject());
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
		if(jiZhuangXiangService.isRegister(mobile)){
			resultMap.put("code","1");
			resultMap.put("message","注册失败，该号码已经被注册");
			return GsonUtil.toJson(resultMap);
		}
		jiZhuangXiangService.registerUser(mobile,pwd);
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
			 UserInfo userInfo = jiZhuangXiangService.selectUserInfo(mobile);
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
	 * 获取监控数据
	 */
	@ResponseBody
	@RequestMapping(value="/data.do",produces={"application/json;charset=UTF-8"})
	public String data() {
		return jiZhuangXiangService.data();
	}
	
	/**
	 * 初始化设备状态
	 */
	@ResponseBody
	@RequestMapping(value="/initDescStatus.do",produces={"application/json;charset=UTF-8"})
	public String initDesc() {
		jiZhuangXiangService.initDesc();
		HashMap<String, String> data = new HashMap<String,String>();
		data.put("result", "ok");
		return GsonUtil.toJson(data);
	}
	
	/**
	 * 获取设备状态
	 */
	@ResponseBody
	@RequestMapping(value="/loadDescStatus.do",produces={"application/json;charset=UTF-8"})
	public String loadDescStatus() {
		HashMap<String, Object> data = new HashMap<String,Object>();
		List<DescStatus> loadDescStatus = jiZhuangXiangService.loadDescStatus();
		data.put("data", loadDescStatus);
		return GsonUtil.toJson(data);
	}
	
	/**
	 * 改变设备状态
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/switchDesc.do",produces={"application/json;charset=UTF-8"})
	public String switchDesc(@RequestParam("desc") String desc,@RequestParam("status")Integer status) throws Exception {
		System.out.println("设备为:"+desc+"状态为:"+status);
		jiZhuangXiangService.updateDescStatus(desc,status);
		HashMap<String, Object> data = new HashMap<String,Object>();
		data.put("result", "ok");
		return GsonUtil.toJson(data);
	}
	
	/**
	 * 改变临界温度
	 */
	@ResponseBody
	@RequestMapping(value="/updateLinJieWenDu.do",produces={"application/json;charset=UTF-8"})
	public String updateLinJieWenDu(@RequestParam("wenDu") Integer wenDu) {
		System.out.println("更新温度为:"+wenDu);
		HashMap<String, Object> data = new HashMap<String,Object>();
		jiZhuangXiangService.updateLinJieWenDu(wenDu);
		data.put("result", "ok");
		return GsonUtil.toJson(data);
	}
	
	/**
	 * 获取临界温度
	 */
	@ResponseBody
	@RequestMapping(value="/loadLinJieWenDu.do",produces={"application/json;charset=UTF-8"})
	public String loadLinJieWenDu() {
		HashMap<String, Object> data = new HashMap<String,Object>();
		LinJieWenDu wenDu = jiZhuangXiangService.selectLinjieWenDu();
		data.put("linJieWenDu", String.valueOf(wenDu.getLinJieWenDu()));
		return GsonUtil.toJson(data);
	}
	
}
