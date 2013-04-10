package com.appsolve.acimprayers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DataBaseHelper extends SQLiteOpenHelper{
	
    public static final String DEBUG_TAG = "acimParyers";
	
    int id = 0;
    //The Android's default system path for the application database.
    private static String DB_PATH = "/data/data/com.appsolve.acimprayer/databases/";
 
    // Data Base name
    private static String DB_NAME = "prayers.db";
 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;
 
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {
 
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
    }	
 
  /**
     * Creates a empty database on the system and rewrites it with the data base loaded in assets.
     * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
    	SQLiteDatabase db_Read = null;
    	
    	if(dbExist){
    		//do nothing - database already exist
    	}else{
     		//By calling this method an empty database will be created into the default system path
            //of the application and overwrite it with the database loaded into assets.
        	db_Read = this.getReadableDatabase();
        	db_Read.close(); 
        	try { 
    			copyDataBase(); 
    		} catch (IOException e) { 
        		throw new Error("Error copying database"); 
        	}
    	} 
    }
 
    
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){ 
    	SQLiteDatabase checkDB = null; 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE); 
    	}catch(SQLiteException e){ 
    		//database does't exist yet.
    	} 
    	if(checkDB != null){ 
    		checkDB.close(); 
    	} 
    	return checkDB != null ? true : false;
    }
    
 
    /**
     * Copies your database from the local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open the local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close(); 
    }
 
	//Opens the database    
    public void openDataBase() throws SQLException{
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
 
    //Closes the database in synchronized mode
    @Override
	public synchronized void close() { 
    	    if(myDataBase != null)
    		    myDataBase.close();
    	    super.close();
	}
 
    
	@Override
	public void onCreate(SQLiteDatabase db) { 
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
 
	
	/**
	 * Does a query on the database and returns
	 * the total number of entries in the WordMaster table
	 *  
	 * <p>
	 *
	 * @param  		no parameters passed
	 * @return      number of word in the data base
	 * @see         rawQuery
	 */  
	public int getCount(String table)	{
		int totalEntries;
	    Cursor cursor = myDataBase.rawQuery("SELECT COUNT(Quote) FROM "+table, null);
	            if(cursor.moveToFirst()) {
	            	totalEntries = cursor.getInt(0);
	            }
            	totalEntries = cursor.getInt(0);
            	if (cursor!=null){
            	    cursor.close();
            	}
            	return totalEntries;
	}
	

	
	/**
	 * Does a query on the database and returns
	 * the total number of entries in the WordMaster table
	 *  
	 * <p>
	 *
	 * @param  		no parameters passed
	 * @return      string with a ramdom word from the database
	 * @see         getAllEntries, rawQuery, Random
	 */  	
	public String getRandomEntry()	{
		String word;
		//id = getAllEntries();
    	
		Random random = new Random();
		int rand = random.nextInt(getCount("WordMaster")); //Generates a random number int the range [0-total number of entries] 
		if(rand == 0)
			++rand;
	    Cursor cursor = myDataBase.rawQuery("SELECT Word FROM WordMaster WHERE _id = " + rand, null);
            if(cursor.moveToFirst()) {
            	word = cursor.getString(0);
            }
    	word = cursor.getString(0);
    	if (cursor!=null){
    	    cursor.close();
    	}
    	return word;
	}

	
	/**
	 * Returns the definition for the word
	 * passed as a parameter
	 *  
	 * <p>
	 *
	 * @param  		string with a word
	 * @return      string the word definition
	 * @see         rawQuery
	 */  	
	public String getWordDef(String word) {
		String wordDef;
	    Cursor cursor = myDataBase.rawQuery("SELECT Meaning FROM WordMaster WHERE Word = '" + word + "'", null);
        if(cursor.moveToFirst()) {
        	wordDef = cursor.getString(0);
        }
        wordDef = cursor.getString(0);
    	if (cursor!=null){
    	    cursor.close();
    	}
    	return wordDef;
	}

	/**
	 * Reads the scores from the database table Scores
	 * and loads it to an ArrayList<ArrayList<String>> and returns it
	 *  
	 * <p>
	 *
	 * @param  		string with the type of sort
	 * @return      ArrayList<ArrayList<String>> results with a list of the scores
	 * @see         rawQuery
	 */  		
	public ArrayList<String> getTitles(String table) {
		ArrayList<String> results = new ArrayList<String>();
		Cursor cursor;
    	
		


	
		   cursor = myDataBase.rawQuery("SELECT Title FROM "+table, null);   
	
		   if(cursor.moveToFirst()) {
		       do {
		           results.add(cursor.getString(0)); //Strores titles into results
		       } while(cursor.moveToNext());
		   }
	       if(cursor != null && !cursor.isClosed())
	          cursor.close();

			
    	return results;
	}	
	
	public ArrayList<String> getNeeds() {
		ArrayList<String> results = new ArrayList<String>();
		Cursor cursor;
 
		   cursor = myDataBase.rawQuery("SELECT Need FROM Needs", null);   
	
		   if(cursor.moveToFirst()) {
		       do {
		           results.add(cursor.getString(0)); //Strores titles into results
		       } while(cursor.moveToNext());
		   }
	       if(cursor != null && !cursor.isClosed())
	          cursor.close();

			
    	return results;
	}	
	
	public String getEntrie(String prayerID) {
		//String wordID =  "";
		String result = null;
		Cursor cursor;
		int i = 0;
		
	   cursor = myDataBase.rawQuery("SELECT Prayer FROM PrayerMaster WHERE _id = '" + prayerID + "'", null);

        	   
        	   if(cursor.moveToFirst()) {
        	       do
        	       {
        	           result = cursor.getString(0); 
        	            Log.d(DEBUG_TAG, "result: "+result);
        	           i++;
        	       }while(cursor.moveToNext());
        	       if(cursor != null && !cursor.isClosed())
        	          cursor.close();
        	   }
    	       if(cursor != null && !cursor.isClosed())
     	          cursor.close();
		return result;
	}	
	
	
	

	/**
	 * Reads the scores from the database table Scores
	 * and loads it to an ArrayList<ArrayList<String>> and returns it
	 *  
	 * <p>
	 *
	 * @param  		string with the type of sort
	 * @return      ArrayList<ArrayList<String>> results with a list of the scores
	 * @see         rawQuery
	 */  		
	public ArrayList<ArrayList<String>> getScores(String sortType) {
		Cursor cursor;
    	
	   ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
	   cursor = myDataBase.rawQuery("SELECT * FROM Scores ORDER BY Score DESC", null);   	   
	   if(cursor.moveToFirst()) {
	       do {
	           ArrayList<String> recipe = new ArrayList<String>();// Stores the 2nd and 3th fields of each entry
	           recipe.add(cursor.getString(2)); // Stores the date
	           recipe.add(Integer.toString(cursor.getInt(3))); // Strores the score
	           if (sortType.equals("date")) 
	        	   recipe.add("0"); // Stores "0" into the 3th will be used for the rank position
	           results.add(recipe); //Strores recipe into results
	       } while(cursor.moveToNext());
	       //if(cursor != null && !cursor.isClosed())
	          //cursor.close();
	   }
       if(cursor != null && !cursor.isClosed())
          cursor.close();
    	return results;
	}
	
	/**
	 * Adds a score to the database table Scores
	 *  
	 * <p>
	 *
	 * @param  		string with the currente date and time, string with the number of question correctly answered 
     * @return      no return value
	 * @see         execSQL
	 */  		
	public void addQuote(String quote ) {
		myDataBase.execSQL("INSERT INTO QuoteSaved (Quote) VALUES ('"+quote+"') ");
	}
	
	
	/**
	 * Deletes all the entries to the database table Scores
	 *  
	 * <p>
	 *
	 * @param  		no parameter passed 
     * @return      no return value
	 * @see         delete
	 */ 
	public void deleteQuote(String quote) {	
		myDataBase.execSQL("DELETE FROM QuoteSaved WHERE Quote = '" + quote + "'");
	}

}
