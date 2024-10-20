package com.example.gestioncontacts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MonAdapter extends BaseAdapter {
    ArrayList<Contact> data;
    Context con;

    public MonAdapter(Context con, ArrayList<Contact> data) {
        this.con = con;
        this.data = data;
    }

    @Override
    public int getCount() { // retourne le nbre de views Holders à créer
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position , View view, ViewGroup viewGroup) {
//        CREATE VIEW HOLDER
        LayoutInflater inf = LayoutInflater.from(con); // pour convertir le contact view (déjà en xml) en java class
        View v = inf.inflate(R.layout.contactview,null); // v n'est pas une activité mais elle devient notre context. v es tle viewholder , ili feha homa les holders.

//        RECUPERATION DE HOLDERS
        TextView tvnom = v.findViewById(R.id.tvnom_contact);
        TextView tvpseudo = v.findViewById(R.id.tvpseudo_contact);
        TextView tvnumero = v.findViewById(R.id.tvnumero_contact);
        ImageView imgcall = v.findViewById(R.id.img_call);
        ImageView imgedit = v.findViewById(R.id.img_edit);
        ImageView imgdelete = v.findViewById(R.id.img_delete);

//      AFFECTER LES HOLDERS
        Contact c = data.get(position);
        tvnom.setText(c.nom);
        tvpseudo.setText(c.pseudo);
        tvnumero.setText(c.numero);

//        EVENT
        imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SUPPRIMER DE LA BDD
                data.remove(position);
                notifyDataSetChanged(); // pour le refresh
            }
        });
        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+c.numero));
                con.startActivity(i);
            }
        });
        imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder((con));
                alert.setTitle("Edition");
                alert.setMessage("Modifier les infos");
                LayoutInflater inf = LayoutInflater.from(con);
                View v = inf.inflate(R.layout.viewdialogue,null);
                alert.setView(v);
                // bich tjib data mt3 contact à modifier w t7othom fe les champs w ti5dim edit
                alert.show();
            }
        });
        return v;
    }
}
