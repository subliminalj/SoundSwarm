package com.jayhixson.soundswarm;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.List;



/**
 * Created by jayhixson on 2/27/18. RecyclerView for the SwarmList
 */

public class SwarmListFragment extends android.support.v4.app.Fragment {
    private RecyclerView mSwarmRecyclerView;
    private SwarmAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swarm_list, container, false);

        mSwarmRecyclerView = (RecyclerView)view.findViewById(R.id.swarm_recycler_view);
        mSwarmRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        Swarm swarm = Swarm.get(getActivity());
        List<SwarmNode> swarmNodes = swarm.getSwarms();

        mAdapter = new SwarmAdapter(swarmNodes);
        mSwarmRecyclerView.setAdapter(mAdapter);
    }

    private class SwarmHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button mEditButton;
        private ToggleButton mPlay;
        private TextView mFileTextView;
        private TextView mTitleTextView;
        private TextView mDescriptionTextView;
        private TextView mBeginTextView;
        private TextView mEndTextView;
        private CheckBox mLoopbox;
        private TextView mSpeedTextView;
        private ProgressBar mProgressBar;
        private SwarmNode mSwarmNode;
        private MediaPlayer mMediaPlayer;

        public void bind(SwarmNode swarmNode){

            mSwarmNode = swarmNode;
            mMediaPlayer = mSwarmNode.getMp();
            mFileTextView.setText(mSwarmNode.getFileName());
            mTitleTextView.setText(mSwarmNode.getTitle());
            mDescriptionTextView.setText(mSwarmNode.getDesc());
            mBeginTextView.setText(mSwarmNode.getBegin().toString());
            mEndTextView.setText(mSwarmNode.getEnd().toString());
            mLoopbox.setChecked(mSwarmNode.isLoop());
            mSpeedTextView.setText(mSwarmNode.getSpeed().toString());
        }

        public SwarmHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_swarm, parent, false));
            itemView.setOnClickListener(this);
        mEditButton = (Button) itemView.findViewById(R.id.editbutton);
        mPlay = (ToggleButton) itemView.findViewById(R.id.playbutton);
        mFileTextView = (TextView) itemView.findViewById(R.id.file_name_text);
        mTitleTextView = (TextView) itemView.findViewById(R.id.title_text);
        mDescriptionTextView = (TextView) itemView.findViewById(R.id.description_text);
        mBeginTextView = (TextView) itemView.findViewById(R.id.begin_text);
        mEndTextView = (TextView) itemView.findViewById(R.id.end_text);
        mLoopbox = (CheckBox) itemView.findViewById(R.id.loopbox);
        mSpeedTextView = (TextView) itemView.findViewById(R.id.speed_text);
        mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

            mPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isChecked) {
                        // Play button says OFF
                        if (mMediaPlayer.isPlaying()) {
                            mMediaPlayer.stop();
                        }

                    } else {
                        // The toggle is disabled / Play Button ON
                        Runnable r = new MediaPlayerRunnable(mSwarmNode,mSwarmNode.getMp(),mPlay);
                        new Thread(r).start();
                    }
                }
            });

            mEditButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = SwarmActivity.newIntent(getActivity(), mSwarmNode.getId());
                    startActivity(intent);
                }
            });

        }

        @Override
        public void onClick(View v) {
            Intent intent = SwarmActivity.newIntent(getActivity(), mSwarmNode.getId());
            startActivity(intent);
        }
    }

    private class SwarmAdapter extends RecyclerView.Adapter<SwarmHolder> {
        private List<SwarmNode> mSwarmNode;
        public SwarmAdapter(List<SwarmNode> swarmNode) {
            mSwarmNode = swarmNode;
        }

        @Override
        public SwarmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new SwarmHolder(layoutInflater,parent);

        }

        @Override
        public void onBindViewHolder(SwarmHolder holder, int position) {
        SwarmNode swarmNode = mSwarmNode.get(position);
        holder.bind(swarmNode);
        }

        @Override
        public int getItemCount() {
            return mSwarmNode.size();
        }
    }
}
