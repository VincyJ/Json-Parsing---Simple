package com.example.jsonparser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	ListView lv;
	ArrayList<String> ad=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listView1);
        
        new AsyncTaskParseJson().execute();
    }
    
    public class AsyncTaskParseJson extends AsyncTask<String, String, String>
    {
    	final String TAG="AsyncTaskParseJson.java";
    	String YourJsonStringUrl="http://demo.codeofaninja.com/tutorials/json-example-with-php/index.php";
    	JSONArray dataJsonArr=null;
    	
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    	}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			try{
				JsonParser jParser=new JsonParser();
				JSONObject json=jParser.getJSONFromUrl(YourJsonStringUrl);
				dataJsonArr=json.getJSONArray("Users");
				
				for(int i=0;i<dataJsonArr.length();i++){
					JSONObject c=dataJsonArr.getJSONObject(i);
					
					String firstname=c.getString("firstname");
					String lastname=c.getString("lastname");
					String username=c.getString("username");
					
					Log.e(TAG,"firstname:"+firstname+",lastname:"+lastname+",username:"+username);
					ad.add(firstname);
				}
			}
				catch (JSONException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			return null;
		}
		
		@Override
		protected void onPostExecute(String StrFromDoInBg) {
			// TODO Auto-generated method stub
			super.onPostExecute(StrFromDoInBg);
			ArrayAdapter<String> adp=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,ad);
			lv.setAdapter(adp);
		}	
    }
}
