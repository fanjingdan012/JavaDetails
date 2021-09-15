package net;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;


import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

public class HttpUtil {
    private static final Logger LOGGER = Logger.getLogger(HttpUtil.class);
    private static final String UTF_8 = "utf-8";

    private static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
    private static final String CONTENT_TYPE_URLENCODED = "application/x-www-form-urlencoded; charset=utf-8";
    public static final String PARAMETER_TYPE_JSON = "json";
    public static final String PARAMETER_TYPE_URLENCODED = "urlencoded";
    public static final String PARAMETER_TYPE_MULTIPART = "multipart";
    public static final String USER_AGENT = "Mozilla/5.0";


    /**
     * for those don't have mock server, use this method, it provides default mockserver of false
     *
     * @param url
     * @param method
     * @param header
     * @param parameterMp
     * @param parameterType
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public String sendRequest(String url, String method, Map<String, String> header, Map<String, Object> parameterMp,
                              String parameterType) {
        HttpRequestBase httpRequest = getHttpRequest(url, method, setUpHeader(header, parameterType),
                setUpEntity(parameterMp, parameterType));
        return getTextResponse(httpRequest);
    }

    /**
     * pass httpClient to close it
     *
     * @param httpClient
     * @param url
     * @param method
     * @param header
     * @param parameterMp
     * @param parameterType
     * @return
     */
    private CloseableHttpResponse sendRequestForEntireResponse(CloseableHttpClient httpClient, String url, String method,
                                                               Map<String, String> header, Map<String, Object> parameterMp, String parameterType) {
        HttpRequestBase httpRequest = getHttpRequest(url, method, setUpHeader(header, parameterType),
                setUpEntity(parameterMp, parameterType));
        return getHttpResponse(httpClient, httpRequest);
    }

    /**
     * @param url
     * @param method
     * @param parameterJson
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public String sendJsonRequest(String url, String method, String parameterJson) throws ParseException, IOException {
        HttpRequestBase httpRequest = getHttpRequest(url, method, setUpHeader(null, PARAMETER_TYPE_JSON),
                setUpEntity(parameterJson));
        return getTextResponse(httpRequest);
    }

    /**
     * download file from url and create file on server
     * @param remotefileUrl
     * @param localFilePath(if not set,will create temp file)
     * @param fileName(if temp file,only filePrefix needed)
     * @param filePrefix
     * @param acceptabelFileType
     * @return
     */
    public File downLoadFile(String remotefileUrl, String localFilePath, String fileName, String filePrefix,
                             List<String> acceptabelFileType) throws IOException {

        HttpRequestBase httpRequest = getHttpRequest(remotefileUrl, "Get",
                setUpHeader(new HashMap<String, String>(), null), setUpEntity(new HashMap<String, Object>(), null));
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        File file = null;
        try {
            response = getHttpResponse(httpClient, httpRequest);
            HttpEntity entity = getResponseEntity(response);
            file = createLocalFile(localFilePath, fileName, filePrefix, acceptabelFileType, entity);
        } finally {
            closeConnect(response, httpClient);
        }

        return file;
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
        LOGGER.info("HttpUtil buildEncodedUrl:" + url);
        return url;
    }

    private String getResponseEncoding(HttpEntity entity) {
        Header header = null;
        if (entity.getContentEncoding() != null) {
            header = entity.getContentEncoding();
        } else if (entity.getContentType() != null) {
            header = entity.getContentType();
        }
        if (header != null && header.getValue() != null && header.getValue().contains("encoding=")) {
            String headerValue = header.getValue();
            if (headerValue.indexOf("encoding=") != -1) {
                String encoding = headerValue.substring(headerValue.indexOf("encoding=") + 9, headerValue.length());
                LOGGER.info("getResponseEncoding is " + encoding);
                return encoding;
            }

        }
        return null;
    }

