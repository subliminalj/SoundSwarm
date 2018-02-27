package com.jayhixson.soundswarm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.jayhixson.soundswarm.R;
import com.jayhixson.soundswarm.SwarmNode;

/**
 * Created by jayhixson on 2/26/18.
 * Inflating and setting listeners for a fragment that displays SwarmNode buttons and EditText fields.
 */

public class SwarmFragment extends Fragment {
    private SwarmNode mSwarmNode;
    private EditText mFileNametxt;
    private EditText mTitletxt;
    private EditText mDescriptiontxt;
    private CheckBox mLoopbox;
  //  private EditText mBegintxt; not implemented - needs error handling

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwarmNode = new SwarmNode();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.swarm_node_fragment, container, false);

        mFileNametxt = (EditText) v.findViewById(R.id.file_name_text);
        mFileNametxt.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after){
                //blank intentionally
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count){
                mSwarmNode.setFileName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //blank intentionally
            }

        });


        mTitletxt = (EditText) v.findViewById(R.id.title_text);
        mTitletxt.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after){
                //blank intentionally
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count){
                        mSwarmNode.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                        //blank intentionally
            }

    });

        mDescriptiontxt = (EditText) v.findViewById(R.id.description_text);
        mDescriptiontxt.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(
                     CharSequence s, int start, int count, int after) {
                 //blank intentionally
             }

             @Override
             public void onTextChanged(
                     CharSequence s, int start, int before, int count) {
                 mSwarmNode.setDesc(s.toString());
             }

             @Override
             public void afterTextChanged(Editable s) {
                 //blank intentionally
             }
        });

        mLoopbox = (CheckBox)v.findViewById(R.id.loopbox);
        mLoopbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSwarmNode.setLoop(isChecked);
            }
        });
/*
        mBegintxt = (EditText) v.findViewById(R.id.begin_text);
        mBegintxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                //blank intentionally
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                try {
                    Float sfloat = Float.parseFloat(s.toString());
                    mSwarmNode.setBegin(sfloat);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                //blank intentionally
            }
        });
*/
        return v;


    }
}
