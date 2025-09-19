package com.example.listycitylab3;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    private static final String ARG_CITY = "city";
    private City editingCity;

    public static AddCityFragment newInstance(@Nullable City city) {
        Bundle args = new Bundle();
        if (city != null) args.putSerializable(ARG_CITY, city);
        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnCitySavedListener {
        void onCityAdded(City city);
        void onCityUpdated(City updated);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater()
                .inflate(R.layout.fragment_add_city, null);

        EditText cityEt = view.findViewById(R.id.edit_text_city_text);
        EditText provEt = view.findViewById(R.id.edit_text_province_text);


        Bundle args = getArguments();
        if (args != null) {
            Object obj = args.getSerializable(ARG_CITY);
            if (obj instanceof City) editingCity = (City) obj;
        }

        if (editingCity != null) {
            cityEt.setText(editingCity.getCity());
            provEt.setText(editingCity.getProvince());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view)
                .setTitle(editingCity == null ? "Add City" : "Edit City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (dialog, which) -> {
                    String name = cityEt.getText().toString().trim();
                    String prov = provEt.getText().toString().trim();

                    OnCitySavedListener host = (OnCitySavedListener) requireActivity();
                    if (editingCity == null) {
                        host.onCityAdded(new City(name, prov));
                    } else {
                        editingCity.setCity(name);
                        editingCity.setProvince(prov);
                        host.onCityUpdated(editingCity);
                    }
                });

        return builder.create();
    }
}
