package com.jiangjh.tripapp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.bean.NewsListBean;
import com.jiangjh.tripapp.database.FavoriteDBOpenHelper;
import com.jiangjh.tripapp.util.ShareDialog;
import com.jiangjh.tripapp.widget.TitleBar;

/**
 * @author Jinghao.Jiang
 * @date 2018/2/24
 */

public class WebActivity extends AppCompatActivity {
    private String url;
    private ShareDialog mShareDialog;
    private WebView mWebView;
    private TitleBar mTitleBar;
    private ProgressBar mProgressBar;
    private boolean isFavorite = false;
    private FavoriteDBOpenHelper mFavoriteDBOpenHelper;
    private NewsListBean newsBean;
    private LinearLayout mLlComment;
    private EditText mEditText;
    private Button mButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("webUrl");
            initView();
            initListener();
            Log.i("WebActivity", "onCreate: ----》"+url);
            if (intent.hasExtra("news")){
                newsBean = (NewsListBean) intent.getSerializableExtra("news");
                mLlComment.setVisibility(View.VISIBLE);
                initFavorite();
            }else {
                mLlComment.setVisibility(View.GONE);
            }
        }else {
            finish();
        }

    }
    private void initView() {
        mShareDialog = new ShareDialog(WebActivity.this);
        mWebView = findViewById(R.id.webView);
        mTitleBar = findViewById(R.id.title_bar);
        mProgressBar = findViewById(R.id.web_progress);
        mLlComment = findViewById(R.id.ll_comment);
        mEditText = findViewById(R.id.et_comment);
        mButton = findViewById(R.id.comment_btn);
        mTitleBar.setRightDrawable(getResources().getDrawable(R.mipmap.icon_share));
        mWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(true);
        }

        settings.setGeolocationEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheMaxSize(1024 * 1024 * 8);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (!TextUtils.isEmpty(title)) {
                    mTitleBar.setTitle(title);
                }
            }

            @Override
            public void onGeolocationPermissionsHidePrompt() {
                super.onGeolocationPermissionsHidePrompt();
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }

            }
        });
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        if (!TextUtils.isEmpty(url)) {
            mWebView.loadUrl(url);
        }


    }

    private void initListener() {
        mTitleBar.setRightImageClickListener(new TitleBar.TitleBarRightImageClickListener() {
            @Override
            public void onClickListener() {
                if (mShareDialog != null && !mShareDialog.isShowing()) {
                    mShareDialog.show();
                }
            }
        });
        mTitleBar.setLiftImageClickListener(new TitleBar.TitleBarLeftImageClickListener() {
            @Override
            public void onClickListener() {
                finish();
            }
        });

        mTitleBar.setRightSecondButtonClickListener(new TitleBar.TitleBarRightSecondListener() {
            @Override
            public void onClickListener() {
                if (isFavorite){
                    deleteFavorite();
                    mTitleBar.setRightSecondButtonDrabable(getResources().getDrawable(R.mipmap.icon_favorite_unclick));
                    isFavorite = false;
                }else {
                    addFavorite();
                    mTitleBar.setRightSecondButtonDrabable(getResources().getDrawable(R.mipmap.icon_favorite_click));
                    isFavorite = true;
                }
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText.getText().toString().trim())){
                    Toast.makeText(WebActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(WebActivity.this,"发表评论成功",Toast.LENGTH_SHORT).show();
                    mEditText.setText(null);
                }
            }
        });
    }

    private void initFavorite(){
        mTitleBar.showRightSecondButton();
        mFavoriteDBOpenHelper = new FavoriteDBOpenHelper(this, "jjh.db", null, 1);
        if (searchFavorite()){
            mTitleBar.setRightSecondButtonDrabable(getResources().getDrawable(R.mipmap.icon_favorite_click));
            isFavorite = true;
        }else {
            mTitleBar.setRightSecondButtonDrabable(getResources().getDrawable(R.mipmap.icon_favorite_unclick));
            isFavorite = false;
        }

    }

    private boolean searchFavorite(){
        SQLiteDatabase database = mFavoriteDBOpenHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM favorite WHERE url = ?",
                new String[]{url});
        if (cursor.moveToFirst()) {
            String dbUrl = cursor.getString(cursor.getColumnIndex("url"));
            if (url.equals(dbUrl)) {
                return true;
            }
        }
        cursor.close();
        return false;
    }

    private void deleteFavorite(){
        SQLiteDatabase database = mFavoriteDBOpenHelper.getWritableDatabase();
        database.execSQL("DELETE FROM favorite WHERE url = ?",new String[]{url});
    }

    private void addFavorite(){
        SQLiteDatabase database = mFavoriteDBOpenHelper.getWritableDatabase();
        database.execSQL("INSERT INTO favorite(time,title,description,picurl,url) values(?,?,?,?,?)",
                new String[]{newsBean.getCtime(), newsBean.getTitle(),newsBean.getDescription(),newsBean.getPicUrl(),newsBean.getUrl()});

    }
}
