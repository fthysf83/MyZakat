package com.example.myzakat;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnKeep;
    Button btnWear;
    ImageView imageView;
    Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnKeep = (Button) findViewById(R.id.btnKeep);
        btnWear = (Button) findViewById(R.id.btnWear);

        btnKeep.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,keepgold.class);
                startActivity(intent);

            }
        });

        btnWear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,weargold.class);
                startActivity(intent);

            }
        });

        imageView=findViewById(R.id.imageView);
        drawable=getResources().getDrawable(R.drawable.myzakatlogo);
        imageView.setImageDrawable(drawable);
    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutus:
                // Toast.makeText(this, "This is about",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, aboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
