package com.jayhixson.soundswarm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class SwarmActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SwarmFragment();
    }

}
