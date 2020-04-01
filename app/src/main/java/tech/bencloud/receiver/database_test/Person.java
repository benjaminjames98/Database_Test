package tech.bencloud.receiver.database_test;

public class Person {

    private long id;
    private String name;
    private int age;
    private String address;

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String name) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Name: " + name + " (" + age + ") has id value: " + id + " and lives at " + address;
    }
}