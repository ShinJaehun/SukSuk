package com.shinjaehun.suksuk;

/**
 * Created by shinjaehun on 2016-08-01.
 */
public interface TaskCompleted {
    public void onTaskCompleted(String result);

    //asynctask 결과를 Activity 또는 Fragment로 돌리기 위해 필요한 interface
    //http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
}
