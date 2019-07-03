package com.sanyedu.stufeedback.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.utils.StartUtils;
import com.sanyedu.sanylib.utils.ToastUtil;
import com.sanyedu.sanylib.widget.EmptyRecyclerView;
import com.sanyedu.sanylib.wrapper.LoadMoreWrapper;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.adapter.NoticeAdapter;
import com.sanyedu.stufeedback.listener.EndlessRecyclerOnScrollListener;
import com.sanyedu.stufeedback.model.NoticeModel;
import com.sanyedu.stufeedback.mvp.notice.NoticeContacts;
import com.sanyedu.stufeedback.mvp.notice.NoticePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeActivity extends SanyBaseActivity<NoticePresenter> implements NoticeContacts.INoticeUI {


    @BindView(R.id.main_msg_rv)
    EmptyRecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    NoticeAdapter noticeAdapter;

    @BindView(R.id.layout_empty_view)
    View emptyView;

    @OnClick(R.id.goback_ib)
    public void goback(){
        finish();
    }
    private List<NoticeModel> currList = new ArrayList<>();

    private final int PAGE_COUNT = 4;

    private LoadMoreWrapper loadMoreWrapper;
    private int currentPage = 1;

    //最大页面数,初始化为1
    private int  totalSize = 1;
    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initRefreshLayout();
        initRecyclerView();
        setListener();
        getFirstPageData();
    }

    private void getFirstPageData() {
        currentPage = 1;
        currList.clear();
        showLoading();
        getPresenter().getNotices(currentPage+"",PAGE_COUNT + "");
    }

    private void setListener() {
        // 设置下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currList.clear();
                currentPage = 1;
                getPresenter().getNotices(currentPage+"",PAGE_COUNT + "");
            }
        });

        // 设置加载更多监听
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                SanyLogs.i("onLoadMore~~~~");
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                if ( currentPage < totalSize) {
                    addMoreRecords();
                } else {
                    // 显示加载到底的提示
                    loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
                }
            }
        });

        noticeAdapter.setOnItemClickListener(new NoticeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String id) {
                SanyLogs.i("noticeclick~~~~position:" +position + ",id:" + id);
                StartUtils.startActivity(NoticeActivity.this, NoticeDetailActivity.class,id);
            }
        });
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        noticeAdapter = new NoticeAdapter(this);
        loadMoreWrapper = new LoadMoreWrapper(noticeAdapter);
        recyclerView.setAdapter(loadMoreWrapper);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        recyclerView.setEmptyView(emptyView);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_notice;
    }

    @Override
    public NoticePresenter onBindPresenter() {
        return new NoticePresenter(this);
    }

    @Override
    public void setNotices(ArrayList<NoticeModel> notices, int maxPageCount) {
        SanyLogs.i("recordsList:" + notices.size());
        hideLoading();
        //当列表为空时，这时有可能是下拉刷新,因此要设置下拉刷新的标志
        if (currList.size() == 0){
            if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }else{ //当列表不为空时，说明这时只是加载更多的数据，只要把loadMoreWrapper的标志设置一下就可以了。
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        }
        //TODO:最大条数要更
        totalSize = maxPageCount;
        currList.addAll(notices);
        noticeAdapter.setNoticeList(currList);
        loadMoreWrapper.notifyDataSetChanged();

        SanyLogs.i("totalSize:" + totalSize + ",maxCount:" + maxPageCount);
    }

    @Override
    public void setNoNotices() {
        SanyLogs.i("shoNoMoreList~~~~");
        hideLoading();
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);

        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        ToastUtil.showLongToast("没有更多记录啦~");
    }

    @Override
    public void showError(String serverError) {
        SanyLogs.e("showNoNotice~~~~~");
        hideLoading();
        if(!TextUtils.isEmpty(serverError)) {
            ToastUtil.showLongToast(serverError);
        }
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
    }

    private void addMoreRecords() {
        currentPage ++;
        getPresenter().getNotices(currentPage+"",PAGE_COUNT + "");
    }
}
