package com.ruian;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final String IMG_0_PATH = "imgs/0.jpg";
    private static final String IMG_1_PATH = "imgs/1.jpg";
    private static final String IMG_2_PATH = "imgs/2.jpg";
    private static final String IMG_3_PATH = "imgs/3.jpg";
    private static final String IMG_4_PATH = "imgs/4.jpg";
    private static final String IMG_5_PATH = "imgs/5.jpg";

    private static final int SCENE_HEIGHT = 20;
    private static final int SCENE_WIDTH = 50;
    private static final int SCREEN_HEIGHT = 300;
    private static final int SCREEN_WIDTH = 500;

    private static BufferedImage[] imgs = {null, null, null, null, null, null};

    private static BasicBlock[][] scene = new BasicBlock[SCENE_WIDTH][SCENE_HEIGHT];

    public static void main(String[] args) {
        try {
            imgs[0] = ImageIO.read(new File(IMG_0_PATH));
            imgs[1] = ImageIO.read(new File(IMG_1_PATH));
            imgs[2] = ImageIO.read(new File(IMG_2_PATH));
            imgs[3] = ImageIO.read(new File(IMG_3_PATH));
            imgs[4] = ImageIO.read(new File(IMG_4_PATH));
            imgs[5] = ImageIO.read(new File(IMG_5_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("images loaded");

        for(int y = 0; y < SCENE_HEIGHT; y ++) {
            for (int x = 0; x < SCENE_WIDTH; x ++) {
                scene[x][y] = new BasicBlock(ThreadLocalRandom.current().nextInt(0, imgs.length));
            }
        }

        System.out.println("map generated");

        SwingUtilities.invokeLater(() -> {
            BufferedPanel panel = new BufferedPanel(imgs, scene);
            panel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            panel.setLayout(null);

            JFrame frame = new JFrame("Map");
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
