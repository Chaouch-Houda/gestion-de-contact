package com.example.gestioncontacts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class Ajout extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnqte = findViewById(R.id.btn_qte_ajout);
        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnajout = findViewById(R.id.btn_ajout);
        TextView ednom = findViewById(R.id.ed_nom_ajout);
        TextView edpseudo = findViewById(R.id.ed_pseudo_ajout);
        TextView ednumero = findViewById(R.id.ed_tel_ajout);
        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = ednom.getText().toString();
                String pseudo = edpseudo.getText().toString();
                String numero = ednumero.getText().toString();
                Accueil.data.add(new Contact(nom,pseudo,numero));
                // Afficher un Snackbar pour notifier le succès de l'ajout
                Snackbar.make(findViewById(R.id.main), "Contact ajouté avec succès", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}