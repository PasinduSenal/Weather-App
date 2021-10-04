package com.anioncode.wheatheraplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anioncode.wheatheraplication.DataBase.DBHandler;

public class Register extends AppCompatActivity {

    EditText phone,name,dob,pw;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.btnRegister);
        phone = findViewById(R.id.etPhone);
        name = findViewById(R.id.etusername);
        dob = findViewById(R.id.etDob);
        pw = findViewById(R.id.etPw);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (phone.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Input the Phone", Toast.LENGTH_SHORT).show();
                } else if (name.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Input the Name", Toast.LENGTH_SHORT).show();
                }

                if (dob.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Input the Date of Birth", Toast.LENGTH_SHORT).show();
                }

                if (pw.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Input the Password", Toast.LENGTH_SHORT).show();
                } else {

                    DBHandler dbHelper = new DBHandler(getApplicationContext());
                    long newID = dbHelper.addUser(phone.getText().toString(), name.getText().toString(), dob.getText().toString(), pw.getText().toString());

                    Toast.makeText(Register.this, "User Added" + newID, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    phone.setText(null);
                    name.setText(null);
                    dob.setText(null);
                    pw.setText(null);
                }
            }
        });

    }

    }
