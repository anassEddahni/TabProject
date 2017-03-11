package com.example.dit.tabproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DIT on 09/03/2017.
 */

public class SQLiteDatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "clientDatabase";
    private static final int DATABASE_VERSION = 1;
    Context context;

    private static final String TABLE_NAME = "CLIENTS";
    private static final String ID_CLIENT = "id";
    private static final String NAME_CLIENT = "name";
    private static final String EMAIL_CLIENT = "email";

    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +"("+ID_CLIENT +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ NAME_CLIENT +" VARCHAR(300),"+ EMAIL_CLIENT +" VARCHAR(300));";
    public SQLiteDatabaseManager(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Toast.makeText(context, "onCreate Method called table créée", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Toast.makeText(context, "onUpgrade Method called ", Toast.LENGTH_LONG).show();
    }


    // retourner la colonne NAME_CLIENT
    public  String[] getNames(){
        ArrayList<String> listName = new ArrayList<String>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT "+NAME_CLIENT+" FROM "+TABLE_NAME, null);
        int nameColumn=c.getColumnIndex(NAME_CLIENT);
        if (c.moveToFirst()) {
            do {
                String name = c.getString(nameColumn);
                listName.add(name);
            } while (c.moveToNext());
        }
        if (c != null && !c.isClosed()) {
            c.close();
        }
        if (database!=null){
            database.close();
        }

        String[] allName = new String[listName.size()];
        allName = listName.toArray(allName);

        return allName;
    }



    // retourner la colonne EMAIL_CLIENT
    public  String[] getEmails(){
        ArrayList<String> listEmail = new ArrayList<String>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT "+EMAIL_CLIENT+" FROM "+TABLE_NAME, null);
        int emailColumn=c.getColumnIndex(EMAIL_CLIENT);
        if (c.moveToFirst()) {
            do {
                String email = c.getString(emailColumn);
                listEmail.add(email);
            } while (c.moveToNext());
        }
        if (c != null && !c.isClosed()) {
            c.close();
        }
        if (database!=null){
            database.close();
        }

        String[] allEmail = new String[listEmail.size()];
        allEmail = listEmail.toArray(allEmail);

        return allEmail;
    }




    public long insertClient(Client client){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.NAME_CLIENT,client.getNom());
        contentValues.put(this.EMAIL_CLIENT,client.getEmail());
        long id = database.insert(this.TABLE_NAME,null,contentValues);
        Log.d("inseert","INSERTEEEEEEEEEEEEEEEEEEEEEEEEEEED");
        database.close();


        return id;
    }

    public Client getSingleClient(int id){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, new String[] { ID_CLIENT,
                        NAME_CLIENT, EMAIL_CLIENT }, ID_CLIENT + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        Client client = new Client(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        return client;
    }

    public List<Client> getAllClient(){
        List<Client> clientsList = new ArrayList<Client>();
        String query = "SELECT  * FROM " +TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Client client = new Client();
                client.setId(Integer.parseInt(cursor.getString(0)));
                client.setNom(cursor.getString(1));
                client.setEmail(cursor.getString(2));
                clientsList.add(client);
            }while (cursor.moveToNext());
        }
        return clientsList;
    }


    public int updateClient(Client client){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_CLIENT,client.getNom());
        values.put(EMAIL_CLIENT,client.getEmail());
        return database.update(TABLE_NAME,values,ID_CLIENT +" = ?",new String[]{String.valueOf(client.getId())});
    }


    public void deleteAll(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from "+ TABLE_NAME);
    }



  /*  public String getData(){
        SQLiteDatabase database = helper.getReadableDatabase();
        //select all from client
        Cursor cursor = database.query(SQLHelper.TABLE_NAME,null,null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()){
            int indexId = cursor.getColumnIndex((SQLHelper.ID_CLIENT));
            int indexName = cursor.getColumnIndex((SQLHelper.NAME_CLIENT));
            int indexEmail = cursor.getColumnIndex((SQLHelper.EMAIL_CLIENT));

            int id = cursor.getInt(indexId);
            String name = cursor.getString(indexName);
            String email = cursor.getString(indexEmail);
            buffer.append(id+" : "+name+" - "+email+"\n");
        }
        return buffer.toString();
    }*/


  }
