package source;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Rotacao extends JFrame implements ActionListener{
    

    JButton clip = new JButton("Rotate");
    JButton cancelar = new JButton("Cancel");
    JLabel x = new JLabel("Rotate Degrees: ");
    JTextField xField = new JTextField();

    JPanel painel = new JPanel();
    
    public static BufferedImage rotate(BufferedImage bimg, Double angle) {
        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
               cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = bimg.getWidth();
        int h = bimg.getHeight();
        int neww = (int) Math.floor(w*cos + h*sin),
            newh = (int) Math.floor(h*cos + w*sin);
        BufferedImage rotated = new BufferedImage(neww, newh, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.translate((neww-w)/2, (newh-h)/2);
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawRenderedImage(bimg, null);
        graphic.dispose();
        return rotated;
    }

    String imagePath = "assets/image.jpg";
    String rotatedImg = "assets/rotated.jpg";

    JLabel picLabel = new JLabel(new ImageIcon(ImageIO.read(new File(imagePath))));

    public Rotacao() throws IOException{

        setTitle("Rotation");
        setVisible(true);
        setPreferredSize(new Dimension(1100,650));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLayout(null);
        
        clip.setBackground(new Color(134,134,144 ));
        clip.setBounds(890,550,150,30);
        add(clip);
        
        cancelar.setBackground(new Color(134,134,144 ));
        cancelar.setBounds(700,550,150,30);
        add(cancelar);
        
        x.setBounds(30,550,120,23);
        add(x);
        
        xField.setBounds(135,550,50,23);
        add(xField);
        
        painel.setBackground(new Color(156,115,115));
        painel.setBounds(0,0,1090,520);
        painel.add(picLabel);
        add(painel);
        
        
        clip.addActionListener(this);
        cancelar.addActionListener(this);
        pack();
        
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()== clip){
            File imageFile = new File(imagePath);
            BufferedImage bufferedImage;
            try {
                bufferedImage = ImageIO.read(imageFile);
                
                ImageIO.write(rotate(bufferedImage, Double.valueOf(Integer.parseInt(xField.getText()))), "jpg", new File(rotatedImg));

                painel.remove(picLabel);

                JLabel rotatedPic = new JLabel(new ImageIcon(ImageIO.read(new File(rotatedImg))));
                painel.add(rotatedPic);

                painel.revalidate();
                painel.repaint();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(ae.getSource()== cancelar){
            dispose();
        }
    }
    /**
     * 
     * Main Class do programa
     * @throws IOException
     *
     */
    public static void main(String[]args) throws IOException{
        new Rotacao();
    }
}
