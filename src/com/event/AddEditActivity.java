package com.event;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEditActivity extends Activity {

	/*
	 * Attributes
	 */
	EditText input_name;
	EditText input_date;
	EditText input_time;
	EditText input_description;
	EditText input_place;
	EditText input_amount;
	Button button_save;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit);
		
		// init
		input_name 			= (EditText)findViewById(R.id.add_input_name);
		input_date 			= (EditText)findViewById(R.id.add_input_date);
		input_time 			= (EditText)findViewById(R.id.add_input_time);
		input_description 	= (EditText)findViewById(R.id.add_input_description);
		input_place 		= (EditText)findViewById(R.id.add_input_place);
		input_amount		= (EditText)findViewById(R.id.add_input_amount);
		button_save			= (Button)findViewById(R.id.add_button_save);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_edit, menu);
		return true;
	}
	
	/*
	 * insertDB
	 * @param:
	 * @return:
	 */
	public void insertDB(View view){
		//db
		DatabaseHandler db = new DatabaseHandler(this);
		
		Log.d("Insert:", "Insert contact");
		// change dd/MM/YYYY to MM/dd/YYYY format
		String[] dates = input_date.getText().toString().split("[\\/]");
		Log.d("Date number of split:", String.valueOf(dates.length)); 
		String date = dates[1] + "/" +dates[0] + "/"  + dates[2] + ' ' + input_time.getText().toString();
		Date date_in = new Date(date);
		Log.d("Date:",date_in.toString());
		
		db.insert(new Event(
							0,
							input_name.getText().toString(),
							date_in,
							input_description.getText().toString(),
							input_place.getText().toString(),
							Double.parseDouble(input_amount.getText().toString())
							));
		
	}

}
