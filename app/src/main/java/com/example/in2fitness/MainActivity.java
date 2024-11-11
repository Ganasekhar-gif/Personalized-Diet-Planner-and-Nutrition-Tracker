package com.example.in2fitness;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private Spinner genderSpinner;
    private String selectedGender;
    private EditText ageEditText;
    private EditText heightEditText;
    private EditText weightEditText;
    private Spinner meal1ProteinSpinner;
    private Spinner meal2ProteinSpinner;
    private Spinner meal3ProteinSpinner;
    private Spinner meal4ProteinSpinner;
    private Spinner meal5ProteinSpinner;
    private Spinner meal6ProteinSpinner;
    private Spinner meal1CarbsSpinner;
    private Spinner meal2CarbsSpinner;
    private Spinner meal3CarbsSpinner;
    private Spinner meal4CarbsSpinner;
    private Spinner meal5CarbsSpinner;
    private Spinner meal6CarbsSpinner;
    private Spinner meal1fatsSpinner;
    private Spinner meal2fatsSpinner;
    private Spinner meal3fatsSpinner;
    private Spinner meal4fatsSpinner;
    private Spinner meal5fatsSpinner;
    private Spinner meal6fatsSpinner;
    private Spinner meal1fruitsSpinner;
    private Spinner meal2fruitsSpinner;
    private Spinner meal3fruitsSpinner;
    private Spinner meal4fruitsSpinner;
    private Spinner meal5fruitsSpinner;
    private Spinner meal6fruitsSpinner;

    private Spinner meal1vegetableSpinner;
    private Spinner meal2vegetableSpinner;
    private Spinner meal3vegetableSpinner;
    private Spinner meal4vegetableSpinner;
    private Spinner meal5vegetableSpinner;
    private Spinner meal6vegetableSpinner;

    private Button calculatebmrButton;
    private EditText bmrIntakeEditText;
    private EditText noOfMealsEditText;
    private EditText[] proteinsValues;
    private EditText[] carbsValues;
    private EditText[] fatsValues;
    private EditText[] fruitsVegetablesValues;

    private EditText[] VegetablesValues;
    private EditText[] caloriesValues;
    private TextView bmrTextView;

    private Bitmap chartBitmap;

    final static int REQUEST_CODE=1;
    Button btngeneratePDF;

    EditText meal1proteinGramsEditText;

    EditText meal2ProteinGramsEditText;

    EditText meal3ProteinGramsEditText;

    EditText meal4ProteinGramsEditText;

    EditText meal5ProteinGramsEditText;

    EditText meal6ProteinGramsEditText;

    EditText meal1CarbsGramEditText;

    EditText meal2CarbsGramEditText;

    EditText meal3CarbsGramEditText;

    EditText meal4CarbsGramEditText;

    EditText meal5CarbsGramEditText;

    EditText meal6CarbsGramEditText;

    EditText meal1FatsGramEditText;

    EditText meal2FatsGramEditText;

    EditText meal3FatsGramEditText;

    EditText meal4FatsGramEditText;

    EditText meal5FatsGramEditText;

    EditText meal6FatsGramEditText;

    EditText meal1FruitsGramEditText;

    EditText meal2FruitsGramEditText;

    EditText meal3FruitsGramEditText;

    EditText meal4FruitsGramEditText;

    EditText meal5FruitsGramEditText;

    EditText meal6FruitsGramEditText;
    EditText meal1VegetableGramEditText;

    EditText meal2VegetableGramEditText;
    EditText meal3VegetableGramEditText;
    EditText meal4VegetableGramEditText;
    EditText meal5VegetableGramEditText;
    EditText meal6VegetableGramEditText;

    TextView meal1PTextViewResult;
    Spinner proteinFoodSpinner;
    TextView textviewGramsOfProtein;

    TextView meal1CTextViewResult;
    Spinner carbsFoodSpinner;
    TextView textviewGramsOfCarbs;

    Spinner FatsFoodSpinner;
    TextView meal1FTextViewResult;
    TextView textviewGramsOfFats;

    Spinner FruitsSpinner;
    TextView meal1VTextViewResult;
    TextView textviewGramsOfFruits;

    Spinner vegetableSpinner1;

    TextView meal2VVTextViewResult;
    TextView textViewGramsOfVegetables1;

    TextView meal2PTextViewResult;
    Spinner proteinFoodSpinner1;
    TextView textviewGramsOfProtein1;

    TextView meal2CTextViewResult;
    Spinner carbsFoodSpinner1;
    TextView textviewGramsOfCarbs1;
    Spinner FatsFoodSpinner1;
    TextView meal2FTextViewResult;
    TextView textviewGramsOfFats1;


    Spinner FruitsSpinner1;
    TextView meal2VTextViewResult;
    TextView textviewGramsOfFruits1;

    Spinner vegetableSpinner;

    TextView meal1VVTextViewResult;
    TextView textViewGramsOfVegetables;

    TextView meal3PTextViewResult;
    Spinner proteinFoodSpinner2;
    TextView textviewGramsOfProtein2;

    TextView meal4PTextViewResult;
    Spinner proteinFoodSpinner3;
    TextView textviewGramsOfProtein3;

    TextView meal5PTextViewResult;
    Spinner proteinFoodSpinner4;
    TextView textviewGramsOfProtein4;

    TextView meal6PTextViewResult;
    Spinner proteinFoodSpinner5;
    TextView textviewGramsOfProtein5;

    TextView meal3CTextViewResult;
    Spinner carbsFoodSpinner2;
    TextView textviewGramsOfCarbs2;

    TextView meal4CTextViewResult;
    Spinner carbsFoodSpinner3;
    TextView textviewGramsOfCarbs3;

    TextView meal5CTextViewResult;
    Spinner carbsFoodSpinner4;
    TextView textviewGramsOfCarbs4;

    TextView meal6CTextViewResult;
    Spinner carbsFoodSpinner5;
    TextView textviewGramsOfCarbs5;
    Spinner FatsFoodSpinner2;
    TextView meal3FTextViewResult;
    TextView textviewGramsOfFats2;
    Spinner FatsFoodSpinner3;
    TextView meal4FTextViewResult;
    TextView textviewGramsOfFats3;
    Spinner FatsFoodSpinner4;
    TextView meal5FTextViewResult;
    TextView textviewGramsOfFats4;
    Spinner FatsFoodSpinner5;
    TextView meal6FTextViewResult;
    TextView textviewGramsOfFats5;
    Spinner FruitsSpinner2;
    TextView meal3VTextViewResult;
    TextView textviewGramsOfFruits2;

    Spinner FruitsSpinner3;
    TextView meal4VTextViewResult;
    TextView textviewGramsOfFruits3;

    Spinner FruitsSpinner4;
    TextView meal5VTextViewResult;
    TextView textviewGramsOfFruits4;

    Spinner FruitsSpinner5;
    TextView meal6VTextViewResult;
    TextView textviewGramsOfFruits5;

    Spinner vegetableSpinner2;

    TextView meal3VVTextViewResult;
    TextView textViewGramsOfVegetables2;

    Spinner vegetableSpinner3;

    TextView meal4VVTextViewResult;
    TextView textViewGramsOfVegetables3;

    Spinner vegetableSpinner4;

    TextView meal5VVTextViewResult;
    TextView textViewGramsOfVegetables4;

    Spinner vegetableSpinner5;

    TextView meal6VVTextViewResult;
    TextView textViewGramsOfVegetables5;
    private Notification.Builder dataset;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize EditText and TextView
        nameEditText = findViewById(R.id.Name);
        genderSpinner = findViewById(R.id.GenderSpinner);
        ageEditText = findViewById(R.id.Age);
        heightEditText = findViewById(R.id.Height);
        weightEditText = findViewById(R.id.Weight);
        calculatebmrButton = findViewById(R.id.CalculateBMRButton);
        meal1ProteinSpinner = findViewById(R.id.meal1ProteinSpinner);
        meal2ProteinSpinner = findViewById(R.id.meal2ProteinSpinner);
        meal3ProteinSpinner = findViewById(R.id.meal3ProteinSpinner);
        meal4ProteinSpinner = findViewById(R.id.meal4ProteinSpinner);
        meal5ProteinSpinner = findViewById(R.id.meal5ProteinSpinner);
        meal6ProteinSpinner = findViewById(R.id.meal6ProteinSpinner);
        meal1CarbsSpinner = findViewById(R.id.meal1CarbsSpinner);
        meal1CarbsSpinner = findViewById(R.id.meal1CarbsSpinner);
        meal2CarbsSpinner = findViewById(R.id.meal2CarbsSpinner);
        meal3CarbsSpinner = findViewById(R.id.meal3CarbsSpinner);
        meal4CarbsSpinner = findViewById(R.id.meal4CarbsSpinner);
        meal5CarbsSpinner = findViewById(R.id.meal5CarbsSpinner);
        meal6CarbsSpinner = findViewById(R.id.meal6CarbsSpinner);
        meal1fatsSpinner = findViewById(R.id.meal1fatsSpinner);
        meal2fatsSpinner = findViewById(R.id.meal2fatsSpinner);
        meal3fatsSpinner = findViewById(R.id.meal3fatsSpinner);
        meal4fatsSpinner = findViewById(R.id.meal4fatsSpinner);
        meal5fatsSpinner = findViewById(R.id.meal5fatsSpinner);
        meal6fatsSpinner = findViewById(R.id.meal6fatsSpinner);
        meal1fruitsSpinner = findViewById(R.id.meal1fruitsSpinner);
        meal2fruitsSpinner = findViewById(R.id.meal2fruitsSpinner);
        meal3fruitsSpinner = findViewById(R.id.meal3fruitsSpinner);
        meal4fruitsSpinner = findViewById(R.id.meal4fruitsSpinner);
        meal5fruitsSpinner = findViewById(R.id.meal5fruitsSpinner);
        meal6fruitsSpinner = findViewById(R.id.meal6fruitsSpinner);
        meal1vegetableSpinner = findViewById(R.id.meal1vegetableSpinner);
        meal2vegetableSpinner = findViewById(R.id.meal2vegetableSpinner);
        meal3vegetableSpinner = findViewById(R.id.meal3vegetableSpinner);
        meal4vegetableSpinner = findViewById(R.id.meal4vegetableSpinner);
        meal5vegetableSpinner = findViewById(R.id.meal5vegetableSpinner);
        meal6vegetableSpinner = findViewById(R.id.meal6vegetableSpinner);
        bmrIntakeEditText = findViewById(R.id.BMRINTAKE);
        noOfMealsEditText = findViewById(R.id.NOOFMEALS);
        meal1proteinGramsEditText = findViewById(R.id.meal1ProteinGramsEditText);
        meal2ProteinGramsEditText = findViewById(R.id.meal2ProteinGramsEditText);
        meal3ProteinGramsEditText = findViewById(R.id.meal3ProteinGramsEditText);
        meal4ProteinGramsEditText = findViewById(R.id.meal4ProteinGramsEditText);
        meal5ProteinGramsEditText = findViewById(R.id.meal5ProteinGramsEditText);
        meal6ProteinGramsEditText = findViewById(R.id.meal6ProteinGramsEditText);
        meal1CarbsGramEditText = findViewById(R.id.meal1CarbsGramEditText);
        meal2CarbsGramEditText = findViewById(R.id.meal2CarbsGramEditText);
        meal3CarbsGramEditText = findViewById(R.id.meal3CarbsGramEditText);
        meal4CarbsGramEditText = findViewById(R.id.meal4CarbsGramEditText);
        meal5CarbsGramEditText = findViewById(R.id.meal5CarbsGramEditText);
        meal6CarbsGramEditText = findViewById(R.id.meal6CarbsGramEditText);
        meal1FatsGramEditText = findViewById(R.id.meal1FatsGramEditText);
        meal2FatsGramEditText = findViewById(R.id.meal2FatsGramEditText);
        meal3FatsGramEditText = findViewById(R.id.meal3FatsGramEditText);
        meal4FatsGramEditText = findViewById(R.id.meal4FatsGramEditText);
        meal5FatsGramEditText = findViewById(R.id.meal5FatsGramEditText);
        meal6FatsGramEditText = findViewById(R.id.meal6FatsGramEditText);
        meal1FruitsGramEditText = findViewById(R.id.meal1FruitsGramEditText);
        meal2FruitsGramEditText = findViewById(R.id.meal2FruitsGramEditText);
        meal3FruitsGramEditText = findViewById(R.id.meal3FruitsGramEditText);
        meal4FruitsGramEditText = findViewById(R.id.meal4FruitsGramEditText);
        meal5FruitsGramEditText = findViewById(R.id.meal5FruitsGramEditText);
        meal6FruitsGramEditText = findViewById(R.id.meal6FruitsGramEditText);
        meal1VegetableGramEditText = findViewById(R.id.meal1VegetablesGramEditText);
        meal2VegetableGramEditText = findViewById(R.id.meal2VegetablesGramEditText);
        meal3VegetableGramEditText = findViewById(R.id.meal3VegetablesGramEditText);
        meal4VegetableGramEditText = findViewById(R.id.meal4VegetablesGramEditText);
        meal5VegetableGramEditText = findViewById(R.id.meal5VegetablesGramEditText);
        meal6VegetableGramEditText = findViewById(R.id.meal6VegetablesGramEditText);
        // In your activity or fragment, find the PieChart by its ID
        // Initialize proteins, carbs, fats, fruitsVegetables, and calories arrays
        proteinsValues = new EditText[]{findViewById(R.id.number4), findViewById(R.id.number9),
                findViewById(R.id.number14), findViewById(R.id.number19),
                findViewById(R.id.number24), findViewById(R.id.number29)};
        carbsValues = new EditText[]{findViewById(R.id.number5), findViewById(R.id.number10),
                findViewById(R.id.number15), findViewById(R.id.number20),
                findViewById(R.id.number25), findViewById(R.id.number30)};
        fatsValues = new EditText[]{findViewById(R.id.number6), findViewById(R.id.number11),
                findViewById(R.id.number16), findViewById(R.id.number21),
                findViewById(R.id.number26), findViewById(R.id.number31)};
        fruitsVegetablesValues = new EditText[]{findViewById(R.id.number7), findViewById(R.id.number12),
                findViewById(R.id.number17), findViewById(R.id.number22),
                findViewById(R.id.number27), findViewById(R.id.number32)};
        VegetablesValues = new EditText[]{findViewById(R.id.number34), findViewById(R.id.number35),
                findViewById(R.id.number36), findViewById(R.id.number37),
                findViewById(R.id.number38), findViewById(R.id.number39)};
        caloriesValues = new EditText[]{findViewById(R.id.number8), findViewById(R.id.number13),
                findViewById(R.id.number18), findViewById(R.id.number23),
                findViewById(R.id.number28), findViewById(R.id.number33)};
        bmrTextView = findViewById(R.id.CalculateBMRButton);
        ArrayAdapter<String> proteinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getProteinFoodItems());
        proteinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal1ProteinSpinner.setAdapter(proteinAdapter);
        meal2ProteinSpinner.setAdapter(proteinAdapter);
        meal3ProteinSpinner.setAdapter(proteinAdapter);
        meal4ProteinSpinner.setAdapter(proteinAdapter);
        meal5ProteinSpinner.setAdapter(proteinAdapter);
        meal6ProteinSpinner.setAdapter(proteinAdapter);
        ArrayAdapter<String> carbsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getCarbsFoodItems());
        carbsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal1CarbsSpinner.setAdapter(carbsAdapter);
        meal2CarbsSpinner.setAdapter(carbsAdapter);
        meal3CarbsSpinner.setAdapter(carbsAdapter);
        meal4CarbsSpinner.setAdapter(carbsAdapter);
        meal5CarbsSpinner.setAdapter(carbsAdapter);
        meal6CarbsSpinner.setAdapter(carbsAdapter);
        ArrayAdapter<String> fatsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getFatsFoodItems());
        fatsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal1fatsSpinner.setAdapter(fatsAdapter);
        meal2fatsSpinner.setAdapter(fatsAdapter);
        meal3fatsSpinner.setAdapter(fatsAdapter);
        meal4fatsSpinner.setAdapter(fatsAdapter);
        meal5fatsSpinner.setAdapter(fatsAdapter);
        meal6fatsSpinner.setAdapter(fatsAdapter);
        ArrayAdapter<String> fruitsVegetablesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getFruitsVegetablesFoodItems());
        fruitsVegetablesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal1fruitsSpinner.setAdapter(fruitsVegetablesAdapter);
        meal2fruitsSpinner.setAdapter(fruitsVegetablesAdapter);
        meal3fruitsSpinner.setAdapter(fruitsVegetablesAdapter);
        meal4fruitsSpinner.setAdapter(fruitsVegetablesAdapter);
        meal5fruitsSpinner.setAdapter(fruitsVegetablesAdapter);
        meal6fruitsSpinner.setAdapter(fruitsVegetablesAdapter);

        ArrayAdapter<String> VegetablesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getVegetablesFoodItems());
        VegetablesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal1vegetableSpinner.setAdapter(VegetablesAdapter);
        meal2vegetableSpinner.setAdapter(VegetablesAdapter);
        meal3vegetableSpinner.setAdapter(VegetablesAdapter);
        meal4vegetableSpinner.setAdapter(VegetablesAdapter);
        meal5vegetableSpinner.setAdapter(VegetablesAdapter);
        meal6vegetableSpinner.setAdapter(VegetablesAdapter);
        addTextWatcherToProteinsAndCalories(proteinsValues[0], caloriesValues[0]);
        addTextWatcherToCarbsAndCalories(carbsValues[0], caloriesValues[0]);
        addTextWatcherToFatsAndCalories(fatsValues[0], caloriesValues[0]);
        addTextWatcherToFruitsVegetablesAndCalories(fruitsVegetablesValues[0], caloriesValues[0]);
        addTextWatcherToVegetablesAndCalories(VegetablesValues[0], caloriesValues[0]);
        addTextWatcherToProteinsAndCaloriesMeal2(proteinsValues[1], caloriesValues[1]);
        addTextWatcherToCarbsAndCaloriesMeal2(carbsValues[1], caloriesValues[1]);
        addTextWatcherToFatsAndCaloriesMeal2(fatsValues[1], caloriesValues[1]);
        addTextWatcherToFruitsVegetablesAndCaloriesMeal2(fruitsVegetablesValues[1], caloriesValues[1]);
        addTextWatcherToVegetablesAndCaloriesMeal2(VegetablesValues[1], caloriesValues[1]);
        addTextWatcherToProteinsAndCaloriesMeal3(proteinsValues[2], caloriesValues[2]);
        addTextWatcherToCarbsAndCaloriesMeal3(carbsValues[2], caloriesValues[2]);
        addTextWatcherToFatsAndCaloriesMeal3(fatsValues[2], caloriesValues[2]);
        addTextWatcherToFruitsVegetablesAndCaloriesMeal3(fruitsVegetablesValues[2], caloriesValues[2]);
        addTextWatcherToVegetablesAndCaloriesMeal3(VegetablesValues[2], caloriesValues[2]);
        addTextWatcherToProteinsAndCaloriesMeal4(proteinsValues[3], caloriesValues[3]);
        addTextWatcherToCarbsAndCaloriesMeal4(carbsValues[3], caloriesValues[3]);
        addTextWatcherToFatsAndCaloriesMeal4(fatsValues[3], caloriesValues[3]);
        addTextWatcherToFruitsVegetablesAndCaloriesMeal4(fruitsVegetablesValues[3], caloriesValues[3]);
        addTextWatcherToVegetablesAndCaloriesMeal4(VegetablesValues[3], caloriesValues[3]);
        addTextWatcherToProteinsAndCaloriesMeal5(proteinsValues[4], caloriesValues[4]);
        addTextWatcherToCarbsAndCaloriesMeal5(carbsValues[4], caloriesValues[4]);
        addTextWatcherToFatsAndCaloriesMeal5(fatsValues[4], caloriesValues[4]);
        addTextWatcherToFruitsVegetablesAndCaloriesMeal5(fruitsVegetablesValues[4], caloriesValues[4]);
        addTextWatcherToVegetablesAndCaloriesMeal5(VegetablesValues[4], caloriesValues[4]);
        addTextWatcherToProteinsAndCaloriesMeal6(proteinsValues[5], caloriesValues[5]);
        addTextWatcherToCarbsAndCaloriesMeal6(carbsValues[5], caloriesValues[5]);
        addTextWatcherToFatsAndCaloriesMeal6(fatsValues[5], caloriesValues[5]);
        addTextWatcherToFruitsVegetablesAndCaloriesMeal6(fruitsVegetablesValues[5], caloriesValues[5]);
        addTextWatcherToVegetablesAndCaloriesMeal6(VegetablesValues[5], caloriesValues[5]);




        findViewById(R.id.CalculateBMRButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMR(); // Call your BMR calculation method
            }
        });
        // Set onClickListener for the "Clear" button
        findViewById(R.id.clearBMRButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields(); // Call method to clear fields
            }
        });

        // ... (similar code for other meal spinners)
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View Object,int position, long id) {
                // Get the selected gender
                selectedGender = parentView.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here for now
            }
        });
        meal1PTextViewResult = findViewById(R.id.meal1PtextViewResult);
        proteinFoodSpinner = findViewById(R.id.meal1ProteinSpinner);
        textviewGramsOfProtein = findViewById(R.id.textViewGramsOfProtein);


        proteinFoodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfprotein();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        meal1CTextViewResult = findViewById(R.id.meal1CtextViewResult);
        carbsFoodSpinner = findViewById(R.id.meal1CarbsSpinner);
        textviewGramsOfCarbs = findViewById(R.id.textViewGramsOfCarbs);

        carbsFoodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfCarbs();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        FatsFoodSpinner = findViewById(R.id.meal1fatsSpinner);
        meal1FTextViewResult = findViewById(R.id.meal1FtextViewResult);
        textviewGramsOfFats = findViewById(R.id.textViewGramsOfFats);

        FatsFoodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFats();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        FruitsSpinner = findViewById(R.id.meal1fruitsSpinner);
        meal1VTextViewResult = findViewById(R.id.meal1VtextViewResult);
        textviewGramsOfFruits = findViewById(R.id.textViewGramsOfFruits);

        FruitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFruits();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        vegetableSpinner = findViewById(R.id.meal1vegetableSpinner);
        meal1VVTextViewResult = findViewById(R.id.meal1VVtextViewResult);
        textViewGramsOfVegetables = findViewById(R.id.textViewGramsOfVegetables);
        vegetableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfVegetables();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        meal2PTextViewResult = findViewById(R.id.meal2PtextViewResult);
        proteinFoodSpinner1 = findViewById(R.id.meal2ProteinSpinner);
        textviewGramsOfProtein1 = findViewById(R.id.textViewGramsOfProtein1);

        proteinFoodSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfprotein1();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        meal2CTextViewResult = findViewById(R.id.meal2CtextViewResult);
        carbsFoodSpinner1 = findViewById(R.id.meal2CarbsSpinner);
        textviewGramsOfCarbs1 = findViewById(R.id.textViewGramsOfCarbs1);

        carbsFoodSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfCarbs1();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        FatsFoodSpinner1 = findViewById(R.id.meal2fatsSpinner);
        meal2FTextViewResult = findViewById(R.id.meal2FtextViewResult);
        textviewGramsOfFats1 = findViewById(R.id.textViewGramsOfFats1);

        FatsFoodSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFats1();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        FruitsSpinner1 = findViewById(R.id.meal2fruitsSpinner);
        meal2VTextViewResult = findViewById(R.id.meal2VtextViewResult);
        textviewGramsOfFruits1 = findViewById(R.id.textViewGramsOfFruits1);

        FruitsSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFruits1();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        vegetableSpinner1 = findViewById(R.id.meal2vegetableSpinner);
        meal2VVTextViewResult = findViewById(R.id.meal2VVtextViewResult);
        textViewGramsOfVegetables1 = findViewById(R.id.textViewGramsOfVegetables1);
        vegetableSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfVegetables1();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        meal3PTextViewResult = findViewById(R.id.meal3PtextViewResult);
        proteinFoodSpinner2 = findViewById(R.id.meal3ProteinSpinner);
        textviewGramsOfProtein2 = findViewById(R.id.textViewGramsOfProtein2);


        proteinFoodSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfprotein2();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        meal4PTextViewResult = findViewById(R.id.meal4PtextViewResult);
        proteinFoodSpinner3 = findViewById(R.id.meal4ProteinSpinner);
        textviewGramsOfProtein3 = findViewById(R.id.textViewGramsOfProtein3);


        proteinFoodSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfprotein3();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        meal5PTextViewResult = findViewById(R.id.meal5PtextViewResult);
        proteinFoodSpinner4 = findViewById(R.id.meal5ProteinSpinner);
        textviewGramsOfProtein4 = findViewById(R.id.textViewGramsOfProtein4);


        proteinFoodSpinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfprotein4();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        meal6PTextViewResult = findViewById(R.id.meal6PtextViewResult);
        proteinFoodSpinner5 = findViewById(R.id.meal6ProteinSpinner);
        textviewGramsOfProtein5 = findViewById(R.id.textViewGramsOfProtein5);


        proteinFoodSpinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfprotein5();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        meal3CTextViewResult = findViewById(R.id.meal3CtextViewResult);
        carbsFoodSpinner2 = findViewById(R.id.meal3CarbsSpinner);
        textviewGramsOfCarbs2 = findViewById(R.id.textViewGramsOfCarbs2);

        carbsFoodSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfCarbs2();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });


        meal4CTextViewResult = findViewById(R.id.meal4CtextViewResult);
        carbsFoodSpinner3 = findViewById(R.id.meal4CarbsSpinner);
        textviewGramsOfCarbs3 = findViewById(R.id.textViewGramsOfCarbs3);

        carbsFoodSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfCarbs3();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });


        meal5CTextViewResult = findViewById(R.id.meal5CtextViewResult);
        carbsFoodSpinner4 = findViewById(R.id.meal5CarbsSpinner);
        textviewGramsOfCarbs4 = findViewById(R.id.textViewGramsOfCarbs4);

        carbsFoodSpinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfCarbs4();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });


        meal6CTextViewResult = findViewById(R.id.meal6CtextViewResult);
        carbsFoodSpinner5 = findViewById(R.id.meal6CarbsSpinner);
        textviewGramsOfCarbs5 = findViewById(R.id.textViewGramsOfCarbs5);

        carbsFoodSpinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfCarbs5();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        FatsFoodSpinner2 = findViewById(R.id.meal3fatsSpinner);
        meal3FTextViewResult = findViewById(R.id.meal3FtextViewResult);
        textviewGramsOfFats2 = findViewById(R.id.textViewGramsOfFats2);

        FatsFoodSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFats2();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        FatsFoodSpinner3 = findViewById(R.id.meal4fatsSpinner);
        meal4FTextViewResult = findViewById(R.id.meal4FtextViewResult);
        textviewGramsOfFats3 = findViewById(R.id.textViewGramsOfFats3);

        FatsFoodSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFats3();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        FatsFoodSpinner4 = findViewById(R.id.meal5fatsSpinner);
        meal5FTextViewResult = findViewById(R.id.meal5FtextViewResult);
        textviewGramsOfFats4 = findViewById(R.id.textViewGramsOfFats4);

        FatsFoodSpinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFats4();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        FatsFoodSpinner5 = findViewById(R.id.meal6fatsSpinner);
        meal6FTextViewResult = findViewById(R.id.meal6FtextViewResult);
        textviewGramsOfFats5 = findViewById(R.id.textViewGramsOfFats5);

        FatsFoodSpinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFats5();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        FruitsSpinner2 = findViewById(R.id.meal3fruitsSpinner);
        meal3VTextViewResult = findViewById(R.id.meal3VtextViewResult);
        textviewGramsOfFruits2 = findViewById(R.id.textViewGramsOfFruits2);

        FruitsSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFruits2();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        FruitsSpinner3 = findViewById(R.id.meal4fruitsSpinner);
        meal4VTextViewResult = findViewById(R.id.meal4VtextViewResult);
        textviewGramsOfFruits3 = findViewById(R.id.textViewGramsOfFruits3);

        FruitsSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFruits3();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        FruitsSpinner4 = findViewById(R.id.meal5fruitsSpinner);
        meal5VTextViewResult = findViewById(R.id.meal5VtextViewResult);
        textviewGramsOfFruits4 = findViewById(R.id.textViewGramsOfFruits4);

        FruitsSpinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFruits4();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        FruitsSpinner5 = findViewById(R.id.meal6fruitsSpinner);
        meal6VTextViewResult = findViewById(R.id.meal6VtextViewResult);
        textviewGramsOfFruits5 = findViewById(R.id.textViewGramsOfFruits5);

        FruitsSpinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfFruits5();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        vegetableSpinner2 = findViewById(R.id.meal3vegetableSpinner);
        meal3VVTextViewResult = findViewById(R.id.meal3VVtextViewResult);
        textViewGramsOfVegetables2 = findViewById(R.id.textViewGramsOfVegetables2);
        vegetableSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfVegetables2();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        vegetableSpinner3 = findViewById(R.id.meal4vegetableSpinner);
        meal4VVTextViewResult = findViewById(R.id.meal4VVtextViewResult);
        textViewGramsOfVegetables3 = findViewById(R.id.textViewGramsOfVegetables3);
        vegetableSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfVegetables3();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        vegetableSpinner4 = findViewById(R.id.meal5vegetableSpinner);
        meal5VVTextViewResult = findViewById(R.id.meal5VVtextViewResult);
        textViewGramsOfVegetables4 = findViewById(R.id.textViewGramsOfVegetables4);
        vegetableSpinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfVegetables4();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        vegetableSpinner5 = findViewById(R.id.meal6vegetableSpinner);
        meal6VVTextViewResult = findViewById(R.id.meal6VVtextViewResult);
        textViewGramsOfVegetables5 = findViewById(R.id.textViewGramsOfVegetables5);
        vegetableSpinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateAndDisplayGramsOfVegetables5();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        askPermissions();
        btngeneratePDF = findViewById(R.id.generatePDF);
        btngeneratePDF.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                generatePDF();
            }
        });

    }
    private void askPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    private void generatePDF() {
        PdfDocument document = new PdfDocument();

        try {
            PdfDocument.PageInfo pageInfo1 = new PdfDocument.PageInfo.Builder(1300, 1920, 1).create();
            PdfDocument.Page page1 = document.startPage(pageInfo1);
            Canvas canvas1 = page1.getCanvas();
            Paint paint1 = new Paint();
            paint1.setColor(Color.BLACK);
            paint1.setTextSize(25);

            String name = nameEditText.getText().toString();
            String age = ageEditText.getText().toString();
            String height = heightEditText.getText().toString();
            String weight = weightEditText.getText().toString();

            String numberOfMeals = noOfMealsEditText.getText().toString();

            TextView meal1PTextViewResult = findViewById(R.id.meal1PtextViewResult);
            Spinner proteinFoodSpinner = findViewById(R.id.meal1ProteinSpinner);
            String selectedProteinFood = "";
            double gramsOfProtein = 0;

            if (proteinFoodSpinner.getSelectedItem() != null) {
                selectedProteinFood = proteinFoodSpinner.getSelectedItem().toString();
                // Check if "select_protein_food" is selected
                if ("Select_Protein_Food".equals(selectedProteinFood)) {
                    selectedProteinFood = "";  // Set to an empty string
                    gramsOfProtein = 0;  // Set gramsOfProtein to 0
                } else {
                    double totalCalories1 = Double.parseDouble(meal1PTextViewResult.getText().toString());
                    gramsOfProtein = calculateGramsFromCalories1(totalCalories1, selectedProteinFood);
                }
            }


            TextView meal1CTextViewResult = findViewById(R.id.meal1CtextViewResult);
            Spinner carbsFoodSpinner = findViewById(R.id.meal1CarbsSpinner);
            String selectedCarbsFood = "";
            double gramsOfCarbs = 0;

            if (carbsFoodSpinner.getSelectedItem() != null) {
                selectedCarbsFood = carbsFoodSpinner.getSelectedItem().toString();  // Corrected from proteinFoodSpinner
                // Check if "Select_Carbs_Food" is selected
                if ("Select_Carbs_Food".equals(selectedCarbsFood)) {
                    selectedCarbsFood = "";  // Set to an empty string
                    gramsOfCarbs = 0;
                } else {
                    double totalCalories2 = Double.parseDouble(meal1CTextViewResult.getText().toString());
                    gramsOfCarbs = calculateGramsFromCalories2(totalCalories2, selectedCarbsFood);  // Corrected from selectedProteinFood
                }
            }

            TextView meal1FTextViewResult = findViewById(R.id.meal1FtextViewResult);
            Spinner fatsFoodSpinner = findViewById(R.id.meal1fatsSpinner);
            String selectedFatsFood = "";
            double gramsOfFats = 0;

            if (fatsFoodSpinner.getSelectedItem() != null) {
                selectedFatsFood = fatsFoodSpinner.getSelectedItem().toString();
                // Check if "Select_Fats_Food" is selected
                if ("Select_Fat_Food".equals(selectedFatsFood)) {
                    selectedFatsFood = "";  // Set to an empty string
                    gramsOfFats = 0;  // Set gramsOfFats to 0
                } else {
                    double totalCalories3 = Double.parseDouble(meal1FTextViewResult.getText().toString());
                    gramsOfFats = calculateGramsFromCalories3(totalCalories3, selectedFatsFood);
                }
            }

            TextView meal1VTextViewResult = findViewById(R.id.meal1VtextViewResult);
            Spinner fruitsSpinner = findViewById(R.id.meal1fruitsSpinner);
            String selectedFruit = "";
            double gramsOfFruits = 0;

            if (fruitsSpinner.getSelectedItem() != null) {
                selectedFruit = fruitsSpinner.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Fruits_and_Vegetables".equals(selectedFruit)) {
                    selectedFruit = "";  // Set to an empty string
                    gramsOfFruits = 0;  // Set gramsOfFruits to 0
                } else {
                    double totalCalories4 = Double.parseDouble(meal1VTextViewResult.getText().toString());
                    gramsOfFruits = calculateGramsFromCalories4(totalCalories4, selectedFruit);
                }
            }

            TextView meal1VVTextViewResult = findViewById(R.id.meal1VVtextViewResult);
            Spinner vegetableSpinner = findViewById(R.id.meal1vegetableSpinner);
            String selectedVegetable = "";
            double gramsOfVegetable = 0;

            if (vegetableSpinner.getSelectedItem() != null) {
                selectedVegetable = vegetableSpinner.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Vegetables".equals(selectedVegetable)) {
                    selectedVegetable = "";  // Set to an empty string
                    gramsOfVegetable = 0;  // Set gramsOfFruits to 0
                } else {
                    double totalCalories24 = Double.parseDouble(meal1VVTextViewResult.getText().toString());
                    gramsOfVegetable = calculateGramsFromCalories5(totalCalories24, selectedVegetable);
                }
            }


            TextView meal2PTextViewResult = findViewById(R.id.meal2PtextViewResult);
            Spinner proteinFoodSpinner1 = findViewById(R.id.meal2ProteinSpinner);
            String selectedProteinFood1 = "";
            double gramsOfProtein1 = 0;

            if (proteinFoodSpinner1.getSelectedItem() != null) {
                selectedProteinFood1 = proteinFoodSpinner1.getSelectedItem().toString();
                // Check if "Select_Protein_Food" is selected
                if ("Select_Protein_Food".equals(selectedProteinFood1)) {
                    selectedProteinFood1 = "";  // Set to an empty string
                    gramsOfProtein1 = 0;  // Set gramsOfProtein1 to 0
                } else {
                    double totalCalories4 = Double.parseDouble(meal2PTextViewResult.getText().toString());
                    gramsOfProtein1 = calculateGramsFromCalories1(totalCalories4, selectedProteinFood1);
                }
            }


            TextView meal2CTextViewResult = findViewById(R.id.meal2CtextViewResult);
            Spinner carbsFoodSpinner1 = findViewById(R.id.meal2CarbsSpinner);
            String selectedCarbsFood1 = "";
            double gramsOfCarbs1 = 0;

            if (carbsFoodSpinner1.getSelectedItem() != null) {
                selectedCarbsFood1 = carbsFoodSpinner1.getSelectedItem().toString();
                // Check if "Select_Carbs_Food" is selected
                if ("Select_Carbs_Food".equals(selectedCarbsFood1)) {
                    selectedCarbsFood1 = "";  // Set to an empty string
                    gramsOfCarbs1 = 0;  // Set gramsOfCarbs1 to 0
                } else {
                    double totalCalories5 = Double.parseDouble(meal2CTextViewResult.getText().toString());
                    gramsOfCarbs1 = calculateGramsFromCalories2(totalCalories5, selectedCarbsFood1);
                }
            }


            TextView meal2FTextViewResult = findViewById(R.id.meal2FtextViewResult);
            Spinner fatsFoodSpinner1 = findViewById(R.id.meal2fatsSpinner);
            String selectedFatsFood1 = "";
            double gramsOfFats1 = 0;

            if (fatsFoodSpinner1.getSelectedItem() != null) {
                selectedFatsFood1 = fatsFoodSpinner1.getSelectedItem().toString();
                // Check if "Select_Fat_Food" is selected
                if ("Select_Fat_Food".equals(selectedFatsFood1)) {
                    selectedFatsFood1 = "";  // Set to an empty string
                    gramsOfFats1 = 0;  // Set gramsOfFats1 to 0
                } else {
                    double totalCalories6 = Double.parseDouble(meal2FTextViewResult.getText().toString());
                    gramsOfFats1 = calculateGramsFromCalories3(totalCalories6, selectedFatsFood1);
                }
            }
            TextView meal2VTextViewResult = findViewById(R.id.meal2VtextViewResult);
            Spinner fruitsSpinner1 = findViewById(R.id.meal2fruitsSpinner);
            String selectedFruit1 = "";
            double gramsOfFruits1 = 0;

            if (fruitsSpinner1.getSelectedItem() != null) {
                selectedFruit1 = fruitsSpinner1.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Fruits_and_Vegetables".equals(selectedFruit1)) {
                    selectedFruit1 = "";  // Set to an empty string
                    gramsOfFruits1 = 0;  // Set gramsOfFruits1 to 0
                } else {
                    double totalCalories7 = Double.parseDouble(meal2VTextViewResult.getText().toString());
                    gramsOfFruits1 = calculateGramsFromCalories4(totalCalories7, selectedFruit1);
                }
            }

            TextView meal2VVTextViewResult = findViewById(R.id.meal2VVtextViewResult);
            Spinner vegetableSpinner1 = findViewById(R.id.meal2vegetableSpinner);
            String selectedVegetable1 = "";
            double gramsOfVegetable1 = 0;

            if (vegetableSpinner1.getSelectedItem() != null) {
                selectedVegetable1 = vegetableSpinner1.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Vegetables".equals(selectedVegetable1)) {
                    selectedVegetable1 = "";  // Set to an empty string
                    gramsOfVegetable1 = 0;  // Set gramsOfFruits to 0
                } else {
                    double totalCalories25 = Double.parseDouble(meal2VVTextViewResult.getText().toString());
                    gramsOfVegetable1 = calculateGramsFromCalories5(totalCalories25, selectedVegetable1);
                }
            }


            TextView meal3PTextViewResult = findViewById(R.id.meal3PtextViewResult);
            Spinner proteinFoodSpinner2 = findViewById(R.id.meal3ProteinSpinner);
            String selectedProteinFood2 = "";
            double gramsOfProtein2 = 0;

            if (proteinFoodSpinner2.getSelectedItem() != null) {
                selectedProteinFood2 = proteinFoodSpinner2.getSelectedItem().toString();
                // Check if "Select_Protein_Food" is selected
                if ("Select_Protein_Food".equals(selectedProteinFood2)) {
                    selectedProteinFood2 = "";  // Set to an empty string
                    gramsOfProtein2 = 0;  // Set gramsOfProtein2 to 0
                } else {
                    double totalCalories8 = Double.parseDouble(meal3PTextViewResult.getText().toString());
                    gramsOfProtein2 = calculateGramsFromCalories1(totalCalories8, selectedProteinFood2);
                }
            }
            TextView meal3CTextViewResult = findViewById(R.id.meal3CtextViewResult);
            Spinner carbsFoodSpinner2 = findViewById(R.id.meal3CarbsSpinner);
            String selectedCarbsFood2 = "";
            double gramsOfCarbs2 = 0;

            if (carbsFoodSpinner2.getSelectedItem() != null) {
                selectedCarbsFood2 = carbsFoodSpinner2.getSelectedItem().toString();
                // Check if "Select_Carbs_Food" is selected
                if ("Select_Carbs_Food".equals(selectedCarbsFood2)) {
                    selectedCarbsFood2 = "";  // Set to an empty string
                    gramsOfCarbs2 = 0;  // Set gramsOfCarbs2 to 0
                } else {
                    double totalCalories9 = Double.parseDouble(meal3CTextViewResult.getText().toString());
                    gramsOfCarbs2 = calculateGramsFromCalories2(totalCalories9, selectedCarbsFood2);
                }
            }
            TextView meal3FTextViewResult = findViewById(R.id.meal3FtextViewResult);
            Spinner fatsFoodSpinner2 = findViewById(R.id.meal3fatsSpinner);
            String selectedFatsFood2 = "";
            double gramsOfFats2 = 0;

            if (fatsFoodSpinner2.getSelectedItem() != null) {
                selectedFatsFood2 = fatsFoodSpinner2.getSelectedItem().toString();
                // Check if "Select_Fat_Food" is selected
                if ("Select_Fat_Food".equals(selectedFatsFood2)) {
                    selectedFatsFood2 = "";  // Set to an empty string
                    gramsOfFats2 = 0;  // Set gramsOfFats2 to 0
                } else {
                    double totalCalories10 = Double.parseDouble(meal3FTextViewResult.getText().toString());
                    gramsOfFats2 = calculateGramsFromCalories3(totalCalories10, selectedFatsFood2);
                }
            }
            TextView meal3VTextViewResult = findViewById(R.id.meal3VtextViewResult);
            Spinner FruitsSpinner2 = findViewById(R.id.meal3fruitsSpinner);
            String selectedFruit2 = "";
            double gramsOfFruits2 = 0;

            if (FruitsSpinner2.getSelectedItem() != null) {
                selectedFruit2 = FruitsSpinner2.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Fruits_and_Vegetables".equals(selectedFruit2)) {
                    selectedFruit2 = "";  // Set to an empty string
                    gramsOfFruits2 = 0;  // Set gramsOfFruits2 to 0
                } else {
                    double totalCalories11 = Double.parseDouble(meal3VTextViewResult.getText().toString());
                    gramsOfFruits2 = calculateGramsFromCalories4(totalCalories11, selectedFruit2);
                }
            }

            TextView meal3VVTextViewResult = findViewById(R.id.meal3VVtextViewResult);
            Spinner vegetableSpinner2 = findViewById(R.id.meal3vegetableSpinner);
            String selectedVegetable2 = "";
            double gramsOfVegetable2 = 0;

            if (vegetableSpinner2.getSelectedItem() != null) {
                selectedVegetable2 = vegetableSpinner2.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Vegetables".equals(selectedVegetable2)) {
                    selectedVegetable2 = "";  // Set to an empty string
                    gramsOfVegetable2 = 0;  // Set gramsOfFruits to 0
                } else {
                    double totalCalories26 = Double.parseDouble(meal3VVTextViewResult.getText().toString());
                    gramsOfVegetable2 = calculateGramsFromCalories5(totalCalories26, selectedVegetable2);
                }
            }

            TextView meal4PTextViewResult = findViewById(R.id.meal4PtextViewResult);
            Spinner proteinFoodSpinner3 = findViewById(R.id.meal4ProteinSpinner);
            String selectedProteinFood3 = "";
            double gramsOfProtein3 = 0;

            if (proteinFoodSpinner3.getSelectedItem() != null) {
                selectedProteinFood3 = proteinFoodSpinner3.getSelectedItem().toString();
                // Check if "Select_Protein_Food" is selected
                if ("Select_Protein_Food".equals(selectedProteinFood3)) {
                    selectedProteinFood3 = "";  // Set to an empty string
                    gramsOfProtein3 = 0;  // Set gramsOfProtein3 to 0
                } else {
                    double totalCalories12 = Double.parseDouble(meal4PTextViewResult.getText().toString());
                    gramsOfProtein3 = calculateGramsFromCalories1(totalCalories12, selectedProteinFood3);
                }
            }
            TextView meal4CTextViewResult = findViewById(R.id.meal4CtextViewResult);
            Spinner carbsFoodSpinner3 = findViewById(R.id.meal4CarbsSpinner);
            String selectedCarbsFood3 = "";
            double gramsOfCarbs3 = 0;

            if (carbsFoodSpinner3.getSelectedItem() != null) {
                selectedCarbsFood3 = carbsFoodSpinner3.getSelectedItem().toString();
                // Check if "Select_Carbs_Food" is selected
                if ("Select_Carbs_Food".equals(selectedCarbsFood3)) {
                    selectedCarbsFood3 = "";  // Set to an empty string
                    gramsOfCarbs3 = 0;  // Set gramsOfCarbs3 to 0
                } else {
                    double totalCalories13 = Double.parseDouble(meal4CTextViewResult.getText().toString());
                    gramsOfCarbs3 = calculateGramsFromCalories2(totalCalories13, selectedCarbsFood3);
                }
            }
            TextView meal4FTextViewResult = findViewById(R.id.meal4FtextViewResult);
            Spinner fatsFoodSpinner3 = findViewById(R.id.meal4fatsSpinner);
            String selectedFatsFood3 = "";
            double gramsOfFats3 = 0;

            if (fatsFoodSpinner3.getSelectedItem() != null) {
                selectedFatsFood3 = fatsFoodSpinner3.getSelectedItem().toString();
                // Check if "Select_Fat_Food" is selected
                if ("Select_Fat_Food".equals(selectedFatsFood3)) {
                    selectedFatsFood3 = "";  // Set to an empty string
                    gramsOfFats3 = 0;  // Set gramsOfFats3 to 0
                } else {
                    double totalCalories14 = Double.parseDouble(meal4FTextViewResult.getText().toString());
                    gramsOfFats3 = calculateGramsFromCalories3(totalCalories14, selectedFatsFood3);
                }
            }
            TextView meal4VTextViewResult = findViewById(R.id.meal4VtextViewResult);
            Spinner fruitsSpinner3 = findViewById(R.id.meal4fruitsSpinner);
            String selectedFruit3 = "";
            double gramsOfFruits3 = 0;

            if (fruitsSpinner3.getSelectedItem() != null) {
                selectedFruit3 = fruitsSpinner3.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Fruits_and_Vegetables".equals(selectedFruit3)) {
                    selectedFruit3 = "";  // Set to an empty string
                    gramsOfFruits3 = 0;  // Set gramsOfFruits3 to 0
                } else {
                    double totalCalories15 = Double.parseDouble(meal4VTextViewResult.getText().toString());
                    gramsOfFruits3 = calculateGramsFromCalories4(totalCalories15, selectedFruit3);
                }
            }

            TextView meal4VVTextViewResult = findViewById(R.id.meal4VVtextViewResult);
            Spinner vegetableSpinner3 = findViewById(R.id.meal4vegetableSpinner);
            String selectedVegetable3 = "";
            double gramsOfVegetable3 = 0;

            if (vegetableSpinner3.getSelectedItem() != null) {
                selectedVegetable3 = vegetableSpinner3.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Vegetables".equals(selectedVegetable3)) {
                    selectedVegetable3 = "";  // Set to an empty string
                    gramsOfVegetable3 = 0;  // Set gramsOfFruits to 0
                } else {
                    double totalCalories27 = Double.parseDouble(meal4VVTextViewResult.getText().toString());
                    gramsOfVegetable3 = calculateGramsFromCalories5(totalCalories27, selectedVegetable3);
                }
            }

            TextView meal5PTextViewResult = findViewById(R.id.meal5PtextViewResult);
            Spinner proteinFoodSpinner4 = findViewById(R.id.meal5ProteinSpinner);
            String selectedProteinFood4 = "";
            double gramsOfProtein4 = 0;

            if (proteinFoodSpinner4.getSelectedItem() != null) {
                selectedProteinFood4 = proteinFoodSpinner4.getSelectedItem().toString();
                // Check if "Select_Protein_Food" is selected
                if ("Select_Protein_Food".equals(selectedProteinFood4)) {
                    selectedProteinFood4 = "";  // Set to an empty string
                    gramsOfProtein4 = 0;  // Set gramsOfProtein4 to 0
                } else {
                    double totalCalories16 = Double.parseDouble(meal5PTextViewResult.getText().toString());
                    gramsOfProtein4 = calculateGramsFromCalories1(totalCalories16, selectedProteinFood4);
                }
            }

