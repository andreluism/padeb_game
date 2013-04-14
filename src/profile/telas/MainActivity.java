package profile.telas;

import java.util.ArrayList;
import profile.acessos.GetHttp;
import profile.adaptadores.ListaAdapter;
import profile.objetos.Pergunta;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity{
	private CountDownTimer timer;
	private ListaAdapter adaptador;
	private int pos = 0; //contador de posicoes para timer
	private Handler handler;
	private Pergunta pergAtual;
	private boolean isPerguntaSet = false;
	private static final String URL = "http://192.168.0.108:8080/padeb_profile/getProfile";
	private GetHttp acesso;
	private ArrayList<Integer> perguntasFeitas;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.main);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		perguntasFeitas = new ArrayList<Integer>();
		handler = new Handler(){
			public void handleMessage(Message msg) {
        		switch(msg.arg1){
	        		case 1:
	        			pergAtual = (Pergunta) msg.obj;
	        			isPerguntaSet = true;
	        			renderizaInterface();
	        			break;
        		}
        	}
		};
		boolean nova = true;
		int numPergunta;
		do{
			numPergunta = (int) ((Math.random()*27) + 1);
			nova = !perguntasFeitas.contains(numPergunta);
		}while(!nova);
		
		perguntasFeitas.add(numPergunta);
		
		acesso = new GetHttp(handler, URL);
		
		acesso.execute(numPergunta+"");
		//acesso.execute(2+"");
			
	}
	
	private void renderizaInterface(){
		final ListView lista = (ListView)findViewById(R.id.listView1);
		TextView categoria = (TextView)findViewById(R.id.categoria);
		categoria.setText(pergAtual.getCategoria());
		
		LinearLayout respostaLayout = (LinearLayout)findViewById(R.id.respostaLayout);
		respostaLayout.setOnClickListener(respostaListener);
		adaptador = new ListaAdapter(this, pergAtual.getDicas());		
		lista.setAdapter(adaptador);		
		//timer
		timer = new CountDownTimer(8000, 1000) {
			TextView countdown = (TextView) findViewById(R.id.countdown);
		     public void onTick(long millisUntilFinished) {
		    	 countdown.setText("" + millisUntilFinished / 1000);
		     }
		     public void onFinish() {
		    	 pos++;
		    	 lista.smoothScrollToPosition(pos);
		    	 adaptador.setPosicaoAtual(pos);
		    	 adaptador.notifyDataSetChanged();
		    	 //recursivo
		    	 if(pos < pergAtual.getDicas().size())
		    		 timer.start();
		    	 else{
		    		 Intent intent = new Intent(MainActivity.this, RespostaActivity.class);
		    		 intent.putExtra("pergunta", pergAtual);
		    		 startActivity(intent);
		    		 finish();
		    	 }
		     }
		  }.start();
	}
	
	public OnClickListener respostaListener = new OnClickListener() {
		public void onClick(View v) {
			timer.cancel();
			Intent intent = new Intent(MainActivity.this, RespostaActivity.class);
			intent.putExtra("pergunta", pergAtual);
			startActivity(intent);
			finish();
		}
	};
}

