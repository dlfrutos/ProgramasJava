/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Daniel
 */
@Path("Sorteio da Mega")
public class WebApp {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WebApp
     */
    public WebApp() {
    }

    /**
     * Retrieves representation of an instance of App.WebApp
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "HELLO MOTHEFUCKER JSON";
    }

    @GET
    //@Produces("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Sorteio/Numeros")
    public String sorteioNumeros() {

        //considerar fazer uma classe para os numeros
        //ex: combinação, resultado
        //site de referencia:
        // https://www.youtube.com/watch?v=FSepqL7-XAY
        List<String> lista = new ArrayList<String>();

        Random rand = new Random();
        String num;
        for (int i = 0; i < 6; i++) {
            
            num = Integer.toString((rand.nextInt(100)+0));
            System.out.println("Numero "+i+": "+num);
            lista.add(num);
        }

        Gson g = new GsonBuilder().setPrettyPrinting().create();
        return g.toJson(lista, ArrayList.class);

    }

    /**
     * PUT method for updating or creating an instance of WebApp
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

}
