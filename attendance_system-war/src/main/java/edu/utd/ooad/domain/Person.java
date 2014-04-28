package edu.utd.ooad.domain;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class Person {
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!name.equals(person.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
