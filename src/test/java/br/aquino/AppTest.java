package br.aquino;


import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
	
	public static String json = "{ \"commands\" : \"5 5 \\n 1 2 N  \\n LMLMLMLMM \\n 3 3 E \\n MMRMMRMRRM\" }";
	
	@Test
    public void testApp()
    {
    	ReadCommands read = new ReadCommands();
    	Assert.assertEquals("[{\"result\":\"(1, 3) N\"},{\"result\":\"(5, 1) E\"}]", read.readCommands(new JSONObject(json)).toString());
    }
	
}
