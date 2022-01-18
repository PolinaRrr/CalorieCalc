package com.example.caloriecalc;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс обработки данных. Работает с данными и Записей и Категорий
 *
 * @metod createCollectionOfRecords()  - создает коллекцию из записей в файле
 * @metod deliteElementFromCollection() - удаляет заданный лемеент из коллекции записями
 * @metod extractIDfromFile() - возвращает ID из файлой
 * @metod returnNameCategory() - возвращает название категорий
 * @metod extractArrNameCategory() - возвращает массив названий Категорий
 */

public class DataProcessor {

    //TODO: выпилить
    /**
     * Метод, создающий коллекцию записей из файла
     *
     * @return коллекцию
     */
    public static List<String> createCollectionOfRecords(String pathname) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathname));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    //TODO:выпил
    /**
     * Метод удаляющий элемент в коллекции
     *
     * @param elementToDelete номер строки/элемента для удаления
     * @return откорректированную коллекцию
     */
    public static List<String> deliteElementFromCollection(int elementToDelete) {
        return Collections.singletonList(createCollectionOfRecords(FileChecker.getFILERECORD())
                .remove(elementToDelete - 1));
    }

    //TODO:выпил
    /**
     * Метод извлечения данных их файла c ID
     *
     * @param path путь файла
     * @return ID
     */
    public static int extractIDfromFile(String path) {
        int lastID = 0;
        if (FileFiller.isFileFull(path)) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
                lastID = Integer.parseInt(bufferedReader.readLine());
                return lastID;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lastID;
    }


    //TODO: выпил
    /**
     * Метод возвращает название Категории
     *
     * @param s строка, содержащая название Категории
     * @return конкретное навзание Категории
     */
    public static String returnNameCategory(String s) {
        String[] arrWords = s.split(" ");
        return (arrWords[1]);
    }

    //TODO: переделать в вывод списка всех категорий
    /**
     * Метод извлекающий массив названий Категорий
     *
     * @param category коллекуия из файла Категорий
     * @return массив названий Категорий
     */
    public static String[] extractArrNameCategory(List<String> category) {
        String[] arrNameCategory = new String[category.size()];
        for (int i = 0; i < category.size(); i++) {
            arrNameCategory[i] = returnNameCategory(category.get(i));
        }
        return arrNameCategory;
    }

    //TODO: выпил
    /**
     * Метод возвращает элемент в коллекции из файла
     */
    public static int returnIndexOfElement(String word, String pathname) {
        int index = 0;
        List<String> lines = createCollectionOfRecords(pathname);
        for (int i = 0; i < lines.size(); i++) {
            String[] arr = lines.get(i).split(" ");
            for (int j = 0; j < arr.length; j++) {
                if(arr[i].equals(word)){
                   return index = i;
                }
            }
        }
        return index;
    }
}
