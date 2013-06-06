package dennis.keirsgieter.week7;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A dummy fragment representing a section of the app, but that simply displays
 * dummy text.
 */
public class OpdrachtenTabFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	private JSONObject jsonObject;

	public OpdrachtenTabFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_opdrachten,
				container, false);
		
		ListView listView = (ListView) rootView.findViewById( R.id.listView );
		
		Log.d("kutzooi", rootView.findViewById(R.id.listView)+"" );
		
		//we maken een nieuwe arraylist waar we al onze data in zetten
        ArrayList<MyListItem> itemArrayList = new ArrayList<MyListItem>();
        
        //arraylist vullen
        
        itemArrayList.add(new MyListItem(1, "Week2 - ListView"));
        itemArrayList.add(new MyListItem(2, "Week2 - Drag-n-Drop"));
        itemArrayList.add(new MyListItem(3, "Week3 - Tabblad widget"));
        itemArrayList.add(new MyListItem(4, "Week4 - AsyncTask server-client"));
        itemArrayList.add(new MyListItem(5, "Week5 - Gegevensopslag met SharedPreferences"));
        itemArrayList.add(new MyListItem(6, "Week5 - Gegevensopslag met SQLite"));
        itemArrayList.add(new MyListItem(7, "Week6 - WebView browser"));
        
        MyListAdapter arrayAdapter = new MyListAdapter(itemArrayList);
        
        arrayAdapter.notifyDataSetChanged();
        
        listView.setAdapter(arrayAdapter);
		return rootView;
	}

	public void shareData(String string) {
		//Log.d("data", string);
		jsonObject = null;

		try {
			jsonObject = new JSONObject(string);
			JSONArray opdracht = jsonObject.getJSONArray("opdracht");
			Log.d("json",opdracht.toString());
			
		} catch (JSONException e) {
			Log.d("debug", "try gaat fout");
			e.printStackTrace();
		}

	}
}
