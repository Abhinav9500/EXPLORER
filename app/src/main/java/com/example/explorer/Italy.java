package com.example.explorer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import java.util.zip.Inflater;

public class Italy extends Fragment {
    Inflater inflater;
    AppCompatButton appCompatButton;

    public Italy() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_italy, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appCompatButton=getView().findViewById(R.id.exploreitalybutton);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Thanks for choosing Italy", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),ExploreItalyActivity.class);
                startActivity(intent);
            }
        });
    }
}






