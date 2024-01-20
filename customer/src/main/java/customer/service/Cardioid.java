package customer.service;

import lombok.SneakyThrows;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

class Cardioid extends JFrame {
    @SneakyThrows
    public  void run() {

        int count = 0;

        for (float y = 2.5f; y > -2.0f; y -= 0.12f) {
            for (float x = -2.3f; x < 2.3f; x += 0.041f) {
                float tempValue = (x * x) + (y * y) - 4f;

                if (((tempValue * tempValue * tempValue) - (x * x * y * y * y)) < -0.0f) {
                    String tempStr = "梦 梦 最 漂 亮 ";

                    int tempNum = count % tempStr.length();

                    System.err.print("\033[31m" + tempStr.charAt(tempNum));

                    count++;
                } else {
                    System.err.print(" ");
                }
            }

            System.out.println();
            Thread.sleep(100);
        }

        String tempContent = "永远快乐~";
        char[] charArray = tempContent.toCharArray();
        for (char item : charArray) {
            Thread.sleep(300);
            System.err.print("\033[32m" + item);
        }

        Thread.sleep(600);
        System.err.print("   ლ(´ڡ`ლ)");
    }


}



