package items.bwei.com.newstitlexiadong.Https;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class HttpUtilsx {

    public static void LoadData(String url,CallBackData callbackdata){
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    public interface CallBackData<T>{
        void success(T t) ;
    }
}