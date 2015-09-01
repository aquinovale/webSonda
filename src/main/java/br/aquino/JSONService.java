package br.aquino;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/api")
public class JSONService {

	@POST
	@Path("/rest")
	@Consumes("application/json")
	@Produces("application/json")
	public Response executeMove(String json) {
		return Response.status(200).entity(getSondaResult(json)).build();
	}
	
	private String getSondaResult(String in){
    	ReadCommands commands = new ReadCommands();
    	JSONArray array = commands.readCommands(new JSONObject(in));
    	
    	StringBuilder str = new StringBuilder();
    	for (int i = 0; i < array.length(); i++){
    		JSONObject json = array.getJSONObject(i);
    		str.append(json.getString("result")+" \n");
    	}
    	return str.toString();
    }
	
}