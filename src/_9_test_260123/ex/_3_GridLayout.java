package _9_test_260123.ex;

import javax.swing.*;
import java.awt.*;

public class _3_GridLayout {
//    3x2 GridLayout, 각 칸에 번갈아
//    JLabel("항목")과 JTextField(5글자)를 배치해서
//3행 폼을 만들어보세요.
    public static void main(String[] args) {
        JFrame frame = new JFrame("실습3");
        JPanel panel = new JPanel(new GridLayout(3,2));

        for (int i = 1; i <= 3; i++) {
            panel.add(new JLabel("항목 " + i));
            panel.add(new JTextField(5));
        }

        frame.add(panel);
        frame.setSize(250,120);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
