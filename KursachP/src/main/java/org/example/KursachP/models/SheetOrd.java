package org.example.KursachP.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sheetord")
public class SheetOrd {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idproduct")
    private int idProduct;
    @Column(name = "nameproduct")
    private String nameProduct;
    @Column(name = "amount")
    private int amount;


    public SheetOrd() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
