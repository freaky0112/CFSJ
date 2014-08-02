package com.freaky.cfsj;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MyActivity extends Activity {

    private Spinner sprType;
    private ArrayAdapter adapterType;
    private EditText edtID;
    private Button btnQuery;
    private Button btnAdd;
    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        edtID=(EditText)findViewById(R.id.edtID);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnQuery=(Button)findViewById(R.id.btnQuery);
        sprType=(Spinner)findViewById(R.id.sprType);
        adapterType=ArrayAdapter.createFromResource(this,R.array.types,android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprType.setAdapter(adapterType);
        //sprType.setOnItemSelectedListener(new SpinnerXML);
        sprType.setVisibility(View.VISIBLE);
    }

    public void onClick_btnQuery(View view){
        //edtID.setText(sprType.getSelectedItem().toString()+edtID.getText());
        dbManager=new DBManager(MyActivity.this);
        dbManager.openDatabase();
        dbManager.closeDatabase();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
