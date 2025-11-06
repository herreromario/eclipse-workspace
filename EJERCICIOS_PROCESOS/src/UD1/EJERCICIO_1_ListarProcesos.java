package UD1;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EJERCICIO_1_ListarProcesos {
    public static void main(String[] args) {
        try {
            // Determinar el comando según el sistema operativo
            String comando = System.getProperty("os.name").toLowerCase().contains("win")
                    ? "tasklist"     // Windows
                    : "ps -e";       // Linux/Mac

            Process proceso = Runtime.getRuntime().exec(comando);
            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream())
            );

            String linea;
            System.out.println("=== Procesos del sistema ===");
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


