package com.example.gestioncontacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DISPLAY_LENGTH = 3000; // Durée en millisecondes (3 secondes)

    private void checkLoginState() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // Rediriger vers MainActivity si l'utilisateur est connecté
            Intent intent = new Intent(SplashActivity.this, Accueil.class);
            startActivity(intent);
        } else {
            // Rediriger vers LoginActivity si l'utilisateur n'est pas connecté
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
        finish(); // Terminer l'activité du splash pour qu'elle ne soit pas accessible via le bouton Retour
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Utiliser un Handler pour afficher l'écran d'accueil après un certain délai
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //checkLoginState();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
