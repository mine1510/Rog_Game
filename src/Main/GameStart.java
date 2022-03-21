package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GameStart extends JComponent implements Runnable, KeyListener { //Runnable нужен для многопоточности KeyListener для чтения нажатий
    public static int width = 1600;
    public static int height = 900;
    private boolean running; //переменная показывающая запущена ли игра

    Image player;


    public void start(){
        running = true;
        new Thread(this).start(); //создание нового потока
    }

    public void run(){ //функция run появляется при имплементировании Runnable
         long lastTime = System.currentTimeMillis();
         long delta;

         init();

         while (running){
             delta = System.currentTimeMillis() - lastTime; //разница между кадрами
             lastTime = System.currentTimeMillis(); //обновление времени
             update(delta);
             render(getGraphics());
         }
    }

    public void init(){
        try {
            player = ImageIO.read(new File("Assets/Image/player_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(long delta){

    }

    public void render (Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(player,30,30,null);
    }



    public static void main(String[] args) {
        GameStart game = new GameStart();

        JFrame frame = new JFrame("Rog_Game"); //создание окна с именем Rog_Game
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Остановка программы после закрытия окна
        frame.setSize(width,height);
        frame.setLayout(new BorderLayout()); //устанавливаем разметку окна
        frame.setLocation(150,50); //Запуск по центру экрана
        frame.addKeyListener(game);
        frame.add(new GameStart()); //добавляем холст на фрейм
        frame.add(game);
        frame.setResizable(false); //изменяемое окно (нет)
        frame.setVisible(true); //отобразить окно

        game.start(); //запустить игру
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}