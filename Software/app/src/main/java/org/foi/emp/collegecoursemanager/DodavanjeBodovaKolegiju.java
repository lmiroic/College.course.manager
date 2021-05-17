package org.foi.emp.collegecoursemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DodavanjeBodovaKolegiju extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_bodova_kolegiju);
        Intent i=getIntent();
        if(i!=null&&i.getIntExtra("ID_kolegija",0)!=0){
            Toast.makeText(getApplicationContext(),"Ko kumi",Toast.LENGTH_SHORT).show();
        }
    }
}