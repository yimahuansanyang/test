package com.example.filtermenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.linroid.filtermenu.library.FilterMenu;
import com.linroid.filtermenu.library.FilterMenuLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FilterMenuLayout layout = (FilterMenuLayout) findViewById(R.id.filter_menu);
        FilterMenu menu = new FilterMenu.Builder(this)
                .addItem(R.drawable.ic_cancel).addItem(R.drawable.ic_cancel).addItem(R.drawable.ic_cancel).attach(layout)
                .withListener(new FilterMenu.OnMenuChangeListener() {


                    @Override
                    public void onMenuItemClick(View view, int position) {

                    }

                    @Override
                    public void onMenuCollapse() {
                    }
                    @Override
                    public void onMenuExpand() {
                    }
                })
                .build();
    }
}
