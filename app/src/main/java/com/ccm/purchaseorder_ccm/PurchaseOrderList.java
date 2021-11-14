package com.ccm.purchaseorder_ccm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PurchaseOrderList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(super.getSupportActionBar()).hide(); //Hides the ActionBar
        setContentView(R.layout.activity_purchase_order_list);

        ListView purchaseOrdersList = findViewById(R.id.list_PurchaseOrderList);
        List<DB_PurchaseOrderList> purchaseOrders = allPurchaseOrders();
        ArrayAdapter<DB_PurchaseOrderList> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, purchaseOrders);
        purchaseOrdersList.setAdapter(adapter);
    }

    /**
     *
     * @return a list of all the purchase orders
     */
    private List<DB_PurchaseOrderList> allPurchaseOrders() {
        return new ArrayList<>(Arrays.asList(
                new DB_PurchaseOrderList("2021-10100", "CHRISTOPHER MENDES LTDA"),
                new DB_PurchaseOrderList("2021-10528", "MARCO NOVAES ME"),
                new DB_PurchaseOrderList("2021-99958", "HELAMA RODRIGUES EIRELI"),
                new DB_PurchaseOrderList("2021-100025", "NELSON ARROYO S.A"),
                new DB_PurchaseOrderList("2021-10528", "ALESSANDRO ALMEIDA LTDA"),
                new DB_PurchaseOrderList("2021-10100", "CHRISTOPHER MENDES LTDA"),
                new DB_PurchaseOrderList("2021-10528", "MARCO NOVAES ME"),
                new DB_PurchaseOrderList("2021-99958", "HELAMA RODRIGUES EIRELI"),
                new DB_PurchaseOrderList("2021-100025", "NELSON ARROYO S.A"),
                new DB_PurchaseOrderList("2021-10528", "ALESSANDRO ALMEIDA LTDA"),
                new DB_PurchaseOrderList("2021-10100", "CHRISTOPHER MENDES LTDA"),
                new DB_PurchaseOrderList("2021-10528", "MARCO NOVAES ME"),
                new DB_PurchaseOrderList("2021-99958", "HELAMA RODRIGUES EIRELI"),
                new DB_PurchaseOrderList("2021-100025", "NELSON ARROYO S.A"),
                new DB_PurchaseOrderList("2021-10528", "ALESSANDRO ALMEIDA LTDA"),
                new DB_PurchaseOrderList("2021-10100", "CHRISTOPHER MENDES LTDA"),
                new DB_PurchaseOrderList("2021-10528", "MARCO NOVAES ME"),
                new DB_PurchaseOrderList("2021-99958", "HELAMA RODRIGUES EIRELI"),
                new DB_PurchaseOrderList("2021-100025", "NELSON ARROYO S.A"),
                new DB_PurchaseOrderList("2021-10528", "ALESSANDRO ALMEIDA LTDA")
        ));
    }
}