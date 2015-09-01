package br.aquino;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReadCommands {
	
	public static String COMMANDS = "commands";
	private ArrayList<Posicao> sondas;
	
	public ReadCommands(){
		sondas = new ArrayList<Posicao>();
	}
	
	public JSONArray readCommands(JSONObject commands){
		StringTokenizer token = new StringTokenizer(commands.getString(COMMANDS).toString(), "\n");
		int position = 0;
		while(token.hasMoreTokens()){
			executeCommands(token.nextToken().trim(), position);
			position++;
		}
		return returnPosition();
	}

	private void executeCommands(String value, int position){
		// Ignore the first command
		if(position > 0){
			// When command is even the set inicial sonda, 
			// or odd execute the movements
			if((position % 2 != 0)){
				String[] chr = value.split("\\s");
				//Get a value by position
				// position 1 - x
				// position 2 - y
				// position 3 - RosaVentos
				this.sondas.add(new Posicao(new Cartesiano(Integer.parseInt(chr[0]), Integer.parseInt(chr[1])), RosaVentos.valueOf(chr[2])));
			}else{
				// Get a last sonda and do the movements
				Posicao sonda = sondas.get(sondas.size()-1);
				char[] letras = value.toCharArray();
				for (int i = 0; i < letras.length; i++) {
					sonda.Movimentacao(Movimentos.valueOf(letras[i]+""));
				}
			}
		}
	}
	
	private JSONArray returnPosition(){
		JSONArray array = new JSONArray();
		for (Posicao sonda : sondas) {
			array.put(new JSONObject("{ \"result\" : \""+sonda.toString()+"\"}"));
		}
		return array;
	}
}
