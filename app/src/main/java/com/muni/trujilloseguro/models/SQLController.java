package com.muni.trujilloseguro.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {
	private DBhelper dbhelper;
	private Context ourcontext;
	private SQLiteDatabase database;

	public SQLController(Context c) {
		ourcontext = c;
	}

	public SQLController abrir() throws SQLException {
		dbhelper = new DBhelper(ourcontext);
		database = dbhelper.getWritableDatabase();
		return this;

	}

	public void close()
    {
		dbhelper.close();
	}

	public void insertDatos(String name) {
		ContentValues cv = new ContentValues();
		cv.put(DBhelper.MEMBER_NAME, name);
		database.insert(DBhelper.TABLE_MEMBER, null, cv);
	}

	public Cursor CargarDatos() {
		String[] allColumns = new String[] { DBhelper.MEMBER_ID,
				DBhelper.MEMBER_NAME };
		Cursor c = database.query(DBhelper.TABLE_MEMBER, allColumns, null,
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

}
