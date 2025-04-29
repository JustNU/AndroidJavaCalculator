package com.example.androidjavacalculator;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidjavacalculator.databinding.ActivityMainBinding;
import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // states of special symbols
    // set to true as we start with NaN
    private boolean operatorButtonPressed = true;
    private boolean dotButtonPressed = true;

    // where to store states and text
    private final String KEY_TEXT = "KEY_TEXT";
    private final String KEY_OPERATORSTATE = "KEY_OPERATORSTATE";
    private final String KEY_DOTSTATE = "KEY_DOTSTATE";

    // binding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // init buttons
        init();

        // do drawer layout stuff
        initDrawer();
    }

    public void init() {
        binding.button0.setOnClickListener(this);
        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
        binding.button4.setOnClickListener(this);
        binding.button5.setOnClickListener(this);
        binding.button6.setOnClickListener(this);
        binding.button7.setOnClickListener(this);
        binding.button8.setOnClickListener(this);
        binding.button9.setOnClickListener(this);
        binding.buttonPlus.setOnClickListener(this);
        binding.buttonMinus.setOnClickListener(this);
        binding.buttonMultiply.setOnClickListener(this);
        binding.buttonDivide.setOnClickListener(this);
        binding.buttonDot.setOnClickListener(this);
        binding.buttonClear.setOnClickListener(buttonClearOnClick);
        binding.buttonEquals.setOnClickListener(buttonEqualsOnClick);
    }

    // set back to default 0 on pressing clear button
    public View.OnClickListener buttonClearOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            binding.calculatorText.setText(R.string.calculatorText);
            operatorButtonPressed = true;
            dotButtonPressed = true;
        }
    };

    // evaluate string as math expression
    public View.OnClickListener buttonEqualsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Expression mathExp = new Expression(binding.calculatorText.getText().toString());
            binding.calculatorText.setText(String.valueOf(mathExp.calculate()));
            dotButtonPressed = true;
        }
    };

    // i didnt feel like creating multiple mostly same listeners
    // so i did it via override and if
    @Override
    public void onClick(View v) {
        String symbolToAdd = "";

        if (v == binding.button0) {
            symbolToAdd = binding.button0.getText().toString();
            operatorButtonPressed = false;
        }
        if (v == binding.button1) {
            symbolToAdd = binding.button1.getText().toString();
            operatorButtonPressed = false;
        }
        if (v == binding.button2) {
            symbolToAdd = binding.button2.getText().toString();
            operatorButtonPressed = false;
        }
        if (v == binding.button3) {
            symbolToAdd = binding.button3.getText().toString();
            operatorButtonPressed = false;
        }
        if (v == binding.button4) {
            symbolToAdd = binding.button4.getText().toString();
            operatorButtonPressed = false;
        }
        if (v == binding.button5) {
            symbolToAdd = binding.button5.getText().toString();
            operatorButtonPressed = false;
        }
        if (v == binding.button6) {
            symbolToAdd = binding.button6.getText().toString();
            operatorButtonPressed = false;
        }
        if (v == binding.button7) {
            symbolToAdd = binding.button7.getText().toString();
            operatorButtonPressed = false;
        }
        if (v == binding.button8) {
            symbolToAdd = binding.button8.getText().toString();
            operatorButtonPressed = false;
        }
        if (v == binding.button9) {
            symbolToAdd = binding.button9.getText().toString();
            operatorButtonPressed = false;
        }
        if (v == binding.buttonPlus && !operatorButtonPressed) {
            symbolToAdd = binding.buttonPlus.getText().toString();
            operatorButtonPressed = true;
            dotButtonPressed = false;
        }
        if (v == binding.buttonMinus && !operatorButtonPressed) {
            symbolToAdd = binding.buttonMinus.getText().toString();
            operatorButtonPressed = true;
            dotButtonPressed = false;
        }
        if (v == binding.buttonMultiply && !operatorButtonPressed) {
            symbolToAdd = binding.buttonMultiply.getText().toString();
            operatorButtonPressed = true;
            dotButtonPressed = false;
        }
        if (v == binding.buttonDivide && !operatorButtonPressed) {
            symbolToAdd = binding.buttonDivide.getText().toString();
            operatorButtonPressed = true;
            dotButtonPressed = false;
        }
        if (v == binding.buttonDot && !dotButtonPressed && !operatorButtonPressed) {
            symbolToAdd = binding.buttonDot.getText().toString();
            operatorButtonPressed = true;
            dotButtonPressed = true;
        }

        if (binding.calculatorText.getText().toString().equals("NaN") && !operatorButtonPressed)
        {
            binding.calculatorText.setText(symbolToAdd);
            dotButtonPressed = false;
        }
        else
        {
            binding.calculatorText.setText(binding.calculatorText.getText().toString() + symbolToAdd);
        }
    }

    public void initDrawer() {
        // action bar toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close);

        // add listener and sync states on load
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // buttons in nav menu listener
        binding.navView.setNavigationItemSelectedListener(item -> {
            // handle pressing about app option
            if (item.getItemId() == R.id.about_app) {
                // create new alert dialog
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                        .setTitle(R.string.nav_aboutApp)
                        .setMessage(R.string.aboutAppMessage)
                        .setPositiveButton(R.string.nav_close, null);

                // now show it
                AlertDialog alertDialogShower = alertDialogBuilder.create();
                alertDialogShower.show();

                // close drawer
                binding.drawerLayout.closeDrawers();
            }

            // Indicate that the item selection has been handled
            return true;
        });

        // Add a callback to handle the back button press
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            // Called when the back button is pressed.
            @Override
            public void handleOnBackPressed() {
                // Check if the drawer is open
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    // Close the drawer if it's open
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    // Finish the activity if the drawer is closed
                    finish();
                }
            }
        });
    }

    // save calculator text
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_TEXT, binding.calculatorText.getText().toString());
        savedInstanceState.putBoolean(KEY_OPERATORSTATE, operatorButtonPressed);
        savedInstanceState.putBoolean(KEY_DOTSTATE, dotButtonPressed);
    }

    // load calculator text
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        binding.calculatorText.setText(savedInstanceState.getString(KEY_TEXT, "0"));
        operatorButtonPressed = savedInstanceState.getBoolean(KEY_OPERATORSTATE, false);
        dotButtonPressed = savedInstanceState.getBoolean(KEY_DOTSTATE, false);
    }
}