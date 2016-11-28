package com.fajuary.xiyishop_android.module.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
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
 *@time 2016/10/27 20:11
 *
 * 收尾款二维码页面
*/

public class WeiKuanPayActivity extends BaseActivity {

    private String orderGoodsId;
    private String shopId;
    private String goodsName;
    private TextView tv_money_sm;//显示的金额
    private ImageView iv_icon;
    private String shopName;//用户名
    private String content;
    private String money;
    private LinearLayout iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_wei_kuan_pay);
        content = getIntent().getStringExtra("content");
        money = getIntent().getStringExtra("money");
        tv_money_sm = (TextView) findViewById(R.id.tv_money_sm);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        iv_back = (LinearLayout) findViewById(R.id.iv_back);
        tv_money_sm.setText(money);
        Log.e("TAG",content+"=========================================--------------------------");
        createQRCodew();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 创建二维码
     */
    private void createQRCodew() {
        //生成二维码，第一个参数为要生成的文本，第二个参数为生成尺寸，第三个参数为生成回调
        QRCodeEncoder.encodeQRCode(content, DisplayUtils.dp2px(WeiKuanPayActivity.this, 160), new QRCodeEncoder.Delegate() {
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
                Toast.makeText(WeiKuanPayActivity.this, "生成中文二维码失败", Toast.LENGTH_SHORT).show();
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
