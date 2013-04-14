package profile.telas;

import java.util.ArrayList;

import profile.adaptadores.ListaSimplesAdapter;
import profile.objetos.Estado;
import profile.objetos.Pergunta;
import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class InfosActivity extends Activity{

	public InfosActivity() {
		// TODO Auto-generated constructor stub
	}
	
	private Pergunta p;
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.info);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		p = (Pergunta) getIntent().getSerializableExtra("pergunta");
		
		TextView port5 = (TextView) findViewById(R.id.port5txt);
		TextView port9 = (TextView) findViewById(R.id.port9txt);
		TextView mtm5 = (TextView) findViewById(R.id.mtm5txt);
		TextView mtm9 = (TextView) findViewById(R.id.mtm9txt);
		TextView estado = (TextView) findViewById(R.id.infoTxt);
		
		Estado e = p.getEstado();
		
		port5.setText((int)(e.getAprendizadoPort5()*100)+"%");
		port9.setText((int)(e.getAprendizadoPort9()*100)+"%");
		mtm5.setText((int)(e.getAprendizadoMtm5()*100)+"%");
		mtm9.setText((int)(e.getAprendizadoMtm9()*100)+"%");
		estado.setText(estado.getText() + " " + e.getNome() + "...");
		
		String[] infos = e.getInfos().split("@");
		
		ListView lista = (ListView) findViewById(R.id.infos);
		
		final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < infos.length; ++i) {
	      list.add(infos[i]);
	    }
	    
	    ListaSimplesAdapter listAdapter = new ListaSimplesAdapter(this, list);  
	    lista.setAdapter(listAdapter);
	    
	}

}
