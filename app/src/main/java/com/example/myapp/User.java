package com.example.myapp;

public class User {
    private String Nom;
    private String Prenom;
    private String Email;
    private String Mot_de_passe;
    public User(){

    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getEmail(String Email) {
        return Email;
    }

    public void setEmail(String Email) {
        Email = Email;
    }

    public String getMot_de_passe() {
        return Mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        Mot_de_passe = mot_de_passe;
    }
}
