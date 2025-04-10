package fr.iut.androidprojet;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class FelicitationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_felicitation);

        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        Button quitter = (Button) rootLayout.getChildAt(3);
        Button recommencer = (Button) rootLayout.getChildAt(4);

        quitter.setText("Choisir une autre activité");
        recommencer.setText("Recommencer");


        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FelicitationActivity.this, Activites.class);
                startActivity(intent);
            }
        });

        recommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FelicitationActivity.this, Multiplication_choix.class);
                startActivity(intent);
            }
        });
    }
}

