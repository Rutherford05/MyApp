package com.example.myapp;



public class Article {
    private String codearticle;
    private String désignation;
    private Integer unité;
    private Float prix;
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

    public Integer getUnité() {
        return unité;
    }

    public void setUnité(Integer unité) {
        this.unité = unité;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }
}
