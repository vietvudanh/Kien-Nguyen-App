package com.event;

import static com.event.Constants.MAIN_ADD;
import static com.event.Constants.LIST_EDIT;
import static com.event.Constants.EXTRA_MESSAGE;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TextView;

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
	Button button_delete;
	
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
		button_delete		= (Button)findViewById(R.id.add_button_delete);
		button_delete.setVisibility(View.GONE);
		
		/*
		 * check state to decide: add or edit
		 */
		Bundle extras = getIntent().getExtras();
		// edit
		if( extras.getString(EXTRA_MESSAGE).equals(LIST_EDIT) ){
			int _id = extras.getInt("ID");
			
			DatabaseHandler dbHandler = new DatabaseHandler(this);
			Event event =  dbHandler.getEvent(_id);
			
			input_name.setText(event.getName(), TextView.BufferType.EDITABLE);
			input_date.init(event.getDate().year, event.getDate().month, event.getDate().date, null);
			input_time.setCurrentHour(event.getDate().hour);
			input_time.setCurrentMinute(event.getDate().minute);
			input_description.setText(event.getDescription(), TextView.BufferType.EDITABLE);
			input_place.setText(event.getPlace(), TextView.BufferType.EDITABLE);
			input_amount.setText(String.valueOf(event.getAmount()), TextView.BufferType.EDITABLE);
			
			button_delete.setVisibility(View.VISIBLE);
			
			setTitle("Edit event");
			
		}
		else{
			setTitle("Add event");
		}
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
		
		/*
		 * check state to decide: add or edit
		 */
		Bundle extras = getIntent().getExtras();
		//  add
		if( extras.getString(EXTRA_MESSAGE).equals(MAIN_ADD) ){
			
			//db
			DatabaseHandler db = new DatabaseHandler(this);
			
			//
			MyDate date = new MyDate(input_date.getYear(), input_date.getMonth(), input_date.getDayOfMonth(), input_time.getCurrentHour(), input_time.getCurrentMinute(), 0);
			
			Log.d("Month/hour: ", input_date.getMonth()+"/" + input_time.getCurrentHour());
			Log.d("Add: ", date.toString());
			db.insert(new Event(
								0,
								input_name.getText().toString(),
								date,
								input_description.getText().toString(),
								input_place.getText().toString(),
								Double.parseDouble(input_amount.getText().toString())
								));
			
		} else 
		// edit
		if(extras.getString(EXTRA_MESSAGE).equals(LIST_EDIT)){
			//db			
			int _id = extras.getInt("ID");
			
			DatabaseHandler db = new DatabaseHandler(this);
			MyDate date = new MyDate(input_date.getYear(), input_date.getMonth(), input_date.getDayOfMonth(), input_time.getCurrentHour(), input_time.getCurrentMinute(), 0);

			Log.d("Add: ", date.toString());
			db.updateEvent(new Event(
								_id,
								input_name.getText().toString(),
								date,
								input_description.getText().toString(),
								input_place.getText().toString(),
								Double.parseDouble(input_amount.getText().toString())
								));
			
			// back after edit
			onBackPressed();
		}
		else{
			return;
		}
	
	}
	
	public void deleteEvent(View view){
		Bundle extras = getIntent().getExtras();
		
		if(extras.getString(EXTRA_MESSAGE).equals(LIST_EDIT)){
			//db			
			int _id = extras.getInt("ID");
			DatabaseHandler db = new DatabaseHandler(this);
			
			db.deleteEvent(_id);
			db.close();
			
			button_delete.setVisibility(View.GONE);
			onBackPressed();
		}
	}
	

}
