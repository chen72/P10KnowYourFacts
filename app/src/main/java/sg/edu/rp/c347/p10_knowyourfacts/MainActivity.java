package sg.edu.rp.c347.p10_knowyourfacts;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.crazyhitty.chdev.ks.rssmanager.Channel;
import com.crazyhitty.chdev.ks.rssmanager.RSS;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements RssReader.RssCallback {

    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;
    Button btnLater;
    AlarmManager am;
    int b = 0;
    int reqCode = 12345;

    private RssReader rssReader = new RssReader(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = (ViewPager) findViewById(R.id.viewpager1);
        btnLater = (Button) findViewById(R.id.btnLater);
        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);

                Intent intent = new Intent(MainActivity.this,
                        ScheduledNotificationReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

            }
        });

        rssReader.loadFeeds("https://www.gov.sg/rss/factuallyrss");



        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Fg1());
        al.add(new Fg2());
        al.add(new Fg3());
        adapter = new MyFragmentPagerAdapter(fm, al);
        vPager.setAdapter(adapter);





    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Step 2
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.action_previous) {
            if (vPager.getCurrentItem() > 0) {
                int previousPage = vPager.getCurrentItem() - 1;
                vPager.setCurrentItem(previousPage, true);
                b = previousPage;
                Log.i("previous b", b + "");
            }
            return true;
        } else if (id == R.id.action_random) {
            Random randomno = new Random();
            int a = randomno.nextInt(3);
            vPager.setCurrentItem(a, true);
            Log.i("random b", b + "");
            b = a;

            return true;

        } else {
            int max = vPager.getChildCount();
            if (vPager.getCurrentItem() < max - 1) {
                int nextPage = vPager.getCurrentItem() + 1;
                vPager.setCurrentItem(nextPage, true);
                b = nextPage;
                Log.i("next page b", b + " - " + nextPage);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        int page = prefs.getInt("page", 0);
        vPager.setCurrentItem(page, true);
    }

    @Override
    public void onPause() {
        // save the instance variables
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putInt("page", b = vPager.getCurrentItem());
        Log.i("b on pause", b + "");
        prefEdit.commit();
    }

    @Override
    public void rssFeedsLoaded(List<RSS> rssList) {


    }

    @Override
    public void unableToReadRssFeeds(String errorMessage) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
