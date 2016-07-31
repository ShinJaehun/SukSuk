package com.shinjaehun.suksuk;

/**
 * Created by shinjaehun on 2016-07-30.
 */
public class Achievement {
    private long id; //id
    private String name; //도전과제 이름
    private String type; //유형 mul22, common
    private int isUnlock; //활성화되었는지 여부 확인
    private String aka; //별칭(이미지 불러올 때 사용)
    private String description; //해당 과제에 대한 설명
    private int number; //횟수
    private String value; //값(시간 기록 등을 넣을 때 사용)

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIsUnlock() {
        return isUnlock;
    }

    public void setIsUnlock(int isUnlock) {
        this.isUnlock = isUnlock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
