package com.example.caloriecalc;

import java.io.IOException;
import java.sql.*;

public class SQLController {

    private static Connection connection;
    private static DatabaseMetaData databaseMetaData;


    /**
     * Метод проверки существования таблицы Записи
     *
     * @return true/false
     * @throws SQLException в случае дисконнекта
     */
    public static boolean checkDBTableRecords() throws SQLException {
        databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, "records", null);
        return resultSet.next();
    }

    /**
     * Метод проверки существования таблицы Категории
     *
     * @return true/false
     * @throws SQLException в случае дисконнекта
     */
    public static boolean checkDBTableCategories() throws SQLException {
        databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, "categories", null);
        return resultSet.next();
    }

    /**
     * Метод проверки таблицы Категории на пустоту
     */
    public static boolean checkDBTableCategoriesIsFull() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM categories");
        return resultSet.getInt(1) != 0;
    }

    /**
     * Метод создания таблицы Записи
     *
     * @throws SQLException в случае дисконнекта
     */
    public static void createTableRecord() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE records (\n" +
                "    id              INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    amount_calories INT,\n" +
                "    category        INT,\n" +
                "    comment         TEXT,\n" +
                "    date            TEXT\n" +
                ");");
    }

    /**
     * Метод создания таблицы Категории
     *
     * @throws SQLException в случае дисконнекта
     */
    public static void createTableCategory() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE categories (\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    title TEXT,\n" +
                "    ratio INT\n" +
                ");");
    }

    /**
     * Метод соединения с базой
     *
     * @throws SQLException в случае дисконнекта
     */
    public static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:main_db.db");
        Statement statement = connection.createStatement();
        System.out.println("Соединение установлено");
    }

    /**
     * Метод разъединения с базой
     *
     * @throws SQLException в случае дисконнекта
     */
    public static void disconnect() throws SQLException {
        Statement statement = connection.createStatement();
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
        System.out.println("Соединение прервано");
    }

    /**
     * Метод добавления записи в таблицу БД Записи
     *
     * @param countCalories - количество калорий, ввод пользователя
     * @param idCategory    - айди категории из таблицы Категории
     * @param comment       - текст к записи, ввод пользователя
     * @throws SQLException в случае дисконнекта
     */
    public static void addRecordToTableRecords(int countCalories, int idCategory, String comment) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO records (amount_calories,category,comment,created_at) VALUES (?,?,?,datetime('now'))");
        preparedStatement.setInt(1, countCalories);
        preparedStatement.setInt(2, idCategory);
        preparedStatement.setString(3, comment);
        preparedStatement.execute();

    }

    /**
     * Метод получения айди категории из таблицы БД Категории по названию категории
     *
     * @param s -  наименование категории
     * @return - айди из таблицы Категории
     * @throws SQLException в случае дисконнекта
     */
    public static int getIDCategory(String s) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM categories WHERE title=?");
        preparedStatement.setString(1, s);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.getInt(1);
    }

    /**
     * Метод заполнения таблицы Категории дефолтными значениями
     *
     * @throws SQLException в случае дисконнекта
     */
    public static void createEntryDefaultCategoriesDB() throws SQLException, IOException {

        Category.addDefaultCategoryToCollection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories (title,ratio) VALUES (?,?)");

        for (int i = 0; i < Category.defaultCategory.size(); i++) {
            preparedStatement.setString(1, Category.defaultCategory.get(i).getName());
            preparedStatement.setString(2, String.valueOf(Category.defaultCategory.get(i).getRating()));
            preparedStatement.addBatch();
        }
        int[] result = preparedStatement.executeBatch();

    }

    /**
     * Метод отправляющий в базу запрос на добавление новой категории
     *
     * @param title название категории
     * @param ratio коэффициент
     * @throws SQLException в случае дисконнекта
     */
    public static void addCategoriesDB(String title, int ratio) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories (title,ratio) VALUES (?,?)");
        preparedStatement.setString(1, title);
        preparedStatement.setInt(2, ratio);
        preparedStatement.execute();
    }

    /**
     * Метод отправляющий запрос В БД на удаление категории
     *
     * @param title
     * @throws SQLException в случае дисконнекта
     */
    public static void delCategoriesDB(String title) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categories WHERE title=?");
        preparedStatement.setString(1, title);
        preparedStatement.execute();
    }

    /**
     * Метод отпарвляющий запрос в БД для редактирование названия категории
     *
     * @param newTitle
     * @param oldTitle
     * @throws SQLException в стулае дисконнекта
     */
    public static void editNameCategoriesDB(String newTitle, String oldTitle) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categories SET title = ? WHERE title = ?");
        preparedStatement.setString(1, newTitle);
        preparedStatement.setString(2, oldTitle);
        preparedStatement.execute();

    }

    /**
     * Метод отпарвляющий запрос в БД для редактирование коэффициента категории
     *
     * @param title
     * @param ratio
     * @throws SQLException в случае дисконнекта
     */
    public static void editRatCategoriesDB(String title, int ratio) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categories SET ratio = ? WHERE title = ?");
        preparedStatement.setInt(1, ratio);
        preparedStatement.setString(2, title);
        preparedStatement.execute();
    }

    /**
     * Метод отправляющий запрос в БД на вывод записей по конкретной дате
     *
     * @param date требуемая дата
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet showRecordsFromDBbyDate(String date) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM records WHERE created_at LIKE ?");
        preparedStatement.setString(1, date);
        return preparedStatement.executeQuery();
    }


    /**
     * Метод вывода n количества записей из БД
     *
     * @param countRecords - n
     * @return ResultSet  записи из БД
     * @throws SQLException
     */
    public static ResultSet showRecordsFromDBbyNumberRecentEntries(int countRecords) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM records ORDER BY created_at DESC LIMIT ?");
        preparedStatement.setInt(1, countRecords);
        return preparedStatement.executeQuery();
    }

    /**
     * Метод редактирования количества калорий
     *
     * @param id       уникальный номер записи в БД
     * @param newCount
     * @throws SQLException
     */
    public static void editRecordCountCaloriesDB(int id, int newCount) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE records SET amount_calories = ? WHERE id = ?");
        preparedStatement.setInt(1, newCount);
        preparedStatement.setInt(2, id);
        preparedStatement.execute();
    }


    /**
     * Метод корректировки комментария к записи
     *
     * @param newComment новый вариант комментария
     * @param id         уникальный номер записи в БД
     * @throws SQLException
     */
    public static void editRecordCommentDB(String newComment, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE records SET comment = ? WHERE id = ?");
        preparedStatement.setString(1, newComment);
        preparedStatement.setInt(2, id);
        preparedStatement.execute();

    }

    /**
     * Метод удаляет конкретную запись из БД
     * @param idRecord  уникальный номер записи в БД
     * @throws SQLException
     */
    public static void deleteRecordFromDB(int idRecord) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM records WHERE id=?");
        preparedStatement.setInt(1, idRecord);
        preparedStatement.execute();
    }

}
