package com.shinjaehun.suksuk;

/**
 * Created by shinjaehun on 2016-08-01.
 */
public interface TaskCompleted {

//  도전과제를 ListAdapter와 ListView로 표현하지 않고 AchievementTask 결과 생성한 String으로 표현하려고 한 적이 있다.
//  그때 해결 방법이 ProblemFragment에 TaskCompleted 인터페이스의 onTaskCompleted()를 구현해서 결과를 받아오는 방법이었다.
//  이제는 이럴 필요가 없어져서 TaskCompleted 인터페이스도 의미가 없다.

    public void onTaskCompleted(String result);

    //asynctask 결과를 Activity 또는 Fragment로 돌리기 위해 필요한 interface
    //http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
}
