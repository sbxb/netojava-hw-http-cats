import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static final String API_URL =
            "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5_000)
                .setSocketTimeout(20_000)
                .setRedirectsEnabled(false)
                .build();

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("curl/7.81.0")
                .setDefaultRequestConfig(config)
                .build();

        HttpGet request = new HttpGet(API_URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        try {
            CloseableHttpResponse response = httpClient.execute(request);
            //Arrays.stream(request.getAllHeaders()).forEach(System.out::println);
            //System.out.println("");
            //Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
            //System.out.println("");
            String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(body);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
