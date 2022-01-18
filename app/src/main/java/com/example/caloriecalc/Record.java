package com.example.caloriecalc;

import java.util.LinkedHashMap;

public class Record {

    //TODO: выпил
    private int id;
    private int countOfCalories;
    private String commentOfRecord;
    private String categoryOfFood;

    static LinkedHashMap<Integer,Object> linkedHashMapRecord = new LinkedHashMap<>();


    public Record(int id, int countOfCalories, String commentOfRecord, String categoryOfFood) {
        this.id = id;
        this.countOfCalories = countOfCalories;
        this.commentOfRecord = commentOfRecord;
        this.categoryOfFood = categoryOfFood;

    }

    /**
     * Метод добавления объектов в коллекцию
     * @param r Объект Запись
     */
    public static void addRecordToCollection(Record r){
        linkedHashMapRecord.put(r.getId(),r);
        FileFiller.fileIDFill(FileChecker.getFILELASTIDRECORD(),r.getId());
    }

    public int getCountOfCalories() {
        return countOfCalories;
    }

    public String getCommentOfRecord() {
        return commentOfRecord;
    }

    public int getId() {
        return id;
    }

    public String getCategoryOfFood() {
        return categoryOfFood;
    }


}
