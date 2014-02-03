package com.event;

import java.util.ArrayList;

import static com.event.Constants.EXTRA_MESSAGE;
import static com.event.Constants.LIST_EDIT;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressLint("InlinedApi")
public class ListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		//query from db:
		DatabaseHandler db = new DatabaseHandler(this);
		ArrayList<Event> allEvents = db.getAllEvents();
		db.close();
		// table layout handler
		TableLayout table = (TableLayout)findViewById(R.id.list_table);
		
		/*
		 * add each event in all events to table layout
		 */
		Log.d("Tablelayout:", "add to table");
		for(Event event: allEvents){
	        TableRow tableRow = new TableRow(getApplicationContext());
	        tableRow.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	        
	        String[] columns = event.toString().split(";");
	        
	        for (int j = 0; j < columns.length; j++) {             
	            final String col = columns[j];                                 
	            TextView columsView = new TextView(getApplicationContext());
	            columsView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
	            columsView.setTextColor(Color.parseColor("#000000"));
	            columsView.setText(String.format("%7s", col));
	            tableRow.addView(columsView);  

	        }
	        table.addView(tableRow);
	        
	        tableRow.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					TableRow row = (TableRow)v;
					TextView idColumn = (TextView)row.getChildAt(0);
					
					Intent intent = new Intent(v.getContext(), AddEditActivity.class);
			        String message = LIST_EDIT;
			        int id = Integer.parseInt(idColumn.getText().toString().trim());
			    	intent.putExtra(EXTRA_MESSAGE, message);
			    	intent.putExtra("ID", id);
			        startActivity(intent);
				}
			});
               
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
