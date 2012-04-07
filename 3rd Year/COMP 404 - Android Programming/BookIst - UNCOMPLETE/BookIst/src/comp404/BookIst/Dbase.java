package comp404.BookIst;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
public class Dbase extends SQLiteOpenHelper {
	
	private static String DB_PATH = "";
	private static final String DB_NAME = "places.sqlite";
	private SQLiteDatabase db;
	private final Context context;
	
	private static Dbase dbManager;
	 
	public Dbase(Context context){
		 super(context, DB_NAME, null, 1);
	     this.context = context;
	     DB_PATH = "/data/data/" + context.getApplicationContext().getPackageName() + "/databases/";
	      // The Android's default system path of your application database is
			// "/data/data/mypackagename/databases/"
	}

	// context -> dbManager
	//getting instance
	public static synchronized Dbase getDBaseInstance(Context context){
		if (dbManager == null){
			dbManager = new Dbase(context);
		}
		return dbManager;
	}
	
	//creating empty database on our system and rewrite ours on it.
	public void createDatabase() throws IOException{
		boolean dbExist = checkDB();
		
		if(dbExist){
			//do nothing - database already exist
		} else {
			// an empty DB will be created into the default path of our app
			//then we will overwrite that db with ours
			this.getReadableDatabase();
			try{
				copyDB();
			} catch(IOException e){
				throw new Error("Error copying database");
			}
		}
	}
	
	//Transfering our data by transferring bytestream
	private void copyDB() throws IOException{
		// Open our local db as the input stream
		
		InputStream input = context.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		//Open the empty db as the output stream
		OutputStream output = new FileOutputStream(outFileName);
		//transfer bytes from the input to the output
		
		byte[] buffer = new byte[1024];
		int length;
		while ((length = input.read(buffer)) > 0) {
			output.write(buffer, 0, length);
		}
		    // Close the streams
		output.flush();
		output.close();
		input.close();
		
	}

	//Now we will check if there is database already or not. We should avoid re-copying file each time when we open app
	private boolean checkDB() {
		SQLiteDatabase checkDB = null;
		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		} catch(SQLiteException e) {
			// database does't exist yet.
		}
		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}

	//Open the database
	public void openDB() throws SQLException{
		String path = DB_PATH + DB_NAME;
		db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
	}
	
	public synchronized void close() {
		if (db != null)
			db.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	// Now we will write our functions
	
	//getting data (name, lag, lat) according to type
	public ArrayList<String> getData(Integer type){
		ArrayList<String> placelist = new ArrayList<String>();
		Cursor result = db.query("Place",new String[] {"id,name,lag,lat"}, "type = " + type ,null, null, null, null);
		while(result.moveToNext())
			placelist.add(result.getString(result.getColumnIndex("id")));
		return placelist;	
	}
	
	
	
	

}
