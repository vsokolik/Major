package ru.testapp.major.ui.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.testapp.major.R;
import ru.testapp.major.utils.ExtraKeys;
import ru.testapp.major.utils.ImageUtils;

public class ImageActivity extends SwipeDismissBaseActivity {

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.progress)
    RelativeLayout progressView;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.parent_view)
    CoordinatorLayout parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_with_toolbar);

        ViewStub viewStubContent = findViewById(R.id.view_stub_for_content);
        viewStubContent.setLayoutResource(R.layout.activity_image_screen);
        viewStubContent.inflate();

        ViewStub viewStubAppBar = findViewById(R.id.view_stub_for_appbar);
        viewStubAppBar.setLayoutResource(R.layout.appbar_main);
        viewStubAppBar.inflate();

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitle(getIntent().getStringExtra(ExtraKeys.TITLE));

        showProgress(true, progressView, rlContent);

        String imageUrl = getIntent().getStringExtra(ExtraKeys.IMAGE_URL);
        if(!TextUtils.isEmpty(imageUrl)){
            ImageUtils.load(imageUrl, ivIcon, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    showProgress(false, progressView, rlContent);
                }

                @Override
                public void onError(Exception e) {
                    showProgress(false, progressView, rlContent);
                    showSnackBar(parentView, getString(R.string.mess_error_loading_image));
                }
            });
        } else {
            showProgress(false, progressView, rlContent);
        }
    }
}
