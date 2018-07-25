package com.example.eshop;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AppDao {
    @Query("SELECT * FROM `order` ORDER BY id desc ")
    List<Order> selectAllOrders();
    @Query("SELECT * FROM `order` WHERE id=(:id)")
    List<Order> selectAllByIds(int[] id);
    @Query("SELECT * FROM `order`WHERE nameOfBuyer like:buyername and "+"itemName like:nameOfitem and "+"comment like:commet and "+
            "location like:buyerLocation and "+"phoneContact like:buyerContact and "+ "email like:buyerItem and "+"date like:orderDate and "
            +"modeOfPayment like:mdeOfPayment and "+"amount like:productAmount and "+"quantity like:productQuantity limit 1")
    Order findByName(String buyername, String nameOfitem, String commet, String buyerLocation, String buyerContact, String buyerItem,
                     String orderDate, String mdeOfPayment, String productAmount, String productQuantity);
    @Query("SELECT * FROM `order`WHERE id=(:ID)")
    Order getID(int ID);
    @Query("DELETE  FROM `order`")
    void deleteall();
    @Query("DELETE FROM `order` WHERE id=:id")
    abstract  void  deleteByUserId(int id);
    @Insert
    void insertNewOrder(Order order);
    @Delete
    void deleteOder(Order order);
}
