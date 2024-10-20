package com.example.gestioncontacts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyContactRecycleAdapter extends RecyclerView.Adapter<MyContactRecycleAdapter.MyViewHolder> {
    Context con;
    ArrayList<Contact> data;
    public MyContactRecycleAdapter(@NonNull Context con, ArrayList<Contact> data) {
        this.con = con;
        this.data = data;
    }
    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         // c'est qui crée tous les holders ili homa nb 3la 9ad screen + 2 , we7id lfo9 wwe7id ilota
        //CREATE VIEW HOLDER
        LayoutInflater inf = LayoutInflater.from(con); // pour convertir le contact view (déjà en xml) en java class
        View v = inf.inflate(R.layout.contactview,null); // v n'est pas une activité mais elle devient notre context. v es tle viewholder , ili feha homa les holders.
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//      AFFECTER LES HOLDERS
        Contact c = data.get(position);
        holder.tvnom.setText(c.nom);
        holder.tvpseudo.setText(c.pseudo);
        holder.tvnumero.setText(c.numero);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvnom, tvpseudo, tvnumero;
        ImageView imgcall, imgedit, imgdelete;
        public MyViewHolder(@NonNull View v) {
            super(v);
            //RECUPERATION DE HOLDERS
             tvnom = v.findViewById(R.id.tvnom_contact);
             tvpseudo = v.findViewById(R.id.tvpseudo_contact);
             tvnumero = v.findViewById(R.id.tvnumero_contact);
             imgcall = v.findViewById(R.id.img_call);
             imgedit = v.findViewById(R.id.img_edit);
             imgdelete = v.findViewById(R.id.img_delete);
            //EVENT
            imgdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                SUPPRIMER DE LA BDD
                    int indice = getAdapterPosition(); // indice d'element selectionné.Récuperation d'idice lzm b3d click.  m3dch bich naccidiwlou bi indice kima fi list view
                    data.remove(indice);
                    notifyDataSetChanged(); // pour le refresh
                }
            });
            imgcall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int indice = getAdapterPosition();
                    Contact c = data.get(indice);

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
        }
    }
}
