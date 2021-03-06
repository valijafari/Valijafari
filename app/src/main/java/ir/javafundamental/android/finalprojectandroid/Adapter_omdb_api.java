package ir.javafundamental.android.finalprojectandroid;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter_omdb_api extends RecyclerView.Adapter<Adapter_omdb_api.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Search> Data;
    private Context mContext;
    private ItemClickListener mClickListener;

    public Adapter_omdb_api  (List<Search> ListSearchClass ,Context context)
    {
        this.mInflater = LayoutInflater.from(context);
        Data=ListSearchClass;
        this.mContext = context;
    }

    @NonNull
    @Override
    public Adapter_omdb_api.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.myviewholder_omdb_api, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_omdb_api.ViewHolder holder, int position) {
        Search dto_search = Data.get(position);
        try {
            holder.txt_Title.setText(String.valueOf(dto_search.getTitle()));
            holder.txt_year.setText(String.valueOf(dto_search.getYear()));
            holder.txt_imdbID.setText(String.valueOf(dto_search.getImdbID()));
            //holder.txt_rated.setText(String.valueOf(dto_search.getRated()));
            try
            {
                Glide.with(this.mContext).load(dto_search.getPoster()).into(holder.img_poster);
            }
             catch (Exception ex){
                 holder.img_poster.setBackground(ContextCompat.getDrawable(holder.img_poster.getContext(), R.drawable.ic_launcher_background));
            }
        }
        catch (Exception ex){
            String xxx = ex.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //        Button myButton;
        TextView txt_Title;
        TextView txt_year;
        TextView txt_imdbID;
        Button btnMore;
        ImageView img_poster;

        ViewHolder(View itemView) {
            super(itemView);
            txt_Title = itemView.findViewById(R.id.title);
            txt_year = itemView.findViewById(R.id.year);
            txt_imdbID = itemView.findViewById(R.id.imdbID);
            img_poster = itemView.findViewById(R.id.imgShort);
            btnMore = itemView.findViewById(R.id.btnMore);
            btnMore.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick( txt_imdbID.getText().toString());
        }
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick( String imdbID);
    }

}
