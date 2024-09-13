package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.System.out;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        JFrame jframe = new JFrame("Draw by Server: V2");
        jframe.setSize(600,600);
        JTextField jTextField = new JTextField();
        jTextField.setToolTipText("WPISZ TUTAJ KOMENDY!: ");
        jTextField.setFont(new Font("sans",Font.PLAIN,15));
        JLabel textLabel = new JLabel("WPISZ TUTAJ KOMENDY:");
        textLabel.setBackground(Color.YELLOW);
        textLabel.setOpaque(true);
        JPanel textinputPanel = new JPanel();
        textinputPanel.setLayout(new BoxLayout(textinputPanel, BoxLayout.Y_AXIS));
        textinputPanel.add(textLabel);
        textinputPanel.add(Box.createRigidArea(new Dimension(0,5)));
        textinputPanel.add(jTextField);
        jframe.add(textinputPanel, BorderLayout.SOUTH);
        JPanel jPanel = new JPanel();
        BufferedImage picture = ImageIO.read(new File("src/main/resources/123niger.png"));
        JLabel picLabel = new JLabel(new ImageIcon(picture));
        jPanel.add(picLabel);
        jframe.add(jPanel, BorderLayout.CENTER);
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jframe.setVisible(true);

        PrintWriter printWriter = null;

        try {
            Socket socket = new Socket("localhost",12345);
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        PrintWriter finalPrintWriter = printWriter;

        jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String command = actionEvent.getActionCommand();
                finalPrintWriter.println(command);
                jTextField.setText("");
            }
        });

        }
    }