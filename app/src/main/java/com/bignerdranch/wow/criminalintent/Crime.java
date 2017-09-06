package com.bignerdranch.wow.criminalintent;

import java.util.UUID;

/**
 * Created by wow on 2017/9/5.
 */

public class Crime {
    private UUID mId;
    private String mTitle;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;

    }

    public Crime(){
        mId = UUID.randomUUID();

    }
}
