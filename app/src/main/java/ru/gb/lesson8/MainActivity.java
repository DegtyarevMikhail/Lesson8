package ru.gb.lesson8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    private Map<Integer, Fragment> fragmentMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.main_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = fragmentMap.get(item.getItemId());
        if (fragment == null){

        switch (item.getItemId())
        {
            case R.id.menu_main_detail:
                fragment = new DetailFragment();
                //Toast.makeText(this, "Detail", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_main_list:
                fragment = new ListFragment();
                //Toast.makeText(this, "List", Toast.LENGTH_SHORT).show();
                break;
            default:
                fragment = new SettingsFragment();
                //Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        fragmentMap.put(item.getItemId(), fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_host, fragment)
                .commit();
        return true;
    }

    @Override
    public void onBackPressed() {
        for (Fragment f: getSupportFragmentManager().getFragments())
        {
            if (f.isVisible())
            {
                FragmentManager childFm = f.getChildFragmentManager();
                if (childFm.getBackStackEntryCount() > 0)
                {
                    childFm.popBackStack();
                    return;
                }
            }
        }
        super.onBackPressed();
    }
}