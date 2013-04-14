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

public class ListaSimplesAdapter extends BaseAdapter{
	private Context ctx;
	private List<String> infos;
	
	public ListaSimplesAdapter(Context _ctx, List<String> _infos){
		ctx = _ctx;
		infos = _infos;
	}
	public int getCount() {
		return infos.size();
	}

	public Object getItem(int arg0) {		
		return infos.get(arg0);
	}

	public long getItemId(int arg0) {
		return 0;
	}
	
	public View getView(int pos, View arg1, ViewGroup arg2) {
		View v = LayoutInflater.from(ctx).inflate(R.layout.linha_simples, null);
		TextView texto = (TextView)v.findViewById(R.id.rowTextView);
		texto.setText(infos.get(pos));
		return v;
	}

}
