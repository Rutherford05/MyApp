package com.example.myapp;

public class Article {
    private String codearticle;
    private String désignation;
    private int unité;
    private int prix;

    public Article(String codearticle, String désignation, int unité, int prix) {
        this.codearticle = codearticle;
        this.désignation = désignation;
        this.unité = unité;
        this.prix = prix;
    }

    public Article(){

    }

    public String getCodearticle() {
        return codearticle;
    }

    public void setCodearticle(String codearticle) {
        this.codearticle = codearticle;
    }

    public String getDésignation() {
        return désignation;
    }

    public void setDésignation(String désignation) {
        this.désignation = désignation;
    }

    public int getUnité() {
        return unité;
    }

    public void setUnité(int unité) {
        this.unité = unité;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
