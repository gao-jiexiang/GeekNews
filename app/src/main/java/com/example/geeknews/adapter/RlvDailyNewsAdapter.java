package com.example.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.base.BaseApp;
import com.example.geeknews.base.BaseRlvAdapter;
import com.example.geeknews.bean.DailyNewsBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvDailyNewsAdapter extends BaseRlvAdapter {
    private final Context mContext;
    public final ArrayList<DailyNewsBean.StoriesBean> mNewsList;
    private final ArrayList<DailyNewsBean.TopStoriesBean> mBannerList;
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_DATE = 1;
    private static final int TYPE_NEWS = 2;
    private String mDate = BaseApp.getRes().getString(R.string.today_news);
    public RlvDailyNewsAdapter(Context context, ArrayList<DailyNewsBean.StoriesBean> newsList, ArrayList<DailyNewsBean.TopStoriesBean> bannerList) {
        mContext=context;
        mNewsList=newsList;
        mBannerList=bannerList;
    }

    @Override
    public int getItemViewType(int position) {
        //因为旧新闻没有banner，所以第一个条目有可能是banner，也有可能是日期
        //根据bannerlist的长度确定有没有banner
        if (mBannerList.size() > 0 ){
            //有banner
            if (position == 0){
                return TYPE_BANNER;
            }else if (position == 1){
                return TYPE_DATE;
            }else {
                return TYPE_NEWS;
            }
        }else {
            if (position == 0){
                return TYPE_DATE;
            }else {
                return TYPE_NEWS;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == TYPE_BANNER){
            View inflate = inflater.inflate(R.layout.item_banner, parent,false);
            return new BannerVH(inflate);
        }else if (viewType == TYPE_DATE){
            View inflate = inflater.inflate(R.layout.item_date, parent,false);
            return new DateVH(inflate);
        }else {
            View inflate = inflater.inflate(R.layout.item_card, parent,false);
            return new NewsVH(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType==TYPE_BANNER){
            BannerVH bannerVH= (BannerVH) holder;
            bannerVH.mBanner.setImages(mBannerList)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            DailyNewsBean.TopStoriesBean bean= (DailyNewsBean.TopStoriesBean) path;
                            Glide.with(mContext).load(bean.getImage()).into(imageView);
                        }
                    })
                    .start();
        }else if (viewType == TYPE_DATE){
            DateVH dateVH= (DateVH) holder;
            dateVH.mTv.setText(mDate);
        }else {
            //因为上面有banner和日期条目所以position并不是从0 开始的，所以需要处理
            int newPosition = position-1;//减的日期
            if (mBannerList.size()>0){
                newPosition -= 1;
            }
            DailyNewsBean.StoriesBean bean = mNewsList.get(newPosition);
            NewsVH newsVH = (NewsVH) holder;
            newsVH.mTv.setText(bean.getTitle());
            List<String> images = bean.getImages();
            if (images!=null && images.size()>0){
                Glide.with(mContext).load(images.get(0)).into(newsVH.mImg);
            }


            int finalNewPosition=newPosition;
            newsVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOmItemClickListener!=null){
                        mOmItemClickListener.onItemClick(view,finalNewPosition);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (mBannerList.size()>0){
            return 1+1+mNewsList.size();
        }else {
            return 1+mNewsList.size();
        }
    }

    public void setData(DailyNewsBean bean) {
        List<DailyNewsBean.StoriesBean> stories = bean.getStories();
        List<DailyNewsBean.TopStoriesBean> top_stories = bean.getTop_stories();

        mNewsList.clear();
        mBannerList.clear();
        if (stories != null && stories.size()>0){
            mNewsList.addAll(stories);
        }

        if (top_stories != null && top_stories.size()>0){
            mBannerList.addAll(top_stories);
        }

        mDate = bean.getDate();

        notifyDataSetChanged();
    }

    class BannerVH extends RecyclerView.ViewHolder{
        @BindView(R.id.banner)
        Banner mBanner;
        public BannerVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class DateVH extends RecyclerView.ViewHolder{

        @BindView(R.id.tv)
        TextView mTv;
        public DateVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class NewsVH extends RecyclerView.ViewHolder{

        @BindView(R.id.tv)
        TextView mTv;
        @BindView(R.id.img)
        ImageView mImg;
        public NewsVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
