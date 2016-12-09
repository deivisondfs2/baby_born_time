package br.com.deivisondfs2.babyborntime;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabsActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        initializeTabs();
    }

    private void initializeTabs() {
        Resources ressources = getResources();
        TabHost tabHost = getTabHost();

        // Android tab
        Intent intentAndroid = new Intent().setClass(this, CalculateDPPActivity.class);
        TabHost.TabSpec tabSpecAndroid = tabHost
                .newTabSpec(String.valueOf(R.string.tab1))
                .setIndicator("", ressources.getDrawable(R.drawable.ic_home_white_24dp))
                .setContent(intentAndroid);


        // Android tab
        Intent intentAndroid2 = new Intent().setClass(this, CalculateDPPActivity.class);
        TabHost.TabSpec tabSpecAndroid2 = tabHost
                .newTabSpec(String.valueOf(R.string.tab1))
                .setIndicator("", ressources.getDrawable(R.drawable.ic_home_white_24dp))
                .setContent(intentAndroid2);


        // add all tabs
        tabHost.addTab(tabSpecAndroid);
        tabHost.addTab(tabSpecAndroid2);


        //set Windows tab as default (zero based)
        tabHost.setCurrentTab(0);
    }
}
