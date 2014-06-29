package com.muni.trujilloseguro.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;

import com.muni.trujilloseguro.components.TypefaceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.muni.trujilloseguro.adapters.NavDrawerListAdapter;
import com.muni.trujilloseguro.fragments.FragmentAlerta;
import com.muni.trujilloseguro.fragments.FragmentIncidencia;
import com.muni.trujilloseguro.fragments.FragmentLlamar;
import com.muni.trujilloseguro.fragments.FragmentNoticias;
import com.muni.trujilloseguro.fragments.FragmentNotificame;
import com.muni.trujilloseguro.fragments.FragmentOne;
import com.muni.trujilloseguro.gps.GPSTracker;
import com.muni.trujilloseguro.models.DBhelper;
import com.muni.trujilloseguro.models.NavDrawerItem;
import com.muni.trujilloseguro.models.SQLController;

import java.util.ArrayList;

public class MenuActivity extends ActionBarActivity {



    private static final int REQUEST_PHOTO = 1;
    // private static final int REQUEST_INCIDENCIA_MAPA = 2;
    private static final int RQS_PICKCONTACT = 2;
    Fragment fragment;
    private String FONT = "KaushanScript-Regular.otf";

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    public static FragmentManager fragmentManager;


    Spinner spn;
    SQLController SQLcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //spn = (Spinner) findViewById(R.id.spinner_contactos);

        //SQLcon = new SQLController(this);

        //SQLcon.open();
        //loadtospinner();



        new GPSTracker(getApplicationContext());
        fragmentManager = getSupportFragmentManager();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b2533")));

        int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        if (actionBarTitleId > 0) {
            TextView title = (TextView) findViewById(actionBarTitleId);
            if (title != null) {
                title.setTextColor(Color.parseColor("#43c9cb"));

            }
        }

        mTitle = mDrawerTitle = getTitle();

        //cargar los items del menu
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        //recuperar los iconos del nav
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // Añadiendo los items al arreglo
        // Alerta
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Incidencias
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Notificame
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Noticias
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        // Cuenta
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // Contáctanos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
    }

    /**
     * Slide menu item click listener
     */

    /*public void loadtospinner() {

        Cursor c = SQLcon.readData();
        ArrayList<String> al = new ArrayList<String>();

        c.moveToFirst();
        while (!c.isAfterLast()) {

            String name = c.getString(c.getColumnIndex(DBhelper.MEMBER_NAME));
            al.add(name);
            c.moveToNext();
        }

        ArrayAdapter<String> aa1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, R.id.textView1,al);

        spn.setAdapter(aa1);

        // closing database
        SQLcon.close();


    }*/


    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        fragment = null;

        switch (position) {
            case 0:
                fragment = new FragmentAlerta();
                break;
            case 1:
                fragment = new FragmentIncidencia();
                break;
            case 2:
                fragment = new FragmentNotificame();
                break;
            case 3:
                fragment = new FragmentNoticias();
                break;
            case 4:
                fragment = new FragmentOne();
                break;
            case 5:
                fragment = new FragmentLlamar();
                break;

            default:
                break;
        }

        if (fragment != null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;


        SpannableString s = new SpannableString(mTitle);
        s.setSpan(new TypefaceSpan(getApplicationContext(), FONT), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        getSupportActionBar().setTitle(s);

    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Bundle extras;
            switch (requestCode) {

                case REQUEST_PHOTO:
                    extras = data.getExtras();
                    Bitmap bmp = (Bitmap) extras.get("data");
                    ImageView foto = (ImageView) findViewById(R.id.imgPreview);
                    foto.setVisibility(View.VISIBLE);
                    foto.setImageBitmap(bmp);
                    break;

                case RQS_PICKCONTACT:
                    TextView textPhone = (TextView) findViewById(R.id.phone);
                    Uri returnUri = data.getData();
                    Cursor cursor = getContentResolver().query(returnUri, null, null, null, null);

                    if(cursor.moveToNext()){
                        int columnIndex_ID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                        String contactID = cursor.getString(columnIndex_ID);

                        int columnIndex_HASPHONENUMBER = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
                        String stringHasPhoneNumber = cursor.getString(columnIndex_HASPHONENUMBER);

                        if(stringHasPhoneNumber.equalsIgnoreCase("1")){
                            Cursor cursorNum = getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactID,
                                    null,
                                    null);

                            //Obtiene el primer numero
                            if(cursorNum.moveToNext()){
                                int columnIndex_number = cursorNum.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                                String stringNumber = cursorNum.getString(columnIndex_number);
                                textPhone.setText(stringNumber);
                                Toast.makeText(getApplicationContext(), "Se agregó a : " + stringNumber, Toast.LENGTH_LONG).show();
                                //SQLcon.open();
                                //SQLcon.insertData(stringNumber);

                            }

                        }else{
                            textPhone.setText("No tiene numero");
                        }


                    }else{
                        Toast.makeText(getApplicationContext(), "NO hay data", Toast.LENGTH_LONG).show();
                    }
            }

        } else {
            Toast.makeText(getApplicationContext(), "Fallo", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        Button btnshowmap = (Button) findViewById(R.id.btnShowLocation);
        btnshowmap.setEnabled(true);
        super.onBackPressed();

    }
}
