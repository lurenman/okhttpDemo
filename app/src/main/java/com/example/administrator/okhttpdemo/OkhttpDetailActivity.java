package com.example.administrator.okhttpdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.okhttpdemo.adapter.GridViewDeleteAdapter;
import com.example.administrator.okhttpdemo.javaBeans.ResourceInfoClass;
import com.example.administrator.okhttpdemo.showdialog.SelectPicPopupWindow;
import com.example.administrator.okhttpdemo.views.MyGridView;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class OkhttpDetailActivity extends BaseActivity {
    private static final String TAG = "OkhttpDetailActivity";
    private OkhttpMode okMode = OkhttpMode.UNKNOWN;
    private TextView tv_jsondata;
    private TextView tv_title;
    //和上传图片相关的
    private Gson gson = new Gson();
    private MyGridView mgyImg;
    private SelectPicPopupWindow selectWindow;
    private int maxImgCount = 9;
    public static boolean Istabhost = false;
    public static GridViewDeleteAdapter mgva_img;
    public static ArrayList<HashMap<String, Object>> hashlist = new ArrayList<HashMap<String,
            Object>>();
    public static ArrayList<String> listfile = new ArrayList<String>();
    public static List<ResourceInfoClass> resourceInfoClass = new ArrayList<ResourceInfoClass>();
    private ImageView iv_imageView;

    @Override
    protected void onResume() {
        super.onResume();
        if (Istabhost) {
            if (listfile != null && !listfile.isEmpty()) {
                if (listfile != null && !listfile.isEmpty()) {
                 //   UploadImage();
                }
            }
            Istabhost = false;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mgva_img = null;
        hashlist.clear();
        listfile.clear();
        resourceInfoClass.clear();
        Istabhost = false;
    }

    public static enum OkhttpMode {
        UNKNOWN(100),
        GETSYN(0),//同步的Get请求

        GETASY(1),//异步的get请求

        POSTSYN(2),//同步的Post请求
        POSTASY(3),//异步的post请求
        POSTSYNUPLOAD(4),//同步基于post的文件上传
        POSTASYUPLOAD(5),//异步基于post的文件上传
        DOWNLOADASYN(8);//异步下载文件downloadAsyn
        private int value = 0;

        private OkhttpMode(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public static OkhttpMode valueOf(int value) {
            switch (value) {
                case 0:
                    return GETSYN;
                case 1:
                    return GETASY;
                case 2:
                    return POSTSYN;
                case 3:
                    return POSTASY;
                case 4:
                    return POSTSYNUPLOAD;
                case 5:
                    return POSTASYUPLOAD;
                case 8:
                    return DOWNLOADASYN;
                default:
                    return UNKNOWN;
            }
        }

    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_okhttpdetail);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_jsondata = (TextView) findViewById(R.id.tv_jsondata);
        iv_imageView = (ImageView) findViewById(R.id.iv_imageView);
        mgyImg=(MyGridView)findViewById(R.id.mgy_img);
        Intent intent = getIntent();
        if (intent != null) {
            OkhttpMode okMode = OkhttpMode.valueOf(intent.getIntExtra("okMode", 0));
            if (okMode==OkhttpMode.POSTASYUPLOAD)
            {
                HashMap<String, Object> hashMap = new HashMap<String, Object>();
                hashMap.put("add", R.mipmap.new_icon_addimg);
                hashlist.add(hashMap);
                mgva_img = new GridViewDeleteAdapter(OkhttpDetailActivity.this, hashlist);
                mgyImg.setAdapter(mgva_img);
            }else if (okMode==OkhttpMode.DOWNLOADASYN)
            {
                iv_imageView.setVisibility(View.VISIBLE);
            }
            //设置模式
            setOkMode(okMode);
        }


    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        switch (okMode) {
            case UNKNOWN:
                tv_title.setText("不知道的模式");
                break;
            case GETSYN:
                tv_title.setText("同步的Get请求");
                getsyn();
                break;
            case GETASY:
                tv_title.setText("异步的get请求");
                getasy();
                break;
            case POSTSYN:
                tv_title.setText("同步的Post请求");
                postsyn();
                break;
            case POSTASY:
                tv_title.setText("异步的post请求");
                postasy();
            case POSTSYNUPLOAD:
                tv_title.setText("同步基于post的文件上传");
                postsynUpload();
                break;
            case POSTASYUPLOAD:
                tv_title.setText("异步基于post的文件上传");
               // postasyUpload();
                break;
            case DOWNLOADASYN:
                tv_title.setText("异步下载文件");
                downloadAsyn();
                break;
        }
        mgyImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_imgurl = (TextView) view.findViewById(R.id.tv_imgurl);
                boolean clickResult = false;

                if (TextUtils.equals(tv_imgurl.getText(), "add")) {
                    clickResult = true;
                }
                if (clickResult)
                {
                    selectWindow = new SelectPicPopupWindow(OkhttpDetailActivity.this, new View
                            .OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            switch (v.getId()) {
                                case R.id.btn_take_photo:
                                    //上传
                                    selectWindow.dismiss();
                                    break;

                                case R.id.btn_pick_photo:
//                                    Intent intent = new Intent(UpLoadImageActivity.this,
//                                            ImgFileListActivity.class);
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString("pageactivity", "UpLoadImageActivity");
//                                    bundle.putInt("imgcount", maxImgCount - (mgva_img.getCount()
//                                            - 1));
//                                    intent.putExtras(bundle);
//                                    CommonClass.ASMstartActivity(intent, UpLoadImageActivity
//                                            .this, false);
                                    postasyUpload();
                                    selectWindow.dismiss();
                                    break;
                            }
                        }
                    });

                    selectWindow.showAtLocation(OkhttpDetailActivity.this.findViewById(R.id.main),
                            Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                }
            }
        });

    }
    @Override
    protected void loadData() {

    }

    //同步的Get请求
    private void getsyn() {
        //这个还必须里开线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建okHttpClient对象
                OkHttpClient mOkHttpClient = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url("https://free-api.heweather.com/v5/now?city=" + "杭州"
                                + "&key=373451a070ca488fa255e9fe7b0ec2e1")
                        .build();
                Call call = mOkHttpClient.newCall(request);
                //Response execute = call.execute();
                try {
                    Response execute = call.execute();
                    final String jsonString = execute.body().string();
                    if (!TextUtils.isEmpty(jsonString)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_jsondata.setText(jsonString);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //异步的Get请求
    private void getasy() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://free-api.heweather.com/v5/now?city=" + "杭州"
                        + "&key=373451a070ca488fa255e9fe7b0ec2e1")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Response response) throws IOException {

                final String jsonString = response.body().string();
                if (!TextUtils.isEmpty(jsonString)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_jsondata.setText(jsonString);
                        }
                    });
                }

            }
        });
    }

    //同步的Post请求
    private void postsyn() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://admin.hkshijue.com/ModelWeb/AppModel/GetCooperateBusiness";
                OkHttpClient mOkHttpClient = new OkHttpClient();
                FormEncodingBuilder builder = new FormEncodingBuilder();
                builder.add("pageIndex", "0");
                builder.add("PageSize", "1");
                RequestBody requestBody = builder.build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                Response response = null;
                try {
                    response = mOkHttpClient.newCall(request).execute();
                    final String jsonString = response.body().string();
                    if (!TextUtils.isEmpty(jsonString)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_jsondata.setText(jsonString);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //异步的post请求
    private void postasy() {

        String url = "http://admin.hkshijue.com/ModelWeb/AppModel/GetCooperateBusiness";
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("pageIndex", "0");
        builder.add("PageSize", "1");

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
               // e.printStackTrace();
                Log.e(TAG,"------------------------------------aaaaaaaaaaaaaaaaaaaaaaaaaaa");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String jsonString = response.body().string();
                Log.e(TAG,"------------------------------------"+jsonString);
                if (!TextUtils.isEmpty(jsonString)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_jsondata.setText(jsonString);
                        }
                    });
                }
            }
        });

    }
    //同步基于post的文件上传
    private void postsynUpload(){


    }
    //异步基于post的文件上传
    private void postasyUpload() {
        //这快申请权限，文件路径自己配置不展示
        File file=new File("/storage/emulated/0/1508311037848.png");
        //上传一张图片测试
        String url = "http://admin.hkshijue.com/ModelWeb/AppModel/UploadImage";//上传图片的地址
        OkHttpClient mOkHttpClient = new OkHttpClient();
        MultipartBuilder builder = new MultipartBuilder()
                .type(MultipartBuilder.FORM);
        //添加参数
        builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + "fileStorageName"+ "\""),
                RequestBody.create(null, "ModelCompanyStorage"));
        builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" +"uploadName" + "\""),
                RequestBody.create(null, "ModelCompanyUpload"));
        builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + "isCut" + "\""),
                RequestBody.create(null,"0"));

        RequestBody fileBody = null;
        String fileName = file.getName();
        fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
        //TODO 根据文件名设置contentType
        builder.addPart(Headers.of("Content-Disposition",
                "form-data; name=\"" + "文件名描述随便起" + "\"; filename=\"" + fileName + "\""),
                fileBody);
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String jsonString = response.body().string();
                Log.e(TAG,"------------------------------------"+jsonString);
                if (!TextUtils.isEmpty(jsonString))
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_jsondata.setText(jsonString);
                        }
                    });
                }
            }
        });


    }
    //异步下载文件
    private void downloadAsyn() {
        String url = "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg";
        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Response response) throws IOException {
                //下载一个图片测试
                InputStream inputStream = response.body().byteStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv_imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }
    //设置方法
    public void setOkMode(OkhttpMode mode) {
        this.okMode = mode;
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}
