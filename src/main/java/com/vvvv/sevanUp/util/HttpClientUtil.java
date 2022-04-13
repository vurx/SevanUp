package com.vvvv.sevanUp.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 利用HttpClient进行post请求的工具类
 * @author yanwh
 * @datetime 2017年5月31日 上午11:34:12
 */
public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * 功能：	http post 请求
	 * 参数：  url - 请求的url
	 * 		jsonstr - json串
	 *      charset - 字符集
	 *      outTimes - 数据传输时间(秒)
	 * 返回：	String
	 */
	public static String doPost(String url, String jsonstr, String charset, int outTimes,
								Map<String, String> headDatas) throws Exception{
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			// 连接时间
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			// 数据传输时间
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, outTimes * 1000);
			// 存放url地址
			httpPost = new HttpPost(url);
			// 设置请求头
			httpPost.addHeader("Content-Type", "application/json");
			if (headDatas != null && headDatas.size() > 0) {
				for (Map.Entry<String, String> entry : headDatas.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}
			StringEntity se = new StringEntity(jsonstr, charset);
			httpPost.setEntity(se);
			// 发送请求
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
			// 获取返回的http头信息
			if (headDatas != null) {
				headDatas.clear();
				Header[] heads = response.getAllHeaders();
				if (heads != null && heads.length > 0) {
					for (Header header : heads) {
						headDatas.put(header.getName(), header.getValue());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("HttpClientUtil======>doPost======>error:{}", e);
			StringBuffer sb = new StringBuffer();
			sb.append("调用url:").append(url).append("，异常：").append(e.getMessage());
			throw new Exception(e);
		} finally {
			try {
				httpClient.getConnectionManager().shutdown();
			} catch (Exception e) {
				logger.error("HttpClientUtil======>doPost======>error:{}", e);
			}
		}
		return result;
	}
	/**
	 * 功能：	http delete 请求
	 * 参数：  url - 请求的url
	 * 		jsonstr - json串
	 *      charset - 字符集
	 *      outTimes - 数据传输时间(秒)
	 * 返回：	String
	 */
	public static String doDelete(String url, String jsonstr, String charset, int outTimes,
			Map<String, String> headDatas) {
		HttpClient httpClient = null;
		HttpDelete httpDelete = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			// 连接时间
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			// 数据传输时间
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, outTimes * 1000);
			// 存放url地址
			httpDelete = new HttpDelete(url);
			// 设置请求头
			httpDelete.addHeader("Content-Type", "application/json");
			if (headDatas != null && headDatas.size() > 0) {
				for (Map.Entry<String, String> entry : headDatas.entrySet()) {
					httpDelete.setHeader(entry.getKey(), entry.getValue());
				}
			}
			/**
			StringEntity se = new StringEntity(jsonstr, charset);
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
			httpDelete.setEntity(se);**/
			// 发送请求
			HttpResponse response = httpClient.execute(httpDelete);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
			// 获取返回的http头信息
			if (headDatas != null) {
				headDatas.clear();
				Header[] heads = response.getAllHeaders();
				if (heads != null && heads.length > 0) {
					for (Header header : heads) {
						headDatas.put(header.getName(), header.getValue());
					}
				}
			}
		} catch (Exception e) {
			logger.error("HttpClientUtil======>doPost======>error:{}", e);
			StringBuffer sb = new StringBuffer();
			sb.append("调用url:").append(url).append("，异常：").append(e.getMessage());
//			throw new BError(ErrResultCode.XB_EOP_EC_JS_00013.getCode(), sb.toString());
		} finally {
			try {
				httpClient.getConnectionManager().shutdown();
			} catch (Exception e) {
				logger.error("HttpClientUtil======>doPost======>error:{}", e);
			}
		}
		return result;
	}

	/**
	 * 功能：	http delete 请求
	 * 参数：  url - 请求的url
	 * 		jsonstr - json串
	 *      charset - 字符集
	 *      outTimes - 数据传输时间(秒)
	 * 返回：	String
	 */
	public static String doPatch(String url, String jsonstr, String charset, int outTimes,
			Map<String, String> headDatas) {
		HttpClient httpClient = null;
		HttpPatch httpPatch = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			// 连接时间
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			// 数据传输时间
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, outTimes * 1000);
			// 存放url地址
			httpPatch = new HttpPatch(url);
			// 设置请求头
			httpPatch.addHeader("Content-Type", "application/json");
			if (headDatas != null && headDatas.size() > 0) {
				for (Map.Entry<String, String> entry : headDatas.entrySet()) {
					httpPatch.setHeader(entry.getKey(), entry.getValue());
				}
			}
			StringEntity se = new StringEntity(jsonstr, charset);
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
			httpPatch.setEntity(se);
			// 发送请求
			HttpResponse response = httpClient.execute(httpPatch);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
			// 获取返回的http头信息
			if (headDatas != null) {
				headDatas.clear();
				Header[] heads = response.getAllHeaders();
				if (heads != null && heads.length > 0) {
					for (Header header : heads) {
						headDatas.put(header.getName(), header.getValue());
					}
				}
			}
		} catch (Exception e) {
			logger.error("HttpClientUtil======>doPost======>error:{}", e);
			StringBuffer sb = new StringBuffer();
			sb.append("调用url:").append(url).append("，异常：").append(e.getMessage());
//			throw new BError(ErrResultCode.XB_EOP_EC_JS_00013.getCode(), sb.toString());
		} finally {
			try {
				httpClient.getConnectionManager().shutdown();
			} catch (Exception e) {
				logger.error("HttpClientUtil======>doPost======>error:{}", e);
			}
		}
		return result;
	}

	/**
	 * 功能：	http put 请求
	 * 参数：  url - 请求的url
	 * 		jsonstr - json串
	 *      charset - 字符集
	 *      outTimes - 数据传输时间(秒)
	 * 返回：	String
	 */
	public static String doPut(String url, String jsonstr, String charset, int outTimes,
			Map<String, String> headDatas) {
		HttpClient httpClient = null;
		HttpPut httpPut = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			// 连接时间
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			// 数据传输时间
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, outTimes * 1000);
			// 存放url地址
			httpPut = new HttpPut(url);
			// 设置请求头
			httpPut.addHeader("Content-Type", "application/json");
			if (headDatas != null && headDatas.size() > 0) {
				for (Map.Entry<String, String> entry : headDatas.entrySet()) {
					httpPut.setHeader(entry.getKey(), entry.getValue());
				}
			}
			StringEntity se = new StringEntity(jsonstr, charset);
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
			httpPut.setEntity(se);
			// 发送请求
			HttpResponse response = httpClient.execute(httpPut);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
			// 获取返回的http头信息
			if (headDatas != null) {
				headDatas.clear();
				Header[] heads = response.getAllHeaders();
				if (heads != null && heads.length > 0) {
					for (Header header : heads) {
						headDatas.put(header.getName(), header.getValue());
					}
				}
			}
		} catch (Exception e) {
			logger.error("HttpClientUtil======>doPost======>error:{}", e);
			StringBuffer sb = new StringBuffer();
			sb.append("调用url:").append(url).append("，异常：").append(e.getMessage());
//			throw new BError(ErrResultCode.XB_EOP_EC_JS_00013.getCode(), sb.toString());
		} finally {
			try {
				httpClient.getConnectionManager().shutdown();
			} catch (Exception e) {
				logger.error("HttpClientUtil======>doPost======>error:{}", e);
			}
		}
		return result;
	}

	/**
	 * 功能：	http get 请求
	 * 参数：  url - 请求的url
	 * 		jsonstr - json串
	 *      charset - 字符集
	 *      outTimes - 数据传输时间(秒)
	 * 返回：	String
	 */
	public static String doGet(String url, String charset, int outTimes, Map<String, String> headDatas) {
		HttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			// 连接时间
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			// 数据传输时间
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, outTimes * 1000);
			// 存放url地址
			httpGet = new HttpGet(url);
			// 设置请求头
			httpGet.addHeader("Content-Type", "application/json");
			if (headDatas != null && headDatas.size() > 0) {
				for (Map.Entry<String, String> entry : headDatas.entrySet()) {
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			}
			// 发送请求
			HttpResponse response = httpClient.execute(httpGet);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
			// 获取返回的http头信息
			if (headDatas != null) {
				headDatas.clear();
				Header[] heads = response.getAllHeaders();
				if (heads != null && heads.length > 0) {
					for (Header header : heads) {
						headDatas.put(header.getName(), header.getValue());
					}
				}
			}
		} catch (Exception e) {
			logger.error("HttpClientUtil======>doPost======>error:{}", e);
			StringBuffer sb = new StringBuffer();
			sb.append("调用url:").append(url).append("，异常：").append(e.getMessage());
//			throw new BError(ErrResultCode.XB_EOP_EC_JS_00013.getCode(), sb.toString());
		} finally {
			try {
				httpClient.getConnectionManager().shutdown();
			} catch (Exception e) {
				logger.error("HttpClientUtil======>doPost======>error:{}", e);
			}
		}
		return result;
	}

	/**
	 * spring的http client接口调用
	 * @param serviceUrl 服务端地址
	 * @param className  客户端本地的对象，包含服务端的方法
	 * @param methodName 调用方法名
	 * @param paramClass 入参的类型
	 * @param paramValue 入参的值
	 * @return
	 */
	public static String callSpringRemote(String serviceUrl, String className, String methodName, Class<?>[] paramClass,
			Object[] paramValue) throws Exception {
		try {
			Class<?> classInstance = Class.forName(className);
			HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
			factoryBean.setServiceUrl(serviceUrl);
			factoryBean.setServiceInterface(classInstance);
			factoryBean.afterPropertiesSet();
			logger.debug("接口调用begin:");
			Method method = classInstance.getMethod(methodName, paramClass);
			method.setAccessible(true);
			Object invokeResult = method.invoke(factoryBean.getObject(), paramValue);
			logger.debug("接口调用返回: " + (invokeResult == null ? "null" : invokeResult.toString()));
			return invokeResult == null ? "null" : invokeResult.toString();
		} catch (Exception e) {
			logger.error("callSpringRemote异常", e);
			throw new Exception("callSpringRemote异常", e);
		}
	}
}