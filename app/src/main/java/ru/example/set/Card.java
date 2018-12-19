package ru.example.set;

import android.widget.ImageView;

//Сериализация класса карточек
public class Card{

    //Определяем переменные
    private ImageView iView;
    private String Texture;
    private String Color;
    private String Shape;
    private String Count;
    private boolean Valid;
    private int DrawableID;
    private boolean Selected;

    //Делаем переменные общедоступными в проекте
    public void setValid(boolean valid){
        Valid = valid;
    }

    public boolean getValid(){
        return Valid;
    }

    public void setiView(ImageView iview){
        iView = iview;
    }

    public String getTexture() {
        return Texture;
    }

    public void setTexture(String texture){
        Texture = texture;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color){
        Color = color;
    }

    public String getShape() {
        return Shape;
    }

    public void setShape(String shape){
        Shape = shape;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count){
        Count = count;
    }

    public int getDrawableID() {
        return DrawableID;
    }

    public void setDrawableID(int drawableID){
        DrawableID = drawableID;
    }

    public boolean getSelected() {
        return Selected;
    }

    public void setSelected(boolean selected) {
        Selected = selected;
    }

    //Общая структура переменных для вызова
    public Card(String texture, String color, String shape, String count, int drawableID, Boolean selected) {

        iView = null;
        Valid = true;
        Texture = texture;
        Color = color;
        Shape = shape;
        Count = count;
        DrawableID = drawableID;
        Selected = selected;
    }
}
