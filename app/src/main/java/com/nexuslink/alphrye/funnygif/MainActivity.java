package com.nexuslink.alphrye.funnygif;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_CHOOOSE_GIF = 0;

    private LinearLayout mLlAdd;

    private LinearLayout mLlTools;

    private FunnyGifView mFunnyGifView;

    private Button mBtnCreateNew;

    private Button mBtnSave;

    private Button mBtnShare;

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
        mLlAdd.setOnClickListener(this);
        mBtnCreateNew.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnShare.setOnClickListener(this);
        mLlAdd.setVisibility(View.VISIBLE);
        mFunnyGifView.setVisibility(View.GONE);
        mLlTools.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_add
                || v.getId() == R.id.btn_create_new) {
            openGallery();
        } else if (v.getId() == R.id.btn_save) {
            // TODO: 2019/3/3 保存
        } else if (v.getId() == R.id.btn_share) {
            // TODO: 2019/3/3 分享
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CHOOOSE_GIF) {
                mLlAdd.setVisibility(View.GONE);
                mFunnyGifView.setVisibility(View.VISIBLE);
                mLlTools.setVisibility(View.VISIBLE);
                // TODO: 2019/3/3 这里获取gif资源
                mFunnyGifView.setGifResource(R.drawable.test0);
                //并且开始播放gif
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
}
