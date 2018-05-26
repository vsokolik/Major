package ru.testapp.major.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.testapp.major.BoxObject;
import ru.testapp.major.R;
import ru.testapp.major.app.MajorService;
import ru.testapp.major.app.MajorApp;
import ru.testapp.major.ui.adapters.TitlesAdapter;
import ru.testapp.major.ui.views.EmptyView;
import ru.testapp.major.utils.ExtraKeys;

public class MainActivity extends BaseActivity {

    @Inject
    MajorService majorService;

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

    @BindView(R.id.rv_titles)
    RecyclerView rvTitles;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    @BindView(R.id.parent_view)
    CoordinatorLayout parentView;
    @BindView(R.id.progress)
    RelativeLayout progressView;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.empty_view)
    EmptyView emptyView;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MajorApp.getAppComponent().inject(this);

        setContentView(R.layout.activity_with_toolbar);

        ViewStub viewStubContent = findViewById(R.id.view_stub_for_content);
        viewStubContent.setLayoutResource(R.layout.activity_main);
        viewStubContent.inflate();

        ViewStub viewStubAppBar = findViewById(R.id.view_stub_for_appbar);
        viewStubAppBar.setLayoutResource(R.layout.appbar_main);
        viewStubAppBar.inflate();

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitle("");

        this.context = this;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rvTitles.setLayoutManager(linearLayoutManager);

        final List<BoxObject> list = new ArrayList<>();

        final TitlesAdapter adapter = new TitlesAdapter(list);
        adapter.setRvItemClickListener(new TitlesAdapter.OnRvItemClickListener<BoxObject>() {
            @Override
            public void onClick(BoxObject boxObject) {
                Intent intent = new Intent();
                intent.putExtra(ExtraKeys.IMAGE_URL, boxObject.getImg());
                intent.putExtra(ExtraKeys.TITLE, boxObject.getTitle());
                intent.setClass(context, ImageActivity.class);
                startActivity(intent);
            }
        });
        rvTitles.setAdapter(adapter);

        emptyView.setVisibility(View.VISIBLE);

        btnUpdate.setVisibility(View.VISIBLE);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                btnUpdate.setEnabled(false);
                emptyView.setVisibility(View.GONE);
                showProgress(true, progressView, rlContent);

                majorService.getData().enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        btnUpdate.setEnabled(true);

                        if(response.isSuccessful()) {
                            GsonBuilder builder = new GsonBuilder();
                            Gson gson = builder.create();
                            try {
                                JSONArray jsonArray = new JSONArray(response.body());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String jsonItem = jsonArray.getString(i);
                                    BoxObject box = gson.fromJson(jsonItem, BoxObject.class);
                                    list.add(box);
                                }

                                adapter.notifyDataSetChanged();

                                showProgress(false, progressView, rlContent);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            showSnackBar(parentView, getString(R.string.mess_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        btnUpdate.setEnabled(true);
                        showSnackBar(parentView, getString(R.string.mess_no_connection));
                    }
                });
            }
        });
    }
}
