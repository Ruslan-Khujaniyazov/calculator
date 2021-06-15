package ru.geekbrains.calculator;


import android.widget.EditText;
import android.widget.TextView;

public class CalculationLogic {

    private Double arg1 = null;
    private Double arg2 = null;
    private Double result;
    private String currentAction = null;
    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final String MULTIPLICATION = "×";
    private static final String DIVISION = "÷";
    private static final String EQUALS = "=";
    private final Double ZERO = 0.0;

    private EditText numEnterTextView;
    private TextView resultTextView;


    public void setNumEnterTextView(EditText numEnterTextView) {
        this.numEnterTextView = numEnterTextView;
    }

    public void setResultTextView(TextView resultTextView) {
        this.resultTextView = resultTextView;
    }

    public void onCalculationButtonPressed(int buttonId) {
        if (buttonId == R.id.button_addition) {
            calculationButtonAction(ADDITION);
        }
        if (buttonId == R.id.button_subtraction) {
            calculationButtonAction(SUBTRACTION);
        }
        if (buttonId == R.id.button_division) {
            calculationButtonAction(DIVISION);
        }
        if (buttonId == R.id.button_multiplication) {
            calculationButtonAction(MULTIPLICATION);
        }
        if (buttonId == R.id.button_equals) {
            calculationButtonAction(EQUALS);
        }
        if (buttonId == R.id.button_delete) {
            numEnterTextView.getText().delete(numEnterTextView.length() - 1, numEnterTextView.length());
        }
        if (buttonId == R.id.button_clear) {
            numEnterTextView.getText().clear();
            resultTextView.getEditableText().clear();
            arg1 = null;
            arg2 = null;
            result = null;
        }
    }

    //todo если ничего не введено то никаких действий
    private void calculationButtonAction(String mathematicalAction) {

        String argument;

        if (mathematicalAction.equals(EQUALS) & arg1 != null) {
            try {
                argument = String.valueOf(numEnterTextView.getText());
                arg2 = Double.valueOf(argument);
                numEnterTextView.getText().clear();
                mathAction(currentAction, arg1, arg2);
                resultTextView.append(String.format("%s %s %s", arg2, mathematicalAction, result));
                arg1 = null;
                arg2 = null;
            } catch (NumberFormatException e) {
                numEnterTextView.getText().clear();
            }

        } else if(arg1 == null & result != null & !mathematicalAction.equals(EQUALS)) {
            try {
                arg1 = result;
                resultTextView.getEditableText().clear(); //todo check if it clears correctly for every new math action
                resultTextView.append(String.format("%s %s ", arg1, mathematicalAction));
                currentAction = mathematicalAction;
                result = null;
            } catch (NumberFormatException e) {
                numEnterTextView.getText().clear();
            }


        } else if (arg1 == null & !mathematicalAction.equals(EQUALS)) {
            try {
                argument = String.valueOf(numEnterTextView.getText());
                arg1 = Double.valueOf(argument);
                numEnterTextView.getText().clear();
                resultTextView.append(String.format("%s %s ", arg1, mathematicalAction));
                currentAction = mathematicalAction;
            } catch (NumberFormatException e) {
                arg1 = ZERO;
                resultTextView.append(String.format("%s %s ", arg1, mathematicalAction));
                currentAction = mathematicalAction;
            }

        } else {
            try  {
                argument = String.valueOf(numEnterTextView.getText());
                arg2 = Double.valueOf(argument);
                numEnterTextView.getText().clear();
                mathAction(currentAction, arg1, arg2);
                currentAction = mathematicalAction;
                arg1 = result;
                resultTextView.getEditableText().clear();
                resultTextView.append(String.format("%s %s ", arg1, mathematicalAction));
                arg2 = null;
                result = null;
            } catch (NumberFormatException e) {
                numEnterTextView.getText().clear();
            }
        }

    }

        private void mathAction(String action, Double arg1, Double arg2) {
            if(action.equals(ADDITION)) {
                result = arg1 + arg2;

            } else if(action.equals(SUBTRACTION)) {
                result = arg1 - arg2;

            } else if(action.equals(DIVISION)) {
                result = arg1 / arg2;

            } else if(action.equals(MULTIPLICATION)) {
                result = arg1 * arg2;
            }
        }
    }

    //todo Division by zero

//todo отработаьь: если после уже нажатого знака хочу поменять, то при нажатии на другой знак, должен меняться и curr action перезаписать

//todo Decimal format

//todo кол-во знаков в одном числе