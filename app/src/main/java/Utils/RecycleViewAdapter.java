package Utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

/**
 * Created by Cloudco on 26/05/15.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>  {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView visitas;

        public ViewHolder(View v) {
            super(v);
            //imagen = (ImageView) v.findViewById(R.id.imagen_view_card);
            //nombre = (TextView) v.findViewById(R.id.nombre);
            //visitas = (TextView) v.findViewById(R.id.visitas);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
