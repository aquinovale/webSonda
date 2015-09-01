package br.aquino;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
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
	
	@Test
	public void testEndPoint(){
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					"http://localhost:8080/Sonda/api/rest");

			StringEntity input = new StringEntity(json);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			httpClient.getConnectionManager().shutdown();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
