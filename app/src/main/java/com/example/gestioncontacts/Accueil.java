package com.example.gestioncontacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Accueil extends AppCompatActivity {

    private TextView tvusername;
    private Button btnajout,btnaff;
    private ImageButton btnLogout;
    //public static ArrayList<Contact> data = new ArrayList<Contact>(); // Cette ArrayList n'est plus nécessaire car nous utilisons désormais les données directement depuis la base de données.

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        // Rediriger vers Login (MainActivity) après la déconnexion
        Intent intent = new Intent(Accueil.this, MainActivity.class);
        startActivity(intent);
        finish(); // Fermer AccueilActivity
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accueil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //        data.add(new Contact("Chaouch","Houda","1111"));
        //        data.add(new Contact("Ch","Houda","1111"));
        //        data.add(new Contact("Ch","Houda","2222"));

        tvusername = findViewById(R.id.tv_user_acc);
        btnajout = findViewById(R.id.btn_ajout_acc);
        btnaff = findViewById(R.id.btn_aff_acc);
        btnLogout = findViewById(R.id.btn_logout);

        Intent x = this.getIntent();
        Bundle b = x.getExtras();
        String u = b.getString("USER");

        tvusername.setText("Accueil de "+u);

        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accueil.this,Ajout.class);
                startActivity(i);
            }
        });
        btnaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accueil.this, Affichage.class);
                startActivity(i);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }
}