package com.example.project_client;

public class Accounting {
    private Long id;

    private String name;

    private Integer count;

    private Integer defective ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDefective() {
        return defective;
    }

    public void setDefective(Integer defective) {
        this.defective = defective;
    }
}
