import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    final String WINDOWNAME = "Lilithya GUI";
    final int HEIGHT = 720;
    final int WIDTH = 1280;
    private static final long serialVersionUID = 1L;
    JFrame window;
    JPanel controlPanel;
    Action actions;
    //JPanel p;

    private JTextArea   ta; 
    private JButton    ab;
    private JLabel label1;

    GUI(Action action) {
        actions = action;
        window = new JFrame(WINDOWNAME);
        controlPanel = new JPanel();
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }

    public void createGuiElements() {
        ab = new JButton();
        ab.setSize(300,100);
        ab.setText("Wtf is going on?");
        label1 = new JLabel();
        label1.setSize(300,100);
        label1.setText("Hey hey hey!");
        window.add(ab, BorderLayout.EAST);
        window.add(label1, BorderLayout.EAST);
        ab.setVisible(true);
        label1.setVisible(true);
        ab.setActionCommand("sendmessage 0 hello");
        ab.addActionListener(this);
        pack();
        window.setVisible(true);
    }

    public void actionPerformed (ActionEvent ae) {
        JButton aeSource = (JButton) ae.getSource();
        String[] command = aeSource.getActionCommand().split("\\s+", 3);
        if (aeSource.getActionCommand().equals("sendmessage 0 hello")) actions.executeAction(command);
        if (aeSource.getActionCommand().equals("sub")) ta.setText("SUB");
    }


    public void writeText(String text) {
       
    }
}