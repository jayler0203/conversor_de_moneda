import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {

    private final HttpClient client;
    String url_str = "https://v6.exchangerate-api.com/v6/4c2fae1a3bb2eec43ef29fbc/latest/USD";

    public ApiService() {
        this.client = HttpClient.newHttpClient();
    }

    public ExchangeResponse getData() throws Exception {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url_str)).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), ExchangeResponse.class);
        } else {
            throw new RuntimeException("Error en la solicitud: " + response.statusCode());
        }
    }
}
