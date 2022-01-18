package com.example.caloriecalc;

import java.io.File;
import java.io.IOException;

//TODO: выпил

/**
 * Класс создает системыне файла
 * @metod createFile()  - создает файл по указанному пути
 *
 */

public class FileCreator {

    /**
     * Метод создает файл
     * @param pathfile путь файла
     */
    public static void createFile(String pathfile){
        File file = new File(pathfile);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
