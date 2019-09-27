package net;

import java.util.Map;


public class HttpResponseInfo {
    private String status;
    private String bodyStr;
    private Map<String, String> headers;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the bodyStr
     */
    public String getBodyStr() {
        return bodyStr;
    }

    /**
     * @param bodyStr
     *            the bodyStr to set
     */
    public void setBodyStr(String bodyStr) {
        this.bodyStr = bodyStr;
    }

    /**
     * @return the headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * @param headers
     *            the headers to set
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

}