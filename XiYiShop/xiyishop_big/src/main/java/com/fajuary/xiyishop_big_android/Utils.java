package com.fajuary.xiyishop_big_android;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Utils {

    public static void setAmtType(final EditText et_amt) {

        et_amt.addTextChangedListener(new TextWatcher() {
            private boolean isChanged = false;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {// ----->如果字符未改变则返回
                    return;
                }

                String str = s.toString();

                isChanged = true;
                String cuttedStr = str;

                /* 删除字符串中的dot */
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if ('.' == c) {
                        cuttedStr = str.substring(0, i) + str.substring(i + 1);
                        break;
                    }
                }

                /* 加上dot，以显示小数点后两位 */
                int csl = cuttedStr.length();
                if (2 < csl) {
                    cuttedStr = cuttedStr.substring(0, csl - 2) + "." + cuttedStr.substring(csl - 2);
                    csl = cuttedStr.length();
                    if (4 < csl) {
                        // 删除前面多余的0
                        for (int i = 0; i < csl - 3; i++) {
                            char c = cuttedStr.charAt(i);
                            if (i == 0 && c == '0' && i < csl - 4) {
                                cuttedStr = cuttedStr.substring(i + 1);
                            }
                        }
                    }
                } else {
                    cuttedStr = (2 == csl ? "0." : "0.0") + cuttedStr;
                }

                et_amt.setText(cuttedStr);
                et_amt.setSelection(et_amt.length());
                isChanged = false;
            }
        });

    }
}
