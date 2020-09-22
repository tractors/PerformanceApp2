package com.will.performanceapp2.ui;

public class Droid {
    /**
     * The Droid's name.
     */
    private String name;

    /**
     * The color associated with a droid.
     */
    private int color;

    /**
     *  The id of a drawable associated with a Droid.
     */
    private int imageId;


    public Droid(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public Droid(String name, int color, int imageId) {
        this.name = name;
        this.color = color;
        this.imageId = imageId;
    }

    public String getName() {return name;}
    public int getColor() {return color;}
    public int getAvatarId() {return imageId;}
} 