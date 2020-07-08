package xulyanh;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;


public class Display extends JFrame {

    /**
     * HI
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tf_path;
    File file = null;
    File file_save = null;
    Image_Processing ip;
    BufferedImage img_goc = null;
    BufferedImage img = null;
    BufferedImage image = null;
    JButton bt_save = new JButton(new ImageIcon("save.png"));

    private JLabel lb_image;
    private JButton btnConvert;
    private JTextField tf_c_power;
    private JTextField tf_bit;
    private JRadioButton rd_1;
    private JRadioButton rd_2;
    private JRadioButton rd_3;
    private JRadioButton rd_4;
    private JRadioButton rd_5;
    private JRadioButton rd_6;
    private JRadioButton rd_7;
    private JRadioButton rd_8;
    private JRadioButton rd_9;
    private JRadioButton rd_10;
    private JRadioButton rd_11;
    private JRadioButton rd_12;
    private JRadioButton rd_13;
    private JRadioButton rd_14;
    private JRadioButton rd_15;
    private JRadioButton rd_16;
    private JRadioButton rd_17;
    private JRadioButton rd_18;
    private JRadioButton rd_19;
    private JRadioButton rd_20;
    private JRadioButton rd_21;
    private JLabel lb_newLever;
    private JSlider sliderNewLever;
    private JSlider slider_Threshold;
    private JLabel lb_th;
    private JTextField tf_c;
    private JTextField tf_gamma;


    /**
     * Launch the application.
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    Display frame = new Display();
                    frame.setVisible(true);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                }
            }
        });
    }

    /**
     * Create the frame.
     */

    public Display() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(5, 5, 1350, 740);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        this.setTitle("Bài Tập Xử Lý Ảnh");
        contentPane.setLayout(null);
        tf_path = new JTextField();
        tf_path.setEnabled(false);
        tf_path.setBounds(29, 8, 238, 31);
        contentPane.add(tf_path);
        tf_path.setColumns(10);


