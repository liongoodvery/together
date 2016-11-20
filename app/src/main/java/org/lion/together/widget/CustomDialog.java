package org.lion.together.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.utils.ScreenUtils;

import org.lion.together.App;
import org.lion.together.R;
import org.lion.together.utils.CommonUtils;

/**
 * Created by lion on 11/20/16.
 */

public class CustomDialog extends Dialog {
    public CustomDialog(Context context, @LayoutRes int layoutId) {
        super(context, R.style.dialog_style);
        setContentView(LayoutInflater.from(context).inflate(layoutId, null));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = getWindow();
        if (null != window) {
            lp.copyFrom(window.getAttributes());
            lp.width = ScreenUtils.getScreenWidth(App.sContext) - CommonUtils.dp2px(R.dimen.common_margin);
            window.setAttributes(lp);
        }

    }
}
