package ru.testapp.major.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.testapp.major.BoxObject;
import ru.testapp.major.R;
import ru.testapp.major.ui.holders.TitlesViewHolder;

public class TitlesAdapter extends RecyclerView.Adapter<TitlesViewHolder>{

    private List<BoxObject> mapTitles;

    private OnRvItemClickListener<BoxObject> onRvItemClickListener;

    public TitlesAdapter(List<BoxObject> titles){
        this.mapTitles = titles;
    }

    @NonNull
    @Override
    public TitlesViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRvItemClickListener.onClick(mapTitles.get(viewType));
            }
        });
        return new TitlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TitlesViewHolder holder, int position) {
        holder.tvTitle.setText(mapTitles.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mapTitles.size();
    }

    public interface OnRvItemClickListener<BoxObject> {
        void onClick(BoxObject boxObject);
    }

    public void setRvItemClickListener(OnRvItemClickListener<BoxObject> listener) {
        this.onRvItemClickListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
