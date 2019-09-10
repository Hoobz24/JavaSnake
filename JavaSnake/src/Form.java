import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Author: Andrew Tucker
//Date Created: 9/7/2019
//Version 0.0.0 on 9/9/2019

public class Form extends JPanel implements KeyListener{


    //Variables

    private JPanel panel1;

    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    ArrayList<Integer> direction = new ArrayList<>();
    ArrayList<Integer> bx = new ArrayList<>();
    ArrayList<Integer> by = new ArrayList<>();

    int pd;
    int length = 3;
    int score = 0;
    char key;
    boolean pressed;
    double fps;

    //Form method

    Form(){

        //Sets up JFrame
        addKeyListener(this);

        JFrame frame = new JFrame("App");

        this.setSize(500, 500);
        setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.add(this);
        frame.setTitle("Snake");
        this.set();

        //Game Loop
        while(true) {
            try{

                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();

            }

            //Updates logic and repaints screen
            update();
            repaint();


        }

    }


    @Override
    public void keyPressed(KeyEvent e) {

        //gets current key pressed
        key = e.getKeyChar();
        pressed = true;
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void paint(Graphics g){

        //setups graphic variables
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //paints background and score
        g2d.fillRect(0, 0, 500, 500);
        g2d.setColor(Color.white);
        g2d.drawString("Score: " + score, 10, 20);

        //checks each sector and paints if part of snake
        for(int ix = 0; ix < 50; ix++){

            for (int iy = 0; iy < 50; iy++){

                for(int i3 = 0; i3 < length; i3++){
                    if(x.get(i3) == ix && y.get(i3) == iy){
                        g2d.setColor(Color.green);
                        g2d.fillRect(x.get(i3)*10, y.get(i3)*10 , 10, 10);



                    } else {
                    }

                }


            }
        }


    }

    public void set(){
        //set starting snake parts
        x.add(8);
        y.add(7);
        direction.add(2);
        pd = direction.get(0);

        x.add(7);
        y.add(7);
        direction.add(1);

        x.add(6);
        y.add(7);
        direction.add(1);

        x.add(5);
        y.add(7);
        direction.add(1);

        x.add(4);
        y.add(7);
        direction.add(1);

        x.add(3);
        y.add(7);
        direction.add(1);

        x.add(2);
        y.add(7);
        direction.add(1);

        x.add(1);
        y.add(7);
        direction.add(1);

        length = 8;
    }



    public void update() {

        //movement
        if(pressed == true){
            switch(key){
                case 'a': direction.set(0, 3);
                    break;
                case 's': direction.set(0, 2);
                    break;
                case 'w': direction.set(0, 4);
                    break;
                case 'd': direction.set(0, 1);

            }

        }
        for (int i4 = 0; i4 < length; i4++) {



            int cx = x.get(i4);
            int cy = y.get(i4);
            switch (direction.get(i4)) {
                case 1:
                    cx += 1;
                    x.set(i4, cx);

                    break;
                case 2:
                    cy += 1;
                    y.set(i4, cy);

                    break;
                case 3:
                    cx -= 1;
                    x.set(i4, cx);

                    break;
                case 4:
                    cy -= 1;
                    y.set(i4, cy);

                    break;

            }




        }

        //shifts directional array
        for(int i5 = direction.size() - 1; i5 >= 1; i5--){
            direction.set(i5, direction.get(i5 - 1));

        }


    }

    //main method,  creates new form
    public static void main(String args[]) {
        new Form();

    }
}
