package com.example.explorer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class PaymentconfirmActivity extends AppCompatActivity {
    Button button;
    String SECRECT_KEY="sk_test_51LYozXSD7KPt15fuXMfLVktu68RJ4hw7e4WhLsC5nLvO7FO3e3KV65AruVqKqFjyzE5seX7w0aU4NVuGTRW7NvhH00Y7lucFvO";
    String PUBLISH_KEY="pk_test_51LYozXSD7KPt15fu7JjjUIvYwXoB9YLpVU5JxUCVVR9AIXuaIWuTsix2Q73ONaTgCGqZS9mcTEmhu9qT1WTwuWAW00LSQlsTqi";
    PaymentSheet paymentSheet;

    String customerID;
    String EphericalKey;
    String ClientSecret;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentconfirm);
        button=findViewById(R.id.pay);

        PaymentConfiguration.init(this,PUBLISH_KEY);
        paymentSheet=new PaymentSheet(this,paymentSheetResult -> {

            onPaymentResult(paymentSheetResult);


        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentFlow();
            }
        });

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/customers",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            customerID=object.getString("id");
                            Toast.makeText(PaymentconfirmActivity.this,customerID,Toast.LENGTH_SHORT).show();
                            getEphericalKey(customerID);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header=new HashMap<>();
                header.put("Authorization","Bearer"+SECRECT_KEY);
                return header;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(PaymentconfirmActivity.this);
        requestQueue.add(stringRequest);



    }

    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {

        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {

            Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEphericalKey(String customerID) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                " https://api.stripe.com/v1/ephemeral_keys",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            EphericalKey=object.getString("id");
                            Toast.makeText(PaymentconfirmActivity.this,EphericalKey,Toast.LENGTH_SHORT).show();
                            getClientSecret(customerID,EphericalKey);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header=new HashMap<>();
                header.put("Authorization","Bearer"+SECRECT_KEY);
                header.put("Stripe-Version","2022-08-01");

                return header;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("customer",customerID);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(PaymentconfirmActivity.this);
        requestQueue.add(stringRequest);


    }

    private void getClientSecret(String customerID, String ephericalKey) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                " https://api.stripe.com/v1/payment_intents",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            ClientSecret=object.getString("client_secret");
                            Toast.makeText(PaymentconfirmActivity.this,ClientSecret,Toast.LENGTH_SHORT).show();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header=new HashMap<>();
                header.put("Authorization","Bearer"+SECRECT_KEY);

                return header;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("customer",customerID);
                params.put("amount","60"+"00");
                params.put("currency","USD");
                params.put("automatic_payment_methods[enabled]","true");

                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(PaymentconfirmActivity.this);
        requestQueue.add(stringRequest);
    }

    private void PaymentFlow() {

        paymentSheet.presentWithPaymentIntent(
                ClientSecret,new PaymentSheet.Configuration("EXPLORER"
                        ,new PaymentSheet.CustomerConfiguration(
                        customerID,
                        EphericalKey
                ))
        );
    }
}
