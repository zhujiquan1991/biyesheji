package util;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * HttpClient请求工具类
 */
public class HttpClientWebUtil {
	 
	 //请求超时
	 private static final int HTTP_Request_TIMEOUT = 10*1000;
     //连接超时
	 private static final int HTTP_Connetion_TIMEOUT = 10*1000;
	 //请求编码
	 private static String encode = "UTF-8";
	 
	 //设置连接超时与请求超时，暂时不设置
//	 private void setOutTime(HttpClient httpClient) {
//		    HttpConnectionManagerParams managerParams = httpClient.getParams();
//		    // 设置连接超时时间(单位毫秒)
//		    managerParams.setConnectionTimeout(HTTP_Connetion_TIMEOUT);
//		    // 设置读数据超时时间(单位毫秒)
//		    //managerParams.setSoTimeout(120000);
//	}
	 
	 /**
	  * post请求
	  * @param requestUrl
	  * @param requestMap
	  * @return 成功responseBody字符串，失败返回null
	  * @throws Exception
	  */
	 public static String doHttpPost(String requestUrl, Map<String, Object> requestMap) throws Exception {
		 	// 创建默认的客户端实例  
		 	HttpClient client = new DefaultHttpClient();

		 	// 创建客户端实例
		 	HttpPost post = new HttpPost(requestUrl);

		 	//设置超时时间
		 	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HTTP_Request_TIMEOUT).setConnectTimeout(HTTP_Connetion_TIMEOUT).build();
		 	post.setConfig(requestConfig);
	        // 设置请求参数
	        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	        if (requestMap!=null) {
	        	nvps.add(new BasicNameValuePair("params",requestMap.toString()));
			}
	        post.setEntity(new UrlEncodedFormEntity(nvps, encode));
	        try {
	        	 // 进行请求
		        HttpResponse httpResponse = client.execute(post);
		        //判断返回状态（200状态码，代表请求成功）
		        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
		        	String responseBody = EntityUtils.toString(httpResponse.getEntity());
		        	//关闭连接
		        	client.getConnectionManager().shutdown();
		            return responseBody;
		        }
		        return null;
			} catch (Exception e) {
				//关闭连接
	        	client.getConnectionManager().shutdown();
	        	e.printStackTrace();
	        	throw e;
			}
	    }
	   
	 /**
	  * get请求
	  * @param requestUrl
	  * @param requestMap
	  * @return 返回responseBody字符串
	  * @throws Exception
	  */
	   public static String doHttpGet(String requestUrl, Map<String, Object> requestMap) throws Exception {
		    // 创建默认的客户端实例  
		 	HttpClient client = new DefaultHttpClient();
		 	// 创建客户端实例
		 	HttpGet get = new HttpGet(requestUrl);
	        // 设置请求参数,拼接参数
		 	if(requestMap !=null){
		 		requestUrl = requestUrl +"?params=" + requestMap.toString();
		 	}
		 	 // 实例化HTTP方法  
            HttpGet request = new HttpGet();  
            request.setURI(new URI(requestUrl));
	        try {
	        	// 进行请求
	        	 HttpResponse httpResponse = client.execute(get);
	 	        //判断返回状态（200状态码，代表请求成功）
	 	        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	 	        	String responseBody = EntityUtils.toString(httpResponse.getEntity());
	 	        	//关闭连接
	 	        	client.getConnectionManager().shutdown();
	 	            return responseBody;
	 	        }
	 	        return null;
			} catch (Exception e) {
				//关闭连接
 	        	client.getConnectionManager().shutdown();
 	        	e.printStackTrace();
				throw e;
			}
	       
	    }
	   
	   /**
	    * 調用第三方獲取響應
	    * @param requestUrl 请求地址
	    * @return 返回InputStream对象
	    * @throws Exception 异常
	    */
	   public static byte[] doHttpGet(String requestUrl) throws Exception {
		   // 创建默认的客户端实例  
		 	HttpClient client = new DefaultHttpClient();
		 	// 创建客户端实例
		 	HttpGet get = new HttpGet(requestUrl);
		 	try {
		 		HttpResponse httpResponse = client.execute(get);
		 		//判断返回状态（200状态码，代表请求成功）
	 	        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	 	        	byte[] responseBody = EntityUtils.toByteArray(httpResponse.getEntity());
	 	        	//关闭连接
	 	        	client.getConnectionManager().shutdown();
	 	            return responseBody;
	 	        }
	 	       return null;
		 	} catch (Exception e) {
		 		//关闭连接
 	        	client.getConnectionManager().shutdown();
 	        	e.printStackTrace();
				throw e;
		 	}
	   }
	   
	   public static void main(String[] args) throws Exception {
		   String requestUrl ="http://192.168.1.77/ecmd?pin%20set%20k3%20on";
		   HttpClientWebUtil.doHttpGet(requestUrl, null);
	}
}
