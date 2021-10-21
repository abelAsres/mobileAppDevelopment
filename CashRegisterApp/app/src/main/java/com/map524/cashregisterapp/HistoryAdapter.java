package com.map524.cashregisterapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.map524.purchases.Purchase;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewHolder>{
    ArrayList<Purchase> purchaseList;
    Context context;

    public interface OnItemClickListener{
        void OnHistoryItemClicked(Purchase item);
    }

    private final OnItemClickListener historyItemListener;

    public HistoryAdapter(ArrayList<Purchase> purchaseList, Context context, OnItemClickListener historyItemListener) {
        this.purchaseList = purchaseList;
        this.context = context;
        this.historyItemListener = historyItemListener;
    }

    //prepares each row
    public static class viewHolder extends RecyclerView.ViewHolder{
        private final TextView purchaseItem;
        private final TextView purchaseTotal;
        private final TextView purchaseQuantity;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            this.purchaseItem = itemView.findViewById(R.id.purchaseItemName);
            this.purchaseTotal = itemView.findViewById(R.id.purchaseTotal);
            this.purchaseQuantity = itemView.findViewById(R.id.purchaseQuantity);
        }

        public TextView getPurchaseItem() {
            return purchaseItem;
        }

        public TextView getPurchaseTotal() {
            return purchaseTotal;
        }

        public TextView getPurchaseQuantity() {
            return purchaseQuantity;
        }
    }

    /*
     * RecyclerView calls this method whenever it needs to create a new ViewHolder.
     * The method creates and initializes the ViewHolder and its associated View, but does not fill
     * in the view's contents—the ViewHolder has not yet been bound to specific data.
     * */
    @NonNull
    @Override
    public HistoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflater helps convert xml to java object
        LayoutInflater myInflater =  LayoutInflater.from(context);
        View view =  myInflater.inflate(R.layout.history_item,parent,false);
        return new viewHolder(view);
    }

    /*
     * RecyclerView calls this method to associate a ViewHolder with data. The method fetches the
     * appropriate data and uses the data to fill in the view holder's layout. For example, if
     * the RecyclerView displays a list of names, the method might find the appropriate name in the
     * list and fill in the view holder's TextView widget.
     * */
    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.viewHolder holder, int position) {
        Log.d("onBindViewHolder","PurchaseList size: "+ purchaseList.size());
        Log.d("onBindViewHolder","item: "+ purchaseList.get(position).getItem());
        holder.getPurchaseItem().setText(purchaseList.get(position).getItem());
        holder.getPurchaseTotal().setText(Double.toString(purchaseList.get(position).getPurchaseTotal()));
        holder.getPurchaseQuantity().setText(Integer.toString(purchaseList.get(position).getPurchaseQuantity()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyItemListener.OnHistoryItemClicked(purchaseList.get(holder.getAdapterPosition()));
            }
        });
    }

    /*
     * RecyclerView calls this method to get the size of the data set. For example, in an address
     * book app, this might be the total number of addresses. RecyclerView uses this to determine
     * when there are no more items that can be displayed.*/
    @Override
    public int getItemCount() {return purchaseList.size();}
}
/*
 PurchaseAdapter purchaseAdapter;
    ArrayList<Purchase> purchaseList;
    ListView purchases;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent_from_main = getIntent();
        purchaseList = intent_from_main.getParcelableArrayListExtra("purchases");

        purchaseAdapter = new PurchaseAdapter(this,purchaseList);
        purchases = findViewById(R.id.purchaseList);
        purchases.setAdapter(purchaseAdapter);

        purchases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), SinglePurchase.class);
                intent.putExtra("singlePurchase", purchaseList.get(i));
                startActivity(intent);
            }
        });
    }
* */
