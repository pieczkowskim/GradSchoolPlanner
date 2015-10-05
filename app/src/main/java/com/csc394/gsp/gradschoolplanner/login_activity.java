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
                new communicator().execute("register",username.getText(),pass.getText());


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            //this is supposed to send username and password for authentication
            public void onClick(View v) {
                String text = username.getText().toString()+" "+pass.getText().toString();
                Toast tpo = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                tpo.show();
                new communicator().execute("logon", username.getText(), pass.getText());


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

    //handles sending commands to server
    public class communicator extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                //Toast t = Toast.makeText(getApplicationContext(), "connecting", Toast.LENGTH_SHORT);
                //t.show();
                InetAddress addr = InetAddress.getByName("heebie.ddns.net");
                Socket s = new Socket(addr,12333);
                PrintWriter output = new PrintWriter(s.getOutputStream(),true);
                BufferedReader br = new BufferedReader(( new InputStreamReader(s.getInputStream())));

                //sending text to server
                output.println(params[0]);//logon or register


                output.println(params[1] + "," + params[2]);//username,password



                output.flush();

            }
            catch(Exception e)
            {
//                Toast t = Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT);
//                t.show();
            }
            return null;
        }
    }
}
