package com.example.myapp;

import java.util.Date;

public class Article {
    private String codearticle;
    private String désignation;
    private Integer unité;
    private String date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
