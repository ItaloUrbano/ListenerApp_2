package adapter;

import java.util.List;

import com.example.listenerapp_2.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import entidade.Pessoa;

public class PessoasCustomAdapter extends BaseAdapter{
	Context context;
    List<Pessoa> pessoas;
    
    public PessoasCustomAdapter(Context context, List<Pessoa> pessoas) {
        this.context = context;
        this.pessoas = pessoas;
    }
    
    private class ViewHolder {
        TextView txtFullName;
        TextView txtInscription;
        TextView txtId;
        TextView txtEmail;
        TextView txtIsDelivered;
    }
    
	@Override
	public int getCount() {
		return pessoas.size();
	}

	@Override
	public Object getItem(int position) {
		 return pessoas.get(position);
	}

	@Override
	public long getItemId(int position) {
		 return pessoas.indexOf(getItem(position));
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		
		LayoutInflater mInflater = (LayoutInflater)  context.getSystemService(
                Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.txtInscription = (TextView) convertView.findViewById(R.id.inscription);
            holder.txtFullName = (TextView) convertView.findViewById(R.id.fullName);
            holder.txtId = (TextView) convertView.findViewById(R.id.id);
            holder.txtEmail = (TextView) convertView.findViewById(R.id.email);
            holder.txtIsDelivered = (TextView) convertView.findViewById(R.id.isDelivered);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        Pessoa pessoaItem = (Pessoa) getItem(position);

        holder.txtFullName.setText(pessoaItem.getNome());
        holder.txtInscription.setText(pessoaItem.getDescricao());
        holder.txtEmail.setText(pessoaItem.getEmail());
        holder.txtId.setText("Identificador numérico: "+ pessoaItem.getId());
        if(pessoaItem.isEntregue()){
            holder.txtIsDelivered.setText("Situação da entrega: executado");
        }
        else
            holder.txtIsDelivered.setText("Situação da entrega: não executado");
        return convertView;
    }
	
	

}
