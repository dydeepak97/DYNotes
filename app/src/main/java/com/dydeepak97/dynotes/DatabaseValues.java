package com.dydeepak97.dynotes;



public class DatabaseValues {


    public  static final int DATABSE_VERSION = 1;

    public static final String DATABSE_NAME = "dynotes";

    public static final String TABLE_NOTES= "notes";

    //Notes table column names
    public static final String NOTES_ID="id";
    public static final String NOTES_TITLE="title";
    public static final String NOTES_DESCRIPTION="description";


    //Create Table Queries
    public static final String TABLE_NOTES_CREATE="CREATE TABLE IF NOT EXISTS " + TABLE_NOTES +
            "(" + NOTES_ID +" INTEGER PRIMARY KEY, "+ NOTES_TITLE +" TEXT, " + NOTES_DESCRIPTION + " TEXT )";

    //Drop Table Queries
    public static final String TABLE_NOTES_DROP="DROP TABLE IF EXISTS " + TABLE_NOTES;



}
