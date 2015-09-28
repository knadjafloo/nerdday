package pingidentity.com.nerdday;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DownloadFilesTask().execute();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DownloadFilesTask extends AsyncTask<Void, Integer, String> {
        @Override
        protected String doInBackground(Void... params) {

            String API_URL = "https://httpbin.org/";

            // Create a very simple REST adapter which points the GitHub API endpoint.
            HttpbinClient client = ServiceGenerator.createService(HttpbinClient.class, API_URL);

            // Fetch and print a list of the contributors to this library.

            Response<IpAddress> ipAddress = null;
            try {
                ipAddress = client.getIpAddress().execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(ipAddress.body().origin);
            return ipAddress.body().origin;
        }


        protected void onPostExecute(String result) {
            if (result != null) {
                TextView timeView = (TextView) findViewById(R.id.mainText);
                timeView.setText("origin: {" + result + "}");
            }
        }
    }

}
