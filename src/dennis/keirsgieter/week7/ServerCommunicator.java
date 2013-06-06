package dennis.keirsgieter.week7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.util.Log;
import android.widget.Toast;

public class ServerCommunicator implements Runnable {
	private SendTabFragment activity;
	private Thread thread;

	private String ip;
	private String naam;
	private String vraag;
	private String reponse = null;
	private int port;

	public ServerCommunicator(SendTabFragment activity, String ip, int port,
			String naam, String vraag) {
		// we gebruiken de activity om de userinterface te updaten
		this.activity = activity;

		// gegevens om naar de server te verbinden en een message te sturen
		this.ip = ip;
		this.port = port;
		this.vraag = vraag;
		this.naam = naam;

		// de nieuwe thread kan tekst verzenden en ontvangen van en naar een
		// server
		this.thread = new Thread(this);
		this.thread.start();
	}

	// dit is een methode die niet op de UI thread wordt aangeroepen, maar door
	// onze eigen nieuwe thread
	// we kunnen dus niet zomaar ontvangen berichten in een userinterface object
	// stoppen m.b.v. view.setText( message )
	// hier gebruiken we de activity voor: activity.runOnUiThread( activity )
	@Override
	public void run() {
		Log.d("debug", "In de thread");
		try {
			Socket serverSocket = new Socket();
			serverSocket.connect(new InetSocketAddress(this.ip, this.port),
					4000);

			// verzend een bericht naar de server
			this.sendMessage(this.vraag, serverSocket);

			this.reponse = waitForResponse(serverSocket);

			Log.d("debug", "In de try run");

		}

		catch (UnknownHostException e) {
			Log.d("debug", "can't find host");
		}

		catch (SocketTimeoutException e) {
			Log.d("debug", "time-out");
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		// catch (InterruptedException e)
		// {
		// e.printStackTrace();
		// }
		
		this.activity.setReceivedServerMessage(reponse);

	}

	// ook deze methoden kunnen niet naar de UI direct communiceren, hou hier
	// rekening mee
	private void sendMessage(String message, Socket serverSocket) {
		OutputStreamWriter outputStreamWriter = null;

		Log.d("debug", "in de sendMessage");
		try {
			outputStreamWriter = new OutputStreamWriter(
					serverSocket.getOutputStream());
			Log.d("debug", "in de sendMessage try");
		}

		catch (IOException e2) {
			e2.printStackTrace();
		}

		if (outputStreamWriter != null) {
			BufferedWriter bufferedWriter = new BufferedWriter(
					outputStreamWriter);
			PrintWriter writer = new PrintWriter(bufferedWriter, true);

			writer.println(message);

			Log.d("debug", "in de sendMessage en write message aangeroepen + "
					+ message);
		}
	}

	// wacht op server bericht (na versturen)
	private String waitForResponse(Socket serverSocket) {
		String serverMessage = null;
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = serverSocket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (bufferedReader != null) {
			try {
				serverMessage = bufferedReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Log.d("debug", serverMessage);
		return serverMessage;
	}

}
