package com.mulganov.test_task.middle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.mulganov.test_task.middle.model.Filter;
import com.mulganov.test_task.middle.model.list.BoxAdapter;
import com.mulganov.test_task.middle.model.tools.Info;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private View mContentView;
    private Presenter presenter;

    private ArrayList<Info> infos = new ArrayList<>();

    public static MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        mContentView = findViewById(R.id.main);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        findViewById(R.id.popup).setVisibility(View.INVISIBLE);

        findViewById(R.id.button_start).setOnClickListener(v -> presenter.onClickStart(findViewById(R.id.text_start), findViewById(R.id.button_start)));

        presenter = new Presenter(this);
    }

    public void openPopupWindow() {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_example, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it

        popupView.findViewById(R.id.iAll).setOnClickListener(presenter::onClickAll);

        popupView.findViewById(R.id.iHour).setOnClickListener(presenter::onClickHour);

        popupView.findViewById(R.id.iDay).setOnClickListener(presenter::onClickDay);

        popupView.findViewById(R.id.iMonth).setOnClickListener(presenter::onClickMonth);


        popupView.findViewById(R.id.tAll).setOnClickListener(v -> presenter.onClickAll(popupView.findViewById(R.id.iAll)));

        popupView.findViewById(R.id.tHour).setOnClickListener(v -> presenter.onClickHour(popupView.findViewById(R.id.iHour)));

        popupView.findViewById(R.id.tDay).setOnClickListener(v -> presenter.onClickDay(popupView.findViewById(R.id.iDay)));

        popupView.findViewById(R.id.tMonth).setOnClickListener(v -> presenter.onClickMonth(popupView.findViewById(R.id.iMonth)));

        if (presenter.getFilter().getFilter() == Filter.ALL)
            ((ImageView)popupView.findViewById(R.id.iAll)).setImageResource(R.drawable.radio_button_checked);

        if (presenter.getFilter().getFilter() == Filter.HOUR)
            ((ImageView)popupView.findViewById(R.id.iHour)).setImageResource(R.drawable.radio_button_checked);

        if (presenter.getFilter().getFilter() == Filter.DAY)
            ((ImageView)popupView.findViewById(R.id.iDay)).setImageResource(R.drawable.radio_button_checked);

        if (presenter.getFilter().getFilter() == Filter.MONTH)
            ((ImageView)popupView.findViewById(R.id.iMonth)).setImageResource(R.drawable.radio_button_checked);

        ArrayList<ImageView> list = new ArrayList<>();

        list.add(popupView.findViewById(R.id.iAll));
        list.add(popupView.findViewById(R.id.iHour));
        list.add(popupView.findViewById(R.id.iDay));
        list.add(popupView.findViewById(R.id.iMonth));

        presenter.getFilter().setListView(list);

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken

        popupWindow.showAtLocation(mContentView, Gravity.NO_GRAVITY,(int) findViewById(R.id.popup).getX(), (int) findViewById(R.id.popup).getY());

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                findViewById(R.id.back).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.back).setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void onClickSort(MenuItem item) {
        openPopupWindow();
    }

    public void addInfo(Info info){
        infos.add(0, info);

        presenter.getFilter().all.add(0, info);

        reloadList();
    }

    public void addInfo(ArrayList<Info> info){
        infos.removeAll(infos);
        infos.addAll(info);

        reloadList();
    }

    private void reloadList() {
        System.out.println("Size: " + infos.size());
        if (infos.size() != 0){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.imageView).setVisibility(View.INVISIBLE);
                    findViewById(R.id.text_status).setVisibility(View.INVISIBLE);
                    findViewById(R.id.textView).setVisibility(View.INVISIBLE);
                }
            });
        }

        BoxAdapter adapter = new BoxAdapter(this, infos);
        ListView listView = findViewById(R.id.list);

        listView.post(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(adapter);
            }
        });
    }

}
