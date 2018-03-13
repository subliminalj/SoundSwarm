package com.jayhixson.soundswarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

public class SwarmActivity extends SingleFragmentActivity {

    public static final String EXTRA_SWARM_ID = "com.jayhixson.soundswarm.swarm_id";

    public static Intent newIntent(Context packageContext, UUID swarmID) {
        Intent intent = new Intent(packageContext, SwarmActivity.class);
        intent.putExtra(EXTRA_SWARM_ID, swarmID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new SwarmFragment();
    }

}
