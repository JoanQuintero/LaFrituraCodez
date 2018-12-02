package com.lafrituracodez.letsdeal;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity{

    private ArrayList<Library> detail;
    protected static String Title = "";
    protected static String Price = "";
    protected static String Info = "";
    protected static String Index = "";
    private TextView Return, bookTitle;
    private RelativeLayout detailsimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

       //  String bTitle ="TITLE" "INFO" "INDEX" "IMAGE_ID"
        Title = getIntent().getStringExtra("TITLE");
        Info = getIntent().getStringExtra("INFO");
        Index = getIntent().getStringExtra("INDEX");
        Price = getIntent().getStringExtra("PRICE");

        Return = findViewById(R.id.return_to);

        bookTitle = findViewById(R.id.booktitle);
        bookTitle.setText(Title);

        detailsimg = findViewById(R.id.imgdetail_loc);
        detailsimg.getBackground().setAlpha(100);


//        String IndexOf = getIntent().getStringExtra("INDEX");
//        titleView.setText(String.valueOf(getIntent().getStringExtra("TITLE")));
//        infoView.setText(String.valueOf(getIntent().getStringExtra("INFO")));
//        Glide.with(this)
//                .load(getIntent().getIntExtra("IMAGE_ID", 0))
//                .into(imageView);
//        int resId = getResources().getIdentifier("foodie_details", "array", getPackageName());
//        String[] res = getResources().getStringArray(resId);
//        int indexOfitemDisplayedInDetails = Integer.parseInt(String.valueOf(IndexOf));
//       // moreDetails.setText(String.valueOf(res[indexOfitemDisplayedInDetails]));
//        int linkit = getResources().getIdentifier("foodie_links", "array", getPackageName());
//        String[] is = getResources().getStringArray(linkit);
//        int linkindex = Integer.parseInt(String.valueOf(IndexOf));
//        final String url = String.valueOf(is[linkindex]);
//        links.setText("\n\nClick here for more info"); //make 2 spaces on top, then set text
//        links.setPaintFlags(links.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG); //underline link text

        // ----------------------------------------------------------------------------------------
        // Center the following objects in the screen, horizontally
//        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
//        infoView.setGravity(Gravity.CENTER_HORIZONTAL);
//        moreDetails.setGravity(Gravity.CENTER_HORIZONTAL);
//        links.setGravity(Gravity.CENTER_HORIZONTAL);

        // ----------------------------------------------------------------------------------------
        // this is how you center images

//        titleView.setGravity(Gravity.CENTER_HORIZONTAL);
//        infoView.setGravity(Gravity.CENTER_HORIZONTAL);
//        moreDetails.setGravity(Gravity.CENTER_HORIZONTAL);
//        links.setGravity(Gravity.CENTER_HORIZONTAL);

//        int width = ViewGroup.LayoutParams.MATCH_PARENT;
//        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width, height);
//        layoutParams.gravity=Gravity.CENTER;
        //imageView.setLayoutParams(layoutParams);
        // ----------------------------------------------------------------------------------------

        // ----------------------------------------------------------------------------------------
        // And this is how you create an onclick intent
        // first, links (a textview object) is given the onclicklistener ability
        // once clicked, an intent will send the user to the url defined on line 87.
        // keep in mind (line 87 might change if you add anything above it or if you remove anything)
//        links.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent gotolink = new Intent(Intent.ACTION_VIEW);
//
//                gotolink.setData(Uri.parse(url));
//
//                startActivity(gotolink);
//
//            }
//        });
//    }

    }

}
