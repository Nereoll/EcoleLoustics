package fr.iut.androidprojet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TableMultiplicationActivity extends AppCompatActivity {
    public static final String MULTIPLE_KEY = "multiple_key";

    private final List<Integer> resultats = new ArrayList<>();





    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_multiplication);

        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        Button validateButton = (Button) rootLayout.getChildAt(1);

        final LinearLayout container = findViewById(R.id.container);
        String multiple = getIntent().getStringExtra(MULTIPLE_KEY);

        validateButton.setText("Valider");


        if (multiple != null) {
            try {
                int multipleInt = Integer.parseInt(multiple);
                for (int i = 1; i <= 10; i++) {
                    resultats.add(multipleInt * i);
                }
                for (int i = 1; i <= 10; i++) {
                    LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.one_multiplication, container, false);
                    TextView statictxt = linearTMP.findViewById(R.id.statictxt);
                    TextView calcul = linearTMP.findViewById(R.id.calcVar);
                    EditText resultat = linearTMP.findViewById(R.id.resultat);

                    statictxt.setText(i + " x ");
                    calcul.setText(String.valueOf(multipleInt));
                    resultat.setText(""); // Laisser vide pour que l'utilisateur fasse le calcul

                    container.addView(linearTMP);
                }
                validateButton.setOnClickListener(v -> {
                    Integer nbJuste=0;

                    for (int i = 0; i < container.getChildCount(); i++) {
                        LinearLayout line = (LinearLayout) container.getChildAt(i);
                        EditText resultat = line.findViewById(R.id.resultat);
                        String userInput = resultat.getText().toString();

                        if (userInput.isEmpty()) {
                            line.setBackgroundColor(getResources().getColor(R.color.red)); // Highlight empty field
                            continue;
                        }
                        try {
                            int userResult = Integer.parseInt(userInput);
                            if (userResult != resultats.get(i)) {
                                line.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                line.setBackgroundColor(getResources().getColor(R.color.pale_green));
                                nbJuste++;
                                System.out.println(nbJuste);
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                    if (nbJuste == 10){
                        Intent intent = new Intent(TableMultiplicationActivity.this, FelicitationActivity.class);
                        startActivity(intent);
                    }
                });
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
};
