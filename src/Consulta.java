import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Consulta {

    public API consultaValor(String codigoMoeda) throws RuntimeException {
        URI valorConsultado =
                URI.create("https://v6.exchangerate-api.com/v6/4f9855ef3a18291a2d67e63b/latest/" + codigoMoeda);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(valorConsultado)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return parseExchangeRate(response.body());
        } catch (Exception e) {
            throw new RuntimeException("Moeda inválida!");
        }
    }

    private API parseExchangeRate(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        String baseCode = jsonObject.get("base_code").getAsString();
        Map<String, Double> filteredRates = filterRates(jsonObject.getAsJsonObject("conversion_rates"));
        return new API(baseCode, filteredRates);
    }

    private Map<String, Double> filterRates(JsonObject conversionRatesObject) {
        Map<String, Double> filteredRates = new HashMap<>();
        for (String currency : new String[]{"USD", "EUR", "GBP", "BRL", "JPY", "CHF"}) {
            if (conversionRatesObject.has(currency)) {
                filteredRates.put(currency, conversionRatesObject.get(currency).getAsDouble());
            }
        }
        return filteredRates;
    }
}