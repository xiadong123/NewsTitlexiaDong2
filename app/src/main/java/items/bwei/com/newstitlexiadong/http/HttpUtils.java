package items.bwei.com.newstitlexiadong.http;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class HttpUtils {

    public static <T>void loadDataFromServer(String url,final Class<T> clazz, final CallbackNewsData callbackData) {

        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //谁初始化的 就回调谁的 success
                Log.d("mMessage"," "+result);
                Gson gson = new Gson();
                ArrayList<T> newsContents = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    Iterator<String> keys = jsonObject.keys();

                    while (keys.hasNext()) {
                        String next = keys.next();
                        JSONArray jsonArray = jsonObject.optJSONArray(next);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            T newsContent = gson.fromJson(jsonObject1.toString(), clazz);
                            newsContents.add(newsContent);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //final T t = gson.fromJson(result, clazz);
                callbackData.success(newsContents);
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
}