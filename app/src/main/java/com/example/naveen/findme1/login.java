package com.example.naveen.findme1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class login extends AppCompatActivity {

    public EditText e1, e2;
    public Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=(EditText)findViewById(R.id.e4);
        Firebase.setAndroidContext(this);
        e2=(EditText)findViewById(R.id.e5);
        b1=(Button)findViewById(R.id.b3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String uname=e1.getText().toString().trim();
                final String pass=e2.getText().toString().trim();
                Firebase fm=new Firebase("https://androidtrainee-7e562.firebaseio.com/user/usernames/"+uname+"/password");
                fm.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        String pass1=dataSnapshot.getValue(String.class);
                        if(pass.equals(pass1))
                        {
                            Toast.makeText(getApplicationContext(),pass+" "+pass1,Toast.LENGTH_LONG).show();
                            Intent i=new Intent(login.this,MapsActivity.class);
                            i.putExtra("name",uname);
                            startActivity(i);

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"dont have an account ??",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(login.this,MainActivity.class);
                            startActivity(i);
                        }
                       // Firebase fm1=new Firebase("https://androidtrainee-7e562.firebaseio.com/user/usernames/"+e1+"/password");
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError)
                    {

                    }
                });
            }
        });
    }
}
