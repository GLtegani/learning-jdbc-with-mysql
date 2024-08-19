package application;

import db.DB;

import java.sql.Connection;

public class Program {
   public static void main(String[] args) {
      DB db = new DB();
      Connection connection = db.getConnection();
      db.closeConnection();
   }
}
