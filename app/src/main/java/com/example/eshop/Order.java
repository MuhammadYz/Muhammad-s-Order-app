package com.example.eshop;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "order")
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String nameOfBuyer;
    @ColumnInfo
    public String itemName;
    @ColumnInfo
    public String quantity;
    @ColumnInfo
    public String amount;
    @ColumnInfo
    public String modeOfPayment;
    @ColumnInfo
    public String phoneContact;
    @ColumnInfo
    public String email;
    @ColumnInfo
    public String date;
    @ColumnInfo
    public String location;
    @ColumnInfo
    public String comment;

    @Ignore

    public Order(int id, String nameOfBuyer, String itemName, String quantity, String amount, String modeOfPayment, String phoneContact, String email, String date, String location, String comment) {
        this.id = id;
        this.nameOfBuyer = nameOfBuyer;
        this.itemName = itemName;
        this.quantity = quantity;
        this.amount = amount;
        this.modeOfPayment = modeOfPayment;
        this.phoneContact = phoneContact;
        this.email = email;
        this.date = date;
        this.location = location;
        this.comment = comment;
    }
    public Order( String nameOfBuyer, String itemName, String quantity, String amount, String modeOfPayment, String phoneContact, String email, String date, String location, String comment) {

        this.nameOfBuyer = nameOfBuyer;
        this.itemName = itemName;
        this.quantity = quantity;
        this.amount = amount;
        this.modeOfPayment = modeOfPayment;
        this.phoneContact = phoneContact;
        this.email = email;
        this.date = date;
        this.location = location;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfBuyer() {
        return nameOfBuyer;
    }

    public void setNameOfBuyer(String nameOfBuyer) {
        this.nameOfBuyer = nameOfBuyer;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
