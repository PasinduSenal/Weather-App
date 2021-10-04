package com.anioncode.wheatheraplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anioncode.wheatheraplication.DataBase.DBHandler;

public class Login extends AppCompatActivity {

    private Button login;
    private EditText phone, pw;
    private TextView reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.btnRegister);
        reg = (TextView) findViewById(R.id.txtRegisterNow);
        phone = (EditText) findViewById(R.id.etPhone);
        pw = (EditText) findViewById(R.id.etPw);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                if (dbHandler.loginUser(phone.getText().toString(), pw.getText().toString())){
                    Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    phone.setText(null);
                    pw.setText(null);
                }

                else{
                    Toast.makeText(Login.this, "Invalid User Input", Toast.LENGTH_SHORT).show();
                    phone.setText(null);
                    pw.setText(null);
                }

            }
        });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);

            }
        });



    }
}