package com.tonny.myapps.networkconnectivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class NetworkConnectivityActivity extends Activity {
    TextView responseView;
    TextView connectivityTest;
    EditText inputUrl;
    RelativeLayout userContentArea;
    TextView textView;
    String resp;
    private static String myUrl = "https://graph.facebook.com/saroj09";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_connectivity);

        responseView = (TextView) findViewById(R.id.response);
        connectivityTest = (TextView) findViewById(R.id.connectivityTest);
        inputUrl = (EditText) findViewById(R.id.inputUrl);
        userContentArea = (RelativeLayout) findViewById(R.id.userContentArea);
        if (isConnected()) {
            connectivityTest.setText("Connected");
            connectivityTest.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        } else {
            connectivityTest.setText("No Internet Connection");
            connectivityTest.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.testConnection:
                performNetworkActivity();
                break;
            default:
                responseView.setText("LOL nothing happened");
        }
    }

    private void performNetworkActivity() {
        if (isConnected()) {
            connectivityTest.setText("It's Working Dude! I got the network connection");
            connectivityTest.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            new DownloadWebpage().execute();
            int prevTvId = 0;
            Random random = new Random();
            System.out.print("Response : " + resp);
            for (int i = 0; i < 10; i++) {
                final TextView textDynamic = new TextView(this);
                final LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                textDynamic.setId(prevTvId + 1);
                params.addRule(RelativeLayout.BELOW, prevTvId);
                textDynamic.setText(resp);
                textDynamic.setBackgroundColor(random.nextInt() | 0xff000000);
                textDynamic.setTextColor(Color.RED);
                textDynamic.setTextScaleX(2);
                textDynamic.setTextSize(20);
                textDynamic.isClickable();
                textDynamic.setPadding(10, 10, 10, 10);
                prevTvId = prevTvId + 1;
                userContentArea.addView(textDynamic, params);
            }
        } else {
            responseView.setText("OOP's It's not Working Dude!");
        }
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return null != connectivityManager.getActiveNetworkInfo() && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private class DownloadWebpage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            InputStream is = null;
            String content = null;
            try {
                URL url = null;
                if (null != inputUrl.getText() && !inputUrl.getText().toString().isEmpty()) {
                    url = new URL(inputUrl.getText().toString());
                } else {
                    url = new URL(myUrl);
                }
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                int responseCode = connection.getResponseCode();
                System.out.print(responseCode);
                is = connection.getInputStream();
                Reader reader = new InputStreamReader(is, "UTF-8");
                char[] buffer = new char[1000];
                reader.read(buffer);
                content = new String(buffer);
            } catch (IOException ioe) {
                content = "OOP's Unable to connect to the url";
            } finally {
                if (null != is) {
                    try {
                        is.close();
                    } catch (IOException ioe) {
                        content = "OOP's Unable to close Input Stream";
                    }
                }
            }
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            responseView.setText(result);
            int prevTvId = 0;
            Random random = new Random();
            System.out.print(resp);
            for (int i = 0; i < 10; i++) {
                final TextView textDynamic = getTextView();
                final LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                textDynamic.setId(prevTvId + 1);
                params.addRule(RelativeLayout.BELOW, prevTvId);
                textDynamic.setText(resp);
                textDynamic.setBackgroundColor(random.nextInt() | 0xff000000);
                textDynamic.setTextColor(Color.RED);
                textDynamic.setTextScaleX(2);
                textDynamic.setTextSize(20);
                textDynamic.isClickable();
                textDynamic.setPadding(10, 10, 10, 10);
                prevTvId = prevTvId + 1;
                userContentArea.addView(textDynamic, params);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.network_connectivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public TextView getTextView() {
        return new TextView(this);
    }
}
