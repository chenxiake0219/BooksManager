package cn.books.activity.students.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.books.R;
import cn.books.activity.students.adapter.StudenItemAdapter;
import cn.books.base.BaseActivity;
import cn.books.db.Students;

public class StudenMainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    @BindView(R.id.recycler_view)
    RecyclerView mRecycler;
    @BindView(R.id.students_add)
    LinearLayout studentsAdd;

    private StudenItemAdapter adapter;
    private List<Students> mList = new ArrayList<>();

    @Override
    protected void initVariables() {

    }

    @Override
    protected void onResume() {
        super.onResume();

        mList.clear();
        mList.addAll(LitePal.order("id desc").find(Students.class));
        adapter.notifyDataSetChanged();

        if (mList.size() == 0) {
            tvNodata.setVisibility(View.VISIBLE);
        } else {
            tvNodata.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_studen_main);
        ButterKnife.bind(this);
        setTitle("学生信息管理");
        initToolbarNav();
        setEmployeesRecyclerw();
    }

    @Override
    protected void initData() {
        mList.clear();
        mList.addAll(LitePal.order("id desc").find(Students.class));
        adapter.notifyDataSetChanged();
    }

    /**
     * 设置指纹列表
     */
    private void setEmployeesRecyclerw() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudenItemAdapter(this, mList);
        mRecycler.setAdapter(adapter);
        mRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    @OnClick(R.id.students_add)
    public void onViewClicked() {
        startActivity(new Intent(this, StudenADDActivity.class));
    }

}
