package com.nxd.ftt.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 请求工具类
 */
public class InterfaceUtil
{
	/**
	 * 信任所有站点
	 */
	static {
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
	}

	/**
	 * HTTPS请求
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 * @throws Exception
	 */
	public static String httpsRequest(String requestUrl, String requestMethod,
			String outputStr) throws Exception
	{
		// 建立连接
		URL url = new URL(requestUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		// 使用自定义的信任管理器
//		TrustManager[] tm =
//		{ new MyX509TrustManager() };
		TrustManager[] tm = new TrustManager[]{new X509TrustManager(){
			public X509Certificate[] getAcceptedIssuers(){return null;}
			public void checkClientTrusted(X509Certificate[] certs, String authType){}
			public void checkServerTrusted(X509Certificate[] certs, String authType){}
		}};

		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		conn.setSSLSocketFactory(ssf);
		conn.setDoOutput(true);
		conn.setDoInput(true);

		requestMethod.toUpperCase();
		// 设置请求方式
		conn.setRequestMethod(requestMethod);

		// 如果有数据需要提交时
		if (outputStr != null)
		{
			OutputStream os = conn.getOutputStream();
			os.write(outputStr.getBytes("UTF-8"));
			os.close();
		}

		// 取得输入流
		InputStream is = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// 读取相应内容
		StringBuffer sb = new StringBuffer();
		String str = null;

		while ((str = br.readLine()) != null)
		{
			sb.append(str);
		}

		// 关闭，释放资源
		br.close();
		isr.close();
		is.close();
		conn.disconnect();

		return sb.toString();
	}




	/**
	 * HTTPS请求
	 *
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 * @throws Exception
	 */
	public static String httpsRequest(String requestUrl, String requestMethod,
									  String outputStr,Map<String,String> header) throws Exception
	{
		// 建立连接
		URL url = new URL(requestUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setConnectTimeout(10000);	//连接超时
		conn.setReadTimeout(10000);	//网络访问超时

		// 使用自定义的信任管理器
//		TrustManager[] tm =
//		{ new MyX509TrustManager() };
		TrustManager[] tm = new TrustManager[]{new X509TrustManager(){
			public X509Certificate[] getAcceptedIssuers(){return null;}
			public void checkClientTrusted(X509Certificate[] certs, String authType){}
			public void checkServerTrusted(X509Certificate[] certs, String authType){}
		}};
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		conn.setSSLSocketFactory(ssf);
		conn.setDoOutput(true);
		conn.setDoInput(true);

		requestMethod.toUpperCase();
		// 设置请求方式
		conn.setRequestMethod(requestMethod);

		for (Map.Entry<String,String> entry: header.entrySet()){
			conn.setRequestProperty(entry.getKey(),entry.getValue());
		}

		// 如果有数据需要提交时
		if (outputStr != null)
		{
			OutputStream os = conn.getOutputStream();
			os.write(outputStr.getBytes("UTF-8"));
			os.close();
		}

		// 取得输入流
		InputStream is = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// 读取相应内容
		StringBuffer sb = new StringBuffer();
		String str = null;

		while ((str = br.readLine()) != null)
		{
			sb.append(str);
		}

		// 关闭，释放资源
		br.close();
		isr.close();
		is.close();
		conn.disconnect();

		return sb.toString();
	}



	/**
	 * HTTP请求
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String httpGetRequest(String url) throws Exception
	{
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return result;
	}
	/**
	 * HTTP请求
	 *
	 * @param url
	 * @param param 参数
	 * @return
	 * @throws Exception
	 */
	public static String httpPostRequest(String url, Map<String,String> param) throws Exception
	{
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		List<BasicNameValuePair> list=new ArrayList<>();
		for (String key : param.keySet()){
            list.add(new BasicNameValuePair(key,param.get(key)));
		}
		HttpEntity entity = new UrlEncodedFormEntity(list,"utf-8");
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		HttpEntity responseEntity = response.getEntity();
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		return result;
	}
	/**
	 * HTTP请求
	 *
	 * @param url
	 * @param param 参数
	 * @return
	 * @throws Exception
	 */
	public static String httpPostRequest(String url, Map<String,String> param,Map<String,String> header) throws Exception
	{
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		List<BasicNameValuePair> list=new ArrayList<>();
		for (String key : param.keySet()){
            list.add(new BasicNameValuePair(key,param.get(key)));
		}
		HttpEntity entity = new UrlEncodedFormEntity(list);
		post.setEntity(entity);
		if (header !=null){
			for(Map.Entry<String,String> headerItem : header.entrySet()){
				post.setHeader(headerItem.getKey(), headerItem.getValue());
			}
		}
		HttpResponse response = client.execute(post);
		HttpEntity responseEntity = response.getEntity();
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		return result;
	}

	/**
	 * HTTP请求
	 *
	 * @param url
	 * @param param 参数
	 * @return
	 * @throws Exception
	 */
	public static String httpPostRequest(String url,String param,Map<String,String> header) throws Exception
	{
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(param);
		post.setEntity(entity);
		if (header !=null){
			for(Map.Entry<String,String> headerItem : header.entrySet()){
				post.setHeader(headerItem.getKey(), headerItem.getValue());
			}
		}
		HttpResponse response = client.execute(post);
		HttpEntity responseEntity = response.getEntity();
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		return result;
	}

}
