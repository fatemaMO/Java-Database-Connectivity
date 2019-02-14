
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fatema
 */

public class Database {
    
     private static String dbURL = "jdbc:derby://localhost:1527/ITI";
    private static String tableName = "person";
    private static int id=0;
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
     Vector <person> allPersons = new Vector<person>();
    public static void connectDb()
    {
         try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL,"root","root"); 
            System.out.println("true");
        }
        catch (Exception except)
        {
             System.out.println("false");
            except.printStackTrace();
        }
    }
    static person  getone() throws SQLException
    {
         person p1=new person();
         try {
             stmt=conn.createStatement();
               String guery=new String("select * from PERSON");
       ResultSet result=stmt.executeQuery(guery);
       while(result.next())

                 try {
                     p1.id= (int) result.getInt(1);
                     p1.FirstName=(String) result.getString(2);
                     p1.MiddleName=(String) result.getString(3);
                     p1.LastName=(String) result.getString(4);
                     p1.email=(String) result.getString(5);
                     p1.phone=(String) result.getString(6);
                    
                 } catch (SQLException ex) {
                     Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                 }
           

}

           
          catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
     
return p1;
    }
  
       static person updatePerson(person p2) throws SQLException
    {
         person personUpdated=new person();
         PreparedStatement stmt;
         try {
             System.out.println(p2.FirstName);
             stmt=conn.prepareStatement("UPDATE PERSON set FIRSTNAME=?,LASTNAME=?,MIDDLENAME=?,EMAIL=?,PHONE=? WHERE ID=?");
              stmt.setString(1, p2.FirstName);
              stmt.setString(2, p2.LastName);
              stmt.setString(3, p2.MiddleName);
              stmt.setString(4, p2.email);
              stmt.setString(5, p2.phone);
              stmt.setInt(6, p2.id);
              
       int result=stmt.executeUpdate();
             //System.out.println(result);
             if(result==1)
             {
                 personUpdated=p2;
             }
             else
             {
                 personUpdated=new person();
                personUpdated.id=-1;
             }
             


           }

           
          catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            
         }
     
return personUpdated;
    }
       
    static person next(int id)
    {
           person p2=new person();
           int row=-0;
           
         try {
             stmt=conn.createStatement();
               String guery=new String("select * from PERSON");
              ResultSet result=stmt.executeQuery(guery);
              while(result.next())
              {
                  if(result.getInt(1)==id)
                  {
                       row=result.getRow();
                  result.next();
                  //System.out.println(result.getInt(1));
                      p2.id=result.getInt(1);
                      p2.FirstName=result.getString(2);
                      p2.FirstName=(String) result.getString(2);
                      p2.MiddleName=(String) result.getString(3);
                      p2.LastName=(String) result.getString(4);
                      p2.email=(String) result.getString(5);
                      p2.phone=(String) result.getString(6);
                    break;
                  }
                
                     
              }
              // System.out.println(row);
        
      

}

           
          catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
         return p2;
    }
    
     public person getFirst() throws SQLException{
    String st = "select * from person limit 1";
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(st);  
    rs.next();
    
    person p = new person();
    p.id=rs.getInt(1);
    p.FirstName=rs.getString(2);
    p.MiddleName=rs.getString(3);
    p.LastName=rs.getString(4);
    p.email=rs.getString(5);
    p.phone=rs.getString(6);
    return p;
   }
     public Vector getAll() throws SQLException{
    String st = "select * from person ";
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(st);  
    while (rs.next())
    {
        person p = new person();
        p.id=rs.getInt(1);
        p.FirstName=rs.getString(2);
        p.LastName=rs.getString(3);
        p.email=rs.getString(4);
        p.phone=rs.getString(5);
        allPersons.add(p);
        System.out.println(rs.getInt(1));
    }
    return allPersons;
   }
     public void deletePerson(int deletedId) throws SQLException{
    String st = "delete from person where id = ?";
    PreparedStatement pst = conn.prepareStatement(st);
    pst.setInt(1,deletedId);
   
    int rs = pst.executeUpdate();
    System.out.println("Num of changed rows"+ rs);
    }
      public void addPerson(person p2) throws SQLException{
    person p = new person();
  
    p=p2;
    
    p.id=++id;
    String st = "insert into person(firstName,lastName,email,phone,id) values(?,?,?,?,?)";
    PreparedStatement pst = conn.prepareStatement(st);
    pst.setString(1,p.FirstName);
    pst.setString(2,p.LastName);
    pst.setString(3,p.email);
    pst.setString(4,p.phone);
     pst.setInt(5,p.id);
   
    int rs = pst.executeUpdate();
    System.out.println("Num of changed rows"+ rs); 
   }
       public person getLast() throws SQLException{
    String st = "select * from person order by id desc limit 1";
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(st);  
    rs.next();
    person p = new person();
    p.id=rs.getInt(1);
    p.FirstName=rs.getString(2);
    p.LastName=rs.getString(3);
    p.email=rs.getString(4);
    p.phone=rs.getString(5);
    return p;
   }
    public static void main(String[] args) {
        person p1=new person(1, "fatema", "mohamed", "mahgfdg", "fatemamo7amed@gmail.com", "01066146567");
     connectDb();
         try {
             getone();
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
             updatePerson(p1);
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
         next(2);
                 {
                     
                 }
     
    }
    
}
