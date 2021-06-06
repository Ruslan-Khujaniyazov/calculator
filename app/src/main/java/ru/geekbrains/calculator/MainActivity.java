package ru.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText numEnterTextView;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        numEnterTextView = findViewById(R.id.numEnterTextView);
        resultTextView = findViewById(R.id.resultTextView);

        int[] buttonsId = new int[]{
                R.id.button0,
                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
                R.id.button8,
                R.id.button9
        };

        View.OnClickListener numPressed = v -> onNumPressed(v.getId());

        for (int id : buttonsId) {
            findViewById(id).setOnClickListener(numPressed);
        }


    }

    //изменил на if/else из-за предупреждения "Resource IDs will be non-final in Android Gradle Plugin version 7.0, avoid using them in switch case statements"
    //согласно этой статье  http://tools.android.com/tips/non-constant-fields
    public void onNumPressed(int buttonId) {

        if (buttonId == R.id.button0) {   //todo если 0 первая цифра, то только 1 раз нажать можно. или при нажатии , выставить автоматом 0,
            addNumToEnterTextView("0");
        } else if (buttonId == R.id.button1) {
            addNumToEnterTextView("1");
        } else if (buttonId == R.id.button2) {
            addNumToEnterTextView("2");
        } else if (buttonId == R.id.button3) {
            addNumToEnterTextView("3");
        } else if (buttonId == R.id.button4) {
            addNumToEnterTextView("4");
        } else if (buttonId == R.id.button5) {
            addNumToEnterTextView("5");
        } else if (buttonId == R.id.button6) {
            addNumToEnterTextView("6");
        } else if (buttonId == R.id.button7) {
            addNumToEnterTextView("7");
        } else if (buttonId == R.id.button8) {
            addNumToEnterTextView("8");
        } else if (buttonId == R.id.button9) {
            addNumToEnterTextView("9");
        } else if (buttonId == R.id.button_comma) { //todo проверить нет ли уже запятой. затем при переводе в double replace "," by "."
            addNumToEnterTextView(",");
        } else if (buttonId == R.id.button_positive_or_negative) { //todo отработать повторное нажатие - если есть минус, то уалить
            numEnterTextView.append("-", 0, 0);
        }

    }

    private void addNumToEnterTextView(String num) {
        numEnterTextView.append(num);
    }
}