package fr.iut.androidprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class Multiplication_choix extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplication_choix);
        LinearLayout rootLayout = findViewById(R.id.rootLayout);

        NumberPicker numberPicker= findViewById(R.id.spinner);
        Button buttonValider = (Button) rootLayout.getChildAt(2);
        buttonValider.setText("Choisir");


        Integer[] intValues = {1, 2, 3, 4, 5, 6, 7,8,9,10};

        String[] displayValues = Arrays.stream(intValues)
                .map(String::valueOf)
                .toArray(String[]::new);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(intValues.length - 1);
        numberPicker.setDisplayedValues(displayValues);



        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String multiple= String.valueOf(numberPicker.getValue()+1);
                Intent intent = new Intent(Multiplication_choix.this, TableMultiplicationActivity.class);
                intent.putExtra(TableMultiplicationActivity.MULTIPLE_KEY, multiple);
                startActivity(intent);
            }
        });

    }
}
