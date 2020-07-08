package framework.rest;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;

public class HttpGetClient {

    private HttpGet httpGet;

    public HttpGetClient(String url) {
        this.httpGet = new HttpGet(url);
    }

    public HttpGet getHttpGet() {
        return this.httpGet;
    }

    public HttpGetClient setHeader(String name, ContentType value) {
        this.httpGet.setHeader(name, String.valueOf(value));
        return this;
    }

    public HttpGetClient setHeader(String name, String value) {
        this.httpGet.setHeader(name, value);
        return this;
    }
}
