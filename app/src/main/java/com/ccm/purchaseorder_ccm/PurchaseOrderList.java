package com.ccm.purchaseorder_ccm;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    DatabaseReference descriptionOfProductsDataBaseReference;

    ArrayList<String> ordersList;
    ArrayAdapter<String> adapter;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    Client client;
    Order order;
    Products products;

    Map<String, String> loadedClients;
    Map<String, String> loadedProducts;
    Bundle extras = new Bundle();

    private void setupFirebaseListener(){
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG, "onAuthStateChanged: signed_in: " + user.getUid());
                }else{
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                    Toast.makeText(PurchaseOrderList.this, "Signed out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PurchaseOrderList.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthStateListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        loadedProducts = new HashMap<>();

        setupFirebaseListener();

        ImageButton logout = findViewById(R.id.logout_button);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

            }
        });

        //Add all clients information from database to loadedClients HashMap list.
        clientDataBaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    client = ds.getValue(Client.class);
                    loadedClients.put(Objects.requireNonNull(client).getId(), client.getName());
                    extras.putString(client.getId(), client.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        products = new Products();
        descriptionOfProductsDataBaseReference = database.getReference("Products");
        descriptionOfProductsDataBaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    products = ds.getValue(Products.class);
                    loadedProducts.put(Objects.requireNonNull(products).getProductId(), products.getProductDescription());
                    extras.putString(products.getProductId(), products.getProductDescription());
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
                        extras.putString("position", String.valueOf(position));
                        intent.putExtras(extras);
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