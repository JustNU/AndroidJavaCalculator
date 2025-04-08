package com.example.androidjavacalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView calculatorText;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonClear;

    private final String KEY_PARAMETERS = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // init buttons
        Init();
    }

    public void Init() {
        calculatorText = findViewById(R.id.calculatorText);

        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonClear = findViewById(R.id.button_clear);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonClear.setOnClickListener(buttonClearOnClick);
    }

    // set back to default 0 on pressing clear button
    public View.OnClickListener buttonClearOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculatorText.setText("0");
        }
    };

    // i didnt feel like creating multiple mostly same listeners
    // so i did it via override and if
    @Override
    public void onClick(View v) {
        String numberToAdd = "";

        if (v == button0) {
            numberToAdd = button0.getText().toString();
        }
        if (v == button1) {
            numberToAdd = button1.getText().toString();
        }
        if (v == button2) {
            numberToAdd = button2.getText().toString();
        }
        if (v == button3) {
            numberToAdd = button3.getText().toString();
        }
        if (v == button4) {
            numberToAdd = button4.getText().toString();
        }
        if (v == button5) {
            numberToAdd = button5.getText().toString();
        }
        if (v == button6) {
            numberToAdd = button6.getText().toString();
        }
        if (v == button7) {
            numberToAdd = button7.getText().toString();
        }
        if (v == button8) {
            numberToAdd = button8.getText().toString();
        }
        if (v == button9) {
            numberToAdd = button9.getText().toString();
        }

        if (calculatorText.getText().toString().equals("0"))
        {
            calculatorText.setText(numberToAdd);
        }
        else
        {
            calculatorText.setText(calculatorText.getText().toString() + numberToAdd);
        }
    }

    // save calculator text
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_PARAMETERS, calculatorText.getText().toString());
    }

    // load calculator text
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculatorText.setText(savedInstanceState.getString(KEY_PARAMETERS));
    }
}