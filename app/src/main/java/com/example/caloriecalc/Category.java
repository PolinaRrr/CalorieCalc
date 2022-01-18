package com.example.caloriecalc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private int rating;

    static LinkedHashMap<Integer, Object> linkedHashMapCategory = new LinkedHashMap<>();

    /**
     * коллекция объектов!
     */
    static List<Category> defaultCategory = new ArrayList<>();

    public Category(int id, String name, int rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }


    //TODO: выпиливать
    /**
     * Метод добавления категорий в коллекцию
     *
     * @param c Коллекция категорий
     */
    public static void addCategoryToCollection(List<Category> c) {
        for (Category category : c) {
            linkedHashMapCategory.put(category.id, category);
            FileFiller.fileIDFill(FileChecker.getFILELASTIDCATEGORY(), category.id);
        }
    }

    //TODO: выпиливать
    /**
     * Метод добавления в коллекцию дефолтных категорий
     */
    public static void addDefaultCategoryToCollection() throws IOException {

        String[] arrCategories = new String[]{"Meat", "Vegetable", "Milky", "Fruits", "Confectionery"};
        int[] arrRating = new int[]{2, 1, 2, 3, 5};
        for (int i = 0; i < arrCategories.length; i++) {
            defaultCategory.add(new Category(IDController.generateID(FileChecker.getFILELASTIDCATEGORY()), arrCategories[i], arrRating[i]));
            FileFiller.fileIDFill(FileChecker.getFILELASTIDCATEGORY(), defaultCategory.get(i).getId());
        }
    }

    //TODO: переделать удаление из БД SQLController.delCategoriesDB
    /**
     * Метод удаления категории из файла
     *
     * @param nameCategory название категории
     */
    public static void deleteCategoryFromCollection(String nameCategory) {
        FileFiller.rewriteFile(Collections.singletonList(DataProcessor
                .createCollectionOfRecords(FileChecker.getFILECATEGORY())
                .remove(DataProcessor.returnIndexOfElement(nameCategory, FileChecker.getFILECATEGORY()))));
    }

    //TODO:  переделать в редактирование в БД SQLController.edit..
    /**
     * Метод редактирования названия категории в файле
     *
     * @param nameCategory навзвание категории
     */
    public static void editNameCategoryFromCollection(String nameCategory, String newName) throws NullPointerException {
        String strForEdit = DataProcessor.createCollectionOfRecords(FileChecker.getFILECATEGORY())
                .get(DataProcessor.returnIndexOfElement(nameCategory, FileChecker.getFILECATEGORY()));
        String[] arrWordsInString = strForEdit.split(" ");
        StringBuilder newString = null;
        for (String s : arrWordsInString) {
            if (s.equals(nameCategory)) {
                newString.append(" " + newName + " ");
            }
            newString.append(" " + s + " ");
        }
        FileFiller.rewriteFile(Collections.singletonList(DataProcessor.createCollectionOfRecords(FileChecker.getFILECATEGORY())
                .set(DataProcessor.returnIndexOfElement(nameCategory, FileChecker.getFILECATEGORY()), String.valueOf(newString))));
    }

    //TODO: заменить на редакт в БД и запилить в один метод
    /**
     * Метод редактирования коэффициента категории в файле
     *
     * @param nameCategory навзвание категории
     */
    public static void editRatingCategoryFromCollection(String nameCategory, int newRating) {
        String strForEdit = DataProcessor.createCollectionOfRecords(FileChecker.getFILECATEGORY())
                .get(DataProcessor.returnIndexOfElement(nameCategory, FileChecker.getFILECATEGORY()));
        String[] arrWordsInString = strForEdit.split(" ");
        StringBuilder newString = null;
        String raiting = arrWordsInString[2];
        for (String s : arrWordsInString) {
            if (s.equals(raiting)) {
                newString.append(" " + newRating + " ");
            }
            newString.append(" " + s + " ");
        }
        FileFiller.rewriteFile(Collections.singletonList(DataProcessor.createCollectionOfRecords(FileChecker.getFILECATEGORY())
                .set(DataProcessor.returnIndexOfElement(nameCategory, FileChecker.getFILECATEGORY()), String.valueOf(newString))));
    }

}
