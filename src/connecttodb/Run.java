/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connecttodb;

/**
 *
 * @author Dmitry
 */
public class Run {
    public static void main(String[] args) {
        String name = "getFromDB";
        String varFind = "ID";
        String [] strs = {"var1", "var_2", "var3"};
        Strings s = new Strings();
        System.out.println(s.getSubName(name, varFind) + s.getInitVars(strs) + s.getConnectionString() + s.getConnection("R190409", strs, "IDs", varFind) + s.getSubEnd());
    }
}
