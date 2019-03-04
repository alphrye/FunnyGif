package com.nexuslink.alphrye.funnygif;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_CHOOOSE_GIF = 0;

    private LinearLayout mLlAdd;

    private LinearLayout mLlTools;

    private FunnyGifView mFunnyGifView;

    private Button mBtnCreateNew;

    private Button mBtnSave;

    private Button mBtnShare;

    private TextView mTvTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLlAdd = findViewById(R.id.ll_add);
        mFunnyGifView = findViewById(R.id.v_funny_gif);
        mBtnCreateNew = findViewById(R.id.btn_create_new);
        mBtnSave = findViewById(R.id.btn_save);
        mBtnShare = findViewById(R.id.btn_share);
        mLlTools = findViewById(R.id.ll_tools);
        mTvTips = findViewById(R.id.tv_tips);
        mLlAdd.setOnClickListener(this);
        mBtnCreateNew.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnShare.setOnClickListener(this);
        mLlAdd.setVisibility(View.VISIBLE);
        mFunnyGifView.setVisibility(View.GONE);
        mLlTools.setVisibility(View.GONE);
        mTvTips.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_add
                || v.getId() == R.id.btn_create_new) {
            openGallery();
        } else if (v.getId() == R.id.btn_save) {
            saveGif();
        } else if (v.getId() == R.id.btn_share) {
            // TODO: 2019/3/3 分享
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CHOOOSE_GIF) {
                if (data == null
                        || data.getData() == null) {
                    mTvTips.setVisibility(View.VISIBLE);
                    mTvTips.setText("获取Gif失败");
                    return;
                }
                Uri uri = data.getData();
                ContentResolver resolver = getContentResolver();
                try {
                    InputStream inputStream = resolver.openInputStream(uri);
                    mFunnyGifView.setGifResource(inputStream);
                    mLlAdd.setVisibility(View.GONE);
                    mFunnyGifView.setVisibility(View.VISIBLE);
                    mLlTools.setVisibility(View.VISIBLE);
                    mTvTips.setVisibility(View.GONE);
                } catch (FileNotFoundException e) {
                    mTvTips.setVisibility(View.VISIBLE);
                    mTvTips.setText("获取Gif失败");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 打开相册
     */
    private void openGallery() {
        Intent intent  = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_CODE_CHOOOSE_GIF);
    }

    /**
     * 保存新的gif图
     */
    private void saveGif() {
        shortToast("文件已保存");
    }

    /**
     * 简单的短Toast
     */
    private void shortToast(String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
