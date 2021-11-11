package com.akansha.contentprovider

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("Range", "ResourceType")
    fun getPhoneContacts(v: View) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CONTACTS), 0
            )
        }

        var uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        var cursor: Cursor? = contentResolver.query(uri, null, null,
            null, null)
        var contactList = arrayListOf<String>()
        Log.i("CONTENT_PROVIDER", "Total no of contacts: " + cursor?.count.toString())

        if (cursor != null) {
            if (cursor.count!! > 0) {
                while (cursor.moveToNext()) {
                    var contactName =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    var contactNo =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    Log.i("CONTENT_PROVIDER", "Contact Name:: $contactName Phone:: $contactNo")
                    contactList.add("$contactName  $contactNo")
                }

                var adapter = ArrayAdapter(this, R.layout.contact_layout, contactList)
                var listView = findViewById<ListView>(R.id.list_view)
                listView.adapter = adapter
            }
        }

    }
}