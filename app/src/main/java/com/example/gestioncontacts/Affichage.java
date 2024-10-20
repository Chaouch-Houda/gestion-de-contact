package com.example.gestioncontacts;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Affichage extends AppCompatActivity {

    //private ListView lv;
    private RecyclerView rv;
    private Button btnqte;
    private ArrayList<Contact> data;
    private SearchView searchView;
    private ArrayList<Contact> filteredData;
    private MyContactRecycleAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_affichage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        POUR QUITTER
        btnqte = findViewById(R.id.btn_qte_aff);
        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //lv = findViewById(R.id.lv);
        // MonAdapter MyContactAdapter = new MonAdapter(Affichage.this,Accueil.data);

        // chnista3mlou recycleView fi 3o4 listView
        //lv.setAdapter((MyContactAdapter));
        // Initialisation du RecyclerView
        rv = findViewById(R.id.rv);

        // Initialisation du SearchView
        searchView = findViewById(R.id.search_view);

        //MyContactRecycleAdapter ad = new MyContactRecycleAdapter(this,Accueil.data);
        // Récupérer les données de la base de données
        ContactManager manager = new ContactManager(Affichage.this);
        manager.ouvrir();
        data = manager.getAllContacts();
        filteredData = new ArrayList<>(data);  // Initialiser la liste filtrée avec la liste complète

        // Initialiser l'adaptateur
        ad = new MyContactRecycleAdapter(this, filteredData);
        // nista3Mlou adapter simple m3a listView li 7aja simple par exp data chtjibha mil internet w chtaficheha w khw recycle nista3mlouh li interface graphque
        rv.setAdapter(ad);
        // Définir le LayoutManager pour le RecyclerView
        GridLayoutManager gridManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(gridManager);

        // Configurer le SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // Pas de soumission
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }
        // Méthode de filtrage des contacts
        private void filter(String text) {
            filteredData.clear();
            if (text.isEmpty()) {
                filteredData.addAll(data);
            } else {
                for (Contact contact : data) {
                    if (contact.getNom().toLowerCase().contains(text.toLowerCase()) ||
                            contact.getPseudo().toLowerCase().contains(text.toLowerCase()) ||
                            contact.getNumero().contains(text)) {
                        filteredData.add(contact);
                    }
                }
            }
            ad.notifyDataSetChanged(); // Notifier l'adaptateur que les données ont changé
    }
}