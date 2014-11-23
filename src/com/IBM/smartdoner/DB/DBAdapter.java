package com.IBM.smartdoner.DB;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	static final String KEY_ROWID = "id";

	static final String KEY_NAME = "name";
	static final String KEY_ADDRESS1 = "add1";
	static final String KEY_ADDRESS2 = "add2";
	static final String KEY_AREA = "area";
	static final String KEY_PIN = "pin";
	static final String KEY_STATE = "state";
	static final String KEY_COUNTRY = "country";
	static final String KEY_EMAIL = "email";
	static final String KEY_PHONE = "phone";
	static final String KEY_PAN = "pan";
	static final String KEY_USERTYPE = "usertype";

	static final String KEY_USERID = "userID";
	static final String KEY_DONATION_TYPE = "donationType";
	static final String KEY_QUANTITY = "quantity";
	static final String KEY_VALIDITY = "validity";
	static final String KEY_ADDRESS = "address";
	static final String KEY_NOTE = "note";

	static final String TAG = "DBAdapter";

	static final String DATABASE_NAME = "db_smartDonor";

	static final String DATABASE_TABLE = "table_user";

	static final String DATABASE_TABLE_TRANSACTION = "table_transaction";

	static final int DATABASE_VERSION = 1;

	static final String TABLE_TEXT_QUERY = "create table "
			+ DATABASE_TABLE
			+ " (id integer primary key autoincrement,"
			+ "name text not null, add1 text not null, add2 text,"
			+ "area text not null, pin text not null, state text not null, country text not null,"
			+ "email text not null, phone text not null,  pan text not null,  usertype text not null);";

	static final String TABLE_TEXT_QUERY_TRANSACTION = "create table "
			+ DATABASE_TABLE_TRANSACTION
			+ " (id integer primary key autoincrement,"
			+ "userID text not null, donationType text not null, quantity text not null,"
			+ "validity text not null, address text not null, note text);";

	final Context context;

	DatabaseHelper DBHelper;

	SQLiteDatabase db;

	String order_by = KEY_ROWID;

	String[] col_text = new String[] { KEY_NAME, KEY_ADDRESS1, KEY_ADDRESS2,
			KEY_AREA, KEY_PIN, KEY_STATE, KEY_COUNTRY, KEY_EMAIL, KEY_PHONE,
			KEY_PAN, KEY_USERTYPE };

	String[] col_text_transaction = new String[] { KEY_USERID,
			KEY_DONATION_TYPE, KEY_QUANTITY, KEY_VALIDITY, KEY_ADDRESS,
			KEY_NOTE };

	ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();

	public DBAdapter(Context ctx) {

		this.context = ctx;

		DBHelper = new DatabaseHelper(context);

	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {

			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			try {
				db.execSQL(TABLE_TEXT_QUERY);
				db.execSQL(TABLE_TEXT_QUERY_TRANSACTION);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");

			db.execSQL("DROP TABLE IF EXISTS diary");

			onCreate(db);
		}
	}

	// ---opens the database---
	public DBAdapter open() throws SQLException {

		DBHelper = new DatabaseHelper(context);

		db = DBHelper.getWritableDatabase();

		return this;

	}

	// ---closes the database---
	public void close() {

		DBHelper.close();

	}

	// /////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////

	// ---insert a contact into the database---//
	public long insertValueUser(String name, String add1, String add2,
			String area, String pin, String state, String country,
			String email, String phone, String pan, String usertype) {

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_NAME, name);

		initialValues.put(KEY_ADDRESS1, add1);

		initialValues.put(KEY_ADDRESS2, add2);

		initialValues.put(KEY_AREA, area);

		initialValues.put(KEY_PIN, pin);

		initialValues.put(KEY_STATE, state);

		initialValues.put(KEY_COUNTRY, country);

		initialValues.put(KEY_EMAIL, email);

		initialValues.put(KEY_PHONE, phone);

		initialValues.put(KEY_PAN, pan);

		initialValues.put(KEY_USERTYPE, usertype);

		return db.insert(DATABASE_TABLE, null, initialValues);

	}
	
	
	
	
	// ---insert a contact into the database---//
		public long insertValueTransaction(String userID, String donationType, String quantity,
				String validity, String address, String note) {

			ContentValues initialValues = new ContentValues();

			initialValues.put(KEY_USERID, userID);

			initialValues.put(KEY_DONATION_TYPE, donationType);

			initialValues.put(KEY_QUANTITY, quantity);

			initialValues.put(KEY_VALIDITY, validity);

			initialValues.put(KEY_ADDRESS, address);

			initialValues.put(KEY_NOTE, note);

			return db.insert(DATABASE_TABLE_TRANSACTION, null, initialValues);

		}

	public ArrayList<HashMap<String, String>> getRecords() {
		// TODO Auto-generated method stub

		Cursor c = db.query(DATABASE_TABLE, col_text, null, null, null, null,
				order_by + " DESC");

		// int id_pos = c.getColumnIndex(KEY_ROWID);

		int name_pos = c.getColumnIndex(KEY_NAME);

		int add1_pos = c.getColumnIndex(KEY_ADDRESS1);

		int add2_pos = c.getColumnIndex(KEY_ADDRESS2);

		int area_pos = c.getColumnIndex(KEY_AREA);

		int pin_pos = c.getColumnIndex(KEY_PIN);

		int state_pos = c.getColumnIndex(KEY_STATE);

		int country_pos = c.getColumnIndex(KEY_COUNTRY);

		int email_pos = c.getColumnIndex(KEY_EMAIL);

		int phone_pos = c.getColumnIndex(KEY_PHONE);

		int pan_pos = c.getColumnIndex(KEY_PAN);

		int usertype_pos = c.getColumnIndex(KEY_USERTYPE);

		for (c.moveToFirst(); !(c.isAfterLast()); c.moveToNext()) {

			String name = c.getString(name_pos);

			String add1 = c.getString(add1_pos);

			String add2 = c.getString(add2_pos);

			String area = c.getString(area_pos);

			String pin = c.getString(pin_pos);

			String state = c.getString(state_pos);

			String country = c.getString(country_pos);

			String email = c.getString(email_pos);

			String phone = c.getString(phone_pos);

			String pan = c.getString(pan_pos);

			String usertype = c.getString(usertype_pos);

			HashMap<String, String> map = new HashMap<String, String>();

			// adding each child node to HashMap key => value

			map.put(KEY_NAME, name);

			map.put(KEY_ADDRESS1, add1);

			map.put(KEY_ADDRESS2, add2);

			map.put(KEY_AREA, area);

			map.put(KEY_PIN, pin);

			map.put(KEY_STATE, state);

			map.put(KEY_COUNTRY, country);

			map.put(KEY_EMAIL, email);

			map.put(KEY_PHONE, phone);

			map.put(KEY_PAN, pan);

			map.put(KEY_USERTYPE, usertype);

			// adding HashList to ArrayList
			userList.add(map);
		}
		c.close();

		return userList;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<HashMap<String, String>> getRecordsTransaction() {
		// TODO Auto-generated method stub

		Cursor c = db.query(DATABASE_TABLE_TRANSACTION, col_text_transaction, null, null, null, null,
				order_by + " DESC");

		// int id_pos = c.getColumnIndex(KEY_ROWID);

		int userID_pos = c.getColumnIndex(KEY_USERID);

		int donationType_pos = c.getColumnIndex(KEY_DONATION_TYPE);

		int quantity_pos = c.getColumnIndex(KEY_QUANTITY);

		int validity_pos = c.getColumnIndex(KEY_VALIDITY);

		int address_pos = c.getColumnIndex(KEY_ADDRESS);

		int state_pos = c.getColumnIndex(KEY_NOTE);

		for (c.moveToFirst(); !(c.isAfterLast()); c.moveToNext()) {
			
			
			

			String userID = c.getString(userID_pos);

			String donationType = c.getString(donationType_pos);

			String quantity = c.getString(quantity_pos);

			String validity = c.getString(validity_pos);

			String address = c.getString(address_pos);

			String note = c.getString(state_pos);

			HashMap<String, String> map = new HashMap<String, String>();

			// adding each child node to HashMap key => value
			
			map.put(KEY_USERID, userID);

			map.put(KEY_DONATION_TYPE, donationType);

			map.put(KEY_QUANTITY, quantity);

			map.put(KEY_VALIDITY, validity);

			map.put(KEY_ADDRESS, address);

			map.put(KEY_NOTE, note);

			// adding HashList to ArrayList
			userList.add(map);
		}
		c.close();

		return userList;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public String getUserType(String phone) {

		Cursor cursor = null;
		String userType = "";
		try {

			cursor = db.rawQuery("SELECT usertype FROM table_user WHERE phone="
					+ phone, null);

			if (cursor.getCount() > 0) {

				cursor.moveToFirst();
				userType = cursor.getString(cursor.getColumnIndex("usertype"));
			}

			return userType;
		} finally {

			cursor.close();
		}
	}

	// ---deletes a particular contact---
	public boolean deleteContact1(String id) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + id, null) > 0;
	}

	// ---deletes a table contact---
	public void deletetable() {
		try {
			db.execSQL("DROP TABLE IF EXISTS '" + DATABASE_TABLE + "'");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
