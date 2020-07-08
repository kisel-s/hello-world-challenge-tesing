package framework.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.Header;

public class RestClientResponse {

    private String body;
    private Header[] headers;
    private int statusCode;
    private String requestUri;

    public RestClientResponse() {
    }

    public RestClientResponse(String requestUri, String body, int statusCode, Header[] headers) {
        this.body = body;
        this.requestUri = requestUri;
        this.statusCode = statusCode;
        this.headers = headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public JsonObject getBodyAsJson() {
        return new Gson().fromJson(this.body, JsonObject.class);
    }

}
