package br.aquino;

import org.json.JSONArray;
import org.json.JSONObject;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class App{
	
	public static String json = "{ \"commands\" : \"5 5 \\n 1 2 N  \\n LMLMLMLMM \\n 3 3 E \\n MMRMMRMRRM\" }";
	
    public static void main( String[] args )    {
    	Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(new HttpHandler() {
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                        exchange.getResponseSender().send(getSondaResult(json));
                    }
                }).build();
        server.start();
    }
    
    public static String getSondaResult(String in){
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
