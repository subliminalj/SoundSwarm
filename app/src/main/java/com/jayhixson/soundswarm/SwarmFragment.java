package com.jayhixson.soundswarm;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jayhixson.soundswarm.R;
import com.jayhixson.soundswarm.SwarmNode;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * Created by jayhixson on 2/26/18.
 * Inflating and setting listeners for a fragment that displays SwarmNode load and play buttons and EditText fields.
 */

public class SwarmFragment extends Fragment {
    private static final int SELECT_FILE_CODE = 0;
    private SwarmNode mSwarmNode;
    private MediaPlayer mMediaPlayerSolo;
    private Button mLoadButton;
    private ToggleButton mPlayButton;
    private EditText mFileNametxt;
    private EditText mTitletxt;
    private EditText mDescriptiontxt;
    private CheckBox mLoopbox;
    private EditText mBegintxt;
    private EditText mEndtxt;
    private EditText mSpeedtxt;
    private ProgressBar mProgressBar;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mSwarmNode = new SwarmNode();
        UUID swarmId = (UUID) getActivity().getIntent().getSerializableExtra(SwarmActivity.EXTRA_SWARM_ID);
        mSwarmNode = Swarm.get(getActivity()).getSwarm(swarmId);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.swarm_node_fragment, container, false);

        mLoadButton = v.findViewById(R.id.LoadButton_solo);
        mLoadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Select an Audio file"), SELECT_FILE_CODE);

            }
        });
        mMediaPlayerSolo = MediaPlayer.create(getContext(),mSwarmNode.getFile());

        mPlayButton = v.findViewById(R.id.playbutton_solo);
        mPlayButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                   // Play button says OFF
                    if (mMediaPlayerSolo.isPlaying()) {
                        mMediaPlayerSolo.stop();
                        updatePB(mMediaPlayerSolo);
                    }

                } else {
                    // The toggle is disabled / Play Button ON

                    Runnable r = new MediaPlayerRunnable(mSwarmNode,mMediaPlayerSolo,mPlayButton);
                    new Thread(r).start();
                    updatePB(mMediaPlayerSolo);
                }

            }
        });

        mFileNametxt = v.findViewById(R.id.file_name_text_solo);
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


        mTitletxt = v.findViewById(R.id.title_text_solo);
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

        mDescriptiontxt = v.findViewById(R.id.description_text_solo);
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

        mLoopbox = v.findViewById(R.id.loopbox_solo);
        mLoopbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSwarmNode.setLoop(isChecked);
                            }
        });

        mBegintxt = v.findViewById(R.id.begin_text_solo);
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
                    Double sdouble = Double.parseDouble(s.toString());
                    mSwarmNode.setSpeed(sdouble);
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Invalid number!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mEndtxt = v.findViewById(R.id.end_text_solo);
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
                    Double sdouble = Double.parseDouble(s.toString());
                    mSwarmNode.setSpeed(sdouble);
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Invalid number!", Toast.LENGTH_SHORT).show();
                }

            }

        });

        mSpeedtxt = v.findViewById(R.id.speed_text_solo);
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
                    Double sdouble = Double.parseDouble(s.toString());
                    mSwarmNode.setSpeed(sdouble);
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Invalid number!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mProgressBar = v.findViewById(R.id.progressBar_solo);

        updateUI();
        return v;


    }

    private void updateUI() {

        mFileNametxt.setText(mSwarmNode.getFileName());
        mTitletxt.setText(mSwarmNode.getTitle());
        mDescriptiontxt.setText(mSwarmNode.getDesc());
        mLoopbox.setChecked(mSwarmNode.isLoop());
        mBegintxt.setText(mSwarmNode.getBegin().toString());
        mEndtxt.setText(mSwarmNode.getEnd().toString());
        mSpeedtxt.setText(mSwarmNode.getSpeed().toString());

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_FILE_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (uri == null) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                try {
                    mSwarmNode.getMp().setDataSource(getContext(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                updateUI();
            }

        }

    }

    private void updatePB(MediaPlayer mp) {
            int mPosition = mp.getCurrentPosition();
            mProgressBar.setProgress(mPosition);
            //update progress bar
    }

}

