package com.example.profileusers.profile;

import android.os.Bundle;

public class Event {

    final Bundle content;

    public Event(Bundle content){
        this.content = content;
    }

    private boolean isHandled = false;

    public Bundle getContent() {
        return content;
    }

    public boolean isHandled() {
        if(isHandled) {
            return false;
        }
        else {
            isHandled = true;
            return true;
        }
    }

}
