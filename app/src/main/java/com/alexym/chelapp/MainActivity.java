package com.alexym.chelapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Utils.Post;
import Utils.RecycleViewAdapter;
import Utils.RecyclerItemClickListener;
import network.VolleySingleton;


public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private final String TAG = "MainActivity";
    SwipeRefreshLayout swipeRefresh;
    List items = new ArrayList<>();

    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;

    private static final String URL_BASE = "http://servidorexterno.site90.com/datos";
    private static final String URL_JSON = "/social_media.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        recycler.setLayoutManager(mLayoutManager);

// Crear un nuevo adaptador
        //adapter = new AnimeAdapter(items);
       // adapter = new RecycleViewAdapter(MainActivity.this);
        recycler.setAdapter(adapter);

        recycler.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, recycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Log.i(TAG, "Esta es la position: " + String.valueOf(position));
//                Intent i = new Intent(MainActivity.this, DetailActivity.class);
//                Anime cardClick=(Anime)items.get(position);
//                Log.i(TAG, String.valueOf(cardClick.getImagen()));
//                i.putExtra("imagen",cardClick.getImagen());
//                View sharedView = view.findViewById(R.id.imagen_view_card);
//
//                String transitionName = getString(R.string.image_card_animation);
//
//                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(KawaiiCards.this, sharedView, transitionName);
//                startActivity(i, transitionActivityOptions.toBundle());

            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));

        this.setTitle("ChelApp");
        //Propiedades para el efecto de actualizar listado
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorScheme(android.R.color.holo_blue_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_red_light);
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
    //Evento que se ejecuta al actualizar el listado con swipe
    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {


// Finalizar swipeRefresh
                swipeRefresh.setRefreshing(false);
            }
        }, 8000);
    }

    public void cargaInfo(){
/// Get a RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();


// Add a request (in this example, called stringRequest) to your RequestQueue.


        // Nueva petición JSONObject

        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_BASE + URL_JSON,
                "",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        items = parseJson(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }
        );




        // Añadir petición a la cola
        VolleySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }
    public List<Post> parseJson(JSONObject jsonObject){
        // Variables locales
        List<Post> posts = new ArrayList();
        JSONArray jsonArray= null;

        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("items");

            for(int i=0; i<jsonArray.length(); i++){

                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);

                    Post post = new Post(
                            objeto.getString("titulo"),
                            objeto.getString("descripcion"),
                            objeto.getString("imagen"));


                    posts.add(post);

                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return posts;
    }

}
