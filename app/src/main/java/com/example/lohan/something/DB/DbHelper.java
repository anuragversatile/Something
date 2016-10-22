package com.example.lohan.something.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lohan on 11-10-2016.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String CREATE_TASK = "CREATE TABLE " + Contract.Task.TABLE_NAME + "(" +
            Contract.Task._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            Contract.Task.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
            Contract.Task.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
            Contract.Task.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
            Contract.Task.COLUMN_NAME_START_DATE + TEXT_TYPE + COMMA_SEP +
            Contract.Task.COLUMN_NAME_END_DATE + TEXT_TYPE + COMMA_SEP +
            Contract.Task.COLUMN_NAME_NOTE + TEXT_TYPE + COMMA_SEP +
            Contract.Task.COLUMN_NAME_REPEAT + INTEGER_TYPE + COMMA_SEP +
            Contract.Task.COLUMN_NAME_REVISE + INTEGER_TYPE + COMMA_SEP +
            Contract.Task.COLUMN_NAME_HAS_CHILDREN + INTEGER_TYPE + COMMA_SEP +//0-FALSE 1-TRUE
            Contract.Task.COLUMN_NAME_IS_CHILD + INTEGER_TYPE + COMMA_SEP +//PARENT ID ELSE -95959595
            Contract.Task.COLUMN_NAME_COMPLETED + INTEGER_TYPE + COMMA_SEP +//0-FALSE 1-TRUE
            Contract.Task.COLUMN_NAME_TO_BE_REMOVED + INTEGER_TYPE + COMMA_SEP +//0-FALSE 1-TRUE
            Contract.Task.COLUMN_NAME_ACTUAL_END_DATE + TEXT_TYPE + COMMA_SEP +
            Contract.Task.COLUMN_NAME_TASK_RATING + INTEGER_TYPE + COMMA_SEP +
            "FOREIGN KEY(" + Contract.Task._ID + ")" + " REFERENCES " + Contract.TaskChildren.TABLE_NAME + "(" + Contract.TaskChildren.COLUMN_NAME_TASK_ID + ")" +
            ")";

    private static final String CREATE_TASK_CHILDREN = "CREATE TABLE " + Contract.TaskChildren.TABLE_NAME + "(" +
            Contract.TaskChildren._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            Contract.TaskChildren.COLUMN_NAME_TASK_ID + INTEGER_TYPE + COMMA_SEP +
            Contract.TaskChildren.COLUMN_NAME_CHILD_TASK_ID + INTEGER_TYPE + COMMA_SEP +
            "FOREIGN KEY (" + Contract.TaskChildren.COLUMN_NAME_CHILD_TASK_ID + ")" + " REFERENCES " + Contract.Task.TABLE_NAME + "(" + Contract.Task._ID + ")" +
            ")";

    private static final String CREATE_DAILY_DATA = "CREATE TABLE " + Contract.DailyData.TABLE_NAME + "(" +
            Contract.DailyData._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            Contract.DailyData.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
            Contract.DailyData.COLUMN_NAME_FULFILLED + TEXT_TYPE + COMMA_SEP +
            Contract.DailyData.COLUMN_NAME_NOTE + TEXT_TYPE + COMMA_SEP +
            Contract.DailyData.COLUMN_NAME_DAILY_RATING + INTEGER_TYPE + COMMA_SEP +
            "FOREIGN KEY (" + Contract.DailyData._ID + ")" + " REFERENCES " + Contract.TasksToBeDone.TABLE_NAME + "(" + Contract.TasksToBeDone.COLUMN_NAME_DAILY_ID + ")" + COMMA_SEP +
            "FOREIGN KEY (" + Contract.DailyData._ID + ")" + " REFERENCES " + Contract.TasksDone.TABLE_NAME + "(" + Contract.TasksDone.COLUMN_NAME_DAILY_ID + ")" +
            ")";

    private static final String CREATE_TASKS_TO_BE_DONE = "CREATE TABLE " + Contract.TasksToBeDone.TABLE_NAME + "(" +
            Contract.TasksToBeDone._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            Contract.TasksToBeDone.COLUMN_NAME_DAILY_ID + INTEGER_TYPE + COMMA_SEP +
            Contract.TasksToBeDone.COLUMN_NAME_TASK_ID + INTEGER_TYPE + COMMA_SEP +
            "FOREIGN KEY (" + Contract.TasksToBeDone.COLUMN_NAME_TASK_ID + " REFERENCES " + Contract.Task.TABLE_NAME + "(" + Contract.Task._ID + ")" +
            ")";

    private static final String CREATE_TASKS_DONE = "CREATE TABLE " + Contract.TasksDone.TABLE_NAME + "(" +
            Contract.TasksDone._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            Contract.TasksDone.COLUMN_NAME_DAILY_ID + INTEGER_TYPE + COMMA_SEP + COMMA_SEP +
            Contract.TasksDone.COLUMN_NAME_TASK_ID + INTEGER_TYPE + COMMA_SEP + COMMA_SEP +
            "FOREIGN KEY (" + Contract.TasksDone.COLUMN_NAME_TASK_ID + " REFERENCES " + Contract.Task.TABLE_NAME + "(" + Contract.Task._ID + ")" +
            ")";
    private static final String CREATE_LABEL = "CREATE TABLE " + Contract.Label.TABLE_NAME + "(" +
            Contract.Label._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            Contract.Label.COLUMN_NAME_LABEL_NAME + TEXT_TYPE + COMMA_SEP +
            Contract.Label.COLUMN_NAME_LABEL_RATING + INTEGER_TYPE + COMMA_SEP +
            "FOREIGN KEY (" + Contract.Label._ID + ") REFERENCES " + Contract.Label_Tasks.TABLE_NAME + "(" + Contract.Label_Tasks.COLUMN_NAME_LABEL_ID + ")" +
            ")";
    private static final String CREATE_LABEL_TASKS = "CREATE TABLE " + Contract.Label_Tasks.TABLE_NAME + "(" +
            Contract.Label_Tasks._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            Contract.Label_Tasks.COLUMN_NAME_LABEL_ID + INTEGER_TYPE + COMMA_SEP +
            Contract.Label_Tasks.COLUMN_NAME_TASK_ID + INTEGER_TYPE + COMMA_SEP +
            "FOREIGN KEY (" + Contract.Label_Tasks.COLUMN_NAME_TASK_ID + ")" + " REFERENCES " + Contract.Task.TABLE_NAME + "(" + Contract.Task._ID + ")" +
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

    public void addTask(String task_title, String task_type, String creation_date, Integer revise, String start_date, String end_date,
                        String task_note, Integer repeat, Integer has_children, Integer is_child, Integer completed, Integer to_be_removed,
                        String actual_endDate, Integer task_rating, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(Contract.Task.COLUMN_NAME_TITLE, task_title);
        contentValues.put(Contract.Task.COLUMN_NAME_TYPE, task_type);
        contentValues.put(Contract.Task.COLUMN_NAME_DATE, creation_date);
        contentValues.put(Contract.Task.COLUMN_NAME_REVISE, revise);
        contentValues.put(Contract.Task.COLUMN_NAME_START_DATE, start_date);
        contentValues.put(Contract.Task.COLUMN_NAME_END_DATE, end_date);
        contentValues.put(Contract.Task.COLUMN_NAME_NOTE, task_note);
        contentValues.put(Contract.Task.COLUMN_NAME_REPEAT, repeat);
        contentValues.put(Contract.Task.COLUMN_NAME_HAS_CHILDREN, has_children);
        contentValues.put(Contract.Task.COLUMN_NAME_IS_CHILD, is_child);
        contentValues.put(Contract.Task.COLUMN_NAME_COMPLETED, completed);
        contentValues.put(Contract.Task.COLUMN_NAME_TO_BE_REMOVED, to_be_removed);
        contentValues.put(Contract.Task.COLUMN_NAME_ACTUAL_END_DATE, actual_endDate);
        contentValues.put(Contract.Task.COLUMN_NAME_TASK_RATING, task_rating);

        db.insert(Contract.Task.TABLE_NAME, null, contentValues);


    }

    public void addTaskChildren(Integer task_Id, Integer child_task_Id, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.TaskChildren.COLUMN_NAME_TASK_ID, task_Id);
        contentValues.put(Contract.TaskChildren.COLUMN_NAME_CHILD_TASK_ID, child_task_Id);

        db.insert(Contract.TaskChildren.TABLE_NAME, null, contentValues);
    }


    public void addDailyData(String date, String fulfilled, String note, Integer daily_Rating, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.DailyData.COLUMN_NAME_DATE, date);
        contentValues.put(Contract.DailyData.COLUMN_NAME_FULFILLED, fulfilled);
        contentValues.put(Contract.DailyData.COLUMN_NAME_DAILY_RATING, daily_Rating);
        contentValues.put(Contract.DailyData.COLUMN_NAME_NOTE, note);

        db.insert(Contract.DailyData.TABLE_NAME, null, contentValues);

    }

    public void addTasksToBeDone(Integer daily_Id, Integer task_Id, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.TasksToBeDone.COLUMN_NAME_DAILY_ID, daily_Id);
        contentValues.put(Contract.TasksToBeDone.COLUMN_NAME_TASK_ID, task_Id);
        db.insert(Contract.TasksToBeDone.TABLE_NAME, null, contentValues);
    }

    public void addTasksDone(Integer daily_Id, Integer task_Id, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.TasksDone.COLUMN_NAME_DAILY_ID, daily_Id);
        contentValues.put(Contract.TasksDone.COLUMN_NAME_TASK_ID, task_Id);
        db.insert(Contract.TasksDone.TABLE_NAME, null, contentValues);
    }

    public void addLabel(String label_Name, Integer label_Rating, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Label.COLUMN_NAME_LABEL_NAME, label_Name);
        contentValues.put(Contract.Label.COLUMN_NAME_LABEL_RATING, label_Rating);
        db.insert(Contract.Label.TABLE_NAME, null, contentValues);
    }

    public void addLabelTasks(Integer label_Id, Integer task_Id, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Label_Tasks.COLUMN_NAME_LABEL_ID, label_Id);
        contentValues.put(Contract.Label_Tasks.COLUMN_NAME_TASK_ID, task_Id);
        db.insert(Contract.Label_Tasks.TABLE_NAME, null, contentValues);

    }


    public boolean updateTask(String new_task_title, String new_task_type, String new_creation_Date, Integer new_revise, String new_start_date, String new_end_date,
                              String new_task_note, Integer new_repeat, Integer new_has_children, Integer new_is_child, Integer new_completed, Integer new_to_be_removed,
                              String new_actual_endDate, Integer new_task_rating, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(Contract.Task.COLUMN_NAME_TITLE, new_task_title);
        contentValues.put(Contract.Task.COLUMN_NAME_TYPE, new_task_type);
        contentValues.put(Contract.Task.COLUMN_NAME_DATE, new_creation_Date);
        contentValues.put(Contract.Task.COLUMN_NAME_REVISE, new_revise);
        contentValues.put(Contract.Task.COLUMN_NAME_START_DATE, new_start_date);
        contentValues.put(Contract.Task.COLUMN_NAME_END_DATE, new_end_date);
        contentValues.put(Contract.Task.COLUMN_NAME_NOTE, new_task_note);
        contentValues.put(Contract.Task.COLUMN_NAME_REPEAT, new_repeat);
        contentValues.put(Contract.Task.COLUMN_NAME_HAS_CHILDREN, new_has_children);
        contentValues.put(Contract.Task.COLUMN_NAME_IS_CHILD, new_is_child);
        contentValues.put(Contract.Task.COLUMN_NAME_COMPLETED, new_completed);
        contentValues.put(Contract.Task.COLUMN_NAME_TO_BE_REMOVED, new_to_be_removed);
        contentValues.put(Contract.Task.COLUMN_NAME_ACTUAL_END_DATE, new_actual_endDate);
        contentValues.put(Contract.Task.COLUMN_NAME_TASK_RATING, new_task_rating);
        String whereArgs[] = {Contract.Task._ID};
        db.update(Contract.Task.TABLE_NAME, contentValues, Contract.Task._ID + " =? ", whereArgs);
        return true;

    }

    public boolean updateTaskChildren(Integer new_task_Id, Integer new_child_task_Id, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.TaskChildren.COLUMN_NAME_TASK_ID, new_task_Id);
        contentValues.put(Contract.TaskChildren.COLUMN_NAME_CHILD_TASK_ID, new_child_task_Id);
        String whereArgs[] = {Contract.TaskChildren._ID};
        db.update(Contract.TaskChildren.TABLE_NAME, contentValues, Contract.TaskChildren._ID + " =? ", whereArgs);
        return true;
    }


    public boolean updateDailyData(String new_date, String new_fulfilled, String new_note, Integer new_daily_Rating, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.DailyData.COLUMN_NAME_DATE, new_date);
        contentValues.put(Contract.DailyData.COLUMN_NAME_FULFILLED, new_fulfilled);
        contentValues.put(Contract.DailyData.COLUMN_NAME_DAILY_RATING, new_daily_Rating);
        contentValues.put(Contract.DailyData.COLUMN_NAME_NOTE, new_note);
        String whereArgs[] = {Contract.DailyData._ID};
        db.update(Contract.DailyData.TABLE_NAME, contentValues, Contract.DailyData._ID + " =? ", whereArgs);
        return true;
    }


    public boolean updateTasksToBeDone(Integer new_daily_Id, Integer new_task_Id, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.TasksToBeDone.COLUMN_NAME_DAILY_ID, new_daily_Id);
        contentValues.put(Contract.TasksToBeDone.COLUMN_NAME_TASK_ID, new_task_Id);
        String whereArgs[] = {Contract.TasksToBeDone._ID};
        db.update(Contract.TasksToBeDone.TABLE_NAME, contentValues, Contract.TasksToBeDone._ID + " =? ", whereArgs);
        return true;
    }

    public boolean updateTasksDone(Integer new_daily_Id, Integer new_task_Id, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.TasksDone.COLUMN_NAME_DAILY_ID, new_daily_Id);
        contentValues.put(Contract.TasksDone.COLUMN_NAME_TASK_ID, new_task_Id);
        String whereArgs[] = {Contract.TasksDone._ID};
        db.update(Contract.TasksDone.TABLE_NAME, contentValues, Contract.TasksDone._ID + " =? ", whereArgs);
        return true;
    }

    public boolean updateLabel(String new_label_Name, Integer new_label_Rating, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Label.COLUMN_NAME_LABEL_NAME, new_label_Name);
        contentValues.put(Contract.Label.COLUMN_NAME_LABEL_RATING, new_label_Rating);
        String[] whereArgs = {Contract.Label._ID};
        db.update(Contract.Label.TABLE_NAME, contentValues, Contract.Label._ID + " =? ", whereArgs);
        return true;
    }


    public boolean updateLabelTasks(Integer new_Label_Id, Integer new_Task_Id, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Label_Tasks.COLUMN_NAME_LABEL_ID, new_Label_Id);
        contentValues.put(Contract.Label_Tasks.COLUMN_NAME_TASK_ID, new_Task_Id);
        String[] whereArgs = {Contract.Label_Tasks._ID};
        db.update(Contract.Label_Tasks.TABLE_NAME, contentValues, Contract.Label_Tasks._ID + " =? ", whereArgs);
        return true;
    }

    public Integer deleteTask(Integer id, SQLiteDatabase db) {
        return db.delete(Contract.Task.TABLE_NAME, Contract.Task._ID + " =? ", new String[]{String.valueOf(id)});
    }

    public Integer deleteTaskChildren(Integer id, SQLiteDatabase db) {
        return db.delete(Contract.TaskChildren.TABLE_NAME, Contract.TaskChildren._ID + " =? ", new String[]{String.valueOf(id)});
    }

    public Integer deleteDailyData(Integer id, SQLiteDatabase db) {
        return db.delete(Contract.DailyData.TABLE_NAME, Contract.DailyData._ID + " =? ", new String[]{String.valueOf(id)});
    }

    public Integer deleteTasksToBeDone(Integer id, SQLiteDatabase db) {
        return db.delete(Contract.TasksToBeDone.TABLE_NAME, Contract.TasksToBeDone._ID + " =? ", new String[]{String.valueOf(id)});
    }

    public Integer deleteTasksDone(Integer id, SQLiteDatabase db) {
        return db.delete(Contract.TasksDone.TABLE_NAME, Contract.TasksDone._ID + " =? ", new String[]{String.valueOf(id)});
    }

    public Integer deleteLabel(Integer id, SQLiteDatabase db) {
        return db.delete(Contract.Label.TABLE_NAME, Contract.Label._ID + " =? ", new String[]{String.valueOf(id)});
    }

    public Integer deleteLabelTasks(Integer id, SQLiteDatabase db) {
        return db.delete(Contract.Label_Tasks.TABLE_NAME, Contract.Label_Tasks._ID + " =? ", new String[]{String.valueOf(id)});
    }
public Cursor getAllTaskData()
{
SQLiteDatabase db=this.getWritableDatabase();
    Cursor result=db.rawQuery("select * from"+Contract.Task.TABLE_NAME,null);
    return result;
}
    public Cursor getAllTaskChidrenData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from"+Contract.TaskChildren.TABLE_NAME,null);
        return result;
    }

    public Cursor getDailyTaskData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from"+Contract.DailyData.TABLE_NAME,null);
        return result;
    }

    public Cursor getTasksDoneData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from"+Contract.TasksDone.TABLE_NAME,null);
        return result;
    }
    public Cursor getTasksToBeDoneData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from"+Contract.TasksToBeDone.TABLE_NAME,null);
        return result;
    }

    public Cursor getLabelData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from"+Contract.Label.TABLE_NAME,null);
     int x=0;
        int y=2;        return result;
    }

    public Cursor getAnurag()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from"+Contract.Label_Tasks.TABLE_NAME,null);
        return result;
    }





}