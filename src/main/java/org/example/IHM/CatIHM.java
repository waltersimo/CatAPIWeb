package org.example.IHM;

import org.example.beans.CatBeans;

import java.awt.*;
import javax.swing.*;

import static java.awt.Font.BOLD;
import static org.example.Main.bus;

public class CatIHM extends JPanel {
    private JButton buttonCat;
    private JLabel labelUrl,labelTUrl;
    private JLabel labelDescriptionImage;
    private JLabel labelIntro;

    private JProgressBar progressBar;

    public CatIHM() {
        //construct components
        buttonCat = new JButton ("Cat");
        labelUrl = new JLabel ("");
        labelTUrl = new JLabel ("Url: ");
        labelDescriptionImage = new JLabel ("Description Image");
        labelIntro = new JLabel ("Click and Get Random Image of Cat");
        progressBar = new JProgressBar();
        progressBar.setVisible(false);
        progressBar.setIndeterminate(true);

        labelIntro.setFont(new Font("Verdana", BOLD, 13));

        //adjust size and set layout
        setPreferredSize (new Dimension (522, 385));
        setLayout (null);

        //add components
        add (buttonCat);
        add (labelUrl);
        add (labelTUrl);
        add (labelDescriptionImage);
        add (labelIntro);
        add(progressBar);

        labelUrl.setForeground(Color.BLUE);

        //set component bounds (only needed by Absolute Positioning)
        buttonCat.setBounds (200, 300, 120, 50);
        labelTUrl.setBounds (70, 220, 30, 35);
        labelUrl.setBounds (100, 220, 400, 35);
        labelDescriptionImage.setBounds (70, 190, 350, 30);
        labelIntro.setBounds (75, 60, 365, 70);
        progressBar.setBounds (70, 260, 430, 10);

        progressBar.setVisible(false);

        buttonCat.addActionListener(e->{
            System.out.println("Cat pressed !!");
            bus.post("Cat");
        });
    }

    //****************************************** METHODES ******************************************//
    public void updateIHM(CatBeans cat){
        labelUrl.setText(cat.getUrl());
        labelDescriptionImage.setText(" Id = " + cat.getId() + ", width = " + cat.getWidth() + ", Height = " + cat.getHeight());
    }

    public void setProgressbar(boolean isvisible){
        progressBar.setVisible(isvisible);
    }

}

