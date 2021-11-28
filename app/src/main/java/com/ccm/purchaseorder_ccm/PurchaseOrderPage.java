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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PurchaseOrderPage extends AppCompatActivity implements Serializable {
    FirebaseDatabase database;
    DatabaseReference orderDataBaseReference;
    DatabaseReference clientDataBaseReference;
    ArrayList<String> orderList;
    ArrayList<String> nameOrderList;
    ArrayAdapter<String> adapter;
    Client client;
    Order order;

    Map<String, String> loadedClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(super.getSupportActionBar()).hide(); //Hides the ActionBar
        setContentView(R.layout.activity_purchase_order_page);
        Button doneButton = findViewById(R.id.pcop_done_button);
        ImageButton barcodeReaderButton = findViewById(R.id.pcop_barcode_reader_button);

        TextView headerOrderIdNumber = findViewById(R.id.orderIdNumber_TextView);
        TextView headerClientName = findViewById(R.id.clientName_TextView);


        client = new Client();
        ListView listView = findViewById(R.id.list_ProductOrderList);
        database = FirebaseDatabase.getInstance();
        clientDataBaseReference = database.getReference("Clients");
        orderList = new ArrayList<>();
        nameOrderList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, orderList);
        loadedClients = new HashMap<>();

        //Add all clients information from database to loadedClients HashMap list.
        clientDataBaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    client = ds.getValue(Client.class);
                    loadedClients.put(Objects.requireNonNull(client).getId(), client.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Set to the purchase order list layout all orders basic information
        order = new Order();
        orderDataBaseReference = database.getReference("Orders");
        orderDataBaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderList.clear();
                nameOrderList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {

                    order = ds.getValue(Order.class);
                    orderList.add(Objects.requireNonNull(order).getOrderId());
                    nameOrderList.add(loadedClients.get(order.getClientId()));
                }
                //listView.setAdapter(adapter); //Not necessary for now!

                System.out.println(getIntent().getSerializableExtra("PurchaseOrderList"));
                System.out.println("132477");
                headerOrderIdNumber.setText(orderList.get((Integer) getIntent().getSerializableExtra("PurchaseOrderList")));
                headerClientName.setText(nameOrderList.get((Integer) getIntent().getSerializableExtra("PurchaseOrderList")));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Product: " + orderList.get(position), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PurchaseOrderPage.this, ProductPage.class));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
