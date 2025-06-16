import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = crearConversor();
        if (converter == null) {
            System.out.println("❌ No se pudo iniciar el conversor. Terminando el programa.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n💱💰 Bienvenido al *Conversor de Divisas* 💰💱");
            System.out.println("Seleccione una opción:");
            System.out.println("1 Convertir 🇦🇷 ARS a 🇺🇸 USD");
            System.out.println("2 Convertir 🇺🇸 USD a 🇦🇷 ARS");
            System.out.println("3 Convertir 🇨🇴 COP a 🇺🇸 USD");
            System.out.println("4 Convertir 🇺🇸 USD a 🇨🇴 COP");
            System.out.println("5 Convertir 🇧🇷 BRL a 🇺🇸 USD");
            System.out.println("6 Convertir 🇺🇸 USD a 🇧🇷 BRL");
            System.out.println("7 🚪 Salir");
            System.out.print("👉 Opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 7) {
                System.out.println("👋 ¡Hasta luego!");
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
                    System.out.println("❗ Opción inválida.");
                    continue;
                }
            }

            System.out.print("Ingrese la cantidad: ");
            double cantidad = scanner.nextDouble();

            try {
                double resultado = converter.convert(from, to, cantidad);
                double tasa = converter.getRate(to) / converter.getRate(from);
                System.out.printf("✅ %.2f %s equivalen a %.2f %s (Tasa: %.4f)\n",
                        cantidad, getNombreMoneda(from),
                        resultado, getNombreMoneda(to),
                        tasa);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

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

    private static String getNombreMoneda(String codigo) {
        return switch (codigo.toUpperCase()) {
            case "ARS" -> "Pesos Argentinos (ARS)";
            case "USD" -> "Dólares Estadounidenses (USD)";
            case "COP" -> "Pesos Colombianos (COP)";
            case "BRL" -> "Reales Brasileños (BRL)";
            default -> codigo;
        };
    }
}
