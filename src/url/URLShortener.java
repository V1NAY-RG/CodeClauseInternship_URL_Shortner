package url;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class URLShortener extends JFrame {
	//URL_SHORTNER
	private final Map<String, String> urlMap;

    public URLShortener() {
        urlMap = new HashMap<>();

        setTitle("URL Shortener");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);

        JTextField longURLField = new JTextField();
        JTextField shortURLField = new JTextField();
        shortURLField.setEditable(false);

        JButton shortenButton = new JButton("Shorten URL");
        shortenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String longURL = longURLField.getText();
                if (!longURL.isEmpty()) {
                    String shortURL = generateShortURL(longURL);
                    urlMap.put(shortURL, longURL);
                    shortURLField.setText(shortURL);
                }
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(longURLField)
                .addComponent(shortenButton)
                .addComponent(shortURLField));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(longURLField)
                .addComponent(shortenButton)
                .addComponent(shortURLField));

        pack();
    }

    private String generateShortURL(String longURL) {
        int hash = longURL.hashCode();
        return "http://short.url/" + Integer.toString(hash, 36);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new URLShortener().setVisible(true);
            }
        });
    }
	
}
