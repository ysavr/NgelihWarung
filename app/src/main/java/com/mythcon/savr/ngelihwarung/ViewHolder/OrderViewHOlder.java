package com.mythcon.savr.ngelihwarung.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mythcon.savr.ngelihwarung.Interface.ItemClickListener;
import com.mythcon.savr.ngelihwarung.R;

/**
 * Created by SAVR on 14/03/2018.
 */

public class OrderViewHOlder extends RecyclerView.ViewHolder {

    public TextView txtOrderId, txtOrderStatus, txtOrderAddress, txtOrderPhone;
    public Button btnEdit, btnRemove, btnDetail, btnDirection;


    public OrderViewHOlder(View itemView) {
        super(itemView);

        txtOrderId = itemView.findViewById(R.id.order_id);
        txtOrderStatus = itemView.findViewById(R.id.order_status);
        txtOrderAddress = itemView.findViewById(R.id.order_address);
        txtOrderPhone = itemView.findViewById(R.id.order_phone);

        btnEdit = itemView.findViewById(R.id.btnEdit);
        btnRemove = itemView.findViewById(R.id.btnRemove);
        btnDetail = itemView.findViewById(R.id.btnDetail);
        btnDirection = itemView.findViewById(R.id.btnDirection);


    }
}
