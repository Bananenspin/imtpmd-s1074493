package dennis.keirsgieter.week7;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {
	// dit is de lijst met alle items
	// de lijst wordt gevuld in een andere klasse, we gebruiken de lijst hier
	// alleen om uit te lezen
	private ArrayList<MyListItem> itemArrayList;

	// de constructor van de adapter
	public MyListAdapter(ArrayList<MyListItem> itemArrayList) {
		// we gebruiken een bestaande arraylist om de data uit te halen
		this.itemArrayList = itemArrayList;
	}

	@Override
	public int getCount() {
		// geef het aantal objecten terug dat in de itemArrayList staan
		return itemArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// geef het item terug dat op de gevraagde positie staat in de
		// itemArrayList
		// in dit geval is dat het object dat op de positie "position" staat in
		// de arraylist
		return itemArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// geef de index terug van het element die op de gevraagde positie staat
		// in de itemArrayList
		// in dit geval is de positie "position" gelijk aan de index in de
		// arraylist
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// de convertView is de view die al een keer eerder getoond is door de
		// ListView
		// deze kunnen we hergebruiken als hij al bestaat, maar
		// kan ook null zijn als de lijst voor het eerst getoond wordt
		// dit controleren we hieronder in het if statement
		if (convertView == null) {
			// bij een niet-bestaand listview item wordt een nieuw item gemaakt
			// gebruik als Context object voor de inflater parent.getContext()
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.listview_item, parent,
					false);
		}

		// haal het item op uit de lijst dat op de meegegeven positie getoond
		// moet worden
		MyListItem item = (MyListItem) getItem(position);

		// vind het TextView en de CheckBox interface objecten waar de naam en
		// de check van het item in moet komen
		TextView itemNaam = (TextView) convertView.findViewById(R.id.itemnaam);

		// vul het TextView in met de naam van het item
		itemNaam.setText(item.getNaam());

		return convertView;
	}

}
