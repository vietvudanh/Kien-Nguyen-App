package com.event;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns{

	/*
	 * Activity
	 */
	public static final String EXTRA_MESSAGE 	= "com.example.myfirstapp.MESSAGE";
	public static final String MAIN_ADD 		= "ADD";			// simply add(called from main)
	public static final String LIST_EDIT 		= "EDIT";			// edit event id at EDIT:id, need additional info here
	
	/*
	 * Database
	 */
	public static final int DB_VERSION				= 1;
	public static final String DB_NAME 				= "eventdb";
	public static final String DB_TABLE_NAME 		= "events";
	public static final String DB_COL_NAME 			= "name";
	public static final String DB_COL_TIME 			= "time";
	public static final String DB_COL_DESCRIPTION 	= "description";
	public static final String DB_COL_PLACE 		= "place";
	public static final String DB_COL_AMOUNT		= "amount";
	
	/*
	 * List view columns
	 */
	public static final String[] LIST_COLUMS = 	{ "ID", "Name", "Date", "Description", "Place", "Amount"};
	
}
