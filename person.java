/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DOAA
 */
public class person {
    int id;
    String FirstName;
    String MiddleName;
    String LastName;
    String email;
    String phone;

    public person() {
    }
     public person(int id,String first,String middle,String last,String email,String phone)
     {
         this.FirstName=first;
         this.LastName=last;
         this.MiddleName=middle;
         this.email=email;
         this.phone=phone;
     }
    
}
