package com.mythcon.savr.ngelihwarung;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mythcon.savr.ngelihwarung.Common.Common;
import com.mythcon.savr.ngelihwarung.Interface.ItemClickListener;
import com.mythcon.savr.ngelihwarung.Model.Order;
import com.mythcon.savr.ngelihwarung.Model.Request;
import com.mythcon.savr.ngelihwarung.ViewHolder.OrderViewHOlder;
/*
* method "onContextItemSelected" digunakan untuk memilih update atau delete saat longclick
*/

public class OrderStatus extends AppCompatActivity {
    RecyclerView recycler_view;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request,OrderViewHOlder> adapter;
    FirebaseDatabase database;
    DatabaseReference requests;

    MaterialSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Request");

        recycler_view = findViewById(R.id.listOrders);
        recycler_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);

        listOrders();
    }

    private void listOrders() {
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHOlder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHOlder.class,
                requests)
        {
            @Override
            protected void populateViewHolder(OrderViewHOlder viewHolder, final Request model, int position) {
                viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.txtOrderStatus.setText(Common.convertCodeToStatus(model.getStatus()));
                viewHolder.txtOrderPhone.setText(model.getPhone());
                viewHolder.txtOrderAddress.setText(model.getAddress());

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onclick(View view, int position, boolean isLongClick) {
                        Intent trackingOrder = new Intent(OrderStatus.this,TrackingOrder.class);
                        Common.currentRequest = model;
                        startActivity(trackingOrder);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recycler_view.setAdapter(adapter);
    }

    @Override   // longklik untuk menampilkan Dialog Update atau Delete
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals(Common.UPDATE))
        {
            showUpdateDialog(adapter.getRef(item.getOrder()).getKey(),adapter.getItem(item.getOrder()));
        }
        else if (item.getTitle().equals(Common.DELETE)) {
            deleteDialog(adapter.getRef(item.getOrder()).getKey());
        }
        return super.onContextItemSelected(item);
    }

    private void deleteDialog(String key) {
        requests.child(key).removeValue();
    }

    private void showUpdateDialog(final String key, final Request item) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderStatus.this);
        alertDialog.setTitle("Update Order");
        alertDialog.setMessage("Please choose status");

        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.update_order_layout,null);

        spinner = view.findViewById(R.id.statusSpinner);
        spinner.setItems("Places","On my way","Shipped");

        alertDialog.setView(view);

        final String localKey = key;

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                item.setStatus(String.valueOf(spinner.getSelectedIndex()));
                requests.child(key).setValue(item);
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }
}
