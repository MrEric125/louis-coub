package com.louis;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by lixianch on 2016/7/13.
 */
@Slf4j
public class HttpUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
			

	public static String httpGet(String url) {
		return httpGet(url, Integer.valueOf(10000), Integer.valueOf(10000));
	}

	public static String httpsGet(String url) {
		return httpsGet(url, Integer.valueOf(10000), Integer.valueOf(10000));
	}

	public static String httpsGet(String url, Integer connectTimeout, Integer socketTimeout) {
		return send(HttpMethod.GET.name(), url, null, connectTimeout.intValue(), socketTimeout.intValue(), true);
	}

	public static String httpGet(String url, Integer connectTimeout, Integer socketTimeout) {
		return send(HttpMethod.GET.name(), url, null, connectTimeout.intValue(), socketTimeout.intValue(), false);
	}

	public static String httpGet(String url, Integer connectTimeout,
			Integer socketTimeout, Map<String, String> headers) {
		return send(HttpMethod.GET.name(), url, null, connectTimeout.intValue(), socketTimeout.intValue(), headers);
	}

	public static String httpPost(String url, List<NameValuePair> formparams) {
		return httpPost(url, formparams, Integer.valueOf(10000), Integer.valueOf(10000));
	}

	public static String httpsPost(String url, List<NameValuePair> formparams) {
		return httpsPost(url, formparams, Integer.valueOf(10000), Integer.valueOf(10000));
	}

	public static String httpsPost(String url, String data) {
		return httpsPost(url, Integer.valueOf(10000), Integer.valueOf(10000),data);
	}

	public static String httpPost(String url, List<NameValuePair> formparams, Integer connectTimeout, Integer socketTimeout) {
		return send(HttpMethod.POST.name(), url, formparams, connectTimeout.intValue(), socketTimeout.intValue(), false);
	}

	public static String httpsPost(String url, List<NameValuePair> formparams, Integer connectTimeout, Integer socketTimeout) {
		return send(HttpMethod.POST.name(), url, formparams, connectTimeout.intValue(), socketTimeout.intValue(), true);
	}

	public static String httpsPost(String url, Integer connectTimeout, Integer socketTimeout,String data) {
		return send(HttpMethod.POST.name(), url, connectTimeout.intValue(), socketTimeout.intValue(),data, true);
	}

	public static String httpPost(String url, List<NameValuePair> formparams, Map<String, String> headers) {
		return httpPost(url, formparams, Integer.valueOf(10000), Integer.valueOf(10000), headers);
	}

	public static String httpPost(String url, List<NameValuePair> formparams,
			Integer connectTimeout, Integer socketTimeout,
			Map<String, String> headers) {
		
		return send(HttpMethod.POST.name(), url, formparams,
				connectTimeout.intValue(), socketTimeout.intValue(), headers);
	}

	public static String asyPost(String url, List<NameValuePair> formparams,
			Map<String, String> headers) {
		
		return asySend(HttpMethod.POST.name(), url, formparams, 10000,
				10000, headers, false);
	}

	public static String asyPost(String url, List<NameValuePair> formparams,
			Map<String, String> headers, int defaultTimeOut) {
		return asySend(HttpMethod.POST.name(), url, formparams,
				defaultTimeOut, defaultTimeOut, headers, false);
	}

	public static String send(String method, String url,
                              List<NameValuePair> formparams, int connectTimeout,
                              int socketTimeout, boolean isSsl) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		CloseableHttpClient closeableHttpClient = null;
		if (!isSsl) {
			closeableHttpClient = httpClientBuilder.setRetryHandler((exception, executionCount, context) -> {
				if (executionCount > 3)
					return false;
				if ((exception instanceof ConnectTimeoutException))
					return true;
				return false;
			}).build();
		} else {
			try {
				SSLContext sslContext = new org.apache.http.conn.ssl.SSLContextBuilder()
					.loadTrustMaterial(null, (chain, authType) -> true).build();

				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
				closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			} catch (Exception e) {
				e.printStackTrace();
			}
			closeableHttpClient = HttpClients.createDefault();
		}

		CloseableHttpResponse httpResponse = null;
		try {
			RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout)
					.setSocketTimeout(socketTimeout).build();

			if (method.equals(HttpMethod.GET.name())) {
				HttpGet httpGet = new HttpGet(url);
				httpGet.setConfig(config);
				httpResponse = closeableHttpClient.execute(httpGet);
			} else if (method.equals(HttpMethod.POST.name())) {
				HttpPost httpPost = new HttpPost(url);
				httpPost.setConfig(config);
				if (formparams != null) {
					UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
					httpPost.setEntity(uefEntity);
				}
				httpResponse = closeableHttpClient.execute(httpPost);
			} else {
				throw new RuntimeException("HTTP请求失败 ,不支持该请求方法.method:" + method);
			}

			HttpEntity entity = httpResponse.getEntity();
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				LOGGER.error("HTTP请求失败：" + statusCode + ",url:" + url);
				throw new RuntimeException("HTTP请求失败：" + statusCode);
			}
			String content;
			if (entity != null) {
				content = EntityUtils.toString(entity, "UTF-8");
				LOGGER.debug("response content:" + content);
				return content;
			}
			return "";
		} catch (Exception e) {
			LOGGER.error("HTTP请求失败 ,url:" + url);
			throw new RuntimeException("HTTP请求失败 ,url:" + url + "error:" + e.getMessage(), e);
		} finally {
			try {
				if (closeableHttpClient != null) {
					closeableHttpClient.close();
				}
				if (httpResponse != null)
					httpResponse.close();
			} catch (IOException e) {
				LOGGER.error("关闭HTTP连接失败", e);
			}
		}
	}

	public static String send(String method, String url, int connectTimeout,
							  int socketTimeout,String data, boolean isSsl) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		CloseableHttpClient closeableHttpClient = null;
		if (!isSsl) {
			closeableHttpClient = httpClientBuilder.setRetryHandler(new HttpRequestRetryHandler() {

				public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {

					if (executionCount > 2)
						return false;

					if ((exception instanceof ConnectTimeoutException))
						return true;

					return false;
				}
			}).build();
		} else {
			try {
				SSLContext sslContext = new org.apache.http.conn.ssl.SSLContextBuilder()
						.loadTrustMaterial(null, new TrustStrategy() {
							public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
								return true;
							}
						}).build();

				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
				closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			} catch (Exception e) {
				e.printStackTrace();
			}
			closeableHttpClient = HttpClients.createDefault();
		}

		CloseableHttpResponse httpResponse = null;
		try {
			RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout)
					.setSocketTimeout(socketTimeout).build();

			if (method.equals(HttpMethod.GET.name())) {
				HttpGet httpGet = new HttpGet(url);
				httpGet.setConfig(config);
				httpResponse = closeableHttpClient.execute(httpGet);
			} else if (method.equals(HttpMethod.POST.name())) {
				HttpPost httpPost = new HttpPost(url);
				httpPost.setConfig(config);
				if (StringUtils.isNotBlank(data)) {
					StringEntity sEntity = new StringEntity(data, ContentType.APPLICATION_JSON);
					httpPost.setEntity(sEntity);
				}
				httpResponse = closeableHttpClient.execute(httpPost);
			} else {
				throw new RuntimeException("HTTP请求失败 ,不支持该请求方法.method:" + method);
			}

			HttpEntity entity = httpResponse.getEntity();
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				LOGGER.error("HTTP请求失败：" + statusCode + ",url:" + url);
				throw new RuntimeException("HTTP请求失败：" + statusCode);
			}
			String content;
			if (entity != null) {
				content = EntityUtils.toString(entity, "UTF-8");
				LOGGER.debug("response content:" + content);
				return content;
			}
			return "";
		} catch (Exception e) {
			LOGGER.error("HTTP请求失败 ,url:" + url);
			throw new RuntimeException("HTTP请求失败 ,url:" + url + "error:" + e.getMessage(), e);
		} finally {
			try {
				if (closeableHttpClient != null) {
					closeableHttpClient.close();
				}
				if (httpResponse != null)
					httpResponse.close();
			} catch (IOException e) {
				LOGGER.error("关闭HTTP连接失败", e);
			}
		}
	}

	public static String send(String method, String url,
                              List<NameValuePair> formparams, int connectTimeout,
                              int socketTimeout, Map<String, String> headers) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		CloseableHttpClient closeableHttpClient = httpClientBuilder
				.setRetryHandler(new HttpRequestRetryHandler() {
					public boolean retryRequest(IOException exception,
							int executionCount, HttpContext context) {
						if (executionCount > 2) {
							return false;
						}
						if ((exception instanceof ConnectTimeoutException)) {
							return true;
						}
						return false;
					}
				}).build();

		CloseableHttpResponse httpResponse = null;
		HttpGet httpGet = null;
		HttpPost httpPost = null;
		HttpEntity entity = null;
		try {
			RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			long duration;
			if (method.equals(HttpMethod.GET.name())) {
				httpGet = new HttpGet(url);
				httpGet.setConfig(config);
				setHeader(httpGet, headers);
				long start = System.currentTimeMillis();
				httpResponse = closeableHttpClient.execute(httpGet);
				duration = System.currentTimeMillis() - start;

				if (duration > 1000L)
					LOGGER.error("HTTP请求太慢，耗时：" + duration + " MS,url:" + url);
			} else if (method.equals(HttpMethod.POST.name())) {
				httpPost = new HttpPost(url);
				httpPost.setConfig(config);
				setHeader(httpPost, headers);
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");

				httpPost.setEntity(uefEntity);
				httpResponse = closeableHttpClient.execute(httpPost);
			} else {
				throw new RuntimeException("HTTP请求失败 ,不支持该请求方法.method:" + method);
			}

			entity = httpResponse.getEntity();
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				LOGGER.error("HTTP请求失败：" + statusCode + ",url:" + url);
				throw new RuntimeException("HTTP请求失败：" + statusCode);
			}
			String content;
			if (entity != null) {
				content = EntityUtils.toString(entity, "UTF-8");
				LOGGER.debug("response content:" + content);
				return content;
			}
			return "";
		} catch (Exception e) {
			LOGGER.error("HTTP请求失败 ,url:" + url, e);
			throw new RuntimeException("HTTP请求失败 ,url:" + url + "error:" + e.getMessage(), e);
					
		} finally {
			try {
				if (httpGet != null) {
					httpGet.abort();
				}

				if (httpPost != null) {
					httpPost.abort();
				}

				if (httpResponse != null) {
					httpResponse.close();
				}
				if (entity != null) {
					EntityUtils.consume(entity);
				}

				closeableHttpClient.close();
			} catch (IOException e) {
				LOGGER.error("关闭HTTP连接失败", e);
			}
		}
	}

	public static String asyPost(String url, List<NameValuePair> formparams) {
		return null;
	}

	public static String asyGet(String url) {
		return asyGet(url, Integer.valueOf(10000), Integer.valueOf(10000));
	}

	public static String asyGet(String url, Map<String, String> headers) {
		return asyGet(url, Integer.valueOf(10000), Integer.valueOf(10000),
				headers);
	}

	public static String asyGet(String url, Integer connectTimeout,
			Integer socketTimeout, Map<String, String> headers) {
		return asySend(HttpMethod.GET.name(), url, null,
				connectTimeout.intValue(), socketTimeout.intValue(), headers,
				false);
	}

	public static String asyGet(String url, Integer connectTimeout,
			Integer socketTimeout) {
		return asySend(HttpMethod.GET.name(), url, null,
				connectTimeout.intValue(), socketTimeout.intValue(), null,
				false);
	}

	public static String asySend(String method, String url,
                                 List<NameValuePair> formparams, int connectTimeout,
                                 int socketTimeout, Map<String, String> headers, boolean isSSL) {
		CloseableHttpAsyncClient httpclient = null;

		Future<?> future = null;
		HttpResponse response = null;
		HttpGet httpGet = null;
		HttpPost httpPost = null;
		HttpEntity entity = null;
		final StringBuffer sb = new StringBuffer();
		try {
			if (!isSSL) {
				httpclient = HttpAsyncClients.createDefault();
			} else {
				TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
					public boolean isTrusted(X509Certificate[] certificate,
							String authType) {
						return true;
					}
				};
				SSLContext sslContext = SSLContexts.custom()
						.loadTrustMaterial(null, acceptingTrustStrategy)
						.build();

				httpclient = HttpAsyncClients
						.custom()
						.setSSLHostnameVerifier(
								SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
						.setSSLContext(sslContext).build();
			}

			httpclient.start();

			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(connectTimeout)
					.setSocketTimeout(socketTimeout).build();
			int statusCode;
			if (method.equals(HttpMethod.GET.name())) {
				httpGet = new HttpGet(url);
				httpGet.setConfig(config);
				setHeader(httpGet, headers);
				long start = System.currentTimeMillis();
				future = httpclient.execute(httpGet, null);
				response = (HttpResponse) future.get();
				long duration = System.currentTimeMillis() - start;

				if (duration > 1000L) {
					LOGGER.error("HTTP请求太慢，耗时：" + duration + " MS,url:" + url);
				}

				entity = response.getEntity();
				statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 200) {
					LOGGER.error("HTTP请求失败：" + statusCode + ",url:" + url);
					throw new RuntimeException("HTTP请求失败：" + statusCode);
				}
				String content;
				if (entity != null) {
					content = EntityUtils.toString(entity, "UTF-8");
					LOGGER.debug("response content:" + content);
					return content;
				}
				return "";
			}
			if (method.equals(HttpMethod.POST.name())) {
				httpPost = new HttpPost(url);
				httpPost.setConfig(config);
				setHeader(httpPost, headers);
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(
						formparams, "UTF-8");

				httpPost.setEntity(uefEntity);

				httpclient.execute(httpPost,
						new FutureCallback<HttpResponse>() {
							public void failed(Exception ex) {
							}

							public void completed(HttpResponse resp) {
								try {
									HttpEntity entity = resp.getEntity();
									if (entity != null) {
										entity = resp.getEntity();
										sb.append(EntityUtils.toString(entity,
												"UTF-8"));
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}

							public void cancelled() {
							}
						});
			} else {
				throw new RuntimeException("HTTP请求失败 ,不支持该请求方法.method:"
						+ method);
			}

			long duration = 0L;
			long start = System.currentTimeMillis();

			while ((duration < 5000L) && (sb.length() <= 0)) {
				Thread.sleep(10L);
				duration = System.currentTimeMillis() - start;
			}

			return sb.toString();
		} catch (Exception e) {
			LOGGER.error("HTTP请求失败 ,url:" + url, e);
			throw new RuntimeException("HTTP请求失败 ,url:" + url + "error:"
					+ e.getMessage(), e);
		} finally {
			try {
				if (httpGet != null) {
					httpGet.abort();
				}

				if (httpPost != null) {
					httpPost.abort();
				}
				if (entity != null) {
					EntityUtils.consume(entity);
				}

				httpclient.close();
			} catch (IOException e) {
				LOGGER.error("关闭HTTP连接失败", e);
			}
		}
	}

	private static void setHeader(HttpPost post, Map<String, String> headers) {
		if (headers != null) {
			Object[] headerSet = (Object[]) headers.keySet().toArray();
			for (Object header : headerSet)
				post.setHeader((String) header, (String) headers.get(header));
		}
	}

	private static void setHeader(HttpGet get, Map<String, String> headers) {
		if (headers != null) {
			Object[] headerSet = (Object[]) headers.keySet().toArray();
			for (Object header : headerSet)
				get.setHeader((String) header, (String) headers.get(header));
		}
	}

	public static byte[] downRemoteFile(String url){
		CloseableHttpClient client = HttpClients.createDefault();
		//没有代理
		RequestConfig config =  RequestConfig.custom().build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(config);

		try{
			HttpResponse respone = client.execute(httpGet);
			if(respone.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
				return null;
			}
			return EntityUtils.toByteArray(respone.getEntity());
		}
		catch (Exception ex){
			log.error("下载远程路径文件失败 {}," ,url, ex);
		}
		return null;
	}

}
