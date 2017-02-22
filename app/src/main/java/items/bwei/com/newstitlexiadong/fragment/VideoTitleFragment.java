package items.bwei.com.newstitlexiadong.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import items.bwei.com.newstitlexiadong.R;
import items.bwei.com.newstitlexiadong.adapter.HomeNewsAdapter;
import items.bwei.com.newstitlexiadong.bean.VideoBean;
import items.bwei.com.newstitlexiadong.http.CallbackNewsData;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class VideoTitleFragment extends Fragment implements PullToRefreshListView.OnRefreshListener2, CallbackNewsData<VideoBean>{

    private View view;
    private String type;
    private String url;
    private int currentPager=0;
    private PullToRefreshListView pullListView;
    private HomeNewsAdapter homeNewsAdapter;
    private boolean isNeedClear = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_video_title, null);
        return view;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void success(ArrayList<VideoBean> newsContents) {

    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        Bundle arguments = getArguments();
//        if(arguments!=null){
//            type = arguments.getString("type");
//        }
//        url = "http://c.3g.163.com/nc/video/list/"+type+"/"+currentPager+"-10.html";
//        //初始化view
//        initView();
//        //初始化数据
//        initData();
//
//    }
//
//    private void initData() {
//        homeNewsAdapter = new HomeNewsAdapter(getActivity());
//        pullListView.setAdapter(homeNewsAdapter);
//        HttpUtils.loadDataFromServer(url, NewsContent.class, this);
//
//    }
//
//    private void initView() {
//        pullListView = (PullToRefreshListView) view.findViewById(R.id.pullListView);
//        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
//        pullListView.setOnRefreshListener(this);
//    }
//
//    // 下拉刷新的方法
//    @Override
//    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
//        currentPager=0;
//        isNeedClear=true;
//        HttpUtils.loadDataFromServer(url, NewsContent.class, this);
//    }
//    //上拉加载的方法
//    @Override
//    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
//        currentPager++;
//        isNeedClear=false;
//        HttpUtils.loadDataFromServer(url, NewsContent.class, this);;
//    }
//
//
//
//
//
//
//
//
//    @Override
//    public void success(ArrayList<VideoBean> newsContents) {
////        homeNewsAdapter.addData(newsContents,isNeedClear);
//        homeNewsAdapter.notifyDataSetChanged();
//        pullListView.onRefreshComplete();
//
//        pullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent intent = new Intent(getActivity(),WebViewActivity.class);
////                intent.putExtra("webViewUrl",newsContents.get(position-1).getUrl());
//                startActivity(intent);
//
//            }
//        });
//    }
}