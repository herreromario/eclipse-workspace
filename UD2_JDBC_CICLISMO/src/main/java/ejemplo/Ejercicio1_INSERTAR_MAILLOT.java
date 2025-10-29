package ejemplo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class Ejercicio1_INSERTAR_MAILLOT {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement pstmt = null; // Es la clase que inyecta SQL a la BD
		String sql = null;
		String vcodigo, vtipo, vcolor = null;
		Integer vpremio = null;
		
		// Leer valores interfaz
		Scanner sc = new Scanner(System.in);
		
		System.out.println("CÓDIGO");
		vcodigo = sc.nextLine();
		
		System.out.println("TIPO");
		vtipo = sc.nextLine();
		
		System.out.println("COLOR");
		vcolor = sc.nextLine();
		
		System.out.println("PREMIO");
		vpremio = sc.nextInt();
		sc.nextLine();
		
	
	
		// Parte de datos (DAO)
		
		try {
			Properties propiedades = new Properties();
			propiedades.load
			(new FileInputStream("configuracion\\propCiclismo.txt"));

			// Creamos la conexión de DataSource con el fichero propCiclismo.txt
			
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			
			con = ds.getConnection();
			
			// Operación insertar
			
			sql = "insert into maillot values(?,?,?,?)";
			
			// Inyectamos la SQL en el preparedStatement
			
			pstmt = con.prepareStatement(sql);
			
			// Asignamos valor a los ? (empieza por el 1)
			
			pstmt.setString(1, vcodigo);
			pstmt.setString(2, vtipo);
			pstmt.setString(3, vcolor);
			pstmt.setInt(4, vpremio);
			
			// Ejecutamos el insert
			
			Integer num = pstmt.executeUpdate();
			
			// Salida por pantalla del número de filas actualizadas
			
			System.out.println("FILAS ACTUALIZADAS: " + num);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
