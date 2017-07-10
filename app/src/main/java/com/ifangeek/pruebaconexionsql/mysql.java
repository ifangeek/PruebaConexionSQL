package com.ifangeek.pruebaconexionsql;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DIEGO on 26/06/2017.
 */

public class mysql {

    String TAG = "MYSQL";

    protected static Connection connection = null;
    protected static Statement statement = null;
    protected static ResultSet resultSet = null;

    private String user = "";
    private String pass = "";
    private String port = "";
    private String database = "";
    private String ip = "";
    private String host = "";
    private String url = "";
    private static final String url1 = "jdbc:mysql://{URL_SERVER}/{BASE_DATOS}";


    public mysql(){
        //Iniciar Parametros


        user = "root";
        pass = "mysql";
        port = "3306";
        database = "tester";
        ip = "";
        host = "192.168.8.108";
        url = "jdbc:mysql://"+host+"/"+database;

    }

    public Connection conectar(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            Log.v(TAG,"Intentando Conexion MySQL...");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url);
            Log.v(TAG,"Conexion MySQL OK " + connection);
        }catch (ClassNotFoundException e) {
            Log.e(TAG,"ClassNotFoundException: " + e.getMessage());
        }catch (java.sql.SQLException e) {
            Log.e(TAG,"SQLException: " + e.getMessage());
        }catch (Exception e){
            Log.e(TAG,"Exception: " + e.getMessage());
        }
        return connection;
    }

    public List<String> Consulta(){

        List<String> stringList = new ArrayList<>();

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM activo");



            while(resultSet.next()){
                Log.v(TAG,"Datos -> " + resultSet.getString("id") + " - " + resultSet.getString("nombre"));
                stringList.add(0,resultSet.getString("id"));
                stringList.add(1,resultSet.getString("nombre"));
            }

            return stringList;

        }catch(SQLException e){
            Log.e(TAG,"Conexion MySQL OK " + connection);
        }catch(Exception e){
            Log.e(TAG,"Exception: " + e.getMessage());
        }

        return stringList;

    }

}
