package com.me.dingxiangyuan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.acitvity.BanKuaiActivity;
import com.me.dingxiangyuan.acitvity.DeatisBankuaiActivity;
import com.me.dingxiangyuan.bean.CommunityBean;
import com.me.dingxiangyuan.forumFragment.PhotoFragment;
import com.me.dingxiangyuan.holder.BaseHolder;
import com.me.dingxiangyuan.holder.BoardContentHolder;
import com.me.dingxiangyuan.holder.BoardTopHolder;
import com.me.dingxiangyuan.holder.LoadMore2Holder;
import com.me.dingxiangyuan.interfaces.OnItemClickListenerItem;
import com.me.dingxiangyuan.loadanim.LoadAnim;

import java.text.SimpleDateFormat;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by Administrator on 2017/1/9.
 */

public class BoardRecyclerAdapter extends RecyclerView.Adapter<BaseHolder> {
    private List<CommunityBean.DataBean> topList;
    private List<CommunityBean.DataBean> contentList;
    private Context context;
    private static final int Top = 0;
    private static final int NOMAL = 1;
    private static final int LAST_POSITION = 2;
    private static int lastPosition = -1;
    private OnItemClickListenerItem listenerItem;
    private Intent intent;

    private SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日 HH:mm:ss");
    private RecyclerAdapter<CommunityBean.DataBean> recyclerAdapter;

    public BoardRecyclerAdapter(List<CommunityBean.DataBean> topList, List<CommunityBean.DataBean> contentList, Context context) {
        this.topList = topList;
        this.contentList = contentList;
        this.context = context;
    }

    /**
     * 判断布局文件
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseHolder baseHolder = null;
        View view = null;
        switch (viewType) {
            case Top:
                view = LayoutInflater.from(context).inflate(R.layout.boardtop_recycle, parent, false);
                baseHolder = new BoardTopHolder(view);
                break;
            case NOMAL:
                view = LayoutInflater.from(context).inflate(R.layout.community, parent, false);
                baseHolder = new BoardContentHolder(view);
                break;
            case LAST_POSITION:
                view = LayoutInflater.from(context).inflate(R.layout.layout_loadmore, null);
                baseHolder = new LoadMore2Holder(view);
                break;
        }

        return baseHolder;

    }

    /**
     * 绑定数据类型
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case Top:
                BoardTopHolder holder1 = (BoardTopHolder) holder;
                holder1.top_recycleView.setLayoutManager(new LinearLayoutManager(context));
                /**
                 * 嵌套recyclerView
                 */
                recyclerAdapter = new RecyclerAdapter<CommunityBean.DataBean>(context, topList, R.layout.layout_top_firstitem) {
                    @Override
                    public void convert(RecyclerHolder holder, CommunityBean.DataBean data, int position) {
                        holder.setText(R.id.tv_topTitle, data.getTitle());
                    }
                };
                holder1.top_recycleView.setAdapter(recyclerAdapter);
                holder1.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listenerItem != null) {
                            listenerItem.onItemClick(view, position);
                        }
                    }
                });
                recyclerAdapter.setOnItemClickListener(new OnItemClickListenerItem() {
                    @Override
                    public void onItemClick(View view, int position) {
                        CommunityBean.DataBean dataBean = topList.get(position);
                        intentActivity(dataBean);
                    }
                });
                break;
            case NOMAL:
                BoardContentHolder contentHolder = (BoardContentHolder) holder;
                contentHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CommunityBean.DataBean dataBean = contentList.get(position);
                        intentActivity(dataBean);
                    }
                });
                View itemView = contentHolder.itemView;
                if (lastPosition < position) {
                    new LoadAnim().loadingAnim(itemView);
                    lastPosition = position;
                }
                String content = contentList.get(position-1).getContent();
                contentHolder.title_tv.setText(contentList.get(position-1).getTitle());
                if (content.length() > 60) {
                    contentHolder.content_tv.setText(content.substring(0, 60) + "....");
                } else {
                    contentHolder.content_tv.setText(content);
                }
                //图片
                final List<CommunityBean.DataBean.ImgsBean> imgs = (List<CommunityBean.DataBean.ImgsBean>) contentList.get(position-1 ).getImgs();
                if (imgs == null || imgs.size() == 0) {
                    contentHolder.view_group.setVisibility(View.GONE);
                } else {
                    contentHolder.view_group.setVisibility(View.VISIBLE);
                    for (int i = 0; i < 3; i++) {
                        ImageView childAt = (ImageView) contentHolder.view_group.getChildAt(i);
                        if (imgs.size() > i && !TextUtils.isEmpty(imgs.get(i).getOriginalImg())) {
                            childAt.setVisibility(View.VISIBLE);
                            final int finalI = i;
                            childAt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DialogFragment photoViewInstance = PhotoFragment.getPhotoViewInstance(imgs.get(finalI).getOriginalImg());
                                    BanKuaiActivity boardActivity = (BanKuaiActivity) BoardRecyclerAdapter.this.context;
                                    photoViewInstance.show(boardActivity.getSupportFragmentManager(), "dialogFragment");
                                }
                            });
                            Glide.with(context).load(imgs.get(i).getOriginalImg()).into(childAt);
                        } else {
                            childAt.setVisibility(View.GONE);
                        }
                    }
                }
                contentHolder.broadcasting_tv.setText(contentList.get(position-1 ).getUserName());
                //时间
                String str = formatter.format(contentList.get(position-1 ).getCreateTime());
                contentHolder.timer_tv.setText(str);
                //点击评论

                contentHolder.go_to_comment_iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "data:" + data, Toast.LENGTH_SHORT).show();
                    }
                });
                //评论数
                contentHolder.comment_number_tv.setText(contentList.get(position-1).getReplyTimes() + "");
                break;


        }
    }

    /**
     * 数据传值
     * @param dataBean
     */
    private void intentActivity(CommunityBean.DataBean dataBean) {
        if (intent == null) {
            intent = new Intent(context, DeatisBankuaiActivity.class);
        }
        intent.putExtra("communityBean", dataBean);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {

        return contentList.size() + 2;
    }

    /**
     * 多条目扎展示
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == Top) {
            return Top;
        }
        if (position == contentList.size() + 1) {
            return LAST_POSITION;
        }
        return NOMAL;
    }
}
