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
}
