package com.jayhixson.soundswarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        private TextView mTitleTextView;
        private TextView mDescriptionTextView;
        private TextView mFileTextView;
        private SwarmNode mSwarmNode;

        public void bind(SwarmNode swarmNode){
            mSwarmNode = swarmNode;
            mTitleTextView.setText(mSwarmNode.getTitle());
            mDescriptionTextView.setText(mSwarmNode.getDesc());
            mFileTextView.setText(mSwarmNode.getFileName());
        }

        public SwarmHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_swarm, parent, false));
            itemView.setOnClickListener(this);
        mTitleTextView = (TextView) itemView.findViewById(R.id.title_text);
        mDescriptionTextView = (TextView) itemView.findViewById(R.id.description_text);
        mFileTextView = (TextView) itemView.findViewById(R.id.file_name_text);
        }

        @Override
        public void onClick(View v) {
           // Toast.makeText(getActivity(), mSwarmNode.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
           // Intent intent = new Intent(getActivity(),SwarmActivity.class);
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
