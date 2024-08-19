package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
   private Connection connection = null;

   private Properties loadProperties() {
      try (FileInputStream fs = new FileInputStream("db.properties")) {
         Properties props = new Properties();
         props.load(fs);
         return props;
      } catch (IOException e) {
         throw new DbException(e.getMessage());
      }
   }

   public Connection getConnection() {
      if(this.connection == null) {
         try {
            Properties props = loadProperties();
            String url = props.getProperty("dburl");
            this.connection = DriverManager.getConnection(url, props);
         } catch (SQLException e) {
            throw new DbException(e.getMessage());
         }
      }

      return this.connection;
   }

   public void closeConnection() {
      if(this.connection != null) {
         try {
            this.connection.close();
         } catch (SQLException e) {
            throw new DbException(e.getMessage());
         }
      }
   }
}
