package items.bwei.com.newstitlexiadong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import items.bwei.com.newstitlexiadong.R;
import items.bwei.com.newstitlexiadong.adapter.VideoAdapter;
import items.bwei.com.newstitlexiadong.bean.VideoBean;
import items.bwei.com.newstitlexiadong.http.CallbackNewsData;
import items.bwei.com.newstitlexiadong.http.HttpUtils;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class VideoTitleFragment extends Fragment implements PullToRefreshListView.OnRefreshListener2, CallbackNewsData<VideoBean>{

    private View view;
    private String types;
    private String url;
    private int currentPager=10;
    private PullToRefreshListView pullListViewVideo;
    private VideoAdapter videoAdapter;
    private boolean isNeedClear = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_title, null);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


         types=   getArguments().getString("type");


//        http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
        url = "http://c.3g.163.com/nc/video/list/"+types+"/n/"+currentPager+"-10.html";

        //初始化view
        initView();
        //初始化数据
        initData();

    }

    public void initData() {
        videoAdapter = new VideoAdapter(getActivity());
        pullListViewVideo.setAdapter(videoAdapter);
        HttpUtils.loadDataFromServer(url,VideoBean.class, this);

    }

    public void initView() {
        pullListViewVideo = (PullToRefreshListView) view.findViewById(R.id.pullListViewVideo);
        pullListViewVideo.setMode(PullToRefreshBase.Mode.BOTH);
        pullListViewVideo.setOnRefreshListener(this);
    }

    // 下拉刷新的方法
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        currentPager=10;
        isNeedClear=true;
        HttpUtils.loadDataFromServer(url, VideoBean.class, this);
    }
    //上拉加载的方法
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        currentPager+=10;
        isNeedClear=false;
        HttpUtils.loadDataFromServer(url, VideoBean.class, this);
    }



    @Override
    public void success(ArrayList VideoBeans) {


        videoAdapter.addData(VideoBeans, isNeedClear);
        videoAdapter.notifyDataSetChanged();
        pullListViewVideo.onRefreshComplete();

    }
}