    public String getTextResponse(HttpRequestBase httpRequest) {
        HttpEntity entity = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;

        try {

            response = getHttpResponse(httpClient, httpRequest);
            entity = getResponseEntity(response);
            String encoding = getResponseEncoding(entity);
            if (encoding == null) {
                encoding = UTF_8;
            }
            String result = EntityUtils.toString(entity, encoding);
            LOGGER.info("HttpUtil getTextResponse result:" + result);
            return result;
        } catch (ParseException e) {
            LOGGER.warn("getTextResponse ParseException", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGGER.warn("getTextResponse IOException", e);
            throw new RuntimeException(e);
        } finally {
            closeConnect(response, httpClient);
        }

    }

    public static void closeConnect(CloseableHttpResponse response, CloseableHttpClient httpClient) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            LOGGER.info("closeConnect fail to close response {}", e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    LOGGER.error("closeConnect fail to close httpclient {}", e);
                }
            }
        }
    }

    /*
     * private HttpEntity getHttpResponseEntity(CloseableHttpClient httpClient,
     * CloseableHttpResponse response, HttpRequestBase httpRequest) {
     * try {
     * if (httpRequest == null) {
     * return null;
     * }
     * httpClient = getHttpClient(httpRequest);
     * response = httpClient.execute(httpRequest);
     * String status = String.valueOf(response.getStatusLine()
     * .getStatusCode());
     * if (!status.startsWith("2")) {
     * LOGGER.warn("server return with status:" + status);
     * throw new ServerException(status);
     * }
     * HttpEntity entity = response.getEntity();
     * LOGGER.debug("status:" + response.getStatusLine());
     * if (entity != null) {
     * LOGGER.debug("contentEncoding:" + entity.getContentEncoding());
     * }
     * return entity;
     * } catch (IOException e) {
     * throw new SystemException(e);
     * } catch (KeyManagementException e) {
     * throw new SystemException(e);
     * } catch (NoSuchAlgorithmException e) {
     * throw new SystemException(e);
     * }
     * }
     */
    private CloseableHttpResponse getHttpResponse(CloseableHttpClient httpClient, HttpRequestBase httpRequest) {
        try {
            if (httpRequest == null) {
                LOGGER.debug("getHttpResponse: httpRequest is null");
                return null;
            }
            httpClient = getHttpClient(httpRequest);
            CloseableHttpResponse response = httpClient.execute(httpRequest);
            return response;
        } catch (IOException e) {
            LOGGER.warn("getHttpResponse execute IOException" + e);
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            LOGGER.warn("getHttpResponse getHttpClient KeyManagementException" + e);
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.warn("getHttpResponse getHttpClient NoSuchAlgorithmException" + e);
            throw new RuntimeException(e);
        }
    }

    private HttpEntity getResponseEntity(CloseableHttpResponse response) {
        String status = String.valueOf(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity();
        if (!status.startsWith("2")) {
            LOGGER.warn("server return with status:{}" + status);
            try {
                String description = EntityUtils.toString(entity);
                LOGGER.warn("server return with error status and description:{}" + description);
                System.out.println("server return with error status and description:{}" + description + status);
                throw new RuntimeException(description);
            } catch (ParseException e) {
                LOGGER.warn("parsing error description error", e);
                throw new RuntimeException(status);
            } catch (IOException e) {
                LOGGER.warn("parsing error description error", e);
                throw new RuntimeException(status);
            }

        }

        LOGGER.debug("status:" + response.getStatusLine());
        if (entity != null) {
            LOGGER.debug("contentEncoding:" + entity.getContentEncoding());
        }
        return entity;
    }

    private File createLocalFile(String localFilePath, String fileName, String filePrefix,
                                 List<String> acceptabelFileType, HttpEntity entity) {
        String contentType = entity.getContentType().toString();
        String fileType = contentType.substring(contentType.lastIndexOf("/") + 1);
        if (null != acceptabelFileType && !acceptabelFileType.contains(fileType)) {
            LOGGER.warn("Current fileType：" + fileType + " file type not allowed");
            throw new RuntimeException("File_Type_Not_Supported") {
            };
        }
        File file = null;
        InputStream is = null;
        FileOutputStream out = null;
        // create temp file
        try {
            if (StringUtils.isEmpty(localFilePath)) {
                file = File.createTempFile(filePrefix, UUID.randomUUID().toString() + "." + fileType);
            } else {
                // create according to localFilePath
            }

            is = entity.getContent();
            out = new FileOutputStream(file);
            byte[] buf = new byte[4096];
            int n;
            while ((n = is.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        } catch (IOException e) {
            LOGGER.warn("Create File Error with IO Exception!");
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return file;
    }

    private HttpRequestBase getHttpRequest(String url, String method, Map<String, String> header,
                                           HttpEntity parameterEntity) {
        HttpRequestBase httpRequest = null;
        try {
            if ("Get".equals(method)) {
                httpRequest = new HttpGet(url);
            } else if ("Post".equals(method)) {
                httpRequest = new HttpPost(url);
                ((HttpPost) httpRequest).setEntity(parameterEntity);
            } else if ("Put".equals(method)) {
                httpRequest = new HttpPut(url);
                ((HttpPut) httpRequest).setEntity(parameterEntity);
            } else if ("Patch".equals(method)) {
                httpRequest = new HttpPatch(url);
                ((HttpPatch) httpRequest).setEntity(parameterEntity);
            } else if ("Delete".equals(method)) {
                httpRequest = new HttpDelete(url);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("getHttpRequest IllegalArgumentException, url:{}, {}" + url + e);
            throw new RuntimeException("SocialCommonErrorCode.INVALID_URL" + url);
        }
        if (httpRequest == null) {
            LOGGER.error("getHttpRequest httpRequest is null, method:{}, url:{}" + method + url);
            throw new RuntimeException("SocialCommonErrorCode.INVALID_METHOD" + method);
        }
        if (header != null) {
            Iterator<Entry<String, String>> iter = header.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, String> entry = iter.next();
                httpRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
        return httpRequest;
    }

    private Map<String, String> setUpHeader(Map<String, String> originalHeader, String parameterType) {
        Map<String, String> header = new HashMap<>();
        if (originalHeader != null) {
            header.putAll(originalHeader);
        }
        header.put("Content-Encoding", UTF_8);
        if (PARAMETER_TYPE_JSON.equals(parameterType)) {
            header.put("Content-Type", CONTENT_TYPE_JSON);
        } else if (PARAMETER_TYPE_URLENCODED.equals(parameterType)) {
            header.put("Content-Type", CONTENT_TYPE_URLENCODED);

        }
        return header;
    }

    private CloseableHttpClient getHttpClient(HttpRequestBase httpRequest)
            throws NoSuchAlgorithmException, KeyManagementException {
        CloseableHttpClient httpclient;
        if ("https".equals(httpRequest.getURI().getScheme())) {
            SSLContext ctx = null;
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, null, null);
            // ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());

            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(ctx);
            httpclient = HttpClients.custom().setSSLSocketFactory(sf).build();
        } else {
            httpclient = HttpClients.createDefault();

        }
//
//        HttpHost proxy = getProxy();
//        if (proxy != null) {
//            LOGGER.info("getHttpClient: use proxy");
//            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
//            httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
//        }

        return httpclient;
    }

    private HttpHost getProxy() {

        HttpHost proxy = null;

        try {
            String[] values = {"proxyHost",  "proxyPort" };
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

    private static String encodeParameters(Map<String, String> urlParameterMp) {
        StringBuffer buf = new StringBuffer();
        if (urlParameterMp == null || urlParameterMp.isEmpty()) {
            return "";
        } else {
            for (Map.Entry<String, String> entry : urlParameterMp.entrySet()) {
                buf.append("&");
                try {
                    buf.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=")
                            .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                } catch (java.io.UnsupportedEncodingException e) {
                    LOGGER.error("UnsupportedEncodingException when encoding params {}" + urlParameterMp.toString());
                    throw new RuntimeException(e);
                }
            }

            return buf.toString().substring(1);
        }

    }

    static HttpEntity setUpEntity(Map<String, Object> parameterMp, String parameterType) {
        HttpEntity entity = null;
        if (PARAMETER_TYPE_MULTIPART.equals(parameterType)) {
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
                    LOGGER.info("multipart payload text part:{}"+ sbin);
                    builder.addPart(entry.getKey(), sbin);
                }
            }
            entity = builder.build();

        } else

        if (PARAMETER_TYPE_JSON.equals(parameterType)) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectMapperUtil.initObjectMapper(mapper);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            String para = "";
            try {
                para = mapper.writeValueAsString(parameterMp);
                LOGGER.info("setUpEntity payload:" + para);
            } catch (JsonGenerationException e) {
                LOGGER.warn("setUpRequestEntity JsonGenerationException", e);
                throw new RuntimeException(e);
            } catch (JsonMappingException e) {
                LOGGER.warn("setUpRequestEntity JsonMappingException", e);
                throw new RuntimeException(e);
            } catch (IOException e) {
                LOGGER.warn("setUpRequestEntity IOException", e);
                throw new RuntimeException(e);
            }
            StringEntity strEntity = new StringEntity(para, UTF_8);
            strEntity.setContentEncoding(UTF_8);
            strEntity.setContentType(CONTENT_TYPE_JSON);
            entity = strEntity;
        } else if (PARAMETER_TYPE_URLENCODED.equals(parameterType)) {

            // List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            Map<String, String> parameters = new HashMap<>();
            if (parameterMp != null) {
                for (Entry<String, Object> parameter : parameterMp.entrySet()) {
                    // parameters.add(new BasicNameValuePair(parameter.getKey(), (String) parameter.getValue()));
                    parameters.put(parameter.getKey(), (String) parameter.getValue());
                }
            }
            String content = encodeParameters(parameters);
            LOGGER.info("url encoded payload :{}" + content);
            StringEntity strEntity = new StringEntity(content, UTF_8);
            strEntity.setContentEncoding(UTF_8);
            strEntity.setContentType(CONTENT_TYPE_URLENCODED);
            entity = strEntity;
            // UrlEncodedFormEntity urlEntity;
            // try {
            // urlEntity = new UrlEncodedFormEntity(parameters, UTF_8);
            // } catch (UnsupportedEncodingException e) {
            // LOGGER.error("setUpEntity: cannot setup entity,UnsupportedEncodingException UTF-8 {}", e);
            // throw new SystemException(e);
            // }
            // urlEntity.setContentType(CONTENT_TYPE_URLENCODED);
            // entity = urlEntity;
        }

        return entity;
    }

    static HttpEntity setUpEntity(String parameterJson)
            throws JsonGenerationException, JsonMappingException, IOException {
        LOGGER.info("setUpEntity payload:" + parameterJson);
        HttpEntity entity = null;
        StringEntity strEntity = new StringEntity(parameterJson, UTF_8);
        strEntity.setContentEncoding(UTF_8);
        strEntity.setContentType(CONTENT_TYPE_JSON);
        entity = strEntity;
        return entity;

    }

    /**
     * public for test, don't use it except Test and HttpCommonUtil
     *
     * @param url
     * @param method
     * @param header
     * @param reqEntity
     * @param parameterType
     * @return
     */
    public HttpResponseInfo sendRequestForResponseInfo(String url, String method, Map<String, String> header,
                                                       HttpEntity reqEntity, String parameterType) {
        LOGGER.info("sendRequestForResponseInfo: {} {}, {}" + method + url + parameterType);
        HttpRequestBase httpRequest = getHttpRequest(url, method, setUpHeader(header, parameterType), reqEntity);

        return getInfoForTextResponse(httpRequest);

    }

    private HttpResponseInfo getInfoForTextResponse(HttpRequestBase httpRequest) {
        HttpResponseInfo hri = new HttpResponseInfo();
        HttpEntity entity = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;

        try {

            response = getHttpResponse(httpClient, httpRequest);
            entity = getResponseEntity(response);
            String encoding = getResponseEncoding(entity);
            if (encoding == null) {
                encoding = UTF_8;
            }
            String result = EntityUtils.toString(entity, encoding);
            LOGGER.info("HttpUtil getResponseInfo result:{}" + result);
            hri.setBodyStr(result);

            String status = String.valueOf(response.getStatusLine().getStatusCode());
            hri.setStatus(status);
            Header[] headers = response.getAllHeaders();
            Map<String, String> headerMp = new HashMap<>();
            for (Header resHeader : headers) {
                String name = resHeader.getName();
                String value = resHeader.getValue();
                headerMp.put(name, value);
            }
            hri.setHeaders(headerMp);

            return hri;
        } catch (ParseException e) {
            LOGGER.warn("getResponseInfo ParseException", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGGER.warn("getResponseInfo IOException", e);
            throw new RuntimeException(e);
        } finally {
            closeConnect(response, httpClient);
        }

    }





}