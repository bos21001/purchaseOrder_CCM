package com.ccm.purchaseorder_ccm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class ProductPage extends AppCompatActivity implements Serializable {
    String clickedProduct;

    FirebaseDatabase database;
    DatabaseReference serialNumberFromOrderDatabaseReference;

    ArrayList<String> productPageList;
    ArrayAdapter<String> adapter;


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

        serialNumberFromOrderDatabaseReference = database.getReference(extras.getString("databasePath"));
        serialNumberFromOrderDatabaseReference.child("serialNumbers")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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

        // Go to BarcodeReader activity
        barcodeReaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BarcodeReader.class);
                v.getContext().startActivity(intent);
            }
        });

    }

}