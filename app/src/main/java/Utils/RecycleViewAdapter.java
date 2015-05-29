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
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import network.VolleySingleton;

/**
 * Created by Cloudco on 26/05/15.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>  {
    // Atributos

    public static final String URL_BASE = "http://servidorexterno.site90.com/datos";
    private static final String URL_JSON = "/social_media.json";
    private static final String TAG = "RecyleViewAdapter";
    List<Post> items;
    public Bitmap imagenTemp;
    Context context;



    public RecycleViewAdapter(List items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView descripcion;
        public NetworkImageView mNetworkImageView;

        public ViewHolder(View v) {
            super(v);
            //imagen = (ImageView) v.findViewById(R.id.imagen_view_card);
            nombre = (TextView) v.findViewById(R.id.nombre);
            descripcion = (TextView) v.findViewById(R.id.descripcion);
            mNetworkImageView = (NetworkImageView) v.findViewById(R.id.networkImageView);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context= viewGroup.getContext();
        View v = LayoutInflater.from(context)
                .inflate(R.layout.row_card, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Post item = items.get(position);
        viewHolder.nombre.setText(items.get(position).getTitulo());
        viewHolder.descripcion.setText(items.get(position).getDescripcion());
//        ImageRequest request = new ImageRequest(
//                URL_BASE + item.getImagen(),
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//
//                        imagenTemp= bitmap;
//                    }
//                }, 0, 0, null,null,
//                new Response.ErrorListener() {
//                    public void onErrorResponse(VolleyError error) {
//                        //viewHolder.setImageResource(R.drawable.error);
//                        Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
//                    }
//                });
//        viewHolder.imagen.setImageBitmap(imagenTemp);
        // Añadir petición a la cola
        //requestQueue.add(request);
        ImageLoader mImageLoader;



// Get the ImageLoader through your singleton class.
        mImageLoader = VolleySingleton.getInstance(context).getImageLoader();

// Set the URL of the image that should be loaded into this view, and
// specify the ImageLoader that will be used to make the request.
        viewHolder.mNetworkImageView.setImageUrl(URL_BASE + item.getImagen(), mImageLoader);
    }

    @Override
    public int getItemCount() {

        return items != null ? items.size() : 0;

    }



}
