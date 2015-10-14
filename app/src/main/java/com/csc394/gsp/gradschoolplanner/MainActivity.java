package com.csc394.gsp.gradschoolplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    ExpandableCourseAdapter adapter;
    ExpandableListView view;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (ExpandableListView) findViewById(R.id.course_list);

        prepareListData();

        adapter = new ExpandableCourseAdapter(this, listDataHeader, listDataChild);

        view.setAdapter(adapter);
        view.expandGroup(0);

        view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(MainActivity.this, CourseInfo.class);
                MainActivity.this.startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //go to appropriate activities if selected
        if (id == R.id.action_settings) {
            //open settings
            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.logon) {
            //open settings
            Intent intent = new Intent(getApplicationContext(),login_activity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild  = new HashMap<String, List<String>>();

        listDataHeader.add("Fall 2015");
        listDataHeader.add("Winter 2016");
        listDataHeader.add("Spring 2016");

        listDataHeader.add("Fall 2016");
        listDataHeader.add("Winter 2017");
        listDataHeader.add("Spring 2017");

        listDataHeader.add("Fall 2017");


        List<String> quarter1 = new ArrayList<String>();
        List<String> quarter2 = new ArrayList<String>();
        List<String> quarter3 = new ArrayList<String>();
        List<String> quarter4 = new ArrayList<String>();
        List<String> quarter5 = new ArrayList<String>();
        List<String> quarter6 = new ArrayList<String>();
        List<String> quarter7 = new ArrayList<String>();

        quarter1.add("CSC 423 - Data Analysis and Regression");
        quarter1.add("CSC 425 - Time Series Analysis and Forecasting");

        quarter2.add("CSC 431 - Scientific Computing");
        quarter2.add("CSC 485 - Numerical Analysis");

        quarter3.add("CSC 521 - Monte Carlo Algorithms");
        quarter3.add("ACC 500 - Financial Accounting");

        quarter4.add("ECO 555 - Economics for Decision-Making");
        quarter4.add("FIN 555 - Financial Management");

        quarter5.add("FIN 523 - Investment Analysis");
        quarter5.add("FIN 525 - Portfolio Management");

        quarter6.add("FIN 562 - Risk Management");
        quarter6.add("FIN 662 - Derivatives Valuation");

        quarter7.add("CSC 559 - Software Engineering for Financial Markets");

        listDataChild.put(listDataHeader.get(0), quarter1);
        listDataChild.put(listDataHeader.get(1), quarter2);
        listDataChild.put(listDataHeader.get(2), quarter3);
        listDataChild.put(listDataHeader.get(3), quarter4);
        listDataChild.put(listDataHeader.get(4), quarter5);
        listDataChild.put(listDataHeader.get(5), quarter6);
        listDataChild.put(listDataHeader.get(6), quarter7);
        listDataChild.put(listDataHeader.get(6), quarter7);

    }

}
