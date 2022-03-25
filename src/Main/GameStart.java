package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class GameStart extends JComponent implements Runnable, KeyListener { //Runnable нужен для многопоточности KeyListener для чтения нажатий
    public static int width = 1600, height = 900, px=740,py=340, arrowNumber = 0;
    public static int[] arrowX = new int[10], arrowY = new int[10];
    private boolean running; //переменная показывающая запущена ли игру
    Image player,paper,enemybBall,enemyRectangle,arrowUp,arrowLeft,arrowDown,arrowRight;
    public static boolean upd = false;

    public void start(){
        running = true;
        new Thread(this).start(); //создание нового потока
    }

    public void run(){ //функция run появляется при имплементировании Runnable
         init();
        renderStatic(getGraphics());
    }

    private void renderStatic(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(paper,0,0,null);
        g2.drawImage(player,px,py,null);
    }

    public void init(){
        try {
            player = ImageIO.read(new File("Assets/Image/player_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            paper = ImageIO.read(new File("Assets/Image/paper.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            arrowDown = ImageIO.read(new File("Assets/Image/ArrowDown.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
        arrowLeft = ImageIO.read(new File("Assets/Image/ArrowLeft.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        upd = false;
        for (int i=0; i<10; i++){
            arrowX[i]++;
            arrowY[i]++;
            renderArrow(getGraphics(), i);
        }
    }

    public void renderArrow (Graphics g, int arrowNumber){
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(arrowDown,arrowX[arrowNumber],arrowY[arrowNumber],null);
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
        if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            px+= 1;
            renderStatic(getGraphics());
            upd = true;
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT){
            px-= 1;
            renderStatic(getGraphics());
            upd = true;
        }
        if (e.getKeyCode()==KeyEvent.VK_UP){
            py-= 1;
            renderStatic(getGraphics());
            upd = true;
        }
        if (e.getKeyCode()==KeyEvent.VK_DOWN){
            py+= 1;
            renderStatic(getGraphics());
            upd = true;
        }

        if (e.getKeyCode()==KeyEvent.VK_D){
            px+= 1;
            renderStatic(getGraphics());
            upd = true;
        }
        if (e.getKeyCode()==KeyEvent.VK_A){
            px-= 1;
            renderStatic(getGraphics());
            upd = true;
        }
        if (e.getKeyCode()==KeyEvent.VK_W){
            py-= 1;
            renderStatic(getGraphics());
            upd = true;
        }
        if (e.getKeyCode()==KeyEvent.VK_S){
            py+= 1;
            renderStatic(getGraphics());
            upd = true;
        }
        if (e.getKeyCode()==KeyEvent.VK_SPACE){
            renderArrow(getGraphics(), arrowNumber);
            if(arrowNumber<10){
            arrowNumber++;
            } else {
                arrowNumber = 0;
            }
            arrowX[arrowNumber]= px;
            arrowY[arrowNumber]= py;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}