import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Game extends JPanel implements KeyListener, ActionListener {
    Timer timer = new Timer(5,this);
    private int Time=0;
    private int FireCounter=0;
    private BufferedImage image;
    private ArrayList<Fire> Fires = new ArrayList<Fire>();
    private int fireMoveY = 10;
    private int aimX=0;
    private int aimMoveX=10;
    private int spaceShipX=0;
    private int spaceShipMoveX=20;

    public boolean control(){
        for(Fire fire : Fires){
            if(new Rectangle(fire.getX(),fire.getY(),10,20).intersects(new Rectangle(aimX,0,20,20))){
                return true;

            }

        }
        return false;
    }


    public Game(){
        try {
            image = ImageIO.read(new FileImageInputStream(new File("spaceship.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setBackground(Color.BLACK);
        timer.start();

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Time += 5;

        g.setColor(Color.red);
        g.fillOval(aimX,0,20,20);
        g.drawImage(image,spaceShipX,470,image.getWidth()/5,image.getHeight()/5,this);
        for(Fire fire: Fires){
            if(fire.getY()<0){
                Fires.remove(fire);
            }
        }
        g.setColor(Color.BLUE);
        for(Fire fire : Fires){
            g.fillRect(fire.getX(),fire.getY(),10,20);
        }
        if(control()){
            timer.stop();
            String message = ("You won the game!\nYour time: "+Time/300+"seconds.");
            JOptionPane.showMessageDialog(this,message);
            System.exit(0);
        }

    }

    @Override
    public void repaint() {
        super.repaint();
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        for(Fire fire: Fires){
            fire.setY(fire.getY()-fireMoveY);
        }


        aimX += aimMoveX;
        if(aimX >= 750){
            aimMoveX = -aimMoveX;
        }
        if(aimX <= 0){
            aimMoveX = -aimMoveX;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT){
            if(spaceShipX <= 0){
                spaceShipX=0;
            }else{
                spaceShipX -= spaceShipMoveX;
            }

        }else if (c == KeyEvent.VK_RIGHT){
            if(spaceShipX >= 700){
                spaceShipX = 700;
            }else{
                spaceShipX += spaceShipMoveX;
            }

        }else if(c == KeyEvent.VK_CONTROL){
            Fires.add(new Fire(spaceShipX+36,470));
            FireCounter++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
