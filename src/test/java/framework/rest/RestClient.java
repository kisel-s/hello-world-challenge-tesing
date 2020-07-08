package framework.rest;

import framework.Logger;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RestClient {

    private static final String CHARSET_NAME = "UTF-8";
    protected static Logger logger = Logger.getInstance();
    private CloseableHttpClient client;
    private CookieStore httpCookieStore;


    public RestClient() {
        client = prepareClientBuilderWithDefaultSSL().build();
    }

    private HttpClientBuilder prepareClientBuilderWithDefaultSSL() {
        SSLContext sslcontext;
        HttpClientBuilder clientBuilder = null;
        try {
            sslcontext = SSLContexts.custom().useSSL().build();
            sslcontext.init(null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
            clientBuilder = prepareClientBuilder(sslcontext);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            logger.error(e.toString(), e);
        }
        return clientBuilder;
    }

    private HttpClientBuilder prepareClientBuilder(SSLContext sslcontext) {
        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        return HttpClients.custom()
                .setSSLSocketFactory(factory)
                .setDefaultCookieStore(httpCookieStore);
    }


    /**
     * Function to show cookies that are currently stored in http client
     *
     * @return currently stored cookies
     */
    public RestClientResponse runGetRequest(HttpGet get) {
        try {
            HttpResponse response = client.execute(get);
            StringBuilder result = getParsedResult(response);
            return new RestClientResponse(get.getURI().toString(),
                    result.toString(), response.getStatusLine().getStatusCode(), response.getAllHeaders());
        } catch (IOException ex) {
            logger.debug("Cookies currently stored");
        }
        return new RestClientResponse();
    }

    /**
     * Run POST request
     *
     * @param post - HttpPost request that you want to run
     * @return SsoResponse string
     * @throws IOException
     */
    public RestClientResponse runPostRequest(HttpPost post) throws IOException {
        HttpResponse response = client.execute(post);
        StringBuilder result = getParsedResult(response);
        return new RestClientResponse(post.getURI().toString(),
                result.toString(), response.getStatusLine().getStatusCode(), response.getAllHeaders());
    }

    public RestClientResponse runPutRequest(HttpPut put) throws IOException {
        HttpResponse response = client.execute(put);
        StringBuilder result = getParsedResult(response);
        return new RestClientResponse(put.getURI().toString(),
                result.toString(), response.getStatusLine().getStatusCode(), response.getAllHeaders());
    }

    private StringBuilder getParsedResult(HttpResponse response) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), CHARSET_NAME))) {
            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            Header[] headers = response.getAllHeaders();
            logger.info("API response : ");
            for (Header respHeader : headers) {
                logger.info(respHeader.getName() + ": " + respHeader.getValue());
            }
            logger.info("Status Code : " + response.getStatusLine().getStatusCode());
            logger.info("Content : " + result);
            return result;
        }
    }

    /**
     * Close connection
     */
    public void close() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
