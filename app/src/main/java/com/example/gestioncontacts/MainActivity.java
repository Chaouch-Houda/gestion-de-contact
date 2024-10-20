package com.example.gestioncontacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText ednom,edmp;
    private Button btnval;
    private Button btnqte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        // Récuperation des composants
        edmp=findViewById(R.id.ed_mp_auth);
        ednom=findViewById(R.id.ed_nom_auth);
        btnval = findViewById(R.id.btn_val_auth);
        btnqte = findViewById(R.id.btn_qte_auth);

        //ecouteur d'action
        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });

        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = ednom.getText().toString();
                String mp = edmp.getText().toString();
                if(nom.equalsIgnoreCase("Houda") && mp.equals("000")) {
                    Intent i = new Intent(MainActivity.this,Accueil.class);
                    i.putExtra("USER",nom);
                    startActivity(i);
                } else{
                    Toast.makeText(MainActivity.this, "Les données saisies sont non valides", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}