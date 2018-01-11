package abohawya.com.mcclabtestproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import abohawya.com.mcclabtestproject.User;

/**
 * Created by USER15 on 1/11/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "userInfo";
    // Users table name
    private static final String TABLE_USERS = "users";

    // Users Table Columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PH_NO = "phone_number";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + KEY_NAME + " TEXT,"+ KEY_AGE +" INTEGER,"+
                KEY_EMAIL +" TEXT,"+ KEY_PH_NO + " TEXT PRIMARY KEY" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        // Create tables again
        onCreate(db);
    }

    public void addContact(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_EMAIL, user.getName());
        values.put(KEY_PH_NO, user.getPhone());

        // Inserting Row


        long rowInserted =  db.insert(TABLE_USERS, null, values);
        if(rowInserted != -1)
            Log.d("MyDatabaseStatus", "UserSaved Successfully");
        else
            Log.d("MyDatabaseStatus", "UserSaved Failed");
        db.close(); // Closing database connection


    }


    public List<User> getAllContacts() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                int age = Integer.parseInt(cursor.getString(1));
                String email = cursor.getString(2);
                String phone = cursor.getString(3);
                User user = new User(name, age, email, phone);
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        db.close();

        // return userList;
        if(userList.size()!=0){
            Log.d("MyDatabaseStatus", "Name  " +userList.get(0).getName());
        }
        return userList;
    }
}
