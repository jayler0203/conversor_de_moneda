public class Main {
    public static void main(String[] args) {
        ApiService apiService = new ApiService();
        try {
            ExchangeResponse data = apiService.getData();
            System.out.println(data);
        } catch (Exception e) {
            System.out.println("Error al obtener los datos" + e.getMessage());
        }

    }

}