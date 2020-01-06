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
public class MainActivity extends AppCompatActivity {

    private Firebase ref;
    public EditText e1, e2,e3;
    public Button b1, b2;
    public int coun=0;
    public int nof;
    static public int nof1;
    public void getnof()
    {
        Firebase.setAndroidContext(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        final MainActivity mm=new MainActivity();
        e1 = (EditText) findViewById(R.id.e1);
        e3 = (EditText) findViewById(R.id.e3);
        e2 = (EditText) findViewById(R.id.e2);
        b2 = (Button) findViewById(R.id.b2);
        b1 = (Button) findViewById(R.id.b1);
        Firebase rg=new Firebase("https://androidtrainee-7e562.firebaseio.com/no");
        rg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val=dataSnapshot.getValue(String.class);
                mm.nof=Integer.parseInt(val);
                //Toast.makeText(getApplicationContext(),"nof="+String.valueOf(mm.nof),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //Toast.makeText(getApplicationContext(),"nof1="+String.valueOf(mm.nof),Toast.LENGTH_LONG).show();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"nof1="+String.valueOf(mm.nof),Toast.LENGTH_LONG).show();
                final String user=e1.getText().toString().trim();
                    Toast.makeText(getApplicationContext(),"welcome "+user,Toast.LENGTH_LONG).show();
                    Firebase fm=new Firebase("https://androidtrainee-7e562.firebaseio.com/user/usernames/user"+String.valueOf(mm.nof+1));
                    Firebase fg=fm.child("name");
                    fg.setValue(user);
                    fg=fm.child("password");
                    fg.setValue(e2.getText().toString().trim());
                    Firebase fm1=new Firebase("https://androidtrainee-7e562.firebaseio.com/user/");
                    fg=fm1.child("no");
                    fg.setValue(mm.nof+1);
                    e3.setText("user"+String.valueOf(mm.nof+1));
            }
      /*  ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val=dataSnapshot.getValue(String.class);
                e1.setText(val);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/
        });

        b2=(Button)findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,login.class);
                startActivity(i);
            }
        });
    }
}

