package com.csc394.gsp.gradschoolplanner;

/**
 * Created by Colin on 10/2/2015.
 */


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class login_activity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final Button button1 = (Button) findViewById(R.id.button);//register
        final Button button2 = (Button) findViewById(R.id.button2);//logon
        final EditText username = (EditText) (findViewById(R.id.loginusername));
        final EditText pass = (EditText) (findViewById(R.id.loginpass));

        button1.setOnClickListener(new View.OnClickListener() {
        //this is supposed to send username and password for authentication
            public void onClick(View v) {
                String text = username.getText().toString()+" "+pass.getText().toString();
                Toast tpo = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                tpo.show();



            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            //this is supposed to send username and password for authentication
            public void onClick(View v) {
                String text = username.getText().toString()+" "+pass.getText().toString();
                Toast tpo = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                tpo.show();


            }
        });
        username.setOnClickListener(new View.OnClickListener() {//clears text when clicked
            public void onClick(View v) {
                username.setText("");
            }


        });
        pass.setOnClickListener(new View.OnClickListener() {//clears text when clicked
            public void onClick(View v) {
                pass.setText("");
            }


        });

    }


    }
}
