package interfaz;


import java.util.Scanner;

import dao.DaoDepartamento;
import jdbc.ConexionJdbc;
import pojos.Departamento;

public class insertarDepartamento {
	public static void main(String[]args) {
		ConexionJdbc conJdbc = null;
		Departamento d = null;
		DaoDepartamento daoDepartamento = new DaoDepartamento();
		String nombre_d = null;
		Scanner tec = new Scanner(System.in);
		System.out.println("nombre departamento");
		nombre_d = tec.nextLine();
		
		try {
			
			// Conextar a la base de datos 
			conJdbc = new ConexionJdbc("Configuracion/propiedadesInventario.txt");
			conJdbc.conectar();
			
			// Crear el departamento
			d = new Departamento();
			d.setNombre(nombre_d);
			daoDepartamento.grabar(d);
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			conJdbc.desconectar();
		}
	}
}
