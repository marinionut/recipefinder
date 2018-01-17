package ro.ionutmarin.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import ro.ionutmarin.model.Recipe;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by ionut on 1/7/2018.
 */
public class DBService {
    public static Connection connectToDB(String host) throws SQLException, InterruptedException {
        Connection conn = null;
        //sudo docker exec -i -t 665b4a1e17b6 /bin/bash
        //psql -U postgres
        try {

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + host + "/postgres";

            while (conn == null) {
                try {
                    conn = DriverManager.getConnection(url, "postgres", "");
                } catch (SQLException e) {
                    System.err.println("Waiting for db");
                    Thread.sleep(1000);
                }
            }

            PreparedStatement st = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS recipe (ingredients VARCHAR(1000) NOT NULL UNIQUE, recipes TEXT NOT NULL)");
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println("Exception catch on database connection:" +  e.getCause());
            //System.exit(1);
        }

        System.err.println("Connected to db");
        return conn;
    }

    public static void insertRecipe(Connection dbConn, String ingredients, List<Recipe> recipes) throws SQLException {
        PreparedStatement insert = dbConn.prepareStatement(
                "INSERT INTO recipe (ingredients, recipes) VALUES (?, ?)");
        insert.setString(1, ingredients);
        Gson gson = new Gson();
        String jsonRecipes = gson.toJson(recipes);
        insert.setString(2, jsonRecipes);

        insert.executeUpdate();
    }

    public static ArrayList<Recipe> getRecipeByIngredients(Connection dbConn, String ingredients) throws SQLException {
        ArrayList<Recipe> recipes = new ArrayList<>();

        String query = "SELECT recipes FROM recipe WHERE ingredients LIKE ?";

        // create the preparedstatement and add the criteria
        PreparedStatement ps = dbConn.prepareStatement(query);
        ps.setString(1, "%" + ingredients + "%");

        // process the results
        ResultSet rs = ps.executeQuery();
        while ( rs.next() )
        {
            String recipesJson = rs.getString("recipes");
            // Converts JSON string into a collection of Student object.
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Recipe>>() {}.getType();
            if(recipesJson != null && recipesJson != "")
                recipes.addAll(gson.fromJson(recipesJson, type));
        }
        rs.close();
        ps.close();

        return recipes;
    }
}
