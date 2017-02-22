package items.bwei.com.newstitlexiadong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import items.bwei.com.newstitlexiadong.R;
import items.bwei.com.newstitlexiadong.activity.WebViewActivity;
import items.bwei.com.newstitlexiadong.adapter.HomeNewsAdapter;
import items.bwei.com.newstitlexiadong.bean.NewsContent;
import items.bwei.com.newstitlexiadong.http.CallbackNewsData;
import items.bwei.com.newstitlexiadong.http.HttpUtils;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class TitleFragment extends Fragment implements PullToRefreshListView.OnRefreshListener2,CallbackNewsData<NewsContent> {

    private View view;
    private String type;
    private int currentPager=0;
    private PullToRefreshListView pullListView;
    private HomeNewsAdapter homeNewsAdapter;
    private boolean isNeedClear = false;
    private String url;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_title,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle arguments = getArguments();
        if(arguments!=null){
            type = arguments.getString("type");
        }

        url = "http://c.m.163.com/nc/article/headline/"+type+"/"+currentPager+"-20.html";
        //初始化view
        initView();
        //初始化数据
        initData();
        HttpUtils.loadDataFromServer(url, NewsContent.class, this);
    }

    private void initView() {

        pullListView = (PullToRefreshListView) view.findViewById(R.id.pullListView);
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.setOnRefreshListener(this);

    }

    private void initData() {
        homeNewsAdapter = new HomeNewsAdapter(getActivity());
        pullListView.setAdapter(homeNewsAdapter);
        HttpUtils.loadDataFromServer(url, NewsContent.class, this);
    }
    // 下拉刷新的方法
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        currentPager=0;
        isNeedClear=true;
        HttpUtils.loadDataFromServer(url, NewsContent.class, this);
    }
    //上拉加载的方法
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        currentPager++;
        isNeedClear=false;
        HttpUtils.loadDataFromServer(url, NewsContent.class, this);;
    }

    @Override
    public void success(final ArrayList<NewsContent> newsContents) {

        homeNewsAdapter.addData(newsContents,isNeedClear);
        homeNewsAdapter.notifyDataSetChanged();
        pullListView.onRefreshComplete();

        pullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra("webViewUrl",newsContents.get(position-1).getUrl());
                startActivity(intent);

            }
        });

    }
}