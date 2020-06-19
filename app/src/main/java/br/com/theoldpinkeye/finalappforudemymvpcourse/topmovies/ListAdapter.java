package br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.theoldpinkeye.finalappforudemymvpcourse.R;

/**
 * Created by Just Us on 29/11/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {
    private List<ViewModel> list;

    public ListAdapter(List<ViewModel> list){
        this.list = list;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);

        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        holder.itemName.setText(list.get(position).getName());
        holder.countryName.setText(list.get(position).getCountry());


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ListItemViewHolder extends  RecyclerView.ViewHolder{


        public TextView itemName;
        public TextView countryName;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textView_fragmentlist_task_name);
            countryName = itemView.findViewById(R.id.textView_fragmentlist_country_name);
        }
    }
}
