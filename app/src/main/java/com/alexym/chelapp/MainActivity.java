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

import java.util.ArrayList;
import java.util.List;

import Utils.RecycleViewAdapter;
import Utils.RecyclerItemClickListener;


public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private final String TAG = "MainActivity";
    SwipeRefreshLayout swipeRefresh;
    List items = new ArrayList<>();

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
        adapter = new RecycleViewAdapter(MainActivity.this);
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

}
