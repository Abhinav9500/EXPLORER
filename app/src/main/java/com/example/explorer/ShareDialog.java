package com.example.explorer;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

class ShareDialog extends Dialog {
    ImageView cancel_button;

    public ShareDialog(@NonNull Context context, int shareDialogStyle) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_dialog_layout);
        final AppCompatButton LinkButton = findViewById(R.id.LINKBUTTON);
        final AppCompatButton APKButton = findViewById(R.id.APKBUTTON);
        cancel_button = findViewById(R.id.cancel_button);


        LinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkgeneratorMethod(getContext());
            }
        });

        APKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APKgeneratorMethod(getContext());

            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void APKgeneratorMethod(Context context) {
        Intent intent = new Intent(context, ShareapkActivity.class);
        context.startActivity(intent);
    }


    private void LinkgeneratorMethod(Context context) {

        Intent intent = new Intent(context, DynamiclinkgeneratorActivity.class);
        context.startActivity(intent);


    }

}

