package com.ccm.purchaseorder_ccm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private OrderAdapter mOrderAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Order> orders, List<String> keys){
        mContext = context;
        mOrderAdapter = new OrderAdapter(orders, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mOrderAdapter);
    }

    class OrderItemView extends RecyclerView.ViewHolder{
        private TextView mOrderId;
        private TextView mClient;

        private String  key;

        public OrderItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.order_list_item, parent, false));

            mOrderId = (TextView) itemView.findViewById(R.id.orderId_txtView);
            mClient = (TextView) itemView.findViewById(R.id.client_txtView);
        }

        public void bind(Order order, String key){
            mOrderId.setText(order.getOrderId());
            mClient.setText(order.getClientId());
            this.key = key;
        }
    }

    class OrderAdapter extends RecyclerView.Adapter<OrderItemView>{
        private List<Order> mOrderList;
        private List<String> mKeys;

        public OrderAdapter(List<Order> mOrderList, List<String> mKeys) {
            this.mOrderList = mOrderList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public OrderItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new OrderItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderItemView holder, int position) {
            holder.bind(mOrderList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mOrderList.size();
        }


    }
}
