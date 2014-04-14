package nasa.app.colombia.Android;


import nasa.app.colombia.Android.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RemoteViews;

public class MainActivity extends Activity {


	// Notification ID to allow for future updates
	private static final int MY_NOTIFICATION_ID = 1;

	// Notification Count
	private int mNotificationCount;

	// Notification Text Elements
	private final CharSequence tickerText = "La información de UPMEStats ha sido actualizada!";
	private final CharSequence contentTitle = "Notification";
	private final CharSequence contentText = "Información UPMEStats Actualizada.";

	// Notification Action Elements
	private Intent mNotificationIntent;
	private PendingIntent mContentIntent;

	// Notification Sound and Vibration on Arrival
	

	RemoteViews mContentView = new RemoteViews(
			"upmestats.app.colombia.Android",
			R.layout.custom_notification);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ImageButton loadButton = (ImageButton) findViewById(R.id.button1);
		loadButton.setOnClickListener(new OnClickListener() {
			@Override
		    public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						WebViewActivity5.class));
			
			}
		});		

		final ImageButton webButton = (ImageButton) findViewById(R.id.ImageButton03);
		webButton.setOnClickListener(new OnClickListener() {

			@Override
		    public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						WebViewActivity1.class));
			}
		});		

	}
}