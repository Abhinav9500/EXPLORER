package com.example.explorer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class PaymentconfirmActivity extends AppCompatActivity {
    Button button;
    String SECRECT_KEY = "sk_test_51M7Dt5SAAlyebV6dRrXNoLFLdJqyUJmrvbntZyinoD2V5qiKxSzWUBDPy64RxRIvOxkx7qhMibbA6OwMSlObjosn008NnERRhl";
    String PUBLISH_KEY = "pk_test_51M7Dt5SAAlyebV6d35X0OpbcuqfF9LSx3fvDheg03VM5qw03XUIzbXzzqYcRbGa5syk9WufFqWVl7Mh3gcqcvLDL00OamufWmB";
    PaymentSheet paymentSheet;

    String customerID;
    String EphericalKey;
    String ClientSecret;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentconfirm);
        button = findViewById(R.id.pay);

        PaymentConfiguration.init(this, PUBLISH_KEY);
        paymentSheet = new PaymentSheet(this, paymentSheetResult -> {

            onPaymentResult(paymentSheetResult);


        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PaymentFlow();

            }
        });

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/customers",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            customerID = object.getString("id");
                            Toast.makeText(PaymentconfirmActivity.this, customerID, Toast.LENGTH_SHORT).show();
                            getEphericalKey(customerID);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer" + SECRECT_KEY);
                return header;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(PaymentconfirmActivity.this);
        requestQueue.add(stringRequest);
    }

    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {

        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {

            Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEphericalKey(String customerID) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                " https://api.stripe.com/v1/ephemeral_keys",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            EphericalKey = object.getString("id");
                            Toast.makeText(PaymentconfirmActivity.this, EphericalKey, Toast.LENGTH_SHORT).show();
                            getClientSecret(customerID, EphericalKey);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer" + SECRECT_KEY);
                header.put("Stripe-Version", "2022-08-01");
                return header;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("customer", customerID);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(PaymentconfirmActivity.this);
        requestQueue.add(stringRequest);


    }

    private void getClientSecret(String customerID, String ephericalKey) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                " https://api.stripe.com/v1/payment_intents",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            ClientSecret = object.getString("client_secret");
                            Toast.makeText(PaymentconfirmActivity.this, ClientSecret, Toast.LENGTH_SHORT).show();

                           /*
                            PaymentFlow();

                            */

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer" + SECRECT_KEY);

                return header;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("customer", customerID);
                params.put("amount", "60" + "00");
                params.put("currency", "usd");
                params.put("automatic_payment_methods[enabled]", "true");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(PaymentconfirmActivity.this);
        requestQueue.add(stringRequest);
    }

    private void PaymentFlow() {

        paymentSheet.presentWithPaymentIntent(
                ClientSecret, new PaymentSheet.Configuration("Team EXPLORER",
                        new PaymentSheet.CustomerConfiguration
                        (customerID, EphericalKey))
        );
    }
}
