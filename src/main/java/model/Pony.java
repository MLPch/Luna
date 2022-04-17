package model;

import java.util.Objects;

public class Pony {

    private int id;
    private String name;
    private int age;

    public Pony() {
    }


    public int getId() {
        return id;
    }

    public Pony setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pony setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Pony setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pony pony = (Pony) o;
        return id == pony.id && age == pony.age && name.equals(pony.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return "Pony{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


}

