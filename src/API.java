import java.util.Map;

public record API(String base_code, Map<String, Double> conversion_rates) {
    @Override
    public String toString() {
        return  "Código da Moeda: " + base_code +
                "\nConversões de Valores: " + conversion_rates;
    }

    public Map<String, Double> conversionRates() {
        return conversion_rates;
    }
}