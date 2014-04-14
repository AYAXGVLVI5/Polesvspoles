package nasa.app.colombia.Android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import nasa.app.colombia.Android.R;


import android.app.ListActivity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.ConsoleMessage;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NetworkingAndroidHttpClientJSONActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new HttpGetTask().execute();
	}

	private class HttpGetTask extends AsyncTask<Void, Void, List<String>> {

		private static final String TAG = "HttpGetTask";

		// Get your own user name at http://www.geonames.org/login
		private static final String USER_NAME = "upmestats";

		private static final String URL = "http://10.0.2.2:3003";

		AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

		@Override
		protected List<String> doInBackground(Void... params) {
			HttpGet request = new HttpGet(URL);
			JSONResponseHandler responseHandler = new JSONResponseHandler();
			try {
				return mClient.execute(request, responseHandler);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(final List<String> result) {
			if (null != mClient)
				mClient.close();
			setListAdapter(new ArrayAdapter<String>(
					NetworkingAndroidHttpClientJSONActivity.this,
					R.layout.list_item, result));	
                    ListView lv = getListView(); 
                    lv.setTextFilterEnabled(true);
                    lv.setOnItemClickListener(new OnItemClickListener(){                      	
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    	 //  Toast.makeText(getApplicationContext(),
           				//		((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                    	 //  Intent graphactivity = new Intent(NetworkingAndroidHttpClientJSONActivity.this,
                    	//		   GraphButtonActivity2.class);                    	   
                    	 //  graphactivity.putStringArrayListExtra("result",(ArrayList<String>) result);                    	   
                    	//   startActivity(graphactivity);                    	   
                       }
                    });            
		}

	}

	private class JSONResponseHandler implements ResponseHandler<List<String>> {

		private static final String FECHA_TAG = "Fecha";
		private static final String HISTORICO_TAG = "historico";
		private static final String ALTO_TAG = "alto";
		private static final String DB_TAG = "Energía Electrica";
		private static final String id_TAG = "_id";


		@Override
		public List<String> handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			List<String> result = new ArrayList<String>();
			
			String JSONResponse = new BasicResponseHandler()
					.handleResponse(response);
			try {

				// Get top-level JSON Object - a Map
				JSONObject responseObject = (JSONObject) new JSONTokener(
						JSONResponse).nextValue();

				// Extract value of "DB_TAG" key -- a List
				JSONArray inputdata = responseObject
						.getJSONArray(DB_TAG);

				// Iterate over main list
			/*	for (int idx = 0; idx < responseObject.length(); idx++) {
					// Get main names
					JSONObject receiveddata = (JSONObject) inputdata.get(idx);
					if( responseObject.names().get(idx).toString().contains(id_TAG) ){
					}
					else{
						result.add(""+responseObject.names().get(idx).toString());
					}
					//result.add(""+receiveddata.get(FECHA_TAG)+" "+receiveddata.get(HISTORICO_TAG) );
					// Summarize received data as a string and add it to
					// result
					/*result.add(ALTO_TAG + ":"
							+ receiveddata.length() + ","
							+ HISTORICO_TAG + ":"
							+ receiveddata.getString(HISTORICO_TAG) + ","
							+ FECHA_TAG + ":"
							+ receiveddata.get(FECHA_TAG));
				} */
				for (int idx = 0; idx < inputdata.length(); idx++) {
					// Get main names
					JSONObject receiveddata = (JSONObject) inputdata.get(idx);
					
					result.add(""+receiveddata.get(HISTORICO_TAG) );
					
					// Summarize received data as a string and add it to
					// result
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
}