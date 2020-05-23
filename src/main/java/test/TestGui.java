package test;

import level.MainGui;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class TestGui implements Serializable {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    MainGui window = new MainGui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
