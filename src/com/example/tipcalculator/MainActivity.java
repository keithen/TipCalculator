package com.example.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "TipCalculator MainActivity";
	
	private double billAmount;
	private double tipAmount;
	private double tipRate = .15;

	TextView tipTextView;		// output
	EditText billEditText;		// input

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tipTextView = (TextView) findViewById(R.id.tipValue);
		billEditText = (EditText) findViewById(R.id.billValue);
		billEditText.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		    	// Don't have to do anything here because afterTextChanged() will be called
		    	// immediately after this.
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count,
		            int after) {
		        // Fires right before text is changing
		    }

		    @Override
		    public void afterTextChanged(Editable s) {
				 Log.d(TAG, "afterTextChanged s = " + s);
				 tipAmount = calculateTip();
				 displayTip(tipAmount);
		    }
		});
		
	}

	 private double calculateTip() {
		 try {
			 // If we just deleted bill string, we get NumberFormatException from
			 // parseDouble().
			 String strBillValue = billEditText.getText().toString();
			 billAmount = Double.parseDouble(strBillValue); 
		 } catch(Exception e) {
			 Log.d(TAG, "calculateTip exception = " + e.toString());
			 billAmount = 0;
		 }
		 double tip = billAmount * tipRate;
		 return tip;
	 }
		
		private void displayTip(double tip) {
			 tipTextView.setText(String.format("%.2f", tip));
	 }
		
	 public void on10PercentClick(View view) {
		 tipRate = .10;
		 tipAmount = calculateTip();
		 displayTip(tipAmount);
	 }
		
	 public void on15PercentClick(View view) {
		 tipRate = .15;
		 tipAmount = calculateTip();
		 displayTip(tipAmount);
	 }
		
	 public void on20PercentClick(View view) {
		 tipRate = .20;
		 tipAmount = calculateTip();
		 displayTip(tipAmount);
	 }
}
