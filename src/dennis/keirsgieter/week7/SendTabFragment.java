package dennis.keirsgieter.week7;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * De send tab pagina waarin een naam, vraag, ip en poort kan worden ingevuld om
 * met de server te communiceren
 */
public class SendTabFragment extends Fragment implements OnClickListener {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	public View rootView;
	private ServerCommunicator serverCommunicator;

	public SendTabFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_main_send, container,
				false);

		Button button = (Button) rootView.findViewById(R.id.buttonsend);
		button.setOnClickListener(this);

		return rootView;
	}

	@Override
	public void onClick(View v) {
		Log.d("debug", "klik");

		// haal gegevens op uit de UI
		EditText editTextNaam = (EditText) rootView
				.findViewById(R.id.edittextnaam);
		String naam = editTextNaam.getText().toString();

		EditText editTextVraag = (EditText) rootView
				.findViewById(R.id.edittextvraag);
		String vraag = editTextVraag.getText().toString();

		EditText editTextIp = (EditText) rootView.findViewById(R.id.edittextip);
		String serverIp = editTextIp.getText().toString();

		EditText editTextPort = (EditText) rootView
				.findViewById(R.id.edittextport);
		int serverPort = Integer.parseInt(editTextPort.getText().toString());

		// maak een nieuwe verbinding met de server
		this.serverCommunicator = new ServerCommunicator(this, serverIp,
				serverPort, naam, vraag);

		// bij ontvangen van een bericht wordt de methode
		// setReceivedServerMessage en run aangeroepen
		// om het bericht van de server binnen te halen en op het scherm te
		// tonen

	}

	public void setReceivedServerMessage(String string) {
		((MainActivity) getActivity()).shareData(string);
	}
}
