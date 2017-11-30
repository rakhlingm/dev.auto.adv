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

    // Adverts table name
    private static final String TABLE_ADVERTS = "Adverts";

    // Queries table name
    private static final String TABLE_QUERIES= "Queries";

    // Adverts and Queries Table Columns names
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
    private static final String IS_MAIN = "isActive";  /* For advert */
    private static final String IS_ACTIVE = "isActive";  /* For BLE listening */



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADVERTS_TABLE = "CREATE TABLE " + TABLE_ADVERTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IMEI + " TEXT,"
                + KEY_MAKE_INDEX + " INTEGER," + KEY_MODEL_INDEX+ " INTEGER,"
                + KEY_MAKE + " TEXT," + KEY_MODEL + " TEXT," + KEY_COLOR + " TEXT,"
                + KEY_MIN_PRICE + " INTEGER," + KEY_MAX_PRICE + " INTEGER," + KEY_MIN_MIL + " INTEGER,"
                + KEY_MAX_MIL + " INTEGER," + KEY_IMAGE_1 + " TEXT," + KEY_IMAGE_2 + " TEXT," + IS_MAIN + " INTEGER" + ")";
        db.execSQL(CREATE_ADVERTS_TABLE);
        Log.e("Table Adverts", "Table Adverts was created");
        String CREATE_QUERIES_TABLE = "CREATE TABLE " + TABLE_QUERIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IMEI + " TEXT,"
                + KEY_MAKE_INDEX + " INTEGER," + KEY_MODEL_INDEX+ " INTEGER,"
                + KEY_MAKE + " TEXT," + KEY_MODEL + " TEXT," + KEY_COLOR + " TEXT,"
                + KEY_MIN_PRICE + " INTEGER," + KEY_MAX_PRICE + " INTEGER," + KEY_MIN_MIL + " INTEGER,"
                + KEY_MAX_MIL + " INTEGER," + KEY_IMAGE_1 + " TEXT," + KEY_IMAGE_2 + " TEXT," + IS_ACTIVE + " INTEGER" + ")";
        db.execSQL(CREATE_QUERIES_TABLE);
        Log.e("Table Queries", "Table Queries was created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADVERTS);
        // Create tables again
        onCreate(db);
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUERIES);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new advert
    void addAdvert(Advert advert) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IMEI, advert.getIMEI()); // IMEI
        values.put(KEY_MAKE_INDEX, advert.getMakeIndex()); // IndexMake
        values.put(KEY_MODEL_INDEX, advert.getModelIndex()); // indexModel
        values.put(KEY_MAKE, advert.getMake()); // Make
        values.put(KEY_MODEL, advert.getModel()); // Model
        values.put(KEY_COLOR, advert.getColor()); // Color
        values.put(KEY_MIN_PRICE, advert.getMinPrice()); // Min price
        values.put(KEY_MAX_PRICE, advert.getMaxPrice()); // Max price
        values.put(KEY_MIN_MIL, advert.getMinMileage()); // Min mileage
        values.put(KEY_MAX_MIL, advert.getMaxMileage()); // Max mileage
        values.put(KEY_IMAGE_1, advert.getImage_1()); // Image #1
        values.put(KEY_IMAGE_2, advert.getImage_2()); // Image #2
        values.put(IS_MAIN, advert.getIsMain()); // isMane (for beacons)
        // Inserting Row
        db.insert(TABLE_ADVERTS, null, values);
        db.close(); // Closing database connection
    }
    // Adding new advert
    void addQuery(Query query) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IMEI, query.getIMEI()); // IMEI
        values.put(KEY_MAKE_INDEX, query.getMakeIndex()); // indexMake
        values.put(KEY_MODEL_INDEX, query.getModelIndex()); // indexModel
        values.put(KEY_MAKE, query.getMake()); // Make
        values.put(KEY_MODEL, query.getModel()); // Model
        values.put(KEY_COLOR, query.getColor()); // Color
        values.put(KEY_MIN_PRICE, query.getMinPrice()); // Min price
        values.put(KEY_MAX_PRICE, query.getMaxPrice()); // Max price
        values.put(KEY_MIN_MIL, query.getMinMileage()); // Min mileage
        values.put(KEY_MAX_MIL, query.getMaxMileage()); // Max mileage
        values.put(KEY_IMAGE_1, query.getImage_1()); // Image #1
        values.put(KEY_IMAGE_2, query.getImage_2()); // Image #2
        values.put(IS_ACTIVE, query.getIsActive()); // isActive (for BLE listening)
        // Inserting Row
        db.insert(TABLE_QUERIES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Advert getAdvert(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADVERTS, null, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Advert advert = new Advert(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)),
                Integer.parseInt(cursor.getString(10)), cursor.getString(11), cursor.getString(12), Integer.parseInt(cursor.getString(13)));
        // return advert
        return advert;
    }
    Query getQuery(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_QUERIES, null, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Query query = new Query(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)),
                Integer.parseInt(cursor.getString(10)), cursor.getString(11), cursor.getString(12), Integer.parseInt(cursor.getString(13)));
        // return query
        return query;
    }
    // Getting All Adverts
    public List<Advert> getAllAdverts() {
        List<Advert> advertList = new ArrayList<Advert>();
        // Select All Adverts
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
    // Getting All Queries
    public List<Query> getAllQueries() {
        List<Query> queryList = new ArrayList<Query>();
        // Select All Queries
        String selectQuery = "SELECT  * FROM " + TABLE_QUERIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Query query = new Query();
                query.setIndexNumber(Integer.parseInt(cursor.getString(0)));
                query.setIMEI(cursor.getString(1));
                query.setMakeIndex(cursor.getInt(2));
                query.setModelIndex(cursor.getInt(3));
                query.setMake(cursor.getString(4));
                query.setModel(cursor.getString(5));
                query.setColor(cursor.getString(6));
                query.setMinPrice(cursor.getInt(7));
                query.setMaxPrice(cursor.getInt(8));
                query.setMinMileage(cursor.getInt(9));
                query.setMaxMileage(cursor.getInt(10));
                query.setImage_1(cursor.getString(11));
                query.setImage_2(cursor.getString(12));
                query.setIsActive(Integer.parseInt(cursor.getString(13)));
                // Adding contact to list
                queryList.add(query);
                Log.e("Get all queries", query.toString());
            } while (cursor.moveToNext());
        }
        // return query list
        return queryList;
    }
    // Updating single advert
    public int updateAdvert(Advert advert) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MODEL_INDEX, advert.getModelIndex()); // indexModel
        values.put(KEY_MAKE, advert.getMake()); // Make
        values.put(KEY_MODEL, advert.getModel()); // Model
        values.put(KEY_COLOR, advert.getColor()); // Color
        values.put(KEY_MIN_PRICE, advert.getMinPrice()); // Min price
        values.put(KEY_MAX_PRICE, advert.getMaxPrice()); // Max price
        values.put(KEY_MIN_MIL, advert.getMinMileage()); // Min mileage
        values.put(KEY_MAX_MIL, advert.getMaxMileage()); // Max mileage
        values.put(KEY_IMAGE_1, advert.getImage_1()); // Image #1
        values.put(KEY_IMAGE_2, advert.getImage_2()); // Image #2
        values.put(IS_MAIN, advert.getIsMain());
        Log.e("Updating advert", "Updated successfully");
        // updating row
        return db.update(TABLE_ADVERTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(advert.getIndexNumber()) });
    }
    // Updating single query
    public int updateQuery(Query query) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MODEL_INDEX, query.getModelIndex()); // indexModel
        values.put(KEY_MAKE, query.getMake()); // Make
        values.put(KEY_MODEL, query.getModel()); // Model
        values.put(KEY_COLOR, query.getColor()); // Color
        values.put(KEY_MIN_PRICE, query.getMinPrice()); // Min price
        values.put(KEY_MAX_PRICE, query.getMaxPrice()); // Max price
        values.put(KEY_MIN_MIL, query.getMinMileage()); // Min mileage
        values.put(KEY_MAX_MIL, query.getMaxMileage()); // Max mileage
        values.put(IS_ACTIVE, query.getIsActive());
        Log.e("Updating query", "Updated successfully");
        // updating row
        return db.update(TABLE_QUERIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(query.getIndexNumber()) });
    }
    // Deleting single advert
    public void deleteAdvert(Advert advert) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ADVERTS, KEY_ID + " = ?", new String[] {Integer.toString(advert.getIndexNumber())});
        db.close();
        Log.e("Advert was removed", advert.toString());
    }
    // Deleting single query
    public void deleteQuery(Query query) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUERIES, KEY_ID + " = ?", new String[] {Integer.toString(query.getIndexNumber())});
        db.close();
        Log.e("Query was removed", query.toString());
    }
    // Getting Main advert
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
    // Getting Active queries
    public List<Query> getAllActiveQueries() {
        List<Query> queryList = new ArrayList<Query>();
        // Select All Active Queries
        String selectQuery = "SELECT  * FROM " + TABLE_QUERIES + IS_ACTIVE + "`=1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Query query = new Query();
                query.setIndexNumber(Integer.parseInt(cursor.getString(0)));
                query.setIMEI(cursor.getString(1));
                query.setMakeIndex(cursor.getInt(2));
                query.setModelIndex(cursor.getInt(3));
                query.setMake(cursor.getString(4));
                query.setModel(cursor.getString(5));
                query.setColor(cursor.getString(6));
                query.setMinPrice(cursor.getInt(7));
                query.setMaxPrice(cursor.getInt(8));
                query.setMinMileage(cursor.getInt(9));
                query.setMaxMileage(cursor.getInt(10));
                query.setImage_1(cursor.getString(11));
                query.setImage_2(cursor.getString(12));
                query.setIsActive(Integer.parseInt(cursor.getString(13)));
                // Adding contact to list
                queryList.add(query);
                Log.e("Get all queries", query.toString());
            } while (cursor.moveToNext());
        }
        // return query list
        return queryList;
    }

    // Getting adverts Count
    public int getAdvertCount() {
        String countQuery = "SELECT * FROM " + TABLE_ADVERTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
 //       cursor.close();
        // return count
        return cursor.getCount();
    }
    // Getting query Count
    public int getQueryCount() {
        String countQuery = "SELECT * FROM " + TABLE_QUERIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //       cursor.close();
        // return count
        return cursor.getCount();
    }
}