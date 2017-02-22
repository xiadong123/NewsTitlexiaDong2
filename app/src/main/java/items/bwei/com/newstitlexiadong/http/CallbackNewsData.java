package items.bwei.com.newstitlexiadong.http;

import java.util.ArrayList;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public interface CallbackNewsData<T> {
    void success(ArrayList<T> newsContents);
}