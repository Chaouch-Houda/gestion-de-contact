package com.example.gestioncontacts;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

    private ListView lv;
    private RecyclerView rv;
    private Button btnqte;

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
        // nista3Mlou adapter simple m3a list view li 7aja simple par exp data chtjibha mil internet w chtaficheha w khw recycle nista3mlouh li interface graphque
        //lv.setAdapter((MyContactAdapter));

        rv = findViewById(R.id.rv);
        MyContactRecycleAdapter ad = new MyContactRecycleAdapter(this,Accueil.data);
        // nista3Mlou adapter simple m3a list view li 7aja simple par exp data chtjibha mil internet w chtaficheha w khw recycle nista3mlouh li interface graphque
        rv.setAdapter(ad);
        //ça n'affiche rien
        GridLayoutManager gridManager = new GridLayoutManager(this,1,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(gridManager);

    }
}