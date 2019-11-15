package com.example.cats.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cats.R;
import com.example.cats.SearchAdapter;
import com.example.cats.model.Cats;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class SearchRecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;

    public SearchRecyclerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_searched);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final SearchAdapter searchAdapter = new SearchAdapter();
        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());
        String searchTextPass= getPrefs("SearchedText", getContext());
        System.out.println(searchTextPass+"here is the string");

        String url = "https://api.thecatapi.com/v1/breeds"+ "/search?q="+ searchTextPass;

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Cats[] catsArray = gson.fromJson(response, Cats[].class);
                List<Cats> objectsList = Arrays.asList(catsArray);
                searchAdapter.setData(objectsList);
                recyclerView.setAdapter(searchAdapter);
                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);

        return view;
    }

    public void updateRecyclerView(String url){

        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Cats[] catsArray = gson.fromJson(response, Cats[].class);
                List<Cats> objectsList = Arrays.asList(catsArray);
                searchAdapter.setData(objectsList);
                recyclerView.setAdapter(searchAdapter);
                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static String getPrefs(String key, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

}
