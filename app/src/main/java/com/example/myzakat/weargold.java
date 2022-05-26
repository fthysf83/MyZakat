package com.example.myzakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class weargold extends AppCompatActivity implements View.OnClickListener {

    EditText etWeightW;
    EditText etCurrentValueW;
    Button btnCalculateW;
    Button btnResetW;
    TextView tvOutputW1;
    TextView tvOutputW2;
    TextView tvOutputW3;
    float weightgramW;
    float valuegramW;

    SharedPreferences sharedPrefW;
    SharedPreferences sharedPrefW2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weargold);

        etWeightW = (EditText) findViewById(R.id.etWeightW);
        etCurrentValueW = (EditText) findViewById(R.id.etCurrentValueW);
        btnCalculateW = (Button) findViewById(R.id.btnCalculateW);
        btnResetW = (Button) findViewById(R.id.btnResetW);
        tvOutputW1 = (TextView) findViewById(R.id.tvOutputW1);
        tvOutputW2 = (TextView) findViewById(R.id.tvOutputW2);
        tvOutputW3 = (TextView) findViewById(R.id.tvOutputW3);

        btnCalculateW.setOnClickListener(this);
        btnResetW.setOnClickListener(this);

        sharedPrefW = this.getSharedPreferences("weightW", Context.MODE_PRIVATE);
        weightgramW = sharedPrefW.getFloat("weightW", 0.0F);

        sharedPrefW2 = this.getSharedPreferences("valueW", Context.MODE_PRIVATE);
        valuegramW = sharedPrefW2.getFloat("valuew", 0.0F);
    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.aboutus:
                Toast.makeText(this, "This is about", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View viewW) {

        try {
            switch (viewW.getId()) {

                case R.id.btnCalculateW:
                    calculateW();
                    break;

                case R.id.btnResetW:
                    etWeightW.setText("");
                    etCurrentValueW.setText("");
                    tvOutputW1.setText("");
                    tvOutputW2.setText("");
                    tvOutputW3.setText("");
                    break;
            }
        }  catch (java.lang.NumberFormatException nfe) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();

        } catch (Exception exp) {
            Toast.makeText(this,"Unknown Exception" + exp.getMessage(),Toast.LENGTH_SHORT).show();

            Log.d("Exception",exp.getMessage());

        }
    }

    public void calculateW(){

        DecimalFormat df = new DecimalFormat("##.00");
        float weightgramW = 0;
        float valuegramW = 0;
        double totalvaluegoldW = 0.00;
        double zakatpayableW = 0.00;
        double totalzakatW = 0.00;

        SharedPreferences.Editor editor = sharedPrefW.edit();
        editor.putFloat("weight", weightgramW);
        editor.apply();
        SharedPreferences.Editor editor2 = sharedPrefW2.edit();
        editor2.putFloat("value", valuegramW);
        editor2.apply();

            int wear = 200;

            weightgramW = Float.parseFloat(etWeightW.getText().toString());
            valuegramW = Float.parseFloat(etCurrentValueW.getText().toString());
            totalvaluegoldW = weightgramW * valuegramW;

            zakatpayableW = valuegramW * (weightgramW - 200);
            if(zakatpayableW < 0.0)
            {
                totalzakatW = 0 * 0.025;
                tvOutputW1.setText("Total Value Gold: RM" + df.format(totalvaluegoldW));
                tvOutputW2.setText("Total Zakat Payable: RM0.00 because Zakat Payable is less than 0.");
                tvOutputW3.setText("Total Zakat : RM " + df.format(totalzakatW));
            }

            else
            {
                totalzakatW = zakatpayableW * 0.025;
                tvOutputW1.setText("Total Value Gold: RM" + df.format(totalvaluegoldW));
                tvOutputW2.setText("Total Zakat Payable: RM" + df.format(zakatpayableW));
                tvOutputW3.setText("Total Zakat : RM " + df.format(totalzakatW));
            }
        }
    }
