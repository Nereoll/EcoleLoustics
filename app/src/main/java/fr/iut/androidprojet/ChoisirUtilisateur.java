package fr.iut.androidprojet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import fr.iut.androidprojet.db.AppDatabase;
import fr.iut.androidprojet.db.Questions;
import fr.iut.androidprojet.db.User;

public class ChoisirUtilisateur extends AppCompatActivity {

    AppDatabase db;
    ListView listView;
    Button btnCreer;
    List<User> listeEleves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir_utilisateur);

        listView = findViewById(R.id.listeUtilisateurs);
        btnCreer = findViewById(R.id.btnCreerUtilisateur);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "eleve-db")
                .fallbackToDestructiveMigration() // ✅ TRÈS important
                .allowMainThreadQueries()
                .build();


        listeEleves = db.userDao().getAllUsers();





        List<String> nomsAffiches = new ArrayList<>();
        for (User e : listeEleves) {
            nomsAffiches.add(e.prenom + " " + e.nom + " Additions : " + e.meilleurScoreAddition + " Quizz : " + e.meilleurScoreQuizz);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, nomsAffiches);
        listView.setAdapter(adapter);

        // Lorsqu'on sélectionne un utilisateur existant
        listView.setOnItemClickListener((parent, view, position, id) -> {
            User eleveSelectionne = listeEleves.get(position);

            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("idEleveCourant", eleveSelectionne.id);
            editor.apply();

            // Aller à la page d’activités
            Intent intent = new Intent(this, Activites.class);
            startActivity(intent);
            finish();
        });

        // Redirection vers la création d'utilisateur
        btnCreer.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreerCompteActivity.class);
            startActivity(intent);
        });
    }
}

