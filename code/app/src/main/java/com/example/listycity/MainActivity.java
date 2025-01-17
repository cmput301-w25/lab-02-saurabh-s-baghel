/*
 * Lab 02 - Due 17th Jan Fri 2025
 * CMPUT 301 - LEC B1 (LAB H03)
 * Author: Saurabh Singh Baghel
 */

package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Variables to be used as references later
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    EditText editList;      // variable to store the input city
    String selectedCity = null;    // Variable to store a city selected to delete

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Vancouver", "Toronto", "Moscow", "Sydney"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        // LAB 2 Participation Exercise
        editList = findViewById(R.id.new_city);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCity = dataList.get(i);
            }
        });

        // When the add button is pressed it makes the input block visible so that user can put in Input Text
        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout addCityBlock = findViewById(R.id.add_city_block);
                addCityBlock.setVisibility(View.VISIBLE);
            }
        });

        // Confirm Button which adds the new city to the data list

        Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = editList.getText().toString().trim();    // Get the text using edit text
                if (!city.isEmpty()) {          // If the city item is not empty then add it to the list
                    dataList.add(city);
                    cityAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, city + " added to the list", Toast.LENGTH_LONG).show();
                    editList.setText("");       // Empty the input text box
                    LinearLayout addCityBlock = findViewById(R.id.add_city_block);
                    addCityBlock.setVisibility(View.GONE);      // Make the block invisible after the city has been added
                }
            }
        });


        // Function to delete the city
        Button removeButton = findViewById(R.id.remove_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedCity != null) {         // Check if the city is selected and then remove from the list
                    dataList.remove(selectedCity);
                    Toast.makeText(MainActivity.this, selectedCity + " removed from the list", Toast.LENGTH_LONG).show();
                    cityAdapter.notifyDataSetChanged();

                }
            }
        });
    }
}