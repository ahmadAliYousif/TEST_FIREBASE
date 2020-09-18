package com.example.test_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Log_In extends AppCompatActivity {
    Button btn_log_in;
    Button btn_sign_out;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);
        btn_log_in = findViewById(R.id.button2);
        btn_sign_out = findViewById(R.id.btm_sign_out);
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                Task<AuthResult> resultTask = mAuth.signInAnonymously();
                resultTask.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getBaseContext(), "success log in ", Toast.LENGTH_LONG).show();
                    }
                });
                btn_sign_out.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAuth.signOut();
                    }
                });


            }
        });
    }

//    private void updateUI(FirebaseUser user) {
//        hideProgressBar();
//
//       // TextView idView = findViewById(R.id.anonymousStatusId);
//       // TextView emailView = findViewById(R.id.anonymousStatusEmail);
//        boolean isSignedIn = (user != null);
//
//        // Status text
//        if (isSignedIn) {
//            idView.setText(getString(R.string.id_fmt, user.getUid()));
//            emailView.setText(getString(R.string.email_fmt, user.getEmail()));
//        } else {
//            idView.setText(R.string.signed_out);
//            emailView.setText(null);
//        }
//
//        // Button visibility
//        findViewById(R.id.buttonAnonymousSignIn).setEnabled(!isSignedIn);
//        findViewById(R.id.buttonAnonymousSignOut).setEnabled(isSignedIn);
//        findViewById(R.id.buttonLinkAccount).setEnabled(isSignedIn);
//    }

}