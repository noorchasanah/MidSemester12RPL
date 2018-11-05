package com.example.rplrus24.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    private int MAIN_ACTIVITY_REQUEST_CODE;
    public static final String isi = "isi";
    EditText edtemail, edtpassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtemail = (EditText) findViewById(R.id.edtemail);
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtemail.getText().toString().equals("noor") && edtpassword.getText().toString().equals("123")) {
                    ;
                    Toast.makeText(getApplicationContext(), "berhasil login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, recyclerview.class);
                    String username = edtemail.getText().toString();
                    sharedpreferences = getSharedPreferences("isi", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("username", username);
                    editor.commit();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "login gagal", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data2){
            sharedpreferences = sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
            boolean stateValue = sharedpreferences.getBoolean("", false);
            if (requestCode == MAIN_ACTIVITY_REQUEST_CODE) {
                if (!stateValue) {
                    finish();
                } else {
                    updateLoginState(false);
                    super.onActivityResult(requestCode, resultCode, data2);
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data2);
            }
        }
        private void updateLoginState(boolean b){
        }
    }