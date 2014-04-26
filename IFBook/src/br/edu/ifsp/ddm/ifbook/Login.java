package br.edu.ifsp.ddm.ifbook;

import br.edu.ifsp.ddm.ifbook.R;


import br.edu.ifsp.ddm.ifbook.dao.UsuarioDAO;
import br.edu.ifsp.ddm.ifbook.modelo.Usuario;
import br.edu.ifsp.ddm.ifbook.util.LoginManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	
	private static final int ACTIVITY_EXIBIR_PERFIL = 1;
	private Context c;
	private UsuarioDAO dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perfil_login);
		c = getApplicationContext();
		Button entrar = (Button) findViewById(R.id.buttonEntrar);
		entrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText login = (EditText) findViewById(R.id.editLogin);
				EditText eSenha = (EditText) findViewById(R.id.editSenha);
				
				String prontuario = login.getText().toString();
				String senha = eSenha.getText().toString();

				dao = new UsuarioDAO(c);
				if(prontuario.length() == 0 || senha.length() == 0){
					exibirMensagem("Preencha os campos para logar!");
				}
				else{
					try{
						Usuario user = dao.getByProntuario(prontuario);

						if(user.getSenha().equals(senha) && user.getProntuario().equals(prontuario)){
							LoginManager.getLogin().setUsuario(user.getNome());
							LoginManager.getLogin().setId(user.getIdUsuario());
							LoginManager.getLogin().setNivel(user.getNivel());
						
							Intent it = new Intent(getApplicationContext(), ExibePerfil.class);
							startActivityForResult(it, ACTIVITY_EXIBIR_PERFIL);
						}
						else{
							exibirMensagem("Prontuario ou Senha inválidos!");
						}
					}
					catch(Exception e){
						exibirMensagem("Falha ao logar! :(");
						e.printStackTrace();
					}

				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private void exibirMensagem(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
}
