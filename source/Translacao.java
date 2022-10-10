package source;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Mr. Savagery
 */
public class Translacao extends JFrame implements ActionListener{
    
    JButton clip = new JButton("Translate");
    JButton cancelar = new JButton("Cancel");
    JLabel x = new JLabel("X: ");
    JTextField xField = new JTextField();

    JLabel y = new JLabel("Y: ");
    JTextField yField = new JTextField();

    JPanel painel = new JPanel();

    String imagePath = "assets/image.jpg";
    String croppedImg = "assets/cropped.jpg";

    JLabel picLabel = new JLabel(new ImageIcon(ImageIO.read(new File(imagePath))));

    public Translacao() throws IOException{
        
        setTitle("Clip");
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
        
        x.setBounds(30,550,20,23);
        add(x);
        
        xField.setBounds(70,550,50,23);
        add(xField);
        
        y.setBounds(170,550,20,23);
        add(y);
        
        yField.setBounds(210,550,50,23);
        add(yField);
        
        painel.setBackground(new Color(156,115,115));
        painel.setBounds(0,0,1090,520);
        painel.add(picLabel);
        add(painel);
        
        clip.addActionListener(this);
        cancelar.addActionListener(this);
        pack();
        
        setLocationRelativeTo(null);
    }
    
    /**
     * Metodo contendo todos eventos da classe
     * @param ae como variavel do evento
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()== clip){
            painel.setLocation(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
            painel.revalidate();
            painel.repaint();
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
        new Translacao();
    }
}