package com.ccm.purchaseorder_ccm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PurchaseOrderPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(super.getSupportActionBar()).hide(); //Hides the ActionBar
        setContentView(R.layout.activity_purchase_order_page);

        Button doneButton = findViewById(R.id.pcop_done_button);
        ImageButton barcodeReaderButton = findViewById(R.id.pcop_barcode_reader_button);
        ImageButton lougoutButton = findViewById(R.id.logout_button);

        ListView purchaseList = findViewById(R.id.list_PurchaseOrderList);
        List<DB_PurchaseOrder> orders = allPurchaseOrders();
        ArrayAdapter<DB_PurchaseOrder> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, orders);
        purchaseList.setAdapter(adapter);

        // Open ProductPage.class when clicking in the first item of the list
        purchaseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    startActivity(new Intent(PurchaseOrderPage.this, ProductPage.class));
                }
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

        // Go to login activity
        lougoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    /**
     *
     *
     * @return A list of the products in a Purchase Order example
     */
    private List<DB_PurchaseOrder> allPurchaseOrders() {
        return new ArrayList<>(Arrays.asList(
                new DB_PurchaseOrder("5512420", "ROÇADEIRA NAKASHI L 270-N", 40),
                new DB_PurchaseOrder("5600910", "GERADOR KAWASHIMA GG 8000-DS - MONO/BIVOLT", 10),
                new DB_PurchaseOrder("8210040", "PULVERIZADOR 2 EM 1", 150),
                new DB_PurchaseOrder("8210010", "PULVERIZADOR MANUAL", 50),
                new DB_PurchaseOrder("5440030", "GERADOR KAWASHIMA SERIE E GG 6000-B - MONO / BIVOLT", 5),
                new DB_PurchaseOrder("5600910", "GERADOR KAWASHIMA GG 8000-DS - MONO/BIVOLT", 15),
                new DB_PurchaseOrder("6300220", "MOTOR DE POPA KAWASHIMA KM 15T  15 HP", 10),
                new DB_PurchaseOrder("5512420", "ROÇADEIRA NAKASHI L 270-N", 40),
                new DB_PurchaseOrder("5600910", "GERADOR KAWASHIMA GG 8000-DS - MONO/BIVOLT", 10),
                new DB_PurchaseOrder("8210040", "PULVERIZADOR 2 EM 1", 150),
                new DB_PurchaseOrder("8210010", "PULVERIZADOR MANUAL", 50),
                new DB_PurchaseOrder("5440030", "GERADOR KAWASHIMA SERIE E GG 6000-B - MONO / BIVOLT", 5),
                new DB_PurchaseOrder("5600910", "GERADOR KAWASHIMA GG 8000-DS - MONO/BIVOLT", 15),
                new DB_PurchaseOrder("6300220", "MOTOR DE POPA KAWASHIMA KM 15T  15 HP", 10),
                new DB_PurchaseOrder("5512420", "ROÇADEIRA NAKASHI L 270-N", 40),
                new DB_PurchaseOrder("5600910", "GERADOR KAWASHIMA GG 8000-DS - MONO/BIVOLT", 10),
                new DB_PurchaseOrder("8210040", "PULVERIZADOR 2 EM 1", 150),
                new DB_PurchaseOrder("8210010", "PULVERIZADOR MANUAL", 50),
                new DB_PurchaseOrder("5440030", "GERADOR KAWASHIMA SERIE E GG 6000-B - MONO / BIVOLT", 5),
                new DB_PurchaseOrder("5600910", "GERADOR KAWASHIMA GG 8000-DS - MONO/BIVOLT", 15),
                new DB_PurchaseOrder("6300220", "MOTOR DE POPA KAWASHIMA KM 15T  15 HP", 10),
                new DB_PurchaseOrder(),
                new DB_PurchaseOrder()
        ));
    }
}