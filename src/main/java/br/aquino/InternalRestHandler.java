package br.aquino;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xnio.channels.StreamSourceChannel;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class InternalRestHandler implements HttpHandler {
	public static String json = "{ \"commands\" : \"5 5 \\n 1 2 N  \\n LMLMLMLMM \\n 3 3 E \\n MMRMMRMRRM\" }";
	
	public void handleRequest(final HttpServerExchange exchange) throws Exception {
		StreamSourceChannel channel = exchange.getRequestChannel();
		System.out.println(channel);
		
		exchange.setStatusCode(200);
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
		exchange.getResponseSender().send(getSondaResult(channel.toString()));
		exchange.getResponseSender().send(getSondaResult(json));
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
