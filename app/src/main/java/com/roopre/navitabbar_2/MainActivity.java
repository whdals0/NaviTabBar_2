package com.roopre.navitabbar_2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private final String TAG = getClass().getSimpleName();
    // 툴바 중앙 텍스트뷰
    TextView title_tv;

    //툴바
    Toolbar toolbar;

    //좌측 메뉴 레이아웃
    DrawerLayout drawer;

    // 메뉴 버튼 클릭 시 동작하는 토글 버튼
    ActionBarDrawerToggle toggle;

    // 현재 Toolbar 의 메뉴버튼 활성화 / 비활성화에 사용할 변수
    boolean mToolbarNavi = false;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFragment, mainFragment, "MAIN")
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onBackStackChanged() {
        Log.d(TAG, "onBackStackChanged getBackStackEntryCount = " + getSupportFragmentManager().getBackStackEntryCount());
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp() {
        boolean canback = getSupportFragmentManager().getBackStackEntryCount() > 0;
        if (canback) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            // 햄버거 버튼 지우기
            toggle.setDrawerIndicatorEnabled(false);
            // 뒤로 버튼 활성화
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if(!mToolbarNavi) {
                toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });

                mToolbarNavi = true;
            }
        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            // 백버튼 지우기
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // 메뉴버튼 활성화

            toggle.setDrawerIndicatorEnabled(true);

            toggle.setToolbarNavigationClickListener(null);
            mToolbarNavi = false;
        }
    }
}
