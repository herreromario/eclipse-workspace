package Practicas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Productos {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		
		Object objetoProductos;
		try {
			objetoProductos = parser.parse(new FileReader("ficheros\\productos.json"));
			JSONArray jArrayProductos = (JSONArray) objetoProductos;
			
			
			//ejercicio1(jArrayProductos);
			//ejercicio2(jArrayProductos);
			//ejercicio3(jArrayProductos);
			ejercicio4(jArrayProductos);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	// 1. Obten el nombre de todos los productos
	
	public static void ejercicio1 (JSONArray jArrayProductos) {
		for (Object object : jArrayProductos) {
			JSONObject jObjProducto = (JSONObject) object;
			System.out.println("Producto: " + jObjProducto.get("nombre_producto"));
		}
	}
	
	// 2. Obtener el nombre y el precio de los productos de la tipo «Ropa»
	public static void ejercicio2(JSONArray jArrayProductos) {
		for (Object object : jArrayProductos) {
			JSONObject jObjProducto = (JSONObject) object;
			if(jObjProducto.get("tipo_producto").equals("Ropa")) {
				System.out.println("Producto: " + jObjProducto.get("nombre_producto") + " | Precio: " + jObjProducto.get("precio") );
			}
		}
	}
	
	// 3. - Mostrar toda la información del «Pantalones Levi's 501»
	public static void ejercicio3(JSONArray jArrayProductos) {
		for (Object object : jArrayProductos) {
			JSONObject jObjProducto = (JSONObject) object;
			if(jObjProducto.get("nombre_producto").equals("Pantalones Levi's 501")) {
				System.out.println(jObjProducto.toJSONString());
			}
		}
	}
	
	// 4. Mostrar el nombre y el stock de los productos que están en el «Almacén 1» y que su precio es
	// inferior a 15
	public static void ejercicio4(JSONArray jArrayProductos) {
		for (Object object : jArrayProductos) {
			JSONObject jObjProducto = (JSONObject) object;
			JSONObject jObjUbicacion = (JSONObject) jObjProducto.get("ubicacion");
			if(jObjUbicacion.get("almacen").equals("Almacén 1") & (Double)jObjProducto.get("precio") > 15.0) {
				System.out.println("Producto: " + jObjProducto.get("nombre_producto") + " | Stock: " + jObjProducto.get("stock"));
		}
	}
}
}
