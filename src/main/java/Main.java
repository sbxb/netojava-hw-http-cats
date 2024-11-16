import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

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

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("curl/7.81.0")
                .setDefaultRequestConfig(config)
                .build()) {

            HttpGet request = new HttpGet(API_URL);
            request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                List<CatFact> facts = mapper.readValue(response.getEntity().getContent(),
                        new TypeReference<List<CatFact>>() {
                        });
                facts.stream().filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0)
                        .forEach(System.out::println);
            }
        } catch (IOException e) {
            System.out.println("ERROR cannot parse info from remote server: " + e.getMessage());
        }
    }
}
