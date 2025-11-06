package UD1;

public class EJERCICIO_2_CrearProceso {
	public static void main(String[] args) {
		try {
			String comando = System.getProperty("os.name").toLowerCase().contains("win") ? "notepad.exe" // Windows
					: "gedit"; // Linux
			ProcessBuilder pb = new ProcessBuilder(comando);
			pb.start(); // Lanza el proceso
			System.out.println("Proceso lanzado: " + comando);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
