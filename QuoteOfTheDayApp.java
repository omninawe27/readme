import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class QuoteOfTheDayApp extends JFrame {

    private JLabel quoteLabel;
    private String[] quotes = {
            "The only way to do great work is to love what you do. - Steve Jobs",
            "Innovation distinguishes between a leader and a follower. - Steve Jobs",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "Strive not to be a success, but rather to be of value. - Albert Einstein",
            "The only source of knowledge is experience. - Albert Einstein",
            "Life is like riding a bicycle. To keep your balance, you must keep moving. - Albert Einstein"
    };

    public QuoteOfTheDayApp() {
        setTitle("Quote of the Day App");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        quoteLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(quoteLabel, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRandomQuote();
            }
        });
        panel.add(refreshButton, BorderLayout.SOUTH);

        displayRandomQuote();

        add(panel);
    }

    private void displayRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.length);
        quoteLabel.setText("<html><body style='width: 250px'>" + quotes[index] + "</body></html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuoteOfTheDayApp().setVisible(true);
            }
        });
    }
}
