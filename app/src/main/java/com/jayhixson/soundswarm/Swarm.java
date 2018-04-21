package com.jayhixson.soundswarm;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**A SWARM is a list of SwarmNodes. Herein lies logic for the recycler to use List<SwarmNode>
 * Created by jayhixson on 2/26/18.
 */

public class Swarm {
    private static Swarm sSwarm;

    private List<SwarmNode> mSwarms;
    private List<MediaPlayer> mMediaPlayers;
    private List<Integer> mFiles;

    public List<MediaPlayer> getMediaPlayers() {
        return mMediaPlayers;
    }

    public void setMediaPlayers(List<MediaPlayer> mediaPlayers) {
        mMediaPlayers = mediaPlayers;
    }

    public static Swarm get(Context context) {
        if (sSwarm == null) {
            sSwarm = new Swarm(context);
        }
        return sSwarm;

    }
    private Swarm(Context context){
        mSwarms = new ArrayList<>();
        mFiles = new ArrayList<>();
        mFiles.add(R.raw.a0);
        mFiles.add(R.raw.a1);
        mFiles.add(R.raw.a2);
        mFiles.add(R.raw.a3);
        mFiles.add(R.raw.a4);
        mFiles.add(R.raw.a5);
        mFiles.add(R.raw.a6);
        mFiles.add(R.raw.a7);
        mFiles.add(R.raw.a8);
        mFiles.add(R.raw.a9);

        for (int i = 0; i < 9; i++){
            String filename = "a"+ i;
            SwarmNode swarmNode = new SwarmNode();
            swarmNode.setFileName(filename);
            swarmNode.setTitle("Node #"+i);
            swarmNode.setFile(mFiles.get(i));
            mSwarms.add(swarmNode);
        }
    }

    public List<SwarmNode> getSwarms() {
        return mSwarms;
    }


    public SwarmNode getSwarm(UUID id) {
        for (SwarmNode swarmNode : mSwarms) {
            if (swarmNode.getId().equals(id)){
                return swarmNode;
            }
        }
        return null;
    }
}
