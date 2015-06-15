package at.tugraz.mobileapps.tournamentplanner;

import java.util.ArrayList;
import java.util.Locale;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class PlayersActivity extends AppCompatActivity {

    public static final String TAG = "PlayersActivity";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_players, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position) {
                case 0:
                    return PlayerSelectionFragment.newInstance(1);
                case 1:
                    return RoundsFragment.newInstance(2);
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_placeholder, container, false);
            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class RoundsFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private static RecyclerView roundsList;
        private LinearLayoutManager roundsLayoutManager;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static RoundsFragment newInstance(int sectionNumber) {
            RoundsFragment fragment = new RoundsFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public RoundsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_rounds, container, false);

            // TODO replace
            ArrayList<Encounter> encounters = new ArrayList();
            encounters.add(new Encounter(new Player("Name 1"), new Player("Name 2")));
            encounters.add(new Encounter(new Player("Name 3"), new Player("Name 4")));

            roundsList = (RecyclerView) rootView.findViewById(R.id.rounds_list);
            roundsLayoutManager = new LinearLayoutManager(getActivity());
            roundsList.setLayoutManager(roundsLayoutManager);
            roundsList.setAdapter(new RoundAdapter(encounters));
            return rootView;
        }
    }

    /**
     * The player selection fragment.
     */
    public static class PlayerSelectionFragment extends Fragment {

        private static RecyclerView currentPlayersList;
        private static RecyclerView recentPlayersList;

        protected RecyclerView.LayoutManager currentLayoutManager;
        protected RecyclerView.LayoutManager recentLayoutManager;

        private PlayerAdapter currentPlayerAdapter;
        private PlayerAdapter recentPlayerAdapter;

        private ArrayList<Player> currentPlayers = new ArrayList();

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlayerSelectionFragment newInstance(int sectionNumber) {
            PlayerSelectionFragment fragment = new PlayerSelectionFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlayerSelectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_players, container, false);

            Log.d(TAG, "onCreateView");

            currentPlayersList = (RecyclerView) rootView.findViewById(R.id.current_players);
            recentPlayersList = (RecyclerView) rootView.findViewById(R.id.recent_players);

            currentLayoutManager = new LinearLayoutManager(getActivity());
            recentLayoutManager = new LinearLayoutManager(getActivity());
            currentPlayersList.setLayoutManager(currentLayoutManager);
            recentPlayersList.setLayoutManager(recentLayoutManager);

            if (currentPlayerAdapter == null || recentPlayerAdapter == null) {
                currentPlayerAdapter = new PlayerAdapter(currentPlayers);
                recentPlayerAdapter = new PlayerAdapter(getActivity(), "players.json");
                currentPlayerAdapter.setCounterpartAdapter(recentPlayerAdapter);
                recentPlayerAdapter.setCounterpartAdapter(currentPlayerAdapter);
            }
            currentPlayersList.setAdapter(currentPlayerAdapter);
            recentPlayersList.setAdapter(recentPlayerAdapter);

            final EditText newPlayer = (EditText) rootView.findViewById(R.id.new_player_name);
            newPlayer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        return addPlayer(newPlayer);
                    }
                    return false;
                }
            });

            final Button add = (Button) rootView.findViewById(R.id.add_button);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addPlayer(newPlayer);
                }
            });

            return rootView;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        private boolean addPlayer(EditText newPlayer) {
            String name = newPlayer.getText().toString();
            if (!name.trim().isEmpty()) {
                Log.d(TAG, name);
                currentPlayerAdapter.addPlayer(new Player(newPlayer.getText().toString()));
                newPlayer.setText("");
                return true;
            }
            return false;
        }
    }

}
