package br.edu.ifsp.ddm.ifbook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.edu.ifsp.ddm.ifbook.dao.UsuarioDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;


public class Perfil_Usuario extends Activity {

	private TextView nome;
	private TextView apelido;
	private TextView localTrabalho;
	private TextView Cidade;
	private UsuarioDAO usuariodao;
	private TextView EstadoCivil;
	private TextView aniversario;
	private TextView email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil__usuario);
		nome = (TextView) findViewById(R.id.textNomeUsuario);
		apelido = (TextView) findViewById(R.id.textUsuarioApelido);
		localTrabalho = (TextView) findViewById(R.id.textLocaltrabalho);
		Cidade = (TextView) findViewById(R.id.textCidade);
		EstadoCivil = (TextView) findViewById(R.id.txtEstadoCivil);		
		aniversario = (TextView) findViewById(R.id.txtAniversario);
		email = (TextView) findViewById(R.id.txtEmail);
		
		
		System.out.println("ENTREI NA CLASSE PERFIL_USUARIO!!");
	
		this.getIntent().getStringExtra("idUsuario");
		String idUsuario = this.getIntent().getStringExtra("idUsuario");
		usuariodao = new UsuarioDAO(getApplicationContext());
		int idusuario = Integer.parseInt(idUsuario);
		Usuario usuario = new Usuario();

	    usuario = usuariodao.getById(idusuario);
	      
	    nome.setText(("Nome: "+usuario.getNome()));
	    apelido.setText("Apelido: "+usuario.getApelido());
	    localTrabalho.setText("Local de Trabalho: "+usuario.getLocalTrabalho());		   
	    Cidade.setText("Cidade: " + usuario.getCidade());
	    EstadoCivil.setText("Estado Civil: "+usuario.getIdEstadoCivil().getEstadoCivil());
	    email.setText("Email: "+usuario.getEmail());
	
		Calendar c = Calendar.getInstance();  
	    SimpleDateFormat f = new SimpleDateFormat("yyyy");
	    String ano = f.format( c.getTime() );
	    
	    Calendar c2 = Calendar.getInstance();  
	    SimpleDateFormat f2 = new SimpleDateFormat("MM");
	    String mes = f2.format( c2.getTime() );
	    
	    Calendar c3 = Calendar.getInstance();  
	    SimpleDateFormat f3 = new SimpleDateFormat("dd");
	    String dia = f3.format( c3.getTime() );
	  
	    
	    String anoNascimento = usuario.getNascimento().substring(0,4);
	    String mesNascimento = usuario.getNascimento().substring(6,7);
	    String diaNascimento = usuario.getNascimento().substring(8,10);

	   
	    
	   int diaAtual = Integer.parseInt(dia);
	   int mesAtual = Integer.parseInt(mes);
	   int anoAtual = Integer.parseInt(ano);
	   int diaUsuario = Integer.parseInt(diaNascimento);
	   int mesUsuario = Integer.parseInt(mesNascimento);
	   int anoUsuario = Integer.parseInt(anoNascimento);
	    
	   int idade = anoAtual - anoUsuario;
	 
	   if((mesAtual <= mesUsuario) && (diaAtual < diaUsuario)){
		  
	   		idade = idade - 1;
	  	
	   }
	     
	    aniversario.setText("Idade: "+idade);
	
	}


}
