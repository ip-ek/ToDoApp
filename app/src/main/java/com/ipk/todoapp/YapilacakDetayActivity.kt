package com.ipk.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ipk.todoapp.R
import com.ipk.todoapp.VtYardimcisi
import com.ipk.todoapp.Yapilacaklar
import kotlinx.android.synthetic.main.activity_yapilacak_detay.*

class YapilacakDetayActivity : AppCompatActivity() {

    private lateinit var yapilacak: Yapilacaklar
    private lateinit var vt:VtYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yapilacak_detay)

        toolbarYapilacakDetayActivity.title = "Yapilacak Detay"
        setSupportActionBar(toolbarYapilacakDetayActivity)

        vt = VtYardimcisi(this@YapilacakDetayActivity)

        yapilacak = intent.getSerializableExtra("nesne") as Yapilacaklar

        editTextYapilacakIs.setText(yapilacak.yapilacak_is)

        buttonGuncelle.setOnClickListener {
            val yapilacak_is = editTextYapilacakIs.text.toString()

            guncelle(yapilacak.yapilacak_id,yapilacak_is)
        }
    }

    fun guncelle(yapilacak_id:Int,yapilacak_is:String){
        Log.e("Yapilacak Güncelle","$yapilacak_id - $yapilacak_is")

        YapilacaklarDao().yapilacakGuncelle(vt,yapilacak_id,yapilacak_is)

        startActivity(Intent(this@YapilacakDetayActivity,MainActivity::class.java))
    }
}