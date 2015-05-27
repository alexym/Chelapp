package Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.alexym.chelapp.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloudco on 26/05/15.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>  {
    // Atributos

    private static final String URL_BASE = "http://servidorexterno.site90.com/datos";
    private static final String URL_JSON = "/social_media.json";
    private static final String TAG = "PostAdapter";
    List<Post> items;
    public Bitmap imagenTemp;



    public RecycleViewAdapter(List items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView descripcion;

        public ViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen_view_card);
            nombre = (TextView) v.findViewById(R.id.nombre);
            descripcion = (TextView) v.findViewById(R.id.descripcion);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_card, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Post item = items.get(position);
        viewHolder.nombre.setText(items.get(position).getTitulo());
        viewHolder.descripcion.setText(items.get(position).getDescripcion());
        ImageRequest request = new ImageRequest(
                URL_BASE + item.getImagen(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {

                        imagenTemp= bitmap;
                    }
                }, 0, 0, null,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        //viewHolder.setImageResource(R.drawable.error);
                        Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                    }
                });
        viewHolder.imagen.setImageBitmap(imagenTemp);
        // Añadir petición a la cola
        //requestQueue.add(request);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;

    }



}
