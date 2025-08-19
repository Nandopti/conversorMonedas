package conversor;

import java.util.Scanner;

public class ConversorMonedaApp {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ServicioTipoCambio servicioTipoCambio = new ServicioTipoCambio();
        ConversorMoneda conversorMoneda = new ConversorMoneda();

        while (true) {
            System.out.println("""
            ************************************
            Bienvenido al Conversor de Monedas
            Seleccione la opción deseada:
            
            1) Dólar (USD)          => Peso argentino (ARS)
            2) Peso argentino (ARS) => Dólar (USD)
            3) Dólar (USD)          => Real brasileño (BRL)
            4) Real brasileño (BRL) => Dólar (USD)
            5) Dólar (USD)          => Peso colombiano (COP)
            6) Peso colombiano (COP)=> Dólar (USD)
            7) Salir
            ************************************
            """);

            System.out.print("Ingrese el número de la opción: ");
            int opcion = entrada.nextInt();

            if (opcion == 7) {
                System.out.println("\nGracias por usar el conversor de monedas. Hasta pronto.");
                break;
            }

            String monedaOrigen;
            String monedaDestino;

            // Selección de monedas según la opción
            switch (opcion) {
                case 1 -> { monedaOrigen = "USD"; monedaDestino = "ARS"; }
                case 2 -> { monedaOrigen = "ARS"; monedaDestino = "USD"; }
                case 3 -> { monedaOrigen = "USD"; monedaDestino = "BRL"; }
                case 4 -> { monedaOrigen = "BRL"; monedaDestino = "USD"; }
                case 5 -> { monedaOrigen = "USD"; monedaDestino = "COP"; }
                case 6 -> { monedaOrigen = "COP"; monedaDestino = "USD"; }
                default -> {
                    System.out.println("Opción no válida. Por favor, elija de nuevo.\n");
                    continue;
                }
            }

            try {
                System.out.print("Ingrese el monto a convertir: ");
                double monto = entrada.nextDouble();

                double tasaCambio = servicioTipoCambio.obtenerTasa(monedaOrigen, monedaDestino);
                double montoConvertido = conversorMoneda.convertir(monto, tasaCambio);

                System.out.printf("%.2f %s equivalen a %.2f %s%n%n",
                        monto, monedaOrigen, montoConvertido, monedaDestino);

            } catch (Exception e) {
                System.out.println("Error durante la conversión: " + e.getMessage());
            }
        }
    }
}