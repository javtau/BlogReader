package com.jjv.blogreader_javier_lozano.UI;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jjv.blogreader_javier_lozano.R;
import com.jjv.blogreader_javier_lozano.adapters.Blog_Adapter;
import com.jjv.blogreader_javier_lozano.objects.Blog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends ListActivity{

    public static final String URL_ENTRADA = "nentradas";
    private final static String BASE_URL = "http://blog.teamtreehouse.com/api/get_recent_summary/?count=";
    private final static String TAG = MainActivity.class.getSimpleName();
    private String blog_url;
    private ProgressBar progressBar;
    private Blog[] blogs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Intent i = getIntent();
        int nentradas = i.getIntExtra(WelcomeActivity.NUMENTRADAS,1);
        blog_url = BASE_URL+nentradas;
        consulta_blogs();


    }

    private void consulta_blogs() {
        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(blog_url).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        //Response response = call.execute();

                        String result = response.body().string();

                        if (response.isSuccessful()) {
                            blogs = getBlogs(result);
                            setBlogAdapter();

                        } else {
                            alerUserAboutError();
                        }

                    } catch (IOException | JSONException ex) {
                        Log.e(TAG, "Excepcion: ", ex);

                    }
                }
            });

        } else {
            Toast.makeText(this, "Error de conexion", Toast.LENGTH_SHORT).show();
        }
    }

    private void setBlogAdapter() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BaseAdapter adapter = new Blog_Adapter(MainActivity.this,blogs);
                setListAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void alerUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "Error dialog:");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
    public Blog[] getBlogs(String jsondata) throws JSONException {
        JSONObject result = new JSONObject(jsondata);
        JSONArray array = result.getJSONArray("posts");
        Blog[] blogs = new Blog[array.length()];
        for (int i = 0; i < array.length(); i++){
            JSONObject blogData = array.getJSONObject(i);
            blogs[i] = new Blog();
            blogs[i].setTitulo(blogData.getString("title"));
            blogs[i].setAutor(blogData.getString("author"));
            blogs[i].setBlogUrl(blogData.getString("url"));

        }
        return  blogs;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i =  new Intent(this, WebActivity.class);
        i.putExtra(URL_ENTRADA,blogs[position].getBlogUrl());
        startActivity(i);
    }


}
