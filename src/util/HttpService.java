package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class HttpService {
	
	// IP da máquina onde se encontra o servidor. response
    private static final String URL_CONTEXT = "http://ladoss.com.br:8773/pesquisa-simpif-service/services/";

    public static Response sendJSONPostResquest(String service, String jsonObject)
            throws MalformedURLException, IOException {

        HttpURLConnection connection = null;
        Response response = null;

        // Url Base e Serviço.
        URL url = new URL(URL_CONTEXT + service);

        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

        connection.connect();

        DataOutputStream stream = new DataOutputStream(connection.getOutputStream());

        stream.writeBytes(jsonObject);
        stream.flush();
        stream.close();

        // Resposta HTTP - Código e Conteúdo.
        int httpCode = connection.getResponseCode();
        String content = getHttpContent(connection);
        response = new Response(httpCode, content);

        return response;
    }


    public static String getHttpContent(HttpURLConnection connection) {

        StringBuilder builder = new StringBuilder();

        try {

            InputStream content = null;

            if(connection.getResponseCode() <= HttpURLConnection.HTTP_BAD_REQUEST)
                content = connection.getInputStream();
            else
                content = connection.getErrorStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    content, "UTF-8"), 8);

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            content.close();

        } catch (IOException e) {
            Log.e("EditTextListener", "IOException: " + e);
        }

        return builder.toString();
    }
}
