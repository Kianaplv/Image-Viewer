import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.RescaleOp;

public class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton= new JButton("Choose Image");

    JButton showImageButton= new JButton("Show Image");
    JButton resizeButton= new JButton("Resize");
    JButton grayscaleButton= new JButton("Gray Scale");
    JButton brightnessButton= new JButton("Brightness");
    JButton closeButton= new JButton("Exit");
    JButton showResizeButton= new JButton("Show Result");
    JButton showBrightnessButton= new JButton("Result");
    JButton backButton= new JButton("Back");
    JTextField widthTextField= new JTextField();
    JTextField heightTextField= new JTextField();
    JTextField brightnessTextField= new JTextField();
    String filePath = "C:\\Users\\Kisana Pahlevan\\OneDrive\\Desktop\\image";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;
    JLabel pictureLabel= new JLabel();

    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
        this.setResizable(true);

        mainPanel();
    }

    public void mainPanel(){
        // Create main panel for adding to Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        selectFileButton.setBackground(Color.white);
        showImageButton.setBackground(Color.white);
        resizeButton.setBackground(Color.white);
        grayscaleButton.setBackground(Color.white);
        brightnessButton.setBackground(Color.white);
        closeButton.setBackground(Color.white);
        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));
        buttonsPanel.setBounds(200,50,300,200);
        //adding actionListener
        selectFileButton.addActionListener(this);
        showImageButton.addActionListener(this);
        brightnessButton.addActionListener(this);
        grayscaleButton.addActionListener(this);
        resizeButton.addActionListener(this);
        closeButton.addActionListener(this);
        backButton.addActionListener(this);
        showBrightnessButton.addActionListener(this);
        showResizeButton.addActionListener(this);
        // Adding all buttons to Grid panel
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        // add Grid panel that contains 6 buttons to main panel
        mainPanel.add(buttonsPanel);

        // add main panel to our frame
        this.add(mainPanel);
    }
    public void chooseFileImage() {
        fileChooser.setAcceptAllFileFilterUsed(false);
        int option = fileChooser.showOpenDialog(ImageViewerGUI.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
            pictureLabel.setIcon(imageIcon);
        }
        else
            System.out.println("please choose image");
    }
    public void showOriginalImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new GridLayout(1,1));
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        tempPanel.add(pictureLabel);
    }
    public void resizePanel(){
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);
        resizePanel.setSize(700,300);
        JLabel resizeLabel= new JLabel("Resize Section");
        resizeLabel.setBounds(300,50,100,50);
        JLabel widthLabel= new JLabel("Width:");
        JLabel heightLabel= new JLabel("Height:");
        widthLabel.setBounds(120,92,50,50);
        heightLabel.setBounds(120,142,50,50);
        widthTextField.setBounds(270,100,160,35);
        heightTextField.setBounds(270,150,160,35);
        backButton.setBackground(Color.white);
        backButton.setBounds(70,220,150,30);
        showResizeButton.setBackground(Color.white);
        showResizeButton.setBounds(500,220,150,30);

        //adding to panel
        resizePanel.add(resizeLabel);
        resizePanel.add(widthLabel);
        resizePanel.add(heightLabel);
        resizePanel.add(widthTextField);
        resizePanel.add(heightTextField);
        resizePanel.add(backButton);
        resizePanel.add(showResizeButton);
        //removing old panel
        this.getContentPane().removeAll();
        this.add(resizePanel);
        this.revalidate();
        this.repaint();
    }
    public void showResizeImage(int w, int h){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
        Image newImage = imageIcon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
        ImageIcon newImageIcon= new ImageIcon(newImage);
        pictureLabel.setIcon(newImageIcon);
        tempPanel.setLayout(new GridLayout(1,1));
        tempPanel.setBounds(0,0,1800, 1000);
        tempPanel.add(pictureLabel);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void brightnessPanel(){
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);
        brightnessPanel.setSize(700,300);
        JLabel brightnessLabel= new JLabel("Enter f");
        JLabel brightnessLabel2=new JLabel("(must be between 0 and 1)");
        brightnessTextField.setBounds(300,100,100,40);
        brightnessLabel.setBounds(100,100,100,50);
        brightnessLabel2.setBounds(100,120,300,50);
        brightnessPanel.add(brightnessLabel);
        brightnessPanel.add(brightnessTextField);
        brightnessPanel.add(brightnessLabel2);
        brightnessPanel.add(showBrightnessButton);
        showBrightnessButton.setBounds(300,200,100,40);
        backButton.setBounds(100,200,100,40);
        backButton.setBackground(Color.white);
        showBrightnessButton.setBackground(Color.white);
        brightnessPanel.add(backButton);
        this.getContentPane().removeAll();
        this.add(brightnessPanel);
        this.revalidate();
        this.repaint();
    }
    public void showBrightnessImage(float f){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new GridLayout(1,1));
        tempPanel.setBounds(0,0,1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            RescaleOp op = new RescaleOp(f, 0, null);
            bufferedImage = op.filter(bufferedImage, bufferedImage);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(bufferedImage).getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
            pictureLabel.setIcon(imageIcon);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        pictureLabel.setBounds(0,0,1800,1000);
        tempPanel.add(pictureLabel);
        tempFrame.add(tempPanel);
    }



    public void grayScaleImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            BufferedImage resultBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            Graphics g = resultBufferedImage.getGraphics();
            g.drawImage(bufferedImage, 0, 0, null);
            g.dispose();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(resultBufferedImage).getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
            pictureLabel.setIcon(imageIcon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tempPanel.setLayout(new GridLayout(1,1));
        tempPanel.setBounds(0,0,1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempPanel.add(pictureLabel);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            if(file==null)
                System.out.println("please choose an image");
            else
                resizePanel();

        }else if(e.getSource()== showImageButton){
            if(file==null)
                System.out.println("please choose an image");
            else
                showOriginalImage();

        }else if(e.getSource()==grayscaleButton){
            if(file==null)
                System.out.println("please choose an image");
            else
                grayScaleImage();

        }else if(e.getSource()== showResizeButton){
            System.out.println("please choose an image");
            if (widthTextField.getText().matches("[0-9]+") && heightTextField.getText().matches("[0-9]+")) {
                w = Integer.parseInt(widthTextField.getText());
                h = Integer.parseInt(heightTextField.getText());
                if (w <= 1800 && h <= 1000)
                    showResizeImage(w, h);
                else
                    System.out.println("width or height are out of bounds!");
            } else
                System.out.println("Please enter integer number");
        }else if(e.getSource()==brightnessButton){
            if(file==null)
                System.out.println("please choose an image");
            else
                brightnessPanel();

        }else if(e.getSource()== showBrightnessButton){
            try {
                brightenFactor = Float.parseFloat(brightnessTextField.getText());
                if(brightenFactor>=0&&brightenFactor<=1)
                    showBrightnessImage(brightenFactor);
                else
                    System.out.println("f should be between 0 and 1");
            } catch (NumberFormatException en){
                System.out.println("please enter number");
            }

        }else if(e.getSource()== selectFileButton){
            chooseFileImage();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}
