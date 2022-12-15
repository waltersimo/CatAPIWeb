package org.example;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import org.example.Controller.CatController;
import org.example.IHM.CatIHM;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static final String URL_RANDOM_CAT = "https://api.thecatapi.com/v1/images/search?limit=10&breed_ids=beng&api_key=live_LThZH9F7j7Jf9GqaE5fjcFxgD24XFHQsoYZux8exFIgvAkSoMAaNa7M4htF4gc79";
//    public static final String URL_RANDOM_CAT = "https://api.thecatapi.com/v1/images/search?limit=10";
    public static final Bus bus = new Bus(ThreadEnforcer.ANY);

    public static void main(String[] args) throws IOException, URISyntaxException {

        JFrame frame = new JFrame ("MyPanel");
        frame.setTitle("API Cat App");
        frame.setResizable(true);
        frame.setSize(1200, 700);
        frame.revalidate();

        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        CatIHM Catihm = new CatIHM();

        frame.getContentPane().add (Catihm);
        frame.pack();
        frame.setVisible (true);

        new CatController(Catihm);
    }
}