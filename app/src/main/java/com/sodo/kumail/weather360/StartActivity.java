package com.sodo.kumail.weather360;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class StartActivity extends ActionBarActivity implements View.OnClickListener {

    public static String selected_city=null;

    EditText editText;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        editText= (EditText) findViewById(R.id.selected_city);
        button= (Button) findViewById(R.id.selected_city_button);
        button.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {


       if(editText.getText().toString()!=null)
       {
           selected_city=editText.getText().toString();
           startActivity(new Intent(StartActivity.this,MainActivity.class));
       }
        else
       {
           showDialog("Hold On Man!!.Enter the city first");
       }



    }
    public static void showDialog(String message)
    {
        final AlertDialog.Builder builder= new AlertDialog.Builder(MyApplication.getMyApplication().getApplicationContext());
        builder.setTitle("Alert");
        builder.setMessage(message);
        builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
