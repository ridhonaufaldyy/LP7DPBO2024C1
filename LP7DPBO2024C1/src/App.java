import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private JLabel scoreLabel; // Label untuk menampilkan skor
    private JButton StartButton; // Tombol untuk memulai permainan
    private JButton exitButton; // Tombol untuk keluar dari aplikasi
    private JPanel mainPanel; // Panel utama untuk menempatkan elemen-elemen GUI

    public static void main(String[] args)
    {
        App window = new App(); // Membuat instance dari kelas App
        // atur ukuran window
        window.setSize(400, 300);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window dengan panel utama
        window.setContentPane(window.mainPanel);
        // ubah warna background menjadi putih
        window.getContentPane().setBackground(Color.white);
        // tampilkan window
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public App() {
        // klik start
        StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // memulai permainan Flappy Bird
                startApp();
            }
        });
        // klik exit
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // keluar dari aplikasi saat tombol exit diklik
            }
        });
    }

    public void startApp() {
        JFrame frame = new JFrame("Flappy Bird"); // Membuat frame baru untuk permainan Flappy Bird
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640); // Set ukuran frame
        frame.setLocationRelativeTo(null); // Letakkan frame di tengah layar
        frame.setResizable(false); // Tidak bisa diubah ukurannya oleh pengguna

        // JLabel untuk menampilkan skor
        scoreLabel = new JLabel("0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Mengatur font dan ukuran teks
        frame.add(scoreLabel, BorderLayout.NORTH); // Menambahkan label skor di bagian atas frame

        // Membuat objek JPanel untuk permainan Flappy Bird
        FlappyBird flappyBird = new FlappyBird(this);
        frame.add(flappyBird); // Menambahkan panel permainan ke dalam frame
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true); // Menampilkan frame
    }

    public void setSkor(int skor)
    {
        scoreLabel.setText(Integer.toString(skor)); // Mengatur teks label skor dengan skor terbaru
    }
}
