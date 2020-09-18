package com.example.test_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// fire Store
public class MainActivity extends AppCompatActivity {
    Button add_user;
    ArrayList<Det> arrayList;
    RecyclerView recyclerView;
    Rec_Fire rec_fire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_user = findViewById(R.id.button);
        recyclerView = findViewById(R.id.rec);

        arrayList = new ArrayList<>();
        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                arrayList.add(documentSnapshot.toObject(Det.class));
                                //Det s = documentSnapshot.toObject(Det.class);
                                //Toast.makeText(getBaseContext(), s.getName(), Toast.LENGTH_SHORT).show();

//                               Toast.makeText(getBaseContext(), documentSnapshot.getString("name"), Toast.LENGTH_SHORT).show();

                            }
                            rec_fire = new Rec_Fire(arrayList, getBaseContext());
                            RecyclerView.LayoutManager l = new GridLayoutManager(getBaseContext(), 1);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(l);
                            recyclerView.setAdapter(rec_fire);
                            rec_fire.notifyDataSetChanged();

                        }
                    }
                });


//                Map<String, Object> user = new HashMap<>();// هاش ماب لاضافة حقول للداتا بيز ,..
//                user.put("id", 1301181207);
//                user.put("name", "ahmed");
//                user.put("age", 21);
//
//                db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(getBaseContext(), "Sucsess", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getBaseContext(), "faild", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
            }
        });

    }
}