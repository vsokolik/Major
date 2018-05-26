package ru.testapp.major;

public class BoxObject {

    private int id;
    private String title;
    private String img;

    public BoxObject(int id, String title, String img) {
        this.id = id;
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }
}
