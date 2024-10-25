package com.example.gestioncontacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText ednom, edmp;
    private Button btnval;
    private Button btnqte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Récupération des composants
        edmp = findViewById(R.id.ed_mp_auth);
        ednom = findViewById(R.id.ed_nom_auth);
        btnval = findViewById(R.id.btn_val_auth);
        btnqte = findViewById(R.id.btn_qte_auth);

        // Écouteur d'action pour le bouton de déconnexion
        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });

        // Écouteur d'action pour le bouton de connexion
        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = ednom.getText().toString();
                String mp = edmp.getText().toString();

                // Vérifiez les informations de connexion ici
                if (nom.equalsIgnoreCase("Houda") && mp.equals("000")) {
                    // Sauvegarder l'état de connexion
                    saveLoginState(true);

                    // Lancer l'activité d'accueil
                    Intent i = new Intent(MainActivity.this, Accueil.class);
                    i.putExtra("USER", nom);
                    startActivity(i);
                    finish(); // Fermer l'activité actuelle
                } else {
                    // Afficher un message d'erreur
                    Toast.makeText(MainActivity.this, "Les données saisies sont non valides", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Fonction pour sauvegarder l'état de connexion
    private void saveLoginState(boolean isLoggedIn) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }
}
