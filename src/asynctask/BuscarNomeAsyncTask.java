package asynctask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.protocol.HttpService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import activity.BuscarNomeActivity;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import callback.BuscarPessoaCallBack;
import entidade.Pessoa;
import util.Response;

@SuppressLint("NewApi")
public class BuscarNomeAsyncTask extends AsyncTask<Pessoa, Void, Response> {

	private BuscarNomeActivity buscarNomeCallBack;
	
	public BuscarNomeAsyncTask(BuscarNomeActivity buscarNomeActivity) {

        this.buscarNomeCallBack = buscarNomeActivity;
    }
	
	@Override
    protected Response doInBackground(Pessoa... pessoas) {

        Response response = null;

        Pessoa pessoa = pessoas[0];
        Gson gson =  new Gson();
        Log.i("EditTextListener", "doInBackground (JSON): " + pessoa);

        try {

            response = HttpService.sendJSONPostResquest("get-byname",gson.toJson(pessoa));

        } catch (IOException e) {

            Log.e("EditTextListener", e.getMessage());
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {

        int codeHttp = response.getStatusCodeHttp();

        Log.i("EditTextListener", "Código HTTP: " + codeHttp
                + " Conteúdo: " + response.getContentValue());

        if (codeHttp != HttpURLConnection.HTTP_OK) {

            buscarNomeCallBack.errorBuscarNome(response.getContentValue());

        } else {

            Gson gson = new Gson();
            List<Pessoa> pessoas = gson.fromJson(response.getContentValue(),
                    new TypeToken<ArrayList<Pessoa>>(){}.getType());

            buscarNomeCallBack.backBuscarNome(pessoas);
        }
    }
	
}
