package com.mythcon.savr.ngelihwarung.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.mythcon.savr.ngelihwarung.Interface.ItemClickListener;
import com.mythcon.savr.ngelihwarung.R;

/**
 * Created by SAVR on 14/03/2018.
 */

public class OrderViewHOlder extends RecyclerView.ViewHolder implements
        View.OnClickListener,View.OnLongClickListener,
        View.OnCreateContextMenuListener {
    public TextView txtOrderId, txtOrderStatus, txtOrderAddress, txtOrderPhone;

    private ItemClickListener itemClickListener;

    public OrderViewHOlder(View itemView) {
        super(itemView);

        txtOrderId = itemView.findViewById(R.id.order_id);
        txtOrderStatus = itemView.findViewById(R.id.order_status);
        txtOrderAddress = itemView.findViewById(R.id.order_address);
        txtOrderPhone = itemView.findViewById(R.id.order_phone);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onclick(v,getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select the action");
        menu.add(0,0,getAdapterPosition(),"Update");
        menu.add(0,1,getAdapterPosition(),"Delete");
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onclick(v,getAdapterPosition(),true);
        return true;
    }
}
