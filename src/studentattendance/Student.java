/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance;

public class Student {
    private String name, course, year, address;

    public Student(String name, String course, String year, String address) {
        this.name = name;
        this.course = course;
        this.year = year;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }
    
}
