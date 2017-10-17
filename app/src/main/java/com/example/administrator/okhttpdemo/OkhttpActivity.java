package com.example.administrator.okhttpdemo;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.okhttpdemo.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class OkhttpActivity extends BaseActivity {
    private ListView lv_listView;
    private ListViewAdapter mListViewAdapter;
    private List<String> mDatas = new ArrayList<String>();

    @Override
    protected void initVariables() {
        String[] data = {"同步的Get请求","异步的get请求","同步的Post请求",
                "异步的post请求","同步基于post的文件上传","异步基于post的文件上传","异步基于post的文件上传，单文件不带参数上传"
        ,"异步基于post的文件上传，单文件且携带其他form参数上传","异步下载文件"};
        List<String> arrays = Arrays.asList(data);
        mDatas.addAll(arrays);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_okhttp);
        lv_listView = (ListView) findViewById(R.id.lv_listView);
        if (null != mDatas && !mDatas.isEmpty())
            mListViewAdapter = new ListViewAdapter(getApplicationContext(), mDatas);

        lv_listView.setAdapter(mListViewAdapter);
    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        lv_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            OkhttpDetailActivity.OkhttpMode okMode= null;
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        okMode=OkhttpDetailActivity.OkhttpMode.GETSYN;
                        break;
                    case 1:
                        okMode=OkhttpDetailActivity.OkhttpMode.GETASY;
                        break;
                    case 2:
                        okMode=OkhttpDetailActivity.OkhttpMode.POSTSYN;
                        break;
                    case 3:
                        okMode=OkhttpDetailActivity.OkhttpMode.POSTASY;
                        break;
                    case 4:
                        okMode=OkhttpDetailActivity.OkhttpMode.POSTSYNUPLOAD;
                        break;
                    case 5:
                        okMode=OkhttpDetailActivity.OkhttpMode.POSTASYUPLOAD;
                        break;
                    case 8:
                        okMode=OkhttpDetailActivity.OkhttpMode.DOWNLOADASYN;
                        break;
                    default:
                        okMode=OkhttpDetailActivity.OkhttpMode.UNKNOWN;
                        break;
                }
                Intent intent = new Intent(OkhttpActivity.this,OkhttpDetailActivity.class);
                intent.putExtra("okMode", okMode.value());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
