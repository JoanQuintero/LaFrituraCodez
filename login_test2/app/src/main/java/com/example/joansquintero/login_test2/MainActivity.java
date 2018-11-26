package com.example.joansquintero.login_test2;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity  {
    Button b1,b2;
    EditText ed1,ed2;

    Button initianlSignin, initialRegister;
    TextView appName, appDesc, loginAttempts;

    TextView tx1;
    int counter = 3;

    /* just added lines below
    * for the purpose of saving username and password when they register */
    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    private final String DefaultUnameValue = "";
    private String UnameValue;

    private final String DefaultPasswordValue = "";
    private String PasswordValue;
    /* just added lines above
    * and also for the purpose of retrieving such values*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        b1 = (Button)findViewById(R.id.button);
//        ed1 = (EditText)findViewById(R.id.editText);
//        ed2 = (EditText)findViewById(R.id.editText2);
//
//        b2 = (Button)findViewById(R.id.button2);
//        tx1 = (TextView)findViewById(R.id.textView3);
//        tx1.setVisibility(View.GONE);
//
//        dontShow(b1);
//        dontShow(b2);
//        dontShow(ed1);
//        dontShow(ed2);
//
//        initianlSignin = (Button)findViewById(R.id.signin);
//        initialRegister = (Button)findViewById(R.id.register);
//        appName = (TextView)findViewById(R.id.appname);
//        appDesc = (TextView)findViewById(R.id.appdesc);
//        loginAttempts = (TextView)findViewById(R.id.appattempts);
//        dontShow(appName);
//        dontShow(appDesc);
//        dontShow(loginAttempts);
//
//        final Animation fadeIn, slideDown, sequential;
//        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
//        slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
//        sequential = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sequential);
//
//        initianlSignin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dontShow(initianlSignin);
//                dontShow(initialRegister);
//                doShow(b1); doShow(b2); doShow(ed1); doShow(ed2); doShow(appName); doShow(appDesc);
//                ed1.startAnimation(fadeIn);
//                ed2.startAnimation(fadeIn);
//                appName.startAnimation(fadeIn);
//                appDesc.startAnimation(fadeIn);
//                b1.startAnimation(slideDown);
//                b2.startAnimation(slideDown);
//
//            }
//        });
//
//        initialRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //recreate();
//                dontShow(initianlSignin); dontShow(initialRegister);
//                doShow(b1); doShow(b2); doShow(ed1); doShow(ed2); doShow(appName); doShow(appDesc);
//                b1.startAnimation(sequential);
//                b2.startAnimation(sequential);
//                ed1.startAnimation(sequential);
//                ed2.startAnimation(sequential);
//                appName.startAnimation(sequential);
//                appDesc.startAnimation(sequential);
//               // sequential.setRepeatCount(0); // PROBLEM: ANIMATION WONT STOP
//                sequential.cancel();
//                sequential.reset();
//                b1.setText("Register");
//
//            }
//        });
//
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // b1.setVisibility(View.GONE); // added this on
//                if(ed1.getText().toString().equals("admin") &&
//                        ed2.getText().toString().equals("admin")) {
//                    Toast.makeText(getApplicationContext(),
//                            "Redirecting...",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getApplicationContext(), "Wrong " +
//                            "Credentials",Toast.LENGTH_SHORT).show();
//
//                            doShow(loginAttempts);
//                            tx1.setVisibility(View.VISIBLE);
//                    tx1.setBackgroundColor(Color.RED);
//                    counter--;
//                    tx1.setText(Integer.toString(counter));
//
//                    if (counter == 0) {
//                        b1.setEnabled(false);
//                    }
//                }
//            }
//        });
//
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //finish();
//                recreate(); // relaunches activity from beginning
//                ed1.setText(null);
//                ed2.setText(null);
//            }
//        });
    }
//
//    public void dontShow(View view){
//        view.setVisibility(View.GONE);
//    }
//
//    public void doShow(View view){
//        view.setVisibility(View.VISIBLE);
//    }
//
//    /* just added lines below
//     * for the purpose of saving username and password when they register */
//    @Override
//    public void onPause() {
//        super.onPause();
//        savePreferences();
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        loadPreferences();
//    }
//
//    private void savePreferences() {
//        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = settings.edit();
//
//        UnameValue = String.valueOf(ed1.getText());
//        PasswordValue = String.valueOf(ed2.getText());
//        editor.putString(PREF_UNAME, UnameValue);
//        editor.putString(PREF_PASSWORD, PasswordValue);
//        editor.commit();
//    }
//
//    private void loadPreferences(){
//        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
//                Context.MODE_PRIVATE);
//
//        UnameValue = settings.getString(PREF_UNAME, DefaultUnameValue);
//        PasswordValue = settings.getString(PREF_PASSWORD, DefaultPasswordValue);
//        ed1.setText(UnameValue);
//        ed2.setText(PasswordValue);
//    }
    /* just added lines above
     * and also for the purpose of retrieving such values*/

}