package profile.telas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import profile.objetos.Pergunta;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class RespostaActivity extends Activity{
	private ArrayList<String> palavra = new ArrayList<String>();
	private ArrayList<String> letras = new ArrayList<String>();
	private ArrayList<String> resp = new ArrayList<String>();
	
	private Pergunta p;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resposta);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		p = (Pergunta) getIntent().getSerializableExtra("pergunta");
		
		char[] chars = p.getEstado().getNome().replaceAll(" ", "").toCharArray();
		
		for(int i = 0; i < chars.length; i++)
			resp.add(Character.toUpperCase(chars[i])+"");
		
		int respTam = resp.size();
		randomizarLetras(28-respTam);
		
		LinearLayout layoutResposta = (LinearLayout)findViewById(R.id.layoutResposta);
		LinearLayout layoutResposta2 = (LinearLayout)findViewById(R.id.layoutResposta2);
		LinearLayout layoutResposta3 = (LinearLayout)findViewById(R.id.layoutResposta3);
		LinearLayout layoutLetras = (LinearLayout)findViewById(R.id.layoutLetras);
		LinearLayout layoutLetras2 = (LinearLayout)findViewById(R.id.layoutLetras2);
		LinearLayout layoutLetras3 = (LinearLayout)findViewById(R.id.layoutLetras3);
		LinearLayout layoutLetras4 = (LinearLayout)findViewById(R.id.layoutLetras4);
		LayoutParams parametros = new LinearLayout.LayoutParams(60, 60);
		parametros.setMargins(5, 5, 5, 5);
		//cria caixas da resposta
		for(int i = 0; i < respTam; i++){
			LinearLayout temp = new LinearLayout(this);
			temp.setBackgroundResource(R.drawable.retangulo_arredondado);			
			TextView txtTemp = new TextView(this);
			txtTemp.setTextSize(20);
			palavra.add("99");
			temp.addView(txtTemp);
			temp.setGravity(Gravity.CENTER_HORIZONTAL);
			temp.setTag(i);
			temp.setOnClickListener(letraRemListener);
			if(i < 7)
				layoutResposta.addView(temp, i, parametros);
			else
				if(i < 14)
					layoutResposta2.addView(temp, i-7, parametros);
				else
					layoutResposta3.addView(temp, i-14, parametros);
		}
		//cria caixas das letras
		for(int i = 0; i < 28; i++){
			String letra = letras.get(i);
			LinearLayout temp = new LinearLayout(this);
			temp.setBackgroundResource(R.drawable.retangulo_arredondado);			
			TextView txtTemp = new TextView(this);
			txtTemp.setTextSize(26);
			txtTemp.setText(letra);
			temp.addView(txtTemp);
			temp.setGravity(Gravity.CENTER_HORIZONTAL);
			temp.setTag(letra);
			temp.setOnClickListener(letraAddListener);
			if(i < 7)
				layoutLetras.addView(temp, i, parametros);
			else
				if (i < 14)
					layoutLetras2.addView(temp, i-7, parametros);
				else
					if(i < 21)
						layoutLetras3.addView(temp, i-14, parametros);
					else
						layoutLetras4.addView(temp, i-21, parametros);
		}
	}
	
	public OnClickListener letraAddListener = new OnClickListener() {		
		public void onClick(View v) {
			int num = palavra.indexOf("99");	
			if(num == -1){
				Log.e("ERRO", "ERRO");
				return;
			}
			LinearLayout layoutResposta = (LinearLayout)findViewById(R.id.layoutResposta);
			LinearLayout layoutResposta2 = (LinearLayout)findViewById(R.id.layoutResposta2);
			LinearLayout layoutResposta3 = (LinearLayout)findViewById(R.id.layoutResposta3);
			LinearLayout temp = (LinearLayout)layoutResposta.getChildAt(num);
			if(num < 7)
				temp = (LinearLayout)layoutResposta.getChildAt(num);
			else
				if(num < 14)
					temp = (LinearLayout)layoutResposta2.getChildAt(num-7);
				else
					temp = (LinearLayout)layoutResposta3.getChildAt(num-14);
			
			TextView txt = (TextView)temp.getChildAt(0);
			String texto = v.getTag()+"";
			txt.setText(texto);
			palavra.set(num, texto);
			if(palavra.indexOf("99") == -1){
				Log.e("FIMM", palavra.toString());
				Log.e("FIM2", resp.toString());
				if(palavra.equals(resp)){
					layoutResposta.setBackgroundColor(Color.GREEN);
					layoutResposta2.setBackgroundColor(Color.GREEN);
					layoutResposta3.setBackgroundColor(Color.GREEN);
					
					final Intent intent = new Intent(RespostaActivity.this, InfosActivity.class);
					intent.putExtra("pergunta", p);
					
					new AlertDialog.Builder(RespostaActivity.this)
				    .setTitle("Você acertou!!")
				    .setMessage("Parabéns você acertou!!")
				    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) { 
				            startActivity(intent);
				        }
				     })
				     .show();
				}
				else{
					layoutResposta.setBackgroundColor(Color.RED);
					layoutResposta2.setBackgroundColor(Color.RED);
					layoutResposta3.setBackgroundColor(Color.RED);
				}
				return;
			}
		}
	};
	
	public OnClickListener letraRemListener = new OnClickListener() {		
		public void onClick(View v) {
			int pos = Integer.parseInt(v.getTag()+"");
			palavra.set(pos, "99");
			LinearLayout layoutResposta = (LinearLayout)findViewById(R.id.layoutResposta);
			LinearLayout temp = (LinearLayout)layoutResposta.getChildAt(pos);
			TextView txt = (TextView)temp.getChildAt(0);
			txt.setText("");
		}
	};
	
	public void randomizarLetras(int total){
		for(int i = 0; i < resp.size(); i++)
			letras.add(resp.get(i));
		
		Random random = new Random();
		for(int i = 0; i < total; i++){
			char ch = (char)(random.nextInt('z'-'a'+1)+'A');
			letras.add(Character.toUpperCase(ch)+"");
		}
		Collections.shuffle(letras);
	}
}
