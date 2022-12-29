//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class View.GUI implements ActionListener {
//
//    JFrame frame = new JFrame();
//    JLabel title_label = new JLabel();
//    JPanel title_panel = new JPanel();
//    JLabel red_info = new JLabel();
//    JPanel redText = new JPanel();
//    JLabel blue_info = new JLabel();
//    JPanel blueText = new JPanel();
//    JButton[] buttons = new JButton[16];
//    JPanel button_panel = new JPanel();
//
//    View.GUI(){
//
//        // set frame
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the window
//        frame.setSize(1000, 800);
//        frame.getContentPane().setBackground(Color.BLACK);
//        frame.setLayout(new BorderLayout());
//        frame.setVisible(true);
//
//        title_label.setBackground(new Color(0,0,0));
//        title_label.setForeground(Color.orange);
//        title_label.setFont(new Font("Ink Free", Font.BOLD, 55));
//        title_label.setHorizontalAlignment(JLabel.CENTER);
//        title_label.setText("Conway Game of Life");
//        title_label.setOpaque(true);
////        title_label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
//
//        title_panel.setLayout(new BorderLayout());
////        title_panel.setBackground(Color.BLUE);
//        title_panel.setBounds(0,0,800,100);
//        title_panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
//
//        red_info.setBackground(Color.BLACK);
//        red_info.setForeground(Color.white);
//        red_info.setHorizontalAlignment(JLabel.LEFT);
//        red_info.setText("Stats of Red Side");
//        red_info.setOpaque(true);
//
//
////        redText.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
//        redText.setLayout(new BorderLayout());
//        redText.setBounds(5,5,10,10);
//        redText.add(red_info);
//
//        button_panel.setLayout(new BorderLayout());
//        button_panel.setBackground(Color.BLACK);
////        button_panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
//
//        for(int i=0; i<16; i++){
//            buttons[i] = new JButton();
//            //buttons[i].setBorder(BorderFactory.createLineBorder(Color.blue, 1));
//            buttons[i].setFocusable(false);
//            buttons[i].setBackground(Color.BLUE);
//            button_panel.add(buttons[i]);
//            buttons[i].addActionListener(this);
//        }
//
//
//        title_panel.add(title_label);
//        frame.add(title_panel, BorderLayout.NORTH);
////        frame.add(redText, BorderLayout.EAST);
//        frame.add(button_panel);
//
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}
