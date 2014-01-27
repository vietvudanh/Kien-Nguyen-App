package com.event;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class AddEditActivity extends Activity {

	/*
	 * Attributes
	 */
	EditText input_name;
	DatePicker input_date;
	TimePicker input_time;
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
		input_date 			= (DatePicker)findViewById(R.id.add_input_date);
		input_time 			= (TimePicker)findViewById(R.id.add_input_time);
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
		String date = String.valueOf(input_date.getMonth() + 1) + "/" 
						+ String.valueOf(input_date.getDayOfMonth()) + "/" 
						+ String.valueOf(input_date.getYear()) + " " 
						+ String.valueOf(input_time.getCurrentHour()) + ":"
						+ String.valueOf(input_time.getCurrentMinute());
	
		Log.d("Date:",date);
		Date date_in = new Date(date);
		
		
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
