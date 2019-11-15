package com.example.cats.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cats.R;
import com.example.cats.SearchAdapter;
import com.example.cats.activites.MainActivity;
import com.example.cats.model.Cats;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;


public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    ImageButton searchButton;
    EditText searchText;
//    DataPassListener mCallback;
//
//    public interface DataPassListener{
//        public void passData(String data);
//    }

//    @Override
//    public void onAttach(Context context)
//    {
//        super.onAttach(context);
//        try
//        {
//            mCallback = (DataPassListener) context;
//        }
//        catch (ClassCastException e)
//        {
//            throw new ClassCastException(context.toString()+ " must implement OnImageClickListener");
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.rv_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final SearchAdapter searchAdapter = new SearchAdapter();
        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());
        String url = "https://api.thecatapi.com/v1/breeds";

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

        searchText = view.findViewById(R.id.search);
        final String searchTextPass = searchText.getText().toString();
        searchButton = view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//
//                SearchRecyclerFragment srFrag = new SearchRecyclerFragment();
//                Bundle bundle = new Bundle();
                editPrefs("SearchedText", searchTextPass, getContext());
//                srFrag.setArguments(bundle);

//                Intent intent = new Intent(getActivity().getBaseContext(),
//                        MainActivity.class);
//                intent.putExtra("SearchedText", searchTextPass);
//                getActivity().startActivity(intent);

                FragmentTransaction fr =getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_slot,new SearchRecyclerFragment());
                fr.commit();
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public static void editPrefs(String key, String value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }


}
