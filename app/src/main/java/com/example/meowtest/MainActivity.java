package com.example.meowtest;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation mMeowBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        mMeowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_notifications));
        mMeowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_home));
        mMeowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_info));

        mMeowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        fragment = NotificationFragment.newInstance();
                        break;
                    case 2:
                        fragment = HomeFragment.newInstance();
                        break;
                    case 3:
                        fragment = AboutFragment.newInstance();
                        break;
                }
                loadFragment(fragment);
            }
        });

        mMeowBottomNavigation.setCount(1, "10");
        mMeowBottomNavigation.show(2, true);

        mMeowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "You Clicked " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        mMeowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "You Reselected " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViews() {
        mMeowBottomNavigation = findViewById(R.id.bottom_navigation);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}