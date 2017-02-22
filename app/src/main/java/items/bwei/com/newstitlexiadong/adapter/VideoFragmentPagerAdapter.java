package items.bwei.com.newstitlexiadong.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class VideoFragmentPagerAdapter extends FragmentPagerAdapter {
    Context context;
    List<Fragment> list;
    String[] title;
    public VideoFragmentPagerAdapter(FragmentManager fm, Context context, List<Fragment> list, String[] title) {
        super(fm);
        this.context=context;
        this.title=title;
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}