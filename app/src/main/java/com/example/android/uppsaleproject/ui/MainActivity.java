package com.example.android.uppsaleproject.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.uppsaleproject.Networking.WebServiceClient;
import com.example.android.uppsaleproject.R;
import com.example.android.uppsaleproject.model.UserDetails;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView welcomeTextView;
    private Button entryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        entryButton = findViewById(R.id.entry_button);
        
        entryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkNetworkAvailability()) {

                    Call<UserDetails> call = WebServiceClient.getWebServiceClient()
                            .getUppSaleService().getUserDetails(getString(R.string.token));

                    call.enqueue(new Callback<UserDetails>() {
                        @Override
                        public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {

                            if(response.isSuccessful()){
                                UserDetails userDetails = response.body();
                                showWelcomeUi(userDetails.getDisplayName());
                            }
                            else {
                              String code = String.valueOf(response.code());
                              showAlertDialog(  getString(R.string.alert_error_code_message, code));
                            }
                        }

                        @Override
                        public void onFailure(Call<UserDetails> call, Throwable t) {
                            showAlertDialog(getString(R.string.alert_error_message));
                            Log.d(TAG, "onFailure: " + t.getCause());
                        }
                    });
                }
                else
                   showAlertDialog(getString(R.string.alert_network_error_message));
            }
        });


    }

    /*
    return boolean that represent device network state: connected / disconnected
    */
    private boolean checkNetworkAvailability() {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        Log.d(TAG, "checkNetworkAvailability: " + isConnected);
       return isConnected;
    }

    /*
    show welcome message with user name and hide entry button
    */
    private void showWelcomeUi(String userName) {
        welcomeTextView.setText
                (getString(R.string.welcome_message_f, userName));
        welcomeTextView.setVisibility(View.VISIBLE);

        entryButton.setVisibility(View.GONE);
    }

    /*
    display alert dialog
    */
    private void showAlertDialog(String errorMessage){

        AlertDialogFragment dialogFragment = AlertDialogFragment.newInstance(errorMessage);
        dialogFragment.show(getSupportFragmentManager(), "error_dialog");
    }

    /*

    */



}
