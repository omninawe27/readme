import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmClockApp extends JFrame {
    private JLabel currentTimeLabel;
    private JButton setAlarmButton;
    private JPanel alarmsPanel;
    private List<String> alarms;

    public AlarmClockApp() {
        setTitle("Alarm Clock App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        currentTimeLabel = new JLabel();
        currentTimeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(currentTimeLabel, BorderLayout.NORTH);

        setAlarmButton = new JButton("Set Alarm");
        setAlarmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String alarmTime = JOptionPane.showInputDialog("Enter alarm time (HH:mm:ss)");
                if (alarmTime != null && !alarmTime.isEmpty()) {
                    addAlarm(alarmTime);
                }
            }
        });
        mainPanel.add(setAlarmButton, BorderLayout.CENTER);

        alarmsPanel = new JPanel();
        alarmsPanel.setLayout(new BoxLayout(alarmsPanel, BoxLayout.Y_AXIS));
        mainPanel.add(alarmsPanel, BorderLayout.SOUTH);

        alarms = new ArrayList<>();

        add(mainPanel);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTime();
            }
        }, 0, 1000);
    }

    private void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        currentTimeLabel.setText(currentTime);
    }

    private void addAlarm(String alarmTime) {
        alarms.add(alarmTime);

        JLabel alarmLabel = new JLabel(alarmTime);
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alarmsPanel.remove(alarmLabel);
                alarmsPanel.remove(removeButton);
                alarmsPanel.revalidate();
                alarmsPanel.repaint();
                alarms.remove(alarmTime);
            }
        });

        alarmsPanel.add(alarmLabel);
        alarmsPanel.add(removeButton);

        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AlarmClockApp app = new AlarmClockApp();
                app.setVisible(true);
            }
        });
    }
}
