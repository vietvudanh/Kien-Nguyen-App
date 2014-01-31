package com.event;

import java.util.ArrayList;

import android.R.color;
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
	         
	    }
//		/*
//		 * Scroll viewable
//		 */
//		ScrollView parentScrollView = (ScrollView)findViewById(R.id.list_scrollView);
//		HorizontalScrollView childScrollView = (HorizontalScrollView)findViewById(R.id.list_horizontalScrollView);
//		
//		parentScrollView.setOnTouchListener(new View.OnTouchListener() {
//
//	        @Override
//	        public boolean onTouch(View v, MotionEvent event) {
//	        Log.v("TOUCH", "PARENT TOUCH");
//
//	        findViewById(R.id.list_horizontalScrollView).getParent()
//	                .requestDisallowInterceptTouchEvent(false);
//	        return false;
//	    }
//	        
//		});
//	
//		childScrollView.setOnTouchListener(new View.OnTouchListener() {
//	
//		        @Override
//		        public boolean onTouch(View v, MotionEvent event) {
//		        Log.v("Touch", "CHILD TOUCH");
//	
//		        // Disallow the touch request for parent scroll on touch of  child view
//		        v.getParent().requestDisallowInterceptTouchEvent(true);
//		        return false;
//		    }
//		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
