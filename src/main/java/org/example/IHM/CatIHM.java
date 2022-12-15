package org.example.IHM;

import org.example.beans.CatBeans;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

import static java.awt.Font.BOLD;
import static org.example.Main.bus;

public class CatIHM extends JPanel {
    private JButton buttonCat;
    private JLabel labelUrl,labelTUrl;

    private JLabel labelIntro;

    private JProgressBar progressBar;

    private JLabel labelImage;

    public CatIHM() {
        //construct components
        buttonCat = new JButton ("Get a Cat");
        labelUrl = new JLabel ("");
        labelTUrl = new JLabel ("Url: ");
        labelImage = new JLabel ("Description Image");
        labelIntro = new JLabel ("Click and Get Random Image of Cat");
        progressBar = new JProgressBar();
        progressBar.setVisible(false);
        labelImage = new JLabel();



        progressBar.setIndeterminate(true);

        buttonCat.setFont(new Font("Verdana", BOLD,20));
        labelIntro.setFont(new Font("Verdana", BOLD, 13));

        labelUrl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //adjust size and set layout
        setPreferredSize (new Dimension (522, 385));
        setLayout (null);

        //add components
        add (buttonCat);
        add (labelUrl);
        add (labelTUrl);
        add (labelImage);
        add (labelIntro);
        add(labelImage);
        add(progressBar);

        labelUrl.setForeground(Color.BLUE);

        //set component bounds (only needed by Absolute Positioning)
        buttonCat.setBounds (150, 335, 210, 40);
        labelTUrl.setBounds (70, 290, 30, 35);
        labelUrl.setBounds (100, 290, 400, 35);
        labelImage.setBounds (70, 50, 400, 250);
        labelIntro.setBounds (140, 0, 365, 70);
        progressBar.setBounds (70, 320, 430, 10);

        progressBar.setVisible(false);
        labelTUrl.setVisible(false);

        labelIntro.setForeground(new Color(168, 66, 28));

        buttonCat.addActionListener(e->{
            System.out.println("Cat pressed !!");
            bus.post("Cat");
        });

        labelUrl.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (labelUrl.getText() != null) {
                        Desktop.getDesktop().browse(new URI(labelUrl.getText()));
                    }

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // the mouse has entered the label
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // the mouse has exited the label
            }

        });

    }

    //****************************************** METHODES ******************************************//
    public void updateIHM(CatBeans cat) throws IOException {
        labelUrl.setText(cat.getUrl());
        URL url = new URL(cat.getUrl());
        labelTUrl.setVisible(true);
        BufferedImage image = ImageIO.read(url);
        Image redimImg = image.getScaledInstance(labelImage.getWidth(), labelImage.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(redimImg);
        labelImage.setIcon(imageIcon);
        System.out.println("Load ended .");
//        labelImage.setText(" Id = " + cat.getId() + ", width = " + cat.getWidth() + ", Height = " + cat.getHeight());
    }

    public void setProgressbar(boolean isvisible){
        progressBar.setVisible(isvisible);
    }

}

