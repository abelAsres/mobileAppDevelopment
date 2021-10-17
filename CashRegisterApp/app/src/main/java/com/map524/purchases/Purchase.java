package com.map524.purchases;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Purchase implements Parcelable {
    String item;
    double purchaseTotal;
    int purchaseQuantity;
    String dateOfPurchase;

    public Purchase(String item, double purchaseTotal, int purchaseQuantity) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        this.item = item;
        this.purchaseTotal = purchaseTotal;
        this.purchaseQuantity = purchaseQuantity;
        this.dateOfPurchase = formatter.format(new Date());
        Log.d("purhcased on: ", this.dateOfPurchase);
    }

    protected Purchase(Parcel in) {
        item = in.readString();
        purchaseTotal = in.readDouble();
        purchaseQuantity = in.readInt();
    }

    public static final Creator<Purchase> CREATOR = new Creator<Purchase>() {
        @Override
        public Purchase createFromParcel(Parcel in) {
            return new Purchase(in);
        }

        @Override
        public Purchase[] newArray(int size) {
            return new Purchase[size];
        }
    };

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPurchaseTotal() {
        return purchaseTotal;
    }

    public void setPurchaseTotal(double purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getDate() {
        return dateOfPurchase;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item);
        dest.writeDouble(purchaseTotal);
        dest.writeInt(purchaseQuantity);
    }
}
