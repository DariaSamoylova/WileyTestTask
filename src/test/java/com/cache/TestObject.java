package com.cache;

import java.util.Objects;

public class TestObject {
    private String name;
    private Integer num;

    public TestObject(String name, Integer num) {
        this.name = name;
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestObject)) return false;
        TestObject that = (TestObject) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getNum(), that.getNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNum());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
