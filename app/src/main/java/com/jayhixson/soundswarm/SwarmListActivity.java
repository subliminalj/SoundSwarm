package com.jayhixson.soundswarm;


/**
 * Created by jayhixson on 2/27/18.
 */

public class SwarmListActivity extends SingleFragmentActivity {
@Override
    protected android.support.v4.app.Fragment createFragment(){
    return new SwarmListFragment();
}

}
