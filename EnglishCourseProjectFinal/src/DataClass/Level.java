package DataClass;

import java.util.ArrayList;

public class Level {
    private int levelId;
    private String levelDescription;
    private String imagePath;
    private String levelColor;
    private ArrayList<Lesson> lessons; 

    public Level(int levelId, String levelDescription, String imagePath, String levelColor) {
        this.levelId = levelId;
        this.levelDescription = levelDescription;
        this.imagePath = imagePath;
        this.levelColor = levelColor;
        this.lessons = new ArrayList<>(); 
    }

    public int getLevelId() {
        return levelId;
    }

    public String getLevelDescription() {
        return levelDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public String getLevelColor(){
        return levelColor;
    }
}
