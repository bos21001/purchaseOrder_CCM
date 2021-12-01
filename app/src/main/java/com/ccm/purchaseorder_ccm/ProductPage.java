package com.ccm.purchaseorder_ccm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProductPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(super.getSupportActionBar()).hide(); //Hides the ActionBar
        setContentView(R.layout.activity_product_page);

        Button doneButton = findViewById(R.id.pp_done_button);
        ImageButton barcodeReaderButton = findViewById(R.id.pp_barcode_reader_button);

        ListView productList = findViewById(R.id.list_ProductPage);
        List<DB_ProductOrder> products = allProducts();
        ArrayAdapter<DB_ProductOrder> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, products);
        productList.setAdapter(adapter);

        // Go one activity back when clicking on DONE button
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PurchaseOrderPage.class);
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

    /**
     *
     *
     * @return A list of the orderProducts in a Purchase Order example
     */
    private List<DB_ProductOrder> allProducts() {
        return new ArrayList<>(Arrays.asList(
                new DB_ProductOrder("5512420", "5512420200500025", 1),
                new DB_ProductOrder("5512420", "5512420200500026", 1),
                new DB_ProductOrder("5512420", "5512420200500027", 1),
                new DB_ProductOrder("5512420", "5512420200500028", 1),
                new DB_ProductOrder("5512420", "5512420200500029", 1),
                new DB_ProductOrder("5512420", "5512420200500030", 1),
                new DB_ProductOrder("5512420", "5512420200500031", 1),
                new DB_ProductOrder("5512420", "5512420200500037", 1),
                new DB_ProductOrder("5512420", "5512420200500022", 1)
        ));
    }
}