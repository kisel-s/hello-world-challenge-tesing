package framework.rest;

import framework.Logger;
import framework.PropertiesResourceManager;
import framework.exception.RequestExecutionException;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.Arrays;

public class RestApiService {

    private static Logger logger = Logger.getInstance();
    private static String url;
    private static RestClient client;

    private static String formatUrl(String part) {
        return new PropertiesResourceManager("configuration").getSystemProperty("startUrl") + part;
    }

    public static RestClientResponse runPostRequest(String postUrlPart, String body) {
        url = formatUrl(postUrlPart);
        client = new RestClient();
        HttpPostClient httpPost = new HttpPostClient(url);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        httpPost.addJson(body);
        logger.info(String.format("Run Post request (url: '%s', body: %s)", url, body));
        Arrays.stream(httpPost.getHttpPost().getAllHeaders()).forEach(logger::info);
        RestClientResponse response;
        try {
            response = client.runPostRequest(httpPost.getHttpPost());
        } catch (IOException e) {
            logger.error(e.toString(), e);
            throw new RequestExecutionException(e);
        }
        return response;
    }

    public static RestClientResponse runGetRequest(String urlPart, Header... headers) {
        url = formatUrl(urlPart);
        client = new RestClient();
        HttpGetClient httpGet = new HttpGetClient(url);
        for (Header header : headers) {
            httpGet.setHeader(header.getName(), header.getValue());
        }
        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        logger.info(String.format("Run Get request (url: '%s'", url));
        Arrays.stream(httpGet.getHttpGet().getAllHeaders()).forEach(logger::info);
        RestClientResponse response;
        response = client.runGetRequest(httpGet.getHttpGet());
        return response;
    }

}
