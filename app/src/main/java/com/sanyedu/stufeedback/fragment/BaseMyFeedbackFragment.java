package com.sanyedu.stufeedback.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;


import com.sanyedu.sanylib.base.BaseFragment;
import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.model.RecordsBean;
import com.sanyedu.sanylib.share.SpHelper;
import com.sanyedu.sanylib.utils.ConstantUtil;
import com.sanyedu.sanylib.utils.ErrorUtils;
import com.sanyedu.sanylib.utils.StartUtils;
import com.sanyedu.sanylib.utils.ToastUtil;
import com.sanyedu.sanylib.wrapper.LoadMoreWrapper;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.activity.ModifyDetailActivity;
import com.sanyedu.stufeedback.adapter.NeedModifyAdapter;
import com.sanyedu.stufeedback.listener.EndlessRecyclerOnScrollListener;
import com.sanyedu.stufeedback.model.StudentModel;
import com.sanyedu.stufeedback.mvp.MyFeedbackFragment.CommonFeedbackFragmentContacts;
import com.sanyedu.stufeedback.mvp.MyFeedbackFragment.MyFeedbackFragmentPresenter;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  我的反馈
 */
public class BaseMyFeedbackFragment extends BaseFragment<MyFeedbackFragmentPresenter> implements CommonFeedbackFragmentContacts.ICommonFeedbackFragmentUI{

    @BindView(R.id.feedback_rl)
    RecyclerView recyclerView;

    @BindView(R.id.pulldown_srl)
    SwipeRefreshLayout swipeRefreshLayout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private BaseMyFeedbackFragment.OnFragmentInteractionListener mListener;

    //    private RecyclerView listView;
    private NeedModifyAdapter recordAdapter;
    private List<RecordsBean> currList = new ArrayList<>();
    private final int PAGE_COUNT = 4;
    private LoadMoreWrapper loadMoreWrapper;
    private int currentPage = 1;
    //最大页面数,初始化为1
    private int  totalSize = 1;


    public BaseMyFeedbackFragment() {

    }

    public static BaseMyFeedbackFragment newInstance(String param1, String param2) {
        BaseMyFeedbackFragment fragment = new BaseMyFeedbackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static BaseMyFeedbackFragment newInstance(String param1) {
        return newInstance(param1,null);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my_feedback;
    }

    @Override
    protected void init(View view) {
        ButterKnife.bind(this,view);
        initParams();
        initRefreshLayout();
        initRecycleView();
        setListener();
        getFirstPageData();
    }

    private void initParams() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }else{
            mParam1 = ConstantUtil.FK_STATE_SUBMITED;
            mParam2 = null;
        }
    }

    private void setListener() {
        // 设置下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currList.clear();
                currentPage = 1;
                getDataFromServer();

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

        recordAdapter.setItemClickListener(new NeedModifyAdapter.OnItemClickListener(){
            @Override
            public void onClick(View view, int position, String id) {
                SanyLogs.i("noticeclick~~~~position:" +position + ",id:" + id);
                StartUtils.startActivity(getContext(), ModifyDetailActivity.class,id);
            }
        });
    }

    private void addMoreRecords() {
        currentPage ++;
        getDataFromServer();
    }

    private void initRecycleView() {
        recordAdapter = new NeedModifyAdapter(getContext());
        loadMoreWrapper = new LoadMoreWrapper(recordAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(loadMoreWrapper);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layout);
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
    }

    @Override
    public MyFeedbackFragmentPresenter onBindPresenter() {
        return new MyFeedbackFragmentPresenter(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
////        if (mListener != null) {
////            mListener.onFragmentInteraction(uri);
////        }
////    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setFeebacks(List<RecordsBean> recordsList,int maxCount) {

        SanyLogs.i("recordsList:" + recordsList.size());

        //当列表为空时，这时有可能是下拉刷新,因此要设置下拉刷新的标志
        if (currList.size() == 0){
            if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }else{ //当列表不为空时，说明这时只是加载更多的数据，只要把loadMoreWrapper的标志设置一下就可以了。
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        }
        //TODO:最大条数要更
        totalSize = maxCount;
        currList.addAll(recordsList);
        recordAdapter.setRecordsList(currList);
        loadMoreWrapper.notifyDataSetChanged();

        SanyLogs.i("totalSize:" + totalSize + ",maxCount:" + totalSize);

    }

    @Override
    public void showNoMoreList() {
        SanyLogs.i("shoNoMoreList~~~~");
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
    }

    @Override
    public void showError(@NonNull String serverErrorMsg) {
        SanyLogs.e("showError~~~~~");
        if(!TextUtils.isEmpty(serverErrorMsg)) {
            ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
        }
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getDataFromServer() {
        StudentModel bean = SpHelper.getObj(ConstantUtil.USERINFO);
        if(bean != null){
            SanyLogs.i("get id:" + bean.getId() + ",type:" + mParam1);
            getPresenter().getFeedbacks(currentPage + "",PAGE_COUNT + "",bean.getId(),mParam1);
        }
    }

    private void getFirstPageData() {
        currentPage = 1;
        currList.clear();
        getDataFromServer();
    }

}
