package framework.rest;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.net.URI;

public class HttpPostClient {

    private HttpPost httpPost;

    public HttpPostClient() {
        this.httpPost = new HttpPost();
    }

    public HttpPostClient(String url) {
        this.httpPost = new HttpPost(url);
    }

    public HttpPost getHttpPost() {
        return this.httpPost;
    }

    public void setHttpPostURL(String uri) {
        this.httpPost.setURI(URI.create(uri));
    }

    /**
     * Add header name and value
     *
     * @param name
     * @param value
     * @return
     */
    public HttpPostClient setHeader(String name, ContentType value) {
        this.httpPost.setHeader(name, String.valueOf(value));
        return this;
    }

    /**
     * Add JSON to POST request body
     *
     * @param json
     */
    public void addJson(String json) {
        StringEntity entity = null;
        try {
            entity = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.httpPost.setEntity(entity);
    }
}
