package fr.iut.androidprojet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.iut.androidprojet.db.AppDatabase;
import fr.iut.androidprojet.db.Questions;
import fr.iut.androidprojet.db.User;

public class QuizzActivity extends AppCompatActivity {

    private AppDatabase db;
    private List<Questions> questions;
    private int indexQuestion = 0;
    private int bonnesReponses = 0;

    private TextView textQuestion;
    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_activity);

        // Initialisation de la base de données
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "eleve-db")
                .fallbackToDestructiveMigration() // ✅ TRÈS important
                .allowMainThreadQueries()
                .build();

        textQuestion = findViewById(R.id.textQuestion);
        btn1 = findViewById(R.id.btnRep1);
        btn2 = findViewById(R.id.btnRep2);
        btn3 = findViewById(R.id.btnRep3);

        questions = db.questionsDAO().getAll();
        Collections.shuffle(questions); // Pour rendre l'ordre aléatoire
        questions = questions.subList(0, Math.min(10, questions.size())); // Max 10 questions

        afficherQuestion();
    }

    private void afficherQuestion() {
        if (indexQuestion >= questions.size()) {
            sauvegarderScore();
            Intent intent = new Intent(QuizzActivity.this, FelicitationQuizz.class);
            intent.putExtra(FelicitationQuizz.NBJUSTE, bonnesReponses);
            intent.putExtra(FelicitationQuizz.NBERREURS, questions.size() - bonnesReponses);
            startActivity(intent);
            finish();
            return;
        }

        Questions q = questions.get(indexQuestion);
        textQuestion.setText("Q" + (indexQuestion + 1) + " : " + q.question);

        List<String> reponses = Arrays.asList(q.reponse1, q.reponse2, q.reponse3);
        Collections.shuffle(reponses); // Mélange les positions

        btn1.setText(reponses.get(0));
        btn2.setText(reponses.get(1));
        btn3.setText(reponses.get(2));

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            if (b.getText().toString().equals(q.bonneReponse)) {
                bonnesReponses++;
            }
            indexQuestion++;
            afficherQuestion();
        };

        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
    }

    private void sauvegarderScore() {
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        int idEleve = prefs.getInt("idEleveCourant", -1);

        if (idEleve != -1) {
            User eleve = db.userDao().getUserById(idEleve);
            if (eleve != null && bonnesReponses > eleve.meilleurScoreQuizz) {
                eleve.meilleurScoreQuizz = bonnesReponses;
                db.userDao().update(eleve);
            }
        }
    }
}
