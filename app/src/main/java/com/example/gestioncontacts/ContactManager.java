package com.example.gestioncontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactManager {
    SQLiteDatabase db=null ;
    Context con;

    public ContactManager(Context con) {
        this.con = con;
    }

    public void ouvrir(){
        ContactHelper helper = new ContactHelper(con,"mabase.db",null,1);
        db = helper.getWritableDatabase();
        // va ouvrir la bd en mode lecture/écriture si le fichier mabase.db du bd n'existe pas le sys va le créer et appeler la méthode onCreate,
        // sinon s'il existe et on a la meme version le sys va l'ouvrir tel qu'il est.
        // si on met une autre version le sys va appeler la méthode onUpgrate et on aura la table de bd vide.

    }

    public long ajout(String nom, String pseudo, String numero){
        long a = 0;
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_nom,nom);
        values.put(ContactHelper.col_pseudo,pseudo);
        values.put(ContactHelper.col_numero,numero);
        a = db.insert(ContactHelper.table_contacts,null,values); // return long : nb d'élts ajoutés
        return a;
    }

    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> l = new ArrayList<Contact>();
        Cursor cr = db.query(ContactHelper.table_contacts,new String[]{ContactHelper.col_id,ContactHelper.col_nom,ContactHelper.col_pseudo,ContactHelper.col_numero},null,null,null,null,null)
        return l;
    }

    public void supprimer(){

    }
    public void fermer(){

    }
}
