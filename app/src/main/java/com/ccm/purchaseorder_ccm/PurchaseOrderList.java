package com.ccm.purchaseorder_ccm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Objects;



public class PurchaseOrderList extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
   // ListView listView; // First Try
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Client client;
    Order order;

    //First Try
    //    ArrayList<String> arrayList = new ArrayList<>();
//    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(super.getSupportActionBar()).hide(); //Hides the ActionBar
        setContentView(R.layout.activity_purchase_order_list);

        client = new Client();
        ListView listView = findViewById(R.id.list_PurchaseOrderList);
        database = FirebaseDatabase.getInstance();
        databaseReference1 = database.getReference("Clients");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){

                    client = ds.getValue(Client.class);
                    list.add(client.getId().toString() + " " + client.getName().toString());

                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        order = new Order();
        databaseReference = database.getReference("Orders");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){

                    order = ds.getValue(Order.class);
                    list.add(order.getClientId().toString() + " " + order.getOrderId().toString());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });










        ////First try to read the data from Firebase Real Storage
//        databaseReference1 = FirebaseDatabase.getInstance().getReference("Clients");
//        databaseReference = FirebaseDatabase.getInstance().getReference("Orders");
//        ListView listView = findViewById(R.id.list_PurchaseOrderList);
//        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
//        listView.setAdapter(arrayAdapter);
//
//
//
//
//
//
//
//
//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                String value1 = snapshot.getValue(Clients.class).toString();
//                String value = snapshot.getValue(Orders.class).toString();
//                arrayList.add(value);
//                arrayList.add(value1);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//
//





        //Christopher Layout Implementation
//        List<DB_PurchaseOrderList> purchaseOrders = allPurchaseOrders();
//        ArrayAdapter<DB_PurchaseOrderList> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, purchaseOrders);
//        purchaseOrdersList.setAdapter(adapter);
//
//        // Open PurchaseOrderPage.class when clicking in the first item of the list
//        purchaseOrdersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0){
//                    startActivity(new Intent(PurchaseOrderList.this, PurchaseOrderPage.class));
//                }
//            }
//        });
//
//        }
//
//    /**
//     *
//     * @return a list of all the purchase orders
//     */
//    private List<DB_PurchaseOrderList> allPurchaseOrders() {
//        return new ArrayList<>(Arrays.asList(
//                new DB_PurchaseOrderList("2021-10100", "CHRISTOPHER MENDES LTDA"),
//                new DB_PurchaseOrderList("2021-10528", "MARCO NOVAES ME"),
//                new DB_PurchaseOrderList("2021-99958", "HELAMA RODRIGUES EIRELI"),
//                new DB_PurchaseOrderList("2021-100025", "NELSON ARROYO S.A"),
//                new DB_PurchaseOrderList("2021-10528", "ALESSANDRO ALMEIDA LTDA"),
//                new DB_PurchaseOrderList("2021-10100", "CHRISTOPHER MENDES LTDA"),
//                new DB_PurchaseOrderList("2021-10528", "MARCO NOVAES ME"),
//                new DB_PurchaseOrderList("2021-99958", "HELAMA RODRIGUES EIRELI"),
//                new DB_PurchaseOrderList("2021-100025", "NELSON ARROYO S.A"),
//                new DB_PurchaseOrderList("2021-10528", "ALESSANDRO ALMEIDA LTDA"),
//                new DB_PurchaseOrderList("2021-10100", "CHRISTOPHER MENDES LTDA"),
//                new DB_PurchaseOrderList("2021-10528", "MARCO NOVAES ME"),
//                new DB_PurchaseOrderList("2021-99958", "HELAMA RODRIGUES EIRELI"),
//                new DB_PurchaseOrderList("2021-100025", "NELSON ARROYO S.A"),
//                new DB_PurchaseOrderList("2021-10528", "ALESSANDRO ALMEIDA LTDA"),
//                new DB_PurchaseOrderList("2021-10100", "CHRISTOPHER MENDES LTDA"),
//                new DB_PurchaseOrderList("2021-10528", "MARCO NOVAES ME"),
//                new DB_PurchaseOrderList("2021-99958", "HELAMA RODRIGUES EIRELI"),
//                new DB_PurchaseOrderList("2021-100025", "NELSON ARROYO S.A"),
//                new DB_PurchaseOrderList("2021-10528", "ALESSANDRO ALMEIDA LTDA")
//        ));
    }
}