package com.example.explorer;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;


public class ExplorevietnamhotelFragment extends Fragment {


    public ExplorevietnamhotelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explorevietnamhotel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView33 = getView().findViewById(R.id.imageView33);
        imageView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You selected this hotel", Toast.LENGTH_SHORT).show();
            }
        });
        AppCompatButton appCompatButton = getView().findViewById(R.id.appCompatButton);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Thanks for choosing", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), VietnamhotelActivity.class);
                startActivity(intent);

            }
        });

        TextView textView43 = getView().findViewById(R.id.textView43);
        textView43.setText("Miami Beachside..\nit is surrounded by greenery " +
                "a very good environment with pool so guest can live their lives to the fullest...");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.lightblueforindia));
        }
    }
}