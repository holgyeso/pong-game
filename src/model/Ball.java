package model;

public class Ball {

    private double xCoord;
    private double yCoord;
    private double size;

    public Ball(double xCoord, double yCoord, double size) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.size = size;
    }

    public double getxCoord() {
        return xCoord;
    }

    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }

    public double getSize() {
        return size;
    }
}
