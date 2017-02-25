package items.bwei.com.newstitlexiadong.application;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class XutilsApplication extends Application {
    public static boolean flag = true;
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);

            ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480,480)
                .threadPriority(4)//设置线程优先级
                .threadPoolSize(2)//设置线程池大小
                .memoryCacheSize(2*1024*1024)//设置内存缓存区大小
                .diskCacheSize(20*1024*1024)//设置磁盘缓存区大小

                .build();
        ImageLoader.getInstance().init(configuration);
        // 默认设置为日间模式
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);

    }
}