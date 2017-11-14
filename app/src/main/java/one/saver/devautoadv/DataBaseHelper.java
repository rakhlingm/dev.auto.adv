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
    private static final String KEY_MAKE_INDEX = "makeIndex";
    private static final String KEY_MODEL_INDEX = "modelIndex";
    private static final String KEY_MAKE = "make";
    private static final String KEY_MODEL = "model";
    private static final String KEY_COLOR = "color";
    private static final String KEY_MIN_PRICE = "minPrice";
    private static final String KEY_MAX_PRICE = "maxPrice";
    private static final String KEY_MIN_MIL = "minMileage";
    private static final String KEY_MAX_MIL = "maxMileage";
    private static final String KEY_IMAGE_1 = "image_1";
    private static final String KEY_IMAGE_2 = "image_2";
    private static final String IS_MAIN = "isMain";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ADVERTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IMEI + " TEXT,"
                + KEY_MAKE_INDEX + " INTEGER," + KEY_MODEL_INDEX+ " INTEGER,"
                + KEY_MAKE + " TEXT," + KEY_MODEL + " TEXT," + KEY_COLOR + " TEXT,"
                + KEY_MIN_PRICE + " INTEGER," + KEY_MAX_PRICE + " INTEGER," + KEY_MIN_MIL + " INTEGER,"
                + KEY_MAX_MIL + " INTEGER," + KEY_IMAGE_1 + " TEXT," + KEY_IMAGE_2 + " TEXT," + IS_MAIN + " INTEGER" + ")";
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
        values.put(KEY_MAKE_INDEX, advert.getMakeIndex()); // Contact Phone
        values.put(KEY_MODEL_INDEX, advert.getModelIndex()); // Contact Name
        values.put(KEY_MAKE, advert.getMake()); // Contact Phone
        values.put(KEY_MODEL, advert.getModel()); // Contact Name
        values.put(KEY_COLOR, advert.getColor()); // Contact Phone
        values.put(KEY_MIN_PRICE, advert.getMinPrice()); // Contact Name
        values.put(KEY_MAX_PRICE, advert.getMaxPrice()); // Contact Phone
        values.put(KEY_MIN_MIL, advert.getMinMileage()); // Contact Name
        values.put(KEY_MAX_MIL, advert.getMaxMileage()); // Contact Phone
        values.put(KEY_IMAGE_1, advert.getImage_1()); // Contact Name
        values.put(KEY_IMAGE_2, advert.getImage_2()); // Contact Phone
        values.put(IS_MAIN, advert.getIsMain()); // Contact Phone
        // Inserting Row
        db.insert(TABLE_ADVERTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Advert getAdvert(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

  /*      Cursor cursor = db.query(TABLE_ADVERTS, new String[] { KEY_ID, KEY_IMEI, KEY_MAKE_INDEX,
                        KEY_MODEL_INDEX, KEY_MAKE, KEY_MODEL, KEY_COLOR, KEY_MIN_PRICE, KEY_MAX_PRICE,
                        KEY_MIN_MIL, KEY_MAX_MIL, KEY_IMAGE_1, KEY_IMAGE_2}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);  */
        Cursor cursor = db.query(TABLE_ADVERTS, null, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Advert advert = new Advert(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)),
                Integer.parseInt(cursor.getString(10)), cursor.getString(11), cursor.getString(12), Integer.parseInt(cursor.getString(13)));
        // return contact
        return advert;
    }

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
                advert.setMakeIndex(cursor.getInt(2));
                advert.setModelIndex(cursor.getInt(3));
                advert.setMake(cursor.getString(4));
                advert.setModel(cursor.getString(5));
                advert.setColor(cursor.getString(6));
                advert.setMinPrice(cursor.getInt(7));
                advert.setMaxPrice(cursor.getInt(8));
                advert.setMinMileage(cursor.getInt(9));
                advert.setMaxMileage(cursor.getInt(10));
                advert.setImage_1(cursor.getString(11));
                advert.setImage_2(cursor.getString(12));
                advert.setIsMain(Integer.parseInt(cursor.getString(13)));
                // Adding contact to list
                advertList.add(advert);
                Log.e("Get all adverts", advert.toString());
            } while (cursor.moveToNext());
        }

        // return contact list
        return advertList;
    }

    // Updating single contact
    public int updateAdvert(Advert advert) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IS_MAIN, advert.getIsMain());
        Log.e("Updating advert", "Updated successfully");
        // updating row
        return db.update(TABLE_ADVERTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(advert.getIndexNumber()) });
    }

    // Deleting single contact
    public void deleteAdvert(Advert advert) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ADVERTS, KEY_ID + " = ?", new String[] {Integer.toString(advert.getIndexNumber())});
        db.close();
        Log.e("Advert was removed", advert.toString());
    }

    Advert getMainAdvert(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADVERTS, null, IS_MAIN + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Advert advert = new Advert(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)),
                Integer.parseInt(cursor.getString(10)), cursor.getString(11), cursor.getString(12), Integer.parseInt(cursor.getString(13)));
        // return contact
        Log.e("Main advert", advert.toString());
        return advert;
    }
    // Getting contacts Count
    public int getAdvertCount() {
        String countQuery = "SELECT * FROM " + TABLE_ADVERTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
 //       cursor.close();
        // return count
        return cursor.getCount();
    }

}