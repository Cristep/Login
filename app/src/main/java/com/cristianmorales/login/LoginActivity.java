package com.cristianmorales.login;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.cristianmorales.login.Utils.Util;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Switch switch1;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindUI();
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setCredeantialsIfExists();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                if (login(email,password)){


                    goToMain();
                    saveOnPreferences(email,password);
                }
            }
        });
    }
    private void bindUI(){

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        switch1 = (Switch) findViewById(R.id.switch1);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }
    private void setCredeantialsIfExists(){
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getUserPassPrefs(prefs);
        if(!TextUtils.isEmpty(email)&& !TextUtils.isEmpty(password)){
           editTextEmail.setText(email);
           editTextPassword.setText(password);
        }
    }
    private boolean login(String email, String password) {

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Email no valido, Introduzca otro", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!isValidPassword(password)){
            Toast.makeText(this, "La contrasena no es valida, 4 caracteres o mas", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }


    }
    private void saveOnPreferences(String email, String password){
        if(switch1.isChecked()){
           SharedPreferences.Editor editor = prefs.edit();
           editor.putString("email", email);
           editor.putString("pass", password);
           editor.apply();
        }
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }
    private boolean isValidPassword(String password){

        return password.length() >= 4;
    }
    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

}
