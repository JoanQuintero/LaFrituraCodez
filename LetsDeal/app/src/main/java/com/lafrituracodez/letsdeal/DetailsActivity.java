package com.lafrituracodez.letsdeal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

	protected String title;
	protected String desc;
	protected String key;
	protected static Double price;

	private TextView description, total;
	private ImageView cover;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		title = getIntent().getStringExtra("title");
		desc = getIntent().getStringExtra("desc");
		price = getIntent().getDoubleExtra("price", 0.0);
		key = getIntent().getStringExtra("key");

		Toolbar detail_toolbar = findViewById(R.id.toolbar_DetailActivity);
		detail_toolbar.setTitle(title);
		setSupportActionBar(detail_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		cover = findViewById(R.id.imageView_detailCover);
		Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/lafrituracodez.appspot.com/o/images%2F" + key + "?alt=media").into(cover);

		description = findViewById(R.id.textView_detailBookDesc);
		total = findViewById(R.id.textView_detailBookPriceTotal);

		description.setText(desc);
		String price_str = String.format(Locale.US, "$%.2f", price);
		total.setText(price_str);

	}
}
