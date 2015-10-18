package com.csc394.gsp.gradschoolplanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class login_activity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        final Button logonButton = (Button) findViewById(R.id.logonButton);
        final EditText username = (EditText) (findViewById(R.id.loginusername));
        final EditText pass = (EditText) (findViewById(R.id.loginpass));

        registerButton.setOnClickListener(new View.OnClickListener() {
        //this is supposed to send username and password for authentication
            public void onClick(View v) {
                String text = username.getText().toString()+" "+pass.getText().toString();
                Toast tpo = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                tpo.show();


            }
        });

        logonButton.setOnClickListener(new View.OnClickListener() {
            //this is supposed to send username and password for authentication
            public void onClick(View v) {
                String text = username.getText().toString()+" "+pass.getText().toString();
                Toast tpo = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                tpo.show();
                //if logon successful go to mainactivity
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
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
