package com.jayhixson.soundswarm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.jayhixson.soundswarm.R;
import com.jayhixson.soundswarm.SwarmNode;

import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * Created by jayhixson on 2/26/18.
 * Inflating and setting listeners for a fragment that displays SwarmNode load and play buttons and EditText fields.
 */

public class SwarmFragment extends Fragment {
    private static final int SELECT_FILE_CODE = 0;
    private SwarmNode mSwarmNode;
    private Button mLoadButton;
    private Button mPlayButton;
    private EditText mFileNametxt;
    private EditText mTitletxt;
    private EditText mDescriptiontxt;
    private CheckBox mLoopbox;
    private EditText mBegintxt;
    private EditText mEndtxt;
    private EditText mSpeedtxt; // needs error handling

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mSwarmNode = new SwarmNode();
        UUID swarmId = (UUID) getActivity().getIntent().getSerializableExtra(SwarmActivity.EXTRA_SWARM_ID);
        mSwarmNode = Swarm.get(getActivity()).getSwarm(swarmId);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.swarm_node_fragment, container, false);

        mLoadButton = (Button) v.findViewById(R.id.LoadButton_solo);
        mLoadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Select an Audio file"), SELECT_FILE_CODE);

            }
        });

        mPlayButton = (Button) v.findViewById(R.id.playbutton_solo);
        mFileNametxt = (EditText) v.findViewById(R.id.file_name_text_solo);
        mFileNametxt.setText(mSwarmNode.getFileName());
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


        mTitletxt = (EditText) v.findViewById(R.id.title_text_solo);
        mTitletxt.setText(mSwarmNode.getTitle());
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

        mDescriptiontxt = (EditText) v.findViewById(R.id.description_text_solo);
        mDescriptiontxt.setText(mSwarmNode.getDesc());
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

        mLoopbox = (CheckBox)v.findViewById(R.id.loopbox_solo);
        mLoopbox.setChecked(mSwarmNode.isLoop());
        mLoopbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSwarmNode.setLoop(isChecked);
                            }
        });

        mBegintxt = (EditText) v.findViewById(R.id.begin_text_solo);
        mBegintxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                //blank intentionally
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
               // blank intentionally
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Float sfloat = Float.parseFloat(s.toString());
                    mSwarmNode.setBegin(sfloat);
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Invalid number!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mEndtxt = (EditText) v.findViewById(R.id.end_text_solo);
        mEndtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                //blank intentionally
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                // blank intentionally
            }
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Float sfloat = Float.parseFloat(s.toString());
                    mSwarmNode.setEnd(sfloat);
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Invalid number!", Toast.LENGTH_SHORT).show();
                }

            }

        });

        mSpeedtxt = (EditText) v.findViewById(R.id.speed_text_solo);
        mSpeedtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                //blank intentionally
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                //blank intentionally

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Float sfloat = Float.parseFloat(s.toString());
                    mSwarmNode.setSpeed(sfloat);
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Invalid number!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return v;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_FILE_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (uri == null) {
               // Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                mSwarmNode.setFile(uri);
            }

        }

    }

}

