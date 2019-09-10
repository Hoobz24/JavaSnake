import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;



// Author: Andrew Tucker
// Date Created: 9/7/2019
// Last Modified: 9/9/2019


public class Form extends JPanel implements KeyListener{


    //Variables

    private JPanel panel1;

    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    ArrayList<Integer> direction = new ArrayList<>();
    ArrayList<Integer> bx = new ArrayList<>();
    ArrayList<Integer> by = new ArrayList<>();

    int pd;
    int length = 1;
    int blength = 1;
    int score = 0;
    char key;
    boolean pressed;
    boolean alive = true;
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

        if(alive == false){
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2d.setColor(Color.red);
            g2d.drawString("GAME OVER", 5, 20);
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2d.setColor(Color.white);
            g2d.drawString("Score: " + score, 5, 40);

        } else{
            g2d.setColor(Color.white);
            g2d.drawString("Score: " + score, 10, 20);

        }

        //checks each sector and paints if part of snake
        for(int ix = 0; ix < 50; ix++){
            for (int iy = 0; iy < 50; iy++){
                for(int i3 = 0; i3 < length; i3++){
                    if(x.get(i3) == ix && y.get(i3) == iy){
                        g2d.setColor(Color.green);
                        g2d.fillRect(x.get(i3) * 10, y.get(i3) * 10 , 10, 10);
                    }

                    if(blength > 0) {
                        for (int i6 = 0; i6 < blength; i6++) {
                            if (x.get(i6) == ix && y.get(i6) == iy) {
                                g2d.setColor(Color.red);
                                g2d.fillRect(bx.get(i6) * 10, by.get(i6) * 10, 10, 10);
                            }
                        }
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

        bx.add(9);
        by.add(7);

       length = 1;
    }

    public void update() {

        if(alive == true) {
            //movement
            if (pressed == true) {
                switch (key) {
                    case 'a':
                        direction.set(0, 3);
                        break;
                    case 's':
                        direction.set(0, 2);
                        break;
                    case 'w':
                        direction.set(0, 4);
                        break;
                    case 'd':
                        direction.set(0, 1);
                        break;
                    case 'p':
                       alive = false;
                        break;
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
            for (int i5 = direction.size() - 1; i5 >= 1; i5--) {
                direction.set(i5, direction.get(i5 - 1));

            }

            if(x.get(0) > 50 || x.get(0) < 0 || y.get(0) > 50 || y.get(0) < 0){
                alive = false;

            }

            for (int i7 = 0; i7 < length; i7++) {

                for(int i8 = 0; i8 < length; i8++){
                   if(x.get(i7) == x.get(i8) && y.get(i7) == y.get(i8) && i7 != i8){
                       alive = false;

                   }
                }

                if (blength > 0) {
                    if (x.get(i7) == bx.get(0) && y.get(i7) == by.get(0)) {

                        System.out.println("bruh");
                        if (direction.get(direction.size() - 1) == 1) {
                            x.add(x.get(x.size() - 1) - 1);
                            y.add(y.get(y.size() - 1));
                            direction.add(1);
                            length += 1;
                            score += 1;

                        }

                        if (direction.get(direction.size() - 1) == 2) {
                            x.add(x.get(x.size() - 1));
                            y.add(y.get(y.size() - 1) - 1);
                            direction.add(2);
                            length += 1;
                            score += 1;

                        }
                        if (direction.get(direction.size() - 1) == 3) {
                            x.add(x.get(x.size() - 1) + 1);
                            y.add(y.get(y.size() - 1));
                            direction.add(3);
                            length += 1;
                            score += 1;

                        }
                        if (direction.get(direction.size() - 1) == 4) {
                            x.add(x.get(x.size() - 1));
                            y.add(y.get(y.size() - 1) +
                                    1);
                            direction.add(4);
                            length += 1;
                            score += 1;

                        }


                        bx.clear();
                        by.clear();
                        bx.add((int) Math.round(Math.random() * 50));
                        by.add((int) Math.round(Math.random() * 50));

                    }
                }
            }

        } else {
        }
    }

    //main method,  creates new form
    public static void main(String args[]) {
        new Form();
    }
}

