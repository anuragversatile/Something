package com.example.lohan.something.DB;

import android.provider.BaseColumns;

/**
 * Created by lohan on 11-10-2016.
 */

public class Contract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Contract() {}

    /* Inner class that defines the table contents */
    public static class Task implements BaseColumns {//14 columns EXCLUDING _ID
        //// TODO: 10-10-2016 column-taskDoneOnDays/ NotDoneOnDays
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_DATE="date";//when was this inputted
        public static final String COLUMN_NAME_REVISE="revise";//revise duration after completion/ revise during doing
        public static final String COLUMN_NAME_START_DATE = "start_date";
        public static final String COLUMN_NAME_END_DATE = "end_date";
        public static final String COLUMN_NAME_NOTE = "note";
        public static final String COLUMN_NAME_REPEAT = "repeat";//repeat every 3rd day
        public static final String COLUMN_NAME_HAS_CHILDREN = "has_children";//boolean
        public static final String COLUMN_NAME_IS_CHILD = "is_child";//(_id of parent)IF NOT CHILD THEN =NULL/-99999
        public static final String COLUMN_NAME_COMPLETED = "completed";//has the task been completed
        public static final String COLUMN_NAME_TO_BE_REMOVED = "to_be_removed";
        public static final String COLUMN_NAME_ACTUAL_END_DATE = "actual_end_date";
        public static final String COLUMN_NAME_TASK_RATING = "task_rating";
    }//todo: FOREIGN KEY(_ID) REFERENCES TaskChildren(task_id)

    public static class TaskChildren implements BaseColumns {//children of a task
        public static final String TABLE_NAME="task_children";
        public static final String COLUMN_NAME_TASK_ID = "task_id";
        public static final String COLUMN_NAME_CHILD_TASK_ID = "child_task_id";
    }//todo: FOREIGN KEY(child_task_id) REFERENCES Task(_id)

    public static class DailyData implements BaseColumns{
        public static final String TABLE_NAME="daily_data";
        public static final String COLUMN_NAME_DATE="date";
        public static final String COLUMN_NAME_FULFILLED="fulfilled";
        public static final String COLUMN_NAME_NOTE="note";
        public static final String COLUMN_NAME_DAILY_RATING="daily_rating";
    }// TODO: FOREIGN KEY(_ID) REFERENCES tasks_to_be_done(daily_id)
    //TODO: FOREIGN KEY(_ID) REFERENCES tasks_done(daily_id)

    public static class TasksToBeDone implements BaseColumns{//tasks that had to be done on that day
        public static final String TABLE_NAME="tasks_to_be_done";
        public static final String COLUMN_NAME_DAILY_ID = "daily_id";
        public static final String COLUMN_NAME_TASK_ID = "task_id";
    }//todo: FOREIGN KEY(task_id) REFERENCES Task(_ID)

    public static class TasksDone implements BaseColumns{//tasks done that day
        public static final String TABLE_NAME="tasks_done";
        public static final String COLUMN_NAME_DAILY_ID="daily_id";
        public static final String COLUMN_NAME_TASK_ID="task_id";
    }//todo: FOREIGN KEY(task_id) REFERENCES Task(_ID)

    public static class Label implements BaseColumns{
        public static final String TABLE_NAME="label";
        public static final String COLUMN_NAME_LABEL_NAME="label_name";
        public static final String COLUMN_NAME_LABEL_RATING="label_rating";
    }//// todo: FOREIGN KEY(_ID) REFERENCES label_tasks(label_id)

    public static class Label_Tasks implements BaseColumns{
        public static final String TABLE_NAME="label_tasks";
        public static final String COLUMN_NAME_LABEL_ID="label_id";
        public static final String COLUMN_NAME_TASK_ID="task_id";
    }//todo: FOREIGN KEY(task_id) references task(_ID)
}
