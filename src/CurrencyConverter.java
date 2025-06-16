import java.util.Map;

public class CurrencyConverter {
    private final Map<String, Double> rates;

    public CurrencyConverter(Map<String, Double> rates) {
        this.rates = rates;
    }

    public double convert(String from, String to, double amount) {
        double rateFrom = getRate(from);
        double rateTo = getRate(to);
        return amount / rateFrom * rateTo;
    }

    public double getRate(String code) {
        if (code.equalsIgnoreCase("USD")) {
            return 1.0;
        }
        Double rate = rates.get(code.toUpperCase());
        if (rate == null) {
            throw new IllegalArgumentException("Moneda no soportada: " + code);
        }
        return rate;
    }

}
