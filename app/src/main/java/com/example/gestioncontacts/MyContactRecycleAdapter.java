package com.example.gestioncontacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.Manifest;

public class MyContactRecycleAdapter extends RecyclerView.Adapter<MyContactRecycleAdapter.MyViewHolder> {
    Context con;
    ArrayList<Contact> data;
    ContactManager contactManager;

    public MyContactRecycleAdapter(@NonNull Context con, ArrayList<Contact> data) {
        this.con = con;
        this.data = data;
        this.contactManager = new ContactManager(con); // Initialisation de ContactManager
        contactManager.ouvrir(); // Ouverture de la base de données
    }
    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         // c'est qui crée tous les holders ili homa nb 3la 9ad matarfa3 screen + 2 , we7id lfo9 wwe7id ilota
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
                // DELETE CONTACT
            imgdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                SUPPRIMER DE LA BDD
                    int indice = getAdapterPosition(); // indice d'element selectionné.Récuperation d'idice lzm b3d click.  m3dch bich naccidiwlou bi indice kima fi list view
                    Contact contactToDelete = data.get(indice);
                    // Supprimer de la base de données
                    contactManager.supprimer(contactToDelete.getId());
                    // Supprimer de l'ArrayList et rafraîchir l'affichage
                    data.remove(indice);
                    notifyDataSetChanged();
                }
            });

                // CALL CONTACT
            imgcall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int indice = getAdapterPosition();
                    Contact c = data.get(indice);

                    // Intent i = new Intent(Intent.ACTION_DIAL); un appel en passant par le composeur
                    // Vérifie si la permission CALL_PHONE est accordée
                    if (ContextCompat.checkSelfPermission(con, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // Demande la permission si elle n'est pas accordée
                        ActivityCompat.requestPermissions((Activity) con, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                        // Lance l'appel direct si la permission est accordée
                        Intent i = new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel:" + c.numero));
                        con.startActivity(i);
                }
            });

                // EDIT CONTACT
            imgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int indice = getAdapterPosition();
                    Contact contactToEdit = data.get(indice);

                    AlertDialog.Builder alert = new AlertDialog.Builder((con));
                    alert.setTitle("Édition");
                    alert.setMessage("Modifier les infos");

                    LayoutInflater inf = LayoutInflater.from(con);
                    View v = inf.inflate(R.layout.viewdialogue,null);
                    alert.setView(v);

                    // Initialisation des champs dans le dialogue
                    EditText edtNom = v.findViewById(R.id.edt_nom);
                    EditText edtPseudo = v.findViewById(R.id.edt_pseudo);
                    EditText edtNumero = v.findViewById(R.id.edt_numero);

                    // Pré-remplir les champs avec les données actuelles du contact
                    edtNom.setText(contactToEdit.getNom());
                    edtPseudo.setText(contactToEdit.getPseudo());
                    edtNumero.setText(contactToEdit.getNumero());

                    // Boutons dans le dialogue
                    Button edtVal = v.findViewById(R.id.edt_val);
                    Button edtQte = v.findViewById(R.id.edt_qte);

                    AlertDialog dialog = alert.create();
                    dialog.show();

                    // Listener pour le bouton "Valider"
                    edtVal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Récupérer les nouvelles données depuis les champs de saisie
                            String nouveauNom = edtNom.getText().toString();
                            String nouveauPseudo = edtPseudo.getText().toString();
                            String nouveauNumero = edtNumero.getText().toString();

                            // Mettre à jour l'objet Contact
                            contactToEdit.setNom(nouveauNom);
                            contactToEdit.setPseudo(nouveauPseudo);
                            contactToEdit.setNumero(nouveauNumero);

                            // Mettre à jour la base de données
                            contactManager.modifier(contactToEdit);

                            // Mettre à jour la liste et rafraîchir l'adaptateur
                            data.set(indice, contactToEdit);
                            notifyDataSetChanged();

                            // Fermer le dialogue
                            dialog.dismiss();
                        }
                    });

                    // Listener pour le bouton "Quitter"
                    edtQte.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Fermer le dialogue sans sauvegarder
                            dialog.dismiss();
                        }
                    });

                }
            });
        }
    }
}
