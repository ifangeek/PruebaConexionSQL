package com.ifangeek.pruebaconexionsql;

import android.app.Application;
import android.database.SQLException;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by DIEGO on 9/07/2017.
 */

public class db extends Application{


        String ConnectionURL ="";


    public static class ConexionSQL {


        public static Connection ConnectionHelper()
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection connection = null;
            String ConnectionURL = null;

            try{
                Log.d("SQLSERVER","Intentando Conexion MSSQLSERVER...");
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                ConnectionURL = "jdbc:jtds:sqlserver://192.168.8.108;port=1433;databaseName=PROYECTODAM;user=sa;password=sql;";
                connection = DriverManager.getConnection(ConnectionURL);
                Log.d("SQLSERVER","Conexion MSSQLSERVER OK");
                Log.d("SQLSERVER",""+connection);
            }catch (ClassNotFoundException e) {
                Log.d("ERROR","Class not Found: " + e.getMessage());
            }catch (java.sql.SQLException e) {
                Log.d("ERROR","Sql: " + e.getMessage());
            }catch (Exception e){
                Log.e("ERROR",e.getMessage());
            }
            return connection;
        }

    }

    public boolean ObtenerDatos() {
        String datosConsultados = "";
        try {
            Connection connect = ConexionSQL.ConnectionHelper();
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("select idactivo,nomactivo,nomtipo,marca,modelo from ACTIVO");
            while (rs.next()) {
                datosConsultados = rs.getString("idactivo");
                datosConsultados = rs.getString("nomactivo");
                datosConsultados = rs.getString("marca");
                datosConsultados = rs.getString("modelo");
            }
            connect.close();
            Toast.makeText(getApplicationContext(),
                    datosConsultados, Toast.LENGTH_SHORT).show();
            return true;

        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(),
                    e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            return false;

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
