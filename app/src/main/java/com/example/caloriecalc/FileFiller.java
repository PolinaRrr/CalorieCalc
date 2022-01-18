package com.example.caloriecalc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
//TODO: выпил всего
/**
 * Класс работает с любыми файлами: проверяет на пустоту, пишет в файлы
 * @metod fileRecordFill() - метод наполнения файла Записи
 * @metod isFileFull() - метод проверки файла на пустоту
 * @metod fileIDFill() - метод напомнения файлов ID
 * @metod fileCategoryFill() - метод наполнения файла Категории
 * @metod fileDefaultCategoryFill() - метод наполнения файла Категории Дефотлными
 */

public class FileFiller {

    /**
     * Метод наполняющий файл Записи
     *
     * @param pathfile строка с указанием пути файла
     * @param r        Объект запись
     */
    public static void fileRecordFill(String pathfile, Record r) {
        try {
            FileWriter fileWriter = new FileWriter(pathfile, true);
            fileWriter.write(r.getId() + " " + r.getCountOfCalories() + " " + r.getCommentOfRecord() + r.getCategoryOfFood() + " \n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Record.addRecordToCollection(r);
    }

    /**
     * Метод чекает пустой ли файл
     *
     * @param path путь файла
     * @return true or false
     */
    public static boolean isFileFull(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            if (bufferedReader.readLine() != null) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Метод записывает в файл последний ID
     *
     * @param pathfile путь файла
     * @param IDcount значение ID
     */
    public static void fileIDFill(String pathfile, int IDcount) {
        try {
            FileWriter fileWriter = new FileWriter(pathfile, false);
            fileWriter.write(Integer.toString(IDcount));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод добавления в файл Категории новых категорий
     *
     * @param pathfile путь
     * @param c        объект Категория
     */
    public static void fileCategoryFill(String pathfile, Category c) {
        try {
            FileWriter fileWriter = new FileWriter(pathfile, true);
            fileWriter.write(c.getId() + " " + c.getName() + " " + c.getRating());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Category.linkedHashMapCategory.put(c.getId(), c);

    }

    /**
     * Метод дефолтного наполнения файла с категориями
     *
     * @param pathfile путь файла
     */
    public static void fileDefaultCategoryFill(String pathfile, List<Category> c) {
        try {
            FileWriter fileWriter = new FileWriter(pathfile, true);
            for (Category category : c) {
                fileWriter.write(category.getId() + " " + category.getName() + " " + category.getRating() + " \n");
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Category.addCategoryToCollection(c);
    }

    /**
     * Метод переписывающий файл после внесенных изменений
     * @param collection коллекция записей с учетом изменений
     */
    public static void rewriteFile(List<String> collection){
        try {
            FileWriter fileWriter = new FileWriter("newList.txt", true);
            for (String element : collection) {
                fileWriter.write(element + "\n");
            }
            fileWriter.close();

            Files.delete(Paths.get(FileChecker.getFILERECORD()));
            File newFile = new File(FileChecker.getNEWFILERECORD());
            File oldFile = new File(FileChecker.getFILERECORD());
            newFile.renameTo(oldFile);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

