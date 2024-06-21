package com.example.test1;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero;
    private Button plus, minus, zvezd, del, rezult, Kvadrat, Steret, Koren, Procent;

    private TextView Formula, EndResult;
    private double valueFirst = Double.NaN;
    private char Action;

    private double valueSecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupView();

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                Formula.setText(Formula.getText().toString() + button.getText().toString());

            }
        };
        One.setOnClickListener(numberClickListener);
        Two.setOnClickListener(numberClickListener);
        Three.setOnClickListener(numberClickListener);
        Four.setOnClickListener(numberClickListener);
        Five.setOnClickListener(numberClickListener);
        Six.setOnClickListener(numberClickListener);
        Seven.setOnClickListener(numberClickListener);
        Eight.setOnClickListener(numberClickListener);
        Nine.setOnClickListener(numberClickListener);
        Zero.setOnClickListener(numberClickListener);

        View.OnClickListener actionOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                calculate();
                Action = button.getText().charAt(0);
                Formula.setText(String.valueOf(valueFirst) + Action);
                EndResult.setText(null);
            }
        };
        plus.setOnClickListener(actionOnclickListener);
        minus.setOnClickListener(actionOnclickListener);
        zvezd.setOnClickListener(actionOnclickListener);
        del.setOnClickListener(actionOnclickListener);
        Kvadrat.setOnClickListener(actionOnclickListener);
        Steret.setOnClickListener(actionOnclickListener);
        Koren.setOnClickListener(actionOnclickListener);
        Procent.setOnClickListener(actionOnclickListener);


        Koren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                Action = '√';
                Formula.setText("√");
                EndResult.setText(null);
                valueFirst = Double.NaN;
            }
        });



        rezult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                Action = '=';
                EndResult.setText(String.valueOf(valueFirst));
                Formula.setText(null);
            }
        });
        Steret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndResult.setText("0");
                valueFirst = Double.NaN;
                Formula.setText(null);
            }
        });

    }


    private void setupView() { //сюда все кнопки зарегать надо
        One = (Button) findViewById(R.id.One);
        Two = (Button) findViewById(R.id.Two);
        Three = (Button) findViewById(R.id.Three);
        Four = (Button) findViewById(R.id.Four);
        Five = (Button) findViewById(R.id.Five);
        Six = (Button) findViewById(R.id.Six);
        Seven = (Button) findViewById(R.id.Seven);
        Eight = (Button) findViewById(R.id.Eight);
        Nine = (Button) findViewById(R.id.Nine);
        Zero = (Button) findViewById(R.id.Zero);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        zvezd = (Button) findViewById(R.id.zvezd);
        del = (Button) findViewById(R.id.del);
        rezult = (Button) findViewById(R.id.rezult);
        Formula = (TextView) findViewById(R.id.Formula);
        EndResult = (TextView) findViewById(R.id.EndResult);
        Procent = (Button) findViewById(R.id.Procent);
        Kvadrat = (Button) findViewById(R.id.Kvadrat);
        Steret = (Button) findViewById(R.id.Steret);
        Koren = (Button) findViewById(R.id.Koren);
    }

    private void calculate() {
        String textFormula = Formula.getText().toString();
        int indexAction = textFormula.indexOf(Action);
        if (!Double.isNaN(valueFirst) || (Action == '√') || (Action == '%')) {
            try {
                if (indexAction != -1) {
                    String numberValue = textFormula.substring(indexAction + 1);
                    valueSecond = Double.parseDouble(numberValue);
                    switch (Action) {
                        case '+':
                            valueFirst += valueSecond;
                            break;
                        case '-':
                            valueFirst -= valueSecond;
                            break;
                        case '*':
                            valueFirst *= valueSecond;
                            break;
                        case '/':
                            if (valueSecond == 0) {
                                valueFirst = 0.0;
                            } else {
                                valueFirst /= valueSecond;
                            }
                            break;
                        case '=':
                            valueFirst = valueSecond;
                            break;
                        case '√':
                            valueFirst = Math.sqrt(valueSecond);
                            break;
                        case '2':
                            valueFirst = Math.pow(valueFirst, valueSecond);
                            break;
                        case '%':
                            valueFirst = valueSecond / 100;
                            break;

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                valueFirst = Double.parseDouble(Formula.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        EndResult.setText(String.valueOf(valueFirst));
        Formula.setText("");
    }
}

