package com.example.gestioncontacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactHelper extends SQLiteOpenHelper {
    public static final String table_contacts= "Contacts";
    public static final String col_id ="ID";
    public static final String col_nom ="Nom";
    public static final String col_pseudo ="Pseudo";
    public static final String col_numero ="numero";

    String requete = "CREATE TABLE " + table_contacts + " ("
            + col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + col_nom + " TEXT NOT NULL, "
            + col_pseudo + " TEXT NOT NULL, "
            + col_numero + " TEXT NOT NULL)";

    public ContactHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    // LORS DE L'OUVERTURE DE LA BASE pour la 1ère fois (càd le fichier n'existe pas encore, le système va créer le fichier et appèle la méthode onCreate)
    sqLiteDatabase.execSQL(requete);
    }
    // MODIFICATION DE LA VERSION
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +table_contacts);
        onCreate(sqLiteDatabase);
    }
}
