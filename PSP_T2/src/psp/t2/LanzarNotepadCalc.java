package psp.t2;

public class LanzarNotepadCalc {
	public static void main(String[] args) {
		try {
			System.out.println("Lanzando Notepad...");
			Process p1 = Runtime.getRuntime().exec("notepad.exe");
			Thread.sleep(2000);
			
			System.out.println("Lanzando Calculadora...");
			Process p2 = Runtime.getRuntime().exec("calc.exe");
			Thread.sleep(2000);
			
			System.out.println("Cerrando Notepad...");
			p1.destroy(); // o p1.destroyForcibly();
			
			System.out.println("Cerrando Calculadora...");
			p2.destroy();
			
			System.out.println("Terminado.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
