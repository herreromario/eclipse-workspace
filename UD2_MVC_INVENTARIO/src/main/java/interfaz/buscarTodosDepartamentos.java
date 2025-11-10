package interfaz;

import java.util.ArrayList;
import java.util.List;

import dao.DaoDepartamento;
import jdbc.ConexionJdbc;
import pojos.Departamento;

public class buscarTodosDepartamentos {

	public static void main(String[] args) {
		ConexionJdbc conJdbc = null;
		DaoDepartamento daoDepartamento = new DaoDepartamento();
		List<Departamento> ldepartamentos = new ArrayList<Departamento>();
		
		
		try {	
			// Conextar a la base de datos 
			conJdbc = new ConexionJdbc("Configuracion/propiedadesInventario.txt");
			conJdbc.conectar();
			ldepartamentos=daoDepartamento.buscarTodos();
			for(Departamento d:ldepartamentos) {
				System.out.println(d.getIdDepartamento()
						+ " " + d.getNombre());
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			conJdbc.desconectar();
		}
	}
}
