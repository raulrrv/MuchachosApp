package com.muchachos.muchachosapp.Modelo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene on Systems  10/03/2018.
 */

public class conexionBD {

    private String resultado ="";
    private String URL = "http://r3g.000webhostapp.com/Modelo/";///la ubicacion

    private HttpClient httpClient;
    private HttpContext localContext;
    private HttpPost httpPost;


    public conexionBD(String clase) {
        httpClient = new DefaultHttpClient();
        localContext = new BasicHttpContext();
        httpPost = new HttpPost(URL + "/" + clase + ".php");
    }


    public String consultaConRetorno(List<NameValuePair> parametro)
    {
        try {

            httpPost.setEntity(new UrlEncodedFormEntity(parametro));///envia datos al servidor
            HttpResponse response = httpClient.execute(httpPost , localContext);//devuelve una respuesta con datos o sin ellos

            HttpEntity entity = response.getEntity();//tranformamos respuesta en datos leibles
            resultado = EntityUtils.toString(entity,"UTF-8");//se carga datos en variable
        }
        catch (Exception e)
        {
            resultado = "ERROR AL CARGAR DATOS: " + e.getMessage();
        }
        return resultado;
    }



    public String consultaSinRetorno(String sql , String accion)
    {
        try {

            //////////////////   cargo parametros   ////////////////////////////
            List<NameValuePair> parametro = new ArrayList<NameValuePair>(2);//creamos contexto pos
            parametro.add(new BasicNameValuePair("sql" , sql));
            parametro.add(new BasicNameValuePair("accion" , accion));



            httpPost.setEntity(new UrlEncodedFormEntity(parametro));///carga los datos post
            HttpResponse response = httpClient.execute(httpPost , localContext);//debueve una respuesta

            HttpEntity entity = response.getEntity();//tranformamos respuesta
            resultado = EntityUtils.toString(entity,"UTF-8");//respuesta
        }
        catch (Exception e)
        {
            resultado = "";
        }
        return resultado;

    }
}
