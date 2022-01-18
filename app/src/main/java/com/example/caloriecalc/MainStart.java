package com.example.caloriecalc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

//TODO: написать юнит тесты для основных методов


public class MainStart {
    //TODO: выпил
    static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    public static void mainStart(String[] args) throws IOException, SQLException {

        try {
            if (!FileChecker.checkFile(FileChecker.getFILECATEGORY())) {
                FileCreator.createFile(FileChecker.getFILECATEGORY());
                FileCreator.createFile(FileChecker.getFILELASTIDCATEGORY());
                FileCreator.createFile(FileChecker.getFILELASTIDRECORD());
                Category.addDefaultCategoryToCollection();
                FileFiller.fileDefaultCategoryFill(FileChecker.getFILECATEGORY(),Category.defaultCategory);
            }
            SQLController.connect();
            System.out.println(SQLController.checkDBTableRecords());
            if(!SQLController.checkDBTableRecords()){
                SQLController.checkDBTableRecords();
            }
            System.out.println(SQLController.checkDBTableCategories());
            if(!SQLController.checkDBTableCategories()){
                SQLController.createTableCategory();
            }
            System.out.println(SQLController.checkDBTableCategoriesIsFull());
            if(!SQLController.checkDBTableCategoriesIsFull()){
                SQLController.createEntryDefaultCategoriesDB();
                System.out.println("Таблицу наполнили");
            }
            startProcess();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            SQLController.disconnect();
        }

    }

    //TODO:  переписать
    /**
     * Метод запускающий процесс считывания ответов с консоли
     */
    public static void startProcess() throws SQLException {
        System.out.println("Enter command: add, list, del or exit");

        String command;
        do {
            command = sc.nextLine();
            processing(command);
        }while (true);
    }


    //TODO: переписать на ввод через графический модуль

    /**
     * Метод обработки вводимых из консоли команд
     * @param s команда
     */
    public static void processing(String s) throws SQLException {

        switch (s) {
            case "add":

                //TODO:проверка через каст строки в инт

                System.out.println("Enter amount of calories");

                String countCaloriesStr;

                do {
                    countCaloriesStr = sc.next();
                    Validator.promptCorrectEnterForCountCalories(countCaloriesStr);

                }while (!Validator.isConvertStringToInt(countCaloriesStr));


                System.out.println("Choose a category from the suggested ones: " + Arrays.toString(DataProcessor.extractArrNameCategory(DataProcessor.createCollectionOfRecords(FileChecker.getFILECATEGORY()))));

                String selectedCategory;

                do {
                    selectedCategory= sc.next();
                    Validator.promptCorrectEnterNameCategory(selectedCategory);
                }while (!Validator.isValidNameCategory(selectedCategory));

                System.out.println("Enter your comment");

                String comment = sc.next();

                System.out.println(comment);

                try {
                    RecordController.add(new Record(IDController.generateID(FileChecker.getFILELASTIDRECORD()),Integer.parseInt(countCaloriesStr),comment, selectedCategory));
                    SQLController.addRecordToTableRecords(Integer.parseInt(countCaloriesStr), SQLController.getIDCategory(selectedCategory),comment);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case "list":
                System.out.println("Enter number of records");
                int records = sc.nextInt();
                RecordController.list(records);
                break;
            case "del":
                int recordToDelete;
                System.out.println("Enter the number of the record to delete");
                recordToDelete = sc.nextInt();
                System.out.println("Do you want to delete this line: " + RecordController.getRecordByNumber(recordToDelete) + " Enter command: YES or NO ");
                String answer = sc.next();
                if(answer.equals("YES")){
                    System.out.println("DELETE...");
                    FileFiller.rewriteFile(DataProcessor.deliteElementFromCollection(recordToDelete));
                }
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Enter command: add, list, del or exit");
                break;
        }

    }

}
