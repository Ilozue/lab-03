package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements AddCityFragment.OnCitySavedListener {

    private ArrayList<City> cities;
    private CityArrayAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.city_list);
        FloatingActionButton fab = findViewById(R.id.button_add_city);


        String[] cityNames = { "Edmonton", "Vancouver", "Toronto" };
        String[] provinces = { "AB", "BC", "ON" };

        cities = new ArrayList<>();
        for (int i = 0; i < cityNames.length; i++) {
            cities.add(new City(cityNames[i], provinces[i]));
        }

        adapter = new CityArrayAdapter(this, cities);
        listView.setAdapter(adapter);


        fab.setOnClickListener(v -> {
            AddCityFragment dialog = AddCityFragment.newInstance(null);
            dialog.show(getSupportFragmentManager(), "AddCity");
        });


        listView.setOnItemClickListener((parent, view, position, id) -> {
            City toEdit = cities.get(position);
            AddCityFragment dialog = AddCityFragment.newInstance(toEdit);
            dialog.show(getSupportFragmentManager(), "EditCity");
        });
    }


    @Override
    public void onCityAdded(City city) {
        cities.add(city);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCityUpdated(City updated) {
        int pos = cities.indexOf(updated);
        if (pos >= 0) {
            cities.set(pos, updated);
        }
        adapter.notifyDataSetChanged();
    }
}
