package com.example.caloriecalc;

public class Validator {

    /**
     * Метод, конвертирующий строку в int
     * @param s ответ пользователя
     * @return true/false
     */
    public static boolean isConvertStringToInt(String s){
        try{
            int i = Integer.parseInt(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Метод запрашивающий корректный ввод данных для количественной переменной
     * @param s ответ пользователя
     */

    public static void promptCorrectEnterForCountCalories(String s){
        if(!isConvertStringToInt(s)){
            System.out.println("Enter amount of calories: use only numbers to enter");
        }
    }

    /**
     * Метод запрашивающий корректный ввод данных для наименования категории
     * @param s
     */
    public static void promptCorrectEnterNameCategory(String s){
        if(!isValidNameCategory(s)){
            System.out.println("There is no such category. Check the spelling of the category name.");
        }
    }

    /**
     * Метод проверки правильности введенного названия категории
     * @param s ответ пользователя
     * @return true/false
     */
    public static boolean isValidNameCategory(String s) {
        String[] arrCategory = DataProcessor.extractArrNameCategory(DataProcessor.createCollectionOfRecords(FileChecker.getFILECATEGORY()));
        for (String value : arrCategory) {
            if (value.equals(s)) {
                return true;
            }
        }
        return false;
    }

}

