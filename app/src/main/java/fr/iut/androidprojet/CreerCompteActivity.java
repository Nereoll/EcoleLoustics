package fr.iut.androidprojet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import fr.iut.androidprojet.db.AppDatabase;
import fr.iut.androidprojet.db.User;

public class CreerCompteActivity extends AppCompatActivity {

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_compte);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "eleve-db")
                .fallbackToDestructiveMigration() // ✅ ça supprime et recrée si nécessaire
                .allowMainThreadQueries()
                .build();

        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        EditText prenom = findViewById(R.id.editPrenom);
        EditText nom = findViewById(R.id.editNom);
        Button btnCreer = (Button) rootLayout.getChildAt(4);
        Button btnSelectionner = (Button) rootLayout.getChildAt(5);

        btnCreer.setText("Créer un nouvel utilisateur");
        btnSelectionner.setText("Sélectionner un utilisateur");

        btnCreer.setOnClickListener(v -> {
            User eleve = new User();
            eleve.prenom = prenom.getText().toString();
            eleve.nom = nom.getText().toString();
            eleve.meilleurScoreAddition = 0;

            db.userDao().insert(eleve);

            // Sauvegarder l'élève courant
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("idEleveCourant", eleve.id); // si tu veux le récupérer plus tard
            editor.apply();

            // Rediriger vers la liste ou directement les exercices
            Intent intent = new Intent(this, ChoisirUtilisateur.class);
            startActivity(intent);
            finish();
        });
        btnSelectionner.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChoisirUtilisateur.class);
            startActivity(intent);
            finish();
        });
    }
}
