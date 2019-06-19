package com.example.nihal.medeasy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.nihal.medeasy.Models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.hawk.Hawk;


public class SignupActivity extends AppCompatActivity {

    EditText UserName, Phone, Address, Occupation, FamilyHistoryLink, Weight, Height, PassWord, YearOfBirth;
    Button saveData;
    RadioButton male, female;
    String gender, type;
    FirebaseAuth auth;
    String UserPhoneInFireB;
    LinearLayout specialization_Section , id_section;
    EditText doc_id , specialization ;
    //  FirebaseDatabase database  ;
    // DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        UserName = findViewById(R.id.UserName);
        Phone = findViewById(R.id.Phone);
        YearOfBirth = findViewById(R.id.YearOfBirth);
        Address = findViewById(R.id.Address);
        Occupation = findViewById(R.id.Occupation);
        FamilyHistoryLink = findViewById(R.id.FamilyHistoryLink);
        Weight = findViewById(R.id.Weight);
        Height = findViewById(R.id.Height);
        PassWord = findViewById(R.id.PassWord);
        saveData = findViewById(R.id.SignUp);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        doc_id = findViewById(R.id.doc_id);
        specialization =findViewById(R.id.specialization);
        specialization_Section =findViewById(R.id.specialization_section);
        id_section =findViewById(R.id.Doctor_id_section);


        auth = FirebaseAuth.getInstance();

        //  database  = FirebaseDatabase.getInstance();
        // myRef = database.getReference("Users");

        saveData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final ProgressDialog loding = new ProgressDialog(SignupActivity.this);

                loding.setMessage("Loding please w8 ...");
                loding.setCancelable(false);

                if (male.isChecked()) {
                    gender = "0";
                } else {
                    gender = "1";
                }
                type = Hawk.get("type");
                 UserPhoneInFireB = Phone.getText().toString();
                final String PassWordInFireB = PassWord.getText().toString();
                final String UserNameInFireB = UserName.getText().toString();
                final String YearOfBirthInFireB = YearOfBirth.getText().toString();
                final String AddressInFireB = Address.getText().toString();
                final String OccupationInFireB = Occupation.getText().toString();
                final String FamilyHistoryLinkInFireB = FamilyHistoryLink.getText().toString();
                final String WeightInFireB = Weight.getText().toString();
                final String HeightInFireB = Height.getText().toString();

                if (UserNameInFireB.isEmpty()) {
                    Toast.makeText(SignupActivity.this, R.string.Toast_needName, Toast.LENGTH_SHORT).show();
                } else if (UserPhoneInFireB.isEmpty()) {
                    Toast.makeText(SignupActivity.this, R.string.Toast_needPhone, Toast.LENGTH_SHORT).show();
                }
//                else if (UserPhoneInFireB.length() != 11) {
//                    Toast.makeText(SignupActivity.this, R.string.Toast_invalidPhone, Toast.LENGTH_SHORT).show();
//                }
                else if (YearOfBirthInFireB.isEmpty()) {
                    Toast.makeText(SignupActivity.this, R.string.Toast_needYearOfBirth, Toast.LENGTH_SHORT).show();
                } else if (AddressInFireB.isEmpty()) {
                    Toast.makeText(SignupActivity.this, R.string.Toast_needAddress, Toast.LENGTH_SHORT).show();
                } else if (OccupationInFireB.isEmpty()) {
                    Toast.makeText(SignupActivity.this, R.string.Toast_needOccupation, Toast.LENGTH_SHORT).show();
                } else if (FamilyHistoryLinkInFireB.isEmpty()) {
                    Toast.makeText(SignupActivity.this, R.string.Toast_needFamilyHistoryLink, Toast.LENGTH_SHORT).show();
                } else if (WeightInFireB.isEmpty()) {
                    Toast.makeText(SignupActivity.this, R.string.Toast_needWeight, Toast.LENGTH_SHORT).show();
                } else if (HeightInFireB.isEmpty()) {
                    Toast.makeText(SignupActivity.this, R.string.Toast_needHeight, Toast.LENGTH_SHORT).show();
                } else if (PassWordInFireB.isEmpty()) {
                    Toast.makeText(SignupActivity.this, R.string.Toast_needPassword, Toast.LENGTH_SHORT).show();
                } else {

                    loding.show();
                    UserPhoneInFireB = UserPhoneInFireB + "@gmail.com";

                    auth.createUserWithEmailAndPassword(UserPhoneInFireB, PassWordInFireB).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                final UserModel user = new UserModel(
                                        UserNameInFireB+"",
                                        YearOfBirthInFireB+"",
                                        AddressInFireB+"",
                                        OccupationInFireB+"",
                                        FamilyHistoryLinkInFireB+"",
                                        WeightInFireB+"",
                                        HeightInFireB+"",
                                        PassWordInFireB+"",
                                        UserPhoneInFireB+"",
                                        "" + type
                                        , "" + gender
                                        ,""+FirebaseAuth.getInstance().getUid()
                                );

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getUid()).child("Info")
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {


                                            if (type.equals("2")) {


                                                loding.dismiss();
                                                Toast.makeText(SignupActivity.this, R.string.Toast_RegisterSuccessfull, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), DoctorLogin.class));

                                            } else {

                                                loding.dismiss();
                                                Toast.makeText(SignupActivity.this, R.string.Toast_RegisterSuccessfull, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                            }
                                        }

                                        /*else {
                                            loding.dismiss();
                                            if (task.getException().getMessage().equals("The email address is already in use by another account.")) {
                                                Toast.makeText(SignupActivity.this, "The phone is already in use by another user.", Toast.LENGTH_SHORT).show();
                                            } else
                                                Toast.makeText(SignupActivity.this, " Register Fail", Toast.LENGTH_SHORT).show();

                                        }*/
                                    }
                                });

                            } else {
                                if (task.getException().getMessage().equals("The email address is already in use by another account.")) {
                                    loding.dismiss();
                                    Toast.makeText(SignupActivity.this, R.string.Toast_PhoneAreUesd, Toast.LENGTH_SHORT).show();
                                } else
                                    loding.dismiss();
                                Toast.makeText(SignupActivity.this, R.string.Toast_RegisterFail, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }


            }
        });

    }

}

