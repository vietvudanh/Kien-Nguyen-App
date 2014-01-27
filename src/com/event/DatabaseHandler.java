package com.event;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.provider.BaseColumns._ID;
import static com.event.Constants.DB_NAME;
import static com.event.Constants.DB_TABLE_NAME;
import static com.event.Constants.DB_VERSION;
import static com.event.Constants.DB_COL_AMOUNT;
import static com.event.Constants.DB_COL_DESCRIPTION;
import static com.event.Constants.DB_COL_NAME;
import static com.event.Constants.DB_COL_PLACE;
import static com.event.Constants.DB_COL_TIME;

public class DatabaseHandler extends SQLiteOpenHelper{

	public DatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("ON CREATE", "CREATE");
		String query = "CREATE TABLE " + DB_TABLE_NAME + "("
				+ _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ DB_COL_NAME 			+ " TEXT " 		+ ", "
				+ DB_COL_TIME 			+ " INT " 	+ ", " 
				+ DB_COL_DESCRIPTION 	+ " TEXT "		+ ", "
				+ DB_COL_PLACE 			+ " TEXT "		+ ", "
				+ DB_COL_AMOUNT 		+ " DOUBLE "
				+ ");";
		db.execSQL(query);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
 
        // Create tables again
        onCreate(db);
		
	}
	/*
	 * insert Event
	 * @param: Event
	 * @return: 0/1
	 */
	public void insert(Event event){
		Log.d("Insert:","insert called");
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(DB_COL_NAME, event.getName()); 
	    values.put(DB_COL_TIME, Utils.df.format(event.getDate()));
	    values.put(DB_COL_DESCRIPTION, event.getDescription());
	    values.put(DB_COL_PLACE, event.getPlace());
	    values.put(DB_COL_AMOUNT, event.getAmount());
	    Log.d("Values:", values.toString());
	 
	    // Inserting Row
	    db.insert(DB_TABLE_NAME, null, values);
	    db.close(); // Closing database connection
//	    String query = "INSERT INTO " + DB_TABLE_NAME + " (" 
//	    		+ DB_COL_NAME + ", " 
//				+ DB_COL_TIME + ", "
//				+ DB_COL_DESCRIPTION + ", " 
//				+ DB_COL_PLACE + ", " 
//				+ DB_COL_AMOUNT 
//				+ ") VALUES (" 
//				+ "`" + event.getName() + "`" + ", "
//				+ "`" + Utils.df.format(event.getDate()) + "`" + ", "
//				+ "`" + event.getDescription() + "`" + ", "
//				+ "`" + event.getPlace() + "`" + ", "
//				+ event.getAmount()
//				+ ");";
//	    Log.d("query:", query);
//	    db.execSQL(	query);
	}
	
	/*
	 * getAllEvents()
	 * @param:
	 * @return: all events
	 */
	public ArrayList<Event> getAllEvents(){
		ArrayList<Event> list = new ArrayList<Event>();
		
		// Select All Query
	    String selectQuery = "SELECT  * FROM " + DB_TABLE_NAME;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            Event event = new Event(
	            					cursor.getInt(1),
	            					cursor.getString(2),
	            					new Date(cursor.getInt(3)),
	            					cursor.getString(4),
	            					cursor.getString(5),
	            					cursor.getDouble(6)
	            					);
	            list.add(event);
	        } while (cursor.moveToNext());
	    }
		
		return list;
	}

	/*
	 * getEvent
	 * get an Event
	 * @param:  id
	 * @return: event
	 */
	public Event getEvent(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String[] queryString = {_ID, DB_COL_NAME, DB_COL_TIME, DB_COL_DESCRIPTION, DB_COL_PLACE, DB_COL_AMOUNT};
		
	    Cursor cursor = db.query(DB_TABLE_NAME, queryString, _ID + "= ", new String[]{String.valueOf(id)}, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
		
	    Event event = new Event(
	    						cursor.getInt(1),
	    						cursor.getString(2),
	    						new Date(cursor.getInt(3)),
	    						cursor.getString(4),
	    						cursor.getString(5),
	    						cursor.getDouble(2));
	    return event;
	}
	
}
