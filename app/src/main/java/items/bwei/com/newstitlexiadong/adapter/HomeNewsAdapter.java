package items.bwei.com.newstitlexiadong.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import items.bwei.com.newstitlexiadong.R;
import items.bwei.com.newstitlexiadong.bean.NewsContent;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class HomeNewsAdapter extends BaseAdapter {
    private ArrayList<NewsContent> dataBeens =new ArrayList<>();
    private DisplayImageOptions options;
    private int TYPE_ONE=0;
    private int TYPE_TWO=1;
    Context context;
    public HomeNewsAdapter(Context context) {
        this.context = context;
        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();

    }

    @Override
    public int getCount() {
        return dataBeens.size();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        NewsContent item = getItem(position);
        if(item.getImgextra()!=null&&item.getImgextra().size()>0){
            return TYPE_TWO;
        }else {
            return TYPE_ONE;
        }
    }

    @Override
    public NewsContent getItem(int position) {
        return dataBeens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderOne holderOne = null;
        ViewHolderTwo holderTwo = null;
        if(convertView==null){
            if(getItemViewType(position)==TYPE_ONE){
                holderOne = new ViewHolderOne();
                convertView = LayoutInflater.from(context).inflate(R.layout.listview_item,null);
                holderOne.title = (TextView) convertView.findViewById(R.id.titleName);
                holderOne.replyCount = (TextView) convertView.findViewById(R.id.replyCount);
                holderOne.source = (TextView) convertView.findViewById(R.id.source);
                holderOne.img = (ImageView) convertView.findViewById(R.id.img);
                holderOne.Xx = (ImageView) convertView.findViewById(R.id.X);

                convertView.setTag(holderOne);
            }else if(getItemViewType(position)==TYPE_TWO){
                holderTwo = new ViewHolderTwo();
                convertView = LayoutInflater.from(context).inflate(R.layout.listview_item3,null);
                holderTwo.img = (ImageView) convertView.findViewById(R.id.img);
                holderTwo.img_one = (ImageView) convertView.findViewById(R.id.img_one);
                holderTwo.img_two = (ImageView) convertView.findViewById(R.id.img_two);
                holderTwo.Xx = (ImageView) convertView.findViewById(R.id.X);

                holderTwo.title = (TextView) convertView.findViewById(R.id.titleName);
                holderTwo.ptime = (TextView) convertView.findViewById(R.id.ptime);
                holderTwo.replyCount = (TextView) convertView.findViewById(R.id.source);
                holderTwo.source = (TextView) convertView.findViewById(R.id.source);
                convertView.setTag(holderTwo);
            }
        }else {
            if(getItemViewType(position)==TYPE_ONE){
                holderOne = (ViewHolderOne) convertView.getTag();
            }else if(getItemViewType(position)==TYPE_TWO){
                holderTwo = (ViewHolderTwo) convertView.getTag();
            }

            if(getItemViewType(position)==TYPE_ONE){
                holderOne.title.setText(getItem(position).getTitle());
                holderOne.source.setText(getItem(position).getSource());
                holderOne.replyCount.setText("评论数："+getItem(position).getReplyCount());

                String imgUrl = getItem(position).getImgsrc();

                holderOne.Xx.setImageResource(R.drawable.ugc_icon_not_interested);
                ImageLoader.getInstance().displayImage(imgUrl,holderOne.img,options);

            }else if (getItemViewType(position)==TYPE_TWO){

                holderTwo.title.setText(getItem(position).getTitle());
                holderTwo.source.setText(getItem(position).getSource());
                holderTwo.replyCount.setText("评论数："+getItem(position).getReplyCount());
                holderTwo.ptime.setText(getItem(position).getPtime());

                String imgUrl = getItem(position).getImgsrc();
                String imgUrl1 = getItem(position).getImgextra().get(0).getImgsrc();
                String imgUrl2 = getItem(position).getImgextra().get(1).getImgsrc();

                holderTwo.Xx.setImageResource(R.drawable.ugc_icon_not_interested);
                ImageLoader.getInstance().displayImage(imgUrl,holderTwo.img,options);
                ImageLoader.getInstance().displayImage(imgUrl1,holderTwo.img_one,options);
                ImageLoader.getInstance().displayImage(imgUrl2,holderTwo.img_two,options);

            }

        }

        return convertView;
    }

    public void addData(ArrayList<NewsContent> datas, boolean isNeedClear) {
        if (datas != null) {
            if (isNeedClear) {
                dataBeens.clear();
            }
            dataBeens.addAll(datas);
            Log.e("myMessage", "dataBeens size " + dataBeens.size());
        }
    }

    static class ViewHolderOne{
        TextView title;
        TextView source;
        TextView replyCount;
        ImageView img;
        ImageView Xx;

    }

    static class ViewHolderTwo{
        TextView title;
        TextView source;
        TextView replyCount;
        TextView ptime;
        ImageView img_one;
        ImageView img_two;
        ImageView img;
        ImageView Xx;

    }
}