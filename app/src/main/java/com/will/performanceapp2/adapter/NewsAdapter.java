package com.will.performanceapp2.adapter;

import android.content.Intent;
import android.net.Uri;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.will.performanceapp2.R;
import com.will.performanceapp2.bean.PhotoItem;
import com.will.performanceapp2.memory.MemoryLeakActivity;
import com.will.performanceapp2.memory.MemoryShakeActivity;
import com.will.performanceapp2.net.NetUtils;
import com.will.performanceapp2.utils.LaunchTimer;
import com.will.performanceapp2.utils.LogUtils;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<PhotoItem> mItems;
    private boolean mHasRecorded;
    private OnFeedShowCallBack mCallBack;

    public NewsAdapter(List<PhotoItem> items) {
        this.mItems = items;
    }

    public void setItems(List<PhotoItem> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public void setOnFeedShowCallBack(OnFeedShowCallBack callBack) {
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_constrainlayout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (position == 0 && !mHasRecorded) {
            mHasRecorded = true;
            holder.layout.getViewTreeObserver()
                    .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            holder.layout.getViewTreeObserver().removeOnPreDrawListener(this);
                            LogUtils.i("FeedShow");
                            LaunchTimer.endRecord("FeedShow");
                            if (mCallBack != null) {
                                mCallBack.onFeedShow();
                            }
                            return true;
                        }
                    });
        }

        PhotoItem newsItem = mItems.get(position);

        // 以下代码是为了演示字符串的拼接
        String msgOld = newsItem.getUser() + newsItem.getId();// 原有方式

        StringBuilder builder = new StringBuilder();
        builder.append(newsItem.getUser())
                .append(newsItem.getId());// 建议使用方式，不要小看这点优化
        String msgNew = builder.toString();

        holder.textView.setText(newsItem.getUser());
        Uri uri = Uri.parse(newsItem.getWebformatURL());
        holder.imageView.setImageURI(uri);
        holder.tvSource.setText(newsItem.getWebformatURL());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentTime = System.currentTimeMillis();

                long netStats = NetUtils.getNetStats(holder.imageView.getContext(),
                        currentTime - DateUtils.DAY_IN_MILLIS, currentTime);
                Log.i("lz", "netStats " + netStats);

                // ConfigManager.sOpenClick模拟的是功能的开关
//                if(ConfigManager.sOpenClick){
//                    // 此处模拟的是WakeLock使用的兜底策略
//                    WakeLockUtils.acquire(holder.imageView.getContext());
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            WakeLockUtils.release();
//                        }
//                    },200);
//                }
                // 以下代码是为了演示Luban这个库对图片压缩对流量方面的影响
//                Luban.with(holder.imageView.getContext())
//                        .load(Environment.getExternalStorageDirectory()+"/Android/1.jpg")
//                        .setTargetDir(Environment.getExternalStorageDirectory()+"/Android")
//                        .launch();

                // 以下代码是为了演示解决过度绘制问题，可以换成解决内存抖动等方面的代码
//                Intent intent = new Intent(holder.imageView.getContext(), SolveOverDrawActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                holder.imageView.getContext().startActivity(intent);

//                Intent intent = new Intent(holder.imageView.getContext(), MemoryShakeActivity.class);
//                holder.imageView.getContext().startActivity(intent);

                Intent intent = new Intent(holder.imageView.getContext(), MemoryLeakActivity.class);
                holder.imageView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        SimpleDraweeView imageView;
        ConstraintLayout layout;
        TextView tvSource;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.tv_title);
            imageView = view.findViewById(R.id.iv_news);
            layout = view.findViewById(R.id.ll_out);
            tvSource = view.findViewById(R.id.tv_source);
        }
    }

} 