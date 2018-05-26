package ru.testapp.major.utils;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import ru.testapp.major.R;

public class ImageUtils {
    public static void load(String url, ImageView imageView, Callback callback){
        Picasso.get()
                .load(url)
                .error(R.drawable.ic_error_outline)
                .into(imageView, callback);
    }
}
