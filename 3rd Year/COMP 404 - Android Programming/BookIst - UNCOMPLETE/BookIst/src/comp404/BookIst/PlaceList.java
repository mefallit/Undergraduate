package comp404.BookIst;

import java.sql.SQLException;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;

public class PlaceList extends ListActivity{
	private Dbase mDBHelper;
	private Cursor mCursor;
	
	protected void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.list);
		
		mDBHelper = new Dbase(this);
		try {
			mDBHelper.openDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fillData();
		getListView().setTextFilterEnabled(true);
		
	}
	
	private void fillData(){
		//get all rows from database
		mCursor = mDBHelper.getData()
	}
	
}
