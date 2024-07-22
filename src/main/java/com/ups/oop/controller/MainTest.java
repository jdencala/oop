package com.ups.oop.controller;

public class MainTest {
    public static void main(String[] args) {
        String fullName = "Jorge Encalada";
        //[0      , 1         ]
        //["Jorge", "Encalada"]
        String[] nameStrings = fullName.split(" ");
        String name = nameStrings[0];
        String lastname = nameStrings[1];
        System.out.println("FullName: " + fullName);
        System.out.println("Name: " + name);
        System.out.println("Lastname: " + lastname);
    }
}
