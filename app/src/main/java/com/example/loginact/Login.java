package com.example.loginact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    EditText emailInput, passInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        emailInput = (EditText)findViewById(R.id.emailInput);
        passInput = (EditText)findViewById(R.id.passInput);
    }

    public void login(View view){
        String email = emailInput.getText().toString().trim();
        String password = passInput.getText().toString();
        if (emailInput.getText().toString().length() == 0  && passInput.getText().toString().length() == 0 ){
            emailInput.setError("Campos Vacios");
            passInput.setError("Campos Vacios");

        }else {

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Has iniciado sesion correctamente!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Correo o Contraseña Invalidos", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }


    public void register(View view) {

        String email = emailInput.getText().toString().trim();
        String password = passInput.getText().toString();

        if (emailInput.getText().toString().length() == 0  && passInput.getText().toString().length() == 0 ){
            emailInput.setError("Campos Vacios");
            passInput.setError("Campos Vacios");

        }else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "EL REGISTRO FUE CORRECTO", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(Login.this, home.class);
                        startActivity(myIntent);
                        finish();
                    } else {
                        String e = task.getException().toString();
                        Toast.makeText(Login.this, e, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }
    public void limp(){
        emailInput.setText("");
        passInput.setText("");
    }



}