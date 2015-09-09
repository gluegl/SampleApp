package com.rivetz.sampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rivetz.lib.KeyRecord;
import com.rivetz.bridge.Rivet;
import com.rivetz.lib.Utilities;

public class MainActivity extends Activity {
    Rivet rivet;
    String keyName = "MyKey";

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
        KeyRecord key = rivet.createKey(Rivet.KeyType.ECDSA_DFLT,keyName);
        if (key != null) {
            Toast.makeText(this, key.name + " has been created", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error creating key: "+rivet.status, Toast.LENGTH_LONG).show();
        }
    }

    public void doSign(View v) {
        byte[] signature = rivet.sign(keyName,"this is a string");
        Toast.makeText(this, Utilities.bytesToHex(signature), Toast.LENGTH_LONG).show();
    }

    public void doDelete(View v) {
        rivet.deleteKey(keyName);

    }
}
