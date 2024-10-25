package com.example.gestioncontacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DISPLAY_LENGTH = 3000; // Durée en millisecondes (3 secondes)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Utiliser un Handler pour afficher l'écran d'accueil après un certain délai
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                boolean isConnected = sharedPreferences.getBoolean("connected", false);
                Intent intent;
                if (isConnected) {
                    // Redirige vers l'accueil si l'utilisateur est déjà connecté
                    String username = sharedPreferences.getString("USER", "inconnu");
                    intent = new Intent(SplashActivity.this, Accueil.class);
                    intent.putExtra("USER", username); // Passer le nom d'utilisateur
                } else {
                    // Redirige vers la page de connexion si l'utilisateur n'est pas connecté
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish(); // Ferme l'activité splash
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
