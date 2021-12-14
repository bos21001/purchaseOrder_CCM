package com.ccm.purchaseorder_ccm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.Serializable;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Objects;

public class ProductPage extends AppCompatActivity implements Serializable {
    String clickedProduct;

    FirebaseDatabase database;
    DatabaseReference serialNumberFromOrderDatabaseReference;

    ArrayList<String> productPageList;
    ArrayAdapter<String> adapter;
    private String st_scanned_result;
    private BreakIterator scanResult;
    Integer counter;
    IntentIntegrator integrator;
    Integer breaker = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(super.getSupportActionBar()).hide(); //Hides the ActionBar
        setContentView(R.layout.activity_product_page);
        database = FirebaseDatabase.getInstance();
        productPageList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, productPageList);

        Button doneButton = findViewById(R.id.pp_done_button);
        ImageButton barcodeReaderButton = findViewById(R.id.pp_barcode_reader_button);

        Intent INTENT = getIntent();
        Bundle extras = INTENT.getExtras();
        clickedProduct = extras.getString("clickedProduct");
        TextView headerProductName = findViewById(R.id.productNameHeader_TextView);
        ListView listView = findViewById(R.id.list_ProductPage);
        headerProductName.setText(clickedProduct);

        integrator = new IntentIntegrator(this);
        integrator.setPrompt("Scan a serial number");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(false);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);


        serialNumberFromOrderDatabaseReference = database.getReference(extras.getString("databasePath"));
        serialNumberFromOrderDatabaseReference.child("serialNumbers")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productPageList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            productPageList.add(extras.getString("productId") + " " + Objects.requireNonNull(ds.getValue()).toString());
                        }
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        // Go one activity back when clicking on DONE button
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductPage.super.onBackPressed();
                finish();
            }
        });

        serialNumberFromOrderDatabaseReference.child("amount");
        serialNumberFromOrderDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                counter = Integer.parseInt(Objects.requireNonNull(snapshot.child("amount").getValue()).toString().trim()) - 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Go to BarcodeReader activity
        barcodeReaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("BarcodeReader", "Cancelled scan");
                Toast.makeText(this, "Serial number scanning was canceled by the user", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("BarcodeReader", "Scanned");
                st_scanned_result = result.getContents();
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_SHORT).show();


                DatabaseReference actualSN = serialNumberFromOrderDatabaseReference.child("serialNumbers").child(breaker.toString());
                actualSN.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        System.out.println(Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getValue()).toString().length());
                        System.out.println("132477");
                        int length = Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getValue()).toString().length();
                        if (length <= 7) {
                            actualSN.setValue(st_scanned_result);
                            breaker++;
                        } else {
                            System.out.println("DONE");
                        }
                    }
                });

            }
        }

    }
}