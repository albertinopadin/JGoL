package org.tino;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        // Set this here so application menu displays proper name:
        System.setProperty("apple.awt.application.name", "JGoL");
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        GameFrame gameFrame = new GameFrame();

        Timer timer = new Timer("Drawer", true);

        EventQueue.invokeLater(() -> {
            timer.scheduleAtFixedRate(new TimerTask() {
                                  @Override
                                  public void run() {
                                      gameFrame.repaint();
                                      toolkit.sync();
                                  }
                              },
            100,
            16);
        });
    }
}