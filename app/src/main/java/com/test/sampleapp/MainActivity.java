package com.test.sampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rivetz.stub.KeyRecord;
import com.rivetz.stub.Rivet;

public class MainActivity extends AppCompatActivity {
    Rivet rivet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rivet = new Rivet(this, Rivet.DEVELOPER_SPID);
    }

    public void doPair(View v) {
        rivet.pairDevice(this);
    }

    public void doCreateKey(View v) {
        KeyRecord key = rivet.createKey(Rivet.KeyType.ECDSA_DFLT);
        if (key != null) {
            Toast.makeText(this, key.name + " has been created", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error creating key: "+rivet.status, Toast.LENGTH_LONG).show();
        }
    }

    public void doSign(View v) {
        String signature = rivet.sign("mykey","this is a string");
        Toast.makeText(this, signature, Toast.LENGTH_LONG).show();
    }
}
