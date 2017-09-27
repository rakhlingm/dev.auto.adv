package one.saver.devautoadv;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CarsApp";

    // Contacts table name
    private static final String TABLE_ADVERTS = "Adverts";

    // Contacts Table Columns names
    private static final String KEY_ID = "indexNumber";
    private static final String KEY_IMEI = "IMEI";
    private static final String KEY_MAKE = "make";
    private static final String KEY_MODEL = "model";
    private static final String KEY_COLOR = "color";
    private static final String KEY_MIN_PRICE = "minPrice";
    private static final String KEY_MAX_PRICE = "maxPrice";
    private static final String KEY_MIN_MIL = "minMileage";
    private static final String KEY_MAX_MIL = "maxMileage";
    private static final String KEY_IMAGE_1 = "image_1";
    private static final String KEY_IMAGE_2 = "image_2";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ADVERTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IMEI + " TEXT,"
                + KEY_MAKE + " TEXT," + KEY_MODEL + " TEXT," + KEY_COLOR + " TEXT,"
                + KEY_MIN_PRICE + " INTEGER," + KEY_MAX_PRICE + " INTEGER," + KEY_MIN_MIL + " INTEGER,"
                + KEY_MAX_MIL + " INTEGER," + KEY_IMAGE_1 + " TEXT," + KEY_IMAGE_2 + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.e("Table Adverts", "Table Adverts was created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADVERTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addAdvert(Advert advert) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IMEI, advert.getIMEI()); // Contact Name
        values.put(KEY_MAKE, advert.getMake()); // Contact Phone
        values.put(KEY_MODEL, advert.getModel()); // Contact Name
        values.put(KEY_COLOR, advert.getColor()); // Contact Phone
        values.put(KEY_MIN_PRICE, advert.getMinPrice()); // Contact Name
        values.put(KEY_MAX_PRICE, advert.getMaxPrice()); // Contact Phone
        values.put(KEY_MIN_MIL, advert.getMinMileage()); // Contact Name
        values.put(KEY_MAX_MIL, advert.getMaxMileage()); // Contact Phone
        values.put(KEY_IMAGE_1, advert.getImage_1()); // Contact Name
        values.put(KEY_IMAGE_2, advert.getImage_2()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_ADVERTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
 /*   Advert getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ADVERTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }  */

    // Getting All Contacts
    public List<Advert> getAllAdverts() {
        List<Advert> advertList = new ArrayList<Advert>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ADVERTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Advert advert = new Advert();
                advert.setIndexNumber(Integer.parseInt(cursor.getString(0)));
                advert.setIMEI(cursor.getString(1));
                advert.setMake(cursor.getString(2));
                advert.setModel(cursor.getString(3));
                advert.setColor(cursor.getString(4));
                advert.setMinPrice(cursor.getInt(5));
                advert.setMaxPrice(cursor.getInt(6));
                advert.setMinMileage(cursor.getInt(7));
                advert.setMaxMileage(cursor.getInt(8));
                advert.setImage_1(cursor.getString(9));
                advert.setImage_2(cursor.getString(10));
                // Adding contact to list
                advertList.add(advert);
            } while (cursor.moveToNext());
        }

        // return contact list
        return advertList;
    }

    // Updating single contact
 /*   public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }   */

}