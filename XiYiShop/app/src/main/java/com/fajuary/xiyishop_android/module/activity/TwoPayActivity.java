package com.fajuary.xiyishop_android.module.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.DisplayUtils;
import com.fajuary.xiyishop_android.tools.QRCodeEncoder;


/**
 *
 *@author 黄辉
 *@time 2016/10/13 0:01
 *
 * 扫码支付页面
*/
public class TwoPayActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout iv_back;
    private ImageView iv_icon;
    private TextView tv_money_sm;

    private String result = null;
    private String result1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_two_pay);
        iv_back = (LinearLayout) findViewById(R.id.iv_back);
        iv_icon= (ImageView) findViewById(R.id.iv_icon);
        tv_money_sm= (TextView) findViewById(R.id.tv_money_sm);

        //获取Activity传递过来的内容
        result = getIntent().getStringExtra("ti");
        result1 = getIntent().getStringExtra("to");
        tv_money_sm.setText(result1);
        //点击事件 返回
        iv_back.setOnClickListener(this);
//        Toast.makeText(TwoPayActivity.this, "内容"+result1, Toast.LENGTH_SHORT).show();
        createQRCode();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 创建二维码
     */
    private void createQRCode() {

        //生成二维码，第一个参数为要生成的文本，第二个参数为生成尺寸，第三个参数为生成回调
        QRCodeEncoder.encodeQRCode(result, DisplayUtils.dp2px(TwoPayActivity.this, 160), new QRCodeEncoder.Delegate() {
            /**
             * 生成成功
             * @param bitmap
             */
            @Override
            public void onEncodeQRCodeSuccess(Bitmap bitmap) {
                iv_icon.setImageBitmap(bitmap);
            }
            /**
             * 生成失败
             */
            @Override
            public void onEncodeQRCodeFailure() {
                Toast.makeText(TwoPayActivity.this, "生成中文二维码失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }
}