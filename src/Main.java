import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class Main {
    private static CurrencyConverter crearConversor() {
        ApiService service = new ApiService();
        try {
            ExchangeResponse response = service.getData();
            ConversionRates ratesObj = response.conversion_rates();

            Gson gson = new Gson();
            String json = gson.toJson(ratesObj);
            Type type = new TypeToken<Map<String, Double>>() {}.getType();
            Map<String, Double> ratesMap = gson.fromJson(json, type);

            return new CurrencyConverter(ratesMap);
        } catch (Exception e) {
            System.out.println("⚠️ Error al obtener los datos de la API: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        CurrencyConverter converter = crearConversor();
        if (converter == null) {
            System.out.println("❌ No se pudo iniciar el conversor. Terminando el programa.");
            return;
        }

        // Creamos una instancia de nuestra nueva clase de UI
        MenuUI ui = new MenuUI();

        while (true) {
            ui.mostrarMenu();
            int opcion = ui.obtenerOpcion();

            if (opcion == 7) {
                ui.mostrarDespedida();
                break;
            }

            String from, to;
            switch (opcion) {
                case 1 -> { from = "ARS"; to = "USD"; }
                case 2 -> { from = "USD"; to = "ARS"; }
                case 3 -> { from = "COP"; to = "USD"; }
                case 4 -> { from = "USD"; to = "COP"; }
                case 5 -> { from = "BRL"; to = "USD"; }
                case 6 -> { from = "USD"; to = "BRL"; }
                default -> {
                    ui.mostrarError("❗ Opción inválida.");
                    continue;
                }
            }

            double cantidad = ui.obtenerCantidad();

            try {
                double resultado = converter.convert(from, to, cantidad);
                double tasa = converter.getRate(to) / converter.getRate(from);
                ui.mostrarResultado(cantidad, from, resultado, to, tasa);
            } catch (IllegalArgumentException e) {
                ui.mostrarError("❌ Error: " + e.getMessage());
            }
        }
    }
}