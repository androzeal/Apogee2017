package bitspilani.dvm.apogee2017;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.wunderlist.slidinglayer.SlidingLayer;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean exit=false;
    Fragment fragment=null;
    Fragment lastFragment=null;
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView nvDrawer;
   SlidingLayer mSlidingLayer;
    DatabaseReference mDatabase;
//    CoordinatorLayout coordinatorLayout;



    TextView slider_name;
    TextView slider_work;
    TextView slider_description;
    ImageView slider_pic;


    //Jai
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private boolean searchResultShowing=false;
     EditText edtSeach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDatabase = Utils.getDatabase().getReference();
         toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

//         coordinatorLayout=(CoordinatorLayout) findViewById(R.id.coordinatorLayout);



        //Slider stuff
        mSlidingLayer = (SlidingLayer) findViewById(R.id.slidingLayer2);
        slider_name=(TextView) findViewById(R.id.slider_name);
        slider_work=(TextView) findViewById(R.id.slider_work);
        slider_description=(TextView) findViewById(R.id.slider_description);
        slider_pic=(ImageView) findViewById(R.id.slider_pic);


         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                exit=false;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                exit = false;
            }
        };



        drawer.setDrawerListener(toggle);
        toggle.syncState();



        nvDrawer= (NavigationView) findViewById(R.id.nav_view);
        nvDrawer.setNavigationItemSelectedListener(this);

        fragment=new Events();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();


    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.about:
                lastFragment=new About();
                fragment=new About();
                break;

            case R.id.events:
                lastFragment=new Events();
                fragment=new Events();
                break;

            case R.id.schedule:
                lastFragment=new Schedule();
                fragment=new Schedule();
                break;

            case R.id.think_again:
                lastFragment=new ThinkAgain();
                fragment=new ThinkAgain();
                break;

            case R.id.startup_weekend:
                lastFragment=new StartupWeekend();
                fragment=new StartupWeekend();
                break;

            case R.id.literary_fest:
                lastFragment=new LiteraryFest();
                fragment=new LiteraryFest();
                break;

            case R.id.workshops:
            lastFragment=new Workshops();
            fragment=new Workshops();
            break;

            case R.id.exhibitions:
                lastFragment=new Exhibitions();
                fragment=new Exhibitions();
                break;

            case R.id.contact_us:
                lastFragment=new ContactUs();
                fragment=new ContactUs();
                break;

            case R.id.sponsors:
                lastFragment=new Sponsors();
                fragment=new Sponsors();
                break;

            case R.id.developers:
                lastFragment=new Developers();
                fragment=new Developers();
                break;

            case R.id.map:
                final Intent i=new Intent(this,MapsActivity.class);
                drawer.closeDrawer(GravityCompat.START);
                fragment=null;
                startActivity(i);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(fragment!= null)
        {
            item.setChecked(true);
            toolbar.setTitle(item.getTitle());
        }


        drawer.closeDrawer(GravityCompat.START);

        if(mSlidingLayer.isOpened())
        {
            mSlidingLayer.closeLayer(true);
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (fragment != null) {
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                }
            }
        }, 380);

        return true;
    }

    @Override
    public void onBackPressed() {

        if(searchResultShowing)
        {
            fragment=lastFragment;
            if(lastFragment==null)
            {
                fragment=new Events();
            }
            if (fragment != null) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }

            return;
        }

        if(isSearchOpened) {
            handleMenuSearch();
            return;
        }
        if(mSlidingLayer.isOpened()) {
            mSlidingLayer.closeLayer(true);
            return;
        }


        if (!drawer.isDrawerOpen(nvDrawer)&&!exit) {
            drawer.openDrawer(nvDrawer);
            Toast.makeText(this,"Press Again to Exit",Toast.LENGTH_SHORT).show();
            exit = true;
            return;
        }
        else if(!exit && drawer.isDrawerOpen(nvDrawer)) {
            drawer.closeDrawers();
        }

        if(exit)
        System.exit(0);
        super.onBackPressed();
    }

    //Jai

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                handleMenuSearch();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void handleMenuSearch(){
        ActionBar action = getSupportActionBar(); //get the actionbar

        if(isSearchOpened){ //test if the search is open

            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar

            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);

            //add the search icon in the action bar
            mSearchAction.setIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_menu_search));

            isSearchOpened = false;
        } else { //open the search entry

            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.search_bar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title

            edtSeach = (EditText)action.getCustomView().findViewById(R.id.edtSearch); //the text editor

            //this is a listener to do a search when the user clicks on search button
            edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        doSearch();
                        return true;
                    }
                    return false;
                }
            });


            edtSeach.requestFocus();

            //open the keyboard focused in the edtSearch
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);


            //add the close icon
            mSearchAction.setIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_close_search));

            isSearchOpened = true;
        }
    }

    public void doSearch() {
        Log.d("Success","doSearch");

        fragment=new SearchResults();
        //Passing data to the fragment
        Bundle args = new Bundle();
        args.putString("key", edtSeach.getText().toString());
        fragment.setArguments(args);

        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            searchResultShowing=true;
        }

    }


}
