package com.ipk.todoapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VtYardimcisi(context: Context): SQLiteOpenHelper(context, "yapilacaklar.sqlite", null, 1) {

    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL("CREATE TABLE IF NOT EXISTS \"yapilacaklar\" (\n" +
                "\t\"yapilacak_id\"\tINTEGER,\n" +
                "\t\"yapilacak_is\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"yapilacak_id\" AUTOINCREMENT)\n" +
                ")")
    }

    override fun onUpgrade(p0: SQLiteDatabase, oldVers: Int, newVers: Int) {
        p0.execSQL("DROP TABLE IF EXISTS yapilacaklar")
        onCreate(p0)
    }


}