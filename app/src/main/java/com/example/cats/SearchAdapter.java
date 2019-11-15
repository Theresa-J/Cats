package com.example.cats;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cats.activites.CatsProfileActivity;
import com.example.cats.model.Cats;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>  {

    private List<Cats> catsToAdapt;

    public void setData(List<Cats> catsToAdapt) {

        this.catsToAdapt = catsToAdapt;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cats, parent, false);

        SearchViewHolder searchViewHolder = new SearchViewHolder(view);
        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        final Cats catsAtPosition = catsToAdapt.get(position);

        holder.breedTextView.setText(catsAtPosition.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, CatsProfileActivity.class);
                intent.putExtra("CatsID", catsAtPosition.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView breedTextView;

        public SearchViewHolder(View v) {
            super(v);

            view = v;
            breedTextView = v.findViewById(R.id.breed);
        }

    }
}
