package activity;

import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import entidade.Pessoa;

public interface BuscarPessoaCallBack {
	
	void backBuscarNome(List<Pessoa> names);

    void errorBuscarNome(String error);

	void onItemClick1(AdapterView<?> parent, View view, int position, long id);

}
