package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameStart extends Canvas implements Runnable { //Runnable нужен для многопоточности, поток идет в run
    public static int width = 1600;
    public static int height = 900;
    private boolean running; //переменная показывающая запущена ли игра

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
             render();
         }
    }

    public void init(){

    }

    public void update(long delta){

    }

    public void render(){
        BufferStrategy bs = getBufferStrategy(); //переменная типа BufferStrategy для реализации буфера
        if (bs == null){
            createBufferStrategy(2); //создание двойной буферизации
            requestFocus();
            return;
        }

        Graphics gr = bs.getDrawGraphics(); //переменная графического типа
        gr.setColor(Color.black); //устанавливаем цвет
        gr.fillRect(0,0,getWidth(),getHeight()); //заполняем прямоугольник цветом
        gr.dispose(); //обновляем позицию
        bs.show();
    }

    public static void main(String[] args) {
        GameStart game = new GameStart();
        game.setPreferredSize(new Dimension(width,height));

        JFrame frame = new JFrame("Rog_Game"); //создание окна с именем Rog_Game
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Остановка программы после закрытия окна
        frame.setLayout(new BorderLayout()); //устанавливаем разметку окна
        frame.add(game, BorderLayout.CENTER); //добавляем холст на фрейм
        frame.pack();
        frame.setResizable(false); //изменяемое окно (нет)
        frame.setVisible(true); //отобразить окно

        game.start(); //запустить игру
    }
}