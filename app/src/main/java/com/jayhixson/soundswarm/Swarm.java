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
    public List<MediaPlayer> mMediaPlayers;


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
        for (int i = 0; i < 9; i++){
            SwarmNode swarmNode = new SwarmNode();
            swarmNode.setTitle("Node #"+i);
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
