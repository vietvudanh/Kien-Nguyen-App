package com.event;

import java.util.ArrayList;

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
		Log.d("Database:", "CREATE");
		String query = "CREATE TABLE " + DB_TABLE_NAME + "("
				+ _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ DB_COL_NAME 			+ " TEXT " 		+ ", "
				+ DB_COL_TIME 			+ " INT8 " 		+ ", " 
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
		Log.d("DB.Insert: ","INSERT");
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(DB_COL_NAME, event.getName()); 
	    values.put(DB_COL_TIME, event.getDate().getTime());
	    values.put(DB_COL_DESCRIPTION, event.getDescription());
	    values.put(DB_COL_PLACE, event.getPlace());
	    values.put(DB_COL_AMOUNT, event.getAmount());
	   
	    // Inserting Row
	    db.insert(DB_TABLE_NAME, null, values);
	    db.close(); 
	}
	
	/*
	 * getAllEvents()
	 * @param:
	 * @return: all events
	 */
	public ArrayList<Event> getAllEvents(){
		
		Log.d("DB.getAll", "get all");
		ArrayList<Event> list = new ArrayList<Event>();
		
		// Select All Query
	    String selectQuery = "SELECT  * FROM " + DB_TABLE_NAME;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            Event event = new Event(
	            					cursor.getInt(0),
	            					cursor.getString(1),
	            					new MyDate(cursor.getLong(2)),
	            					cursor.getString(3),
	            					cursor.getString(4),
	            					cursor.getDouble(5)
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
		
		Log.d("DB.getEvent", "get");
		SQLiteDatabase db = this.getReadableDatabase();
		
		String[] queryString = {_ID, DB_COL_NAME, DB_COL_TIME, DB_COL_DESCRIPTION, DB_COL_PLACE, DB_COL_AMOUNT};
		
		String[] a = new String[]{String.valueOf(id)};
		
	    Cursor cursor = db.query(DB_TABLE_NAME, queryString, _ID + " =?", a, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
		
	    Event event = new Event(
	    						cursor.getInt(0),
	    						cursor.getString(1),
	    						new MyDate(cursor.getLong(2)),
	    						cursor.getString(3),
	    						cursor.getString(4),
	    						cursor.getDouble(5));
	    
	    return event;
	}
	
	/*
	 * updateEvent
	 * update an event row in DB
	 * @param: event to be updated
	 * @return: state: 0=failed, 1=success
	 */
	public int updateEvent(Event event){
				
		Log.d("DB.Update", "Update" + event.toString());
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(DB_COL_NAME, event.getName()); 
	    values.put(DB_COL_TIME, event.getDate().getTime());
	    values.put(DB_COL_DESCRIPTION, event.getDescription());
	    values.put(DB_COL_PLACE, event.getPlace());
	    values.put(DB_COL_AMOUNT, event.getAmount());
	 
	    // updating row
	    return db.update(DB_TABLE_NAME, values, _ID + " = ?",
	            new String[] { String.valueOf(event.getID()) });
	
	}
	
	/*
	 * deleteEvent
	 * delete an event row in DB
	 * @param: Event to be delete
	 */
	public void deleteEvent(int _id){
		
		Log.d("DB.Delete", "Delete");
		SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(DB_TABLE_NAME, _ID + " = ?",
	            new String[] { String.valueOf(_id) });
	    
	    db.close();
		
	}
	
}
