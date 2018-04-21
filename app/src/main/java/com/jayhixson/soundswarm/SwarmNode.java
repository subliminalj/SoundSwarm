package com.jayhixson.soundswarm;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;

import java.util.UUID;

/**
 * Created by jayhixson on 2/14/18.
 * Each node holds one file and it's associated properties for playback.
 */

public class SwarmNode {
    private UUID mId;
    private int mFile;
    private String mFileName;
    private String mTitle;
    private String mDesc;
    private boolean mLoop;
    private Double mBegin;
    private Double mEnd;
    private Double mSpeed;
    private MediaPlayer mMp;

    public MediaPlayer getMp() {
        return mMp;
    }

    public void setMp(MediaPlayer mp) {
        mMp = mp;
    }


    public SwarmNode() {
        mId = UUID.randomUUID();
        mFileName = "file.wav";
        mTitle = "Title";
        mDesc = "Description";
        mLoop = false;
        mBegin = 0.0;
        mEnd = 1.0;
        mSpeed = 0.5;
    }

    public UUID getId() {
        return mId;
    }


    public int getFile() { return mFile; }

    public void setFile(int file) { mFile = file; }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public void setFileNameFromUri(Uri fileUri) throws IllegalAccessError {
    // don't use this code... just an example
        // String path = Environment.getRootDirectory().getAbsolutePath(); //Will return "/system"
        // path = path + fileUri.getPath().substring(9); //To cut "content:/" from the Uri path.
        // mFileName = path;
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

    public Double getBegin() {
        return mBegin;
    }

    public void setBegin(Double begin) throws IllegalArgumentException {
        // Pre-condition: begin >= 0.0 && begin <= 1.0
        if (Assertion.PRE) {
            Assertion.isTrue(begin >= 0.0);
            Assertion.isTrue(begin <= 1.0);
        }
        if (begin > 1.0 || begin < 0.0){ throw new IllegalArgumentException(); }
        mBegin = begin;
    }

    public Double getEnd() {
        return mEnd;
    }

    public void setEnd(Double end) throws IllegalArgumentException  {
        // Pre-condition: end >= 0.0 && end <= 1.0
        if (Assertion.PRE) {
            Assertion.isTrue(end >= 0.0);
            Assertion.isTrue(end <= 1.0);
        }

        if (end >= 1.0 || end <= 0.0) { throw new IllegalArgumentException(); }
        mEnd = end;
    }

    public Double getSpeed() { return mSpeed; }

    public void setSpeed(Double speed) throws IllegalArgumentException {
        // Pre-condition: speed >= 0.5 && speed <= 1.0
        if (Assertion.PRE) {
            Assertion.isTrue(speed >= 0.5);
            Assertion.isTrue(speed <= 2.0);
        }
        if (speed > 1.0 || speed < 0.5) { throw new IllegalArgumentException(); }
        mSpeed = speed; }

}

