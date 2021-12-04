package com.ccm.purchaseorder_ccm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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


public class PurchaseOrderList extends AppCompatActivity implements Serializable {
    FirebaseDatabase database;
    DatabaseReference orderDataBaseReference;
    DatabaseReference clientDataBaseReference;

    ArrayList<String> ordersList;
    ArrayAdapter<String> adapter;

    Client client;
    Order order;

    Map<String, String> loadedClients;


    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(super.getSupportActionBar()).hide(); //Hides the ActionBar
        setContentView(R.layout.activity_purchase_order_list);

        client = new Client();
        ListView listView = findViewById(R.id.list_PurchaseOrderList);
        database = FirebaseDatabase.getInstance();
        clientDataBaseReference = database.getReference("Clients");
        ordersList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, ordersList);
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
                ordersList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {

                    order = ds.getValue(Order.class);
                    ordersList.add(Objects.requireNonNull(order).getOrderId() + " " + loadedClients.get(order.getClientId()));
                }
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Order: " + ordersList.get(position), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PurchaseOrderList.this, PurchaseOrderPage.class);
                        intent.putExtra("PurchaseOrderList", position);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}