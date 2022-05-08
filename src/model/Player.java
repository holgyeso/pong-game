package model;

public class Player {

    private int xCoord;
    private int yCoord;
    private int width;
    private int height;
    private int score;
    private String name;

    public Player(int xCoord, int yCoord, int height, int width, String name) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.height = height;
        this.width = width;
        this.name = name;
        score = 0;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
