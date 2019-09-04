package com.example.myapp;

public class Article {
    private String codearticle;
    private String désignation;
    private String unité;
    private String prix;

    public Article(String codearticle, String désignation, String unité, String prix) {
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

    public String getUnité() {
        return unité;
    }

    public void setUnité(String unité) {
        this.unité = unité;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
