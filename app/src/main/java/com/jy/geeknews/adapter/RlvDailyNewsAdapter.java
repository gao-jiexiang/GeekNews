package com.jy.geeknews.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jy.geeknews.R;
import com.jy.geeknews.bean.DailyNews;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class RlvDailyNewsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<DailyNews.TopStoriesBean> mTopStories;
    private String date = "";//日期
    private List<DailyNews.StoriesBean> mStories;
    public static final int TYPE_BANNER = 0;
    public static final int TYPE_DATE = 1;
    public static final int TYPE_NEWS = 2;

    public void setDate(String date) {
        this.date = date;
    }

    public RlvDailyNewsAdapter(Context context, List<DailyNews.TopStoriesBean> topStories, List<DailyNews.StoriesBean> stories) {
        mContext = context;
        mTopStories = topStories;
        mStories = stories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_BANNER){
            View view0 = View.inflate(mContext, R.layout.rlv_dailay_news_item0, null);
            return new BannerVH(view0);
        }else if(viewType == TYPE_DATE){
            View view1 = View.inflate(mContext, R.layout.rlv_dailay_news_item1, null);
            return new DateVH(view1);
        }else {
            View view2 = View.inflate(mContext, R.layout.rlv_dailay_news_item2, null);
            return new NewsVH(view2);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if(type == TYPE_BANNER){//第一行banner
            BannerVH vh = (BannerVH) holder;
            vh.mBanner.setImages(mTopStories).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    DailyNews.TopStoriesBean bean = (DailyNews.TopStoriesBean) path;
                    String imgUrl = bean.getImage();
                    Glide.with(mContext).load(imgUrl).into(imageView);
                }
            }).start();
        }else if(type == TYPE_DATE){//第二行日期
            DateVH vh = (DateVH) holder;
            vh.mDate.setText(date);
        }else {
            int newPosition = position-1;//先把日期的1减掉
            if(mTopStories.size()>0){//如果有banner。把banner的1减掉
                newPosition -=1;
            }
            DailyNews.StoriesBean storiesBean = mStories.get(newPosition);//得到新闻列表的正确索引，以免越界
            NewsVH vh = (NewsVH) holder;
            //图片集合不为空，长度不为0，说明有图片
            if(storiesBean.getImages() != null && storiesBean.getImages().size()>0){
                Glide.with(mContext).load(storiesBean.getImages().get(0)).into(vh.img);
            }
            vh.title.setText(storiesBean.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if(mTopStories.size()>0){//有banner
            return 1+1+mStories.size();//第1个 1 是banner的行，第2个1是 日期的行  加上 新闻的数量
        }else {//没有banner
            return 1+mStories.size();//第1个1是 日期的行  加上 新闻的数量
        }
    }

    //控制每行的类别
    @Override
    public int getItemViewType(int position) {
        if(mTopStories.size()>0){//有banner
            if(position == 0){
                return TYPE_BANNER;
            }else if(position == 1){
                return TYPE_DATE;
            }else {
                return TYPE_NEWS;
            }
        }else {//没有banner
            if(position == 0){
                return TYPE_DATE;
            }else {
                return TYPE_NEWS;
            }
        }
    }

    //定义三个viewholder
    private class BannerVH extends RecyclerView.ViewHolder{
        Banner mBanner;
        public BannerVH(@NonNull View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.banner_dailynews);
        }
    }
    private class DateVH extends RecyclerView.ViewHolder{
        TextView mDate;
        public DateVH(@NonNull View itemView) {
            super(itemView);
            mDate = itemView.findViewById(R.id.txt_date);
        }
    }
    private class NewsVH extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title;
        public NewsVH(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.txt_title);
        }
    }
}