// Similar code for Carbs food items
            TextView meal5CTextViewResult = findViewById(R.id.meal5CtextViewResult);
            Spinner carbsFoodSpinner4 = findViewById(R.id.meal5CarbsSpinner);
            String selectedCarbsFood4 = "";
            double gramsOfCarbs4 = 0;

            if (carbsFoodSpinner4.getSelectedItem() != null) {
                selectedCarbsFood4 = carbsFoodSpinner4.getSelectedItem().toString();
                // Check if "Select_Carbs_Food" is selected
                if ("Select_Carbs_Food".equals(selectedCarbsFood4)) {
                    selectedCarbsFood4 = "";  // Set to an empty string
                    gramsOfCarbs4 = 0;  // Set gramsOfCarbs4 to 0
                } else {
                    double totalCalories17 = Double.parseDouble(meal5CTextViewResult.getText().toString());
                    gramsOfCarbs4 = calculateGramsFromCalories2(totalCalories17, selectedCarbsFood4);
                }
            }

// Similar code for Fats food items
            TextView meal5FTextViewResult = findViewById(R.id.meal5FtextViewResult);
            Spinner fatsFoodSpinner4 = findViewById(R.id.meal5fatsSpinner);
            String selectedFatsFood4 = "";
            double gramsOfFats4 = 0;

            if (fatsFoodSpinner4.getSelectedItem() != null) {
                selectedFatsFood4 = fatsFoodSpinner4.getSelectedItem().toString();
                // Check if "Select_Fat_Food" is selected
                if ("Select_Fat_Food".equals(selectedFatsFood4)) {
                    selectedFatsFood4 = "";  // Set to an empty string
                    gramsOfFats4 = 0;  // Set gramsOfFats4 to 0
                } else {
                    double totalCalories18 = Double.parseDouble(meal5FTextViewResult.getText().toString());
                    gramsOfFats4 = calculateGramsFromCalories3(totalCalories18, selectedFatsFood4);
                }
            }

