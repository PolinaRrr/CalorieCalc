package com.example.caloriecalc;

import java.io.File;

/**
 * Класс системных файлом, содержит константы путей файлов и метод проверки на наличие этих системных файлов
 * @metod checkFile()  - проверяет наличе системного файла
 * @variable FILERECORD    - системный файл, реестр записей.
 * @variable NEWFILERECORD    - системный файл, реестр записей.
 * @variable FILECATEGORY  -  системный файл, реестр категорий.
 * @variable FILELASTIDCATEGORY  - системный файл, реестр ID категорий.
 * @variable FILELASTIDRECORD    - системный файл, реестр ID записей.
 *
 */
public class FileChecker {
    //TODO:выпилить и написать путь для логгера
    private static final String FILERECORD = "list.txt";
    private static final String NEWFILERECORD = "newList.txt";
    private static final String FILECATEGORY = "category.txt";
    private static final String FILELASTIDCATEGORY = "lastIDcategory.txt";
    private static final String FILELASTIDRECORD = "lastIDrecord.txt";


    //TODO: переписать на файл логера
    /**
     * Метод чекает наличие файла
     * @param filename путь файла
     * @return true - если файл есть
     */
    public static boolean checkFile(final String filename){
        File file = new File(filename);
        return file.exists();
    }
    //TODO: выпил
    public static String getFILELASTIDCATEGORY() {
        return FILELASTIDCATEGORY;
    }
    //TODO: выпил
    public static String getNEWFILERECORD() {
        return NEWFILERECORD;
    }
    //TODO: выпил
    public static String getFILELASTIDRECORD() {
        return FILELASTIDRECORD;
    }
    //TODO: выпил
    public static String getFILERECORD() {
        return FILERECORD;
    }
    //TODO: выпил
    public static String getFILECATEGORY() {
        return FILECATEGORY;
    }
}
