/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

public enum AccessPrivilege {
    
    ADMIN(3, "admin"),
    STUDENT(1, "student"),
    TEACHER(2, "teacher");

    private final int id;
    private final String name;
   // private final Pane layoutPane;

    private AccessPrivilege(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

 

    /*public static Pane getLayout(Privilege privilege) {

        AccessPrivilege[] accessPrivileges = AccessPrivilege.values();
        for (AccessPrivilege accessPrivilege : accessPrivileges) {
            if (accessPrivilege.id == privilege.getId()) {
                return accessPrivilege.layoutPane;
            }
        }
        return new UserView();
    }*/
}
