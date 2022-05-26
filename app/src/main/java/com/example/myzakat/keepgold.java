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

public class keepgold extends AppCompatActivity implements View.OnClickListener {
    EditText etWeight;
    EditText etCurrentValue;
    Button btnCalculate;
    Button btnReset;
    TextView tvOutput1;
    TextView tvOutput2;
    TextView tvOutput3;
    float weightgram;
    float valuegram;

    SharedPreferences sharedPref;
    SharedPreferences sharedPref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keepgold);

        etWeight = (EditText) findViewById(R.id.etWeight);
        etCurrentValue = (EditText) findViewById(R.id.etCurrentValue);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnReset = (Button) findViewById(R.id.btnReset);
        tvOutput1 = (TextView) findViewById(R.id.tvOutput1);
        tvOutput2 = (TextView) findViewById(R.id.tvOutput2);
        tvOutput3 = (TextView) findViewById(R.id.tvOutput3);

        btnCalculate.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        sharedPref = this.getSharedPreferences("weight", Context.MODE_PRIVATE);
        weightgram = sharedPref.getFloat("weight", 0.0F);

        sharedPref = this.getSharedPreferences("value", Context.MODE_PRIVATE);
        valuegram = sharedPref.getFloat("value", 0.0F);

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
    public void onClick(View view) {

        try {

            switch (view.getId()) {

                case R.id.btnCalculate:
                    calculate();
                    break;

                case R.id.btnReset:
                    etWeight.setText("");
                    etCurrentValue.setText("");
                    tvOutput1.setText("");
                    tvOutput2.setText("");
                    tvOutput3.setText("");
                    break;

            }
        }catch (java.lang.NumberFormatException nfe) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();

        } catch (Exception exp) {
            Toast.makeText(this,"Unknown Exception" + exp.getMessage(),Toast.LENGTH_SHORT).show();

            Log.d("Exception",exp.getMessage());

        }

    }

    public void calculate(){

        DecimalFormat df = new DecimalFormat("##.00");
        float weightgram = 0;
        float valuegram = 0;
        double totalvaluegold = 0.00;
        double zakatpayable = 0.00;
        double totalzakat = 0.00;

        //saving data
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("weight", weightgram);
        editor.apply();
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putFloat("value", valuegram);
        editor2.apply();
        
            int keep = 85;

            weightgram = Float.parseFloat(etWeight.getText().toString());
            valuegram = Float.parseFloat(etCurrentValue.getText().toString());
            totalvaluegold = weightgram * valuegram;

            zakatpayable = valuegram * (weightgram - keep);
            if(zakatpayable < 0.0)
            {
                totalzakat = 0 * 0.025;
                tvOutput1.setText("Total Value Gold: RM" + df.format(totalvaluegold));
                tvOutput2.setText("Total Zakat Payable: RM" + df.format (zakatpayable) + " or RM0.00 because Zakat Payable is less than 0.");
                tvOutput3.setText("Total Zakat : RM " + df.format(totalzakat));
            }

            else {
                totalzakat = zakatpayable * 0.025;
                tvOutput1.setText("Total Value Gold: RM" + df.format(totalvaluegold));
                tvOutput2.setText("Total Zakat Payable: RM" + df.format(zakatpayable));
                tvOutput3.setText("Total Zakat : RM " + df.format(totalzakat));
            }
        }
    }
