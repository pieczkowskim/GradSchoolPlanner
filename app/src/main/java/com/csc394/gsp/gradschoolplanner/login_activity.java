package com.csc394.gsp.gradschoolplanner;

/**
 * Created by Colin on 10/2/2015.
 */


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class login_activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final Button button1 = (Button) findViewById(R.id.button);
        final Button button2 = (Button) findViewById(R.id.button2);
        final EditText username = (EditText) (findViewById(R.id.loginusername));
        final EditText pass = (EditText) (findViewById(R.id.loginpass));

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = username.getText().toString()+" "+pass.getText().toString();
                Toast t = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                t.show();
            }
        });
        username.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                username.setText("");
            }


        });
        pass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pass.setText("");
            }


        });

    }
}
