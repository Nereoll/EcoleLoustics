package fr.iut.androidprojet;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import fr.iut.androidprojet.db.AppDatabase;
import fr.iut.androidprojet.db.User;

public class FelicitationAdditionActivity extends AppCompatActivity {
    public static final String NBJUSTE = "1";
    public static final String NBERREURS = "2";
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_felicitation);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "eleve-db")
                .fallbackToDestructiveMigration() // ✅ TRÈS important
                .allowMainThreadQueries()
                .build();

        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        Button quitter = (Button) rootLayout.getChildAt(3);
        Button recommencer = (Button) rootLayout.getChildAt(4);
        TextView erreurs = findViewById(R.id.nbErreurs);
        TextView juste = findViewById(R.id.nbJuste);
        TextView textView = findViewById(R.id.textView);

        int i = 0;
        String nbErreur = String.valueOf(getIntent().getIntExtra(NBERREURS,i));
        String nbJuste = String.valueOf(getIntent().getIntExtra(NBJUSTE,i));

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        int idEleve = prefs.getInt("idEleveCourant", -1);

        // Charge l’élève depuis la base
        User eleve = db.userDao().getUserById(idEleve);

        quitter.setText("Choisir une autre activité");
        recommencer.setText("Réessayer");
        erreurs.setText("Nombre de réponses fausses : "+nbErreur);
        juste.setText("Nombre de réponses juste  : "+nbJuste);

        // Compare et met à jour si le score est meilleur
        if (getIntent().getIntExtra(NBJUSTE,i) > eleve.meilleurScoreAddition) {
            eleve.meilleurScoreAddition = getIntent().getIntExtra(NBJUSTE,i);
            db.userDao().update(eleve);
        }

        if (nbErreur == "0"){
            textView.setText("Félicitation !!!");
        } else {
            textView.setText("Pas mal !!!");
        }

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FelicitationAdditionActivity.this, Activites.class);
                startActivity(intent);
            }
        });

        recommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FelicitationAdditionActivity.this, AdditionActivity.class);
                startActivity(intent);
            }
        });
    }
}

