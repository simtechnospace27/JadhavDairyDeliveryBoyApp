package simtechnospace.tech.jadhavdairydeliveryboy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import simtechnospace.tech.jadhavdairydeliveryboy.PojoClass.Cart;

public class DBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "daily_details_db";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create carts table
        //System.out.println(Cart.CREATE_TABLE);
        db.execSQL(Cart.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Cart.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }


    public long insertUserDetails(String mUserId, String mCustomerName, String mDeliveryAddress, String mRequirements) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Cart.COLUMN_USER_ID, mUserId);
        values.put(Cart.COLUMN_CUSTOMER_NAME, mCustomerName);
        values.put(Cart.COLUMN_CUSTOMER_DELIVERY_ADDRESS, mDeliveryAddress);
        values.put(Cart.COLUMN_CUSTOMER_REQUIREMENTS, mRequirements);

        // insert row
        long id = db.insert(Cart.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }




    public Cart getSingleUserDetails(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Cart.TABLE_NAME,
                new String[]{Cart.COLUMN_ID, Cart.COLUMN_USER_ID, Cart.COLUMN_TIMESTAMP, Cart.COLUMN_CUSTOMER_NAME, Cart.COLUMN_CUSTOMER_DELIVERY_ADDRESS, Cart.COLUMN_CUSTOMER_REQUIREMENTS, Cart.COLUMN_CUSTOMER_DELIVERY_STATUS},
                Cart.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            // prepare note object
            Cart cart = new Cart(
                    cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(Cart.COLUMN_USER_ID)),
                    cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_NAME)),
                    cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_DELIVERY_ADDRESS)),
                    cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_REQUIREMENTS)),
                    cursor.getString(cursor.getColumnIndex(Cart.COLUMN_TIMESTAMP)),
                    cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_DELIVERY_STATUS)));


            // close the db connection
            cursor.close();

            return cart;
        }
        else {
            return null;
        }
    }





    public Cart getSingleUserDetailsUsingCustomerId(String customerId) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Cart.TABLE_NAME,
                new String[]{Cart.COLUMN_ID, Cart.COLUMN_USER_ID, Cart.COLUMN_TIMESTAMP, Cart.COLUMN_CUSTOMER_NAME, Cart.COLUMN_CUSTOMER_DELIVERY_ADDRESS, Cart.COLUMN_CUSTOMER_REQUIREMENTS, Cart.COLUMN_CUSTOMER_DELIVERY_STATUS},
                Cart.COLUMN_USER_ID + "=?",
                new String[]{customerId}, null, null, null, null);


       // System.out.println(cursor.getCount());
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            // prepare note object
            Cart cart = new Cart(
                    cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(Cart.COLUMN_USER_ID)),
                    cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_NAME)),
                    cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_DELIVERY_ADDRESS)),
                    cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_REQUIREMENTS)),
                    cursor.getString(cursor.getColumnIndex(Cart.COLUMN_TIMESTAMP)),
                    cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_DELIVERY_STATUS)));


            // close the db connection
            cursor.close();

            return cart;
        }
        else{
            return null;
        }
    }





    public List<Cart> getAllUserList() {
        List<Cart> carts = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Cart.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Cart cart = new Cart();

                cart.setId(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_ID)));
                cart.setCustomerId(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_USER_ID)));
                cart.setCustomerName(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_NAME)));
                cart.setDeliveryAddress(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_DELIVERY_ADDRESS)));
                cart.setRequirements(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_REQUIREMENTS)));
                cart.setDeliveryStatus(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_DELIVERY_STATUS)));
                cart.setTimeStamp(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_TIMESTAMP)));

                carts.add(cart);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return carts;
    }







    public List<Cart> getDeliveredUserList() {
        List<Cart> carts = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Cart.TABLE_NAME + " WHERE " + Cart.COLUMN_CUSTOMER_DELIVERY_STATUS + " = 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Cart cart = new Cart();

                cart.setId(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_ID)));
                cart.setCustomerId(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_USER_ID)));
                cart.setCustomerName(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_NAME)));
                cart.setDeliveryAddress(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_DELIVERY_ADDRESS)));
                cart.setRequirements(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_REQUIREMENTS)));
                cart.setDeliveryStatus(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_DELIVERY_STATUS)));
                cart.setTimeStamp(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_TIMESTAMP)));

                carts.add(cart);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return carts;
    }



    public List<Cart> getDate()
    {
        List<Cart> carts = new ArrayList<>();

        String selectQuery = "SELECT  DISTINCT DATE("+Cart.COLUMN_TIMESTAMP+") FROM " + Cart.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

      //  System.out.println(cursor.getColumnName(0));
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

          //  System.out.println(cursor.getString(0));

            do {
                Cart cart = new Cart();

                cart.setTimeStamp(cursor.getString(0));

                carts.add(cart);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return carts;

    }


    public List<Cart> getPendingUserList() {
        List<Cart> carts = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Cart.TABLE_NAME + " WHERE " + Cart.COLUMN_CUSTOMER_DELIVERY_STATUS + " = 0";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {


            do {
                Cart cart = new Cart();

                cart.setId(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_ID)));
                cart.setCustomerId(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_USER_ID)));
                cart.setCustomerName(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_NAME)));
                cart.setDeliveryAddress(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_DELIVERY_ADDRESS)));
                cart.setRequirements(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_REQUIREMENTS)));
                cart.setDeliveryStatus(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_CUSTOMER_DELIVERY_STATUS)));
                cart.setTimeStamp(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_TIMESTAMP)));

                carts.add(cart);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return carts;
    }










    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + Cart.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }




    public int updateUserDetails(Cart cart) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Cart.COLUMN_CUSTOMER_DELIVERY_STATUS, cart.getDeliveryStatus());

        // updating row
        return db.update(Cart.TABLE_NAME, values, Cart.COLUMN_ID + " = ?",
                new String[]{String.valueOf(cart.getId())});
    }



    public void deleteUserDetails(Cart cart) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Cart.TABLE_NAME, Cart.COLUMN_ID + " = ?",
                new String[]{String.valueOf(cart.getId())});
        db.close();
    }


    public void deleteUserDetailsByDate(Cart cart) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Cart.TABLE_NAME, "DATE( "+Cart.COLUMN_TIMESTAMP + ") = ?",
                new String[]{String.valueOf(cart.getTimeStamp())});
        db.close();
    }


}

