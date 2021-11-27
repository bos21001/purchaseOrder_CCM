package com.ccm.purchaseorder_ccm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class PurchaseOrderList extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    public FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceClients;
    private Clients client;
    private HashMap<String, String> clientsList;
    //Second Try
    //    FirebaseDatabase database;
//    DatabaseReference databaseReference;
//    DatabaseReference databaseReference1;
//
//    ArrayList<String> list;
//    ArrayAdapter<String> adapter;
//    Clients clients;
//    Order order;

    //First Try
    //    ArrayList<String> arrayList = new ArrayList<>();
    //    ArrayAdapter<String> arrayAdapter;
    // ListView listView; // First Try


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(super.getSupportActionBar()).hide(); //Hides the ActionBar
        setContentView(R.layout.activity_purchase_order_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_PurchaseOrderList);

        client = new Clients();
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceClients = mDatabase.getReference("Clients");
        clientsList = new HashMap<>();

        // Create a list of all the actual clients
        mReferenceClients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    client = ds.getValue(Clients.class);
                    clientsList.put(Objects.requireNonNull(client).getId(), client.getName().trim());
                }
                System.out.println(clientsList);
                System.out.println(132477);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        new FirebaseDatabaseHelper().readOrders(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Order> orders, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, PurchaseOrderList.this,
                        orders, keys, clientsList);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


        //Second Try
//        clients = new Clients();
//        ListView listView = findViewById(R.id.list_PurchaseOrderList);
//        database = FirebaseDatabase.getInstance();
//        databaseReference1 = database.getReference("Clients");
//        list = new ArrayList<>();
//        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
//        databaseReference1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds: snapshot.getChildren()){
//
//                    clients = ds.getValue(Clients.class);
//                    list.add(client.getId().toString() + " " + client.getName().toString());
//
//                }
//                listView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        order = new Order();
//        databaseReference = database.getReference("Orders");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds: snapshot.getChildren()){
//
//                    order = ds.getValue(Order.class);
//                    list.add(order.getClientId().toString() + " " + order.getOrderId().toString());
//                }
//                listView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


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