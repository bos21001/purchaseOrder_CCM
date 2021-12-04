package com.ccm.purchaseorder_ccm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class PurchaseOrderPage extends AppCompatActivity implements Serializable {
    FirebaseDatabase database;
    DatabaseReference orderDataBaseReference;
    DatabaseReference productsDataBaseReference;

    ArrayList<String> orderList;
    ArrayList<String> orderProductsList;
    ArrayList<String> loadedClients;
    ArrayAdapter<String> adapter;

    Order order;
    OrderProducts orderProducts;

    String position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(super.getSupportActionBar()).hide(); //Hides the ActionBar
        setContentView(R.layout.activity_purchase_order_page);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        position = extras.getString("position");

        Button doneButton = findViewById(R.id.pcop_done_button);
        ImageButton barcodeReaderButton = findViewById(R.id.pcop_barcode_reader_button);
        TextView headerOrderIdNumber = findViewById(R.id.orderIdNumber_TextView);
        TextView headerClientName = findViewById(R.id.clientName_TextView);
        ListView listView = findViewById(R.id.list_ProductOrderList);

        database = FirebaseDatabase.getInstance();
        orderList = new ArrayList<>();
        orderProductsList = new ArrayList<>();
        loadedClients = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, orderProductsList);

        //Set to the purchase order list layout all orders basic information
        order = new Order();
        orderDataBaseReference = database.getReference("Orders");
        orderDataBaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderList.clear();
                loadedClients.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {

                    order = ds.getValue(Order.class);
                    orderList.add(Objects.requireNonNull(order).getOrderId());
                    loadedClients.add(extras.getString(order.getClientId()));
                }
                headerOrderIdNumber.setText(orderList.get(Integer.parseInt(position)));
                headerClientName.setText(loadedClients.get(Integer.parseInt(position)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        orderProducts = new OrderProducts();
        productsDataBaseReference = database.getReference("Orders/" + position + "/orderProducts");
        productsDataBaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds1 : snapshot.getChildren()) {
                    orderProducts = ds1.getValue(OrderProducts.class);
                    orderProductsList.add(Objects.requireNonNull(orderProducts).getProductId() + " " + extras.getString(orderProducts.getProductId()) + " " + orderProducts.getAmount());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Clicked on Product: " + orderList.get(position), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PurchaseOrderPage.this, ProductPage.class));
            }
        });

        // Go one activity back when clicking on DONE button
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PurchaseOrderList.class);
                v.getContext().startActivity(intent);
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
