package com.example.tipcaculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.security.spec.ECField;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView amountTv;
    private TextView percentLabelTv;
    private TextView TipTv;
    private TextView totalTv;

    private double billamount = 0.0;
    private double percent = 0.15;

    private static final NumberFormat NumberCurrency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat NumberPercent = NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTv =( TextView)findViewById(R.id.amountTv);
        percentLabelTv=(TextView)findViewById(R.id.percentLabelTv);
        TipTv=(TextView)findViewById(R.id.TipTv);
        totalTv=(TextView)findViewById(R.id.totalTv);
        TipTv.setText(NumberCurrency.format(0));
        totalTv.setText(NumberPercent.format(0));

        final EditText amountEdt = (EditText) findViewById(R.id.amountEdt);
        amountEdt.addTextChangedListener(amountEdtWatcher);
        SeekBar percentSeekBar = ( SeekBar)findViewById(R.id.PercentSeebar);
        percentSeekBar.setOnSeekBarChangeListener(seekbarListener);



    }
    private void calculator (){
        double tip = billamount * percent;
        double total=billamount + tip;
        TipTv.setText(NumberCurrency.format(tip));
        totalTv.setText(NumberCurrency.format(total));
    }
    private  final TextWatcher amountEdtWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try
            {
                billamount = Double.parseDouble(s.toString())/100;
                amountTv.setText(NumberCurrency.format(billamount));
            }
            catch (NumberFormatException e)
            {
                amountTv.setText("");
                billamount=0.0;
            }
            calculator();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private  final SeekBar.OnSeekBarChangeListener seekbarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent=progress/100.0;
            percentLabelTv.setText(NumberPercent.format(percent));
            calculator();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
