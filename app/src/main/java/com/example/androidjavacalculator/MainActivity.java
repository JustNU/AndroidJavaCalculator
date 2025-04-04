package com.example.androidjavacalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView calculatorText;
    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_clear;

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

        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_clear = findViewById(R.id.button_clear);

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_clear.setOnClickListener(buttonClearOnClick);
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

        if (v == button_0) {
            numberToAdd = button_0.getText().toString();
        }
        if (v == button_1) {
            numberToAdd = button_1.getText().toString();
        }
        if (v == button_2) {
            numberToAdd = button_2.getText().toString();
        }
        if (v == button_3) {
            numberToAdd = button_3.getText().toString();
        }
        if (v == button_4) {
            numberToAdd = button_4.getText().toString();
        }
        if (v == button_5) {
            numberToAdd = button_5.getText().toString();
        }
        if (v == button_6) {
            numberToAdd = button_6.getText().toString();
        }
        if (v == button_7) {
            numberToAdd = button_7.getText().toString();
        }
        if (v == button_8) {
            numberToAdd = button_8.getText().toString();
        }
        if (v == button_9) {
            numberToAdd = button_9.getText().toString();
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
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_PARAMETERS, calculatorText.getText().toString());
    }

    // load calculator text
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculatorText.setText(savedInstanceState.getString(KEY_PARAMETERS));
    }
}