// Similar code for Fruits and Vegetables
            TextView meal5VTextViewResult = findViewById(R.id.meal5VtextViewResult);
            Spinner fruitsSpinner4 = findViewById(R.id.meal5fruitsSpinner);
            String selectedFruit4 = "";
            double gramsOfFruits4 = 0;

            if (fruitsSpinner4.getSelectedItem() != null) {
                selectedFruit4 = fruitsSpinner4.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Fruits_and_Vegetables".equals(selectedFruit4)) {
                    selectedFruit4 = "";  // Set to an empty string
                    gramsOfFruits4 = 0;  // Set gramsOfFruits4 to 0
                } else {
                    double totalCalories19 = Double.parseDouble(meal5VTextViewResult.getText().toString());
                    gramsOfFruits4 = calculateGramsFromCalories4(totalCalories19, selectedFruit4);
                }
            }

            TextView meal5VVTextViewResult = findViewById(R.id.meal5VVtextViewResult);
            Spinner vegetableSpinner4 = findViewById(R.id.meal5vegetableSpinner);
            String selectedVegetable4 = "";
            double gramsOfVegetable4 = 0;

            if (vegetableSpinner4.getSelectedItem() != null) {
                selectedVegetable4 = vegetableSpinner4.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Vegetables".equals(selectedVegetable4)) {
                    selectedVegetable4 = "";  // Set to an empty string
                    gramsOfVegetable4 = 0;  // Set gramsOfFruits to 0
                } else {
                    double totalCalories28 = Double.parseDouble(meal5VVTextViewResult.getText().toString());
                    gramsOfVegetable4 = calculateGramsFromCalories5(totalCalories28, selectedVegetable4);
                }
            }


            TextView meal6PTextViewResult = findViewById(R.id.meal6PtextViewResult);
            Spinner proteinFoodSpinner5 = findViewById(R.id.meal6ProteinSpinner);
            String selectedProteinFood5 = "";
            double gramsOfProtein5 = 0;

            if (proteinFoodSpinner5.getSelectedItem() != null) {
                selectedProteinFood5 = proteinFoodSpinner5.getSelectedItem().toString();
                // Check if "Select_Protein_Food" is selected
                if ("Select_Protein_Food".equals(selectedProteinFood5)) {
                    selectedProteinFood5 = "";  // Set to an empty string
                    gramsOfProtein5 = 0;  // Set gramsOfProtein5 to 0
                } else {
                    double totalCalories20 = Double.parseDouble(meal6PTextViewResult.getText().toString());
                    gramsOfProtein5 = calculateGramsFromCalories1(totalCalories20, selectedProteinFood5);
                }
            }
            TextView meal6CTextViewResult = findViewById(R.id.meal6CtextViewResult);
            Spinner carbsFoodSpinner5 = findViewById(R.id.meal6CarbsSpinner);
            String selectedCarbsFood5 = "";
            double gramsOfCarbs5 = 0;

            if (carbsFoodSpinner5.getSelectedItem() != null) {
                selectedCarbsFood5 = carbsFoodSpinner5.getSelectedItem().toString();
                // Check if "Select_Carbs_Food" is selected
                if ("Select_Carbs_Food".equals(selectedCarbsFood5)) {
                    selectedCarbsFood5 = "";  // Set to an empty string
                    gramsOfCarbs5 = 0;  // Set gramsOfCarbs5 to 0
                } else {
                    double totalCalories21 = Double.parseDouble(meal6CTextViewResult.getText().toString());
                    gramsOfCarbs5 = calculateGramsFromCalories2(totalCalories21, selectedCarbsFood5);
                }
            }
            TextView meal6FTextViewResult = findViewById(R.id.meal6FtextViewResult);
            Spinner fatsFoodSpinner5 = findViewById(R.id.meal6fatsSpinner);
            String selectedFatsFood5 = "";
            double gramsOfFats5 = 0;

            if (fatsFoodSpinner5.getSelectedItem() != null) {
                selectedFatsFood5 = fatsFoodSpinner5.getSelectedItem().toString();
                // Check if "Select_Fat_Food" is selected
                if ("Select_Fat_Food".equals(selectedFatsFood5)) {
                    selectedFatsFood5 = "";  // Set to an empty string
                    gramsOfFats5 = 0;  // Set gramsOfFats5 to 0
                } else {
                    double totalCalories22 = Double.parseDouble(meal6FTextViewResult.getText().toString());
                    gramsOfFats5 = calculateGramsFromCalories3(totalCalories22, selectedFatsFood5);
                }
            }
            TextView meal6VTextViewResult = findViewById(R.id.meal6VtextViewResult);
            Spinner FruitsSpinner5 = findViewById(R.id.meal6fruitsSpinner);
            String selectedFruit5 = "";
            double gramsOfFruits5 = 0;

            if (FruitsSpinner5.getSelectedItem() != null) {
                selectedFruit5 = FruitsSpinner5.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Fruits_and_Vegetables".equals(selectedFruit5)) {
                    selectedFruit5 = "";  // Set to an empty string
                    gramsOfFruits5 = 0;  // Set gramsOfFruits5 to 0
                } else {
                    double totalCalories23 = Double.parseDouble(meal6VTextViewResult.getText().toString());
                    gramsOfFruits5 = calculateGramsFromCalories4(totalCalories23, selectedFruit5);
                }
            }

            TextView meal6VVTextViewResult = findViewById(R.id.meal6VVtextViewResult);
            Spinner vegetableSpinner5 = findViewById(R.id.meal6vegetableSpinner);
            String selectedVegetable5 = "";
            double gramsOfVegetable5 = 0;

            if (vegetableSpinner5.getSelectedItem() != null) {
                selectedVegetable5 = vegetableSpinner5.getSelectedItem().toString();
                // Check if "Select_Fruits_and_Vegetables" is selected
                if ("Select_Vegetables".equals(selectedVegetable5)) {
                    selectedVegetable5 = "";  // Set to an empty string
                    gramsOfVegetable5 = 0;  // Set gramsOfFruits to 0
                } else {
                    double totalCalories29 = Double.parseDouble(meal6VVTextViewResult.getText().toString());
                    gramsOfVegetable5 = calculateGramsFromCalories5(totalCalories29, selectedVegetable5);
                }
            }

            double totalProteins1 = calculateProteinsFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);

            double totalProteins2 = calculateProteinsFromProteinFoodItem1(meal2ProteinSpinner, meal2ProteinGramsEditText);

            double totalProteins3 = calculateProteinsFromProteinFoodItem2(meal3ProteinSpinner, meal3ProteinGramsEditText);

            double totalProteins4 = calculateProteinsFromProteinFoodItem3(meal4ProteinSpinner, meal4ProteinGramsEditText);

            double totalProteins5 = calculateProteinsFromProteinFoodItem4(meal5ProteinSpinner, meal5ProteinGramsEditText);

            double totalProteins6 = calculateProteinsFromProteinFoodItem5(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalCarbs1 = calculateCarbsFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);

            double totalCarbs2 = calculateCarbsFromCarbsFoodItem1(meal2CarbsSpinner, meal2CarbsGramEditText);

            double totalCarbs3 = calculateCarbsFromCarbsFoodItem2(meal3CarbsSpinner, meal3CarbsGramEditText);

            double totalCarbs4 = calculateCarbsFromCarbsFoodItem3(meal4CarbsSpinner, meal4CarbsGramEditText);

            double totalCarbs5 = calculateCarbsFromCarbsFoodItem4(meal5CarbsSpinner, meal5CarbsGramEditText);

            double totalCarbs6 = calculateCarbsFromCarbsFoodItem5(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalFats1 = calculateFatsFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);

            double totalFats2 = calculateFatsFromFatsFoodItem1(meal2fatsSpinner, meal2FatsGramEditText);

            double totalFats3 = calculateFatsFromFatsFoodItem2(meal3fatsSpinner, meal3FatsGramEditText);

            double totalFats4 = calculateFatsFromFatsFoodItem3(meal4fatsSpinner, meal4FatsGramEditText);

            double totalFats5 = calculateFatsFromFatsFoodItem4(meal5fatsSpinner, meal5FatsGramEditText);

            double totalFats6 = calculateFatsFromFatsFoodItem5(meal6fatsSpinner, meal6FatsGramEditText);

            double totalFibres1 = calculateFibresFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);

            double totalFibres2 = calculateFibresFromFruitsFoodItem1(meal2fruitsSpinner, meal2FruitsGramEditText);

            double totalFibres3 = calculateFibresFromFruitsFoodItem2(meal3fruitsSpinner, meal3FruitsGramEditText);

            double totalFibres4 = calculateFibresFromFruitsFoodItem3(meal4fruitsSpinner, meal4FruitsGramEditText);

            double totalFibres5 = calculateFibresFromFruitsFoodItem4(meal5fruitsSpinner, meal5FruitsGramEditText);

            double totalFibres6 = calculateFibresFromFruitsFoodItem5(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalCarbs7 = calculateCarbsFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);

            double totalCarbs8 = calculateCarbsFromProteinFoodItem1(meal2ProteinSpinner, meal2ProteinGramsEditText);

            double totalCarbs9 = calculateCarbsFromProteinFoodItem2(meal3ProteinSpinner, meal3ProteinGramsEditText);

            double totalCarbs10 = calculateCarbsFromProteinFoodItem3(meal4ProteinSpinner, meal4ProteinGramsEditText);

            double totalCarbs11 = calculateCarbsFromProteinFoodItem4(meal5ProteinSpinner, meal5ProteinGramsEditText);

            double totalCarbs12 = calculateCarbsFromProteinFoodItem5(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalFats7 = calculateFatsFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);

            double totalFats8 = calculateFatsFromProteinFoodItem1(meal2ProteinSpinner, meal2ProteinGramsEditText);

            double totalFats9 = calculateFatsFromProteinFoodItem2(meal3ProteinSpinner, meal3ProteinGramsEditText);

            double totalFats10 = calculateFatsFromProteinFoodItem3(meal4ProteinSpinner, meal4ProteinGramsEditText);

            double totalFats11 = calculateFatsFromProteinFoodItem4(meal5ProteinSpinner, meal5ProteinGramsEditText);

            double totalFats12 = calculateFatsFromProteinFoodItem5(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalFibres19 = calculateFibresFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);

            double totalFibres20 = calculateFibresFromProteinFoodItem1(meal2ProteinSpinner, meal2ProteinGramsEditText);

            double totalFibres21 = calculateFibresFromProteinFoodItem2(meal3ProteinSpinner, meal3ProteinGramsEditText);

            double totalFibres22 = calculateFibresFromProteinFoodItem3(meal4ProteinSpinner, meal4ProteinGramsEditText);

            double totalFibres23 = calculateFibresFromProteinFoodItem4(meal5ProteinSpinner, meal5ProteinGramsEditText);

            double totalFibres24 = calculateFibresFromProteinFoodItem5(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalProteins7 = calculateproteinsFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);

            double totalProteins8 = calculateproteinsFromCarbsFoodItem1(meal2CarbsSpinner, meal2CarbsGramEditText);

            double totalProteins9 = calculateproteinsFromCarbsFoodItem2(meal3CarbsSpinner, meal3CarbsGramEditText);

            double totalProteins10 = calculateproteinsFromCarbsFoodItem3(meal4CarbsSpinner, meal4CarbsGramEditText);

            double totalProteins11 = calculateproteinsFromCarbsFoodItem4(meal5CarbsSpinner, meal5CarbsGramEditText);

            double totalProteins12 = calculateproteinsFromCarbsFoodItem5(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalFats13 = calculateFatsFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);

            double totalFats14 = calculateFatsFromCarbsFoodItem1(meal2CarbsSpinner, meal2CarbsGramEditText);

            double totalFats15 = calculateFatsFromCarbsFoodItem2(meal3CarbsSpinner, meal3CarbsGramEditText);

            double totalFats16 = calculateFatsFromCarbsFoodItem3(meal4CarbsSpinner, meal4CarbsGramEditText);

            double totalFats17 = calculateFatsFromCarbsFoodItem4(meal5CarbsSpinner, meal5CarbsGramEditText);

            double totalFats18 = calculateFatsFromCarbsFoodItem5(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalFibres7 = calculateFibresFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);

            double totalFibres8 = calculateFibresFromCarbsFoodItem1(meal2CarbsSpinner, meal2CarbsGramEditText);

            double totalFibres9 = calculateFibresFromCarbsFoodItem2(meal3CarbsSpinner, meal3CarbsGramEditText);

            double totalFibres10 = calculateFibresFromCarbsFoodItem3(meal4CarbsSpinner, meal4CarbsGramEditText);

            double totalFibres11 = calculateFibresFromCarbsFoodItem4(meal5CarbsSpinner, meal5CarbsGramEditText);

            double totalFibres12 = calculateFibresFromCarbsFoodItem5(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalProteins13 = calculateProteinsFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);

            double totalProteins14 = calculateProteinsFromFatsFoodItem1(meal2fatsSpinner, meal2FatsGramEditText);

            double totalProteins15 = calculateProteinsFromFatsFoodItem2(meal3fatsSpinner, meal3FatsGramEditText);

            double totalProteins16 = calculateProteinsFromFatsFoodItem3(meal4fatsSpinner, meal4FatsGramEditText);

            double totalProteins17 = calculateProteinsFromFatsFoodItem4(meal5fatsSpinner, meal5FatsGramEditText);

            double totalProteins18 = calculateProteinsFromFatsFoodItem5(meal6fatsSpinner, meal6FatsGramEditText);

            double totalCarbs13 = calculateCarbsFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);

            double totalCarbs14 = calculateCarbsFromFatsFoodItem1(meal2fatsSpinner, meal2FatsGramEditText);

            double totalCarbs15 = calculateCarbsFromFatsFoodItem2(meal3fatsSpinner, meal3FatsGramEditText);

            double totalCarbs16 = calculateCarbsFromFatsFoodItem3(meal4fatsSpinner, meal4FatsGramEditText);

            double totalCarbs17 = calculateCarbsFromFatsFoodItem4(meal5fatsSpinner, meal5FatsGramEditText);

            double totalCarbs18 = calculateCarbsFromFatsFoodItem5(meal6fatsSpinner, meal6FatsGramEditText);

            double totalFibres13 = calculateFibresFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);

            double totalFibres14 = calculateFibresFromFatsFoodItem1(meal2fatsSpinner, meal2FatsGramEditText);

            double totalFibres15 = calculateFibresFromFatsFoodItem2(meal3fatsSpinner, meal3FatsGramEditText);

            double totalFibres16 = calculateFibresFromFatsFoodItem3(meal4fatsSpinner, meal4FatsGramEditText);

            double totalFibres17 = calculateFibresFromFatsFoodItem4(meal5fatsSpinner, meal5FatsGramEditText);

            double totalFibres18 = calculateFibresFromFatsFoodItem5(meal6fatsSpinner, meal6FatsGramEditText);

            double totalProteins19 = calculateProteinsFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);

            double totalProteins20 = calculateProteinsFromFruitsFoodItem1(meal2fruitsSpinner, meal2FruitsGramEditText);

            double totalProteins21 = calculateProteinsFromFruitsFoodItem2(meal3fruitsSpinner, meal3FruitsGramEditText);

            double totalProteins22 = calculateProteinsFromFruitsFoodItem3(meal4fruitsSpinner, meal4FruitsGramEditText);

            double totalProteins23 = calculateProteinsFromFruitsFoodItem4(meal5fruitsSpinner, meal5FruitsGramEditText);

            double totalProteins24 = calculateProteinsFromFruitsFoodItem5(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalCarbs19 = calculateCarbsFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);

            double totalCarbs20 = calculateCarbsFromFruitsFoodItem1(meal2fruitsSpinner, meal2FruitsGramEditText);

            double totalCarbs21 = calculateCarbsFromFruitsFoodItem2(meal3fruitsSpinner, meal3FruitsGramEditText);

            double totalCarbs22 = calculateCarbsFromFruitsFoodItem3(meal4fruitsSpinner, meal4FruitsGramEditText);

            double totalCarbs23 = calculateCarbsFromFruitsFoodItem4(meal5fruitsSpinner, meal5FruitsGramEditText);

            double totalCarbs24 = calculateCarbsFromFruitsFoodItem5(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalFats19 = calculateFatsFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);

            double totalFats20 = calculateFatsFromFruitsFoodItem1(meal2fruitsSpinner, meal2FruitsGramEditText);

            double totalFats21 = calculateFatsFromFruitsFoodItem2(meal3fruitsSpinner, meal3FruitsGramEditText);

            double totalFats22 = calculateFatsFromFruitsFoodItem3(meal4fruitsSpinner, meal4FruitsGramEditText);

            double totalFats23 = calculateFatsFromFruitsFoodItem4(meal5fruitsSpinner, meal5FruitsGramEditText);

            double totalFats24 = calculateFatsFromFruitsFoodItem5(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalFibres25 = calculateFibresFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);

            double totalFibres26 = calculateFibresFromVegetableFoodItem1(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalFibres27 = calculateFibresFromVegetableFoodItem2(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalFibres28 = calculateFibresFromVegetableFoodItem3(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalFibres29 = calculateFibresFromVegetableFoodItem4(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalFibres30 = calculateFibresFromVegetableFoodItem5(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalProtein25 = calculateProteinsFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);

            double totalProtein26 = calculateProteinsFromVegetableFoodItem1(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalProtein27 = calculateProteinsFromVegetableFoodItem2(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalProtein28 = calculateProteinsFromVegetableFoodItem3(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalProtein29 = calculateProteinsFromVegetableFoodItem4(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalProtein30 = calculateProteinsFromVegetableFoodItem5(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalCarbs25 = calculateCarbsFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);

            double totalCarbs26 = calculateCarbsFromVegetableFoodItem1(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalCarbs27 = calculateCarbsFromVegetableFoodItem2(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalCarbs28 = calculateCarbsFromVegetableFoodItem3(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalCarbs29 = calculateCarbsFromVegetableFoodItem4(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalCarbs30 = calculateCarbsFromVegetableFoodItem5(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalFats25 = calculateFatsFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);

            double totalFats26 = calculateFatsFromVegetableFoodItem1(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalFats27 = calculateFatsFromVegetableFoodItem2(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalFats28 = calculateFatsFromVegetableFoodItem3(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalFats29 = calculateFatsFromVegetableFoodItem4(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalFats30 = calculateFatsFromVegetableFoodItem5(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalCalcium1 = calculateCalciumFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalCalcium2 = calculateCalciumFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalCalcium3 = calculateCalciumFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalCalcium4 = calculateCalciumFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalCalcium5 = calculateCalciumFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalCalcium6 = calculateCalciumFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalPhosphorus1 = calculatePhosphorusFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalPhosphorus2 = calculatePhosphorusFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalPhosphorus3 = calculatePhosphorusFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalPhosphorus4 = calculatePhosphorusFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalPhosphorus5 = calculatePhosphorusFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalPhosphorus6 = calculatePhosphorusFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalMagnesium1 = calculateMagnesiumFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalMagnesium2 = calculateMagnesiumFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalMagnesium3 = calculateMagnesiumFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalMagnesium4 = calculateMagnesiumFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalMagnesium5 = calculateMagnesiumFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalMagnesium6 = calculateMagnesiumFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalElectrolyte1 = calculateElectrolyteFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalElectrolyte2 = calculateElectrolyteFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalElectrolyte3 = calculateElectrolyteFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalElectrolyte4 = calculateElectrolyteFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalElectrolyte5 = calculateElectrolyteFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalElectrolyte6 = calculateElectrolyteFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalSodium1 = calculateSodiumFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalSodium2 = calculateSodiumFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalSodium3 = calculateSodiumFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalSodium4 = calculateSodiumFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalSodium5 = calculateSodiumFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalSodium6 = calculateSodiumFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalPotassium1 = calculatePotassiumFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalPotassium2 = calculatePotassiumFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalPotassium3 = calculatePotassiumFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalPotassium4 = calculatePotassiumFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalPotassium5 = calculatePotassiumFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalPotassium6 = calculatePotassiumFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalChloride1 = calculateChlorideFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalChloride2 = calculateChlorideFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalChloride3 = calculateChlorideFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalChloride4 = calculateChlorideFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalChloride5 = calculateChlorideFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalChloride6 = calculateChlorideFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalIron1 = calculateIronFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalIron2 = calculateIronFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalIron3 = calculateIronFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalIron4 = calculateIronFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalIron5 = calculateIronFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalIron6 = calculateIronFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalZinc1 = calculateZincFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalZinc2 = calculateZincFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalZinc3 = calculateZincFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalZinc4 = calculateZincFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalZinc5 = calculateZincFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalZinc6 = calculateZincFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalCopper1 = calculateCopperFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalCopper2 = calculateCopperFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalCopper3 = calculateCopperFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalCopper4 = calculateCopperFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalCopper5 = calculateCopperFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalCopper6 = calculateCopperFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalSelenium1 = calculateSeleniumFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalSelenium2 = calculateSeleniumFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalSelenium3 = calculateSeleniumFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalSelenium4 = calculateSeleniumFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalSelenium5 = calculateSeleniumFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalSelenium6 = calculateSeleniumFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalChromium1 = calculateChromiumFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalChromium2 = calculateChromiumFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalChromium3 = calculateChromiumFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalChromium4 = calculateChromiumFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalChromium5 = calculateChromiumFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalChromium6 = calculateChromiumFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalIodine1 = calculateIodineFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalIodine2 = calculateIodineFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalIodine3 = calculateIodineFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalIodine4 = calculateIodineFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalIodine5 = calculateIodineFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalIodine6 = calculateIodineFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalManganese1 = calculateManganeseFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalManganese2 = calculateManganeseFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalManganese3 = calculateManganeseFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalManganese4 = calculateManganeseFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalManganese5 = calculateManganeseFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalManganese6 = calculateManganeseFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalMolybdenum1 = calculateMolybdenumFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalMolybdenum2 = calculateMolybdenumFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalMolybdenum3 = calculateMolybdenumFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalMolybdenum4 = calculateMolybdenumFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalMolybdenum5 = calculateMolybdenumFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalMolybdenum6 = calculateMolybdenumFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalFluoride1 = calculateFluorideFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalFluoride2 = calculateFluorideFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalFluoride3 = calculateFluorideFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalFluoride4 = calculateFluorideFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalFluoride5 = calculateFluorideFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalFluoride6 = calculateFluorideFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalBoron1 = calculateBoronFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalBoron2 = calculateBoronFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalBoron3 = calculateBoronFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalBoron4 = calculateBoronFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalBoron5 = calculateBoronFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalBoron6 = calculateBoronFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalSilicon1 = calculateSiliconFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalSilicon2 = calculateSiliconFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalSilicon3 = calculateSiliconFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalSilicon4 = calculateSiliconFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalSilicon5 = calculateSiliconFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalSilicon6 = calculateSiliconFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVanadium1 = calculateVanadiumFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVanadium2 = calculateVanadiumFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVanadium3 = calculateVanadiumFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVanadium4 = calculateVanadiumFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVanadium5 = calculateVanadiumFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVanadium6 = calculateVanadiumFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalCobalt1 = calculateCobaltFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalCobalt2 = calculateCobaltFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalCobalt3 = calculateCobaltFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalCobalt4 = calculateCobaltFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalCobalt5 = calculateCobaltFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalCobalt6 = calculateCobaltFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalCalcium7 = calculateCalciumFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalCalcium8 = calculateCalciumFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalCalcium9 = calculateCalciumFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalCalcium10 = calculateCalciumFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalCalcium11 = calculateCalciumFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalCalcium12 = calculateCalciumFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalPotassium7 = calculatePotassiumFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalPotassium8 = calculatePotassiumFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalPotassium9 = calculatePotassiumFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalPotassium10 = calculatePotassiumFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalPotassium11 = calculatePotassiumFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalPotassium12 = calculatePotassiumFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalPhosphorus7 = calculatePhosphorusFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalPhosphorus8 = calculatePhosphorusFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalPhosphorus9 = calculatePhosphorusFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalPhosphorus10 = calculatePhosphorusFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalPhosphorus11 = calculatePhosphorusFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalPhosphorus12 = calculatePhosphorusFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalMagnesium7 = calculateMagnesiumFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalMagnesium8 = calculateMagnesiumFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalMagnesium9 = calculateMagnesiumFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalMagnesium10 = calculateMagnesiumFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalMagnesium11 = calculateMagnesiumFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalMagnesium12 = calculateMagnesiumFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalElectrolyte7 = calculateElectrolyteFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalElectrolyte8 = calculateElectrolyteFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalElectrolyte9 = calculateElectrolyteFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalElectrolyte10 = calculateElectrolyteFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalElectrolyte11 = calculateElectrolyteFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalElectrolyte12 = calculateElectrolyteFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalSodium7 = calculateSodiumFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalSodium8 = calculateSodiumFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalSodium9 = calculateSodiumFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalSodium10 = calculateSodiumFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalSodium11 = calculateSodiumFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalSodium12 = calculateSodiumFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalChloride7 = calculateChlorideFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalChloride8 = calculateChlorideFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalChloride9 = calculateChlorideFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalChloride10 = calculateChlorideFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalChloride11 = calculateChlorideFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalChloride12 = calculateChlorideFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalIron7 = calculateIronFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalIron8 = calculateIronFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalIron9 = calculateIronFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalIron10 = calculateIronFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalIron11 = calculateIronFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalIron12 = calculateIronFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalZinc7 = calculateZincFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalZinc8 = calculateZincFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalZinc9 = calculateZincFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalZinc10 = calculateZincFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalZinc11 = calculateZincFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalZinc12 = calculateZincFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalCopper7 = calculateCopperFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalCopper8 = calculateCopperFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalCopper9 = calculateCopperFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalCopper10 = calculateCopperFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalCopper11 = calculateCopperFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalCopper12 = calculateCopperFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalSelenium7 = calculateSeleniumFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalSelenium8 = calculateSeleniumFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalSelenium9 = calculateSeleniumFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalSelenium10 = calculateSeleniumFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalSelenium11 = calculateSeleniumFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalSelenium12 = calculateSeleniumFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalChromium7 = calculateChromiumFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalChromium8 = calculateChromiumFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalChromium9 = calculateChromiumFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalChromium10 = calculateChromiumFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalChromium11 = calculateChromiumFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalChromium12 = calculateChromiumFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalIodine7 = calculateIodineFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalIodine8 = calculateIodineFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalIodine9 = calculateIodineFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalIodine10 = calculateIodineFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalIodine11 = calculateIodineFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalIodine12 = calculateIodineFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalManganese7 = calculateManganeseFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalManganese8 = calculateManganeseFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalManganese9 = calculateManganeseFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalManganese10 = calculateManganeseFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalManganese11 = calculateManganeseFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalManganese12 = calculateManganeseFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalMolybdenum7 = calculateMolybdenumFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalMolybdenum8 = calculateMolybdenumFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalMolybdenum9 = calculateMolybdenumFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalMolybdenum10 = calculateMolybdenumFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalMolybdenum11 = calculateMolybdenumFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalMolybdenum12 = calculateMolybdenumFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalFluoride7 = calculateFluorideFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalFluoride8 = calculateFluorideFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalFluoride9 = calculateFluorideFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalFluoride10 = calculateFluorideFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalFluoride11 = calculateFluorideFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalFluoride12 = calculateFluorideFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalBoron7 = calculateBoronFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalBoron8 = calculateBoronFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalBoron9 = calculateBoronFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalBoron10 = calculateBoronFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalBoron11 = calculateBoronFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalBoron12 = calculateBoronFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalSilicon7 = calculateSiliconFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalSilicon8 = calculateSiliconFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalSilicon9 = calculateSiliconFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalSilicon10 = calculateSiliconFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalSilicon11 = calculateSiliconFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalSilicon12 = calculateSiliconFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVanadium7 = calculateVanadiumFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVanadium8 = calculateVanadiumFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVanadium9 = calculateVanadiumFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVanadium10 = calculateVanadiumFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVanadium11 = calculateVanadiumFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVanadium12 = calculateVanadiumFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalCobalt7 = calculateCobaltFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalCobalt8 = calculateCobaltFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalCobalt9 = calculateCobaltFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalCobalt10 = calculateCobaltFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalCobalt11 = calculateCobaltFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalCobalt12 = calculateCobaltFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalCalcium13 = calculateCalciumFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalCalcium14 = calculateCalciumFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalCalcium15 = calculateCalciumFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalCalcium16 = calculateCalciumFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalCalcium17 = calculateCalciumFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalCalcium18 = calculateCalciumFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalPhosphorus13 = calculatePhosphorusFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalPhosphorus14 = calculatePhosphorusFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalPhosphorus15 = calculatePhosphorusFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalPhosphorus16 = calculatePhosphorusFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalPhosphorus17 = calculatePhosphorusFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalPhosphorus18 = calculatePhosphorusFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalMagnesium13 = calculateMagnesiumFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalMagnesium14 = calculateMagnesiumFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalMagnesium15 = calculateMagnesiumFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalMagnesium16 = calculateMagnesiumFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalMagnesium17 = calculateMagnesiumFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalMagnesium18 = calculateMagnesiumFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalElectrolyte13 = calculateElectrolyteFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalElectrolyte14 = calculateElectrolyteFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalElectrolyte15 = calculateElectrolyteFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalElectrolyte16 = calculateElectrolyteFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalElectrolyte17 = calculateElectrolyteFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalElectrolyte18 = calculateElectrolyteFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalSodium13 = calculateSodiumFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalSodium14 = calculateSodiumFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalSodium15 = calculateSodiumFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalSodium16 = calculateSodiumFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalSodium17 = calculateSodiumFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalSodium18 = calculateSodiumFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalPotassium13 = calculatePotassiumFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalPotassium14 = calculatePotassiumFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalPotassium15 = calculatePotassiumFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalPotassium16 = calculatePotassiumFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalPotassium17 = calculatePotassiumFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalPotassium18 = calculatePotassiumFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalChloride13 = calculateChlorideFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalChloride14 = calculateChlorideFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalChloride15 = calculateChlorideFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalChloride16 = calculateChlorideFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalChloride17 = calculateChlorideFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalChloride18 = calculateChlorideFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalIron13 = calculateIronFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalIron14 = calculateIronFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalIron15 = calculateIronFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalIron16 = calculateIronFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalIron17 = calculateIronFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalIron18 = calculateIronFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalZinc13 = calculateZincFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalZinc14 = calculateZincFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalZinc15 = calculateZincFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalZinc16 = calculateZincFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalZinc17 = calculateZincFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalZinc18 = calculateZincFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalCopper13 = calculateCopperFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalCopper14 = calculateCopperFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalCopper15 = calculateCopperFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalCopper16 = calculateCopperFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalCopper17 = calculateCopperFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalCopper18 = calculateCopperFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalSelenium13 = calculateSeleniumFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalSelenium14 = calculateSeleniumFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalSelenium15 = calculateSeleniumFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalSelenium16 = calculateSeleniumFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalSelenium17 = calculateSeleniumFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalSelenium18 = calculateSeleniumFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalChromium13 = calculateChromiumFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalChromium14 = calculateChromiumFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalChromium15 = calculateChromiumFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalChromium16 = calculateChromiumFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalChromium17 = calculateChromiumFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalChromium18 = calculateChromiumFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalIodine13 = calculateIodineFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalIodine14 = calculateIodineFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalIodine15 = calculateIodineFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalIodine16 = calculateIodineFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalIodine17 = calculateIodineFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalIodine18 = calculateIodineFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalManganese13 = calculateManganeseFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalManganese14 = calculateManganeseFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalManganese15 = calculateManganeseFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalManganese16 = calculateManganeseFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalManganese17 = calculateManganeseFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalManganese18 = calculateManganeseFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalMolybdenum13 = calculateMolybdenumFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalMolybdenum14 = calculateMolybdenumFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalMolybdenum15 = calculateMolybdenumFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalMolybdenum16 = calculateMolybdenumFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalMolybdenum17 = calculateMolybdenumFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalMolybdenum18 = calculateMolybdenumFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalFluoride13 = calculateFluorideFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalFluoride14 = calculateFluorideFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalFluoride15 = calculateFluorideFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalFluoride16 = calculateFluorideFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalFluoride17 = calculateFluorideFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalFluoride18 = calculateFluorideFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalBoron13 = calculateBoronFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalBoron14 = calculateBoronFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalBoron15 = calculateBoronFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalBoron16 = calculateBoronFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalBoron17 = calculateBoronFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalBoron18 = calculateBoronFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalSilicon13 = calculateSiliconFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalSilicon14 = calculateSiliconFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalSilicon15 = calculateSiliconFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalSilicon16 = calculateSiliconFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalSilicon17 = calculateSiliconFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalSilicon18 = calculateSiliconFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVanadium13 = calculateVanadiumFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVanadium14 = calculateVanadiumFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVanadium15 = calculateVanadiumFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVanadium16 = calculateVanadiumFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVanadium17 = calculateVanadiumFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVanadium18 = calculateVanadiumFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalCobalt13 = calculateCobaltFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalCobalt14 = calculateCobaltFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalCobalt15 = calculateCobaltFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalCobalt16 = calculateCobaltFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalCobalt17 = calculateCobaltFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalCobalt18 = calculateCobaltFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalCalcium19 = calculateCalciumFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalCalcium20 = calculateCalciumFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalCalcium21 = calculateCalciumFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalCalcium22 = calculateCalciumFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalCalcium23 = calculateCalciumFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalCalcium24 = calculateCalciumFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalPhosphorus19 = calculatePhosphorusFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalPhosphorus20 = calculatePhosphorusFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalPhosphorus21 = calculatePhosphorusFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalPhosphorus22 = calculatePhosphorusFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalPhosphorus23 = calculatePhosphorusFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalPhosphorus24 = calculatePhosphorusFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalMagnesium19 = calculateMagnesiumFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalMagnesium20 = calculateMagnesiumFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalMagnesium21 = calculateMagnesiumFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalMagnesium22 = calculateMagnesiumFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalMagnesium23 = calculateMagnesiumFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalMagnesium24 = calculateMagnesiumFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalElectrolyte19 = calculateElectrolyteFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalElectrolyte20 = calculateElectrolyteFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalElectrolyte21 = calculateElectrolyteFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalElectrolyte22 = calculateElectrolyteFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalElectrolyte23 = calculateElectrolyteFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalElectrolyte24 = calculateElectrolyteFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalSodium19 = calculateSodiumFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalSodium20 = calculateSodiumFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalSodium21 = calculateSodiumFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalSodium22 = calculateSodiumFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalSodium23 = calculateSodiumFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalSodium24 = calculateSodiumFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalPotassium19 = calculatePotassiumFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalPotassium20 = calculatePotassiumFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalPotassium21 = calculatePotassiumFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalPotassium22 = calculatePotassiumFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalPotassium23 = calculatePotassiumFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalPotassium24 = calculatePotassiumFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalChloride19 = calculateChlorideFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalChloride20 = calculateChlorideFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalChloride21 = calculateChlorideFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalChloride22 = calculateChlorideFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalChloride23 = calculateChlorideFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalChloride24 = calculateChlorideFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalIron19 = calculateIronFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalIron20 = calculateIronFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalIron21 = calculateIronFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalIron22 = calculateIronFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalIron23 = calculateIronFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalIron24 = calculateIronFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalZinc19 = calculateZincFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalZinc20 = calculateZincFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalZinc21 = calculateZincFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalZinc22 = calculateZincFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalZinc23 = calculateZincFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalZinc24 = calculateZincFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalCopper19 = calculateCopperFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalCopper20 = calculateCopperFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalCopper21 = calculateCopperFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalCopper22 = calculateCopperFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalCopper23 = calculateCopperFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalCopper24 = calculateCopperFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalSelenium19 = calculateSeleniumFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalSelenium20 = calculateSeleniumFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalSelenium21 = calculateSeleniumFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalSelenium22 = calculateSeleniumFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalSelenium23 = calculateSeleniumFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalSelenium24 = calculateSeleniumFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalChromium19 = calculateChromiumFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalChromium20 = calculateChromiumFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalChromium21 = calculateChromiumFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalChromium22 = calculateChromiumFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalChromium23 = calculateChromiumFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalChromium24 = calculateChromiumFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalIodine19 = calculateIodineFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalIodine20 = calculateIodineFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalIodine21 = calculateIodineFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalIodine22 = calculateIodineFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalIodine23 = calculateIodineFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalIodine24 = calculateIodineFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalManganese19 = calculateManganeseFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalManganese20 = calculateManganeseFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalManganese21 = calculateManganeseFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalManganese22 = calculateManganeseFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalManganese23 = calculateManganeseFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalManganese24 = calculateManganeseFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalMolybdenum19 = calculateMolybdenumFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalMolybdenum20 = calculateMolybdenumFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalMolybdenum21 = calculateMolybdenumFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalMolybdenum22 = calculateMolybdenumFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalMolybdenum23 = calculateMolybdenumFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalMolybdenum24 = calculateMolybdenumFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalFluoride19 = calculateFluorideFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalFluoride20 = calculateFluorideFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalFluoride21 = calculateFluorideFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalFluoride22 = calculateFluorideFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalFluoride23 = calculateFluorideFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalFluoride24 = calculateFluorideFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalBoron19 = calculateBoronFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalBoron20 = calculateBoronFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalBoron21 = calculateBoronFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalBoron22 = calculateBoronFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalBoron23 = calculateBoronFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalBoron24 = calculateBoronFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalSilicon19 = calculateSiliconFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalSilicon20 = calculateSiliconFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalSilicon21 = calculateSiliconFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalSilicon22 = calculateSiliconFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalSilicon23 = calculateSiliconFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalSilicon24 = calculateSiliconFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVanadium19 = calculateVanadiumFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVanadium20 = calculateVanadiumFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVanadium21 = calculateVanadiumFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVanadium22 = calculateVanadiumFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVanadium23 = calculateVanadiumFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVanadium24 = calculateVanadiumFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalCobalt19 = calculateCobaltFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalCobalt20 = calculateCobaltFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalCobalt21 = calculateCobaltFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalCobalt22 = calculateCobaltFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalCobalt23 = calculateCobaltFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalCobalt24 = calculateCobaltFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalCalcium25 = calculateCalciumFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalCalcium26 = calculateCalciumFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalCalcium27 = calculateCalciumFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalCalcium28 = calculateCalciumFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalCalcium29 = calculateCalciumFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalCalcium30 = calculateCalciumFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalPhosphorus25 = calculatePhosphorusFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalPhosphorus26 = calculatePhosphorusFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalPhosphorus27 = calculatePhosphorusFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalPhosphorus28 = calculatePhosphorusFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalPhosphorus29 = calculatePhosphorusFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalPhosphorus30 = calculatePhosphorusFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalMagnesium25 = calculateMagnesiumFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalMagnesium26 = calculateMagnesiumFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalMagnesium27 = calculateMagnesiumFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalMagnesium28 = calculateMagnesiumFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalMagnesium29 = calculateMagnesiumFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalMagnesium30 = calculateMagnesiumFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalElectrolyte25 = calculateElectrolyteFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalElectrolyte26 = calculateElectrolyteFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalElectrolyte27 = calculateElectrolyteFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalElectrolyte28 = calculateElectrolyteFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalElectrolyte29 = calculateElectrolyteFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalElectrolyte30 = calculateElectrolyteFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalSodium25 = calculateSodiumFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalSodium26 = calculateSodiumFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalSodium27 = calculateSodiumFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalSodium28 = calculateSodiumFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalSodium29 = calculateSodiumFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalSodium30 = calculateSodiumFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalPotassium25 = calculatePotassiumFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalPotassium26 = calculatePotassiumFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalPotassium27 = calculatePotassiumFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalPotassium28 = calculatePotassiumFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalPotassium29 = calculatePotassiumFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalPotassium30 = calculatePotassiumFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalChloride25 = calculateChlorideFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalChloride26 = calculateChlorideFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalChloride27 = calculateChlorideFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalChloride28 = calculateChlorideFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalChloride29 = calculateChlorideFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalChloride30 = calculateChlorideFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalIron25 = calculateIronFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalIron26 = calculateIronFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalIron27 = calculateIronFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalIron28 = calculateIronFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalIron29 = calculateIronFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalIron30 = calculateIronFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalZinc25 = calculateZincFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalZinc26 = calculateZincFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalZinc27 = calculateZincFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalZinc28 = calculateZincFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalZinc29 = calculateZincFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalZinc30 = calculateZincFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalCopper25 = calculateCopperFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalCopper26 = calculateCopperFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalCopper27 = calculateCopperFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalCopper28 = calculateCopperFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalCopper29 = calculateCopperFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalCopper30 = calculateCopperFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalSelenium25 = calculateSeleniumFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalSelenium26 = calculateSeleniumFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalSelenium27 = calculateSeleniumFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalSelenium28 = calculateSeleniumFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalSelenium29 = calculateSeleniumFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalSelenium30 = calculateSeleniumFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalChromium25 = calculateChromiumFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalChromium26 = calculateChromiumFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalChromium27 = calculateChromiumFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalChromium28 = calculateChromiumFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalChromium29 = calculateChromiumFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalChromium30 = calculateChromiumFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalIodine25 = calculateIodineFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalIodine26 = calculateIodineFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalIodine27 = calculateIodineFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalIodine28 = calculateIodineFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalIodine29 = calculateIodineFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalIodine30 = calculateIodineFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalManganese25 = calculateManganeseFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalManganese26 = calculateManganeseFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalManganese27 = calculateManganeseFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalManganese28 = calculateManganeseFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalManganese29 = calculateManganeseFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalManganese30 = calculateManganeseFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalMolybdenum25 = calculateMolybdenumFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalMolybdenum26 = calculateMolybdenumFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalMolybdenum27 = calculateMolybdenumFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalMolybdenum28 = calculateMolybdenumFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalMolybdenum29 = calculateMolybdenumFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalMolybdenum30 = calculateMolybdenumFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalFluoride25 = calculateFluorideFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalFluoride26 = calculateFluorideFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalFluoride27 = calculateFluorideFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalFluoride28 = calculateFluorideFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalFluoride29 = calculateFluorideFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalFluoride30 = calculateFluorideFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalBoron25 = calculateBoronFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalBoron26 = calculateBoronFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalBoron27 = calculateBoronFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalBoron28 = calculateBoronFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalBoron29 = calculateBoronFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalBoron30 = calculateBoronFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalSilicon25 = calculateSiliconFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalSilicon26 = calculateSiliconFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalSilicon27 = calculateSiliconFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalSilicon28 = calculateSiliconFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalSilicon29 = calculateSiliconFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalSilicon30 = calculateSiliconFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVanadium25 = calculateVanadiumFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVanadium26 = calculateVanadiumFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVanadium27 = calculateVanadiumFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVanadium28 = calculateVanadiumFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVanadium29 = calculateVanadiumFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVanadium30 = calculateVanadiumFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalCobalt25 = calculateCobaltFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalCobalt26 = calculateCobaltFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalCobalt27 = calculateCobaltFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalCobalt28 = calculateCobaltFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalCobalt29 = calculateCobaltFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalCobalt30 = calculateCobaltFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminA1 = calculateVitaminAFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminA2 = calculateVitaminAFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminA3 = calculateVitaminAFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminA4 = calculateVitaminAFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminA5 = calculateVitaminAFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminA6 = calculateVitaminAFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminD1 = calculateVitaminDFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminD2 = calculateVitaminDFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminD3 = calculateVitaminDFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminD4 = calculateVitaminDFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminD5 = calculateVitaminDFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminD6 = calculateVitaminDFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminE1 = calculateVitaminEFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminE2 = calculateVitaminEFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminE3 = calculateVitaminEFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminE4 = calculateVitaminEFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminE5 = calculateVitaminEFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminE6 = calculateVitaminEFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminK1 = calculateVitaminKFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminK2 = calculateVitaminKFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminK3 = calculateVitaminKFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminK4 = calculateVitaminKFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminK5 = calculateVitaminKFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminK6 = calculateVitaminKFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminB1 = calculateVitaminBFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminB2 = calculateVitaminBFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminB3 = calculateVitaminBFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminB4 = calculateVitaminBFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminB5 = calculateVitaminBFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminB6 = calculateVitaminBFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminB11 = calculateVitaminB1FromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminB12 = calculateVitaminB1FromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminB13 = calculateVitaminB1FromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminB14 = calculateVitaminB1FromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminB15 = calculateVitaminB1FromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminB16 = calculateVitaminB1FromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminB21 = calculateVitaminB2FromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminB22 = calculateVitaminB2FromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminB23 = calculateVitaminB2FromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminB24 = calculateVitaminB2FromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminB25 = calculateVitaminB2FromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminB26 = calculateVitaminB2FromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminB31 = calculateVitaminB3FromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminB32 = calculateVitaminB3FromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminB33 = calculateVitaminB3FromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminB34 = calculateVitaminB3FromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminB35 = calculateVitaminB3FromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminB36 = calculateVitaminB3FromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminP1 = calculateVitaminPFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminP2 = calculateVitaminPFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminP3 = calculateVitaminPFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminP4 = calculateVitaminPFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminP5 = calculateVitaminPFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminP6 = calculateVitaminPFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminB71 = calculateVitaminB7FromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminB72 = calculateVitaminB7FromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminB73 = calculateVitaminB7FromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminB74 = calculateVitaminB7FromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminB75 = calculateVitaminB7FromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminB76 = calculateVitaminB7FromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminB91 = calculateVitaminB9FromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminB92 = calculateVitaminB9FromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminB93 = calculateVitaminB9FromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminB94 = calculateVitaminB9FromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminB95 = calculateVitaminB9FromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminB96 = calculateVitaminB9FromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminB121 = calculateVitaminB12FromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminB122 = calculateVitaminB12FromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminB123 = calculateVitaminB12FromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminB124 = calculateVitaminB12FromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminB125 = calculateVitaminB12FromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminB126 = calculateVitaminB12FromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminB61 = calculateVitaminB6FromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminB62 = calculateVitaminB6FromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminB63 = calculateVitaminB6FromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminB64 = calculateVitaminB6FromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminB65 = calculateVitaminB6FromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminB66 = calculateVitaminB6FromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminC1 = calculateVitaminCFromProteinFoodItem(meal1ProteinSpinner, meal1proteinGramsEditText);
            double totalVitaminC2 = calculateVitaminCFromProteinFoodItem(meal2ProteinSpinner, meal2ProteinGramsEditText);
            double totalVitaminC3 = calculateVitaminCFromProteinFoodItem(meal3ProteinSpinner, meal3ProteinGramsEditText);
            double totalVitaminC4 = calculateVitaminCFromProteinFoodItem(meal4ProteinSpinner, meal4ProteinGramsEditText);
            double totalVitaminC5 = calculateVitaminCFromProteinFoodItem(meal5ProteinSpinner, meal5ProteinGramsEditText);
            double totalVitaminC6 = calculateVitaminCFromProteinFoodItem(meal6ProteinSpinner, meal6ProteinGramsEditText);

            double totalVitaminA7 = calculateVitaminAFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminA8 = calculateVitaminAFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminA9 = calculateVitaminAFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminA10 = calculateVitaminAFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminA11 = calculateVitaminAFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminA12 = calculateVitaminAFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminD7 = calculateVitaminDFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminD8 = calculateVitaminDFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminD9 = calculateVitaminDFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminD10 = calculateVitaminDFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminD11 = calculateVitaminDFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminD12 = calculateVitaminDFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminE7 = calculateVitaminEFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminE8 = calculateVitaminEFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminE9 = calculateVitaminEFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminE10 = calculateVitaminEFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminE11 = calculateVitaminEFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminE12 = calculateVitaminEFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminK7 = calculateVitaminKFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminK8 = calculateVitaminKFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminK9 = calculateVitaminKFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminK10 = calculateVitaminKFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminK11 = calculateVitaminKFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminK12 = calculateVitaminKFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminB7 = calculateVitaminBFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminB8 = calculateVitaminBFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminB9 = calculateVitaminBFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminB10 = calculateVitaminBFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminB50 = calculateVitaminBFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminB51 = calculateVitaminBFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminB17 = calculateVitaminB1FromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminB18 = calculateVitaminB1FromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminB19 = calculateVitaminB1FromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminB110 = calculateVitaminB1FromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminB111 = calculateVitaminB1FromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminB112 = calculateVitaminB1FromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminB27 = calculateVitaminB2FromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminB28 = calculateVitaminB2FromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminB29 = calculateVitaminB2FromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminB210 = calculateVitaminB2FromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminB211 = calculateVitaminB2FromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminB212 = calculateVitaminB2FromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminB37 = calculateVitaminB3FromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminB38 = calculateVitaminB3FromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminB39 = calculateVitaminB3FromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminB310 = calculateVitaminB3FromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminB311 = calculateVitaminB3FromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminB312 = calculateVitaminB3FromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminP7 = calculateVitaminPFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminP8 = calculateVitaminPFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminP9 = calculateVitaminPFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminP10 = calculateVitaminPFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminP11 = calculateVitaminPFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminP12 = calculateVitaminPFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminB77 = calculateVitaminB7FromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminB78 = calculateVitaminB7FromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminB79 = calculateVitaminB7FromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminB710 = calculateVitaminB7FromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminB711 = calculateVitaminB7FromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminB712 = calculateVitaminB7FromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminB97 = calculateVitaminB9FromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminB98 = calculateVitaminB9FromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminB99 = calculateVitaminB9FromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminB910 = calculateVitaminB9FromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminB911 = calculateVitaminB9FromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminB912 = calculateVitaminB9FromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminB127 = calculateVitaminB12FromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminB128 = calculateVitaminB12FromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminB129 = calculateVitaminB12FromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminB1210 = calculateVitaminB12FromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminB1211 = calculateVitaminB12FromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminB1212 = calculateVitaminB12FromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminB67 = calculateVitaminB6FromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminB68 = calculateVitaminB6FromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminB69 = calculateVitaminB6FromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminB610 = calculateVitaminB6FromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminB611 = calculateVitaminB6FromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminB612 = calculateVitaminB6FromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminC7 = calculateVitaminCFromCarbsFoodItem(meal1CarbsSpinner, meal1CarbsGramEditText);
            double totalVitaminC8 = calculateVitaminCFromCarbsFoodItem(meal2CarbsSpinner, meal2CarbsGramEditText);
            double totalVitaminC9 = calculateVitaminCFromCarbsFoodItem(meal3CarbsSpinner, meal3CarbsGramEditText);
            double totalVitaminC10 = calculateVitaminCFromCarbsFoodItem(meal4CarbsSpinner, meal4CarbsGramEditText);
            double totalVitaminC11 = calculateVitaminCFromCarbsFoodItem(meal5CarbsSpinner, meal5CarbsGramEditText);
            double totalVitaminC12 = calculateVitaminCFromCarbsFoodItem(meal6CarbsSpinner, meal6CarbsGramEditText);

            double totalVitaminA13 = calculateVitaminAFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminA14 = calculateVitaminAFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminA15 = calculateVitaminAFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminA16 = calculateVitaminAFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminA17 = calculateVitaminAFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminA18 = calculateVitaminAFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminD13 = calculateVitaminDFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminD14 = calculateVitaminDFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminD15 = calculateVitaminDFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminD16 = calculateVitaminDFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminD17 = calculateVitaminDFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminD18 = calculateVitaminDFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminE13 = calculateVitaminEFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminE14 = calculateVitaminEFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminE15 = calculateVitaminEFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminE16 = calculateVitaminEFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminE17 = calculateVitaminEFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminE18 = calculateVitaminEFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminK13 = calculateVitaminKFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminK14 = calculateVitaminKFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminK15 = calculateVitaminKFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminK16 = calculateVitaminKFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminK17 = calculateVitaminKFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminK18 = calculateVitaminKFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminB52 = calculateVitaminBFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminB53 = calculateVitaminBFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminB54 = calculateVitaminBFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminB55 = calculateVitaminBFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminB56 = calculateVitaminBFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminB57 = calculateVitaminBFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminB113 = calculateVitaminB1FromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminB114 = calculateVitaminB1FromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminB115 = calculateVitaminB1FromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminB116 = calculateVitaminB1FromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminB117 = calculateVitaminB1FromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminB118 = calculateVitaminB1FromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminB213 = calculateVitaminB2FromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminB214 = calculateVitaminB2FromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminB215 = calculateVitaminB2FromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminB216 = calculateVitaminB2FromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminB217 = calculateVitaminB2FromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminB218 = calculateVitaminB2FromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminB313 = calculateVitaminB3FromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminB314 = calculateVitaminB3FromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminB315 = calculateVitaminB3FromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminB316 = calculateVitaminB3FromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminB317 = calculateVitaminB3FromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminB318 = calculateVitaminB3FromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminP13 = calculateVitaminPFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminP14 = calculateVitaminPFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminP15 = calculateVitaminPFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminP16 = calculateVitaminPFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminP17 = calculateVitaminPFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminP18 = calculateVitaminPFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminB713 = calculateVitaminB7FromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminB714 = calculateVitaminB7FromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminB715 = calculateVitaminB7FromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminB716 = calculateVitaminB7FromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminB717 = calculateVitaminB7FromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminB718 = calculateVitaminB7FromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminB913 = calculateVitaminB9FromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminB914 = calculateVitaminB9FromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminB915 = calculateVitaminB9FromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminB916 = calculateVitaminB9FromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminB917 = calculateVitaminB9FromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminB918 = calculateVitaminB9FromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminB1213 = calculateVitaminB12FromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminB1214 = calculateVitaminB12FromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminB1215 = calculateVitaminB12FromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminB1216 = calculateVitaminB12FromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminB1217 = calculateVitaminB12FromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminB1218 = calculateVitaminB12FromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminB613 = calculateVitaminB6FromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminB614 = calculateVitaminB6FromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminB615 = calculateVitaminB6FromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminB616 = calculateVitaminB6FromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminB617 = calculateVitaminB6FromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminB618 = calculateVitaminB6FromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminC13 = calculateVitaminCFromFatsFoodItem(meal1fatsSpinner, meal1FatsGramEditText);
            double totalVitaminC14 = calculateVitaminCFromFatsFoodItem(meal2fatsSpinner, meal2FatsGramEditText);
            double totalVitaminC15 = calculateVitaminCFromFatsFoodItem(meal3fatsSpinner, meal3FatsGramEditText);
            double totalVitaminC16 = calculateVitaminCFromFatsFoodItem(meal4fatsSpinner, meal4FatsGramEditText);
            double totalVitaminC17 = calculateVitaminCFromFatsFoodItem(meal5fatsSpinner, meal5FatsGramEditText);
            double totalVitaminC18 = calculateVitaminCFromFatsFoodItem(meal6fatsSpinner, meal6FatsGramEditText);

            double totalVitaminA19 = calculateVitaminAFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminA20 = calculateVitaminAFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminA21 = calculateVitaminAFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminA22 = calculateVitaminAFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminA23 = calculateVitaminAFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminA24 = calculateVitaminAFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminD19 = calculateVitaminDFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminD20 = calculateVitaminDFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminD21 = calculateVitaminDFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminD22 = calculateVitaminDFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminD23 = calculateVitaminDFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminD24 = calculateVitaminDFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminE19 = calculateVitaminEFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminE20 = calculateVitaminEFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminE21 = calculateVitaminEFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminE22 = calculateVitaminEFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminE23 = calculateVitaminEFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminE24 = calculateVitaminEFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminK19 = calculateVitaminKFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminK20 = calculateVitaminKFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminK21 = calculateVitaminKFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminK22 = calculateVitaminKFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminK23 = calculateVitaminKFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminK24 = calculateVitaminKFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminB58 = calculateVitaminBFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminB59 = calculateVitaminBFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminB60 = calculateVitaminBFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminB80 = calculateVitaminBFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminB81 = calculateVitaminBFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminB82 = calculateVitaminBFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminB119 = calculateVitaminB1FromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminB120 = calculateVitaminB1FromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminB131 = calculateVitaminB1FromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminB132 = calculateVitaminB1FromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminB133 = calculateVitaminB1FromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminB134 = calculateVitaminB1FromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminB219 = calculateVitaminB2FromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminB220 = calculateVitaminB2FromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminB221 = calculateVitaminB2FromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminB222 = calculateVitaminB2FromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminB223 = calculateVitaminB2FromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminB224 = calculateVitaminB2FromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminB319 = calculateVitaminB3FromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminB320 = calculateVitaminB3FromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminB321 = calculateVitaminB3FromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminB322 = calculateVitaminB3FromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminB323 = calculateVitaminB3FromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminB324 = calculateVitaminB3FromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminP19 = calculateVitaminPFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminP20 = calculateVitaminPFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminP21 = calculateVitaminPFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminP22 = calculateVitaminPFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminP23 = calculateVitaminPFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminP24 = calculateVitaminPFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminB719 = calculateVitaminB7FromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminB720 = calculateVitaminB7FromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminB721 = calculateVitaminB7FromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminB722 = calculateVitaminB7FromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminB723 = calculateVitaminB7FromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminB724 = calculateVitaminB7FromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminB919 = calculateVitaminB9FromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminB920 = calculateVitaminB9FromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminB921 = calculateVitaminB9FromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminB922 = calculateVitaminB9FromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminB923 = calculateVitaminB9FromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminB924 = calculateVitaminB9FromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminB1219 = calculateVitaminB12FromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminB1220 = calculateVitaminB12FromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminB1221 = calculateVitaminB12FromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminB1222 = calculateVitaminB12FromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminB1223 = calculateVitaminB12FromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminB1224 = calculateVitaminB12FromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminB619 = calculateVitaminB6FromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminB620 = calculateVitaminB6FromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminB621 = calculateVitaminB6FromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminB622 = calculateVitaminB6FromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminB623 = calculateVitaminB6FromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminB624 = calculateVitaminB6FromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminC19 = calculateVitaminCFromFruitsFoodItem(meal1fruitsSpinner, meal1FruitsGramEditText);
            double totalVitaminC20 = calculateVitaminCFromFruitsFoodItem(meal2fruitsSpinner, meal2FruitsGramEditText);
            double totalVitaminC21 = calculateVitaminCFromFruitsFoodItem(meal3fruitsSpinner, meal3FruitsGramEditText);
            double totalVitaminC22 = calculateVitaminCFromFruitsFoodItem(meal4fruitsSpinner, meal4FruitsGramEditText);
            double totalVitaminC23 = calculateVitaminCFromFruitsFoodItem(meal5fruitsSpinner, meal5FruitsGramEditText);
            double totalVitaminC24 = calculateVitaminCFromFruitsFoodItem(meal6fruitsSpinner, meal6FruitsGramEditText);

            double totalVitaminA25 = calculateVitaminAFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminA26 = calculateVitaminAFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminA27 = calculateVitaminAFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminA28 = calculateVitaminAFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminA29 = calculateVitaminAFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminA30 = calculateVitaminAFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminD25 = calculateVitaminDFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminD26 = calculateVitaminDFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminD27 = calculateVitaminDFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminD28 = calculateVitaminDFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminD29 = calculateVitaminDFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminD30 = calculateVitaminDFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminE25 = calculateVitaminEFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminE26 = calculateVitaminEFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminE27 = calculateVitaminEFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminE28 = calculateVitaminEFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminE29 = calculateVitaminEFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminE30 = calculateVitaminEFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminK25 = calculateVitaminKFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminK26 = calculateVitaminKFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminK27 = calculateVitaminKFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminK28 = calculateVitaminKFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminK29 = calculateVitaminKFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminK30 = calculateVitaminKFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminB83 = calculateVitaminBFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminB84 = calculateVitaminBFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminB85 = calculateVitaminBFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminB86 = calculateVitaminBFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminB87 = calculateVitaminBFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminB88 = calculateVitaminBFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminB135 = calculateVitaminB1FromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminB136 = calculateVitaminB1FromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminB137 = calculateVitaminB1FromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminB138 = calculateVitaminB1FromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminB139 = calculateVitaminB1FromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminB140 = calculateVitaminB1FromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminB225 = calculateVitaminB2FromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminB226 = calculateVitaminB2FromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminB227 = calculateVitaminB2FromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminB228 = calculateVitaminB2FromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminB229 = calculateVitaminB2FromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminB230 = calculateVitaminB2FromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminB325 = calculateVitaminB3FromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminB326 = calculateVitaminB3FromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminB327 = calculateVitaminB3FromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminB328 = calculateVitaminB3FromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminB329 = calculateVitaminB3FromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminB330 = calculateVitaminB3FromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminP25 = calculateVitaminPFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminP26 = calculateVitaminPFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminP27 = calculateVitaminPFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminP28 = calculateVitaminPFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminP29 = calculateVitaminPFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminP30 = calculateVitaminPFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminB725 = calculateVitaminB7FromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminB726 = calculateVitaminB7FromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminB727 = calculateVitaminB7FromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminB728 = calculateVitaminB7FromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminB729 = calculateVitaminB7FromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminB730 = calculateVitaminB7FromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminB925 = calculateVitaminB9FromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminB926 = calculateVitaminB9FromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminB927 = calculateVitaminB9FromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminB928 = calculateVitaminB9FromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminB929 = calculateVitaminB9FromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminB930 = calculateVitaminB9FromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminB1225 = calculateVitaminB12FromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminB1226 = calculateVitaminB12FromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminB1227 = calculateVitaminB12FromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminB1228 = calculateVitaminB12FromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminB1229 = calculateVitaminB12FromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminB1230 = calculateVitaminB12FromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminB625 = calculateVitaminB6FromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminB626 = calculateVitaminB6FromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminB627 = calculateVitaminB6FromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminB628 = calculateVitaminB6FromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminB629 = calculateVitaminB6FromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminB630 = calculateVitaminB6FromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            double totalVitaminC25 = calculateVitaminCFromVegetableFoodItem(meal1vegetableSpinner, meal1VegetableGramEditText);
            double totalVitaminC26 = calculateVitaminCFromVegetableFoodItem(meal2vegetableSpinner, meal2VegetableGramEditText);
            double totalVitaminC27 = calculateVitaminCFromVegetableFoodItem(meal3vegetableSpinner, meal3VegetableGramEditText);
            double totalVitaminC28 = calculateVitaminCFromVegetableFoodItem(meal4vegetableSpinner, meal4VegetableGramEditText);
            double totalVitaminC29 = calculateVitaminCFromVegetableFoodItem(meal5vegetableSpinner, meal5VegetableGramEditText);
            double totalVitaminC30 = calculateVitaminCFromVegetableFoodItem(meal6vegetableSpinner, meal6VegetableGramEditText);

            // Initialize an array to store the values from caloriesValues
            double[] caloriesValuesArray = new double[caloriesValues.length];

// Loop through each EditText and convert the entered text to a double value
            for (int i = 0; i < caloriesValues.length; i++) {
                String caloriesText = caloriesValues[i].getText().toString();
                if (!caloriesText.isEmpty()) {
                    caloriesValuesArray[i] = Double.parseDouble(caloriesText);
                } else {
                    // Handle the case where the EditText is empty (you may set a default value or handle it as needed)
                    caloriesValuesArray[i] = 0.0;
                }
            }

// Calculate the sum of calories
            double sumTotalCalories = 0.0;
            for (double calories : caloriesValuesArray) {
                sumTotalCalories += calories;
            }

            float x = 100;
            float y = 200;
            float rowHeight = 36;
            float columnWidth = 235;

            Bitmap watermarkBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.watermark); // Replace R.drawable.watermark with your watermark image resource

            // Draw watermark image on canvas with desired transparency
            Paint watermarkPaint = new Paint();
            watermarkPaint.setAlpha(50); // Set transparency (0-255), 100 is semi-transparent
            canvas1.drawBitmap(watermarkBitmap, null, new Rect(0, 0, canvas1.getWidth(), canvas1.getHeight()), watermarkPaint);

// Draw the entered details from EditText fields
            canvas1.drawText("Name: " + nameEditText.getText().toString(), x, y, paint1);
            y += rowHeight;
            canvas1.drawText("Age: " + ageEditText.getText().toString(), x, y, paint1);
            y += rowHeight;
            canvas1.drawText("Height: " + heightEditText.getText().toString(), x, y, paint1);
            y += rowHeight;
            canvas1.drawText("Weight: " + weightEditText.getText().toString(), x, y, paint1);
            y += rowHeight;

            canvas1.drawText("Number of Meals: " + noOfMealsEditText.getText().toString(), x, y, paint1);
            y += rowHeight;


// Draw the label for "Meal3 Food Sources"
            canvas1.drawText("Meal1 Food Sources", x, y, paint1);
            y += rowHeight;

// Draw the nutritional information dynamically in the table


            String[] nutritionValues = {selectedProteinFood, selectedCarbsFood, selectedFatsFood, selectedFruit, selectedVegetable};
            double[] gramsValues = {gramsOfProtein, gramsOfCarbs, gramsOfFats, gramsOfFruits, gramsOfVegetable};


// Draw the nutritional information dynamically in the table
            for (int i = 0; i < nutritionValues.length; i++) {
                drawTableCell(canvas1, x + i * columnWidth, y, columnWidth, rowHeight, paint1, false, nutritionValues[i]);
                drawTableCell(canvas1, x + i * columnWidth, y + rowHeight, columnWidth, rowHeight, paint1, false, String.valueOf(gramsValues[i]));
            }
            y += 3 * rowHeight;  // Increase the spacing before "Meal3 Food Sources"

// Draw the label for "Meal3 Food Sources"
            canvas1.drawText("Meal2 Food Sources", x, y, paint1);
            y += rowHeight;


            String[] meal2NutritionValues = {selectedProteinFood1, selectedCarbsFood1, selectedFatsFood1, selectedFruit1, selectedVegetable1};
            double[] meal2GramsValues = {gramsOfProtein1, gramsOfCarbs1, gramsOfFats1, gramsOfFruits1, gramsOfVegetable1};

            for (int i = 0; i < meal2NutritionValues.length; i++) {
                drawTableCell(canvas1, x + i * columnWidth, y, columnWidth, rowHeight, paint1, false, meal2NutritionValues[i]);
                drawTableCell(canvas1, x + i * columnWidth, y + rowHeight, columnWidth, rowHeight, paint1, false, String.valueOf(meal2GramsValues[i]));
            }
            y += 3 * rowHeight;  // Increase the spacing before "Meal3 Food Sources"

// Draw the label for "Meal3 Food Sources"
            canvas1.drawText("Meal3 Food Sources", x, y, paint1);
            y += rowHeight;
            String[] meal3NutritionValues = {selectedProteinFood2, selectedCarbsFood2, selectedFatsFood2, selectedFruit2, selectedVegetable2};
            double[] meal3GramsValues = {gramsOfProtein2, gramsOfCarbs2, gramsOfFats2, gramsOfFruits2, gramsOfVegetable2};

            for (int i = 0; i < meal3NutritionValues.length; i++) {
                drawTableCell(canvas1, x + i * columnWidth, y, columnWidth, rowHeight, paint1, false, meal3NutritionValues[i]);
                drawTableCell(canvas1, x + i * columnWidth, y + rowHeight, columnWidth, rowHeight, paint1, false, String.valueOf(meal3GramsValues[i]));
            }
            y += 3 * rowHeight;  // Increase the spacing before "Meal4 Food Sources"

// Draw the label for "Meal4 Food Sources"
            canvas1.drawText("Meal4 Food Sources", x, y, paint1);
            y += rowHeight;

            String[] meal4NutritionValues = {selectedProteinFood3, selectedCarbsFood3, selectedFatsFood3, selectedFruit3, selectedVegetable3};
            double[] meal4GramsValues = {gramsOfProtein3, gramsOfCarbs3, gramsOfFats3, gramsOfFruits3, gramsOfVegetable3};

            for (int i = 0; i < meal4NutritionValues.length; i++) {
                drawTableCell(canvas1, x + i * columnWidth, y, columnWidth, rowHeight, paint1, false, meal4NutritionValues[i]);
                drawTableCell(canvas1, x + i * columnWidth, y + rowHeight, columnWidth, rowHeight, paint1, false, String.valueOf(meal4GramsValues[i]));
            }
            y += 3 * rowHeight;  // Increase the spacing before "Meal5 Food Sources"

// Draw the label for "Meal5 Food Sources"
            canvas1.drawText("Meal5 Food Sources", x, y, paint1);
            y += rowHeight;

            String[] meal5NutritionValues = {selectedProteinFood4, selectedCarbsFood4, selectedFatsFood4, selectedFruit4, selectedVegetable4};
            double[] meal5GramsValues = {gramsOfProtein4, gramsOfCarbs4, gramsOfFats4, gramsOfFruits4, gramsOfVegetable4};

            for (int i = 0; i < meal5NutritionValues.length; i++) {
                drawTableCell(canvas1, x + i * columnWidth, y, columnWidth, rowHeight, paint1, false, meal5NutritionValues[i]);
                drawTableCell(canvas1, x + i * columnWidth, y + rowHeight, columnWidth, rowHeight, paint1, false, String.valueOf(meal5GramsValues[i]));
            }
            y += 3 * rowHeight;  // Increase the spacing before "Meal6 Food Sources"

// Draw the label for "Meal6 Food Sources"
            canvas1.drawText("Meal6 Food Sources", x, y, paint1);
            y += rowHeight;

            String[] meal6NutritionValues = {selectedProteinFood5, selectedCarbsFood5, selectedFatsFood5, selectedFruit5, selectedVegetable5};
            double[] meal6GramsValues = {gramsOfProtein5, gramsOfCarbs5, gramsOfFats5, gramsOfFruits5, gramsOfVegetable5};

            for (int i = 0; i < meal6NutritionValues.length; i++) {
                drawTableCell(canvas1, x + i * columnWidth, y, columnWidth, rowHeight, paint1, false, meal6NutritionValues[i]);
                drawTableCell(canvas1, x + i * columnWidth, y + rowHeight, columnWidth, rowHeight, paint1, false, String.valueOf(meal6GramsValues[i]));
            }
            y += 4 * rowHeight;

            canvas1.drawText("MACRO COUNT :", x + 120, y, paint1);

            y += 2 * rowHeight;

            // Draw the sum of calories in the PDF
            double[] totalProteins = {totalProteins1, totalProteins2, totalProteins3, totalProteins4, totalProteins5, totalProteins6, totalProteins7, totalProteins8, totalProteins9, totalProteins10, totalProteins11, totalProteins12, totalProteins13, totalProteins14, totalProteins15, totalProteins16, totalProteins17, totalProteins18, totalProteins19, totalProteins20, totalProteins21, totalProteins22, totalProteins23, totalProteins24, totalProtein25, totalProtein26, totalProtein27, totalProtein28, totalProtein29, totalProtein30};
            double[] totalCarbs = {totalCarbs1, totalCarbs2, totalCarbs3, totalCarbs4, totalCarbs5, totalCarbs6, totalCarbs7, totalCarbs8, totalCarbs9, totalCarbs10, totalCarbs11, totalCarbs12, totalCarbs13, totalCarbs14, totalCarbs15, totalCarbs16, totalCarbs17, totalCarbs18, totalCarbs19, totalCarbs20, totalCarbs21, totalCarbs22, totalCarbs23, totalCarbs24, totalCarbs25, totalCarbs26, totalCarbs27, totalCarbs28, totalCarbs29, totalCarbs30};

            double[] totalFats = {totalFats1, totalFats2, totalFats3, totalFats4, totalFats5, totalFats6, totalFats7, totalFats8, totalFats9, totalFats10, totalFats11, totalFats12, totalFats13, totalFats14, totalFats15, totalFats16, totalFats17, totalFats18, totalFats19, totalFats20, totalFats21, totalFats22, totalFats23, totalFats24, totalFats25, totalFats26, totalFats27, totalFats28, totalFats29, totalFats30};
            double[] totalFibres = {totalFibres1, totalFibres2, totalFibres3, totalFibres4, totalFibres5, totalFibres6, totalFibres7, totalFibres8, totalFibres9, totalFibres10, totalFibres11, totalFibres12, totalFibres13, totalFibres14, totalFibres15, totalFibres16, totalFibres17, totalFibres18, totalFibres19, totalFibres20, totalFibres21, totalFibres22, totalFibres23, totalFibres24, totalFibres25, totalFibres26, totalFibres27, totalFibres28, totalFibres29, totalFibres30};

            double sumTotalProteins = 0.0;
            for (double protein : totalProteins) {
                sumTotalProteins += protein;
            }
            double sumTotalCarbs = 0.0;
            for (double carb : totalCarbs) {
                sumTotalCarbs += carb;
            }
            double sumTotalFats = 0.0;
            for (double fat : totalFats) {
                sumTotalFats += fat;
            }
            double sumTotalFibres = 0.0;
            for (double fibre : totalFibres) {
                sumTotalFibres += fibre;
            }
            Bitmap chartBitmap = createPieChart(this, sumTotalProteins, sumTotalCarbs, sumTotalFats, sumTotalFibres, sumTotalCalories);
// Draw the pie chart in the PDF
            canvas1.drawBitmap(chartBitmap, x, y, paint1);


            document.finishPage(page1);

            PdfDocument.PageInfo pageInfo2 = new PdfDocument.PageInfo.Builder(1300, 1920, 2).create();
            PdfDocument.Page page2 = document.startPage(pageInfo2);
            Canvas canvas2 = page2.getCanvas();
            Paint paint2 = new Paint();
            paint2.setColor(Color.BLUE);
            paint2.setTextSize(30);

            float X = 100;
            float Y = 200;
            float RowHeight = 36;

            canvas2.drawBitmap(watermarkBitmap, null, new Rect(0, 0, canvas2.getWidth(), canvas2.getHeight()), watermarkPaint);

            double[] totalCalcium = {totalCalcium1, totalCalcium2, totalCalcium3, totalCalcium4, totalCalcium5, totalCalcium6, totalCalcium7, totalCalcium8, totalCalcium9, totalCalcium10, totalCalcium11, totalCalcium12, totalCalcium13, totalCalcium14, totalCalcium15, totalCalcium16, totalCalcium17, totalCalcium18, totalCalcium19, totalCalcium20, totalCalcium21, totalCalcium22, totalCalcium23, totalCalcium24, totalCalcium25, totalCalcium26, totalCalcium27, totalCalcium28, totalCalcium29, totalCalcium30};
            double[] totalPhosphorus = {totalPhosphorus1, totalPhosphorus2, totalPhosphorus3, totalPhosphorus4, totalPhosphorus5, totalPhosphorus6, totalPhosphorus7, totalPhosphorus8, totalPhosphorus9, totalPhosphorus10, totalPhosphorus11, totalPhosphorus12, totalPhosphorus13, totalPhosphorus14, totalPhosphorus15, totalPhosphorus16, totalPhosphorus17, totalPhosphorus18, totalPhosphorus19, totalPhosphorus20, totalPhosphorus21, totalPhosphorus22, totalPhosphorus23, totalPhosphorus24, totalPhosphorus25, totalPhosphorus26, totalPhosphorus27, totalPhosphorus28, totalPhosphorus29, totalPhosphorus30};
            double[] totalMagnesium = {totalMagnesium1, totalMagnesium2, totalMagnesium3, totalMagnesium4, totalMagnesium5, totalMagnesium6, totalMagnesium7, totalMagnesium8, totalMagnesium9, totalMagnesium10, totalMagnesium11, totalMagnesium12, totalMagnesium13, totalMagnesium14, totalMagnesium15, totalMagnesium16, totalMagnesium17, totalMagnesium18, totalMagnesium19, totalMagnesium20, totalMagnesium21, totalMagnesium22, totalMagnesium23, totalMagnesium24, totalMagnesium25, totalMagnesium26, totalMagnesium27, totalMagnesium28, totalMagnesium29, totalMagnesium30};
            double[] totalElectrolyte = {totalElectrolyte1, totalElectrolyte2, totalElectrolyte3, totalElectrolyte4, totalElectrolyte5, totalElectrolyte6, totalElectrolyte7, totalElectrolyte8, totalElectrolyte9, totalElectrolyte10, totalElectrolyte11, totalElectrolyte12, totalElectrolyte13, totalElectrolyte14, totalElectrolyte15, totalElectrolyte16, totalElectrolyte17, totalElectrolyte18, totalElectrolyte19, totalElectrolyte20, totalElectrolyte21, totalElectrolyte22, totalElectrolyte23, totalElectrolyte24, totalElectrolyte25, totalElectrolyte26, totalElectrolyte27, totalElectrolyte28, totalElectrolyte29, totalElectrolyte30};
            double[] totalSodium = {totalSodium1, totalSodium2, totalSodium3, totalSodium4, totalSodium5, totalSodium6, totalSodium7, totalSodium8, totalSodium9, totalSodium10, totalSodium11, totalSodium12, totalSodium13, totalSodium14, totalSodium15, totalSodium16, totalSodium17, totalSodium18, totalSodium19, totalSodium20, totalSodium21, totalSodium22, totalSodium23, totalSodium24, totalSodium25, totalSodium26, totalSodium27, totalSodium28, totalSodium29, totalSodium30};
            double[] totalPotassium = {totalPotassium1, totalPotassium2, totalPotassium3, totalPotassium4, totalPotassium5, totalPotassium6, totalPotassium7, totalPotassium8, totalPotassium9, totalPotassium10, totalPotassium11, totalPotassium12, totalPotassium13, totalPotassium14, totalPotassium15, totalPotassium16, totalPotassium17, totalPotassium18, totalPotassium19, totalPotassium20, totalPotassium21, totalPotassium22, totalPotassium23, totalPotassium24, totalPotassium25, totalPotassium26, totalPotassium27, totalPotassium28, totalPotassium29, totalPotassium30};
            double[] totalChloride = {totalChloride1, totalChloride2, totalChloride3, totalChloride4, totalChloride5, totalChloride6, totalChloride7, totalChloride8, totalChloride9, totalChloride10, totalChloride11, totalChloride12, totalChloride13, totalChloride14, totalChloride15, totalChloride16, totalChloride17, totalChloride18, totalChloride19, totalChloride20, totalChloride21, totalChloride22, totalChloride23, totalChloride24, totalChloride25, totalChloride26, totalChloride27, totalChloride28, totalChloride29, totalChloride30};
            double[] totalIron = {totalIron1, totalIron2, totalIron3, totalIron4, totalIron5, totalIron6, totalIron7, totalIron8, totalIron9, totalIron10, totalIron11, totalIron12, totalIron13, totalIron14, totalIron15, totalIron16, totalIron17, totalIron18, totalIron19, totalIron20, totalIron21, totalIron22, totalIron23, totalIron24, totalIron25, totalIron26, totalIron27, totalIron28, totalIron29, totalIron30};
            double[] totalZinc = {totalZinc1, totalZinc2, totalZinc3, totalZinc4, totalZinc5, totalZinc6, totalZinc7, totalZinc8, totalZinc9, totalZinc10, totalZinc11, totalZinc12, totalZinc13, totalZinc14, totalZinc15, totalZinc16, totalZinc17, totalZinc18, totalZinc19, totalZinc20, totalZinc21, totalZinc22, totalZinc23, totalZinc24, totalZinc25, totalZinc26, totalZinc27, totalZinc28, totalZinc29, totalZinc30};
            double[] totalCopper = {totalCopper1, totalCopper2, totalCopper3, totalCopper4, totalCopper5, totalCopper6, totalCopper7, totalCopper8, totalCopper9, totalCopper10, totalCopper11, totalCopper12, totalCopper13, totalCopper14, totalCopper15, totalCopper16, totalCopper17, totalCopper18, totalCopper19, totalCopper20, totalCopper21, totalCopper22, totalCopper23, totalCopper24, totalCopper25, totalCopper26, totalCopper27, totalCopper28, totalCopper29, totalCopper30};
            double[] totalSelenium = {totalSelenium1, totalSelenium2, totalSelenium3, totalSelenium4, totalSelenium5, totalSelenium6, totalSelenium7, totalSelenium8, totalSelenium9, totalSelenium10, totalSelenium11, totalSelenium12, totalSelenium13, totalSelenium14, totalSelenium15, totalSelenium16, totalSelenium17, totalSelenium18, totalSelenium19, totalSelenium20, totalSelenium21, totalSelenium22, totalSelenium23, totalSelenium24, totalSelenium25, totalSelenium26, totalSelenium27, totalSelenium28, totalSelenium29, totalSelenium30};
            double[] totalChromium = {totalChromium1, totalChromium2, totalChromium3, totalChromium4, totalChromium5, totalChromium6, totalChromium7, totalChromium8, totalChromium9, totalChromium10, totalChromium11, totalChromium12, totalChromium13, totalChromium14, totalChromium15, totalChromium16, totalChromium17, totalChromium18, totalChromium19, totalChromium20, totalChromium21, totalChromium22, totalChromium23, totalChromium24, totalChromium25, totalChromium26, totalChromium27, totalChromium28, totalChromium29, totalChromium30};
            double[] totalIodine = {totalIodine1, totalIodine2, totalIodine3, totalIodine4, totalIodine5, totalIodine6, totalIodine7, totalIodine8, totalIodine9, totalIodine10, totalIodine11, totalIodine12, totalIodine13, totalIodine14, totalIodine15, totalIodine16, totalIodine17, totalIodine18, totalIodine19, totalIodine20, totalIodine21, totalIodine22, totalIodine23, totalIodine24, totalIodine25, totalIodine26, totalIodine27, totalIodine28, totalIodine29, totalIodine30};
            double[] totalManganese = {totalManganese1, totalManganese2, totalManganese3, totalManganese4, totalManganese5, totalManganese6, totalManganese7, totalManganese8, totalManganese9, totalManganese10, totalManganese11, totalManganese12, totalManganese13, totalManganese14, totalManganese15, totalManganese16, totalManganese17, totalManganese18, totalManganese19, totalManganese20, totalManganese21, totalManganese22, totalManganese23, totalManganese24, totalManganese25, totalManganese26, totalManganese27, totalManganese28, totalManganese29, totalManganese30};
            double[] totalMolybdenum = {totalMolybdenum1, totalMolybdenum2, totalMolybdenum3, totalMolybdenum4, totalMolybdenum5, totalMolybdenum6, totalMolybdenum7, totalMolybdenum8, totalMolybdenum9, totalMolybdenum10, totalMolybdenum11, totalMolybdenum12, totalMolybdenum13, totalMolybdenum14, totalMolybdenum15, totalMolybdenum16, totalMolybdenum17, totalMolybdenum18, totalMolybdenum19, totalMolybdenum20, totalMolybdenum21, totalMolybdenum22, totalMolybdenum23, totalMolybdenum24, totalMolybdenum25, totalMolybdenum26, totalMolybdenum27, totalMolybdenum28, totalMolybdenum29, totalMolybdenum30};
            double[] totalFluoride = {totalFluoride1, totalFluoride2, totalFluoride3, totalFluoride4, totalFluoride5, totalFluoride6, totalFluoride7, totalFluoride8, totalFluoride9, totalFluoride10, totalFluoride11, totalFluoride12, totalFluoride13, totalFluoride14, totalFluoride15, totalFluoride16, totalFluoride17, totalFluoride18, totalFluoride19, totalFluoride20, totalFluoride21, totalFluoride22, totalFluoride23, totalFluoride24, totalFluoride25, totalFluoride26, totalFluoride27, totalFluoride28, totalFluoride29, totalFluoride30};
            double[] totalBoron = {totalBoron1, totalBoron2, totalBoron3, totalBoron4, totalBoron5, totalBoron6, totalBoron7, totalBoron8, totalBoron9, totalBoron10, totalBoron11, totalBoron12, totalBoron13, totalBoron14, totalBoron15, totalBoron16, totalBoron17, totalBoron18, totalBoron19, totalBoron20, totalBoron21, totalBoron22, totalBoron23, totalBoron24, totalBoron25, totalBoron26, totalBoron27, totalBoron28, totalBoron29, totalBoron30};
            double[] totalSilicon = {totalSilicon1, totalSilicon2, totalSilicon3, totalSilicon4, totalSilicon5, totalSilicon6, totalSilicon7, totalSilicon8, totalSilicon9, totalSilicon10, totalSilicon11, totalSilicon12, totalSilicon13, totalSilicon14, totalSilicon15, totalSilicon16, totalSilicon17, totalSilicon18, totalSilicon19, totalSilicon20, totalSilicon21, totalSilicon22, totalSilicon23, totalSilicon24, totalSilicon25, totalSilicon26, totalSilicon27, totalSilicon28, totalSilicon29, totalSilicon30};
            double[] totalVanadium = {totalVanadium1, totalVanadium2, totalVanadium3, totalVanadium4, totalVanadium5, totalVanadium6, totalVanadium7, totalVanadium8, totalVanadium9, totalVanadium10, totalVanadium11, totalVanadium12, totalVanadium13, totalVanadium14, totalVanadium15, totalVanadium16, totalVanadium17, totalVanadium18, totalVanadium19, totalVanadium20, totalVanadium21, totalVanadium22, totalVanadium23, totalVanadium24, totalVanadium25, totalVanadium26, totalVanadium27, totalVanadium28, totalVanadium29, totalVanadium30};
            double[] totalCobalt = {totalCobalt1, totalCobalt2, totalCobalt3, totalCobalt4, totalCobalt5, totalCobalt6, totalCobalt7, totalCobalt8, totalCobalt9, totalCobalt10, totalCobalt11, totalCobalt12, totalCobalt13, totalCobalt14, totalCobalt15, totalCobalt16, totalCobalt17, totalCobalt18, totalCobalt19, totalCobalt20, totalCobalt21, totalCobalt22, totalCobalt23, totalCobalt24, totalCobalt25, totalCobalt26, totalCobalt27, totalCobalt28, totalCobalt29, totalCobalt30};

            double sumTotalCalcium = 0.0;
            for (double Calcium : totalCalcium) {
                sumTotalCalcium += Calcium;
            }

            double sumTotalPhosphorus = 0.0;
            for (double Phosphorus : totalPhosphorus) {
                sumTotalPhosphorus += Phosphorus;
            }

            double sumTotalMagnesium = 0.0;
            for (double Magnesium : totalMagnesium) {
                sumTotalMagnesium += Magnesium;
            }

            double sumTotalElectrolyte = 0.0;
            for (double Electrolyte : totalElectrolyte) {
                sumTotalElectrolyte += Electrolyte;
            }

            double sumTotalSodium = 0.0;
            for (double Sodium : totalSodium) {
                sumTotalSodium += Sodium;
            }

            double sumTotalPotassium = 0.0;
            for (double Potassium : totalPotassium) {
                sumTotalPotassium += Potassium;
            }

            double sumTotalChloride = 0.0;
            for (double Chloride : totalChloride) {
                sumTotalChloride += Chloride;
            }

            double sumTotalIron = 0.0;
            for (double Iron : totalIron) {
                sumTotalIron += Iron;
            }

            double sumTotalZinc = 0.0;
            for (double Zinc : totalZinc) {
                sumTotalZinc += Zinc;
            }

            double sumTotalCopper = 0.0;
            for (double Copper : totalCopper) {
                sumTotalCopper += Copper;
            }

            double sumTotalSelenium = 0.0;
            for (double Selenium : totalSelenium) {
                sumTotalSelenium += Selenium;
            }
            double sumTotalChromium = 0.0;
            for (double Chromium : totalChromium) {
                sumTotalChromium += Chromium;
            }
            double sumTotalIodine = 0.0;
            for (double Iodine : totalIodine) {
                sumTotalIodine += Iodine;
            }
            double sumTotalManganese = 0.0;
            for (double Manganese : totalManganese) {
                sumTotalManganese += Manganese;
            }
            double sumTotalMolybdenum = 0.0;
            for (double Molybdenum : totalMolybdenum) {
                sumTotalMolybdenum += Molybdenum;
            }
            double sumTotalFluoride = 0.0;
            for (double Fluoride : totalFluoride) {
                sumTotalFluoride += Fluoride;
            }
            double sumTotalBoron = 0.0;
            for (double Boron : totalBoron) {
                sumTotalBoron += Boron;
            }
            double sumTotalSilicon = 0.0;
            for (double Silicon : totalSilicon) {
                sumTotalSilicon += Silicon;
            }
            double sumTotalVanadium = 0.0;
            for (double Vanadium : totalVanadium) {
                sumTotalVanadium += Vanadium;
            }
            double sumTotalCobalt = 0.0;
            for (double Cobalt : totalCobalt) {
                sumTotalCobalt += Cobalt;
            }


            String formattedSum = String.format("%.2f", sumTotalCalcium);
            String formattedSumPhosphorus = String.format("%.2f", sumTotalPhosphorus);
            String formattedSumMagnesium = String.format("%.2f", sumTotalMagnesium);
            String formattedSumElectrolyte = String.format("%.2f", sumTotalElectrolyte);
            String formattedSumSodium = String.format("%.2f", sumTotalSodium);
            String formattedSumPotassium = String.format("%.2f", sumTotalPotassium);
            String formattedSumChloride = String.format("%.2f", sumTotalChloride);
            String formattedSumIron = String.format("%.2f", sumTotalIron);
            String formattedSumZinc = String.format("%.2f", sumTotalZinc);
            String formattedSumCopper = String.format("%.2f", sumTotalCopper);
            String formattedSumSelenium = String.format("%.2f", sumTotalSelenium);
            String formattedSumChromium = String.format("%.2f", sumTotalChromium);
            String formattedSumIodine = String.format("%.2f", sumTotalIodine);
            String formattedSumManganese = String.format("%.2f", sumTotalManganese);
            String formattedSumMolybdenum = String.format("%.2f", sumTotalMolybdenum);
            String formattedSumFluoride = String.format("%.2f", sumTotalFluoride);
            String formattedSumBoron = String.format("%.2f", sumTotalBoron);
            String formattedSumSilicon = String.format("%.2f", sumTotalSilicon);
            String formattedSumVanadium = String.format("%.2f", sumTotalVanadium);
            String formattedSumCobalt = String.format("%.2f", sumTotalCobalt);


            String sumTotalCalciumText = "Total Calcium: " + formattedSum;
            String sumTotalPhosphorusText = "Total Phosphorus" + formattedSumPhosphorus;
            String sumTotalMagnesiumText = "Total Magnesium" + formattedSumMagnesium;
            String sumTotalElectrolyteText = "Total Electrolytes" + formattedSumElectrolyte;
            String sumTotalSodiumText = "Total Sodium: " + formattedSumSodium;
            String sumTotalPotassiumText = "Total Potassium: " + formattedSumPotassium;
            String sumTotalChlorideText = "Total Chloride: " + formattedSumChloride;
            String sumTotalIronText = "Total Iron: " + formattedSumIron;
            String sumTotalZincText = "Total Zinc: " + formattedSumZinc;
            String sumTotalCopperText = "Total Copper: " + formattedSumCopper;
            String sumTotalSeleniumText = "Total Selenium: " + formattedSumSelenium;
            String sumTotalChromiumText = "Total Chromium: " + formattedSumChromium;
            String sumTotalIodineText = "Total Iodine: " + formattedSumIodine;
            String sumTotalManganeseText = "Total Manganese: " + formattedSumManganese;
            String sumTotalMolybdenumText = "Total Molybdenum: " + formattedSumMolybdenum;
            String sumTotalFluorideText = "Total Fluoride: " + formattedSumFluoride;
            String sumTotalBoronText = "Total Boron: " + formattedSumBoron;
            String sumTotalSiliconText = "Total Silicon: " + formattedSumSilicon;
            String sumTotalVanadiumText = "Total Vanadium: " + formattedSumVanadium;
            String sumTotalCobaltText = "Total Cobalt: " + formattedSumCobalt;

            float chartLeft = 30;
            float chartTop = 600;
            float chartRight = pageInfo2.getPageWidth() - 80; // Adjust as needed
            float chartBottom = pageInfo2.getPageHeight() - 500;

            BarChart barChart = new BarChart(this);
            barChart.layout((int) chartLeft, (int) chartTop, (int) chartRight, (int) chartBottom);
            barChart.getLegend().setEnabled(false);
// Prepare data for the BarChart

            ArrayList<BarEntry> entries = new ArrayList<>();
            entries.add(new BarEntry(1F, (float) sumTotalCalcium));
            entries.add(new BarEntry(2F, (float) sumTotalPhosphorus));
            entries.add(new BarEntry(3F, (float) sumTotalMagnesium));
            entries.add(new BarEntry(4F, (float) sumTotalElectrolyte));
            entries.add(new BarEntry(5F, (float) sumTotalSodium));
            entries.add(new BarEntry(6F, (float) sumTotalPotassium));
            entries.add(new BarEntry(7F, (float) sumTotalChloride));
            entries.add(new BarEntry(8F, (float) sumTotalIron));
            entries.add(new BarEntry(9F, (float) sumTotalZinc));
            entries.add(new BarEntry(10F, (float) sumTotalCopper));
            entries.add(new BarEntry(11F, (float) sumTotalSelenium));
            entries.add(new BarEntry(12F, (float) sumTotalChromium));
            entries.add(new BarEntry(13F, (float) sumTotalIodine));
            entries.add(new BarEntry(14F, (float) sumTotalManganese));
            entries.add(new BarEntry(15F, (float) sumTotalMolybdenum));
            entries.add(new BarEntry(16F, (float) sumTotalFluoride));
            entries.add(new BarEntry(17F, (float) sumTotalBoron));
            entries.add(new BarEntry(18F, (float) sumTotalSilicon));
            entries.add(new BarEntry(19F, (float) sumTotalVanadium));
            entries.add(new BarEntry(20F, (float) sumTotalCobalt));

            BarDataSet calciumDataSet = new BarDataSet(getEntriesForIndex(entries, 0), "");
            BarDataSet phosphorusDataSet = new BarDataSet(getEntriesForIndex(entries, 1), "");
            BarDataSet MagnesiumDataSet = new BarDataSet(getEntriesForIndex(entries, 2), "");
            BarDataSet ElectrolyteDataSet = new BarDataSet(getEntriesForIndex(entries, 3), "");
            BarDataSet SodiumDataSet = new BarDataSet(getEntriesForIndex(entries, 4), "");
            BarDataSet PotassiumDataSet = new BarDataSet(getEntriesForIndex(entries, 5), "");
            BarDataSet ChlorideDataSet = new BarDataSet(getEntriesForIndex(entries, 6), "");
            BarDataSet IronDataSet = new BarDataSet(getEntriesForIndex(entries, 7), "");
            BarDataSet ZincDataSet = new BarDataSet(getEntriesForIndex(entries, 8), "");
            BarDataSet CopperDataSet = new BarDataSet(getEntriesForIndex(entries, 9), "");
            BarDataSet SeleniumDataSet = new BarDataSet(getEntriesForIndex(entries, 10), "");
            BarDataSet ChromiumDataSet = new BarDataSet(getEntriesForIndex(entries, 11), "");
            BarDataSet IodineDataSet = new BarDataSet(getEntriesForIndex(entries, 12), "");
            BarDataSet ManganeseDataSet = new BarDataSet(getEntriesForIndex(entries, 13), "");
            BarDataSet MolybdenumDataSet = new BarDataSet(getEntriesForIndex(entries, 14), "");
            BarDataSet FluorideDataSet = new BarDataSet(getEntriesForIndex(entries, 15), "");
            BarDataSet BoronDataSet = new BarDataSet(getEntriesForIndex(entries, 16), "");
            BarDataSet SiliconDataSet = new BarDataSet(getEntriesForIndex(entries, 17), "");
            BarDataSet VanadiumDataSet = new BarDataSet(getEntriesForIndex(entries, 18), "");
            BarDataSet CobaltDataSet = new BarDataSet(getEntriesForIndex(entries, 19), "");
// Create BarData from the datasets

            if (sumTotalCalcium >= 2500) {
                calciumDataSet.setColor(Color.RED);
            } else {
                calciumDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalPhosphorus >= 4000) {
                phosphorusDataSet.setColor(Color.RED);
            } else {
                phosphorusDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalMagnesium >= 350) {
                MagnesiumDataSet.setColor(Color.RED);
            } else {
                MagnesiumDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalElectrolyte >= 1000) {
                ElectrolyteDataSet.setColor(Color.RED);
            } else {
                ElectrolyteDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalSodium >= 500) {
                SodiumDataSet.setColor(Color.RED);
            } else {
                SodiumDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalPotassium >= 500) {
                PotassiumDataSet.setColor(Color.RED);
            } else {
                PotassiumDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalChloride >= 3600) {
                ChlorideDataSet.setColor(Color.RED);
            } else {
                ChlorideDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalIron >= 45) {
                IronDataSet.setColor(Color.RED);
            } else {
                IronDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalZinc >= 40) {
                ZincDataSet.setColor(Color.RED);
            } else {
                ZincDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalCopper >= 10) {
                CopperDataSet.setColor(Color.RED);
            } else {
                CopperDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalSelenium >= 400) {
                SeleniumDataSet.setColor(Color.RED);
            } else {
                SeleniumDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalChromium >= 500) {
                ChromiumDataSet.setColor(Color.RED);
            } else {
                ChromiumDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalIodine >= 1100) {
                IodineDataSet.setColor(Color.RED);
            } else {
                IodineDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalManganese >= 11) {
                ManganeseDataSet.setColor(Color.RED);
            } else {
                ManganeseDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalMolybdenum >= 2000) {
                MolybdenumDataSet.setColor(Color.RED);
            } else {
                MolybdenumDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalFluoride >= 10) {
                FluorideDataSet.setColor(Color.RED);
            } else {
                FluorideDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalBoron >= 200) {
                BoronDataSet.setColor(Color.RED);
            } else {
                BoronDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalSilicon >= 200) {
                SiliconDataSet.setColor(Color.RED);
            } else {
                SiliconDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalVanadium >= 500) {
                VanadiumDataSet.setColor(Color.RED);
            } else {
                VanadiumDataSet.setColor(Color.GREEN); // Default color
            }

            if (sumTotalCobalt >= 500) {
                CobaltDataSet.setColor(Color.RED);
            } else {
                CobaltDataSet.setColor(Color.GREEN); // Default color
            }

            calciumDataSet.setValueTextColor(Color.BLACK);
            phosphorusDataSet.setValueTextColor(Color.BLACK);
            MagnesiumDataSet.setValueTextColor(Color.BLACK);
            ElectrolyteDataSet.setValueTextColor(Color.BLACK);
            SodiumDataSet.setValueTextColor(Color.BLACK);
            PotassiumDataSet.setValueTextColor(Color.BLACK);
            ChlorideDataSet.setValueTextColor(Color.BLACK);
            IronDataSet.setValueTextColor(Color.BLACK);
            ZincDataSet.setValueTextColor(Color.BLACK);
            CopperDataSet.setValueTextColor(Color.BLACK);
            SeleniumDataSet.setValueTextColor(Color.BLACK);
            ChromiumDataSet.setValueTextColor(Color.BLACK);
            IodineDataSet.setValueTextColor(Color.BLACK);
            ManganeseDataSet.setValueTextColor(Color.BLACK);
            MolybdenumDataSet.setValueTextColor(Color.BLACK);
            FluorideDataSet.setValueTextColor(Color.BLACK);
            BoronDataSet.setValueTextColor(Color.BLACK);
            SiliconDataSet.setValueTextColor(Color.BLACK);
            VanadiumDataSet.setValueTextColor(Color.BLACK);
            CobaltDataSet.setValueTextColor(Color.BLACK);

            BarData barData = new BarData(calciumDataSet, phosphorusDataSet, MagnesiumDataSet, ElectrolyteDataSet, SodiumDataSet, PotassiumDataSet, ChlorideDataSet, IronDataSet, ZincDataSet, CopperDataSet, SeleniumDataSet, ChromiumDataSet, IodineDataSet, ManganeseDataSet, MolybdenumDataSet, FluorideDataSet, BoronDataSet, SiliconDataSet, VanadiumDataSet, CobaltDataSet);
            barChart.setData(barData);
// Customize the chart as needed
            XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setGranularity(1f);
            xAxis.setLabelCount(entries.size());
            xAxis.setDrawLabels(true);
            xAxis.setDrawGridLines(false);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"S", "Ca", "P", "Mg", "El", "Na", "K", "Cl", "Fe", "Zn", "Cu", "Se", "Cr", "I", "Mn", "Mo", "F", "B", "Si", "V", "Co"}));

            barChart.draw(canvas2);
            Y += 19 * RowHeight;
            paint2.setColor(Color.BLUE);
            canvas2.drawText("MINERALS BAR GRAPH", X + 250, Y, paint2);
            Y += 2 * RowHeight;
            canvas2.drawText("MICRO COUNT :", X, Y, paint2);
            Y += 2 * RowHeight;

            canvas2.drawText("MAJOR MINERALS PROFILE :", X, Y, paint2);
            Y += 2 * RowHeight;
            canvas2.drawText(sumTotalCalciumText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalPhosphorusText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalMagnesiumText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalElectrolyteText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalSodiumText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalPotassiumText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalChlorideText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalIronText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalZincText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalCopperText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalSeleniumText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalChromiumText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalIodineText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalManganeseText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalMolybdenumText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText("NON ESSENTIAL TRACE AND ULTRA TRACE MINERALS :", X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalFluorideText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalBoronText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalSiliconText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalVanadiumText, X, Y, paint2);
            Y += RowHeight;
            canvas2.drawText(sumTotalCobaltText, X, Y, paint2);
            Y += 2 * RowHeight;

            document.finishPage(page2);

            PdfDocument.PageInfo pageInfo3 = new PdfDocument.PageInfo.Builder(1300, 1920, 3).create();
            PdfDocument.Page page3 = document.startPage(pageInfo3);
            Canvas canvas3 = page3.getCanvas();
            Paint paint3 = new Paint();
            paint3.setColor(Color.BLACK);
            paint3.setTextSize(30);

            double[] totalVitaminA = {totalVitaminA1, totalVitaminA2, totalVitaminA3, totalVitaminA4, totalVitaminA5, totalVitaminA6, totalVitaminA7, totalVitaminA8, totalVitaminA9, totalVitaminA10, totalVitaminA11, totalVitaminA12, totalVitaminA13, totalVitaminA14, totalVitaminA15, totalVitaminA16, totalVitaminA17, totalVitaminA18, totalVitaminA19, totalVitaminA20, totalVitaminA21, totalVitaminA22, totalVitaminA23, totalVitaminA24, totalVitaminA25, totalVitaminA26, totalVitaminA27, totalVitaminA28, totalVitaminA29, totalVitaminA30};
            double[] totalVitaminD = {totalVitaminD1, totalVitaminD2, totalVitaminD3, totalVitaminD4, totalVitaminD5, totalVitaminD6, totalVitaminD7, totalVitaminD8, totalVitaminD9, totalVitaminD10, totalVitaminD11, totalVitaminD12, totalVitaminD13, totalVitaminD14, totalVitaminD15, totalVitaminD16, totalVitaminD17, totalVitaminD18, totalVitaminD19, totalVitaminD20, totalVitaminD21, totalVitaminD22, totalVitaminD23, totalVitaminD24, totalVitaminD25, totalVitaminD26, totalVitaminD27, totalVitaminD28, totalVitaminD29, totalVitaminD30};
            double[] totalVitaminE = {totalVitaminE1, totalVitaminE2, totalVitaminE3, totalVitaminE4, totalVitaminE5, totalVitaminE6, totalVitaminE7, totalVitaminE8, totalVitaminE9, totalVitaminE10, totalVitaminE11, totalVitaminE12, totalVitaminE13, totalVitaminE14, totalVitaminE15, totalVitaminE16, totalVitaminE17, totalVitaminE18, totalVitaminE19, totalVitaminE20, totalVitaminE21, totalVitaminE22, totalVitaminE23, totalVitaminE24, totalVitaminE25, totalVitaminE26, totalVitaminE27, totalVitaminE28, totalVitaminE29, totalVitaminE30};
            double[] totalVitaminK = {totalVitaminK1, totalVitaminK2, totalVitaminK3, totalVitaminK4, totalVitaminK5, totalVitaminK6, totalVitaminK7, totalVitaminK8, totalVitaminK9, totalVitaminK10, totalVitaminK11, totalVitaminK12, totalVitaminK13, totalVitaminK14, totalVitaminK15, totalVitaminK16, totalVitaminK17, totalVitaminK18, totalVitaminK19, totalVitaminK20, totalVitaminK21, totalVitaminK22, totalVitaminK23, totalVitaminK24, totalVitaminK25, totalVitaminK26, totalVitaminK27, totalVitaminK28, totalVitaminK29, totalVitaminK30};
            double[] totalVitaminB = {totalVitaminB1, totalVitaminB2, totalVitaminB3, totalVitaminB4, totalVitaminB5, totalVitaminB6, totalVitaminB7, totalVitaminB8, totalVitaminB9, totalVitaminB10, totalVitaminB51, totalVitaminB50, totalVitaminB56, totalVitaminB52, totalVitaminB53, totalVitaminB54, totalVitaminB55, totalVitaminB57, totalVitaminB58, totalVitaminB59, totalVitaminB60, totalVitaminB80, totalVitaminB81, totalVitaminB82, totalVitaminB83, totalVitaminB84, totalVitaminB85, totalVitaminB86, totalVitaminB87, totalVitaminB88};
            double[] totalVitaminT = {totalVitaminB11, totalVitaminB12, totalVitaminB13, totalVitaminB14, totalVitaminB15, totalVitaminB16, totalVitaminB17, totalVitaminB18, totalVitaminB19, totalVitaminB110, totalVitaminB111, totalVitaminB112, totalVitaminB113, totalVitaminB114, totalVitaminB115, totalVitaminB116, totalVitaminB117, totalVitaminB118, totalVitaminB119, totalVitaminB120, totalVitaminB131, totalVitaminB132, totalVitaminB133, totalVitaminB134, totalVitaminB135, totalVitaminB136, totalVitaminB137, totalVitaminB138, totalVitaminB139, totalVitaminB140};
            double[] totalVitaminR = {totalVitaminB21, totalVitaminB22, totalVitaminB23, totalVitaminB24, totalVitaminB25, totalVitaminB26, totalVitaminB27, totalVitaminB28, totalVitaminB29, totalVitaminB210, totalVitaminB211, totalVitaminB212, totalVitaminB213, totalVitaminB214, totalVitaminB215, totalVitaminB216, totalVitaminB217, totalVitaminB218, totalVitaminB219, totalVitaminB220, totalVitaminB221, totalVitaminB222, totalVitaminB223, totalVitaminB224, totalVitaminB225, totalVitaminB226, totalVitaminB227, totalVitaminB228, totalVitaminB229, totalVitaminB230};
            double[] totalVitaminN = {totalVitaminB31, totalVitaminB32, totalVitaminB33, totalVitaminB34, totalVitaminB35, totalVitaminB36, totalVitaminB37, totalVitaminB38, totalVitaminB39, totalVitaminB310, totalVitaminB311, totalVitaminB312, totalVitaminB313, totalVitaminB314, totalVitaminB315, totalVitaminB316, totalVitaminB317, totalVitaminB318, totalVitaminB319, totalVitaminB320, totalVitaminB321, totalVitaminB322, totalVitaminB323, totalVitaminB324, totalVitaminB325, totalVitaminB326, totalVitaminB327, totalVitaminB328, totalVitaminB329, totalVitaminB330};
            double[] totalVitaminP = {totalVitaminP1, totalVitaminP2, totalVitaminP3, totalVitaminP4, totalVitaminP5, totalVitaminP6, totalVitaminP7, totalVitaminP8, totalVitaminP9, totalVitaminP10, totalVitaminP11, totalVitaminP12, totalVitaminP13, totalVitaminP14, totalVitaminP15, totalVitaminP16, totalVitaminP17, totalVitaminP18, totalVitaminP19, totalVitaminP20, totalVitaminP21, totalVitaminP22, totalVitaminP23, totalVitaminP24, totalVitaminP25, totalVitaminP26, totalVitaminP27, totalVitaminP28, totalVitaminP29, totalVitaminP30};
            double[] totalVitaminBi = {totalVitaminB71, totalVitaminB72, totalVitaminB73, totalVitaminB74, totalVitaminB75, totalVitaminB76, totalVitaminB77, totalVitaminB78, totalVitaminB79, totalVitaminB710, totalVitaminB711, totalVitaminB712, totalVitaminB713, totalVitaminB714, totalVitaminB715, totalVitaminB716, totalVitaminB717, totalVitaminB718, totalVitaminB719, totalVitaminB720, totalVitaminB721, totalVitaminB722, totalVitaminB723, totalVitaminB724, totalVitaminB725, totalVitaminB726, totalVitaminB727, totalVitaminB728, totalVitaminB729, totalVitaminB730};
            double[] totalVitaminf = {totalVitaminB91, totalVitaminB92, totalVitaminB93, totalVitaminB94, totalVitaminB95, totalVitaminB96, totalVitaminB97, totalVitaminB98, totalVitaminB99, totalVitaminB910, totalVitaminB911, totalVitaminB912, totalVitaminB913, totalVitaminB914, totalVitaminB915, totalVitaminB916, totalVitaminB917, totalVitaminB918, totalVitaminB919, totalVitaminB920, totalVitaminB921, totalVitaminB922, totalVitaminB923, totalVitaminB924, totalVitaminB925, totalVitaminB926, totalVitaminB927, totalVitaminB928, totalVitaminB929, totalVitaminB930};
            double[] totalVitaminCo = {totalVitaminB121, totalVitaminB122, totalVitaminB123, totalVitaminB124, totalVitaminB125, totalVitaminB126, totalVitaminB127, totalVitaminB128, totalVitaminB129, totalVitaminB1210, totalVitaminB1211, totalVitaminB1212, totalVitaminB1213, totalVitaminB1214, totalVitaminB1215, totalVitaminB1216, totalVitaminB1217, totalVitaminB1218, totalVitaminB1219, totalVitaminB1220, totalVitaminB1221, totalVitaminB1222, totalVitaminB1223, totalVitaminB1224, totalVitaminB1225, totalVitaminB1226, totalVitaminB1227, totalVitaminB1228, totalVitaminB1229, totalVitaminB1230};
            double[] totalVitaminb = {totalVitaminB61, totalVitaminB62, totalVitaminB63, totalVitaminB64, totalVitaminB65, totalVitaminB66, totalVitaminB67, totalVitaminB68, totalVitaminB69, totalVitaminB610, totalVitaminB611, totalVitaminB612, totalVitaminB613, totalVitaminB614, totalVitaminB615, totalVitaminB616, totalVitaminB617, totalVitaminB618, totalVitaminB619, totalVitaminB620, totalVitaminB621, totalVitaminB622, totalVitaminB623, totalVitaminB624, totalVitaminB625, totalVitaminB626, totalVitaminB627, totalVitaminB628, totalVitaminB629, totalVitaminB630};
            double[] totalVitaminC = {totalVitaminC1, totalVitaminC2, totalVitaminC3, totalVitaminC4, totalVitaminC5, totalVitaminC6, totalVitaminC7, totalVitaminC8, totalVitaminC9, totalVitaminC10, totalVitaminC11, totalVitaminC12, totalVitaminC13, totalVitaminC14, totalVitaminC15, totalVitaminC16, totalVitaminC17, totalVitaminC18, totalVitaminC19, totalVitaminC20, totalVitaminC21, totalVitaminC22, totalVitaminC23, totalVitaminC24, totalVitaminC25, totalVitaminC26, totalVitaminC27, totalVitaminC28, totalVitaminC29, totalVitaminC30};

            double sumTotalVitaminA = 0.0;
            for (double VitaminA : totalVitaminA) {
                sumTotalVitaminA += VitaminA;
            }
            double sumTotalVitaminD = 0.0;
            for (double VitaminD : totalVitaminD) {
                sumTotalVitaminD += VitaminD;
            }
            double sumTotalVitaminE = 0.0;
            for (double VitaminE : totalVitaminE) {
                sumTotalVitaminE += VitaminE;
            }
            double sumTotalVitaminK = 0.0;
            for (double VitaminK : totalVitaminK) {
                sumTotalVitaminK += VitaminK;
            }
            double sumTotalVitaminB = 0.0;
            for (double VitaminB : totalVitaminB) {
                sumTotalVitaminB += VitaminB;
            }
            double sumTotalVitaminB1 = 0.0;
            for (double VitaminB1 : totalVitaminT) {
                sumTotalVitaminB1 += VitaminB1;
            }
            double sumTotalVitaminB2 = 0.0;
            for (double VitaminB2 : totalVitaminR) {
                sumTotalVitaminB2 += VitaminB2;
            }
            double sumTotalVitaminB3 = 0.0;
            for (double VitaminB3 : totalVitaminN) {
                sumTotalVitaminB3 += VitaminB3;
            }
            double sumTotalVitaminP = 0.0;
            for (double VitaminP : totalVitaminP) {
                sumTotalVitaminP += VitaminP;
            }
            double sumTotalVitaminB7 = 0.0;
            for (double VitaminB7 : totalVitaminBi) {
                sumTotalVitaminB7 += VitaminB7;
            }
            double sumTotalVitaminB9 = 0.0;
            for (double VitaminB9 : totalVitaminf) {
                sumTotalVitaminB9 += VitaminB9;
            }
            double sumTotalVitaminB12 = 0.0;
            for (double VitaminB12 : totalVitaminCo) {
                sumTotalVitaminB12 += VitaminB12;
            }
            double sumTotalVitaminB6 = 0.0;
            for (double VitaminB6 : totalVitaminb) {
                sumTotalVitaminB6 += VitaminB6;
            }
            double sumTotalVitaminC = 0.0;
            for (double VitaminC : totalVitaminC) {
                sumTotalVitaminC += VitaminC;
            }

            String formattedSumVitaminA = String.format("%.2f", sumTotalVitaminA);
            String formattedSumVitaminD = String.format("%.2f", sumTotalVitaminD);
            String formattedSumVitaminE = String.format("%.2f", sumTotalVitaminE);
            String formattedSumVitaminK = String.format("%.2f", sumTotalVitaminK);
            String formattedSumVitaminB = String.format("%.2f", sumTotalVitaminB);
            String formattedSumVitaminB1 = String.format("%.2f", sumTotalVitaminB1);
            String formattedSumVitaminB2 = String.format("%.2f", sumTotalVitaminB2);
            String formattedSumVitaminB3 = String.format("%.2f", sumTotalVitaminB3);
            String formattedSumVitaminP = String.format("%.2f", sumTotalVitaminP);
            String formattedSumVitaminB7 = String.format("%.2f", sumTotalVitaminB7);
            String formattedSumVitaminB9 = String.format("%.2f", sumTotalVitaminB9);
            String formattedSumVitaminB12 = String.format("%.2f", sumTotalVitaminB12);
            String formattedSumVitaminB6 = String.format("%.2f", sumTotalVitaminB6);
            String formattedSumVitaminC = String.format("%.2f", sumTotalVitaminC);

            String sumTotalVitaminAText = "Total VitaminA: " + formattedSumVitaminA;
            String sumTotalVitaminDText = "Total VitaminD: " + formattedSumVitaminD;
            String sumTotalVitaminEText = "Total VitaminE: " + formattedSumVitaminE;
            String sumTotalVitaminKText = "Total VitaminK: " + formattedSumVitaminK;
            String sumTotalVitaminBText = "Total VitaminB: " + formattedSumVitaminB;
            String sumTotalVitaminB1Text = "Total VitaminB1(thiamin): " + formattedSumVitaminB1;
            String sumTotalVitaminB2Text = "Total VitaminB2(riboflavin): " + formattedSumVitaminB2;
            String sumTotalVitaminB3Text = "Total VitaminB3: " + formattedSumVitaminB3;
            String sumTotalVitaminPText = "Total Pantothenic Acid: " + formattedSumVitaminP;
            String sumTotalVitaminB7Text = "Total VitaminB7: " + formattedSumVitaminB7;
            String sumTotalVitaminB9Text = "Total VitaminB9: " + formattedSumVitaminB9;
            String sumTotalVitaminB12Text = "Total VitaminB12: " + formattedSumVitaminB12;
            String sumTotalVitaminB6Text = "Total VitaminB6: " + formattedSumVitaminB6;
            String sumTotalVitaminCText = "Total VitaminC: " + formattedSumVitaminC;
            float A = 100;
            float B = 200;
            float ARowHeight = 36;

            canvas3.drawBitmap(watermarkBitmap, null, new Rect(0, 0, canvas3.getWidth(), canvas3.getHeight()), watermarkPaint);
            int chartWidth = 2400 / 2; // Adjust as needed
            int chartHeight = 2500 / 3;
            BarChart VitaminbarChart = new BarChart(this);
            VitaminbarChart.setLayoutParams(new ViewGroup.LayoutParams(chartWidth, chartHeight));

// Add your vitamin values to the BarChart
            ArrayList<BarEntry> Entries = new ArrayList<>();
            double[] sumTotalVitaminValues = {sumTotalVitaminA, sumTotalVitaminD, sumTotalVitaminE, sumTotalVitaminK, sumTotalVitaminB, sumTotalVitaminB1, sumTotalVitaminB2, sumTotalVitaminB3, sumTotalVitaminP, sumTotalVitaminB7, sumTotalVitaminB9, sumTotalVitaminB12, sumTotalVitaminB6, sumTotalVitaminC}; // Replace with your actual values
            for (int i = 0; i < sumTotalVitaminValues.length; i++) {
                Entries.add(new BarEntry(i, (float) sumTotalVitaminValues[i]));
            }

            BarDataSet barDataSet = new BarDataSet(Entries, "");
            barDataSet.setValueTextSize(10f);
            VitaminbarChart.getLegend().setEnabled(false);

            // Create a list to hold colors for each entry
            ArrayList<Integer> colors = new ArrayList<>();

// Iterate through the Entries list and set color based on conditions
            for (int i = 0; i < Entries.size(); i++) {
                float vitaminValue = Entries.get(i).getY();
                if (i == 0 && vitaminValue >= 3000) {
                    colors.add(Color.RED);
                } else if (i == 1 && vitaminValue >= 100) {
                    colors.add(Color.RED);
                } else if (i == 2 && vitaminValue >= 1000) {
                    colors.add(Color.RED);
                } else if (i == 3 && vitaminValue >= 100) {
                    colors.add(Color.RED);
                } else if (i == 4 && vitaminValue >= 100) {
                    colors.add(Color.RED);
                } else if (i == 5 && vitaminValue >= 200) {
                    colors.add(Color.RED);
                } else if (i == 6 && vitaminValue >= 100) {
                    colors.add(Color.RED);
                } else if (i == 7 && vitaminValue >= 35) {
                    colors.add(Color.RED);
                } else if (i == 8 && vitaminValue >= 200) {
                    colors.add(Color.RED);
                } else if (i == 9 && vitaminValue >= 200) {
                    colors.add(Color.RED);
                } else if (i == 10 && vitaminValue >= 300) {
                    colors.add(Color.RED);
                } else if (i == 11 && vitaminValue >= 300) {
                    colors.add(Color.RED);
                } else if (i == 12 && vitaminValue >= 100) {
                    colors.add(Color.RED);
                } else if (i == 13 && vitaminValue >= 2000) {
                    colors.add(Color.RED);
                } else {
                    colors.add(Color.YELLOW);
                }
            }

// Set the list of colors to the BarDataSet
            barDataSet.setColors(colors);

            BarData barData1 = new BarData(barDataSet);
            VitaminbarChart.setData(barData1);
            VitaminbarChart.measure(chartWidth, chartHeight);
            VitaminbarChart.layout(0, 0, chartWidth, chartHeight);

// Customize the chart appearance
            XAxis xAxis1 = VitaminbarChart.getXAxis();
            xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis1.setDrawGridLines(false);
            xAxis1.setLabelCount(Entries.size());
            xAxis1.setDrawLabels(true);
            xAxis1.setValueFormatter(new IndexAxisValueFormatter(new String[]{"A", "D", "E", "K", "B", "B1", "B2", "B3", "P", "B7", "B9", "B12", "B6", "C"})); // You may need to implement a custom formatter depending on your data.

            YAxis yAxis = VitaminbarChart.getAxisLeft();
            yAxis.setDrawGridLines(false);
// Draw the BarChart onto the PDF
            VitaminbarChart.draw(canvas3);
            B += 19 * ARowHeight;
            paint3.setColor(Color.BLUE);
            canvas3.drawText("VITAMINS BAR GRAPH", A + 250, B, paint3);
            B += 2 * ARowHeight;
            canvas3.drawText("VITAMINS PROFILE :", A, B, paint3);
            B += 2 * ARowHeight;
            canvas3.drawText(sumTotalVitaminAText, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminDText, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminEText, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminKText, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminBText, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminB1Text, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminB2Text, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminB3Text, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminPText, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminB7Text, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminB9Text, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminB12Text, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminB6Text, A, B, paint3);
            B += ARowHeight;
            canvas3.drawText(sumTotalVitaminCText, A, B, paint3);

            document.finishPage(page3);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // For Android 10 and above, use MediaStore
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, "in2Fitness.pdf");
                values.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

                Uri uri = getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);

                try {
                    OutputStream out = getContentResolver().openOutputStream(uri);
                    document.writeTo(out);
                    out.close();
                    Toast.makeText(this, "PDF written successfully!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error while writing the PDF", Toast.LENGTH_SHORT).show();
                }
            } else {
                // For Android 9 and below, use legacy method
                File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                String fileName = "in2Fitness.pdf";
                File file = new File(downloadsDir, fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    document.writeTo(fos);
                    fos.close();

                    // Trigger media scanner to make file visible
                    MediaScannerConnection.scanFile(this, new String[]{file.getAbsolutePath()}, null, null);

                    Toast.makeText(this, "PDF written successfully!", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    Log.d("mylog", "Error while writing" + e.toString());
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        // Close the document
            document.close();

    }

    private List<BarEntry> getEntriesForIndex(List<BarEntry> entries, int index) {
        List<BarEntry> result = new ArrayList<>();
        result.add(entries.get(index));
        return result;
    }

    public Bitmap createPieChart(Context context, double sumTotalProteins, double sumTotalCarbs, double sumTotalFats, double sumTotalFibres , double sumTotalCalories) {
        // ... (rest of the code)
        PieChart pieChart = new PieChart(context);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) sumTotalProteins, "Proteins"));
        entries.add(new PieEntry((float) sumTotalCarbs, "Carbs"));
        entries.add(new PieEntry((float) sumTotalFats, "Fats"));
        entries.add(new PieEntry((float) sumTotalFibres, "Fibres"));

        PieDataSet dataSet = new PieDataSet(entries, "Nutrient Distribution");

        pieChart.setCenterText(generateCenterSpannableText((int) sumTotalCalories));

// Customize colors and appearance
        dataSet.setColors(Color.rgb(255, 102, 102), Color.rgb(255, 204, 102), Color.rgb(102, 178, 255), Color.rgb(204, 204, 255));
        dataSet.setValueTextSize(10f);
        dataSet.setValueTextColor(Color.BLACK);

        pieChart.setHoleRadius(30f);
        pieChart.setTransparentCircleRadius(35f);
        pieChart.setDrawEntryLabels(false);
        pieChart.getDescription().setEnabled(false);

        pieChart.setMinimumHeight(600);
        pieChart.setMinimumWidth(600);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);



// Convert the PieChart to a Bitmap
        pieChart.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        pieChart.layout(0, 0, pieChart.getMeasuredWidth(), pieChart.getMeasuredHeight());
        pieChart.layout(0, 0, 500, 500);

        pieChart.setDrawingCacheEnabled(true);
        return pieChart.getDrawingCache();
    }
    private SpannableString generateCenterSpannableText(int totalCalories) {
        String centerText = "Total Calories\n" + totalCalories;
        SpannableString s = new SpannableString(centerText);

        // Set the size of "Total Calories" to be larger
        s.setSpan(new RelativeSizeSpan(0.6f), 0, 14, 0);
        s.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 14, 0);
        // Set the size of the totalCalories number to be larger, bold, and in blue color
        s.setSpan(new RelativeSizeSpan(0.7f), 15, centerText.length(), 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 15, centerText.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.MAGENTA), 15, centerText.length(), 0);

        return s;
    }
    private void drawText(Canvas canvas, Paint paint, String text, float x, float y) {
        canvas.drawText(text, x, y, paint);
    }
    private String getValueSafely(EditText editText) {
        return editText != null ? editText.getText().toString() : "";
    }
    private void drawTableCell(Canvas canvas, float x, float y, float width, float height, Paint paint, boolean isHeader, String text) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2); // Adjust border thickness as needed

        if (isHeader) {
            paint.setColor(Color.GRAY); // Header cell color
        } else {
            paint.setColor(Color.BLACK); // Data cell color
        }

        // Draw the cell background
        canvas.drawRect(x, y, x + width, y + height, paint);

        paint.setColor(Color.BLACK); // Text color
        paint.setStyle(Paint.Style.FILL);

        // Draw the text inside the cell
        float textX = x + width / 2 - paint.measureText(text) / 2;
        float textY = y + height / 2 - (paint.ascent() + paint.descent()) / 2;
        canvas.drawText(text, textX, textY, paint);
    }


    private void calculateAndDisplayGramsOfprotein() {
        if (proteinFoodSpinner.getSelectedItem() != null) {
            String selectedProteinFood = proteinFoodSpinner.getSelectedItem().toString();

            // Check if "Select_Protein_Food" is selected
            if ("Select_Protein_Food".equals(selectedProteinFood)) {
                textviewGramsOfProtein.setText("Grams of Protein: 0");
            } else {
                double totalCalories1 = Double.parseDouble(meal1PTextViewResult.getText().toString());

                // Calculate grams of protein using the formula
                double gramsOfProtein = calculateGramsFromCalories1(totalCalories1, selectedProteinFood);

                // Update the TextView with the calculated grams of protein
                textviewGramsOfProtein.setText("Grams of Protein: " + gramsOfProtein);

                meal1proteinGramsEditText.setText(String.valueOf(gramsOfProtein));
            }
        }
    }

    private void calculateAndDisplayGramsOfCarbs() {
        if (carbsFoodSpinner.getSelectedItem() != null) {
            String selectedCarbsFood = carbsFoodSpinner.getSelectedItem().toString();

            // Check if "Select_Carbs_Food" is selected
            if ("Select_Carbs_Food".equals(selectedCarbsFood)) {
                textviewGramsOfCarbs.setText("Grams of Carbs: 0");
            } else {
                double totalCalories2 = Double.parseDouble(meal1CTextViewResult.getText().toString());

                // Calculate grams of carbs using the formula
                double gramsOfCarbs = calculateGramsFromCalories2(totalCalories2, selectedCarbsFood);

                // Update the TextView with the calculated grams of carbs
                textviewGramsOfCarbs.setText("Grams of Carbs: " + gramsOfCarbs);

                meal1CarbsGramEditText.setText(String.valueOf(gramsOfCarbs));

            }
        }
    }

    private void calculateAndDisplayGramsOfFats() {
        if (FatsFoodSpinner.getSelectedItem() != null) {
            String selectedFatsFood = FatsFoodSpinner.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fat_Food".equals(selectedFatsFood)) {
                textviewGramsOfFats.setText("Grams of Fats: 0");
            } else {
                double totalCalories3 = Double.parseDouble(meal1FTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFats = calculateGramsFromCalories3(totalCalories3, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFats.setText("Grams of Fats: " + gramsOfFats);

                meal1FatsGramEditText.setText(String.valueOf(gramsOfFats));
            }
        }
    }

    private void calculateAndDisplayGramsOfFruits() {
        if (FruitsSpinner.getSelectedItem() != null) {
            String selectedFatsFood = FruitsSpinner.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fruits_and_Vegetables".equals(selectedFatsFood)) {
                textviewGramsOfFruits.setText("Grams of Fruits : 0");
            } else {
                double totalCalories4 = Double.parseDouble(meal1VTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFruits = calculateGramsFromCalories4(totalCalories4, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFruits.setText("Grams of Fruits : " + gramsOfFruits);

                meal1FruitsGramEditText.setText(String.valueOf(gramsOfFruits));
            }
        }
    }
    private void calculateAndDisplayGramsOfVegetables() {
        if (vegetableSpinner.getSelectedItem() != null) {
            String selectedFatsFood = vegetableSpinner.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Vegetables".equals(selectedFatsFood)) {
                textViewGramsOfVegetables.setText("Grams of Vegetables: 0");
            } else {
                double totalCalories25 = Double.parseDouble(meal1VVTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfVegetables = calculateGramsFromCalories5(totalCalories25, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textViewGramsOfVegetables.setText("Grams of Vegetables: " + gramsOfVegetables);

                meal1VegetableGramEditText.setText(String.valueOf(gramsOfVegetables));
            }
        }
    }
    private void calculateAndDisplayGramsOfprotein1() {
        if (proteinFoodSpinner1.getSelectedItem() != null) {
            String selectedProteinFood = proteinFoodSpinner1.getSelectedItem().toString();

            // Check if "Select_Protein_Food" is selected
            if ("Select_Protein_Food".equals(selectedProteinFood)) {
                textviewGramsOfProtein1.setText("Grams of Protein: 0");
            } else {
                double totalCalories5 = Double.parseDouble(meal2PTextViewResult.getText().toString());

                // Calculate grams of protein using the formula
                double gramsOfProtein = calculateGramsFromCalories1(totalCalories5, selectedProteinFood);

                // Update the TextView with the calculated grams of protein
                textviewGramsOfProtein1.setText("Grams of Protein: " + gramsOfProtein);

                meal2ProteinGramsEditText.setText(String.valueOf(gramsOfProtein));
            }
        }
    }

    private void calculateAndDisplayGramsOfCarbs1() {
        if (carbsFoodSpinner1.getSelectedItem() != null) {
            String selectedCarbsFood = carbsFoodSpinner1.getSelectedItem().toString();

            // Check if "Select_Carbs_Food" is selected
            if ("Select_Carbs_Food".equals(selectedCarbsFood)) {
                textviewGramsOfCarbs1.setText("Grams of Carbs: 0");
            } else {
                double totalCalories6 = Double.parseDouble(meal2CTextViewResult.getText().toString());

                // Calculate grams of carbs using the formula
                double gramsOfCarbs = calculateGramsFromCalories2(totalCalories6, selectedCarbsFood);

                // Update the TextView with the calculated grams of carbs
                textviewGramsOfCarbs1.setText("Grams of Carbs: " + gramsOfCarbs);

                meal2CarbsGramEditText.setText(String.valueOf(gramsOfCarbs));
            }
        }
    }

    private void calculateAndDisplayGramsOfFats1() {
        if (FatsFoodSpinner1.getSelectedItem() != null) {
            String selectedFatsFood = FatsFoodSpinner1.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fat_Food".equals(selectedFatsFood)) {
                textviewGramsOfFats1.setText("Grams of Fats: 0");
            } else {
                double totalCalories7 = Double.parseDouble(meal2FTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFats = calculateGramsFromCalories3(totalCalories7, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFats1.setText("Grams of Fats: " + gramsOfFats);

                meal2FatsGramEditText.setText(String.valueOf(gramsOfFats));
            }
        }
    }

    private void calculateAndDisplayGramsOfFruits1() {
        if (FruitsSpinner1.getSelectedItem() != null) {
            String selectedFatsFood = FruitsSpinner1.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fruits_and_Vegetables".equals(selectedFatsFood)) {
                textviewGramsOfFruits1.setText("Grams of Fruits : 0");
            } else {
                double totalCalories8 = Double.parseDouble(meal2VTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFruits = calculateGramsFromCalories4(totalCalories8, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFruits1.setText("Grams of Fruits : " + gramsOfFruits);

                meal2FruitsGramEditText.setText(String.valueOf(gramsOfFruits));
            }
        }
    }

    private void calculateAndDisplayGramsOfVegetables1() {
        if (vegetableSpinner1.getSelectedItem() != null) {
            String selectedFatsFood = vegetableSpinner1.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Vegetables".equals(selectedFatsFood)) {
                textViewGramsOfVegetables1.setText("Grams of Vegetables: 0");
            } else {
                double totalCalories26 = Double.parseDouble(meal2VVTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfVegetables = calculateGramsFromCalories5(totalCalories26, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textViewGramsOfVegetables1.setText("Grams of Vegetables: " + gramsOfVegetables);

                meal2VegetableGramEditText.setText(String.valueOf(gramsOfVegetables));
            }
        }
    }

    private void calculateAndDisplayGramsOfprotein2() {
        if (proteinFoodSpinner2.getSelectedItem() != null) {
            String selectedProteinFood = proteinFoodSpinner2.getSelectedItem().toString();

            // Check if "Select_Protein_Food" is selected
            if ("Select_Protein_Food".equals(selectedProteinFood)) {
                textviewGramsOfProtein2.setText("Grams of Protein: 0");
            } else {
                double totalCalories9 = Double.parseDouble(meal3PTextViewResult.getText().toString());

                // Calculate grams of protein using the formula
                double gramsOfProtein = calculateGramsFromCalories1(totalCalories9, selectedProteinFood);

                // Update the TextView with the calculated grams of protein
                textviewGramsOfProtein2.setText("Grams of Protein: " + gramsOfProtein);

                meal3ProteinGramsEditText.setText(String.valueOf(gramsOfProtein));
            }
        }
    }

    private void calculateAndDisplayGramsOfprotein3() {
        if (proteinFoodSpinner3.getSelectedItem() != null) {
            String selectedProteinFood = proteinFoodSpinner3.getSelectedItem().toString();

            // Check if "Select_Protein_Food" is selected
            if ("Select_Protein_Food".equals(selectedProteinFood)) {
                textviewGramsOfProtein3.setText("Grams of Protein: 0");
            } else {
                double totalCalories10 = Double.parseDouble(meal4PTextViewResult.getText().toString());

                // Calculate grams of protein using the formula
                double gramsOfProtein = calculateGramsFromCalories1(totalCalories10, selectedProteinFood);

                // Update the TextView with the calculated grams of protein
                textviewGramsOfProtein3.setText("Grams of Protein: " + gramsOfProtein);

                meal4ProteinGramsEditText.setText(String.valueOf(gramsOfProtein));
            }
        }
    }

    private void calculateAndDisplayGramsOfprotein4() {
        if (proteinFoodSpinner4.getSelectedItem() != null) {
            String selectedProteinFood = proteinFoodSpinner4.getSelectedItem().toString();

            // Check if "Select_Protein_Food" is selected
            if ("Select_Protein_Food".equals(selectedProteinFood)) {
                textviewGramsOfProtein4.setText("Grams of Protein: 0");
            } else {
                double totalCalories11 = Double.parseDouble(meal5PTextViewResult.getText().toString());

                // Calculate grams of protein using the formula
                double gramsOfProtein = calculateGramsFromCalories1(totalCalories11, selectedProteinFood);

                // Update the TextView with the calculated grams of protein
                textviewGramsOfProtein4.setText("Grams of Protein: " + gramsOfProtein);

                meal5ProteinGramsEditText.setText(String.valueOf(gramsOfProtein));
            }
        }
    }

    private void calculateAndDisplayGramsOfprotein5() {
        if (proteinFoodSpinner5.getSelectedItem() != null) {
            String selectedProteinFood = proteinFoodSpinner5.getSelectedItem().toString();

            // Check if "Select_Protein_Food" is selected
            if ("Select_Protein_Food".equals(selectedProteinFood)) {
                textviewGramsOfProtein5.setText("Grams of Protein: 0");
            } else {
                double totalCalories12 = Double.parseDouble(meal6PTextViewResult.getText().toString());

                // Calculate grams of protein using the formula
                double gramsOfProtein = calculateGramsFromCalories1(totalCalories12, selectedProteinFood);

                // Update the TextView with the calculated grams of protein
                textviewGramsOfProtein5.setText("Grams of Protein: " + gramsOfProtein);

                meal6ProteinGramsEditText.setText(String.valueOf(gramsOfProtein));
            }
        }
    }
    private void calculateAndDisplayGramsOfCarbs2() {
        if (carbsFoodSpinner2.getSelectedItem() != null) {
            String selectedCarbsFood = carbsFoodSpinner2.getSelectedItem().toString();

            // Check if "Select_Carbs_Food" is selected
            if ("Select_Carbs_Food".equals(selectedCarbsFood)) {
                textviewGramsOfCarbs2.setText("Grams of Carbs: 0");
            } else {
                double totalCalories13 = Double.parseDouble(meal3CTextViewResult.getText().toString());

                // Calculate grams of carbs using the formula
                double gramsOfCarbs = calculateGramsFromCalories2(totalCalories13, selectedCarbsFood);

                // Update the TextView with the calculated grams of carbs
                textviewGramsOfCarbs2.setText("Grams of Carbs: " + gramsOfCarbs);

                meal3CarbsGramEditText.setText(String.valueOf(gramsOfCarbs));
            }
        }
    }

    private void calculateAndDisplayGramsOfCarbs3() {
        if (carbsFoodSpinner3.getSelectedItem() != null) {
            String selectedCarbsFood = carbsFoodSpinner3.getSelectedItem().toString();

            // Check if "Select_Carbs_Food" is selected
            if ("Select_Carbs_Food".equals(selectedCarbsFood)) {
                textviewGramsOfCarbs3.setText("Grams of Carbs: 0");
            } else {
                double totalCalories14 = Double.parseDouble(meal4CTextViewResult.getText().toString());

                // Calculate grams of carbs using the formula
                double gramsOfCarbs = calculateGramsFromCalories2(totalCalories14, selectedCarbsFood);

                // Update the TextView with the calculated grams of carbs
                textviewGramsOfCarbs3.setText("Grams of Carbs: " + gramsOfCarbs);

                meal4CarbsGramEditText.setText(String.valueOf(gramsOfCarbs));
            }
        }
    }

    private void calculateAndDisplayGramsOfCarbs4() {
        if (carbsFoodSpinner4.getSelectedItem() != null) {
            String selectedCarbsFood = carbsFoodSpinner4.getSelectedItem().toString();

            // Check if "Select_Carbs_Food" is selected
            if ("Select_Carbs_Food".equals(selectedCarbsFood)) {
                textviewGramsOfCarbs4.setText("Grams of Carbs: 0");
            } else {
                double totalCalories15 = Double.parseDouble(meal5CTextViewResult.getText().toString());

                // Calculate grams of carbs using the formula
                double gramsOfCarbs = calculateGramsFromCalories2(totalCalories15, selectedCarbsFood);

                // Update the TextView with the calculated grams of carbs
                textviewGramsOfCarbs4.setText("Grams of Carbs: " + gramsOfCarbs);

                meal5CarbsGramEditText.setText(String.valueOf(gramsOfCarbs));
            }
        }
    }

    private void calculateAndDisplayGramsOfCarbs5() {
        if (carbsFoodSpinner5.getSelectedItem() != null) {
            String selectedCarbsFood = carbsFoodSpinner5.getSelectedItem().toString();

            // Check if "Select_Carbs_Food" is selected
            if ("Select_Carbs_Food".equals(selectedCarbsFood)) {
                textviewGramsOfCarbs5.setText("Grams of Carbs: 0");
            } else {
                double totalCalories16 = Double.parseDouble(meal6CTextViewResult.getText().toString());

                // Calculate grams of carbs using the formula
                double gramsOfCarbs = calculateGramsFromCalories2(totalCalories16, selectedCarbsFood);

                // Update the TextView with the calculated grams of carbs
                textviewGramsOfCarbs5.setText("Grams of Carbs: " + gramsOfCarbs);

                meal6CarbsGramEditText.setText(String.valueOf(gramsOfCarbs));
            }
        }
    }

    private void calculateAndDisplayGramsOfFats2() {
        if (FatsFoodSpinner2.getSelectedItem() != null) {
            String selectedFatsFood = FatsFoodSpinner2.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fat_Food".equals(selectedFatsFood)) {
                textviewGramsOfFats2.setText("Grams of Fats: 0");
            } else {
                double totalCalories17 = Double.parseDouble(meal3FTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFats = calculateGramsFromCalories3(totalCalories17, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFats2.setText("Grams of Fats: " + gramsOfFats);

                meal3FatsGramEditText.setText(String.valueOf(gramsOfFats));
            }
        }
    }

    private void calculateAndDisplayGramsOfFats3() {
        if (FatsFoodSpinner3.getSelectedItem() != null) {
            String selectedFatsFood = FatsFoodSpinner3.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fat_Food".equals(selectedFatsFood)) {
                textviewGramsOfFats3.setText("Grams of Fats: 0");
            } else {
                double totalCalories18 = Double.parseDouble(meal4FTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFats = calculateGramsFromCalories3(totalCalories18, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFats3.setText("Grams of Fats: " + gramsOfFats);

                meal4FatsGramEditText.setText(String.valueOf(gramsOfFats));
            }
        }
    }

    private void calculateAndDisplayGramsOfFats4() {
        if (FatsFoodSpinner4.getSelectedItem() != null) {
            String selectedFatsFood = FatsFoodSpinner4.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fat_Food".equals(selectedFatsFood)) {
                textviewGramsOfFats4.setText("Grams of Fats: 0");
            } else {
                double totalCalories19 = Double.parseDouble(meal5FTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFats = calculateGramsFromCalories3(totalCalories19, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFats4.setText("Grams of Fats: " + gramsOfFats);

                meal5FatsGramEditText.setText(String.valueOf(gramsOfFats));
            }
        }
    }

    private void calculateAndDisplayGramsOfFats5() {
        if (FatsFoodSpinner5.getSelectedItem() != null) {
            String selectedFatsFood = FatsFoodSpinner5.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fat_Food".equals(selectedFatsFood)) {
                textviewGramsOfFats5.setText("Grams of Fats: 0");
            } else {
                double totalCalories20 = Double.parseDouble(meal6FTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFats = calculateGramsFromCalories3(totalCalories20, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFats5.setText("Grams of Fats: " + gramsOfFats);

                meal6FatsGramEditText.setText(String.valueOf(gramsOfFats));
            }
        }
    }

    private void calculateAndDisplayGramsOfFruits2() {
        if (FruitsSpinner2.getSelectedItem() != null) {
            String selectedFatsFood = FruitsSpinner2.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fruits_and_Vegetables".equals(selectedFatsFood)) {
                textviewGramsOfFruits2.setText("Grams of Fruits : 0");
            } else {
                double totalCalories21 = Double.parseDouble(meal3VTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFruits = calculateGramsFromCalories4(totalCalories21, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFruits2.setText("Grams of Fruits : " + gramsOfFruits);

                meal3FruitsGramEditText.setText(String.valueOf(gramsOfFruits));
            }
        }
    }

    private void calculateAndDisplayGramsOfFruits3() {
        if (FruitsSpinner3.getSelectedItem() != null) {
            String selectedFatsFood = FruitsSpinner3.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fruits_and_Vegetables".equals(selectedFatsFood)) {
                textviewGramsOfFruits3.setText("Grams of Fruits : 0");
            } else {
                double totalCalories22 = Double.parseDouble(meal4VTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFruits = calculateGramsFromCalories4(totalCalories22, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFruits3.setText("Grams of Fruits : " + gramsOfFruits);

                meal4FruitsGramEditText.setText(String.valueOf(gramsOfFruits));
            }
        }
    }

    private void calculateAndDisplayGramsOfFruits4() {
        if (FruitsSpinner4.getSelectedItem() != null) {
            String selectedFatsFood = FruitsSpinner4.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fruits_and_Vegetables".equals(selectedFatsFood)) {
                textviewGramsOfFruits4.setText("Grams of Fruits : 0");
            } else {
                double totalCalories23 = Double.parseDouble(meal5VTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFruits = calculateGramsFromCalories4(totalCalories23, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFruits4.setText("Grams of Fruits : " + gramsOfFruits);

                meal5FruitsGramEditText.setText(String.valueOf(gramsOfFruits));
            }
        }
    }

    private void calculateAndDisplayGramsOfFruits5() {
        if (FruitsSpinner5.getSelectedItem() != null) {
            String selectedFatsFood = FruitsSpinner5.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Fruits_and_Vegetables".equals(selectedFatsFood)) {
                textviewGramsOfFruits5.setText("Grams of Fruits : 0");
            } else {
                double totalCalories24 = Double.parseDouble(meal6VTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfFruits = calculateGramsFromCalories4(totalCalories24, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textviewGramsOfFruits5.setText("Grams of Fruits: " + gramsOfFruits);

                meal6FruitsGramEditText.setText(String.valueOf(gramsOfFruits));
            }
        }
    }

    private void calculateAndDisplayGramsOfVegetables2() {
        if (vegetableSpinner2.getSelectedItem() != null) {
            String selectedFatsFood = vegetableSpinner2.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Vegetables".equals(selectedFatsFood)) {
                textViewGramsOfVegetables2.setText("Grams of Vegetables: 0");
            } else {
                double totalCalories27 = Double.parseDouble(meal3VVTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfVegetables = calculateGramsFromCalories5(totalCalories27, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textViewGramsOfVegetables2.setText("Grams of Vegetables: " + gramsOfVegetables);

                meal3VegetableGramEditText.setText(String.valueOf(gramsOfVegetables));

            }
        }
    }

    private void calculateAndDisplayGramsOfVegetables3() {
        if (vegetableSpinner3.getSelectedItem() != null) {
            String selectedFatsFood = vegetableSpinner3.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Vegetables".equals(selectedFatsFood)) {
                textViewGramsOfVegetables3.setText("Grams of Vegetables: 0");
            } else {
                double totalCalories28 = Double.parseDouble(meal4VVTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfVegetables = calculateGramsFromCalories5(totalCalories28, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textViewGramsOfVegetables3.setText("Grams of Vegetables: " + gramsOfVegetables);

                meal4VegetableGramEditText.setText(String.valueOf(gramsOfVegetables));
            }
        }
    }

    private void calculateAndDisplayGramsOfVegetables4() {
        if (vegetableSpinner4.getSelectedItem() != null) {
            String selectedFatsFood = vegetableSpinner4.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Vegetables".equals(selectedFatsFood)) {
                textViewGramsOfVegetables4.setText("Grams of Vegetables: 0");
            } else {
                double totalCalories29 = Double.parseDouble(meal5VVTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfVegetables = calculateGramsFromCalories5(totalCalories29, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textViewGramsOfVegetables4.setText("Grams of Vegetables: " + gramsOfVegetables);

                meal5VegetableGramEditText.setText(String.valueOf(gramsOfVegetables));
            }
        }
    }

    private void calculateAndDisplayGramsOfVegetables5() {
        if (vegetableSpinner5.getSelectedItem() != null) {
            String selectedFatsFood = vegetableSpinner5.getSelectedItem().toString();

            // Check if "Select_Fats_Food" is selected
            if ("Select_Vegetables".equals(selectedFatsFood)) {
                textViewGramsOfVegetables5.setText("Grams of Vegetables: 0");
            } else {
                double totalCalories30 = Double.parseDouble(meal6VVTextViewResult.getText().toString());

                // Calculate grams of fats using the formula
                double gramsOfVegetables = calculateGramsFromCalories5(totalCalories30, selectedFatsFood);

                // Update the TextView with the calculated grams of fats
                textViewGramsOfVegetables5.setText("Grams of Vegetables: " + gramsOfVegetables);

                meal6VegetableGramEditText.setText(String.valueOf(gramsOfVegetables));
            }
        }
    }

    private double getCaloriesPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their caloric values
        Map<String, Double> proteinCaloriesMap = new HashMap<>();
        proteinCaloriesMap.put("Select_Protein_Food", 1.00);
        proteinCaloriesMap.put("Chicken Breast", 1.73);
        proteinCaloriesMap.put("Egg Whole Raw",1.55);
        proteinCaloriesMap.put("whey Protein Raw",3.73);
        proteinCaloriesMap.put("Egg Whites Raw",0.52);
        proteinCaloriesMap.put("Fish Salmon Raw",2.08);
        proteinCaloriesMap.put("Fish tuna Raw",1.44);
        proteinCaloriesMap.put("Mutton Boneless Raw",2.95);
        proteinCaloriesMap.put("Firm Tofu Raw",1.44);
        proteinCaloriesMap.put("Paneer Raw",2.65);
        proteinCaloriesMap.put("Lentil Sprouts Raw",1.06);
        proteinCaloriesMap.put("Dal Raw",1.16);
        proteinCaloriesMap.put("Tempeh Raw",1.92);
        proteinCaloriesMap.put("Shrimp Raw",0.85);

        // Return the caloric value for the selected protein food item
        return proteinCaloriesMap.get(foodItem);
    }

    private int calculateGramsFromCalories1(double calories, String proteinFoodItem) {
        // Get caloric content based on the selected protein food item
        double caloriesPerGram = getCaloriesPerGramForProteinFoodItem(proteinFoodItem);

        // Calculate grams of protein
        double gramsOfProtein = calories / caloriesPerGram;
        int roundedGramsOfProtein = (int) Math.round(gramsOfProtein);
        return roundedGramsOfProtein;
    }

    private double getCaloriesPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between food items and their caloric values
        Map<String, Double> caloriesMap = new HashMap<>();
        caloriesMap.put("Select_Carbs_Food", 1.01);
        caloriesMap.put("Basmati Rice Cooked", 1.21);
        caloriesMap.put("Brown Rice Cooked", 1.12);
        caloriesMap.put("Oats Raw", 3.89);
        caloriesMap.put("Potato Raw", 0.77);
        caloriesMap.put("Sweet Potato Raw", 0.86);
        caloriesMap.put("Quinoa Raw", 3.68);
        caloriesMap.put("Millets Raw", 3.78);
        caloriesMap.put("Wheat Pasta Raw", 3.71);
        caloriesMap.put("Carb Powder", 4.00);
        caloriesMap.put("Brown Bread", 2.47);
        caloriesMap.put("Cream of rice Raw", 3.36);// Calories per gram for pasta (example)

        // Return the caloric value for the selected food item
        return caloriesMap.get(foodItem);
    }
    private int calculateGramsFromCalories2(double calories, String CarbsfoodItem) {
        // Get caloric content based on the selected food item
        double caloriesPerGram = getCaloriesPerGramForCarbsFoodItem(CarbsfoodItem);

        // Calculate grams of food based on the formula
        double gramsOfCarbs = calories / caloriesPerGram;
        int roundedGramsOfCarbs = (int) Math.round(gramsOfCarbs);

        return roundedGramsOfCarbs;
    }
    private double getCaloriesPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between fats food items and their caloric values
        Map<String, Double> fatsCaloriesMap = new HashMap<>();
        fatsCaloriesMap.put("Select_Fat_Food", 1.02);
        fatsCaloriesMap.put("Olive Oil", 9.0);  // Calories per gram for olive oil (example)
        fatsCaloriesMap.put("Avocado", 7.0);// Calories per gram for butter (example)
        fatsCaloriesMap.put("Coconut Oil",8.62);
        fatsCaloriesMap.put("Almonds",5.72);
        fatsCaloriesMap.put("Pista",5.62);
        fatsCaloriesMap.put("Walnuts",6.54);
        fatsCaloriesMap.put("Peanuts",5.67);
        fatsCaloriesMap.put("Cashews",4.67);
        fatsCaloriesMap.put("Peanut Butter",5.80);
        fatsCaloriesMap.put("Butter",7.17);
        fatsCaloriesMap.put("Cheese",4.03);
        fatsCaloriesMap.put("Ghee",9.0);
        // Return the caloric value for the selected fats food item
        return fatsCaloriesMap.get(foodItem);
    }
    private int calculateGramsFromCalories3(double calories, String FatsfoodItem) {
        // Get caloric content based on the selected food item
        double caloriesPerGram = getCaloriesPerGramForFatsFoodItem(FatsfoodItem);

        // Calculate grams of food based on the formula
        double gramsOfFats = calories / caloriesPerGram;
        int roundedGramsOfFats = (int) Math.round(gramsOfFats);


        return roundedGramsOfFats;
    }
    private double getCaloriesPerGramForFruitsItem(String fruitItem) {
        // Define a mapping between fruits items and their caloric values
        Map<String, Double> fruitsCaloriesMap = new HashMap<>();
        fruitsCaloriesMap.put("Select_Fruits_and_Vegetables", 1.03);
        fruitsCaloriesMap.put("Apple",0.52);
        fruitsCaloriesMap.put("Banana",0.89);
        fruitsCaloriesMap.put("Orange",0.43);
        fruitsCaloriesMap.put("Papaya",0.43);
        fruitsCaloriesMap.put("Water Melon",0.30);
        fruitsCaloriesMap.put("Kiwi",0.61);
        fruitsCaloriesMap.put("Dragon Fruits",0.60);
        fruitsCaloriesMap.put("Pomogrenate",0.83);
        fruitsCaloriesMap.put("Straw Berry",0.32);
        fruitsCaloriesMap.put("Cran Berries",0.46);
        fruitsCaloriesMap.put("Blue Berries",0.57);
        fruitsCaloriesMap.put("Pine Apple",0.50);

        // Return the caloric value for the selected fruits item
        return fruitsCaloriesMap.get(fruitItem);
    }
    private int calculateGramsFromCalories4(double calories, String foodItem) {
        // Get caloric content based on the selected food item
        double caloriesPerGram = getCaloriesPerGramForFruitsItem(foodItem);

        // Calculate grams of food based on the formula
        double gramsOfFood = calories / caloriesPerGram;
        int roundedGramsOfFruits = (int) Math.round(gramsOfFood);

        return roundedGramsOfFruits;
    }

    private double getCaloriesPerGramForVegetablesItem(String foodItem) {
        // Define a mapping between fruits items and their caloric values
        Map<String, Double> vegetableCaloriesMap = new HashMap<>();
        vegetableCaloriesMap.put("Select_Vegetables", 1.03);
        vegetableCaloriesMap.put("Amaranthus",0.23);   // Calories per gram for apple (example)
        vegetableCaloriesMap.put("Spinach", 0.23);  // Calories per gram for banana (example)
        vegetableCaloriesMap.put("French Beans",0.31);
        vegetableCaloriesMap.put("Broccoli",0.31);
        vegetableCaloriesMap.put("Cauli Flower",0.25);
        vegetableCaloriesMap.put("IceBurg Lettuce",0.14);
        vegetableCaloriesMap.put("Zucchini",0.17);
        vegetableCaloriesMap.put("Capcicum",0.31);
        vegetableCaloriesMap.put("Carrot",0.41);
        vegetableCaloriesMap.put("Cucumber",0.16);
        vegetableCaloriesMap.put("Tomatoes",0.18);
        vegetableCaloriesMap.put("Bell Peppers",0.31);
        vegetableCaloriesMap.put("Green Peas",0.81);
        vegetableCaloriesMap.put("Onion",0.40);
        vegetableCaloriesMap.put("Coriander Leaves",0.23);


        // Return the caloric value for the selected fruits item
        return vegetableCaloriesMap.get(foodItem);
    }

    private int calculateGramsFromCalories5(double calories, String VegetablefoodItem) {
        // Get caloric content based on the selected food item
        double caloriesPerGram = getCaloriesPerGramForVegetablesItem(VegetablefoodItem);

        // Calculate grams of food based on the formula
        double gramsOfVegetable = calories / caloriesPerGram;
        int roundedGramsOfVegetable = (int) Math.round(gramsOfVegetable);

        return roundedGramsOfVegetable;
    }
    private double getProteinPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinValuesMap = new HashMap<>();
        proteinValuesMap.put("Select_Protein_Food", 1.01);
        proteinValuesMap.put("Chicken Breast", 0.31);
        proteinValuesMap.put("Egg Whole Raw",0.13);
        proteinValuesMap.put("whey Protein Raw",0.77);
        proteinValuesMap.put("Egg Whites Raw",0.11);
        proteinValuesMap.put("Fish Salmon Raw",0.20);
        proteinValuesMap.put("Fish tuna Raw",0.23);
        proteinValuesMap.put("Mutton Boneless Raw",0.247);
        proteinValuesMap.put("Firm Tofu Raw",0.0872);
        proteinValuesMap.put("Paneer Raw",0.18);
        proteinValuesMap.put("Lentil Sprouts Raw",0.09);
        proteinValuesMap.put("Dal Raw",0.09);
        proteinValuesMap.put("Tempeh Raw",0.21);
        proteinValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return proteinValuesMap.get(foodItem);
    }
    private double calculateProteinsFromProteinFoodItem(Spinner meal1ProteinSpinner, EditText meal1proteinGramsEditText) {
        String selectedProtein = meal1ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getProteinPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal1proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalProtein1= proteinFoodGrams * proteinPerGram;
                return totalProtein1;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromProteinFoodItem1(Spinner meal2ProteinSpinner, EditText meal2proteinGramsEditText) {
        String selectedProtein = meal2ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getProteinPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal2proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalProtein2= proteinFoodGrams * proteinPerGram;
                return totalProtein2;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromProteinFoodItem2(Spinner meal3ProteinSpinner, EditText meal3proteinGramsEditText) {
        String selectedProtein = meal3ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getProteinPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal3proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalProtein3= proteinFoodGrams * proteinPerGram;
                return totalProtein3;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromProteinFoodItem3(Spinner meal4ProteinSpinner, EditText meal4proteinGramsEditText) {
        String selectedProtein = meal4ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getProteinPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal4proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalProtein4= proteinFoodGrams * proteinPerGram;
                return totalProtein4;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromProteinFoodItem4(Spinner meal5ProteinSpinner, EditText meal5proteinGramsEditText) {
        String selectedProtein = meal5ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getProteinPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal5proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalProtein5= proteinFoodGrams * proteinPerGram;
                return totalProtein5;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromProteinFoodItem5(Spinner meal6ProteinSpinner, EditText meal6proteinGramsEditText) {
        String selectedProtein = meal6ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getProteinPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal6proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalProtein6= proteinFoodGrams * proteinPerGram;
                return totalProtein6;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double getCarbsPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> carbsValuesMap = new HashMap<>();
        carbsValuesMap.put("Select_Protein_Food", 1.03);
        carbsValuesMap.put("Chicken Breast", 0.00);
        carbsValuesMap.put("Egg Whole Raw",0.01);
        carbsValuesMap.put("whey Protein Raw",0.06);
        carbsValuesMap.put("Egg Whites Raw",0.01);
        carbsValuesMap.put("Fish Salmon Raw",0.00);
        carbsValuesMap.put("Fish tuna Raw",0.00);
        carbsValuesMap.put("Mutton Boneless Raw",0.00);
        carbsValuesMap.put("Firm Tofu Raw",0.0428);
        carbsValuesMap.put("Paneer Raw",0.012);
        carbsValuesMap.put("Lentil Sprouts Raw",0.20);
        carbsValuesMap.put("Dal Raw",0.20);
        carbsValuesMap.put("Tempeh Raw",0.09);
        carbsValuesMap.put("Shrimp Raw",0.00);

        // Return the protein value per gram for the selected protein food item
        return carbsValuesMap.get(foodItem);
    }

    private double calculateCarbsFromProteinFoodItem(Spinner meal1ProteinSpinner, EditText meal1proteinGramsEditText) {
        String selectedProtein = meal1ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getCarbsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal1proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalCarbs7= proteinFoodGrams * proteinPerGram;
                return totalCarbs7;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromProteinFoodItem1(Spinner meal2ProteinSpinner, EditText meal2proteinGramsEditText) {
        String selectedProtein = meal2ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getCarbsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal2proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalCarbs8= proteinFoodGrams * proteinPerGram;
                return totalCarbs8;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromProteinFoodItem2(Spinner meal3ProteinSpinner, EditText meal3proteinGramsEditText) {
        String selectedProtein = meal3ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getCarbsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal3proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalCarbs9= proteinFoodGrams * proteinPerGram;
                return totalCarbs9;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromProteinFoodItem3(Spinner meal4ProteinSpinner, EditText meal4proteinGramsEditText) {
        String selectedProtein = meal4ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getCarbsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal4proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalCarbs10= proteinFoodGrams * proteinPerGram;
                return totalCarbs10;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromProteinFoodItem4(Spinner meal5ProteinSpinner, EditText meal5proteinGramsEditText) {
        String selectedProtein = meal5ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getCarbsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal5proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalCarbs11= proteinFoodGrams * proteinPerGram;
                return totalCarbs11;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromProteinFoodItem5(Spinner meal6ProteinSpinner, EditText meal6proteinGramsEditText) {
        String selectedProtein = meal6ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getCarbsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal6proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalCarbs12= proteinFoodGrams * proteinPerGram;
                return totalCarbs12;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double getFatsPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> fatsValuesMap = new HashMap<>();
        fatsValuesMap.put("Select_Protein_Food", 1.04);
        fatsValuesMap.put("Chicken Breast", 0.05);
        fatsValuesMap.put("Egg Whole Raw",0.11);
        fatsValuesMap.put("whey Protein Raw",0.02);
        fatsValuesMap.put("Egg Whites Raw",0.00);
        fatsValuesMap.put("Fish Salmon Raw",0.13);
        fatsValuesMap.put("Fish tuna Raw",0.05);
        fatsValuesMap.put("Mutton Boneless Raw",0.212);
        fatsValuesMap.put("Firm Tofu Raw",0.1578);
        fatsValuesMap.put("Paneer Raw",0.20);
        fatsValuesMap.put("Lentil Sprouts Raw",0.01);
        fatsValuesMap.put("Dal Raw",0.004);
        fatsValuesMap.put("Tempeh Raw",0.10);
        fatsValuesMap.put("Shrimp Raw",0.015);

        // Return the protein value per gram for the selected protein food item
        return fatsValuesMap.get(foodItem);
    }

    private double calculateFatsFromProteinFoodItem(Spinner meal1ProteinSpinner, EditText meal1proteinGramsEditText) {
        String selectedProtein = meal1ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFatsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal1proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFats7= proteinFoodGrams * proteinPerGram;
                return totalFats7;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateFatsFromProteinFoodItem1(Spinner meal2ProteinSpinner, EditText meal2proteinGramsEditText) {
        String selectedProtein = meal2ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFatsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal2proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFats8= proteinFoodGrams * proteinPerGram;
                return totalFats8;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateFatsFromProteinFoodItem2(Spinner meal3ProteinSpinner, EditText meal3proteinGramsEditText) {
        String selectedProtein = meal3ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFatsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal3proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFats9= proteinFoodGrams * proteinPerGram;
                return totalFats9;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateFatsFromProteinFoodItem3(Spinner meal4ProteinSpinner, EditText meal4proteinGramsEditText) {
        String selectedProtein = meal4ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFatsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal4proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFats10= proteinFoodGrams * proteinPerGram;
                return totalFats10;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateFatsFromProteinFoodItem4(Spinner meal5ProteinSpinner, EditText meal5proteinGramsEditText) {
        String selectedProtein = meal5ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFatsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal5proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFats11= proteinFoodGrams * proteinPerGram;
                return totalFats11;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateFatsFromProteinFoodItem5(Spinner meal6ProteinSpinner, EditText meal6proteinGramsEditText) {
        String selectedProtein = meal6ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFatsPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal6proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFats12= proteinFoodGrams * proteinPerGram;
                return totalFats12;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double getFibresPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> fibresValuesMap = new HashMap<>();
        fibresValuesMap.put("Select_Protein_Food", 1.11);
        fibresValuesMap.put("Chicken Breast", 0.00);
        fibresValuesMap.put("Egg Whole Raw",0.00);
        fibresValuesMap.put("whey Protein Raw",0.00);
        fibresValuesMap.put("Egg Whites Raw",0.00);
        fibresValuesMap.put("Fish Salmon Raw",0.00);
        fibresValuesMap.put("Fish tuna Raw",0.00);
        fibresValuesMap.put("Mutton Boneless Raw",0.00);
        fibresValuesMap.put("Firm Tofu Raw",0.00);
        fibresValuesMap.put("Paneer Raw",0.00);
        fibresValuesMap.put("Lentil Sprouts Raw",0.00);
        fibresValuesMap.put("Dal Raw",0.00);
        fibresValuesMap.put("Tempeh Raw",0.00);
        fibresValuesMap.put("Shrimp Raw",0.00);


        return fibresValuesMap.get(foodItem);
    }

    private double calculateFibresFromProteinFoodItem(Spinner meal1ProteinSpinner, EditText meal1proteinGramsEditText) {
        String selectedProtein = meal1ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFibresPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal1proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFibres19= proteinFoodGrams * proteinPerGram;
                return totalFibres19;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateFibresFromProteinFoodItem1(Spinner meal2ProteinSpinner, EditText meal2proteinGramsEditText) {
        String selectedProtein = meal2ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFibresPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal2proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFibres20= proteinFoodGrams * proteinPerGram;
                return totalFibres20;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateFibresFromProteinFoodItem2(Spinner meal3ProteinSpinner, EditText meal3proteinGramsEditText) {
        String selectedProtein = meal3ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFibresPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal3proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFibres21= proteinFoodGrams * proteinPerGram;
                return totalFibres21;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateFibresFromProteinFoodItem3(Spinner meal4ProteinSpinner, EditText meal4proteinGramsEditText) {
        String selectedProtein = meal4ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFibresPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal4proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFibres22= proteinFoodGrams * proteinPerGram;
                return totalFibres22;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateFibresFromProteinFoodItem4(Spinner meal5ProteinSpinner, EditText meal5proteinGramsEditText) {
        String selectedProtein = meal5ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFibresPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal5proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFibres23= proteinFoodGrams * proteinPerGram;
                return totalFibres23;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double calculateFibresFromProteinFoodItem5(Spinner meal6ProteinSpinner, EditText meal6proteinGramsEditText) {
        String selectedProtein = meal6ProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFibresPerGramForProteinFoodItem(selectedProtein);

        String proteinFoodGramsStr = meal6proteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                double totalFibres24= proteinFoodGrams * proteinPerGram;
                return totalFibres24;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double getCalciumPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.31);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.77);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.23);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0872);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.21);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateCalciumFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getCalciumPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double getPhosphorusPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> PhosphorusValuesMap = new HashMap<>();
        PhosphorusValuesMap.put("Select_Protein_Food", 1.01);
        PhosphorusValuesMap.put("Chicken Breast", 0.51);
        PhosphorusValuesMap.put("Egg Whole Raw",0.23);
        PhosphorusValuesMap.put("whey Protein Raw",0.57);
        PhosphorusValuesMap.put("Egg Whites Raw",0.31);
        PhosphorusValuesMap.put("Fish Salmon Raw",0.26);
        PhosphorusValuesMap.put("Fish tuna Raw",0.25);
        PhosphorusValuesMap.put("Mutton Boneless Raw",0.257);
        PhosphorusValuesMap.put("Firm Tofu Raw",0.0842);
        PhosphorusValuesMap.put("Paneer Raw",0.14);
        PhosphorusValuesMap.put("Lentil Sprouts Raw",0.29);
        PhosphorusValuesMap.put("Dal Raw",0.19);
        PhosphorusValuesMap.put("Tempeh Raw",0.23);
        PhosphorusValuesMap.put("Shrimp Raw",0.20);
        // Return the protein value per gram for the selected protein food item
        return PhosphorusValuesMap.get(foodItem);
    }

    private double calculatePhosphorusFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getPhosphorusPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double getMagnesiumPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.31);
        CalciumValuesMap.put("Egg Whole Raw",0.23);
        CalciumValuesMap.put("whey Protein Raw",0.787);
        CalciumValuesMap.put("Egg Whites Raw",0.38);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.56);
        CalciumValuesMap.put("Mutton Boneless Raw",0.287);
        CalciumValuesMap.put("Firm Tofu Raw",0.0872);
        CalciumValuesMap.put("Paneer Raw",0.46);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.59);
        CalciumValuesMap.put("Dal Raw",0.49);
        CalciumValuesMap.put("Tempeh Raw",0.51);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateMagnesiumFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getMagnesiumPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double getElectrolytePerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.48);
        CalciumValuesMap.put("Egg Whole Raw",0.36);
        CalciumValuesMap.put("whey Protein Raw",0.98);
        CalciumValuesMap.put("Egg Whites Raw",0.45);
        CalciumValuesMap.put("Fish Salmon Raw",0.40);
        CalciumValuesMap.put("Fish tuna Raw",0.56);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0872);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.29);
        CalciumValuesMap.put("Dal Raw",0.28);
        CalciumValuesMap.put("Tempeh Raw",0.24);
        CalciumValuesMap.put("Shrimp Raw",0.56);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateElectrolyteFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getElectrolytePerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double getSodiumPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.41);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.77);
        CalciumValuesMap.put("Egg Whites Raw",0.78);
        CalciumValuesMap.put("Fish Salmon Raw",0.50);
        CalciumValuesMap.put("Fish tuna Raw",0.24);
        CalciumValuesMap.put("Mutton Boneless Raw",0.237);
        CalciumValuesMap.put("Firm Tofu Raw",0.0872);
        CalciumValuesMap.put("Paneer Raw",0.183);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.029);
        CalciumValuesMap.put("Dal Raw",0.56);
        CalciumValuesMap.put("Tempeh Raw",0.61);
        CalciumValuesMap.put("Shrimp Raw",0.48);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateSodiumFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getSodiumPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double getPotassiumPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.341);
        CalciumValuesMap.put("Egg Whole Raw",0.18);
        CalciumValuesMap.put("whey Protein Raw",0.67);
        CalciumValuesMap.put("Egg Whites Raw",0.66);
        CalciumValuesMap.put("Fish Salmon Raw",0.40);
        CalciumValuesMap.put("Fish tuna Raw",0.213);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0872);
        CalciumValuesMap.put("Paneer Raw",0.14);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.39);
        CalciumValuesMap.put("Dal Raw",0.29);
        CalciumValuesMap.put("Tempeh Raw",0.27);
        CalciumValuesMap.put("Shrimp Raw",0.28);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculatePotassiumFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getPotassiumPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double getChloridePerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.66);
        CalciumValuesMap.put("Egg Whole Raw",0.71);
        CalciumValuesMap.put("whey Protein Raw",0.717);
        CalciumValuesMap.put("Egg Whites Raw",0.31);
        CalciumValuesMap.put("Fish Salmon Raw",0.50);
        CalciumValuesMap.put("Fish tuna Raw",0.25);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0872);
        CalciumValuesMap.put("Paneer Raw",0.29);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.49);
        CalciumValuesMap.put("Dal Raw",0.69);
        CalciumValuesMap.put("Tempeh Raw",0.21);
        CalciumValuesMap.put("Shrimp Raw",0.12);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateChlorideFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getChloridePerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double getIronPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.121);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.97);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.32);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0852);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.42);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateIronFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getIronPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double getZincPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.67);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.57);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.43);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0143);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.212);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateZincFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getZincPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double getCopperPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.313);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.72);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.232);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0812);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.56);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateCopperFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getCopperPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double getSeleniumPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.90);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.37);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.45);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.087);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.12);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateSeleniumFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getSeleniumPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double getChromiumPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.13);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.75);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.77);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0072);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.36);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateChromiumFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getChromiumPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double getIodinePerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.49);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.70);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.33);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0822);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.99);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateIodineFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getIodinePerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double getManganesePerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.40);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.50);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.60);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0892);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.70);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateManganeseFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getManganesePerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double getMolybdenumPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.21);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.31);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.41);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0822);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.51);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateMolybdenumFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getMolybdenumPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double getFluoridePerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.42);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.52);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.62);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0882);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.82);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateFluorideFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getFluoridePerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getBoronPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.75);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.85);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.95);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0802);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.105);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateBoronFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getBoronPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double getSiliconPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.19);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.29);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.39);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.082);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.49);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateSiliconFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getSiliconPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double getVanadiumPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.27);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.37);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.47);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.087);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.57);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateVanadiumFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVanadiumPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double getCobaltPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.36);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.46);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.56);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.072);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.56);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }

    private double calculateCobaltFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getCobaltPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }
    private double getVitaminAPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.31);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.41);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.51);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.872);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.61);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminAFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminAPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminBPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.22);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.32);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.42);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0875);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.52);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminBFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminBPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminDPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.90);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.100);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.110);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.087);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.120);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminDFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminDPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminEPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.51);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.67);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.71);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.062);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.71);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminEFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminEPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminKPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.81);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.97);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.101);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0812);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.121);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminKFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminKPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB1PerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.67);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.77);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.87);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.097);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.107);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminB1FromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminB1PerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB2PerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.313);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.413);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.523);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0672);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.721);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminB2FromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminB2PerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB3PerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.69);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.79);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.83);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.089);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.99);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminB3FromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminB3PerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminPPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.28);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.37);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.48);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.058);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.68);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminPFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminPPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB7PerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.12);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.22);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.33);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.042);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.52);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminB7FromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminB7PerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB9PerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.45);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.55);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.65);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.085);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.75);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminB9FromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminB9PerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB12PerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.71);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.87);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.93);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.0172);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.121);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminB12FromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminB12PerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB6PerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.18);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.27);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.38);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.872);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.48);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminB6FromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminB6PerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminCPerGramForProteinFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CalciumValuesMap = new HashMap<>();
        CalciumValuesMap.put("Select_Protein_Food", 1.01);
        CalciumValuesMap.put("Chicken Breast", 0.57);
        CalciumValuesMap.put("Egg Whole Raw",0.13);
        CalciumValuesMap.put("whey Protein Raw",0.67);
        CalciumValuesMap.put("Egg Whites Raw",0.11);
        CalciumValuesMap.put("Fish Salmon Raw",0.20);
        CalciumValuesMap.put("Fish tuna Raw",0.95);
        CalciumValuesMap.put("Mutton Boneless Raw",0.247);
        CalciumValuesMap.put("Firm Tofu Raw",0.72);
        CalciumValuesMap.put("Paneer Raw",0.18);
        CalciumValuesMap.put("Lentil Sprouts Raw",0.09);
        CalciumValuesMap.put("Dal Raw",0.09);
        CalciumValuesMap.put("Tempeh Raw",0.89);
        CalciumValuesMap.put("Shrimp Raw",0.18);
        // Return the protein value per gram for the selected protein food item
        return CalciumValuesMap.get(foodItem);
    }
    private double calculateVitaminCFromProteinFoodItem(Spinner mealProteinSpinner, EditText mealProteinGramsEditText) {
        String selectedProtein = mealProteinSpinner.getSelectedItem().toString();
        double proteinPerGram = getVitaminCPerGramForProteinFoodItem(selectedProtein);
        String proteinFoodGramsStr = mealProteinGramsEditText.getText().toString();

        if (!proteinFoodGramsStr.isEmpty()) {
            try {
                double proteinFoodGrams = Double.parseDouble(proteinFoodGramsStr);
                return proteinFoodGrams * proteinPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCarbsPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }

    private double calculateCarbsFromCarbsFoodItem(Spinner meal1CarbsSpinner,EditText meal1CarbsGramEditText) {
        String selectedCarbs = meal1CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getCarbsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal1CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                double totalCarbs1 = CarbsFoodGrams * CarbsPerGram;
                return totalCarbs1;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateCarbsFromCarbsFoodItem1(Spinner meal2CarbsSpinner,EditText meal2CarbsGramEditText) {
        String selectedCarbs = meal2CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getCarbsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal2CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double
                        totalCarbs2 = CarbsFoodGrams * CarbsPerGram;
                return totalCarbs2;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateCarbsFromCarbsFoodItem2(Spinner meal3CarbsSpinner,EditText meal3CarbsGramEditText) {
        String selectedCarbs = meal3CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getCarbsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal3CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalCarbs3 = CarbsFoodGrams * CarbsPerGram;
                return totalCarbs3;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateCarbsFromCarbsFoodItem3(Spinner meal4CarbsSpinner,EditText meal4CarbsGramEditText) {
        String selectedCarbs = meal4CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getCarbsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal4CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalCarbs4 = CarbsFoodGrams * CarbsPerGram;
                return totalCarbs4;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateCarbsFromCarbsFoodItem4(Spinner meal5CarbsSpinner,EditText meal5CarbsGramEditText) {
        String selectedCarbs = meal5CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getCarbsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal5CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalCarbs5 = CarbsFoodGrams * CarbsPerGram;
                return totalCarbs5;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateCarbsFromCarbsFoodItem5(Spinner meal6CarbsSpinner,EditText meal6CarbsGramEditText) {
        String selectedCarbs = meal6CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getCarbsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal6CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalCarbs6 = CarbsFoodGrams * CarbsPerGram;
                return totalCarbs6;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double getProteinsPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinValuesMap = new HashMap<>();
        proteinValuesMap.put("Select_Carbs_Food", 1.05);
        proteinValuesMap.put("Basmati Rice Cooked", 0.027);
        proteinValuesMap.put("Brown Rice Cooked", 0.026);
        proteinValuesMap.put("Oats Raw",0.169);// Protein per gram for beef (grams per 100 grams)
        proteinValuesMap.put("Potato Raw",0.016);
        proteinValuesMap.put("Sweet Potato Raw",0.016);
        proteinValuesMap.put("Quinoa Raw",0.14);
        proteinValuesMap.put("Millets Raw",0.11);
        proteinValuesMap.put("Wheat Pasta Raw",0.12);
        proteinValuesMap.put("Carb Powder",0.00);
        proteinValuesMap.put("Brown Bread",0.11);
        proteinValuesMap.put("Cream of rice Raw",0.045);
        // Return the protein value per gram for the selected protein food item
        return proteinValuesMap.get(foodItem);
    }

    private double calculateproteinsFromCarbsFoodItem(Spinner meal1CarbsSpinner,EditText meal1CarbsGramEditText) {
        String selectedCarbs = meal1CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getProteinsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal1CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalProteins7 = CarbsFoodGrams * CarbsPerGram;
                return totalProteins7;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateproteinsFromCarbsFoodItem1(Spinner meal2CarbsSpinner,EditText meal2CarbsGramEditText) {
        String selectedCarbs = meal2CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getProteinsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal2CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalProteins8 = CarbsFoodGrams * CarbsPerGram;
                return totalProteins8;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateproteinsFromCarbsFoodItem2(Spinner meal3CarbsSpinner,EditText meal3CarbsGramEditText) {
        String selectedCarbs = meal3CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getProteinsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal3CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalProteins9 = CarbsFoodGrams * CarbsPerGram;
                return totalProteins9;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateproteinsFromCarbsFoodItem3(Spinner meal4CarbsSpinner,EditText meal4CarbsGramEditText) {
        String selectedCarbs = meal4CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getProteinsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal4CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalProteins10 = CarbsFoodGrams * CarbsPerGram;
                return totalProteins10;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateproteinsFromCarbsFoodItem4(Spinner meal5CarbsSpinner,EditText meal5CarbsGramEditText) {
        String selectedCarbs = meal5CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getProteinsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal5CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalProteins11 = CarbsFoodGrams * CarbsPerGram;
                return totalProteins11;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateproteinsFromCarbsFoodItem5(Spinner meal6CarbsSpinner,EditText meal6CarbsGramEditText) {
        String selectedCarbs = meal6CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getProteinsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal6CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalProteins12 = CarbsFoodGrams * CarbsPerGram;
                return totalProteins12;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double getFatsPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> fatsValuesMap = new HashMap<>();
        fatsValuesMap.put("Select_Carbs_Food", 1.06);
        fatsValuesMap.put("Basmati Rice Cooked", 0.003);
        fatsValuesMap.put("Brown Rice Cooked", 0.009);
        fatsValuesMap.put("Oats Raw", 0.069);
        fatsValuesMap.put("Potato Raw",0.001);    // Protein per gram for beef (grams per 100 grams)
        fatsValuesMap.put("Sweet Potato Raw", 0.001);
        fatsValuesMap.put("Quinoa Raw", 0.061);
        fatsValuesMap.put("Millets Raw", 0.040);
        fatsValuesMap.put("Wheat Pasta Raw", 0.020);
        fatsValuesMap.put("Carb Powder", 0.00);
        fatsValuesMap.put("Brown Bread", 0.020);
        fatsValuesMap.put("Cream of rice Raw", 0.00);


        // Return the protein value per gram for the selected protein food item
        return fatsValuesMap.get(foodItem);
    }

    private double calculateFatsFromCarbsFoodItem(Spinner meal1CarbsSpinner,EditText meal1CarbsGramEditText) {
        String selectedCarbs = meal1CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFatsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal1CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFats13 = CarbsFoodGrams * CarbsPerGram;
                return totalFats13;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFatsFromCarbsFoodItem1(Spinner meal2CarbsSpinner,EditText meal2CarbsGramEditText) {
        String selectedCarbs = meal2CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFatsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal2CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFats14 = CarbsFoodGrams * CarbsPerGram;
                return totalFats14;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFatsFromCarbsFoodItem2(Spinner meal3CarbsSpinner,EditText meal3CarbsGramEditText) {
        String selectedCarbs = meal3CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFatsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal3CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFats15 = CarbsFoodGrams * CarbsPerGram;
                return totalFats15;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFatsFromCarbsFoodItem3(Spinner meal4CarbsSpinner,EditText meal4CarbsGramEditText) {
        String selectedCarbs = meal4CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFatsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal4CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFats16 = CarbsFoodGrams * CarbsPerGram;
                return totalFats16;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }
    private double calculateFatsFromCarbsFoodItem4(Spinner meal5CarbsSpinner,EditText meal5CarbsGramEditText) {
        String selectedCarbs = meal5CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFatsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal5CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFats17 = CarbsFoodGrams * CarbsPerGram;
                return totalFats17;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }
    private double calculateFatsFromCarbsFoodItem5(Spinner meal6CarbsSpinner,EditText meal6CarbsGramEditText) {
        String selectedCarbs = meal6CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFatsPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal6CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFats18 = CarbsFoodGrams * CarbsPerGram;
                return totalFats18;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }
    private double getFibresPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> fibresValuesMap = new HashMap<>();
        fibresValuesMap.put("Select_Carbs_Food", 1.06);
        fibresValuesMap.put("Basmati Rice Cooked", 0.004);
        fibresValuesMap.put("Brown Rice Cooked", 0.018);
        fibresValuesMap.put("Oats Raw", 0.106);
        fibresValuesMap.put("Potato Raw", 0.018);    // Protein per gram for beef (grams per 100 grams)
        fibresValuesMap.put("Sweet Potato Raw", 0.030);
        fibresValuesMap.put("Quinoa Raw", 0.070);
        fibresValuesMap.put("Millets Raw", 0.085);
        fibresValuesMap.put("Wheat Pasta Raw", 0.030);
        fibresValuesMap.put("Carb Powder", 0.000);
        fibresValuesMap.put("Brown Bread", 0.073);
        fibresValuesMap.put("Cream of rice Raw", 0.000);

        // Return the protein value per gram for the selected protein food item
        return fibresValuesMap.get(foodItem);
    }
    private double calculateFibresFromCarbsFoodItem(Spinner meal1CarbsSpinner,EditText meal1CarbsGramEditText) {
        String selectedCarbs = meal1CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFibresPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal1CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFibres7 = CarbsFoodGrams * CarbsPerGram;
                return totalFibres7;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }
    private double calculateFibresFromCarbsFoodItem1(Spinner meal2CarbsSpinner,EditText meal2CarbsGramEditText) {
        String selectedCarbs = meal2CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFibresPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal2CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFibres8 = CarbsFoodGrams * CarbsPerGram;
                return totalFibres8;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }
    private double calculateFibresFromCarbsFoodItem2(Spinner meal3CarbsSpinner,EditText meal3CarbsGramEditText) {
        String selectedCarbs = meal3CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFibresPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal3CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFibres9 = CarbsFoodGrams * CarbsPerGram;
                return totalFibres9;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }
    private double calculateFibresFromCarbsFoodItem3(Spinner meal4CarbsSpinner,EditText meal4CarbsGramEditText) {
        String selectedCarbs = meal4CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFibresPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal4CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFibres10 = CarbsFoodGrams * CarbsPerGram;
                return totalFibres10;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }
    private double calculateFibresFromCarbsFoodItem4(Spinner meal5CarbsSpinner,EditText meal5CarbsGramEditText) {
        String selectedCarbs = meal5CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFibresPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal5CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFibres11 = CarbsFoodGrams * CarbsPerGram;
                return totalFibres11;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }
    private double calculateFibresFromCarbsFoodItem5(Spinner meal6CarbsSpinner,EditText meal6CarbsGramEditText) {
        String selectedCarbs = meal6CarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFibresPerGramForCarbsFoodItem(selectedCarbs);

        // Get grams of protein food from the EditText
        String CarbsFoodGramsStr = meal6CarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);

                // Calculate total protein
                double totalFibres12 = CarbsFoodGrams * CarbsPerGram;
                return totalFibres12;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }
    private double getCalciumPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateCalciumFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getCalciumPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getPhosphorusPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculatePhosphorusFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getPhosphorusPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getMagnesiumPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateMagnesiumFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getMagnesiumPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getElectrolytePerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateElectrolyteFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getElectrolytePerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSodiumPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateSodiumFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getSodiumPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getPotassiumPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculatePotassiumFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getPotassiumPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getChloridePerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateChlorideFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getChloridePerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getIronPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateIronFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getIronPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getZincPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateZincFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getZincPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCopperPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateCopperFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getCopperPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSeleniumPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateSeleniumFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getSeleniumPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getChromiumPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateChromiumFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getChromiumPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getIodinePerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateIodineFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getIodinePerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getManganesePerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateManganeseFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getManganesePerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getMolybdenumPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateMolybdenumFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getMolybdenumPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getFluoridePerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateFluorideFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getFluoridePerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getBoronPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateBoronFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getBoronPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSiliconPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateSiliconFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getSiliconPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVanadiumPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVanadiumFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVanadiumPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCobaltPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);// Protein per gram for beef (grams per 100 grams)

        // Return the protein value per gram for the selected protein food item
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateCobaltFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getCobaltPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                // Calculate total protein
                return CarbsFoodGrams * CarbsPerGram;

            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminAPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminAFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminAPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminDPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminDFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminDPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminEPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminEFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminEPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminKPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminKFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminKPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminBPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminBFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminBPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB1PerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminB1FromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminB1PerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB2PerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminB2FromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminB2PerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB3PerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminB3FromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminB3PerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminPPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminPFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminPPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB7PerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminB7FromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminB7PerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB9PerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminB9FromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminB9PerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB12PerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminB12FromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminB12PerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
     private double getVitaminB6PerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminB6FromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminB6PerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminCPerGramForCarbsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> CarbsValuesMap = new HashMap<>();
        CarbsValuesMap.put("Select_Carbs_Food", 1.02);
        CarbsValuesMap.put("Basmati Rice Cooked", 0.252);
        CarbsValuesMap.put("Brown Rice Cooked", 0.235);
        CarbsValuesMap.put("Oats Raw", 0.663);
        CarbsValuesMap.put("Potato Raw", 0.17);
        CarbsValuesMap.put("Sweet Potato  Raw", 0.20);
        CarbsValuesMap.put("Quinoa Raw", 0.64);
        CarbsValuesMap.put("Millets Raw", 0.73);
        CarbsValuesMap.put("Wheat Pasta Raw", 0.72);
        CarbsValuesMap.put("Carb Powder", 1.00);
        CarbsValuesMap.put("Brown Bread", 0.45);
        CarbsValuesMap.put("Cream of rice Raw", 0.78);
        return CarbsValuesMap.get(foodItem);
    }
    private double calculateVitaminCFromCarbsFoodItem(Spinner mealCarbsSpinner,EditText mealCarbsGramEditText) {
        String selectedCarbs = mealCarbsSpinner.getSelectedItem().toString();
        double CarbsPerGram = getVitaminCPerGramForCarbsFoodItem(selectedCarbs);
        String CarbsFoodGramsStr = mealCarbsGramEditText.getText().toString();
        if (!CarbsFoodGramsStr.isEmpty()) {
            try {
                double CarbsFoodGrams = Double.parseDouble(CarbsFoodGramsStr);
                return CarbsFoodGrams * CarbsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double getFatsPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FatsValuesMap = new HashMap<>();
        FatsValuesMap.put("Select_Fat_Food", 1.03);
        FatsValuesMap.put("Coconut Oil",1.00); // Protein per gram for chicken (grams per 100 grams)
        FatsValuesMap.put("Olive Oil", 1.00);    // Protein per gram for beef (grams per 100 grams)
        FatsValuesMap.put("Almonds", 0.49);
        FatsValuesMap.put("Pista", 0.45);
        FatsValuesMap.put("Cashews", 0.44);
        FatsValuesMap.put("Walnuts", 0.65);
        FatsValuesMap.put("Peanuts", 0.49);
        FatsValuesMap.put("Peanut Butter", 0.50);
        FatsValuesMap.put("Avocado", 0.14);
        FatsValuesMap.put("Butter", 0.81);
        FatsValuesMap.put("Cheese", 0.33);
        FatsValuesMap.put("Ghee", 1.00);

        // Return the protein value per gram for the selected protein food item
        return FatsValuesMap.get(foodItem);
    }

    private double calculateFatsFromFatsFoodItem(Spinner meal1fatsSpinner,EditText meal1FatsGramEditText) {
        String selectedFats = meal1fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFatsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal1FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFats1 = FatsFoodGrams * FatsPerGram;
                return totalFats1;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFatsFromFatsFoodItem1(Spinner meal2fatsSpinner,EditText meal2FatsGramEditText) {
        String selectedFats = meal2fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFatsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal2FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFats2 = FatsFoodGrams * FatsPerGram;
                return totalFats2;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFatsFromFatsFoodItem2(Spinner meal3fatsSpinner,EditText meal3FatsGramEditText) {
        String selectedFats = meal3fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFatsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal3FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFats3 = FatsFoodGrams * FatsPerGram;
                return totalFats3;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFatsFromFatsFoodItem3(Spinner meal4fatsSpinner,EditText meal4FatsGramEditText) {
        String selectedFats = meal4fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFatsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal4FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFats4 = FatsFoodGrams * FatsPerGram;
                return totalFats4;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFatsFromFatsFoodItem4(Spinner meal5fatsSpinner,EditText meal5FatsGramEditText) {
        String selectedFats = meal5fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFatsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal5FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFats5 = FatsFoodGrams * FatsPerGram;
                return totalFats5;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFatsFromFatsFoodItem5(Spinner meal6fatsSpinner,EditText meal6FatsGramEditText) {
        String selectedFats = meal6fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFatsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal6FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFats6 = FatsFoodGrams * FatsPerGram;
                return totalFats6;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double getProteinsPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateProteinsFromFatsFoodItem(Spinner meal1fatsSpinner,EditText meal1FatsGramEditText) {
        String selectedFats = meal1fatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getProteinsPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = meal1FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                double totalProteins13 = FatsFoodGrams * FatsPerGram;
                return totalProteins13;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromFatsFoodItem1(Spinner meal2fatsSpinner,EditText meal2FatsGramEditText) {
        String selectedFats = meal2fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getProteinsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal2FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalProteins14 = FatsFoodGrams * FatsPerGram;
                return totalProteins14;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateProteinsFromFatsFoodItem2(Spinner meal3fatsSpinner,EditText meal3FatsGramEditText) {
        String selectedFats = meal3fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getProteinsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal3FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalProteins15 = FatsFoodGrams * FatsPerGram;
                return totalProteins15;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateProteinsFromFatsFoodItem3(Spinner meal4fatsSpinner,EditText meal4FatsGramEditText) {
        String selectedFats = meal4fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getProteinsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal4FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalProteins16 = FatsFoodGrams * FatsPerGram;
                return totalProteins16;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateProteinsFromFatsFoodItem4(Spinner meal5fatsSpinner,EditText meal5FatsGramEditText) {
        String selectedFats = meal5fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getProteinsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal5FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalProteins17 = FatsFoodGrams * FatsPerGram;
                return totalProteins17;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateProteinsFromFatsFoodItem5(Spinner meal6fatsSpinner,EditText meal6FatsGramEditText) {
        String selectedFats = meal6fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getProteinsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal6FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalProteins18 = FatsFoodGrams * FatsPerGram;
                return totalProteins18;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double getCarbsPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> carbsValuesMap = new HashMap<>();
        carbsValuesMap.put("Select_Fat_Food", 1.03);
        carbsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        carbsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        carbsValuesMap.put("Almonds", 0.22);
        carbsValuesMap.put("Pista", 0.28);
        carbsValuesMap.put("Cashews", 0.30);
        carbsValuesMap.put("Walnuts", 0.14);
        carbsValuesMap.put("Peanuts", 0.16);
        carbsValuesMap.put("Peanut Butter", 0.20);
        carbsValuesMap.put("Avocado", 0.09);
        carbsValuesMap.put("Butter", 0.006);
        carbsValuesMap.put("Cheese", 0.013);
        carbsValuesMap.put("Ghee", 0.00);

        // Return the protein value per gram for the selected protein food item
        return carbsValuesMap.get(foodItem);
    }

    private double calculateCarbsFromFatsFoodItem(Spinner meal1fatsSpinner,EditText meal1FatsGramEditText) {
        String selectedFats = meal1fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getCarbsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal1FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalCarbs13 = FatsFoodGrams * FatsPerGram;
                return totalCarbs13;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromFatsFoodItem1(Spinner meal2fatsSpinner,EditText meal2FatsGramEditText) {
        String selectedFats = meal2fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getCarbsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal2FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalCarbs14 = FatsFoodGrams * FatsPerGram;
                return totalCarbs14;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromFatsFoodItem2(Spinner meal3fatsSpinner,EditText meal3FatsGramEditText) {
        String selectedFats = meal3fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getCarbsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal3FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalCarbs15 = FatsFoodGrams * FatsPerGram;
                return totalCarbs15;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromFatsFoodItem3(Spinner meal4fatsSpinner,EditText meal4FatsGramEditText) {
        String selectedFats = meal4fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getCarbsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal4FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalCarbs16 = FatsFoodGrams * FatsPerGram;
                return totalCarbs16;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromFatsFoodItem4(Spinner meal5fatsSpinner,EditText meal5FatsGramEditText) {
        String selectedFats = meal5fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getCarbsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal5FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalCarbs17 = FatsFoodGrams * FatsPerGram;
                return totalCarbs17;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromFatsFoodItem5(Spinner meal6fatsSpinner,EditText meal6FatsGramEditText) {
        String selectedFats = meal6fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getCarbsPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal6FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalCarbs18 = FatsFoodGrams * FatsPerGram;
                return totalCarbs18;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }
        return 0.0;
    }

    private double getFibresPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> fibresValuesMap = new HashMap<>();
        fibresValuesMap.put("Select_Fat_Food", 1.07);
        fibresValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        fibresValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        fibresValuesMap.put("Almonds", 0.120);
        fibresValuesMap.put("Pista", 0.103);
        fibresValuesMap.put("Cashews", 0.033);
        fibresValuesMap.put("Walnuts", 0.070);
        fibresValuesMap.put("Peanuts", 0.080);
        fibresValuesMap.put("Peanut Butter", 0.060);
        fibresValuesMap.put("Avocado", 0.070);
        fibresValuesMap.put("Butter", 0.000);
        fibresValuesMap.put("Cheese", 0.005);
        fibresValuesMap.put("Ghee", 0.00);



        // Return the protein value per gram for the selected protein food item
        return fibresValuesMap.get(foodItem);
    }
    private double calculateFibresFromFatsFoodItem(Spinner meal1fatsSpinner,EditText meal1FatsGramEditText) {
        String selectedFats = meal1fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFibresPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal1FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFibres13 = FatsFoodGrams * FatsPerGram;
                return totalFibres13;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFibresFromFatsFoodItem1(Spinner meal2fatsSpinner,EditText meal2FatsGramEditText) {
        String selectedFats = meal2fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFibresPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal2FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFibres14 = FatsFoodGrams * FatsPerGram;
                return totalFibres14;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFibresFromFatsFoodItem2(Spinner meal3fatsSpinner,EditText meal3FatsGramEditText) {
        String selectedFats = meal3fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFibresPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal3FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFibres15 = FatsFoodGrams * FatsPerGram;
                return totalFibres15;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFibresFromFatsFoodItem3(Spinner meal4fatsSpinner,EditText meal4FatsGramEditText) {
        String selectedFats = meal4fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFibresPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal4FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFibres16 = FatsFoodGrams * FatsPerGram;
                return totalFibres16;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFibresFromFatsFoodItem4(Spinner meal5fatsSpinner,EditText meal5FatsGramEditText) {
        String selectedFats = meal5fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFibresPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal5FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFibres17 = FatsFoodGrams * FatsPerGram;
                return totalFibres17;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFibresFromFatsFoodItem5(Spinner meal6fatsSpinner,EditText meal6FatsGramEditText) {
        String selectedFats = meal6fatsSpinner.getSelectedItem().toString();

        double FatsPerGram = getFibresPerGramForFatsFoodItem(selectedFats);

        // Get grams of protein food from the EditText
        String FatsFoodGramsStr = meal6FatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);

                // Calculate total protein
                double totalFibres18 = FatsFoodGrams * FatsPerGram;
                return totalFibres18;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCalciumPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateCalciumFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getCalciumPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getPhosphorusPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculatePhosphorusFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getPhosphorusPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getMagnesiumPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateMagnesiumFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getMagnesiumPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getElectrolytePerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateElectrolyteFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getElectrolytePerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSodiumPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateSodiumFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getSodiumPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getPotassiumPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculatePotassiumFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getPotassiumPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getChloridePerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateChlorideFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getChloridePerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getIronPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateIronFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getIronPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getZincPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateZincFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getZincPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCopperPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateCopperFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getCopperPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSeleniumPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateSeleniumFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getSeleniumPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getChromiumPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateChromiumFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getChromiumPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getIodinePerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateIodineFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getIodinePerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getManganesePerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateManganeseFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getManganesePerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getMolybdenumPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateMolybdenumFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getMolybdenumPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getFluoridePerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateFluorideFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getFluoridePerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getBoronPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateBoronFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getBoronPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSiliconPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateSiliconFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getSiliconPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVanadiumPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVanadiumFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVanadiumPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCobaltPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateCobaltFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getCobaltPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminAPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminAFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminAPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminDPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminDFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminDPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminEPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminEFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminEPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminKPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminKFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminKPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminBPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminBFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminBPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB1PerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminB1FromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminB1PerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB2PerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminB2FromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminB2PerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB3PerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminB3FromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminB3PerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminPPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminPFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminPPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB7PerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminB7FromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminB7PerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB9PerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminB9FromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminB9PerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB12PerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminB12FromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminB12PerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB6PerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminB6FromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminB6PerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminCPerGramForFatsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fat_Food", 1.06);
        proteinsValuesMap.put("Olive Oil", 0.00); // Protein per gram for chicken (grams per 100 grams)
        proteinsValuesMap.put("Coconut Oil", 0.00);    // Protein per gram for beef (grams per 100 grams)
        proteinsValuesMap.put("Almonds", 0.21);
        proteinsValuesMap.put("Pista", 0.21);
        proteinsValuesMap.put("Cashews", 0.18);
        proteinsValuesMap.put("Walnuts", 0.15);
        proteinsValuesMap.put("Peanuts", 0.26);
        proteinsValuesMap.put("Peanut Butter", 0.25);
        proteinsValuesMap.put("Avocado", 0.02);
        proteinsValuesMap.put("Butter", 0.009);
        proteinsValuesMap.put("Cheese", 0.25);
        proteinsValuesMap.put("Ghee", 0.00);
        return proteinsValuesMap.get(foodItem);
    }
    private double calculateVitaminCFromFatsFoodItem(Spinner mealfatsSpinner,EditText mealFatsGramEditText) {
        String selectedFats = mealfatsSpinner.getSelectedItem().toString();
        double FatsPerGram = getVitaminCPerGramForFatsFoodItem(selectedFats);
        String FatsFoodGramsStr = mealFatsGramEditText.getText().toString();
        if (!FatsFoodGramsStr.isEmpty()) {
            try {
                double FatsFoodGrams = Double.parseDouble(FatsFoodGramsStr);
                return FatsFoodGrams * FatsPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }


    private double getFibresPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateFibresFromFruitsFoodItem(Spinner meal1fruitsSpinner,EditText meal1FruitsGramEditText) {
        String selectedFruits = meal1fruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getFibresPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = meal1FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                double totalFibres1 = FruitsFoodGrams * FibresPerGram;
                return totalFibres1;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFibresFromFruitsFoodItem1(Spinner meal2fruitsSpinner,EditText meal2FruitsGramEditText) {
        String selectedFruits = meal2fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getFibresPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal2FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);

                // Calculate total protein
                double totalFibres2 = FruitsFoodGrams * FibresPerGram;
                return totalFibres2;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFibresFromFruitsFoodItem2(Spinner meal3fruitsSpinner,EditText meal3FruitsGramEditText) {
        String selectedFruits = meal3fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getFibresPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal3FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);

                // Calculate total protein
                double totalFibres3 = FruitsFoodGrams * FibresPerGram;
                return totalFibres3;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFibresFromFruitsFoodItem3(Spinner meal4fruitsSpinner,EditText meal4FruitsGramEditText) {
        String selectedFruits = meal4fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getFibresPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal4FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);

                // Calculate total protein
                double totalFibres4 = FruitsFoodGrams * FibresPerGram;
                return totalFibres4;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFibresFromFruitsFoodItem4(Spinner meal5fruitsSpinner,EditText meal5FruitsGramEditText) {
        String selectedFruits = meal5fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getFibresPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal5FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);

                // Calculate total protein
                double totalFibres5 = FruitsFoodGrams * FibresPerGram;
                return totalFibres5;
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid numeric value
                // For example, show an error message or set a default value
            }
        }

        // Return a default value (e.g., 0.0) in case of an error or empty input
        return 0.0;
    }

    private double calculateFibresFromFruitsFoodItem5(Spinner meal6fruitsSpinner,EditText meal6FruitsGramEditText) {
        String selectedFruits = meal6fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getFibresPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal6FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);

                // Calculate total protein
                double totalFibres6 = FruitsFoodGrams * FibresPerGram;
                return totalFibres6;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double getProteinsPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Fruits_and_Vegetables", 1.08);
        proteinsValuesMap.put("Apple", 0.003);
        proteinsValuesMap.put("Banana", 0.011);
        proteinsValuesMap.put("Orange", 0.010);
        proteinsValuesMap.put("Papaya", 0.005);
        proteinsValuesMap.put("Water Melon", 0.006);
        proteinsValuesMap.put("Kiwi", 0.011);
        proteinsValuesMap.put("Dragon Fruits", 0.012);
        proteinsValuesMap.put("Pomogrenate", 0.017);
        proteinsValuesMap.put("Straw Berry", 0.007);
        proteinsValuesMap.put("Cran Berries", 0.004);
        proteinsValuesMap.put("Blue Berries", 0.007);
        proteinsValuesMap.put("Pine Apple", 0.005);
        

        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }

    private double calculateProteinsFromFruitsFoodItem(Spinner meal1fruitsSpinner,EditText meal1FruitsGramEditText) {
        String selectedFruits = meal1fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal1FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins19 = FruitsFoodGrams * FibresPerGram;
                return totalProteins19;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromFruitsFoodItem1(Spinner meal2fruitsSpinner,EditText meal2FruitsGramEditText) {
        String selectedFruits = meal2fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal2FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins20 = FruitsFoodGrams * FibresPerGram;
                return totalProteins20;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromFruitsFoodItem2(Spinner meal3fruitsSpinner,EditText meal3FruitsGramEditText) {
        String selectedFruits = meal3fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal3FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins21 = FruitsFoodGrams * FibresPerGram;
                return totalProteins21;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromFruitsFoodItem3(Spinner meal4fruitsSpinner,EditText meal4FruitsGramEditText) {
        String selectedFruits = meal4fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal4FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins22 = FruitsFoodGrams * FibresPerGram;
                return totalProteins22;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromFruitsFoodItem4(Spinner meal5fruitsSpinner,EditText meal5FruitsGramEditText) {
        String selectedFruits = meal5fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal5FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins23 = FruitsFoodGrams * FibresPerGram;
                return totalProteins23;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromFruitsFoodItem5(Spinner meal6fruitsSpinner,EditText meal6FruitsGramEditText) {
        String selectedFruits = meal6fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal6FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins24 = FruitsFoodGrams * FibresPerGram;
                return totalProteins24;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double getCarbsPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> carbsValuesMap = new HashMap<>();
        carbsValuesMap.put("Select_Fruits_and_Vegetables", 1.09);
        carbsValuesMap.put("Apple", 0.14);
        carbsValuesMap.put("Banana", 0.23);
        carbsValuesMap.put("Orange", 0.083);
        carbsValuesMap.put("Papaya", 0.11);
        carbsValuesMap.put("Water Melon", 0.076);
        carbsValuesMap.put("Kiwi", 0.147);
        carbsValuesMap.put("Dragon Fruits", 0.136);
        carbsValuesMap.put("Pomogrenate", 0.187);
        carbsValuesMap.put("Straw Berry", 0.077);
        carbsValuesMap.put("Cran Berries", 0.122);
        carbsValuesMap.put("Blue Berries", 0.148);
        carbsValuesMap.put("Pine Apple", 0.131);


        // Return the protein value per gram for the selected protein food item
        return carbsValuesMap.get(foodItem);
    }

    private double calculateCarbsFromFruitsFoodItem(Spinner meal1fruitsSpinner,EditText meal1FruitsGramEditText) {
        String selectedFruits = meal1fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal1FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs19 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs19;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromFruitsFoodItem1(Spinner meal2fruitsSpinner,EditText meal2FruitsGramEditText) {
        String selectedFruits = meal2fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal2FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs20 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs20;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromFruitsFoodItem2(Spinner meal3fruitsSpinner,EditText meal3FruitsGramEditText) {
        String selectedFruits = meal3fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal3FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs21 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs21;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromFruitsFoodItem3(Spinner meal4fruitsSpinner,EditText meal4FruitsGramEditText) {
        String selectedFruits = meal4fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal4FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs22 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs22;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromFruitsFoodItem4(Spinner meal5fruitsSpinner,EditText meal5FruitsGramEditText) {
        String selectedFruits = meal5fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal5FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs23 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs23;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromFruitsFoodItem5(Spinner meal6fruitsSpinner,EditText meal6FruitsGramEditText) {
        String selectedFruits = meal6fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal6FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs24 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs24;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double getFatsPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> fatsValuesMap = new HashMap<>();
        fatsValuesMap.put("Select_Fruits_and_Vegetables", 1.10);
        fatsValuesMap.put("Apple", 0.002);
        fatsValuesMap.put("Banana", 0.003);
        fatsValuesMap.put("Orange", 0.002);
        fatsValuesMap.put("Papaya", 0.003);
        fatsValuesMap.put("Water Melon", 0.002);
        fatsValuesMap.put("Kiwi", 0.005);
        fatsValuesMap.put("Dragon Fruits", 0.006);
        fatsValuesMap.put("Pomogrenate", 0.012);
        fatsValuesMap.put("Straw Berry", 0.003);
        fatsValuesMap.put("Cran Berries", 0.001);
        fatsValuesMap.put("Blue Berries", 0.003);
        fatsValuesMap.put("Pine Apple", 0.001);


        // Return the protein value per gram for the selected protein food item
        return fatsValuesMap.get(foodItem);
    }

    private double calculateFatsFromFruitsFoodItem(Spinner meal1fruitsSpinner,EditText meal1FruitsGramEditText) {
        String selectedFruits = meal1fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getFatsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal1FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats19 = FruitsFoodGrams * FibresPerGram;
                return totalFats19;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFatsFromFruitsFoodItem1(Spinner meal2fruitsSpinner,EditText meal2FruitsGramEditText) {
        String selectedFruits = meal2fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getFatsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal2FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats20 = FruitsFoodGrams * FibresPerGram;
                return totalFats20;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFatsFromFruitsFoodItem2(Spinner meal3fruitsSpinner,EditText meal3FruitsGramEditText) {
        String selectedFruits = meal3fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getFatsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal3FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats21 = FruitsFoodGrams * FibresPerGram;
                return totalFats21;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFatsFromFruitsFoodItem3(Spinner meal4fruitsSpinner,EditText meal4FruitsGramEditText) {
        String selectedFruits = meal4fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getFatsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal4FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats22 = FruitsFoodGrams * FibresPerGram;
                return totalFats22;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFatsFromFruitsFoodItem4(Spinner meal5fruitsSpinner,EditText meal5FruitsGramEditText) {
        String selectedFruits = meal5fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getFatsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal5FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats23 = FruitsFoodGrams * FibresPerGram;
                return totalFats23;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFatsFromFruitsFoodItem5(Spinner meal6fruitsSpinner,EditText meal6FruitsGramEditText) {
        String selectedFruits = meal6fruitsSpinner.getSelectedItem().toString();

        double FibresPerGram = getFatsPerGramForFruitsFoodItem(selectedFruits);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal6FruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats24 = FruitsFoodGrams * FibresPerGram;
                return totalFats24;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCalciumPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateCalciumFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getCalciumPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getPhosphorusPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculatePhosphorusFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getPhosphorusPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getMagnesiumPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateMagnesiumFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getMagnesiumPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getElectrolytePerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateElectrolyteFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getElectrolytePerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSodiumPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateSodiumFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getSodiumPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getPotassiumPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculatePotassiumFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getPotassiumPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getChloridePerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateChlorideFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getChloridePerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getIronPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateIronFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getIronPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getZincPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateZincFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getZincPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCopperPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateCopperFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getCopperPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSeleniumPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateSeleniumFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getSeleniumPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double getChromiumPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateChromiumFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getChromiumPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getIodinePerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateIodineFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getIodinePerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getManganesePerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateManganeseFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getManganesePerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getMolybdenumPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateMolybdenumFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getMolybdenumPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getFluoridePerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateFluorideFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getFluoridePerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getBoronPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateBoronFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getBoronPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSiliconPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateSiliconFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getSiliconPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVanadiumPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVanadiumFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVanadiumPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCobaltPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateCobaltFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getCobaltPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminAPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminAFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminAPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminDPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminDFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminDPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminEPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminEFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminEPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminKPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminKFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminKPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminBPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminBFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminBPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB1PerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB1FromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB1PerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB2PerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB2FromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB2PerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB3PerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB3FromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB3PerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminPPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminPFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminPPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB7PerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB7FromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB7PerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB9PerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB9FromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB9PerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB12PerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB12FromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB12PerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB6PerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB6FromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB6PerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminCPerGramForFruitsFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Fruits_and_Vegetables", 1.04);
        FibresValuesMap.put("Apple", 0.024);
        FibresValuesMap.put("Banana", 0.026);
        FibresValuesMap.put("Orange", 0.022);
        FibresValuesMap.put("Papaya", 0.017);
        FibresValuesMap.put("Water Melon", 0.004);
        FibresValuesMap.put("Kiwi", 0.030);
        FibresValuesMap.put("Dragon Fruits", 0.030);
        FibresValuesMap.put("Straw Berry", 0.020);
        FibresValuesMap.put("Pomogrenate", 0.040);
        FibresValuesMap.put("Cran Berries", 0.046);
        FibresValuesMap.put("Blue Berries", 0.024);
        FibresValuesMap.put("Pine Apple", 0.014);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminCFromFruitsFoodItem(Spinner mealfruitsSpinner,EditText mealFruitsGramEditText) {
        String selectedFruits = mealfruitsSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminCPerGramForFruitsFoodItem(selectedFruits);
        String FruitsFoodGramsStr = mealFruitsGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double getFibresPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateFibresFromVegetableFoodItem(Spinner meal1vegetableSpinner,EditText meal1VegetablesGramEditText) {
        String selectedVegetable = meal1vegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getFibresPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = meal1VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                double totalFibres25 = FruitsFoodGrams * FibresPerGram;
                return totalFibres25;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double calculateFibresFromVegetableFoodItem1(Spinner meal2vegetableSpinner,EditText meal2VegetablesGramEditText) {
        String selectedVegetable = meal2vegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getFibresPerGramForVegetableFoodItem(selectedVegetable);
        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal2VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                double totalFibres26 = FruitsFoodGrams * FibresPerGram;
                return totalFibres26;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFibresFromVegetableFoodItem2(Spinner meal3vegetableSpinner,EditText meal3VegetablesGramEditText) {
        String selectedVegetable = meal3vegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getFibresPerGramForVegetableFoodItem(selectedVegetable);
        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal3VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                double totalFibres27 = FruitsFoodGrams * FibresPerGram;
                return totalFibres27;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFibresFromVegetableFoodItem3(Spinner meal4vegetableSpinner,EditText meal4VegetablesGramEditText) {
        String selectedVegetable = meal4vegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getFibresPerGramForVegetableFoodItem(selectedVegetable);
        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal4VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                double totalFibres28 = FruitsFoodGrams * FibresPerGram;
                return totalFibres28;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFibresFromVegetableFoodItem4(Spinner meal5vegetableSpinner,EditText meal5VegetablesGramEditText) {
        String selectedVegetable = meal5vegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getFibresPerGramForVegetableFoodItem(selectedVegetable);
        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal5VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                double totalFibres29 = FruitsFoodGrams * FibresPerGram;
                return totalFibres29;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFibresFromVegetableFoodItem5(Spinner meal6vegetableSpinner,EditText meal6VegetablesGramEditText) {
        String selectedVegetable = meal6vegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getFibresPerGramForVegetableFoodItem(selectedVegetable);
        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal6VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                double totalFibres30 = FruitsFoodGrams * FibresPerGram;
                return totalFibres30;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double getProteinsPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> proteinsValuesMap = new HashMap<>();
        proteinsValuesMap.put("Select_Vegetables", 1.08);
        proteinsValuesMap.put("Spinach", 0.029);
        proteinsValuesMap.put("Amaranthus", 0.025);
        proteinsValuesMap.put("French Beans", 0.018);
        proteinsValuesMap.put("Broccoli", 0.028);
        proteinsValuesMap.put("Cauli Flower", 0.019);
        proteinsValuesMap.put("IceBurg Lettuce", 0.090);
        proteinsValuesMap.put("Zucchini", 0.012);
        proteinsValuesMap.put("Bell Peppers", 0.010);
        proteinsValuesMap.put("Capcicum", 0.010);
        proteinsValuesMap.put("Carrot", 0.009);
        proteinsValuesMap.put("Cucumber", 0.007);
        proteinsValuesMap.put("Green Peas", 0.050);
        proteinsValuesMap.put("Tomatoes", 0.009);
        proteinsValuesMap.put("Onion", 0.011);
        proteinsValuesMap.put("Coriander Leaves", 0.021);

        // Return the protein value per gram for the selected protein food item
        return proteinsValuesMap.get(foodItem);
    }

    private double calculateProteinsFromVegetableFoodItem(Spinner meal1vegetableSpinner,EditText meal1VegetablesGramEditText) {
        String selectedVegetable = meal1vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal1VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins25 = FruitsFoodGrams * FibresPerGram;
                return totalProteins25;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromVegetableFoodItem1(Spinner meal2vegetableSpinner,EditText meal2VegetablesGramEditText) {
        String selectedVegetable = meal2vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal2VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins26 = FruitsFoodGrams * FibresPerGram;
                return totalProteins26;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromVegetableFoodItem2(Spinner meal3vegetableSpinner,EditText meal3VegetablesGramEditText) {
        String selectedVegetable = meal3vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal3VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins27 = FruitsFoodGrams * FibresPerGram;
                return totalProteins27;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromVegetableFoodItem3(Spinner meal4vegetableSpinner,EditText meal4VegetablesGramEditText) {
        String selectedVegetable = meal4vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal4VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins28 = FruitsFoodGrams * FibresPerGram;
                return totalProteins28;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromVegetableFoodItem4(Spinner meal5vegetableSpinner,EditText meal5VegetablesGramEditText) {
        String selectedVegetable = meal5vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal5VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins29 = FruitsFoodGrams * FibresPerGram;
                return totalProteins29;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateProteinsFromVegetableFoodItem5(Spinner meal6vegetableSpinner,EditText meal6VegetablesGramEditText) {
        String selectedVegetable = meal6vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getProteinsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal6VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalProteins30 = FruitsFoodGrams * FibresPerGram;
                return totalProteins30;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double getCarbsPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> carbsValuesMap = new HashMap<>();
        carbsValuesMap.put("Select_Vegetables", 1.09);
        carbsValuesMap.put("Spinach", 0.036);
        carbsValuesMap.put("Amaranthus", 0.041);
        carbsValuesMap.put("French Beans", 0.071);
        carbsValuesMap.put("Broccoli", 0.066);
        carbsValuesMap.put("Cauli Flower", 0.050);
        carbsValuesMap.put("IceBurg Lettuce", 0.029);
        carbsValuesMap.put("Zucchini", 0.031);
        carbsValuesMap.put("Bell Peppers", 0.060);
        carbsValuesMap.put("Capcicum", 0.060);
        carbsValuesMap.put("Green Peas", 0.140);
        carbsValuesMap.put("Tomatoes", 0.039);
        carbsValuesMap.put("Carrot", 0.100);
        carbsValuesMap.put("Cucumber", 0.036);
        carbsValuesMap.put("Onion", 0.000);
        carbsValuesMap.put("Coriander Leaves", 0.037);

        // Return the protein value per gram for the selected protein food item
        return carbsValuesMap.get(foodItem);
    }

    private double calculateCarbsFromVegetableFoodItem(Spinner meal1vegetableSpinner,EditText meal1VegetablesGramEditText) {
        String selectedVegetable = meal1vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal1VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs25 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs25;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromVegetableFoodItem1(Spinner meal2vegetableSpinner,EditText meal2VegetablesGramEditText) {
        String selectedVegetable = meal2vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal2VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs26 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs26;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromVegetableFoodItem2(Spinner meal3vegetableSpinner,EditText meal3VegetablesGramEditText) {
        String selectedVegetable = meal3vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal3VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs27 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs27;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromVegetableFoodItem3(Spinner meal4vegetableSpinner,EditText meal4VegetablesGramEditText) {
        String selectedVegetable = meal4vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal4VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs28 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs28;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromVegetableFoodItem4(Spinner meal5vegetableSpinner,EditText meal5VegetablesGramEditText) {
        String selectedVegetable = meal5vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal5VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs29 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs29;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateCarbsFromVegetableFoodItem5(Spinner meal6vegetableSpinner,EditText meal6VegetablesGramEditText) {
        String selectedVegetable = meal6vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getCarbsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal6VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalCarbs30 = FruitsFoodGrams * FibresPerGram;
                return totalCarbs30;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double getFatsPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> fatsValuesMap = new HashMap<>();
        fatsValuesMap.put("Select_Vegetables", 1.10);
        fatsValuesMap.put("Spinach", 0.004);
        fatsValuesMap.put("Amaranthus", 0.003);
        fatsValuesMap.put("French Beans", 0.002);
        fatsValuesMap.put("Broccoli", 0.006);
        fatsValuesMap.put("Cauli Flower", 0.003);
        fatsValuesMap.put("IceBurg Lettuce", 0.001);
        fatsValuesMap.put("Zucchini", 0.003);
        fatsValuesMap.put("Bell Peppers", 0.003);
        fatsValuesMap.put("Capcicum", 0.003);
        fatsValuesMap.put("Carrot", 0.002);
        fatsValuesMap.put("Cucumber", 0.002);
        fatsValuesMap.put("Green Peas", 0.004);
        fatsValuesMap.put("Tomatoes", 0.002);
        fatsValuesMap.put("Onion", 0.005);
        fatsValuesMap.put("Coriander Leaves", 0.005);


        // Return the protein value per gram for the selected protein food item
        return fatsValuesMap.get(foodItem);
    }

    private double calculateFatsFromVegetableFoodItem(Spinner meal1vegetableSpinner,EditText meal1VegetablesGramEditText) {
        String selectedVegetable = meal1vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getFatsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal1VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats25 = FruitsFoodGrams * FibresPerGram;
                return totalFats25;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFatsFromVegetableFoodItem1(Spinner meal2vegetableSpinner,EditText meal2VegetablesGramEditText) {
        String selectedVegetable = meal2vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getFatsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal2VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats26 = FruitsFoodGrams * FibresPerGram;
                return totalFats26;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFatsFromVegetableFoodItem2(Spinner meal3vegetableSpinner,EditText meal3VegetablesGramEditText) {
        String selectedVegetable = meal3vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getFatsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal3VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats27 = FruitsFoodGrams * FibresPerGram;
                return totalFats27;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFatsFromVegetableFoodItem3(Spinner meal4vegetableSpinner,EditText meal4VegetablesGramEditText) {
        String selectedVegetable = meal4vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getFatsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal4VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats28 = FruitsFoodGrams * FibresPerGram;
                return totalFats28;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFatsFromVegetableFoodItem4(Spinner meal5vegetableSpinner,EditText meal5VegetablesGramEditText) {
        String selectedVegetable = meal5vegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getFatsPerGramForVegetableFoodItem(selectedVegetable);
        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal5VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats29 = FruitsFoodGrams * FibresPerGram;
                return totalFats29;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }

    private double calculateFatsFromVegetableFoodItem5(Spinner meal6vegetableSpinner,EditText meal6VegetablesGramEditText) {
        String selectedVegetable = meal6vegetableSpinner.getSelectedItem().toString();

        double FibresPerGram = getFatsPerGramForVegetableFoodItem(selectedVegetable);

        // Get grams of protein food from the EditText
        String FruitsFoodGramsStr = meal6VegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                // Calculate total protein
                double totalFats30 = FruitsFoodGrams * FibresPerGram;
                return totalFats30;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCalciumPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateCalciumFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getCalciumPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getPhosphorusPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculatePhosphorusFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getPhosphorusPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getMagnesiumPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateMagnesiumFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getMagnesiumPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getElectrolytePerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateElectrolyteFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getElectrolytePerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSodiumPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateSodiumFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getSodiumPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getPotassiumPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculatePotassiumFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getPotassiumPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getChloridePerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateChlorideFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getChloridePerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getIronPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateIronFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getIronPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getZincPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateZincFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getZincPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCopperPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateCopperFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getCopperPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSeleniumPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateSeleniumFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getSeleniumPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getChromiumPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateChromiumFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getChromiumPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getIodinePerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateIodineFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getIodinePerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getManganesePerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateManganeseFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getManganesePerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getMolybdenumPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateMolybdenumFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getMolybdenumPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getFluoridePerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateFluorideFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getFluoridePerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getBoronPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateBoronFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getBoronPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getSiliconPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateSiliconFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getSiliconPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVanadiumPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVanadiumFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVanadiumPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getCobaltPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateCobaltFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getCobaltPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminAPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminAFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminAPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminDPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminDFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminDPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminEPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminEFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminEPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminKPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminKFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminKPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminBPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminBFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminBPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB1PerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB1FromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB1PerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB2PerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB2FromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB2PerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB3PerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB3FromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB3PerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminPPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminPFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminPPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB7PerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB7FromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB7PerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB9PerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB9FromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB9PerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB12PerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB12FromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB12PerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminB6PerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminB6FromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminB6PerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private double getVitaminCPerGramForVegetableFoodItem(String foodItem) {
        // Define a mapping between protein food items and their protein values
        Map<String, Double> FibresValuesMap = new HashMap<>();
        FibresValuesMap.put("Select_Vegetables", 1.04);
        FibresValuesMap.put("Spinach", 0.022);
        FibresValuesMap.put("Amaranthus", 0.016);
        FibresValuesMap.put("French Beans", 0.034);
        FibresValuesMap.put("Broccoli", 0.026);
        FibresValuesMap.put("Cauli Flower", 0.025);
        FibresValuesMap.put("IceBurg Lettuce", 0.012);
        FibresValuesMap.put("Zucchini", 0.010);
        FibresValuesMap.put("Bell Peppers", 0.020);
        FibresValuesMap.put("Capcicum", 0.020);
        FibresValuesMap.put("Green Peas", 0.050);
        FibresValuesMap.put("Tomatoes", 0.012);
        FibresValuesMap.put("Onion", 0.017);
        FibresValuesMap.put("Carrot", 0.028);
        FibresValuesMap.put("Cucumber", 0.005);
        FibresValuesMap.put("Coriander Leaves", 0.050);
        return FibresValuesMap.get(foodItem);
    }
    private double calculateVitaminCFromVegetableFoodItem(Spinner mealvegetableSpinner,EditText mealVegetablesGramEditText) {
        String selectedVegetable = mealvegetableSpinner.getSelectedItem().toString();
        double FibresPerGram = getVitaminCPerGramForVegetableFoodItem(selectedVegetable);
        String FruitsFoodGramsStr = mealVegetablesGramEditText.getText().toString();
        if (!FruitsFoodGramsStr.isEmpty()) {
            try {
                double FruitsFoodGrams = Double.parseDouble(FruitsFoodGramsStr);
                return FruitsFoodGrams * FibresPerGram;
            } catch (NumberFormatException e) {
            }
        }
        return 0.0;
    }
    private List<String> getProteinFoodItems() {
        // You need to provide the actual data source
        List<String> proteinItems = new ArrayList<>();
        proteinItems.add("Select_Protein_Food");
        proteinItems.add("Chicken Breast");
        proteinItems.add("Egg Whole Raw");
        proteinItems.add("whey Protein Raw");
        proteinItems.add("Egg Whites Raw");
        proteinItems.add("Fish Salmon Raw");
        proteinItems.add("Fish tuna Raw");
        proteinItems.add("Mutton Boneless Raw");
        proteinItems.add("Firm Tofu Raw");
        proteinItems.add("Paneer Raw");
        proteinItems.add("Lentil Sprouts Raw");
        proteinItems.add("Dal Raw");
        proteinItems.add("Tempeh Raw");
        proteinItems.add("Shrimp Raw");
        return proteinItems;
    }
    private List<String> getCarbsFoodItems() {
        // You need to provide the actual data source
        List<String> carbsItems = new ArrayList<>();
        carbsItems.add("Select_Carbs_Food");
        carbsItems.add("Basmati Rice Cooked");
        carbsItems.add("Brown Rice Cooked");
        carbsItems.add("Oats Raw");
        carbsItems.add("Potato Raw");
        carbsItems.add("Sweet Potato Raw");
        carbsItems.add("Quinoa Raw");
        carbsItems.add("Millets Raw");
        carbsItems.add("Wheat Pasta Raw");
        carbsItems.add("Carb Powder");
        carbsItems.add("Brown Bread");
        carbsItems.add("Cream of rice Raw");
        // Add other carbs food items
        return carbsItems;
    }
    private List<String> getFatsFoodItems() {
        List<String> fatsItems = new ArrayList<>();
        fatsItems.add("Select_Fat_Food");
        fatsItems.add("Coconut Oil");
        fatsItems.add("Avocado");
        fatsItems.add("Olive Oil");
        fatsItems.add("Almonds");
        fatsItems.add("Pista");
        fatsItems.add("Cashews");
        fatsItems.add("Walnuts");
        fatsItems.add("Peanuts");
        fatsItems.add("Peanut Butter");
        fatsItems.add("Cheese");
        fatsItems.add("Ghee");
        fatsItems.add("Butter");
        // Add other fats food items
        return fatsItems;
    }
    private List<String> getFruitsVegetablesFoodItems() {
        List<String> fruitsVegetablesItems = new ArrayList<>();
        fruitsVegetablesItems.add("Select_Fruits_and_Vegetables");
        fruitsVegetablesItems.add("Apple");
        fruitsVegetablesItems.add("Banana");
        fruitsVegetablesItems.add("Orange");
        fruitsVegetablesItems.add("Papaya");
        fruitsVegetablesItems.add("Water Melon");
        fruitsVegetablesItems.add("Kiwi");
        fruitsVegetablesItems.add("Dragon Fruits");
        fruitsVegetablesItems.add("Pomogrenate");
        fruitsVegetablesItems.add("Straw Berry");
        fruitsVegetablesItems.add("Cran Berries");
        fruitsVegetablesItems.add("Blue Berries");
        fruitsVegetablesItems.add("Pine Apple");

        // Add other fruits and vegetables food items
        return fruitsVegetablesItems;
    }

    private List<String> getVegetablesFoodItems() {
        List<String> VegetablesItems = new ArrayList<>();
        VegetablesItems.add("Select_Vegetables");
        VegetablesItems.add("Spinach");
        VegetablesItems.add("Amaranthus");
        VegetablesItems.add("French Beans");
        VegetablesItems.add("Broccoli");
        VegetablesItems.add("Cauli Flower");
        VegetablesItems.add("IceBurg Lettuce");
        VegetablesItems.add("Zucchini");
        VegetablesItems.add("Bell Peppers");
        VegetablesItems.add("Capcicum");
        VegetablesItems.add("Green Peas");
        VegetablesItems.add("Tomatoes");
        VegetablesItems.add("Onion");
        VegetablesItems.add("Coriander Leaves");
        VegetablesItems.add("Carrot");
        VegetablesItems.add("Cucumber");

        // Add other fruits and vegetables food items
        return VegetablesItems;
    }
    private void addTextWatcherToProteinsAndCalories(final EditText proteinsEditText, final EditText caloriesEditText) {
        proteinsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValue(proteinsEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValue(proteinsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToCarbsAndCalories(final EditText carbsEditText, final EditText caloriesEditText) {
        carbsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValue(carbsEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValue(carbsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToFatsAndCalories(final EditText fatsEditText, final EditText caloriesEditText) {
        fatsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValue(fatsEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValue(fatsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToFruitsVegetablesAndCalories(final EditText fruitsVegetablesEditText, final EditText caloriesEditText) {
        fruitsVegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValue(fruitsVegetablesEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValue(fruitsVegetablesEditText, caloriesEditText);
            }
        });
    }

    private void addTextWatcherToVegetablesAndCalories(final EditText VegetablesEditText, final EditText caloriesEditText) {
        VegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValue(VegetablesEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValue(VegetablesEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToProteinsAndCaloriesMeal2(final EditText proteinsEditText, final EditText caloriesEditText) {
        proteinsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValueMeal2(proteinsEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValueMeal2(proteinsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToCarbsAndCaloriesMeal2(final EditText carbsEditText, final EditText caloriesEditText) {
        carbsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValueMeal2(carbsEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValueMeal2(carbsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToFatsAndCaloriesMeal2(final EditText fatsEditText, final EditText caloriesEditText) {
        fatsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValueMeal2(fatsEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValueMeal2(fatsEditText, caloriesEditText);
            }
        });
    }

    private void addTextWatcherToFruitsVegetablesAndCaloriesMeal2(final EditText fruitsVegetablesEditText, final EditText caloriesEditText) {
        fruitsVegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValueMeal2(fruitsVegetablesEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValueMeal2(fruitsVegetablesEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToVegetablesAndCaloriesMeal2(final EditText VegetablesEditText, final EditText caloriesEditText) {
        VegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValueMeal2(VegetablesEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValueMeal2(VegetablesEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToProteinsAndCaloriesMeal3(final EditText proteinsEditText, final EditText caloriesEditText) {
        proteinsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValueMeal3(proteinsEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValueMeal3(proteinsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToCarbsAndCaloriesMeal3(final EditText carbsEditText, final EditText caloriesEditText) {
        carbsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValueMeal3(carbsEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValueMeal3(carbsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToFatsAndCaloriesMeal3(final EditText fatsEditText, final EditText caloriesEditText) {
        fatsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValueMeal3(fatsEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValueMeal3(fatsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToFruitsVegetablesAndCaloriesMeal3(final EditText fruitsVegetablesEditText, final EditText caloriesEditText) {
        fruitsVegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValueMeal3(fruitsVegetablesEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValueMeal3(fruitsVegetablesEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToVegetablesAndCaloriesMeal3(final EditText VegetablesEditText, final EditText caloriesEditText) {
        VegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValueMeal3(VegetablesEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValueMeal3(VegetablesEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToProteinsAndCaloriesMeal4(final EditText proteinsEditText, final EditText caloriesEditText) {
        proteinsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValueMeal4(proteinsEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValueMeal4(proteinsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToCarbsAndCaloriesMeal4(final EditText carbsEditText, final EditText caloriesEditText) {
        carbsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValueMeal4(carbsEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValueMeal4(carbsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToFatsAndCaloriesMeal4(final EditText fatsEditText, final EditText caloriesEditText) {
        fatsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValueMeal4(fatsEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValueMeal4(fatsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToFruitsVegetablesAndCaloriesMeal4(final EditText fruitsVegetablesEditText, final EditText caloriesEditText) {
        fruitsVegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValueMeal4(fruitsVegetablesEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValueMeal4(fruitsVegetablesEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToVegetablesAndCaloriesMeal4(final EditText VegetablesEditText, final EditText caloriesEditText) {
        VegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValueMeal4(VegetablesEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValueMeal4(VegetablesEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToProteinsAndCaloriesMeal5(final EditText proteinsEditText, final EditText caloriesEditText) {
        proteinsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValueMeal5(proteinsEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValueMeal5(proteinsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToCarbsAndCaloriesMeal5(final EditText carbsEditText, final EditText caloriesEditText) {
        carbsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start,int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValueMeal5(carbsEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValueMeal5(carbsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToFatsAndCaloriesMeal5(final EditText fatsEditText, final EditText caloriesEditText) {
        fatsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValueMeal5(fatsEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValueMeal5(fatsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToFruitsVegetablesAndCaloriesMeal5(final EditText fruitsVegetablesEditText, final EditText caloriesEditText) {
        fruitsVegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValueMeal5(fruitsVegetablesEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValueMeal5(fruitsVegetablesEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToVegetablesAndCaloriesMeal5(final EditText VegetablesEditText, final EditText caloriesEditText) {
        VegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValueMeal5(VegetablesEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValueMeal5(VegetablesEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToProteinsAndCaloriesMeal6(final EditText proteinsEditText, final EditText caloriesEditText) {
        proteinsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValueMeal6(proteinsEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateProteinsValueMeal6(proteinsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToCarbsAndCaloriesMeal6(final EditText carbsEditText, final EditText caloriesEditText) {
        carbsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValueMeal6(carbsEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateCarbsValueMeal6(carbsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToFatsAndCaloriesMeal6(final EditText fatsEditText, final EditText caloriesEditText) {
        fatsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValueMeal6(fatsEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateFatsValueMeal6(fatsEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToFruitsVegetablesAndCaloriesMeal6(final EditText fruitsVegetablesEditText, final EditText caloriesEditText) {
        fruitsVegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValueMeal6(fruitsVegetablesEditText, caloriesEditText);
            }
        });
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateFruitsVegetablesValueMeal6(fruitsVegetablesEditText, caloriesEditText);
            }
        });
    }
    private void addTextWatcherToVegetablesAndCaloriesMeal6(final EditText VegetablesEditText, final EditText caloriesEditText) {
        VegetablesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValueMeal6(VegetablesEditText, caloriesEditText);
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateVegetablesValueMeal6(VegetablesEditText, caloriesEditText);
            }
        });
    }

    private void updateProteinsValue(EditText proteinsEditText, EditText caloriesEditText) {
        String proteinsString = proteinsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        Log.d("MyApp", "Proteins String: " + proteinsString);
        Log.d("MyApp", "Calories String: " + caloriesString);

        if (!proteinsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double proteinsValue = Double.parseDouble(proteinsString);
                double caloriesValue = Double.parseDouble(caloriesString);
                double updatedProteinsValue = (proteinsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewResult with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal1PtextViewResult)).setText(String.valueOf(updatedProteinsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing proteins or calories as double", e);
            }
        }
    }
    private void updateCarbsValue(EditText carbsEditText, EditText caloriesEditText) {
        String carbsString = carbsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!carbsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double carbsValue = Double.parseDouble(carbsString);
                double caloriesValue = Double.parseDouble(caloriesString);
                double updatedCarbsValue = (carbsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewCarbsResult with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal1CtextViewResult)).setText(String.valueOf(updatedCarbsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing carbs or calories as double", e);
            }
        }
    }
    private void updateFatsValue(EditText fatsEditText, EditText caloriesEditText) {
        String fatsString = fatsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fatsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fatsValue = Double.parseDouble(fatsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFatsValue = (fatsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFatsResult with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal1FtextViewResult)).setText(String.valueOf(updatedFatsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fats or calories as double", e);
            }
        }
    }
    private void updateFruitsVegetablesValue(EditText fruitsVegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = fruitsVegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fruitsVegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFruitsVegetablesValue = (fruitsVegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesResult with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal1VtextViewResult)).setText(String.valueOf(updatedFruitsVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }

    private void updateVegetablesValue(EditText VegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = VegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double VegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedVegetablesValue = (VegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesResult with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal1VVtextViewResult)).setText(String.valueOf(updatedVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }
    private void updateProteinsValueMeal2(EditText proteinsEditText, EditText caloriesEditText) {
        String proteinsString = proteinsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!proteinsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double proteinsValue = Double.parseDouble(proteinsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedProteinsValue = (proteinsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewProteinsMeal2Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal2PtextViewResult)).setText(String.valueOf(updatedProteinsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing proteins or calories as double", e);
            }
        }
    }
    private void updateCarbsValueMeal2(EditText carbsEditText, EditText caloriesEditText) {
        String carbsString = carbsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!carbsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double carbsValue = Double.parseDouble(carbsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedCarbsValue = (carbsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewCarbsMeal2Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal2CtextViewResult)).setText(String.valueOf(updatedCarbsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing carbs or calories as double", e);
            }
        }
    }
    private void updateFatsValueMeal2(EditText fatsEditText, EditText caloriesEditText) {
        String fatsString = fatsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fatsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fatsValue = Double.parseDouble(fatsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFatsValue = (fatsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFatsMeal2Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal2FtextViewResult)).setText(String.valueOf(updatedFatsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fats or calories as double", e);
            }
        }
    }
    private void updateFruitsVegetablesValueMeal2(EditText fruitsVegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = fruitsVegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fruitsVegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFruitsVegetablesValue = (fruitsVegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesMeal2Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal2VtextViewResult)).setText(String.valueOf(updatedFruitsVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }
    private void updateVegetablesValueMeal2(EditText VegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = VegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double VegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedVegetablesValue = (VegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesResult with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal2VVtextViewResult)).setText(String.valueOf(updatedVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }
    private void updateProteinsValueMeal3(EditText proteinsEditText, EditText caloriesEditText) {
        String proteinsString = proteinsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!proteinsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double proteinsValue = Double.parseDouble(proteinsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedProteinsValue = (proteinsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewProteinsMeal3Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal3PtextViewResult)).setText(String.valueOf(updatedProteinsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing proteins or calories as double", e);
            }
        }
    }
    private void updateCarbsValueMeal3(EditText carbsEditText, EditText caloriesEditText) {
        String carbsString = carbsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!carbsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double carbsValue = Double.parseDouble(carbsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedCarbsValue = (carbsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewCarbsMeal3Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal3CtextViewResult)).setText(String.valueOf(updatedCarbsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing carbs or calories as double", e);
            }
        }
    }
    private void updateFatsValueMeal3(EditText fatsEditText, EditText caloriesEditText) {
        String fatsString = fatsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fatsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fatsValue = Double.parseDouble(fatsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFatsValue = (fatsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFatsMeal3Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal3FtextViewResult)).setText(String.valueOf(updatedFatsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fats or calories as double", e);
            }
        }
    }
    private void updateFruitsVegetablesValueMeal3(EditText fruitsVegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = fruitsVegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fruitsVegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFruitsVegetablesValue = (fruitsVegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesMeal3Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal3VtextViewResult)).setText(String.valueOf(updatedFruitsVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }
    private void updateVegetablesValueMeal3(EditText VegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = VegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double VegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedVegetablesValue = (VegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesResult with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal3VVtextViewResult)).setText(String.valueOf(updatedVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }
    private void updateProteinsValueMeal4(EditText proteinsEditText, EditText caloriesEditText) {
        String proteinsString = proteinsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!proteinsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double proteinsValue = Double.parseDouble(proteinsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedProteinsValue = (proteinsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewProteinsMeal4Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal4PtextViewResult)).setText(String.valueOf(updatedProteinsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing proteins or calories as double", e);
            }
        }
    }
    private void updateCarbsValueMeal4(EditText carbsEditText, EditText caloriesEditText) {
        String carbsString = carbsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!carbsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double carbsValue = Double.parseDouble(carbsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedCarbsValue = (carbsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewCarbsMeal4Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal4CtextViewResult)).setText(String.valueOf(updatedCarbsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing carbs or calories as double", e);
            }
        }
    }
    private void updateFatsValueMeal4(EditText fatsEditText, EditText caloriesEditText) {
        String fatsString = fatsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fatsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fatsValue = Double.parseDouble(fatsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFatsValue = (fatsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFatsMeal4Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal4FtextViewResult)).setText(String.valueOf(updatedFatsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fats or calories as double", e);
            }
        }
    }

    private void updateFruitsVegetablesValueMeal4(EditText fruitsVegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = fruitsVegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fruitsVegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFruitsVegetablesValue = (fruitsVegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesMeal4Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal4VtextViewResult)).setText(String.valueOf(updatedFruitsVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }
    private void updateVegetablesValueMeal4(EditText VegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = VegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double VegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedVegetablesValue = (VegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesResult with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal4VVtextViewResult)).setText(String.valueOf(updatedVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }
    private void updateProteinsValueMeal5(EditText proteinsEditText, EditText caloriesEditText) {
        String proteinsString = proteinsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!proteinsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double proteinsValue = Double.parseDouble(proteinsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedProteinsValue = (proteinsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewProteinsMeal5Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal5PtextViewResult)).setText(String.valueOf(updatedProteinsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing proteins or calories as double", e);
            }
        }
    }
    private void updateCarbsValueMeal5(EditText carbsEditText, EditText caloriesEditText) {
        String carbsString = carbsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!carbsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double carbsValue = Double.parseDouble(carbsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedCarbsValue = (carbsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewCarbsMeal5Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal5CtextViewResult)).setText(String.valueOf(updatedCarbsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing carbs or calories as double", e);
            }
        }
    }
    private void updateFatsValueMeal5(EditText fatsEditText, EditText caloriesEditText) {
        String fatsString = fatsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fatsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fatsValue = Double.parseDouble(fatsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFatsValue = (fatsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFatsMeal5Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal5FtextViewResult)).setText(String.valueOf(updatedFatsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fats or calories as double", e);
            }
        }
    }
    private void updateFruitsVegetablesValueMeal5(EditText fruitsVegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = fruitsVegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fruitsVegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFruitsVegetablesValue = (fruitsVegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesMeal5Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal5VtextViewResult)).setText(String.valueOf(updatedFruitsVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }
    private void updateVegetablesValueMeal5(EditText VegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = VegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double VegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedVegetablesValue = (VegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesResult with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal5VVtextViewResult)).setText(String.valueOf(updatedVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }
    private void updateProteinsValueMeal6(EditText proteinsEditText, EditText caloriesEditText) {
        String proteinsString = proteinsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!proteinsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double proteinsValue = Double.parseDouble(proteinsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedProteinsValue = (proteinsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewProteinsMeal6Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal6PtextViewResult)).setText(String.valueOf(updatedProteinsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing proteins or calories as double", e);
            }
        }
    }
    private void updateCarbsValueMeal6(EditText carbsEditText, EditText caloriesEditText) {
        String carbsString = carbsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!carbsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double carbsValue = Double.parseDouble(carbsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedCarbsValue = (carbsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewCarbsMeal6Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal6CtextViewResult)).setText(String.valueOf(updatedCarbsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing carbs or calories as double", e);
            }
        }
    }
    private void updateFatsValueMeal6(EditText fatsEditText, EditText caloriesEditText) {
        String fatsString = fatsEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fatsString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fatsValue = Double.parseDouble(fatsString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFatsValue = (fatsValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFatsMeal6Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal6FtextViewResult)).setText(String.valueOf(updatedFatsValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fats or calories as double", e);
            }
        }
    }
    private void updateFruitsVegetablesValueMeal6(EditText fruitsVegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = fruitsVegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double fruitsVegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedFruitsVegetablesValue = (fruitsVegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesMeal6Result with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal6VtextViewResult)).setText(String.valueOf(updatedFruitsVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }
    private void updateVegetablesValueMeal6(EditText VegetablesEditText, EditText caloriesEditText) {
        String fruitsVegetablesString = VegetablesEditText.getText().toString();
        String caloriesString = caloriesEditText.getText().toString();

        if (!fruitsVegetablesString.isEmpty() && !caloriesString.isEmpty()) {
            try {
                double VegetablesValue = Double.parseDouble(fruitsVegetablesString);
                double caloriesValue = Double.parseDouble(caloriesString);

                double updatedVegetablesValue = (VegetablesValue / 100.0) * caloriesValue;
                // Display the calculated value in a separate TextView or handle it appropriately
                // This avoids an infinite loop of updating the same EditText
                // You might need to replace R.id.textViewFruitsVegetablesResult with the actual ID of the TextView you want to update
                ((TextView) findViewById(R.id.meal6VVtextViewResult)).setText(String.valueOf(updatedVegetablesValue));
            } catch (NumberFormatException e) {
                Log.e("MyApp", "Error parsing fruits and vegetables or calories as double", e);
            }
        }
    }
    // Your existing calculateBMR method remains unchanged
    private void calculateBMR() {
        // Check if all required fields are filled
        if (TextUtils.isEmpty(nameEditText.getText()) || TextUtils.isEmpty(ageEditText.getText()) ||
                TextUtils.isEmpty(heightEditText.getText()) || TextUtils.isEmpty(weightEditText.getText()) ||
                TextUtils.isEmpty(selectedGender)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        // Extract values from the input fields
        String name = nameEditText.getText().toString();
        int age = Integer.parseInt(ageEditText.getText().toString());
        double height = Double.parseDouble(heightEditText.getText().toString());
        double weight = Double.parseDouble(weightEditText.getText().toString());

        // Calculate BMR based on gender
        double bmr;
        if (selectedGender.equals("Male")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else { // Assuming the gender is either "Male" or "Female"
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
        // Display the calculated BMR
        bmrTextView.setText(String.format(Locale.getDefault(), "BMR for %s: %.2f", name, bmr));
    }

    private void displaySelectedProteins(Spinner proteinSpinner, EditText proteinsValue, String mealName) {
        String selectedProtein = proteinSpinner.getSelectedItem() != null ?
                proteinSpinner.getSelectedItem().toString() : "null";
        Toast.makeText(this, mealName + " Protein: " + selectedProtein, Toast.LENGTH_SHORT).show();
    }
    private void displaySelectedCarbs(Spinner carbsFoodSpinner) {
        String selectedCarbsFood = carbsFoodSpinner.getSelectedItem() != null ?
                carbsFoodSpinner.getSelectedItem().toString() : "null";
        Toast.makeText(this, "Selected Carbs Food: " + selectedCarbsFood, Toast.LENGTH_SHORT).show();
        // You can use selectedCarbsFood as needed, e.g., update a TextView
    }
    private void displaySelectedFats(Spinner fatsFoodSpinner, String mealName) {
        String selectedFatsFood = fatsFoodSpinner.getSelectedItem() != null ?
                fatsFoodSpinner.getSelectedItem().toString() : "null";
        Toast.makeText(this, mealName + " Fats Food: " + selectedFatsFood, Toast.LENGTH_SHORT).show();
        // You can use selectedFatsFood as needed, e.g., update a TextView
    }
    private void displaySelectedFruitsVegetables(Spinner fruitsVegetablesSpinner, String mealName) {
        String selectedFruitsVegetables = fruitsVegetablesSpinner.getSelectedItem() != null ?
                fruitsVegetablesSpinner.getSelectedItem().toString() : "null";
        Toast.makeText(this, mealName + " Fruits and Vegetables: " + selectedFruitsVegetables, Toast.LENGTH_SHORT).show();
        // You can use selectedFruitsVegetables as needed, e.g., update a TextView
    }
    // Add this method to your MainActivity class
    // Add this method to your MainActivity class

    private void clearFields() {
        nameEditText.setText("");
        genderSpinner.setSelection(0);
        ageEditText.setText("");
        heightEditText.setText("");
        weightEditText.setText("");
        bmrIntakeEditText.setText("");
        noOfMealsEditText.setText("");

        // Clear all arrays of meal-related fields
        clearMealFields(proteinsValues);
        clearMealFields(carbsValues);
        clearMealFields(fatsValues);
        clearMealFields(fruitsVegetablesValues);
        clearMealFields(caloriesValues);
        clearMealFields(VegetablesValues);

        // Clear the BMR TextView
        bmrTextView.setText("Calculate BMR");

        meal1PTextViewResult.setText("");
        meal2PTextViewResult.setText("");
        meal3PTextViewResult.setText("");
        meal4PTextViewResult.setText("");
        meal5PTextViewResult.setText("");
        meal6PTextViewResult.setText("");

        meal1CTextViewResult.setText("");
        meal2CTextViewResult.setText("");
        meal3CTextViewResult.setText("");
        meal4CTextViewResult.setText("");
        meal5CTextViewResult.setText("");
        meal6CTextViewResult.setText("");

        meal1FTextViewResult.setText("");
        meal2FTextViewResult.setText("");
        meal3FTextViewResult.setText("");
        meal4FTextViewResult.setText("");
        meal5FTextViewResult.setText("");
        meal6FTextViewResult.setText("");

        meal1VTextViewResult.setText("");
        meal2VTextViewResult.setText("");
        meal3VTextViewResult.setText("");
        meal4VTextViewResult.setText("");
        meal5VTextViewResult.setText("");
        meal6VTextViewResult.setText("");

        meal1VVTextViewResult.setText("");
        meal2VVTextViewResult.setText("");
        meal3VVTextViewResult.setText("");
        meal4VVTextViewResult.setText("");
        meal5VVTextViewResult.setText("");
        meal6VVTextViewResult.setText("");

        meal1proteinGramsEditText.setText("");
        meal2ProteinGramsEditText.setText("");
        meal3ProteinGramsEditText.setText("");
        meal4ProteinGramsEditText.setText("");
        meal5ProteinGramsEditText.setText("");
        meal6ProteinGramsEditText.setText("");

        meal1CarbsGramEditText.setText("");
        meal2CarbsGramEditText.setText("");
        meal3CarbsGramEditText.setText("");
        meal4CarbsGramEditText.setText("");
        meal5CarbsGramEditText.setText("");
        meal6CarbsGramEditText.setText("");

        meal1FatsGramEditText.setText("");
        meal2FatsGramEditText.setText("");
        meal3FatsGramEditText.setText("");
        meal4FatsGramEditText.setText("");
        meal5FatsGramEditText.setText("");
        meal6FatsGramEditText.setText("");

        meal1FruitsGramEditText.setText("");
        meal2FruitsGramEditText.setText("");
        meal3FruitsGramEditText.setText("");
        meal4FruitsGramEditText.setText("");
        meal5FruitsGramEditText.setText("");
        meal6FruitsGramEditText.setText("");

        meal1VegetableGramEditText.setText("");
        meal2VegetableGramEditText.setText("");
        meal3VegetableGramEditText.setText("");
        meal4VegetableGramEditText.setText("");
        meal5VegetableGramEditText.setText("");
        meal6VegetableGramEditText.setText("");

    }
    // Clear fields related to a specific meal
    private void clearMealFields(EditText[] mealValues) {

        for (EditText editText : mealValues) {
            editText.setText("");
        }
    }
}
