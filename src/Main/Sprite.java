package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private Image image; //изображение
    public Sprite(Image image){
        this.image = image;
    }

    public int getWidth(){ //получаем ширину картинки
        return image.getWidth(null);
    }

    public int getHeight(){ //получаем высоту картинки
        return image.getHeight(null);
    }

    public void draw(Graphics g,int x,int y){ // рисуем картинку
        g.drawImage(image,x,y,null);
    }


}
