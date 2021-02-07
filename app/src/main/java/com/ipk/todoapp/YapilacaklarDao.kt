package com.ipk.todoapp

import android.content.ContentValues

class YapilacaklarDao {

    fun tumYapilacaklar(vt:VtYardimcisi):ArrayList<Yapilacaklar>{
        val db = vt.writableDatabase
        val yapilacaklarListe = ArrayList<Yapilacaklar>()
        val c = db.rawQuery("SELECT * FROM yapilacaklar",null)

        while (c.moveToNext()){
            val yapilacak = Yapilacaklar(c.getInt(c.getColumnIndex("yapilacak_id"))
                ,c.getString(c.getColumnIndex("yapilacak_is")))
            yapilacaklarListe.add(yapilacak)
        }
        return yapilacaklarListe
    }

    fun yapilacakAra(vt:VtYardimcisi,aramaKelimesi:String) : ArrayList<Yapilacaklar>{
        val db = vt.writableDatabase
        val yapilacaklarListe = ArrayList<Yapilacaklar>()
        val c = db.rawQuery("SELECT * FROM yapilacaklar WHERE yapilacak_is like '%$aramaKelimesi%'",null)

        while (c.moveToNext()){
            val yapilacak = Yapilacaklar(c.getInt(c.getColumnIndex("yapilacak_id"))
                ,c.getString(c.getColumnIndex("yapilacak_is")))
            yapilacaklarListe.add(yapilacak)
        }

        return yapilacaklarListe
    }

    fun yapilacakSil(vt:VtYardimcisi,yapilacak_id:Int){
        val db = vt.writableDatabase
        db.delete("yapilacaklar","yapilacak_id=?", arrayOf(yapilacak_id.toString()))
        db.close()
    }

    fun yapilacakEkle(vt:VtYardimcisi,yapilacak_is:String){
        val db = vt.writableDatabase

        val values = ContentValues()
        values.put("yapilacak_is",yapilacak_is)

        db.insertOrThrow("yapilacaklar",null,values)

        db.close()
    }

    fun yapilacakGuncelle(vt:VtYardimcisi,yapilacak_id:Int,yapilacak_is:String){
        val db = vt.writableDatabase

        val values = ContentValues()
        values.put("yapilacak_id",yapilacak_id)
        values.put("yapilacak_is",yapilacak_is)

        db.update("yapilacaklar",values,"yapilacak_id=?", arrayOf(yapilacak_id.toString()))

        db.close()
    }

}