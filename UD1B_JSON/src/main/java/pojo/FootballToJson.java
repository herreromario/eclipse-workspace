package pojo;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class FootballToJson {
	public static void main(String[] args) {
	
	List<FootballPlayer>ValenciaTeam=Arrays.asList(
			new FootballPlayer(1,
					"Cañizares",
					Arrays.asList("portero")
					,"Valencia CF"),
			new FootballPlayer(7,
					"Villa",
					Arrays.asList("delantero"),
					"Valencia CF"));
	
	String json = new Gson().toJson(ValenciaTeam);
	
	System.out.print(json);
	}
}
