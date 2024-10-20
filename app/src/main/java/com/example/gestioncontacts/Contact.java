package com.example.gestioncontacts;

public class Contact {
    private int id;
    String nom;
    String pseudo ;
    String numero;

    public Contact(int id, String nom, String pseudo, String numero) {
        this.id=id;
        this.nom = nom;
        this.pseudo = pseudo;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
