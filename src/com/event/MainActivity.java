package com.event;

import static com.event.Constants.MAIN_ADD;
import static com.event.Constants.EXTRA_MESSAGE;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setTitle("Event App");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /*
     * chage activity to add/edit
     */
    public void mainAddEdit(View view){
    	Intent intent = new Intent(this, AddEditActivity.class);
        String message = MAIN_ADD;
    	intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    
    /*
     * chage activity to add/edit
     */
    public void mainList(View view){
    	Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
    
    /*
     * dropDB
     */
    public void dropDB(View view){
    	
    	//confirmation popup
    	new AlertDialog.Builder(this)
        .setMessage("Are you sure you want delete your data?")
        .setCancelable(false)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	DatabaseHandler dbHandler = new DatabaseHandler(MainActivity.this);
            	dbHandler.dropDB();
            }
        })
        .setNegativeButton("No", null)
        .show();
    	
    }
    
}
