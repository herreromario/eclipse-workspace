package EJERCICIO_1;

import java.util.*;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Insertar {

	public static void main(String[] args) {
		
		// Conectamos al servidor
		MongoClient mongoCliente = MongoClients.create();
		// Conectamos a la BD
		MongoDatabase DB_Biblioteca = mongoCliente.getDatabase("Biblioteca");
		// Conectamos con la coleccion
		MongoCollection COLL_Libros = DB_Biblioteca.getCollection("Libros");
		MongoCollection COLL_Socios = DB_Biblioteca.getCollection("Socios");
		MongoCollection COLL_Prestamos = DB_Biblioteca.getCollection("Prestamos");
		
		/*
		Document doc_libro = new Document("ISBN", 456779)
								.append("Titulo", "La sombra del viento")
									.append("Autor", new Document("Nombre", "Carlos")
														.append("Apellidos",  "Zafón"));
		COLL_Libros.insertOne(doc_libro);
		
		Document doc_libro = new Document("ISBN", 46885)
				.append("Titulo", "Sira")
				.append("Autor", new Document("Nombre", "Maria")
						.append("Apellidos", "Dueñas Vinuesa"));
		
		COLL_Libros.insertOne(doc_libro);
		
		Document doc_socio = new Document("Nombre", "Luis")
				.append("Apellidos", new Document("Apellido 1", "García")
						.append("Apellido 2", "Martín"));
		
		COLL_Socios.insertOne(doc_socio);
		
		*/
		
		ArrayList<Document> arrayLibros = new ArrayList<Document>();
		
		Document libro1 = new Document("isbn", "446854");
		Document libro2 = new Document("isbn", "456779");
		
		arrayLibros.add(libro1);
		arrayLibros.add(libro2);
		
		Document doc_prestamo = new Document("fecha_dev", new Date(2025-9-29))
									.append("fecha_pres", new Date(2025-9-1))
									.append("socio", new Document("num_socio", "2"))
									.append("libro", arrayLibros);
		
		COLL_Prestamos.insertOne(doc_prestamo);
	}
}
