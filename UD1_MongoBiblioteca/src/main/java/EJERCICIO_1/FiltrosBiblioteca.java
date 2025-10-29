package EJERCICIO_1;

import java.util.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class FiltrosBiblioteca {

	public static void main(String[] args) {
		// Conectamos al servidor
		MongoClient mongoCliente = MongoClients.create();
		// Conectamos a la BD
		MongoDatabase DB_Biblioteca = mongoCliente.getDatabase("Biblioteca");
		// Conectamos con la coleccion
		MongoCollection COLL_Libros = DB_Biblioteca.getCollection("Libros");
		MongoCollection COLL_Socios = DB_Biblioteca.getCollection("Socios");
		MongoCollection COLL_Prestamos = DB_Biblioteca.getCollection("Prestamos");
		// Metodo que busca todos los registros -> METODO find()
		MongoCursor <Document> cursor_libros = COLL_Libros.find().iterator();
		MongoCursor <Document> cursor_socios = COLL_Socios.find().iterator();
		MongoCursor <Document> cursor_prestamos = COLL_Prestamos.find().iterator();

		Document DOC_LIBRO = null;
		Document DOC_SOCIO = null;
		
		Bson bsonFilter;
		Bson bsonFilter2;
		
		String cadena;
		
		//System.out.println(DOC_LIBRO.toJson());
		
		/*while(cursor_libros.hasNext()) {
			// Devuelve Document
			System.out.println(cursor_libros.next());
			// Devuelve JSON
			System.out.println(cursor_libros.next().toJson());
		}
		
		// Obtener el primer registro de libros
		
		
		DOC_LIBRO = (Document) COLL_Libros.find().first();
		
		
		while(cursor_socios.hasNext()) {
			// Devuelve Document
			System.out.println(cursor_socios.next());
			// Devuelve JSON
			System.out.println(cursor_socios.next().toJson());
		}
		
		// Obtener el primer registro de libros
		
		Document DOC_SOCIO = null;
		DOC_SOCIO = (Document) COLL_Socios.find().first();
		
		System.out.println(DOC_SOCIO);
		
		
		// Obtener el nombre de los socios -> METODO get()
		
		cursor_socios = COLL_Socios.find().iterator();
		
		while(cursor_socios.hasNext()) {
			// Devuelve document BSON
			System.out.println("Nombre: " + cursor_socios.next().get("Nombre"));
		}
		
		
		
		// FILTROS --------------------------------------------------------------------------
		// Libro que se titula "El Quijote"
		
		bsonFilter = Filters.eq("Titulo","El Quijote");
		DOC_LIBRO = (Document) COLL_Libros.find(bsonFilter).first();
		System.out.println("Titulo : " + DOC_LIBRO.get("Titulo"));
		System.out.println("Autor : " + DOC_LIBRO.get("Autor").toString());
		
		
		 Muestra los prestamos con 2 libros
		bsonFilter = Filters.size("libro", 2);
		cursor_prestamos = COLL_Prestamos.find(bsonFilter).iterator();
		
		while(cursor_prestamos.hasNext()) {
			System.out.println("Prestamo de 2 libros" + cursor_prestamos.next());
		}
		
		
		// Los libros con ISBN mayor que 45000
		bsonFilter = Filters.gt("ISBN",45000);
		cursor_libros = COLL_Libros.find(bsonFilter).iterator();
		while(cursor_libros.hasNext()) {
			System.out.println(cursor_libros.next().get("Titulo"));
		}
		
		
		// Buscar libros de Follet
		
		bsonFilter = Filters.eq("autor.apellido", "Follet");
		
		cursor_libros = COLL_Libros.find(bsonFilter).iterator();
		while(cursor_libros.hasNext()) {
			System.out.println(cursor_libros.next().get("titulo"));
		}
		
		
		//-- Buscar los socios que han tenido prestado 456779
		
		// Hago el primer filtro que compara el ISBN de un libro con un numero
		bsonFilter = Filters.eq("libro.isbn", "456779");
		cursor_prestamos = COLL_Prestamos.find(bsonFilter).iterator();
		
		// Obtenemos el numero del socio al que corresponde ese prestamo
		while(cursor_prestamos.hasNext()) {
			DOC_SOCIO = (Document) cursor_prestamos.next().get("socio");
			System.out.println("Numero de socio: " + DOC_SOCIO.get("num_socio"));
		}
		
		// Hacemos el casting del string numero de socio a Integer
		cadena = (String) DOC_SOCIO.get("num_socio");
		Integer numero = Integer.parseInt(cadena);
		
		// Ahora que tenemos los dos como Integer si que los podemos comparar 
		bsonFilter2 = Filters.eq("Num_socio", numero);
		
		cursor_socios = COLL_Socios.find(bsonFilter2).iterator();
		
		while(cursor_socios.hasNext()) {
			System.out.println("Nombre: " + cursor_socios.next().get("Nombre"));
		}
		
		// 10. Buscar libros con año mayor que 2000 y de más de 500 páginas
		
		bsonFilter = Filters.and(Filters.gt("año_publicacion",2000), Filters.gt("páginas",500));
		cursor_libros = COLL_Libros.find(bsonFilter).iterator();
		
		while(cursor_libros.hasNext()) {
			DOC_LIBRO = cursor_libros.next();
			System.out.println("Libro: " + DOC_LIBRO.get("titulo") + "-> " + DOC_LIBRO.get("año_publicacion") + ". -> Páginas: " + DOC_LIBRO.get("páginas"));
		}
		
		
		
		// 11. Buscar libros editados entre 2010 y 2020 
		
		bsonFilter = Filters.and(Filters.gte("año_publicacion",2010), Filters.lte("año_publicacion",2020));
		cursor_libros = COLL_Libros.find(bsonFilter).iterator();
		
		while(cursor_libros.hasNext()) {
			DOC_LIBRO = cursor_libros.next();
			System.out.println("Libro: " + DOC_LIBRO.get("titulo") + "-> " + DOC_LIBRO.get("año_publicacion"));
		}
		
		
		// 12. Buscar los libros de la editorial Debolsillo
		
		bsonFilter = Filters.eq("editorial", "Debolsillo");
		cursor_libros = COLL_Libros.find(bsonFilter).iterator();
		
		while(cursor_libros.hasNext()) {
			DOC_LIBRO = cursor_libros.next();
			System.out.println("Libro: " + DOC_LIBRO.get("titulo") + " -> Editorial: " + DOC_LIBRO.get("editorial"));
		}
		
		
		 // 13. Mostar el nombre del socio 2
		
		bsonFilter = Filters.eq("Num_socio", 2);
		cursor_socios = COLL_Socios.find(bsonFilter).iterator();
		
		while(cursor_socios.hasNext()) {
			DOC_SOCIO = cursor_socios.next();
			System.out.println("Número de socio: " + DOC_SOCIO.get("Num_socio") + " -> Nombre: " + DOC_SOCIO.get("Nombre"));
		}
		
		
		// Buscar dentro de una cadena
			// Ejemplo Proyección Projection -> Mostrar la fecha de préstamo
			// y devolución de libros del socio 2
		
		bsonFilter = Filters.eq("socio.num_socio", "2");
		
		cursor_prestamos = COLL_Prestamos.find(bsonFilter).projection(Projections.fields(Projections.include("fecha_pres", "fecha_dev"))).iterator();
		
		while(cursor_prestamos.hasNext()) {
			System.out.println("Prestamos" + cursor_prestamos.next().toJson());
		}
		
		
		
		// 10. (clase) Libro de mas de 500 páginas y que son de la ed Plaza&Janes
				///
				/// Proyección: muestre el nombre del autor y número de páginas
				
				bsonFilter = Filters.and(Filters.gt("páginas",500), Filters.eq("editorial","Plaza&Janes"));
				
				//cursor_libros = COLL_Libros.find(bsonFilter).iterator();
				
				cursor_libros = COLL_Libros.find(bsonFilter)
						.sort(Sorts.descending("páginas"))
						.projection(Projections.fields(Projections
								.include("autor.nombre", "titulo", "páginas"),
								Projections.excludeId())).iterator();
				
				
				while(cursor_libros.hasNext()) {
				
					System.out.println("Libros ordenados: " + cursor_libros.next().toJson());
				}
				
				
		// 14. Buscar los libros (título, nombre autor) que sean de autores de nacionalidad americana o de la editorial planeta
		// Actualizar la fecha de fecha_dev socio 1
				
				bsonFilter = Filters.eq("socio.num_socio", "1");
				COLL_Prestamos.updateOne(bsonFilter, new Document("$set",
						new Document("fecha_dev",new Date("2025/11/01"))));
		*/		
		// Borrar libros de menos de 350 páginas
				
				bsonFilter = Filters.lt("páginas", 350);
				COLL_Libros.deleteMany(bsonFilter);
			
	}

}
