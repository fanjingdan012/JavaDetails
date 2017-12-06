package util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.sap.sbo.common.ApplicationContextHolder;
import com.sap.sbo.common.exception.SystemException;
import com.sap.sbo.common.log.Logger;
import com.sap.sbo.common.log.LoggerFactory;
import com.sap.sbo.occ.connector.GlobalSettingService;

public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
    private static final String UTF_8 = "utf-8";
    private static final String JSON = "json";
    private static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
    private static final String CONTENT_TYPE_URLENCODED = "application/x-www-form-urlencoded; charset=utf-8";
    private static final String URLENCODED = "urlencoded";

    private static class DefaultTrustManager implements X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // need do nothing
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // need do nothing
        }
    }

    public HttpEntity sendRequest(String url, String method, Map<String, String> header,
            Map<String, Object> parameterMp, String parameterType) throws ParseException, IOException {
        HttpEntity entity = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        try {
            HttpRequestBase httpRequest = getHttpRequest(url, method, header, parameterMp, parameterType);
            if (httpRequest == null) {
                return null;
            }
            httpClient = getHttpClient(httpRequest);
            response = httpClient.execute(httpRequest);
            entity = response.getEntity();
            LOGGER.debug("status:" + response.getStatusLine());
            if (entity != null) {
                LOGGER.debug("contentEncoding:" + entity.getContentEncoding());
            }
        } catch (IOException e) {
            throw new SystemException(e);
        } catch (KeyManagementException e) {
            throw new SystemException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                throw new SystemException(e);
            }
        }

        return entity;
    }

    private HttpRequestBase getHttpRequest(String url, String method, Map<String, String> header,
            Map<String, Object> parameterMp, String parameterType) throws JsonGenerationException,
            JsonMappingException, IOException {
        HttpRequestBase httpRequest = null;
        if ("Get".equals(method)) {
            httpRequest = new HttpGet(url);
        } else if ("Post".equals(method)) {
            httpRequest = new HttpPost(url);
            ((HttpPost) httpRequest).setEntity(setUpEntity(parameterMp, parameterType));
        }
        if (httpRequest == null) {
            return null;
        }

        Iterator<Entry<String, String>> iter = header.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, String> entry = iter.next();
            httpRequest.setHeader(entry.getKey(), entry.getValue());
        }
        httpRequest.setHeader("Content-Encoding", UTF_8);
        if (JSON.equals(parameterType)) {
            httpRequest.setHeader("Content-Type", CONTENT_TYPE_JSON);
        } else if (URLENCODED.equals(parameterType)) {
            httpRequest.setHeader("Content-Type", CONTENT_TYPE_URLENCODED);

        }
        return httpRequest;
    }

    private CloseableHttpClient getHttpClient(HttpRequestBase httpRequest) throws NoSuchAlgorithmException,
            KeyManagementException {
        CloseableHttpClient httpclient;
        if ("https".equals(httpRequest.getURI().getScheme())) {
            SSLContext ctx = null;
            ctx = SSLContext.getInstance("TLS");

            ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(ctx);
            httpclient = HttpClients.custom().setSSLSocketFactory(sf).build();
        } else {
            httpclient = HttpClients.createDefault();

        }
        HttpHost proxy = getProxy();
        if (proxy != null) {
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
            httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
        }
        return httpclient;
    }

    private HttpHost getProxy() {
        HttpHost proxy = null;
        try {
            GlobalSettingService service = ApplicationContextHolder.getBean(GlobalSettingService.class);
            String[] values = service.getGlobalSettingValues(GlobalSettingService.PROXY_HOST,
                    GlobalSettingService.PROXY_PORT);
            if (values[0] != null && !values[0].isEmpty() && values[1] != null && !values[1].isEmpty()) {
                int proxyPort = Integer.parseInt(values[1]);
                if (proxyPort > 0 && proxyPort < 65536) {
                    proxy = new HttpHost(values[0], proxyPort, "http");
                }
            }
        } catch (Exception e) {
            LOGGER.error("Fail to get proxy from SLD Global Settings.", e);
            proxy = null;
        }
        return proxy;
    }

    public static String buildRealUrl(String urlPatten, Map<String, String> urlParameterMp) {
        Iterator iter = urlParameterMp.entrySet().iterator();
        String[] urlPart = urlPatten.split("#");
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < urlPart.length; i++) {
            Entry entry = (Entry) iter.next();
            str.append(urlPart[i] + entry.getValue());
        }
        return str.toString();
    }

    public static String buildEncodedUrl(String urlPatten, Map<String, String> urlParameterMp) {
        String url = urlPatten;
        if (null != urlParameterMp && urlParameterMp.size() > 0) {
            String encodedParams = encodeParameters(urlParameterMp);
            if (-1 == url.indexOf("?")) {
                url += "?" + encodedParams;
            } else {
                url += "&" + encodedParams;
            }
        }
        return url;
    }

    private static String encodeParameters(Map<String, String> urlParameterMp) {
        StringBuffer buf = new StringBuffer();

        for (Entry<String, String> entry : urlParameterMp.entrySet()) {
            buf.append("&");
            try {
                buf.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (java.io.UnsupportedEncodingException e) {
                LOGGER.error("UnsupportedEncodingException when encoding params {}", urlParameterMp.toString());
                throw new SystemException(e);
            }
        }
        return buf.toString().substring(1);
    }

    public static HttpEntity setUpEntity(Map<String, Object> parameterMp, String parameterType)
            throws JsonGenerationException, JsonMappingException, IOException {
        HttpEntity entity = null;
        if ("multipart".equals(parameterType)) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            Iterator<Entry<String, Object>> iter = parameterMp.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, Object> entry = iter.next();
                Object obj = entry.getValue();
                if (obj instanceof File) {
                    FileBody fbin = new FileBody((File) obj);
                    builder.addPart(entry.getKey(), fbin);
                } else {
                    StringBody sbin = new StringBody((String) obj, ContentType.create("text/plain", UTF_8));
                    builder.addPart(entry.getKey(), sbin);
                }
            }
            entity = builder.build();
            return entity;

        } else {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
            String para = "";
            para = mapper.writeValueAsString(parameterMp);
            StringEntity strEntity = new StringEntity(para, UTF_8);
            strEntity.setContentEncoding(UTF_8);
            if (JSON.equals(parameterType)) {
                strEntity.setContentType(CONTENT_TYPE_JSON);
            } else if (URLENCODED.equals(parameterType)) {
                strEntity.setContentType(CONTENT_TYPE_URLENCODED);

            }
            entity = strEntity;
            return entity;
        }
    }
}
