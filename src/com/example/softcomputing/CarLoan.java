package com.example.softcomputing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CarLoan extends Activity implements OnClickListener {

	Button go, sk;
	EditText sal, price, tenu;
	TextView inc, mor, ten, rate, lon, emi, fe,fe2;
	LinearLayout lay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salient);
		fe = (TextView) findViewById(R.id.tvsal);
		fe2 = (TextView) findViewById(R.id.tvsal2);
		sk = (Button) findViewById(R.id.bsali);
		fe.setText("•       No Advance EMI;\n"
				+ "•       Longest  repayment tenure (7 years);\n"
				+ "•       Low interest rates ;\n" + "•       Low EMI");
		fe2.setText("To avail Car Loan, you should be :\n"+
		"•      Individual between the age of 21-65 years of 	age.\n"+
		"•      Regular employee of State / Central 	Government, Public Sector Undertaking,   Private company or a reputed establishment.\n"+ 
		"•      Professionals, self-employed, businessmen, 	proprietary/partnership firms who is an 	income tax assessee.\n"+
		"•      Person engaged in Agricultural and allied activities.\n"+
		"•       Gross Annual Income :-\n"+"	Above 3 lakhs");
		sk.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bsali:
			setContentView(R.layout.carloan);
			sal = (EditText) findViewById(R.id.etIncome);
			price = (EditText) findViewById(R.id.eTMorgage);
			tenu = (EditText) findViewById(R.id.eTTenure);
			inc = (TextView) findViewById(R.id.tvIncome);
			mor = (TextView) findViewById(R.id.tvMorgage);
			ten = (TextView) findViewById(R.id.tvTenure);
			go = (Button) findViewById(R.id.bGo);
			go.setOnClickListener(this);
			break;
		case R.id.bGo:
			double in,
			mort,
			tn;
			String a,
			b,
			c;
			a = sal.getText().toString();
			b = price.getText().toString();
			c = tenu.getText().toString();
			if (!a.equals(""))
				in = Double.parseDouble(a);
			else
				in = 0;
			if (!(b.equals("")))
				mort = Double.parseDouble(b);
			else
				mort = 0;
			if (!c.equals(""))
				tn = Double.parseDouble(c);
			else
				tn = 0;
			double memm,
			memi,
			meml;
			double wtm,
			wti;
			double ln;
			double iin,
			imort,
			fin,
			fmort;
			double iten,
			ften,
			memt,
			wtt,
			rt;
			rt = 0;
			iten = 1;
			ften = 7;
			iin = 3.0;
			imort = 1.0;
			fin = 100;
			fmort = 1000;
			wtm = 0.9;
			wti = 1.0 - wtm;
			int chk = 0;
			if (in < iin || in > fin) {
				sal.setText("");
				sal.setHint("Invalid!!!(3-100)");
				chk = 1;
			}
			if (mort < imort || mort > fmort) {
				price.setText("");
				price.setHint("Invalid!!!(1-1000)");
				chk = 1;
			}
			if (tn < iten || tn > ften) {
				tenu.setText("");
				tenu.setHint("Invalid!!!(1-7)");
				chk = 1;
			}
			if (chk == 0) {

				memi = ((in - iin) * 0.75 / (fin - iin)) + 0.25;
				memm = ((mort - imort) * 0.25 / (fmort - imort)) + 0.75;
				ln = wtm * memm * mort + wti * memi * in;
				if (ln > mort)
					ln = mort;
				ln *= 100;
				ln = Math.round(ln);
				ln = ln / 100;
				if (ln < 9)
					meml = 0.2;
				else if (ln >= 9 && ln <= 11)
					meml = 0.2 + (ln - 9) / (11 - 9.0) * .1;
				else
					meml = 0.3;

				wtt = 0.6;
				memt = ((tn - iten) * 0.75 / (ften - iten)) + 0.25;
				rt = memt * wtt * tn + (1 - wtt) * meml * ln / 10;
				rt += 15;
				rt *= 100;
				rt = Math.round(rt);
				rt = rt / 100;
				if (rt < 19) {
					double r, em;
					setContentView(R.layout.result);
					rate = (TextView) findViewById(R.id.tvRate);
					lon = (TextView) findViewById(R.id.tvLoan);
					emi = (TextView) findViewById(R.id.tvEmi);
					r = Math.pow(1 + rt / 100, tn * 12);
					em = ln * (rt / 100) * r / (r - 1);
					em *= 100000 / 12;
					em = Math.round(em);
					emi.setText("Rs " + em + " per month");
					lon.setText("" + ln + " lakhs");
					rate.setText(rt + " % p.a.");
				} else {
					setContentView(R.layout.noloan);
				}
			}
			break;
		}
	}
}
