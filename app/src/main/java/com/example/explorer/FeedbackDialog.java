package com.example.explorer;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

class FeedbackDialog extends Dialog {
    ImageView cancel_button3;
    public FeedbackDialog(@NonNull Context context, int shareDialogStyle) {
        super(context);
    }

    public FeedbackDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_dialog_layout);
        final AppCompatButton SendButton = findViewById(R.id.SENDBUTTON);
        cancel_button3=findViewById(R.id.cancel_button3);


        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackMethod(getContext());
            }
        });
        cancel_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void FeedbackMethod(Context context) {
        Intent intent= new Intent(Intent.ACTION_SENDTO);
        String UriText="mailto:"+ Uri.encode("teamexplorer766@gmail.com")+"?subject"+
                Uri.encode("Give us feedback")+ "$body" +Uri.encode("");
        Uri uri =Uri.parse(UriText);
        intent.setData(uri);
        context.startActivity(Intent.createChooser(intent,"send mail"));


    }

}

