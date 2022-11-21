import java.sql.*;

public class Main {
    final static String all_table_schemas =
            "create table department (dept_name varchar(20), building varchar(15),budget numeric(12,2), primary key (dept_name));" +
            "create table instructor (ID varchar(5),name varchar(20),dept_name varchar(20),salary numeric(8,2), primary key (ID), foreign key (dept_name) references department(dept_name));" +
            "create table student (ID varchar(5), name varchar(20) not null, dept_name varchar(20), tot_cred numeric(3,0), primary key (ID), foreign key (dept_name) references department(dept_name));";

    public static void main(String[] args) {
        // 1. Create connection object
        Connection conn = connect();
        // 2. Create statement to execute query
        try{
            Statement stm = conn.createStatement();

//            stm.execute(all_table_schemas);
            stm.execute("insert into department values('Economics','F','13000.00')");

            ResultSet rst = stm.executeQuery("select * from department;");

            System.out.println("-- Departmant Table -- ");

            while (rst.next()){
                String dept_name = rst.getString("dept_name");
                String building = rst.getString("building");
                String budget = rst.getString("budget");

                System.out.println(dept_name + " - " + building +" - "+ budget);

                System.out.println();
            }
            System.out.println("-- --- -- -- -- ");

//            System.out.println("Query Executed!");
        }catch (SQLException e){
            System.out.println("Error Accured :: " + e);
        }
    }
    public static Connection connect() {
        Connection conn = null;

        String db = "myuniv";
        String url = "jdbc:mysql://localhost:3306/"+db+"?allowMultiQueries=true";
        String user = "root", pass ="Ani658445!";
        try {
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Connection to MySQL has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
