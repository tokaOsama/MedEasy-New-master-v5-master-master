package com.example.nihal.medeasy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nihal.medeasy.Utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.hawk.Hawk;

public class LoginActivity extends AppCompatActivity {

    EditText loginPhone, loginPassWord;
    Button login;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPhone = findViewById(R.id.loginPhone);
        loginPassWord = findViewById(R.id.loginPassWord);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 final ProgressDialog loding = new ProgressDialog(LoginActivity.this);

                loding.setMessage("Loding please w8 ..."); // dh m4 btt7t fe el stringFile
                loding.setCancelable(false);

                String UserPhoneInFireB = loginPhone.getText().toString();
                String PassWordInFireB = loginPassWord.getText().toString();

                if (UserPhoneInFireB.isEmpty()) {
                    Toast.makeText(LoginActivity.this, R.string.Toast_needPhone, Toast.LENGTH_SHORT).show();
                } else if (PassWordInFireB.equals("")) {
                    Toast.makeText(LoginActivity.this, R.string.Toast_needPassword, Toast.LENGTH_SHORT).show();
                } else {
                    loding.show();
                    UserPhoneInFireB = UserPhoneInFireB + "@gmail.com";
                    auth.signInWithEmailAndPassword(UserPhoneInFireB, PassWordInFireB).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                FirebaseUser user = task.getResult().getUser();
                                loding.dismiss();
                                Hawk.put(Constants.userID,user.getUid());
                                databaseReference=FirebaseDatabase.getInstance().getReference();
                                databaseReference.child("Users").child(user.getUid()).child("Info").child("type").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String type = (String) dataSnapshot.getValue();
                                        Log.d("TTT", "onDataChange: "+type);
                                        if(type.equals("1")){
                                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                                        }else{
                                            startActivity(new Intent(LoginActivity.this, DoctorLogin.class));

                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            } else {
                                loding.show();
                                loding.dismiss();
                                Toast.makeText(LoginActivity.this, R.string.Toast_wrongPhonOrPassword, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }

    /**
     * to going to signUpForm
     **/

    public void signUpForm(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

}
