package com.jayhixson.soundswarm;

import java.util.UUID;

/**
 * Created by jayhixson on 2/14/18.
 * Each node holds one file and it's associated properties for playback.
 */

public class SwarmNode {
    private UUID mId;
    private String mFileName;
    private String mTitle;
    private String mDesc;
    private boolean mLoop;
    private Float mBegin;
    private Float mEnd;
    private Float mSpeed;


    public SwarmNode() {
        mId = UUID.randomUUID();
        mFileName = "filename.wav";
        mTitle = "Title";
        mDesc = "Description";
        mLoop = false;
        /*
        mBegin = 0.0;
        mEnd = file.getLength();
         */
    }

    public UUID getId() {
        return mId;
    }

    public String getFileName() {

        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public boolean isLoop() {
        return mLoop;
    }

    public void setLoop(boolean loop) {
        mLoop = loop;
    }

    public Float getBegin() {
        return mBegin;
    }

    public void setBegin(Float begin) {
        mBegin = begin;
    }

    public Float getEnd() {
        return mEnd;
    }

    public void setEnd(Float end) {
        mEnd = end;
    }

    public Float getSpeed() { return mSpeed; }

    public void setSpeed(Float speed) { mSpeed = speed; }

}

