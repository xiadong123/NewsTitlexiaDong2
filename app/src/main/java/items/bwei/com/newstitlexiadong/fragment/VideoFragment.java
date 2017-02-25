package items.bwei.com.newstitlexiadong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import items.bwei.com.newstitlexiadong.R;
import items.bwei.com.newstitlexiadong.adapter.VideoFragmentPagerAdapter;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class VideoFragment extends Fragment{
    private View view;
    private TabLayout tableLayout_video;
    private ViewPager viewPager_video;
    private String[] title = {"推荐", "音乐", "搞笑",  "社会","推荐", "音乐", "搞笑",  "社会"};
    String[] titles = {"V9LG4B3A0", "V9LG4CHOR", "V9LG4E6VR", "00850FRB","V9LG4B3A0", "V9LG4CHOR", "V9LG4E6VR", "00850FRB"};
    private List<Fragment> list;
    private PullToRefreshListView pullToRefreshListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.fragment_video,null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
        VideoFragmentPagerAdapter pagerAdapter = new VideoFragmentPagerAdapter(getActivity().getSupportFragmentManager(),getActivity(),list,title);

        viewPager_video.setAdapter(pagerAdapter);

        tableLayout_video.setupWithViewPager(viewPager_video);

        tableLayout_video.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    private void initView() {

        tableLayout_video = (TabLayout) view.findViewById(R.id.tabLayout_video);

        viewPager_video = (ViewPager) view.findViewById(R.id.viewPager_video);
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pullListViewVideo);

    }

    private void initData() {

        list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            VideoTitleFragment fragment = new VideoTitleFragment();

            //fragment利用Bundle传值
            Bundle bundle = new Bundle();
            bundle.putString("type",titles[i]);
            fragment.setArguments(bundle);
            list.add(fragment);
        }

    }
}