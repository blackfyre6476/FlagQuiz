package com.example.flagquiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FlagsDAO {
    public ArrayList<FlagsModel> getRandomSevenquestions(FlagsDatabase fd){
        ArrayList<FlagsModel> modelArrayList=new ArrayList<>();
        SQLiteDatabase liteDatabase=fd.getWritableDatabase();
        Cursor cursor=liteDatabase.rawQuery("SELECT * FROM flagquiz ORDER BY RANDOM () LIMIT 7",null);

        int flagIdIndex=cursor.getColumnIndex("flag_id");
        int flagNameIndex= cursor.getColumnIndex("flag_name");
        int flagImageIndex= cursor.getColumnIndex("flag_image");

        while (cursor.moveToNext()){
            FlagsModel model=new FlagsModel(cursor.getInt(flagIdIndex),cursor.getString(flagNameIndex),cursor.getString(flagImageIndex));

            modelArrayList.add(model);
        }
        return modelArrayList;
    }

    public ArrayList<FlagsModel> getRandomThree(FlagsDatabase fd,int flag_id){
        ArrayList<FlagsModel> modelArrayList=new ArrayList<>();
        SQLiteDatabase liteDatabase=fd.getWritableDatabase();
        Cursor cursor=liteDatabase.rawQuery("SELECT * FROM flagquiz WHERE flag_id!="+flag_id+" ORDER BY RANDOM () LIMIT 3",null);

        int flagIdIndex=cursor.getColumnIndex("flag_id");
        int flagNameIndex= cursor.getColumnIndex("flag_name");
        int flagImageIndex= cursor.getColumnIndex("flag_image");

        while (cursor.moveToNext()){
            FlagsModel model=new FlagsModel(cursor.getInt(flagIdIndex),cursor.getString(flagNameIndex),cursor.getString(flagImageIndex));

            modelArrayList.add(model);
        }
        return modelArrayList;
    }
}
