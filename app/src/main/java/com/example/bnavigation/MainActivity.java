package com.example.bnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    BottomNavigationView bottomNavigationView;
    Fragment fragment = null;

    // "stack" IS USED TO KEEP TRACK OF ALL THE FRAGMENTS THAT WERE VISITED BEFORE REACHING THE PRESENT FRAGMENT
    // IT CONTAINS "" OF MENU-ITEMS IN BOTTOM NAVIGATION
    private List<Integer> stack = new ArrayList<>();

    // "isBackPressed" IS USED TO CHECK WEATHER BACK-BUTTON WAS PRESSED OR NOT
    private boolean isBackPressed = false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (stack.size() > 1) {

            // SET "isBackPressed" TO TRUE
            isBackPressed = true;

            // REMOVE THE LAST ITEM IN THE LIST SINCE LAST ELEMENT IS THE PRESENT ACTIVE FRAGMENT
            stack.remove(stack.size()-1);

            // CALL THE "onNavigationItemSelected" METHOD WITH THE ID OF THE PREVIOUS MENU-ITEM SELECTED
            bottomNavigationView.setSelectedItemId(stack.get(stack.size()-1));

            // SET "isBackPressed" TO FALSE
            isBackPressed = false;

        } else {
            finish();
            //super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

        fragment = new HomeFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        stack.add(R.id.bottom_nav_home);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()){
                case R.id.bottom_nav_home :

                    // ADD THE MENU-ITEM TO THE STACK, IF AND ONLY IF "onNavigationItemSelected"
                    // METHOD IS CALLED CLICKING ON THE NAVIGATION BAR
                    if(!isBackPressed) {
                        stack.add(R.id.bottom_nav_home);
                    }
                    fragment = new HomeFragment();
                    break;

                case R.id.bottom_nav_search :

                    // ADD THE MENU-ITEM TO THE STACK, IF AND ONLY IF "onNavigationItemSelected"
                    // METHOD IS CALLED CLICKING ON THE NAVIGATION BAR
                    if(!isBackPressed) {
                        stack.add(R.id.bottom_nav_search);
                    }
                    fragment = new SearchFragment();
                    break;

                case R.id.bottom_nav_upload :

                    // ADD THE MENU-ITEM TO THE STACK, IF AND ONLY IF "onNavigationItemSelected"
                    // METHOD IS CALLED CLICKING ON THE NAVIGATION BAR
                    if(!isBackPressed) {
                        stack.add(R.id.bottom_nav_upload);
                    }
                    fragment = new UploadFragment();
                    break;

                case R.id.bottom_nav_notification :

                    // ADD THE MENU-ITEM TO THE STACK, IF AND ONLY IF "onNavigationItemSelected"
                    // METHOD IS CALLED CLICKING ON THE NAVIGATION BAR
                    if(!isBackPressed) {
                        stack.add(R.id.bottom_nav_notification);
                    }
                    fragment = new NotificationsFragment();
                    break;

                case R.id.bottom_nav_profile :

                    // ADD THE MENU-ITEM TO THE STACK, IF AND ONLY IF "onNavigationItemSelected"
                    // METHOD IS CALLED CLICKING ON THE NAVIGATION BAR
                    if(!isBackPressed) {
                        stack.add(R.id.bottom_nav_profile);
                    }
                    fragment = new ProfileFragment();
                    break;
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
    };
}
