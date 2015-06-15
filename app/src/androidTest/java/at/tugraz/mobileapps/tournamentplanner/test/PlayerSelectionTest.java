package at.tugraz.mobileapps.tournamentplanner.test;

import at.tugraz.mobileapps.tournamentplanner.PlayersActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class PlayerSelectionTest extends ActivityInstrumentationTestCase2<PlayersActivity> {
  	private Solo solo;
  	
  	public PlayerSelectionTest() {
		super(PlayersActivity.class);
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
        //Wait for activity: 'at.tugraz.mobileapps.tournamentplanner.PlayersActivity'
		solo.waitForActivity(at.tugraz.mobileapps.tournamentplanner.PlayersActivity.class, 2000);
        //Set default small timeout to 776162 milliseconds
		Timeout.setSmallTimeout(776162);
        //Click on Stephan
		solo.clickInRecyclerView(0, 0);
        //Click on Fiona
		solo.clickInRecyclerView(1, 0);
        //Click on Name
		solo.clickOnView(solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.new_player_name));
        //Click on Name
		solo.clickOnView(solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.new_player_name));
        //Enter the text: 'Jürgen'
		solo.clearEditText((android.widget.EditText) solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.new_player_name));
		solo.enterText((android.widget.EditText) solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.new_player_name), "Jürgen");
        //Scroll View to the right side
		solo.scrollViewToSide(solo.getView(at.tugraz.mobileapps.tournamentplanner.R.id.pager), Solo.RIGHT);
	}
}
