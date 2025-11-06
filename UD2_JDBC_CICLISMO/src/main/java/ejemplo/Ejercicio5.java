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

public class Ejercicio5 {

	public static void main(String[] args) {
		// Obtener si un ciclista ha ganado etapas
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		
		// ENTRADA DE DATOS
		
		Integer vdorsal = null;
		
		// SALIDA DE DATOS
		
		String encontrado = null;
		
		// LEER VALORES DE ENTRADA
		
		// LEER VALORES DE LA INTERFAZ
				
		Scanner sc = new Scanner(System.in);
		System.out.println("DORSAL DEL CICLISTA");
		vdorsal = sc.nextInt();
		
		// Parte de datos (DAO)
		
				try {
					Properties propiedades = new Properties();
					propiedades.load
					(new FileInputStream("configuracion\\propCiclismo.txt"));

					// Creamos la conexión de DataSource con el fichero propCiclismo.txt
							
					DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
							
					con = ds.getConnection();
					
					// Operacion consulta
					
					sql = "SELECT * FROM etapa WHERE dorsal = ?";
					
					// Inyectamos la sql en el preparedStatement
					
					pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
													   ResultSet.CONCUR_READ_ONLY);
					
					// Asignamos valor a los ? (empieza por el 1)
					
					pstmt.setInt(1, vdorsal);
					
					// Ejecutamos el insert
					
					rs = pstmt.executeQuery();
					
					if(rs.first()) {
						
						encontrado = "SI";
						
						System.out.println("¿Ha ganado etapas? " + encontrado);
					} else {
						
						encontrado = "NO";
						
						System.out.println("El dorsal " + vdorsal + " NO ha ganado ninguna etapa");
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



