package com.lafrituracodez.letsdeal;


import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {

    private TextView PersonalSettings, ProfileEdit, UserEdit, PwEdit, PreferencesEdit, Settings,
            Orders, AllOrders, Utilities, AppStyle, Cache, SysOptions, MemUsage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //PersonalSettings = findViewById(R.id.account_settings);

        ProfileEdit = findViewById(R.id.edit_profile);
        ProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfile();
            }
        });

        UserEdit = findViewById(R.id.edit_user);
        UserEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditUser();

            }
        });

        PwEdit = findViewById(R.id.change_password);
        PwEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPassword();

            }
        });

        PreferencesEdit = findViewById(R.id.change_preferences);
        PreferencesEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPreferences();
            }
        });

        Settings = findViewById(R.id.account_settings);
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeSettings();

            }
        });

        //Orders = findViewById(R.id.your_Orders);

        AllOrders = findViewById(R.id.account_ViewAllOrders);
        AllOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewOrders();

            }
        });

        // Utilities = findViewById(R.id.account_utilities);

        AppStyle = findViewById(R.id.app_set_style);
        AppStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeStyle();


            }
        });

        Cache = findViewById(R.id.refresh_cache);
        Cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RefreshCache();

            }
        });

        SysOptions = findViewById(R.id.system_options);
        SysOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeSystemOptions();
            }
        });

        MemUsage = findViewById(R.id.memory_usage);
        MemUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryMonitor();
            }
        });


        UserEdit.setPaintFlags(UserEdit.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        //underlines text


        //If the prototype will be the final design, ease_in animation needs
        // to be added to this activity
        //  11/27/2018
    }

    private void EditProfile() {
    }

    private void EditUser() {
    }

    private void EditPassword() {
    }

    private void EditPreferences() {
    }

    private void ChangeSettings() {
    }

    private void ViewOrders() {
    }

    private void ChangeStyle() {
    }

    private void RefreshCache() {
    }

    private void ChangeSystemOptions() {
    }

    private void MemoryMonitor() {
    }

    // Added OnClick methods to all possible candidates
    //  12/2/2018

}
