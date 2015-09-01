package br.aquino;

import static io.undertow.Handlers.path;

import io.undertow.Undertow;

public class App{
	
    public static void main( String[] args )    {
    	Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
//                .setHandler(path().addPrefixPath("/api/rest", new InternalRestHandler())).build();
                  .setHandler(path()).build();
        server.start();
    }
}
