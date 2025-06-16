import java.util.Scanner;

public class MenuUI {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n💱💰 Bienvenido al *Conversor de Divisas* 💰💱");
        System.out.println("Seleccione una opción:");
        System.out.println("1 Convertir 🇦🇷 ARS a 🇺🇸 USD");
        System.out.println("2 Convertir 🇺🇸 USD a 🇦🇷 ARS");
        System.out.println("3 Convertir 🇨🇴 COP a 🇺🇸 USD");
        System.out.println("4 Convertir 🇺🇸 USD a 🇨🇴 COP");
        System.out.println("5 Convertir 🇧🇷 BRL a 🇺🇸 USD");
        System.out.println("6 Convertir 🇺🇸 USD a 🇧🇷 BRL");
        System.out.println("7 🚪 Salir");
    }

    public int obtenerOpcion() {
        System.out.print("👉 Opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❗ Entrada no válida. Por favor ingrese un número.");
            return -1; // Retornamos un valor inválido para que el bucle continúe
        }
    }

    public double obtenerCantidad() {
        while (true) {
            System.out.print("Ingrese la cantidad: ");
            try {
                double cantidad = Double.parseDouble(scanner.nextLine());
                if (cantidad < 0) {
                    System.out.println("❗ La cantidad no puede ser negativa.");
                    continue;
                }
                return cantidad;
            } catch (NumberFormatException e) {
                System.out.println("❗ Entrada no válida. Debe ingresar un número válido.");
            }
        }
    }

    public void mostrarResultado(double cantidad, String from, double resultado, String to, double tasa) {
        System.out.printf("✅ %.2f %s equivalen a %.2f %s (Tasa: %.4f)\n",
                cantidad, getNombreMoneda(from),
                resultado, getNombreMoneda(to),
                tasa);
    }

    public void mostrarError(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarDespedida() {
        System.out.println("👋 ¡Hasta luego!");
    }

    private String getNombreMoneda(String codigo) {
        return switch (codigo.toUpperCase()) {
            case "ARS" -> "Pesos Argentinos (ARS)";
            case "USD" -> "Dólares Estadounidenses (USD)";
            case "COP" -> "Pesos Colombianos (COP)";
            case "BRL" -> "Reales Brasileños (BRL)";
            default -> codigo;
        };
    }
}