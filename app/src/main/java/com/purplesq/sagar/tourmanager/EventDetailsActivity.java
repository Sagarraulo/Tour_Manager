package com.purplesq.sagar.tourmanager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

public class EventDetailsActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private String code="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        Event event = bundle.getParcelable("event");
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new EventPagerAdapter(event));
        tabLayout.setupWithViewPager(viewPager);

    }

    public class EventPagerAdapter extends FragmentPagerAdapter {
        private final Event mevent;
        public EventPagerAdapter(Event event) {
            super(getSupportFragmentManager());
            this.mevent=event;
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0)
            {return EventFragment.newInstance(mevent);}
            else
            {return StudentFragment.newInstance(mevent.students);}
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0)
            {return getString(R.string.event_tab_title);}
            else
            {return getString(R.string.student_tab_title);}
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
