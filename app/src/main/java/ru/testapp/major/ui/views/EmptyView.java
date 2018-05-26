package ru.testapp.major.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.testapp.major.R;

public class EmptyView extends RelativeLayout {

    @BindView (R.id.tv_base_title)
    TextView tvTitle;

    private Context context;
    private String title;


    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        inflate(context, R.layout.view_empty, this);
        ButterKnife.bind(this);
        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.EmptyView);
        if (attr == null) {
            return;
        }

        try {
            title = attr.getString(R.styleable.EmptyView_ev_title);
            tvTitle.setText(title);

        } finally {
            attr.recycle();
        }
    }

    public void setTitle(int resourceID) {
        setTitle(context.getString(resourceID));
    }

    public void setTitle(String title) {
        this.title = title;
        tvTitle.setText(title);
    }

}