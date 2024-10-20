package com.example.gestioncontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        a = db.insert(ContactHelper.table_contacts,null,values); // return long : nb d'élts ajouté
        Log.d("ContactManager", "Contact ajouté avec ID: " + a);
        return a;
    }

    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> l = new ArrayList<Contact>();
        Cursor cr = db.query(ContactHelper.table_contacts,new String[]{ContactHelper.col_id,ContactHelper.col_nom,ContactHelper.col_pseudo,ContactHelper.col_numero},null,null,null,null,null);

        cr.moveToFirst(); // pointer vers le 1er elt
        while(! cr.isAfterLast()) {
            int i1 = cr.getInt(0);
            String i2 = cr.getString(1);
            String i3 = cr.getString(2);
            String i4 = cr.getString(3);
            l.add(new Contact(i1,i2, i3, i4));
            cr.moveToNext();
        }
        cr.close();
        db.close();
        return l;
    }

    // supprimer un contact par ID
    public void supprimer(long id) {
        int rowsDeleted = db.delete(ContactHelper.table_contacts, ContactHelper.col_id + "=?", new String[]{String.valueOf(id)});
        if (rowsDeleted > 0) {
            Log.d("ContactManager", "Contact supprimé avec ID: " + id);
        } else {
            Log.d("ContactManager", "Aucun contact trouvé avec ID: " + id);
        }
    }

    public void supprimerTousLesContacts() {
        db.execSQL("DELETE FROM contacts"); // Supprime toutes les entrées de la table contacts
        db.close(); // Fermer la base de données
    }

    // update contact
    public void modifier(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_nom, contact.getNom());
        values.put(ContactHelper.col_pseudo, contact.getPseudo());
        values.put(ContactHelper.col_numero, contact.getNumero());

        String where = ContactHelper.col_id + " = ?";
        String[] whereArgs = { String.valueOf(contact.getId()) };

        db.update(ContactHelper.table_contacts, values, where, whereArgs);
    }


    public void fermer() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
