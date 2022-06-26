package ru.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  EditText numEnterTextView;
    private TextView resultTextView;
    private CalculationLogic calculationLogicInstance = new CalculationLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        numEnterTextView = findViewById(R.id.numEnterTextView);
        resultTextView = findViewById(R.id.resultTextView);

        calculationLogicInstance.setNumEnterTextView(numEnterTextView);
        calculationLogicInstance.setResultTextView(resultTextView);

        int[] numButtonsId = new int[]{
                R.id.button0,
                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
                R.id.button8,
                R.id.button9,
                R.id.button_period,
                R.id.button_positive_or_negative


        };

        int[] actionButtonsId = new int[]{
                R.id.button_addition,
                R.id.button_subtraction,
                R.id.button_division,
                R.id.button_multiplication,
                R.id.button_percentage,
                R.id.button_equals,
                R.id.button_delete,
                R.id.button_clear
        };

        View.OnClickListener numPressed = v -> calculationLogicInstance.onNumPressed(v.getId());

        for (int id : numButtonsId) {
            findViewById(id).setOnClickListener(numPressed);
        }


        View.OnClickListener actionButtonPressed = v -> calculationLogicInstance.onCalculationButtonPressed(v.getId());

        for (int id : actionButtonsId) {
            findViewById(id).setOnClickListener(actionButtonPressed);
        }


    }

}