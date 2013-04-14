package profile.adaptadores;

import java.util.List;

import profile.objetos.Dica;
import profile.telas.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListaAdapter extends BaseAdapter{
	private int posAtual;
	private Context ctx;
	private List<Dica> dicas;
	
	public ListaAdapter(Context _ctx, List<Dica> _strings){
		ctx = _ctx;
		dicas = _strings;
		posAtual = 0;
}
	public int getCount() {
		return dicas.size();
	}

	public Object getItem(int arg0) {		
		return dicas.get(arg0);
	}

	public long getItemId(int arg0) {
		return 0;
	}
	
	public void setPosicaoAtual(int _posAtual){
		posAtual = _posAtual;
	}

	public View getView(int pos, View arg1, ViewGroup arg2) {
		View v = LayoutInflater.from(ctx).inflate(R.layout.linha, null);
		TextView texto = (TextView)v.findViewById(R.id.linhaTxt);
		LinearLayout backgroundLinha = (LinearLayout)v.findViewById(R.id.backgroundLinha);
		if(pos <= posAtual){			
			texto.setText(dicas.get(pos).getDica());
		}else{		
			backgroundLinha.setBackgroundResource(R.drawable.retangulo_desativado);
			texto.setText("");
		}return v;
	}

}
