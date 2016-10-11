package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lohan on 11-10-2016.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String INTEGER_TYPE =" INTEGER";
    private static final String CREATE_TASK="CREATE TABLE "+Contract.Task.TABLE_NAME+"("+
            Contract.Task._ID+" INTEGER PRIMARY KEY"+COMMA_SEP+
            Contract.Task.COLUMN_NAME_TYPE+ TEXT_TYPE +COMMA_SEP+
            Contract.Task.COLUMN_NAME_TITLE+ TEXT_TYPE+ COMMA_SEP+
            Contract.Task.COLUMN_NAME_DATE+ TEXT_TYPE+ COMMA_SEP+
            Contract.Task.COLUMN_NAME_START_DATE+TEXT_TYPE+COMMA_SEP+
            Contract.Task.COLUMN_NAME_END_DATE+ TEXT_TYPE+ COMMA_SEP+
            Contract.Task.COLUMN_NAME_NOTE+  TEXT_TYPE+ COMMA_SEP+
            Contract.Task.COLUMN_NAME_REPEAT+ INTEGER_TYPE +COMMA_SEP+
            Contract.Task.COLUMN_NAME_REVISE+ INTEGER_TYPE +COMMA_SEP+
            Contract.Task.COLUMN_NAME_HAS_CHILDREN+ INTEGER_TYPE + COMMA_SEP+//0-FALSE 1-TRUE
            Contract.Task.COLUMN_NAME_IS_CHILD + INTEGER_TYPE +COMMA_SEP+//PARENT ID ELSE -95959595
            Contract.Task.COLUMN_NAME_COMPLETED + INTEGER_TYPE +COMMA_SEP+//0-FALSE 1-TRUE
            Contract.Task.COLUMN_NAME_TO_BE_REMOVED + INTEGER_TYPE +COMMA_SEP+//0-FALSE 1-TRUE
            Contract.Task.COLUMN_NAME_ACTUAL_END_DATE + TEXT_TYPE+COMMA_SEP+
            Contract.Task.COLUMN_NAME_TASK_RATING + INTEGER_TYPE+COMMA_SEP+
            "FOREIGN KEY("+ Contract.Task._ID+")"+" REFERENCES "+ Contract.TaskChildren.TABLE_NAME+"("+ Contract.TaskChildren.COLUMN_NAME_TASK_ID+")"+
            ")";

    private static final String CREATE_TASK_CHILDREN="CREATE TABLE "+Contract.TaskChildren.TABLE_NAME+"("+
            Contract.TaskChildren._ID+" INTEGER PRIMARY KEY"+COMMA_SEP+
            Contract.TaskChildren.COLUMN_NAME_TASK_ID+INTEGER_TYPE+COMMA_SEP+
            Contract.TaskChildren.COLUMN_NAME_CHILD_TASK_ID+ INTEGER_TYPE+ COMMA_SEP+
            "FOREIGN KEY ("+ Contract.TaskChildren.COLUMN_NAME_CHILD_TASK_ID+")"+" REFERENCES "+ Contract.Task.TABLE_NAME+"("+ Contract.Task._ID+")"+
            ")";

    private static final String CREATE_DAILY_DATA="CREATE TABLE "+ Contract.DailyData.TABLE_NAME+"("+
            Contract.DailyData._ID+" INTEGER PRIMARY KEY"+COMMA_SEP+
            Contract.DailyData.COLUMN_NAME_DATE+ TEXT_TYPE+COMMA_SEP+
            Contract.DailyData.COLUMN_NAME_FULFILLED+ TEXT_TYPE+COMMA_SEP+
            Contract.DailyData.COLUMN_NAME_NOTE+ TEXT_TYPE+COMMA_SEP+
            Contract.DailyData.COLUMN_NAME_DAILY_RATING+ INTEGER_TYPE+COMMA_SEP+
            "FOREIGN KEY ("+ Contract.DailyData._ID+")"+" REFERENCES "+ Contract.TasksToBeDone.TABLE_NAME+"("+ Contract.TasksToBeDone.COLUMN_NAME_DAILY_ID+")"+COMMA_SEP+
            "FOREIGN KEY ("+ Contract.DailyData._ID+")"+" REFERENCES "+ Contract.TasksDone.TABLE_NAME+"("+ Contract.TasksDone.COLUMN_NAME_DAILY_ID+")"+
            ")";

    private static final String CREATE_TASKS_TO_BE_DONE="CREATE TABLE "+ Contract.TasksToBeDone.TABLE_NAME+"("+
            Contract.TasksToBeDone._ID+" INTEGER PRIMARY KEY"+COMMA_SEP+
            Contract.TasksToBeDone.COLUMN_NAME_DAILY_ID+INTEGER_TYPE+COMMA_SEP+
            Contract.TasksToBeDone.COLUMN_NAME_TASK_ID+INTEGER_TYPE+COMMA_SEP+
            "FOREIGN KEY ("+ Contract.TasksToBeDone.COLUMN_NAME_TASK_ID+" REFERENCES "+ Contract.Task.TABLE_NAME+"("+ Contract.Task._ID+")"+
            ")";

    private static final String CREATE_TASKS_DONE="CREATE TABLE "+ Contract.TasksDone.TABLE_NAME+"("+
            Contract.TasksDone._ID+" INTEGER PRIMARY KEY"+COMMA_SEP+
            Contract.TasksDone.COLUMN_NAME_DAILY_ID+INTEGER_TYPE+COMMA_SEP+COMMA_SEP+
            Contract.TasksDone.COLUMN_NAME_TASK_ID+ INTEGER_TYPE+COMMA_SEP+COMMA_SEP+
            "FOREIGN KEY ("+ Contract.TasksDone.COLUMN_NAME_TASK_ID+" REFERENCES "+ Contract.Task.TABLE_NAME+"("+ Contract.Task._ID+")"+
            ")";

    private static final String CREATE_LABEL="CREATE TABLE "+ Contract.Label.TABLE_NAME+"("+
            Contract.Label._ID+" INTEGER PRIMARY KEY"+COMMA_SEP+
            Contract.Label.COLUMN_NAME_LABEL_NAME+TEXT_TYPE+COMMA_SEP+
            Contract.Label.COLUMN_NAME_LABEL_RATING+INTEGER_TYPE+COMMA_SEP+
            "FOREIGN KEY ("+ Contract.Label._ID+") REFERENCES "+ Contract.Label_Tasks.TABLE_NAME+"("+ Contract.Label_Tasks.COLUMN_NAME_LABEL_ID+")"+
            ")";
    private static final String CREATE_LABEL_TASKS="CREATE TABLE "+ Contract.Label_Tasks.TABLE_NAME+"("+
            Contract.Label_Tasks._ID+" INTEGER PRIMARY KEY"+COMMA_SEP+
            Contract.Label_Tasks.COLUMN_NAME_LABEL_ID+INTEGER_TYPE+COMMA_SEP+
            Contract.Label_Tasks.COLUMN_NAME_TASK_ID+INTEGER_TYPE+COMMA_SEP+
            "FOREIGN KEY ("+ Contract.Label_Tasks.COLUMN_NAME_TASK_ID+")"+" REFERENCES "+ Contract.Task.TABLE_NAME+"("+ Contract.Task._ID+")"+
            ")";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SomethingToDo.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TASK);
        db.execSQL(CREATE_TASK_CHILDREN);
        db.execSQL(CREATE_DAILY_DATA);
        db.execSQL(CREATE_TASKS_TO_BE_DONE);
        db.execSQL(CREATE_TASKS_DONE);
        db.execSQL(CREATE_LABEL);
        db.execSQL(CREATE_LABEL_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
