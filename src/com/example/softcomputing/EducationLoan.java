package com.example.softcomputing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EducationLoan extends Activity implements OnClickListener {

	Button ind, abr, go, sk;
	TextView ln, rate, lo, fe, fe2, tv1, tv2;
	EditText amt, ten;
	double val = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salient);
		fe = (TextView) findViewById(R.id.tvsal);
		fe2 = (TextView) findViewById(R.id.tvsal2);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		tv1.setText("");
		tv2.setText("");
		sk = (Button) findViewById(R.id.bsali);
		fe2.setText("");
		fe.setText("Expenses considered for loan\n"
				+ "•	Fees payable to college/school/hostel\n"
				+ "•	Examination/Library/Laboratory fees\n"
				+ "•	Purchase of Books/Equipment/Instruments/Uniforms, Purchase of computers- essential for completion of the course (maximum 20% of the total tuition fees payable for completion of the course)\n"
				+ "•	Caution Deposit/Building Fund/Refundable Deposit (maximum 10% tuition fees for the entire course)\n"
				+ "•	Travel Expenses/Passage money for studies abroad");
		sk.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.bsali:
			setContentView(R.layout.eduloan);
			ind = (Button) findViewById(R.id.bind);
			abr = (Button) findViewById(R.id.babr);
			ind.setOnClickListener(this);
			abr.setOnClickListener(this);
			break;
		case R.id.bind:
			setContentView(R.layout.edu);
			ln = (TextView) findViewById(R.id.tveloan);
			ln.setText("10 lakhs");
			go = (Button) findViewById(R.id.bego);
			val = 10;
			go.setOnClickListener(this);
			break;
		case R.id.babr:
			setContentView(R.layout.edu);
			ln = (TextView) findViewById(R.id.tveloan);
			ln.setText("30 lakhs");
			go = (Button) findViewById(R.id.bego);
			val = 30;
			go.setOnClickListener(this);
			break;
		case R.id.bego:
			amt = (EditText) findViewById(R.id.eteloan);
			ten = (EditText) findViewById(R.id.etetenure);
			double lon,
			tn;
			String a,
			b;
			a = amt.getText().toString();
			b = ten.getText().toString();
			if (!a.equals(""))
				lon = Double.parseDouble(a);
			else
				lon = 0;
			if (!b.equals(""))
				tn = Double.parseDouble(b);
			else
				tn = 0;
			int chk = 0;
			if (lon > val) {
				amt.setText("");
				amt.setHint("Invalid!!!(1-" + val + ")");
				chk = 1;
			}
			if (tn < 1 || tn > 25) {
				ten.setText("");
				ten.setHint("Invalid!!!(1-25)");
				chk = 1;
			}
			if (chk == 0) {
				double rt, wt, mem;
				wt = 0.5;
				mem = ((tn - 1) * 0.99 / (25 - 1)) + 0.01;
				rt = mem * wt * tn + (1 - wt) * lon / 10;
				rt += 12;
				if (rt > 18)
					rt = 18;
				setContentView(R.layout.result);
				rate = (TextView) findViewById(R.id.tvRate);
				lo = (TextView) findViewById(R.id.tvLoan);
				TextView em = (TextView) findViewById(R.id.textView2);
				em.setText("");
				lo.setText("" + lon + " lacs");
				rate.setText(rt + " % ");

			}
			break;

		}
	}

}
