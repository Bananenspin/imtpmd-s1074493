package dennis.keirsgieter.week7;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	TabPagerAdapter tabPagerAdapter;
	ViewPager mViewPager;
	SendTabFragment sendTab;
	OpdrachtenTabFragment opdrachtTab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// we maken onze eigen adapter aan om de fragments aan toe te voegen
		// de adapter is een klasse die data (b.v. de fragments) en
		// de interface (om de fragments te tonen) aan elkaar koppelt
		List<Fragment> fragmentList = new ArrayList<Fragment>();
		this.sendTab = new SendTabFragment();
		this.opdrachtTab = new OpdrachtenTabFragment();
		fragmentList.add(sendTab);
		fragmentList.add(opdrachtTab);

		tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(),
				fragmentList);

		// we moeten ook een viewpager hebben die de pagina's (fragments) op het
		// scherm toont
		// hier koppelen we ook de adapter aan
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(tabPagerAdapter);

		// We kunnen ook swipen, maar de Tabs bovenaan het beeld gaan niet
		// automatisch mee.
		// We moeten de onPageChangeListener instellen van de viewPager
		// als deze listener door middel van swipe wordt aangeroepen verwisselen
		// we de tabs
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// stel alle tabs in (dit zijn de tabbladen bovenin, niet de pagina's
		// zelf)
		// deze tabs worden verwisseld d.m.v. gebruiker touch en swipe (zie
		// hierboven)
		Tab sendTab = actionBar.newTab();
		sendTab.setText("Send Tab");
		sendTab.setTabListener(this);
		actionBar.addTab(sendTab);

		Tab opdrachtTab = actionBar.newTab();
		opdrachtTab.setText("Opdrachten tab");
		opdrachtTab.setTabListener(this);
		actionBar.addTab(opdrachtTab);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void  shareData(String string)
	{
		opdrachtTab.shareData(string);
	}

}
