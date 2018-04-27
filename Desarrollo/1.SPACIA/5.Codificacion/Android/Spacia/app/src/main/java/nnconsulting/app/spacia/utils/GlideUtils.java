package nnconsulting.app.spacia.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Coded by junior on 27/11/16.
 */

public class GlideUtils {



    public static void loadImage(ImageView view, String url, Context context){

        Glide.with(context)
                .load(url)
                .into(view);
    }
    public static void loadImageWithoutCache(ImageView view, String url, Context context){

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }


    public static void loadImageWithCustomSize(ImageView view, String url, Context context,
                                               int width, int height){

        Glide.with(context)
                .load(url)
                .override(width,height)
                .into(view);
    }

    public static void loadImageCenterCrop(ImageView view, String url, Context context){

        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(view);
    }

    public static void loadImageCircleTransform(ImageView view, String url, Context context){

        Glide.with(context)
                .load(url)
                .transform(new CircleTransform(context))
                .into(view);
    }


    public static void loadImageDiskacheCircleTransform(ImageView view, String url, Context context){

        Glide.with(context)
                .load(url)
                .transform(new CircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(view);
    }

    public static void loadImageWithPlaceHolder(ImageView view, String url, Context context,
                                                int placeHolder, int errorLoader){

        Glide.with(context)
                .load(url)
                .placeholder(placeHolder)
                .error(errorLoader)
                .into(view);
    }

}
