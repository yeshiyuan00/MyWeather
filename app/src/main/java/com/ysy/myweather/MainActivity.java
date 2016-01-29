package com.ysy.myweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new PlaceholderFragment()).commit();
    }

    public static class PlaceholderFragment extends Fragment {

        private ListView listView;

        private String[] forcecastArray = new String[]{"Today-Sunny-88/63", "tomorrow-Foggy-70/46",
                "Wends-Cloudy-72/63", "Thurs-Rainy-64/51", "Fri-Foggy-70/46", "Sat-Sunny-76/68"};
        List<String> weekForcecast = new ArrayList<String>(Arrays.asList(forcecastArray));

        public PlaceholderFragment() {

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
             ArrayAdapter mForcecastAdapter = new ArrayAdapter(getActivity(), R.layout.list_item_forecast,
                    R.id.list_item_forecast_textview, weekForcecast);
            listView= (ListView) rootView.findViewById(R.id.listview_forecast);
            listView.setAdapter(mForcecastAdapter);
            return rootView;
        }
    }
}
