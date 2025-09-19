package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CityArrayAdapter extends ArrayAdapter<City> {

    public CityArrayAdapter(Context context, List<City> cities) {
        super(context, 0, cities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.content, parent, false);
        }

        City city = getItem(position);

        TextView cityName = view.findViewById(R.id.city_text);
        TextView province = view.findViewById(R.id.province_text);

        if (city != null) {
            cityName.setText(city.getCity());
            province.setText(city.getProvince());
        }

        return view;
    }
}
