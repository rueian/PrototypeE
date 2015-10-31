package com.ruian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by rueian on 2015/10/31.
 */
public class BufferedPanel extends JPanel implements KeyListener {
    private Graphics offGraphics;
    private Image offScreen;
    private BufferedImage[] imgs;
    private BasicBlock[][] scene;

    private int x = 2500;
    private int y = 1000;

    public BufferedPanel(BufferedImage[] imgs, BasicBlock[][] scene) {
        super();

        this.imgs = imgs;
        this.scene = scene;

        addKeyListener(this);
        setFocusable(true);
        requestFocus();
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.repaint();
    }

    public void paintComponent(Graphics g){
        offScreen = createImage(800, 600);
        offGraphics = offScreen.getGraphics();

        int i = x / 100;
        int j = y / 100;

        int minI = i - 4;
        int minJ = j - 3;
        int maxI = i + 4;
        int maxJ = j + 3;

        for (j = minJ; j < maxJ; j ++) {
            for(i = minI; i < maxI; i ++) {
                if (i >= 0 && i < 50 && j >= 0 && j < 20)
                    offGraphics.drawImage(imgs[scene[i][j].type], (i - minI) * 100, (j - minJ) * 100, null);
            }
        }

        g.drawImage(offScreen, -(x - (x/100)*100) - 150, -(y - (y/100)*100) - 150, this);
    }

    public void update(Graphics g) {
        paintComponent(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT)
            updatePosition(x - 25, y);
        else if (keyCode == KeyEvent.VK_RIGHT)
            updatePosition(x + 25, y);
        else if (keyCode == KeyEvent.VK_UP)
            updatePosition(x, y - 25);
        else if (keyCode == KeyEvent.VK_DOWN)
            updatePosition(x, y + 25);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
