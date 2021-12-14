package com.ccm.purchaseorder_ccm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Objects;

public class BarcodeReader extends AppCompatActivity {

    TextView tv_showData;
    private String st_scanned_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);
        Objects.requireNonNull(super.getSupportActionBar()).hide(); //Hides the ActionBar

        tv_showData = findViewById(R.id.txt);
    }



    public void onclick(View view) {
        if (view.getId() == R.id.open_scan){
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setPrompt("Scan a serial number");
            integrator.setCameraId(0);  // Use a specific camera of the device
            integrator.setOrientationLocked(true);
            integrator.setBeepEnabled(false);
            integrator.setCaptureActivity(CaptureActivityPortrait.class);
            integrator.initiateScan();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("BarcodeReader", "Cancelled scan");
                Toast.makeText(this, "Serial number scanning was canceled by the user", Toast.LENGTH_LONG).show();
            } else {
                Log.d("BarcodeReader", "Scanned");
                st_scanned_result = result.getContents();
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                tv_showData.setText(result.getContents());
            }
        }

    }
}