package com.jayhixson.soundswarm;

import android.net.Uri;
import android.os.Environment;

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
    private Double mBegin;
    private Double mEnd;
    private Double mSpeed;

    public Uri getFile() {
        return mFile;
    }

    public void setFile(Uri file) {
        mFile = file;
    }

    private Uri mFile;

    public SwarmNode() {
        mId = UUID.randomUUID();
        mFileName = "file.wav";
        mTitle = "Title";
        mDesc = "Description";
        mLoop = false;
        mBegin = 0.0;
        mEnd = 1.0;
        mSpeed = 1.0;
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

    public void setFileNameFromUri(Uri fileUri) throws IllegalAccessError {
        String path = Environment.getRootDirectory().getAbsolutePath(); //Will return "/system"
        path = path + fileUri.getPath().substring(9); //To cut "content:/" from the Uri path.
        mFileName = path;
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
        if (begin > 1.0 || begin < 0.0){ throw new IllegalArgumentException(); }
        mBegin = begin;
    }

    public Double getEnd() {
        return mEnd;
    }

    public void setEnd(Double end) throws IllegalArgumentException  {
        if (end > 1.0 || end < 0.0) { throw new IllegalArgumentException(); }
        mEnd = end;
    }

    public Double getSpeed() { return mSpeed; }

    public void setSpeed(Double speed) throws IllegalArgumentException {
        if (speed > 2.0 || speed < 0.0) { throw new IllegalArgumentException(); }
        mSpeed = speed; }

}

