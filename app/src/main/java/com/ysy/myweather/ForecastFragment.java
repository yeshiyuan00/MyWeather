package com.ysy.myweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ysy.myweather.data.WeatherContract;

import java.util.ArrayList;

/**
 * User: ysy
 * Date: 2016/2/18
 * Time: 16:54
 */
public class ForecastFragment extends Fragment {

    private ForecastAdapter mForcecastAdapter;

    private ListView listView;

    public ForecastFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            updateWeather();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String locationSetting = Utility.getPreferredLocation(getActivity());

        // Sort order:  Ascending, by date.
        String sortOrder = WeatherContract.WeatherEntry.COLUMN_DATE + " ASC";
        Uri weatherForLocationUri = WeatherContract
                .WeatherEntry.buildWeatherLocationWithStartDate(locationSetting,
                        System.currentTimeMillis());
        Cursor cursor = getActivity().getContentResolver()
                .query(weatherForLocationUri, null, null, null, sortOrder);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mForcecastAdapter = new ForecastAdapter(getActivity(), cursor, 0);
        listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForcecastAdapter);

        return rootView;
    }

    private void updateWeather() {
        FetchWeatherTask fetchWeatherTask = new FetchWeatherTask(getActivity());
        String location = Utility.getPreferredLocation(getActivity());
        fetchWeatherTask.execute(location);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }


}
