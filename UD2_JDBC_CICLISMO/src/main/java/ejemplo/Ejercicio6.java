package ejemplo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class Ejercicio6 {
	
	public static void main(String[] args) {
		// Insertar un nuevo ciclista comprobando que 
		// un equipo no tenga má de 15 ciclistas inscritos
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		
		// ENTRADA DE DATOS
		
		String vequipo = null;
	
		// LEER VALORES DE ENTRADA
						
		Scanner sc = new Scanner(System.in);
		System.out.println("EQUIPO");
		vequipo = sc.nextLine();
		
		// SALIDA DE DATOS
		
		Integer vcount = null;
		
		try {
			Properties propiedades = new Properties();
			propiedades.load
			(new FileInputStream("configuracion\\propCiclismo.txt"));

			// Creamos la conexión de DataSource con el fichero propCiclismo.txt
					
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
					
			con = ds.getConnection();
			
			// Operacion consulta
			
			// Comprobar si el equipo existe
			
			sql = "SELECT * FROM equipo WHERE nomeq = ?";
			
			// Inyectamos la sql en el preparedStatement
			
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
												ResultSet.CONCUR_READ_ONLY);
			
			
			pstmt.setString(1, vequipo);
			
			rs = pstmt.executeQuery();
			
			if (rs.first()) {
				sql = "SELECT count(*) FROM ciclista WHERE nomeq = ?";
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				pstmt.setString(1, vequipo);
				rs = pstmt.executeQuery();
				if (rs.first()) {
					vcount = rs.getInt(1);
					if(vcount<15) {
						sql = "insert into ciclista (nombre,nomeq) "
								+ "values (?,?)";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, "Juan Aguado");
						pstmt.setString(2, vequipo);
						pstmt.executeUpdate();
						
					} else {
						System.out.println("No se puedes inscribir más ciciclistas");
					}
				} 
					
			} else {
				
				System.out.println("No se ha encotrado el equipo " + vequipo);
			}
				
			
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


