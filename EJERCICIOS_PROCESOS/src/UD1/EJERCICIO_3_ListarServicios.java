package UD1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EJERCICIO_3_ListarServicios {
	public static void main(String[] args) {
		try {
			String comando = System.getProperty("os.name").toLowerCase().contains("win") ? "sc query" // Windows
					: "systemctl --type=service --state=running"; // Linux
			Process proceso = Runtime.getRuntime().exec(comando);
			BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			String linea;
			System.out.println("=== Servicios activos ===");
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}