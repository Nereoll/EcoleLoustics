package fr.iut.androidprojet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import fr.iut.androidprojet.db.AppDatabase;
import fr.iut.androidprojet.db.Questions;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // Set layout first

        // Fix: Find buttons using index-based search
        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        Button btnAnonymousFixed = (Button) rootLayout.getChildAt(1);
        Button btnAccountFixed = (Button) rootLayout.getChildAt(2);

        btnAnonymousFixed.setText("Anonyme");
        btnAccountFixed.setText("Votre compte");


        //premier peuplement des questions de culture G

        AppDatabase db;
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "eleve-db")
                .fallbackToDestructiveMigration() // ✅ TRÈS important
                .allowMainThreadQueries()
                .build();

        if (db.questionsDAO().getAll().isEmpty()) {
            db.questionsDAO().insert(
                    new Questions("Combien y a-t-il de continents sur Terre ?", "5", "6", "7", "7"),
                    new Questions("Quel est l’animal le plus grand du monde ?", "Éléphant", "Girafe", "Baleine bleue", "Baleine bleue"),
                    new Questions("Quelle est la capitale de la France ?", "Paris", "Grenoble", "Pontcharra", "Paris"),
                    new Questions("De quelle couleur est le drapeau du Japon ?", "Rouge et blanc", "Bleu et blanc", "Rouge et jaune", "Rouge et blanc"),
                    new Questions("Quel fruit est jaune et courbé ?", "Pomme", "Banane", "Poire", "Banane"),
                    new Questions("Combien de pattes a une araignée ?", "6", "8", "10", "8"),
                    new Questions("Quel est le métier de celui qui soigne les dents ?", "Médecin", "Dentiste", "Pharmacien", "Dentiste"),
                    new Questions("Quel est l’animal qui miaule ?", "Chien", "Chat", "Lapin", "Chat"),
                    new Questions("Combien y a-t-il de jours dans une semaine ?", "5", "7", "10", "7"),
                    new Questions("Quel est le plus proche voisin de la Terre dans l’espace ?", "Mars", "La Lune", "Le Soleil", "La Lune")
            );
        }

        // Click listeners
        btnAnonymousFixed.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activites.class);
            startActivity(intent);
        });

        btnAccountFixed.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreerCompteActivity.class);
            startActivity(intent);
        });
    }
}