import javax.swing.*;
import java.awt.*;


public class Main  {

    static public int counter;
    static public int max_prime;

    public static void main(String[] args) {

        //Creating Frame
        JFrame MainFrame =new JFrame("Prime Number Producer");
        JLabel label1 =new JLabel("");
        MainFrame.getContentPane().setBackground( Color.gray );


        final JTextField textField1 = new JTextField();
        textField1.setBounds(30, 45, 150, 24);
        JLabel ul1 = new JLabel("Number");
        ul1.setBounds(240, 40, 150, 30);

        JLabel Label2 = new JLabel("");
        Label2.setBounds(30, 68, 300, 30);

        final JTextField txt2 = new JTextField();
        txt2.setBounds(30, 95, 150, 24);
        JLabel Label3 = new JLabel("Buffer Size ");
        Label3.setBounds(240, 90, 150, 30);

        JLabel label4 = new JLabel("");
        label4.setBounds(30, 122, 300, 30);

        final JTextField textField4 = new JTextField();
        textField4.setBounds(30, 145, 150, 24);
        JLabel ul3 = new JLabel("File Name ");
        ul3.setBounds(240, 140, 150, 30);

        JButton Startbtn = new JButton("Click to Produce");
        Startbtn.setBounds(30, 200, 300, 40);
        Startbtn.setForeground(Color.white);
        Startbtn.setBackground(Color.darkGray);


        JLabel label5 = new JLabel("Largest Prime: ");
        label5.setBounds(30, 260, 300, 30);
        label5.setForeground (Color.darkGray);


        JLabel labelout1 = new JLabel("---------");
        labelout1.setBounds(290, 260, 60, 30);
        labelout1.setForeground (Color.WHITE);


        JLabel label6 = new JLabel("Number of primes Produced: ");
        label6.setBounds(30, 300, 300, 30);

        JLabel labelout2 = new JLabel("---------");
        labelout2.setBounds(290, 300, 60, 30);
        labelout2.setForeground (Color.WHITE);


        JLabel label7 = new JLabel("Time elapsed: ");
        label7.setBounds(30, 340, 300, 30);
        JLabel ans3 = new JLabel("---------");
        ans3.setBounds(290, 340, 60, 30);
        ans3.setForeground (Color.WHITE);


        // Show Frame Elements
        MainFrame.setSize(400, 450);
        MainFrame.add(label1);
        MainFrame.add(Label3);
        MainFrame.add(label6);
        MainFrame.add(ul1);
        MainFrame.add(Label3);
        MainFrame.add(ul3);
        MainFrame.add(textField1);
        MainFrame.add(txt2);
        MainFrame.add(textField4);
        MainFrame.add(Startbtn);
        MainFrame.add(label5);
        MainFrame.add(label6);
        MainFrame.add(label7);
        MainFrame.add(labelout1);
        MainFrame.add(labelout2);
        MainFrame.add(ans3);
        MainFrame.setLayout(null);
        MainFrame.setVisible(true);

        Startbtn.addActionListener(e -> {
           //Counter for # of Prime numbers
            long start_Time = System.currentTimeMillis();
            counter = max_prime = 0;
            buffer buf = new buffer(Integer.parseInt(txt2.getText()));


        Producer P = new Producer(buf,Integer.parseInt(textField1.getText()));
        Consumer C = new Consumer(buf,textField4.getText());

        P.start();
        C.start();
            try {
                // Wait consumer & Producer to end their work before showing answer (counter , max prime)
                C.join();
                P.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            labelout1.setText(String.valueOf(Consumer.largestPrime));
            labelout2.setText(String.valueOf(Consumer.counter));
            ans3.setText(System.currentTimeMillis() - start_Time + " MS");
            SwingUtilities.updateComponentTreeUI(MainFrame);
        });
    }
}