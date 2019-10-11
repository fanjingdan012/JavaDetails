package net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Http2Util {
    private static HttpClient client = HttpClient.newHttpClient();
    public static String sendRequest(String url, String method, Map<String, String> header, String body)  {
        HttpResponse.BodyHandler<String> stringBodyHandler = HttpResponse.BodyHandlers.ofString();


        try{
            HttpRequest request = getHttpRequest(url, method, body);
            HttpResponse<String> response = client.send(request, stringBodyHandler);
            String temp = response.body();
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static CompletableFuture<HttpResponse<String>> sendRequestAsync(String url, String method, Map<String, String> header, String body)  {
        HttpResponse.BodyHandler<String> stringBodyHandler = HttpResponse.BodyHandlers.ofString();
        HttpClient client = HttpClient.newHttpClient();

        try{
            HttpRequest request = getHttpRequest(url, method, body);
            CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, stringBodyHandler);
            return response;
        }  catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    private static HttpRequest getHttpRequest(String url, String method, String body) throws URISyntaxException {
        HttpRequest request = null;
        switch(method){
            case "Post":
                request = HttpRequest.newBuilder().uri(new URI(url))
                        .POST(HttpRequest.BodyPublishers.ofString(body)).build();
                break;
            case "Get":
                request = HttpRequest.newBuilder().uri(new URI(url))
                        .GET().build();
                break;
            case "Put":
                request = HttpRequest.newBuilder().uri(new URI(url))
                        .PUT(HttpRequest.BodyPublishers.ofString(body)).build();
        }
        return request;
    }


    public static String request(String u, String method, String data) throws MalformedURLException, IOException {
        URL url = new URL(u);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", " application/json");
        if (data != null) {
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Length", data.getBytes().length + "");
            connection.connect();
            OutputStream os = connection.getOutputStream();
            os.write(data.getBytes());
            os.flush();
        } else {
            connection.connect();
        }
        InputStream is = connection.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;
        try {
            while ((len = is.read(b)) > 0) {
                baos.write(b, 0, len);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(baos.toByteArray());
    }
}
