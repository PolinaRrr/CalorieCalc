package com.example.caloriecalc;

import java.util.List;

/**
 * Класс работает с Записями.
 * @metod add() - метод добавляет новую запись
 * @metod list() -  метод отображает n-количество записей
 * @metod returnIDcount() - метод возвращает ID конкретной строки
 * @metod returnCommentToRecord() - метод возвращает комментарий конкретной строки
 * @metod getRecordByNumber() - метод возращает запись по номеру элеменнта в коллекции
 */
public class RecordController {
    //TODO: выпил
    /**
     * Метод, добавляющий запись в файл
     *
     * @param o объект
     */
    public static void add(Record o) {
        FileFiller.fileRecordFill(FileChecker.getFILERECORD(),o);
    }
//TODO: выпил
    /**
     * Метод вывода конкретной записи
     *
     * @param records номер строки в файле
     */
    public static void list(int records) {
        List<String> stringsList = DataProcessor.createCollectionOfRecords(FileChecker.getFILERECORD());
        if (stringsList.size() <= records) {
            for (int i = 0; i < stringsList.size(); i++) {
                System.out.println((i + 1) + " " + stringsList.get(i));
            }
        } else {
            for (int i = stringsList.size() - records; i < stringsList.size(); i++) {
                System.out.println((i + 1) + " " + stringsList.get(i));
            }
        }
    }

    //TODO: переписать на получение из БД
    /**
     * Метод, возвращающий значение ID в конкретной строке
     * @param s строка в файле
     * @return количество каллорий
     */
    public static int returnIDcount(String s) {
        String[] arrWords = s.split(" ");
        return Integer.parseInt(arrWords[0]);
    }

    //TODO: выпил
    /**
     * Метод, возвращающий комментарий к конкретной записи
     *
     * @param s строка в файле
     * @return комментарий
     */
    public static String returnCommentToRecord(String s) {
        String[] arrWords = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < arrWords.length; i++) {
            stringBuilder.append(arrWords[i]).append(" ");
        }
        return stringBuilder.toString();
    }

    //TODO: выпил
    /**
     * Метод, получающий запись из файла по номеру
     *
     * @param recordToDelete порядковый номер записи
     * @return строка из файла
     */
    public static String getRecordByNumber(int recordToDelete) {
        return DataProcessor.createCollectionOfRecords(FileChecker.getFILERECORD()).get(recordToDelete - 1);
    }


}
