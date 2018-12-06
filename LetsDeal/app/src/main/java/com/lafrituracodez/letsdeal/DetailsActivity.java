package com.lafrituracodez.letsdeal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

	protected static String title;
	protected static Double price;
	protected static String info;

	private TextView total, subtotal, shipping, taxes, numTotal, numSubTotal, numShipping, numTaxes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		title = getIntent().getStringExtra("title");
		info = getIntent().getStringExtra("desc");
		price = getIntent().getDoubleExtra("price", 0.0);

		Toolbar detail_toolbar = findViewById(R.id.toolbar_DetailActivity);
		detail_toolbar.setTitle(title);
		setSupportActionBar(detail_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		

		numTotal = findViewById(R.id.Totalvalue);
	}
}
