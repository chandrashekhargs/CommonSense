package com.commonsense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
//    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.button_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText fullnameedit = (EditText)findViewById(R.id.full_name);
                final String content = fullnameedit.getText().toString();

                EditText phoneedit = (EditText)findViewById(R.id.phone);
                final String phone_content = phoneedit.getText().toString();

                EditText emailedit = (EditText)findViewById(R.id.register_email);
                final String email_content = emailedit.getText().toString();

                EditText dobedit = (EditText)findViewById(R.id.dob);
                final String dob_content = dobedit.getText().toString();

                EditText genderedit = (EditText)findViewById(R.id.gender);
                final String gender_content = genderedit.getText().toString();

                EditText passwordedit = (EditText)findViewById(R.id.password123);
                final String password_content = passwordedit.getText().toString();

                if(content.isEmpty()){
                    fullnameedit.setError("Full name Required");
                    fullnameedit.requestFocus();
                    return;
                }
                if(phone_content.isEmpty()){
                    phoneedit.setError("Phone Required");
                    phoneedit.requestFocus();
                    return;
                }
                else if(!Patterns.PHONE.matcher(phone_content).matches()){
                    phoneedit.setError("Invalid Number");
                    phoneedit.requestFocus();
                    return;
                }
                if(dob_content.isEmpty()){
                    dobedit.setError("Date of Birth Required");
                    dobedit.requestFocus();
                    return;
                }
                if(gender_content.isEmpty()){
                    genderedit.setError("Gender Required");
                    genderedit.requestFocus();
                    return;
                }
                if(password_content.isEmpty() || password_content.length()<8){
                    passwordedit.setError("Password must be atleast 8 Characters");
                    passwordedit.requestFocus();
                    return;
                }
               if(email_content.isEmpty()){
                   emailedit.setError("Email Required");
                   emailedit.requestFocus();
                   return;
               }
               else if(!Patterns.EMAIL_ADDRESS.matcher(email_content).matches()){
                   emailedit.setError("Invalid Email");
                   emailedit.requestFocus();
                   return;
               }
               register_user(content,phone_content,email_content,dob_content,gender_content,password_content);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean register_user(final String content, final String phone_content, final String email_content, final String dob_content, final String gender_content, final String password_content){
//        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email_content,password_content).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(content,phone_content,dob_content,gender_content,email_content,password_content);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
//                                progressBar.setVisibility(View.GONE);
                                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                                String uid = currentFirebaseUser.getUid();
                                Toast.makeText(getApplicationContext(),"" + currentFirebaseUser.getUid(),Toast.LENGTH_LONG).show();
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("user", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("userid", uid);
                                editor.commit();
                                Intent i = new Intent(SignupActivity.this,MainActivity.class);
                                startActivity(i);

                            }
                            else{
//                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                } else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        return true;
    }
}
