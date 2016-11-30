package org.lion.together.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.lion.together.R;

/**
 * Created by lion on 11/30/16.
 */

public class FragmentUtils {
    public static void switchContent(FragmentActivity fragmentActivity, Fragment fragment) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containner, fragment)
                .commit();
        fragmentActivity.invalidateOptionsMenu();
    }

    public static void addToBackStack(FragmentActivity fragmentActivity,Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containner, fragment)
                .addToBackStack(null)
                .commit();
        fragmentActivity.invalidateOptionsMenu();
    }

}
