package fr.iut.androidprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Activites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activites);

        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        Button btnAddition = (Button) rootLayout.getChildAt(1);
        Button btnMultiplication = (Button) rootLayout.getChildAt(2);
        Button btnquiz = (Button) rootLayout.getChildAt(3);

        btnMultiplication.setText("Multiplications");
        btnAddition.setText("Additions");
        btnquiz.setText("Quiz CultureG");

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activites.this, Multiplication_choix.class);
                startActivity(intent);
            }
        });
        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activites.this, AdditionActivity.class);
                startActivity(intent);
            }
        });
        btnquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activites.this, QuizzActivity.class);
                startActivity(intent);
            }
        });




    }
}
