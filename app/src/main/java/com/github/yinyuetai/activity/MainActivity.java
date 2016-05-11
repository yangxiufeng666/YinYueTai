package com.github.yinyuetai.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;

import com.github.yinyuetai.R;
import com.github.yinyuetai.fragment.FirstPageFragment;
import com.github.yinyuetai.fragment.MVFragment;
import com.github.yinyuetai.fragment.VChartFragment;
import com.github.yinyuetai.fragment.YueDanFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {
    SparseArray<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments = new SparseArray<>();
        fragments.put(R.id.bottomBarItem1,new FirstPageFragment());
        fragments.put(R.id.bottomBarItem2,new MVFragment());
        fragments.put(R.id.bottomBarItem3,new VChartFragment());
        fragments.put(R.id.bottomBarItem4,new YueDanFragment());
        BottomBar mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottom_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                Fragment targetFragment = fragments.get(menuItemId);
                Log.e("id","id = "+menuItemId);
                setFragment(targetFragment);
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(1, "#FF9800");
        mBottomBar.mapColorForTab(2, "#7B1FA2");
        mBottomBar.mapColorForTab(3, "#FF5252");
    }
    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragment.isAdded()&&fragment.isVisible()){
            return;
        }
        if (fragment.isAdded()){
            transaction.show(fragment);
        }else {
            transaction.replace(R.id.content,fragment);
        }
        transaction.commit();
    }
}
