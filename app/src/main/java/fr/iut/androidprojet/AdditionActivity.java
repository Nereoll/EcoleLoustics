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
import java.util.Random;

public class AdditionActivity extends AppCompatActivity {


    List<Integer> add = generateRandomList(10);
    List<Integer> add2 = generateRandomList(10);
    List<Integer> resultats = sumLists(add, add2);
    public Integer nbErreur = 0;
    public Integer nbJuste = 0;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additions);

        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        Button validateButton = (Button) rootLayout.getChildAt(1);

        final LinearLayout container = findViewById(R.id.container);

        validateButton.setText("Valider");


            try {
                for (int i = 0; i <= 9; i++) {
                    LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.one_addition, container, false);
                    TextView calcAdd = linearTMP.findViewById(R.id.calcAdd);
                    TextView calcAdd2 = linearTMP.findViewById(R.id.calcVar);
                    TextView staticAdd = linearTMP.findViewById(R.id.staticAdd);
                    EditText resultat = linearTMP.findViewById(R.id.resultat);

                    calcAdd.setText(add.get(i).toString());
                    staticAdd.setText("+");
                    calcAdd2.setText(add2.get(i).toString());
                    resultat.setText(""); // Laisser vide pour que l'utilisateur fasse le calcul

                    container.addView(linearTMP);
                }
                validateButton.setOnClickListener(v -> {

                    for (int i = 0; i < container.getChildCount(); i++) {
                        LinearLayout line = (LinearLayout) container.getChildAt(i);
                        EditText resultat = line.findViewById(R.id.resultat);
                        String userInput = resultat.getText().toString();

                        if (userInput.isEmpty()) {
                                nbErreur++;
                            continue;
                        }
                        try {
                            int userResult = Integer.parseInt(userInput);
                            if (userResult != resultats.get(i)) {
                                    nbErreur++;
                            } else {
                                    nbJuste++;
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(nbErreur.toString());
                    System.out.println(nbJuste.toString());
                    Intent intent = new Intent(AdditionActivity.this, FelicitationAdditionActivity.class);
                    intent.putExtra(FelicitationAdditionActivity.NBJUSTE, nbJuste);
                    intent.putExtra(FelicitationAdditionActivity.NBERREURS, nbErreur);

                    startActivity(intent);
                });
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

    public static List<Integer> generateRandomList(int size) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(51)); // Génère un nombre entre 0 et 50
        }

        return list;
    }
    public static List<Integer> sumLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> sumList = new ArrayList<>();
        int size = Math.min(list1.size(), list2.size()); // S'assure qu'on ne dépasse pas la plus petite liste

        for (int i = 0; i < size; i++) {
            sumList.add(list1.get(i) + list2.get(i)); // Somme des valeurs aux mêmes index
        }

        return sumList;
    }
};
