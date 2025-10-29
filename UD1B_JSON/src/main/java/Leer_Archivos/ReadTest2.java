package Leer_Archivos;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadTest2 {

	public static void main(String[] args) {
		JSONParser parse = new JSONParser();
		
		String sid = null;
		
		try {
			Object objetoTest2 = parse.parse(new FileReader("ficheros\\test2.json"));
			JSONObject jsonObjetoTest2 = (JSONObject)objetoTest2;  // cargado test2.json es un Object
			
			// Obtener el ID
			
			System.out.print("id"+jsonObjetoTest2.get("id"));
			
			JSONArray jsonArrayPages=(JSONArray)jsonObjetoTest2.get("pages");
			for (Object obj:jsonArrayPages) {
				JSONObject jsonObjectPage = (JSONObject)obj;
				System.out.println(jsonObjectPage.get("pageUrl"));
			}
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

}
