package conversor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class ServicioTipoCambio {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/bde46699a762363a0d9a53f6/latest/";

    public double obtenerTasa(String monedaOrigen, String monedaDestino) throws Exception {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + monedaOrigen))
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        JsonObject json = new Gson().fromJson(respuesta.body(), JsonObject.class);
        JsonObject tasas = json.getAsJsonObject("conversion_rates");

        return tasas.get(monedaDestino).getAsDouble();
    }
}