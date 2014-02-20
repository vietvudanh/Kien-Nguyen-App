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
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressLint({ "InlinedApi", "NewApi" })
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
		
		// add heading row
		TableRow tableRowh = new TableRow(getApplicationContext());
        tableRowh.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        String[] header = {"ID", "Name", "Date", "Description", "Place", "Money"};
        for(int i = 0; i < header.length; i++){
        	TextView columsView = new TextView(getApplicationContext());
        	columsView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        	
        	// get TextView attribute from drawable/cell_header
            columsView.setBackground(getResources().getDrawable(R.drawable.cell_header) );
            columsView.setTypeface(null, Typeface.BOLD);
            columsView.setText(String.format("%7s", header[i]));
            columsView.setTextColor(Color.parseColor("#000000"));
            //columsView.setWidth(LIST_WIDTH[i]);
            //columsView.setHeight(LIST_HEIGHT);
            //columsView.setGravity(Gravity.TOP);
            
        	tableRowh.addView(columsView); 
        }
        table.addView(tableRowh);
        
		for(Event event: allEvents){
	        TableRow tableRow = new TableRow(getApplicationContext());
	        tableRow.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	        
	        String[] columns = event.toString().split(";");
	        
	        // add data rows
	        for (int j = 0; j < columns.length; j++) {             
	            final String col = columns[j];                                 
	            TextView columsView = new TextView(getApplicationContext());
	            columsView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
	            
	            // get TextView attribute from drawable/cell
	            columsView.setBackground(getResources().getDrawable(R.drawable.cell) );
	            columsView.setText(String.format("%7s", col));
	            columsView.setLines(1);
	            //columsView.setWidth(LIST_WIDTH[j]);
	            //columsView.setHeight(LIST_HEIGHT);
	            //columsView.setGravity(Gravity.TOP);
	            
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
			        finish();
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
