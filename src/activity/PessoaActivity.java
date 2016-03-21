package activity;

import com.example.listenerapp_2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import entidade.Pessoa;

public class PessoaActivity extends Activity{
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_pessoa);

	        Intent intent = getIntent();
	        Pessoa pessoa = (Pessoa) intent.getSerializableExtra("pessoa");

	        TextView txtInscription = (TextView) findViewById(R.id.inscription);
	        TextView txtFullName = (TextView) findViewById(R.id.fullName);
	        TextView txtId = (TextView) findViewById(R.id.id);
	        TextView txtEmail = (TextView) findViewById(R.id.email);
	        TextView txtIsDelivered = (TextView) findViewById(R.id.isDelivered);

	        txtInscription.setText(pessoa.getDescricao());
	        txtFullName.setText(pessoa.getNome());
	        txtId.setText("Identificador numérico: " + pessoa.getId());
	        txtEmail.setText(pessoa.getEmail());
	        if(pessoa.isEntregue()){
	            txtIsDelivered.setText("Situação da entrega:  executado");
	        }
	        else
	            txtIsDelivered.setText("Situação da entrega: não executado");

	    }

}
