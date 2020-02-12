package com.example.phpmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView text;
    EditText name, id;
    Button insert, display;
    String name1, id1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intitializeVsr();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id1 = id.getText().toString();
                name1 = name.getText().toString();

                //String link = "http://192.168.10.6/employeeFile.php?id=" + id1 + "& name=" + name1;

                String link = "http://192.168.10.6/employeeFile.php";
                new AsyncTasktoCsllServer().execute(link);


            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id1 = id.getText().toString();

                String link = "http://192.168.10.6/employeeFile.php";
                new AsyncTasktoCsllServer().execute(link);
            }
        });
    }


    void intitializeVsr() {

        text = (TextView) findViewById(R.id.textView);
        id = (EditText) findViewById(R.id.id);
        name = (EditText) findViewById(R.id.name);
        insert = (Button) findViewById(R.id.insert);
        display = (Button) findViewById(R.id.display);
    }


    private class AsyncTasktoCsllServer extends AsyncTask<String, Void, String> {

        String result= null;

        protected String doInBackground(String... params) {


            HttpURLConnection con = null;
            String id2 = null;
            String name2 = null;


            try {
                id2 = URLEncoder.encode(id1, "UTF-8");
                //name2 = URLEncoder.encode(name1, "UTF-8");

                URL url = new URL(params[0]);
                con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                //con.setDoInput(false);
                con.setRequestMethod("POST");




                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write("id2=" + id2);        //String name must ne matched with variable in php script
                //writer.write("name2=" + name2);
                writer.close();



                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    result = "Successful";


                }
                else {
                    result = "Failed";

                }


            }

            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            con.disconnect();
            return  result;
        }

        protected void onPostExecute(String result) {

            text.setText(result);

        }

    }

}


