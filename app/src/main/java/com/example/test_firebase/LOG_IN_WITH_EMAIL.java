package com.example.test_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LOG_IN_WITH_EMAIL extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_o_g__i_n__w_i_t_h__e_m_a_i_l);
        mAuth = FirebaseAuth.getInstance();
        tv = findViewById(R.id.tv);
    }
    
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = mAuth.getCurrentUser();
        log_in();
//        setInRealTimeUsers();
//        updatename(user);

    }

    private void updatename(FirebaseUser user) {
        //updating user's profile data
        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName("NMT")
                .build();

        user.updateProfile(profileUpdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

    } //لادخال بيانات المستخدم

    private void sign_up() {

        mAuth.createUserWithEmailAndPassword("ahmedali2113@robrab.com", "password213")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LOG_IN_WITH_EMAIL.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });


    }//للتسجيل

    private void log_in() {

        mAuth.signInWithEmailAndPassword("ahmedali2113@robrab.com", "password213")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), user.getDisplayName(), Toast.LENGTH_SHORT).show();
//                            setInRealTimeUsers(user.getDisplayName());
//                            getInRealTimeUsers();

                            getInRealTimeUsers();
                        } else {
                            Toast.makeText(LOG_IN_WITH_EMAIL.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }//للدخول

    public void setInRealTimeUsers(String name, int id) {

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("my_users");
//int id, String name, int age, String address, String email, String phone
        reference.child(name).setValue(new Det(id, "AHMAD", 20, "GAZA", "EMAI@gn.com", "059999999"));
    }//اضافة بيانات


    public void getInRealTimeUsers() {

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("my_users").child("NMT");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Det value = dataSnapshot.getValue(Det.class);
                tv.setText(" address : " + value.getAddress() + "\n" + "age : " + value.getAge() + "\n"
                        + " email  : " + value.getEmail() + "\n" + " id  : " + value.getId() + "\n" + " name  : " + value.getName() + "\n"
                        + " phone  : " + value.getPhone());

//                Toast.makeText(getApplicationContext(), value.getAge() + "" + value.getId() + value.getName(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }//جلب البيانات

}