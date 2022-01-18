package com.example.caloriecalc;

import java.io.IOException;
//TODO: выпил выпил всего
/**
 * Класс для работы с ID
 * @metod generateID() - метод генерации ID

 */


public class IDController {

    /**
     * Метод генерации ID
     * @param pathname  путь файла
     * @return число ID
     * @throws IOException
     */
    public static int generateID(String pathname) throws IOException {
       return DataProcessor.extractIDfromFile(pathname) + 1;
    }
}
