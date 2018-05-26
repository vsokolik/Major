package ru.testapp.major.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.testapp.major.R;

public class TitlesViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_title)
    public TextView tvTitle;

    public TitlesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
