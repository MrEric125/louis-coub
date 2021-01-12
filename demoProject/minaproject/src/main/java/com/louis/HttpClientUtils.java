package com.louis;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 基于 httpclient 的 http工具类
 * @author zhangjingjing
 */
@SuppressWarnings("all")
public class HttpClientUtils {

	public static final String CHARSET = "UTF-8";

	static final int maxTotal = 500;// 总最大连接数
	static final int defaultMaxPerRoute = 100;// 每条线路最大连接数 ,本系统核心线程数,这样永远不会超过最大连接

	static final Logger logger = Logger.getLogger(HttpClientUtils.class.getName());

	static HttpClientBuilder clientBuilder;

	private HttpClientUtils() {}

	static {
		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
		ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
		registryBuilder.register("http", plainSF);
		//指定信任密钥存储对象和连接套接字工厂
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			//信任任何链接
			TrustStrategy anyTrustStrategy = new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			};
			HostnameVerifier hostnameVerifier = new HostnameVerifier() {
				@Override
				public boolean verify(String s, SSLSession sslSession) {
					return true;
				}
			};
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(trustStore, anyTrustStrategy).build();
			LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
			registryBuilder.register("https", sslSF);
		} catch (KeyStoreException e) {
			throw new RuntimeException(e);
		} catch (KeyManagementException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		Registry<ConnectionSocketFactory> registry = registryBuilder.build();
		//设置连接管理器
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
//        connManager.setDefaultConnectionConfig(connConfig);
//        connManager.setDefaultSocketConfig(socketConfig);
		//构建客户端
		RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000)
				// .setProxy(new HttpHost("127.0.0.1", 8888))
				.build();
		clientBuilder = HttpClientBuilder.create().setConnectionManager(connManager).setDefaultRequestConfig(config)
				.evictExpiredConnections()
//				.setConnectionManagerShared(true)
				//MaxIdleTime 必须小于服务端的关闭时间否则有可能出现NoHttpResponse
				//用来关闭闲置连接，它会启动一个守护线程进行清理工作。用户可以通过builder#evictIdleConnections开启该组件，并通过builder#setmaxIdleTime设置最大空闲时间。;
				.evictIdleConnections(30, TimeUnit.SECONDS);

	}

//	public static CloseableHttpClient getHttpClient() throws KeyManagementException, NoSuchAlgorithmException {
//
//		SSLContext sslcontext = SSLContexts.custom().build();
//		sslcontext.init(null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
//		SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext,
//				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//		RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000)
//				// .setProxy(new HttpHost("127.0.0.1", 8888))
//				.build();
//		return HttpClientBuilder.create().setSSLSocketFactory(factory).setDefaultRequestConfig(config).build();
//	}

	private static CloseableHttpClient getHttpsClient() {
		return clientBuilder.build();
	}

