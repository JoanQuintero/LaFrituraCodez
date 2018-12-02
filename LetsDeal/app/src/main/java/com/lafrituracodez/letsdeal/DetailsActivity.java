package com.lafrituracodez.letsdeal;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity{

    private ArrayList<Library> detail;
    protected static String Title = "";
    protected static String Price = "";
    protected static String Info = "";
    protected static String Index = "";
    private TextView Return, bookTitle;
    private TextView total, subtotal, shipping, taxes, numTotal, numSubTotal, numShipping, numTaxes;
    private RelativeLayout detailsimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String Img = getIntent().getStringExtra("IMAGE_ID");
        Title = getIntent().getStringExtra("TITLE");
        Info = getIntent().getStringExtra("INFO");
        Index = getIntent().getStringExtra("INDEX");
        Price = getIntent().getStringExtra("PRICE");

        Return = findViewById(R.id.return_to);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bookTitle = findViewById(R.id.booktitle);
        bookTitle.setText(Title);

        detailsimg = findViewById(R.id.imgdetail_loc);
        TypedArray imgs = getResources().obtainTypedArray(R.array.book_images);
        detailsimg.setBackgroundResource(imgs.getResourceId(Integer.parseInt(Index), -1));
        detailsimg.getBackground().setAlpha(100);
        imgs.recycle();

        /* Default Titles */
        total = findViewById(R.id.Total);
        subtotal = findViewById(R.id.Subtotal);
        shipping = findViewById(R.id.Shipping);
        taxes = findViewById(R.id.Taxesandfees);
        /* Numeric values for Titles above */
        numTotal = findViewById(R.id.Totalvalue);
        numSubTotal = findViewById(R.id.Subtotalvalue);
        numShipping = findViewById(R.id.Shippingvalue);
        numTaxes = findViewById(R.id.Taxesandfeesvalue);
        
        /* Setting random values based on current book displaying */
        int defaultShipping = 5;

        numSubTotal.setText("$"+Price+".00");
        numShipping.setText("$"+String.valueOf(defaultShipping+".00"));
        
       // numTaxes.setText("$"+String.valueOf(Integer.parseInt(Price)*0.1)+".00");
//        double TaxesAre =  Integer.parseInt((Price.toString()))*0.1;
        
        int TaxesAre = 5;
//        int TaxesAre = Integer.parseInt(Price.toString()) * 10;
        numTaxes.setText("$"+TaxesAre+".00");

//        numTotal.setText(String.valueOf(Integer.parseInt(Price+defaultShipping+TaxesAre)));
        numTotal.setText(Price+".00");
        

    }

}
