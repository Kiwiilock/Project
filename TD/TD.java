import java.awt.EventQueue;
import javax.swing.JFrame;

public class TD extends JFrame {

    public TD() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("TD");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TD td = new TD();
                td.setVisible(true);
            }
        });
    }
}