        rd_1 = new JRadioButton("Gray Image");
        rd_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableTextField();
            }
        });
        rd_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_1.setBounds(27, 94, 109, 23);
        contentPane.add(rd_1);

        rd_2 = new JRadioButton("Negative Image");
        rd_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableTextField();
            }
        });
        rd_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_2.setBounds(27, 121, 210, 23);
        contentPane.add(rd_2);

        rd_3 = new JRadioButton("Negative Gray Image");
        rd_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableTextField();
            }
        });
        rd_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_3.setBounds(27, 148, 210, 23);
        contentPane.add(rd_3);


        rd_4 = new JRadioButton("Logarit Image");
        rd_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableTextField();
                tf_c.setEnabled(true);
            }
        });
        rd_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_4.setBounds(27, 202, 104, 23);
        contentPane.add(rd_4);

        rd_5 = new JRadioButton("Pow-law Image");
        rd_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableTextField();
                tf_gamma.setEnabled(true);
                tf_c_power.setEnabled(true);
            }
        });
        rd_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_5.setBounds(27, 229, 115, 23);
        contentPane.add(rd_5);




        rd_6 = new JRadioButton("Bit Plane Sciling");
        rd_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableTextField();
                tf_bit.setEnabled(true);
            }
        });
        rd_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_6.setBounds(27, 256, 120, 23);
        contentPane.add(rd_6);


        rd_7 = new JRadioButton(" Threshold");
        rd_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableTextField();
                if (file != null) {
                    slider_Threshold.setEnabled(true);
                    lb_th.setEnabled(true);
                }

            }
        });
        rd_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_7.setBounds(27, 293, 100, 23);
        contentPane.add(rd_7);

        rd_8 = new JRadioButton("Equalisation");
        rd_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableTextField();
                if (file != null) {
                    sliderNewLever.setEnabled(true);
                    lb_newLever.setEnabled(true);
                }
            }
        });
        rd_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_8.setBounds(27, 67, 130, 23);
        contentPane.add(rd_8);


        tf_c_power = new JTextField();
        tf_c_power.setEnabled(false);
        tf_c_power.setBounds(302, 229, 27, 20);
        contentPane.add(tf_c_power);
        tf_c_power.setColumns(10);


        JLabel lblGamma = new JLabel("| Gamma:");
        lblGamma.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblGamma.setBounds(140, 229, 68, 23);
        contentPane.add(lblGamma);

        tf_gamma = new JTextField();
        tf_gamma.setEnabled(false);
        tf_gamma.setBounds(206, 229, 27, 20);
        contentPane.add(tf_gamma);
        tf_gamma.setColumns(10);

        tf_path = new JTextField();
        tf_path.setEnabled(false);
        tf_path.setBounds(29, 8, 238, 31);
        contentPane.add(tf_path);
        tf_path.setColumns(10);


        rd_9 = new JRadioButton("Point Detection");
        rd_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_9.setBounds(27, 460, 180, 23);
        contentPane.add(rd_9);

        rd_10 = new JRadioButton("Line Detection");
        rd_10.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_10.setBounds(27, 490, 160, 23);
        contentPane.add(rd_10);

        rd_11 = new JRadioButton("Sobel detection");
        rd_11.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_11.setBounds(27, 520, 160, 23);
        contentPane.add(rd_11);

        rd_12 = new JRadioButton("Prewitt detection");
        rd_12.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_12.setBounds(27,610 , 160, 23);
        contentPane.add(rd_12);

        rd_13 = new JRadioButton("Robert detection");
        rd_13.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_13.setBounds(27, 580, 160, 23);
        contentPane.add(rd_13);

        rd_14 = new JRadioButton("Laplacian Of Gaussian");
        rd_14.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_14.setBounds(27,550,160,23);
        contentPane.add(rd_14);

        rd_15 = new JRadioButton("Auto Threshold");
        rd_15.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_15.setBounds(27,640 , 160, 23);
        contentPane.add(rd_15);

        rd_16 = new JRadioButton("Adaptive Threshold");
        rd_16.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_16.setBounds(27,670 , 160, 23);
        contentPane.add(rd_16);

        rd_17 = new JRadioButton("Min Neighbourhood Image");
        rd_17.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_17.setBounds(27, 175, 184, 23);
        contentPane.add(rd_17);

        rd_18 = new JRadioButton("Max Neighbourhood Image");
        rd_18.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_18.setBounds(27, 340, 183, 23);
        contentPane.add(rd_18);

        rd_19 = new JRadioButton("Smoothing Image");
        rd_19.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_19.setBounds(27, 370, 150, 23);
        contentPane.add(rd_19);

        rd_20 = new JRadioButton("Median Image");
        rd_20.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_20.setBounds(27, 400, 109, 23);
        contentPane.add(rd_20);

        rd_21 = new JRadioButton("Weight Smoothing Image");
        rd_21.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rd_21.setBounds(27, 430, 189, 23);
        contentPane.add(rd_21);

        lb_image = new JLabel("");
        lb_image.setBounds(850, 65, 400, 600);
        contentPane.add(lb_image);

        JLabel lb_image1 = new JLabel("");
        lb_image1.setBounds(354, 65, 400, 600);
        contentPane.add(lb_image1);

        JButton bt_run = new JButton(new ImageIcon("play.png"));
        bt_run.setEnabled(false);
        bt_run.setBounds(400, 5, 45, 45);
        contentPane.add(bt_run);
        bt_save.setEnabled(false);
        bt_save.setBounds(320, 5, 45, 45);
        contentPane.add(bt_save);

        ButtonGroup group = new ButtonGroup();
        group.add(rd_1);
        group.add(rd_2);
        group.add(rd_3);
        group.add(rd_4);
        group.add(rd_5);
        group.add(rd_6);
        group.add(rd_7);
        group.add(rd_8);
        group.add(rd_11);
        group.add(rd_12);
        group.add(rd_13);
        group.add(rd_9);
        group.add(rd_10);
        group.add(rd_14);
        group.add(rd_15);
        group.add(rd_16);
        group.add(rd_17);
        group.add(rd_18);
        group.add(rd_19);
        group.add(rd_20);
        group.add(rd_21);

        bt_run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             ip = new Image_Processing();
                image.setData(img_goc.getData()); // Cập nhật image lại thành gốc
                try {
                    if (rd_1.isSelected()) {
                        img = ip.grayImage(image);
                    } else if (rd_2.isSelected()) {
                        img = ip.negativeImage(image);
                    } else if (rd_3.isSelected()) {
                        img = ip.negativeGrayImage(image);
                    } else if (rd_4.isSelected()) {
                        if (tf_c.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập vào Textfield Constant", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int constant = Integer.parseInt(tf_c.getText());
                        if (constant > 50) {
                            JOptionPane.showMessageDialog(null,
                                    "Constant vượt quá giới hạn cho phép. Khuyến nghị <= 50", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        } else if (constant < 0) {
                            JOptionPane.showMessageDialog(null, "Constant không nhận được giá trị âm", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        } else {
                            img = ip.logaritImage(image, Integer.parseInt(tf_c.getText()));
                        }
                    } else if (rd_5.isSelected()) {
                        if (tf_gamma.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập vào Textfield Gamma", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        } else if (tf_c_power.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập vào Textfield Constant", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        } else if (Integer.parseInt(tf_c_power.getText()) > 255
                                || Integer.parseInt(tf_c_power.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Constant chỉ nằm trong khoảng 0 - 255", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        } else {
                            img = ip.power_lawImage(image, Integer.parseInt(tf_gamma.getText()),
                                    Integer.parseInt(tf_c_power.getText()));
                        }

                    } else if (rd_6.isSelected()) {
                        if (tf_bit.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập vào Textfield Bit", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int bit = Integer.parseInt(tf_bit.getText());
                        if (bit < 0 || bit > 7) {
                            JOptionPane.showMessageDialog(null, "Bit chỉ nằm trong khoảng 0 - 7", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        } else {
                            img = ip.bitPlaneSlicing(image, bit);
                        }
                    }
                    else if (rd_9.isSelected()) {
                        img = ip.pointDetection(image,180);
                    }else if (rd_10.isSelected()) {
                        img = ip.lineDectecion(image,128);
                    }else if (rd_11.isSelected()){
                        img = ip.sobelImage(image);
                    }else if (rd_12.isSelected()){
                        img= ip.prewittImage(image);
                    }else if (rd_13.isSelected()){
                        img= ip.laplacian_Gaussian(image);
                    }else if (rd_14.isSelected()){
                        img= ip.robertImage(image);
                    }else if (rd_15.isSelected()){
                        img= ip.autoThreshold(image);
                    }else if (rd_16.isSelected()){
                        img= ip.adaptiveThreshold_Median(image,60);
                    } else if (rd_17.isSelected()) {
                        img = ip.minNeighbourhoodImage(image);
                    } else if (rd_18.isSelected()) {
                        img = ip.maxNeighbourhoodImage(image);
                    } else if (rd_19.isSelected()) {
                        img = ip.smoothingImage(image);
                    } else if (rd_20.isSelected()) {
                        img = ip.medianImage(image);
                    } else if (rd_21.isSelected()) {
                        img = ip.weightSmoothingImage(image);
                    }

                    ImageIcon icon = new ImageIcon(img);
                    lb_image.setSize(400, 600);
                    lb_image.setIcon(icon);
                    bt_save.setEnabled(true);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                btnConvert.setEnabled(true);
            }
        });

        bt_save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser jfc = new JFileChooser();
                int returnVal = jfc.showSaveDialog(null); // Nếu có chọn nút Save thì trả về 0
                if (returnVal == jfc.APPROVE_OPTION) { // APPROVE_OPTION chứng tỏ bạn đã chọn Open or Save rồi
                    file_save = jfc.getSelectedFile(); // APPROVE_OPTION ở đây luôn = 0
                    try {
                        ip.save_Image(img, file_save.toString(), "jpg");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        });

        JButton btnOpenFile = new JButton(new ImageIcon("open.png"));
        btnOpenFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                int returnVal = jfc.showOpenDialog(null); // Nếu có chọn nút Save thì trả về 0
                if (returnVal == jfc.APPROVE_OPTION) { // APPROVE_OPTION chứng tỏ bạn đã chọn Open or Save rồi
                    file = jfc.getSelectedFile(); // APPROVE_OPTION ở đây luôn = 0
                    tf_path.setText(file.toString());
                    bt_run.setEnabled(true);
                    sliderNewLever.setValue(255);
                    slider_Threshold.setValue(128);
                    if (rd_8.isSelected()) {
                        sliderNewLever.setEnabled(true);
                        lb_newLever.setEnabled(true);
                    } else if (rd_7.isSelected()) {
                        slider_Threshold.setEnabled(true);
                        lb_th.setEnabled(true);
                    }
                    try {
                        image = ImageIO.read(file);
                        img_goc = ImageIO.read(file);
                        ImageIcon icon_1 = new ImageIcon(image);
                        lb_image1.setSize(400, 600);
                        lb_image1.setIcon(icon_1);

                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnOpenFile.setBounds(270, 5, 45, 45);
        contentPane.add(btnOpenFile);


        btnConvert = new JButton(new ImageIcon("swap.png"));
        btnConvert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                image = img;
                for (int i = 0; i < img_goc.getWidth(); i++) {
                    for (int j = 0; j < img_goc.getHeight(); j++) {
                        img_goc.setRGB(i, j, img.getRGB(i, j));
                    }
                }
                ImageIcon icon_1 = new ImageIcon(image);
                lb_image1.setSize(400, 600);
                lb_image1.setIcon(icon_1);
            }
        });
        btnConvert.setBounds(764, 299, 80, 70);
        btnConvert.setEnabled(false);
        contentPane.add(btnConvert);
        JLabel lblC = new JLabel("|  Constant:");
        lblC.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblC.setBounds(133, 202, 83, 23);
        contentPane.add(lblC);

        tf_c = new JTextField();
        tf_c.setEnabled(false);
        tf_c.setBounds(215, 202, 27, 20);
        contentPane.add(tf_c);
        tf_c.setColumns(10);

        JLabel lblConstant = new JLabel("Constant:");
        lblConstant.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblConstant.setBounds(243, 229, 60, 23);
        contentPane.add(lblConstant);

        JLabel lblBit = new JLabel("|    Bit: ");
        lblBit.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblBit.setBounds(153, 256, 55, 23);
        contentPane.add(lblBit);

        tf_bit = new JTextField();
        tf_bit.setEnabled(false);
        tf_bit.setBounds(196, 256, 27, 20);
        contentPane.add(tf_bit);
        tf_bit.setColumns(10);

        JLabel lblNewLever = new JLabel("| New Lever:");
        lblNewLever.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLever.setBounds(157, 67, 82, 23);
        contentPane.add(lblNewLever);

        sliderNewLever = new JSlider();
        sliderNewLever.setMajorTickSpacing(51);
        sliderNewLever.setEnabled(false);
        sliderNewLever.setMaximum(255);
        sliderNewLever.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                if (file != null) {
                    lb_newLever.setText(String.valueOf(sliderNewLever.getValue()));
                    btnConvert.setEnabled(true);
                    image.setData(img_goc.getData());
                    ip = new Image_Processing();
                    try {
                        img = ip.frequencyBalancingImage(image, sliderNewLever.getValue());
                        ImageIcon icon = new ImageIcon(img);
                        lb_image.setSize(400, 600);
                        lb_image.setIcon(icon);
                        bt_save.setEnabled(true);
                    } catch (IOException ex) {
                        // TODO Auto-generated catch block
                        ex.printStackTrace();
                    }
                }
            }
        });
        sliderNewLever.setValue(255);
        sliderNewLever.setPaintTicks(true);
        sliderNewLever.setBounds(235, 72, 80, 20);
        contentPane.add(sliderNewLever);

        lb_newLever = new JLabel("255");
        lb_newLever.setEnabled(false);
        lb_newLever.setFont(new Font("Tahoma", Font.BOLD, 13));
        lb_newLever.setBounds(320, 67, 27, 23);
        contentPane.add(lb_newLever);

        slider_Threshold = new JSlider();
        slider_Threshold.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                if (file != null) {
                    lb_th.setText(String.valueOf(slider_Threshold.getValue()));
                    btnConvert.setEnabled(true);
                    image.setData(img_goc.getData());
                    ip = new Image_Processing();
                    try {
                        img = ip.thresholdImage(image, slider_Threshold.getValue());
                        ImageIcon icon = new ImageIcon(img);
                        lb_image.setSize(400, 600);
                        lb_image.setIcon(icon);
                        bt_save.setEnabled(true);
                    } catch (IOException ex) {
                        // TODO Auto-generated catch block
                        ex.printStackTrace();
                    }
                }
            }
        });
        slider_Threshold.setEnabled(false);
        slider_Threshold.setMinorTickSpacing(51);
        slider_Threshold.setMaximum(255);
        slider_Threshold.setValue(128);
        slider_Threshold.setPaintTicks(true);
        slider_Threshold.setBounds(130, 300, 80, 20);
        contentPane.add(slider_Threshold);

        lb_th = new JLabel("128");
        lb_th.setEnabled(false);
        lb_th.setFont(new Font("Tahoma", Font.BOLD, 13));
        lb_th.setBounds(215, 295, 32, 23);
        contentPane.add(lb_th);
    }


    public void enableTextField() {
        lb_th.setEnabled(false);
        slider_Threshold.setEnabled(false);
        lb_newLever.setEnabled(false);
        sliderNewLever.setEnabled(false);
        tf_c.setEnabled(false);
        tf_gamma.setEnabled(false);
        tf_c_power.setEnabled(false);
        tf_bit.setEnabled(false);
    }

}
