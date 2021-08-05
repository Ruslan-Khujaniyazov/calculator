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
    private static final String PERCENTAGE = "%";
    private static final String EQUALS = "=";
    private static final String NEGATIVE_SIGN = "-";
    private static final String PERIOD = ".";
    private static final Double ZERO = 0.0;

    private EditText numEnterTextView;
    private TextView resultTextView;


    public void setNumEnterTextView(EditText numEnterTextView) {
        this.numEnterTextView = numEnterTextView;
    }

    public void setResultTextView(TextView resultTextView) {
        this.resultTextView = resultTextView;
    }

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
        } else if (buttonId == R.id.button_period) {
            if (!numEnterTextView.getText().toString().contains(PERIOD)) {
                addNumToEnterTextView(PERIOD);
            }
        } else if (buttonId == R.id.button_positive_or_negative) {
            if (numEnterTextView.length() != 0 && !numEnterTextView.getText().toString().equals("0") && !numEnterTextView.getText().toString().contains(NEGATIVE_SIGN)) {
                numEnterTextView.getText().insert(0, NEGATIVE_SIGN);
            } else if (numEnterTextView.getText().toString().contains(NEGATIVE_SIGN)) {
                numEnterTextView.getText().delete(0, 1);
            }
        }

    }

    private void addNumToEnterTextView(String num) {
        if (numEnterTextView.getText().toString().equals("0") && !num.equals(PERIOD)) {
            numEnterTextView.getText().clear();
        }
        numEnterTextView.append(num);
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
        if (buttonId == R.id.button_percentage) {
            calculationButtonAction(PERCENTAGE);
        }
        if (buttonId == R.id.button_equals) {
            calculationButtonAction(EQUALS);
        }
        if (buttonId == R.id.button_delete) {
            if(numEnterTextView.length() > 0) {
                numEnterTextView.getText().delete(numEnterTextView.length() - 1, numEnterTextView.length());
            }
        }
        if (buttonId == R.id.button_clear) {
            numEnterTextView.getText().clear();
            resultTextViewClear();
            arg1 = null;
            arg2 = null;
            result = null;
        }
    }

    private void calculationButtonAction(String mathematicalAction) {

        String argument;

        if (mathematicalAction.equals(PERCENTAGE)) {
            if (result != null & numEnterTextView.getText().length() == 0) {
                resultTextViewClear();
                arg1 = result;
                result = arg1 / 100;
                numEnterTextView.getText().clear();
                resultTextView.append(String.format("%s %s %s %s", arg1, mathematicalAction, EQUALS, result));
                arg1 = null;

            } else if (arg1 == null) {
                resultTextViewClear();
                argument = String.valueOf(numEnterTextView.getText());
                arg1 = Double.valueOf(argument);
                result = arg1 / 100;
                numEnterTextView.getText().clear();
                resultTextView.append(String.format("%s", result));
                arg1 = null;
            } else {
                argument = String.valueOf(numEnterTextView.getText());
                arg2 = Double.valueOf(argument);
                numEnterTextView.getText().clear();
                result = arg1 + (arg1 * arg2 / 100);
                resultTextView.append(String.format("%s %s %s %s", arg2, mathematicalAction, EQUALS, result));
            }

        } else if (mathematicalAction.equals(EQUALS) & arg1 != null) {
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

        } else if (result != null & !mathematicalAction.equals(EQUALS)) {
            try {
                if (numEnterTextView.getText().length() == 0) {
                    arg1 = result;
                    resultTextViewClear();
                    resultTextView.append(String.format("%s %s ", arg1, mathematicalAction));
                    currentAction = mathematicalAction;
                    result = null;
                } else {
                    resultTextView.getEditableText().clear();
                    argument = String.valueOf(numEnterTextView.getText());
                    arg1 = Double.valueOf(argument);
                    numEnterTextView.getText().clear();
                    resultTextView.append(String.format("%s %s ", arg1, mathematicalAction));
                    currentAction = mathematicalAction;
                }
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
            try {
                argument = String.valueOf(numEnterTextView.getText());
                arg2 = Double.valueOf(argument);
                numEnterTextView.getText().clear();
                mathAction(currentAction, arg1, arg2);
                currentAction = mathematicalAction;
                arg1 = result;
                resultTextViewClear();
                resultTextView.append(String.format("%s %s ", arg1, mathematicalAction));
                arg2 = null;
                result = null;
            } catch (NumberFormatException e) {
                numEnterTextView.getText().clear();
            }

        }
    }

    private void mathAction(String action, Double arg1, Double arg2) {
        switch (action) {
            case ADDITION:
                result = arg1 + arg2;
                break;

            case SUBTRACTION:
                result = arg1 - arg2;
                break;

            case DIVISION:
                result = arg1 / arg2;
                break;

            case MULTIPLICATION:
                result = arg1 * arg2;
                break;
        }
    }

    private void resultTextViewClear() {
        if(resultTextView.length() > 0) {
            resultTextView.getEditableText().clear();
        }
    }


}

//todo Division by zero всплыв "нельзя делить на ноль"

//todo Decimal format

//todo кол-во знаков в одном числе

//изменил switch case на if/else из-за предупреждения "Resource IDs will be non-final in Android Gradle Plugin version 7.0, avoid using them in switch case statements"
//согласно этой статье  http://tools.android.com/tips/non-constant-fields

//отработать: если после уже нажатого знака хочу поменять, то при нажатии на другой знак, должен меняться и curr action перезаписать