//	private static CloseableHttpClient getPoolHttpClient() {
//		// 设置连接池
//		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
//		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
//		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
//				.register("http", plainsf).register("https", sslsf).build();
//		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
//		// 配置最大连接数
//		cm.setMaxTotal(maxTotal);
//		// 配置每条线路的最大连接数
//		cm.setDefaultMaxPerRoute(defaultMaxPerRoute);
//
//		// 请求重试处理
//		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
//			@Override
//			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
//				if (executionCount >= 2) {// 如果已经重试了2次，就放弃
//					return false;
//				}
//				if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
//					return true;
//				}
//				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
//					return false;
//				}
//				if (exception instanceof InterruptedIOException) {// 超时
//					return false;
//				}
//				if (exception instanceof UnknownHostException) {// 目标服务器不可达
//					return false;
//				}
//				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
//					return false;
//				}
//				if (exception instanceof SSLException) {// SSL握手异常
//					return false;
//				}
//
//				HttpClientContext clientContext = HttpClientContext.adapt(context);
//				HttpRequest request = clientContext.getRequest();
//
//				if (!(request instanceof HttpEntityEnclosingRequest)) {
//					return true;
//				}
//				return false;
//			}
//
//		};
//
//		CloseableHttpClient newHttpclient = null;
//
//		newHttpclient = HttpClients.custom().setConnectionManager(cm)
//				// .setDefaultRequestConfig(requestConfig)
//				.setRetryHandler(httpRequestRetryHandler).build();
//
//		return newHttpclient;
//	}

	public static String doGet(String url, Map<String, Object> params) throws Exception {
		return doGet(url, params, CHARSET, null);
	}

	public static String doGet(String url, Map<String, Object> params, Map<String, String> headers) throws Exception {
		return doGet(url, params, CHARSET, headers);
	}

	public static String doPost(String url, Map<String, Object> params) throws Exception {
		return doPost(url, params, null, CHARSET, null);
	}

	public static String doPost(String url, Map<String, Object> params, Map<String, String> headers) throws Exception {
		return doPost(url, params, headers, CHARSET, null);
	}

	public static String doPostJson(String url, Map<String, Object> params, Map<String, String> headers) throws Exception {
		return doPost(url, params, headers, CHARSET, "json");
	}

	public static String doPostJson(String url, String params, Map<String, String> headers) throws Exception {
		return doPost(url, params, headers, CHARSET, "json");
	}

	/**
	 * HTTP Get 获取内容
	 *
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 */
	public static String doGet(String url, Map<String, Object> params, String charset, Map<String, String> headers) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpClient httpClient = null;
		try {
			httpClient = getHttpsClient();

			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					String value = String.valueOf(entry.getValue());
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
				if (StringUtils.contains(url, '?')) {
					url = StringUtils.join(
							new String[] { url, "&", EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset)) });
				} else {
					url = StringUtils.join(
							new String[] { url, "?", EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset)) });
				}
			}
			HttpGet httpGet = new HttpGet(url);
			//设置请求头
			if(null != headers && !headers.isEmpty()){
				for(Map.Entry<String, String> head : headers.entrySet()){
					httpGet.setHeader(head.getKey(), head.getValue());
				}
			}
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, charset);
			}
			EntityUtils.consume(entity);
			return result;
		} finally {
			if (httpClient != null) {
				httpClient.close();
			}
		}
	}

	/**
	 * HTTP Post 获取内容
	 *
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 */
	public static String doPost(String url, Object params,Map<String, String> headers, String charset, String type) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpClient httpClient = null;
		try {
			httpClient = getHttpsClient();
			HttpPost httpPost = new HttpPost(url);
			//设置请求头
			if(null != headers && !headers.isEmpty()){
				for(Map.Entry<String, String> head : headers.entrySet()){
					httpPost.setHeader(head.getKey(), head.getValue());
				}
			}
			//设置请求参数
			if(StringUtils.equals(type, "json")){
				if(params != null){
					String paramStr = null;
					if(params instanceof Map){
						paramStr = JSON.toJSONString(params);
					}else {
						paramStr = params.toString();
					}
					StringEntity stringEntity = new StringEntity(paramStr, Charset.forName(charset));
					stringEntity.setContentType("application/json");
					stringEntity.setContentEncoding(charset);
					httpPost.setEntity(stringEntity);
				}
			}else{
				List<NameValuePair> pairs = null;
				if (params != null && params instanceof Map) {
					Map<String, Object> paramMap = (Map) params;
					pairs = new ArrayList<>(paramMap.size());
					for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
						Object value = entry.getValue();
						if (value != null) {
							pairs.add(new BasicNameValuePair(entry.getKey(), String.valueOf(value)));
						}
					}
				}
				if (pairs != null && pairs.size() > 0) {
					httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
				}
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, charset);
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} finally {
			if (httpClient != null) {
				httpClient.close();
			}
		}
	}

	static final String[] imgPrefix = new String[] { "jpg", "png", "jpeg", "bmp" };

	/**
	 * 下载图片  并按指定路径保存图片
	 * @param url
	 * @param savePath
	 * @param fileName
	 * @return  图片的完整路径
	 *
	 * 实际的 fileName
	 * 实际的path
	 * @throws Exception
	 */
	public static Map<String,String> downloadImage(String url, String savePath, String fileName) throws Exception {
		Map<String, String> map = Maps.newHashMap();
		if (StringUtils.isEmpty(url)) {
			logger.warning("图片下载不能为空");
			return map;
		}
		if (StringUtils.isEmpty(savePath)) {
			logger.warning("图片保存地址不能为空");
			return map;
		}
		if (StringUtils.isEmpty(fileName)) {
			logger.warning("图片 保存名称不能为空");
			return map;
		}
		boolean flag = false;
		String endWith = url.substring(url.lastIndexOf(".")+1);
		for (String prefix : imgPrefix) {
			if (endWith.equalsIgnoreCase(prefix)) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new IllegalArgumentException("超链接所请求的图片格式不支持, 目前仅支持图片格式为 jpg png jpeg bmp.   imgType="+endWith);
		}
		if(fileName.indexOf(".") > 1){
			if(fileName.endsWith(".")){
				fileName = fileName + endWith;
			}else{
				String end = fileName.substring(fileName.lastIndexOf(".")+1);
				if(!end.equalsIgnoreCase(endWith)){
					fileName = fileName +"."+endWith;
				}
			}
		}else{
			fileName = fileName +"."+endWith;
		}

		String targetFile = savePath + File.separator + fileName;
		CloseableHttpClient httpClient = null;
		FileOutputStream fos = null;
		try {
			httpClient = getHttpsClient();
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			InputStream input = response.getEntity().getContent();
			File file = new File(savePath);
			if (!file.exists()) {
				file.mkdirs();
				logger.warning("图片保存路径已经创建成功 savePath: " + savePath);
			} else {
				logger.warning("图片保存路径已经存在 savePath: " + savePath);
			}
			file = new File(targetFile);
			file.delete();
			file.createNewFile();
			logger.warning("图片已经创建成功 targetFile: " + targetFile);
			String path = file.getAbsolutePath();
			logger.info("图片的完整路径： " + path);
			fos = new FileOutputStream(targetFile);
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = input.read(data)) != -1) {
				fos.write(data, 0, len);
			}
			logger.info("图片内容已全部读完   targetFile: " + targetFile);
			map.put("path", path);
			map.put("fileName", file.getName());
			return map;
		}catch(Exception e){
			logger.warning("下载背景图片失败");
			throw e;
		}finally {
			if (httpClient != null) {
				httpClient.close();
			}
			if(fos != null){
				fos.close();
			}
		}
	}

}
