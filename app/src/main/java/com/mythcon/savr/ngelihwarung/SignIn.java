package com.mythcon.savr.ngelihwarung;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mythcon.savr.ngelihwarung.Common.Common;
import com.mythcon.savr.ngelihwarung.Model.User;

public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    Button btnSignIn;

    FirebaseDatabase database;
    DatabaseReference users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSignIn = (Button) findViewById(R.id.btn_signin);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(edtPhone.getText().toString(),edtPassword.getText().toString());
            }
        });
    }

    private void signInUser(final String phone, final String password) {
        final ProgressDialog dialog = new ProgressDialog(SignIn.this);
        dialog.setMessage("Please waiting...");
        dialog.show();

        final String localPhone = phone;
        final String localPassword = password;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(localPhone).exists()){
                    dialog.dismiss();
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setPhone(localPhone);
                    if (Boolean.parseBoolean(user.getIsStaff()))
                    {
                        if (user.getPass().equals(localPassword))
                        {
                            Intent login = new Intent(SignIn.this,Home.class);
                            Common.currentUser = user;
                            startActivity(login);
                            finish();

                        }else {
                            Toast.makeText(SignIn.this, "Wrong Password !!!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignIn.this, "Please login using staff account", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    dialog.dismiss();
                    Toast.makeText(SignIn.this, "User doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
