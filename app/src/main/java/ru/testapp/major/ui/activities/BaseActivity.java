package ru.testapp.major.ui.activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    protected void showSnackBar(View parentView, String message) {
        Snackbar snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackClick();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    protected void onBackClick() {
        finish();
    }

    protected void showProgress(boolean isVisible, View progress, View content){
        progress.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        content.setVisibility(isVisible ? View.GONE : View.VISIBLE);
    }
}
