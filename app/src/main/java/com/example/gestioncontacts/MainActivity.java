package com.example.gestioncontacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText ednom, edmp;
    private Button btnval;
    private Button btnqte;
    private CheckBox cbRememberMe;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialisation de sharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Vérifier si l'utilisateur est déjà connecté
        boolean isConnected = sharedPreferences.getBoolean("connected", false);
        if (isConnected) {
            // Lancer directement l'activité Accueil
            Intent intent = new Intent(MainActivity.this, Accueil.class);
            String username = sharedPreferences.getString("USER", ""); // Récupérer le nom d'utilisateur sauvegardé
            intent.putExtra("USER", username);
            startActivity(intent);
            finish();
            return; // Quitter onCreate pour éviter de réinitialiser l'écran de connexion
        }

        // Récupération des composants
        edmp = findViewById(R.id.ed_mp_auth);
        ednom = findViewById(R.id.ed_nom_auth);
        btnval = findViewById(R.id.btn_val_auth);
        btnqte = findViewById(R.id.btn_qte_auth);
        cbRememberMe = findViewById(R.id.cb_remember_me);

        // Écouteur d'action pour le bouton de connexion
        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = ednom.getText().toString();
                String mp = edmp.getText().toString();

                // Vérifiez les informations de connexion ici
//                if (nom.equalsIgnoreCase("Houda") && mp.equals("000")) {
//                    // Sauvegarder l'état de connexion
//                    saveLoginState(true);

                if (authenticateUser(nom, mp)) {
                    // Sauvegarder l'état de connexion si "Rester connecté" est coché
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("connected", cbRememberMe.isChecked());
                    editor.putString("USER", nom); // Enregistrer le nom d'utilisateur //  Cette méthode fait partie de l'API SharedPreferences, qui permet de stocker des paires clé-valeur de manière persistante. Cela signifie que les données enregistrées restent disponibles même après que l'application a été fermée ou redémarrée.
                    editor.apply();

                    // Lancer l'activité d'accueil
                    Intent i = new Intent(MainActivity.this, Accueil.class);
                    i.putExtra("USER", nom);  // Cette méthode est utilisée pour passer des données d'une activité à une autre via un Intent. Les données passées avec putExtra() sont temporaires et ne seront disponibles que pendant la durée de vie de l'activité que vous démarrez.
                    startActivity(i);
                    finish(); // Fermer l'activité actuelle
                } else {
                    // Afficher un message d'erreur
                    Toast.makeText(MainActivity.this, "Les données saisies sont non valides", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Écouteur d'action pour le bouton quitter
        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });

    }

    private boolean authenticateUser(String username, String password) {
        // Exemple de fonction d'authentification (à remplacer par la logique réelle)
        // return "admin".equals(username) && "password".equals(password);
        return username.equalsIgnoreCase("Houda") &&  password.equals("000");
    }
}
