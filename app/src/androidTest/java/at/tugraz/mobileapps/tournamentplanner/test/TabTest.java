package at.tugraz.mobileapps.tournamentplanner.test;

import at.tugraz.mobileapps.tournamentplanner.SettingsActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class TabTest extends ActivityInstrumentationTestCase2<SettingsActivity> {
  	private Solo solo;
  	
  	public TabTest() {
		super("at.tugraz.mobileapps.tournamentplanner.SettingsActivity", SettingsActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'at.tugraz.mobileapps.tournamentplanner.SettingsActivity'
		solo.waitForActivity(at.tugraz.mobileapps.tournamentplanner.SettingsActivity.class, 2000);
        //Set default small timeout to 105820 milliseconds
		Timeout.setSmallTimeout(105820);
        //Click on Dorien
		solo.clickInRecyclerView(0, 0);
        //Click on Fiona
		solo.clickInRecyclerView(0, 0);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.new_player_name));
        //Enter the text: 'Günther'
		solo.clearEditText((android.widget.EditText) solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.new_player_name));
		solo.enterText((android.widget.EditText) solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.new_player_name), "Günther");
        //Press next button
		solo.pressSoftKeyboardNextButton();
        //Click on Add
		solo.clickOnView(solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.add_button));
        //Click on Stephan
		solo.clickInRecyclerView(0, 0);
        //Click on Start
		solo.clickOnView(solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.start_button));
        //Wait for activity: 'at.tugraz.mobileapps.tournamentplanner.PlayersActivity'
		assertTrue("at.tugraz.mobileapps.tournamentplanner.PlayersActivity is not found!", solo.waitForActivity(at.tugraz.mobileapps.tournamentplanner.PlayersActivity.class));
        //Scroll View to the right side
		solo.scrollViewToSide(solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.pager), Solo.RIGHT);
        //Scroll View to the left side
		solo.scrollViewToSide(solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.pager), Solo.LEFT);
        //Set progress on SeekBar
		solo.setProgressBar((android.widget.ProgressBar) solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.winner_bar, 1), 2);
        //Scroll View to the right side
		solo.scrollViewToSide(solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.pager), Solo.RIGHT);
        //Scroll View to the left side
		solo.scrollViewToSide(solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.pager), Solo.LEFT);
	}
}
