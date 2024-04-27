import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360; // Lebar frame permainan
    int frameHeight = 640; // Tinggi frame permainan

    Image backgroundImage; // Gambar latar belakang
    Image birdImage; // Gambar burung
    Image lowerPipeImage; // Gambar pipa bawah
    Image upperPipeImage; // Gambar pipa atas

    int playerStartPosX = frameWidth / 8; // Posisi awal burung (sumbu X)
    int playerStartPosY = frameHeight / 2; // Posisi awal burung (sumbu Y)
    int playerWidth = 34; // Lebar burung
    int playerHeight = 24; // Tinggi burung
    Player player; // Objek untuk merepresentasikan burung

    int pipeStartPosX = frameWidth; // Posisi awal pipa (sumbu X)
    int pipeStartPosY = 0; // Posisi awal pipa (sumbu Y)
    int pipeWidth = 64; // Lebar pipa
    int pipeHeight = 512; // Tinggi pipa
    ArrayList<Pipe> pipes; // ArrayList untuk menyimpan pipa-pipa dalam permainan

    Timer gameLoop; // Timer untuk mengatur pergerakan permainan
    Timer pipesCooldown; // Timer untuk mengatur waktu kemunculan pipa baru
    int gravity = 1; // Besaran gravitasi dalam permainan
    int skor = 0; // Skor permainan
    private App app; // Objek dari kelas App

    // Constructor untuk kelas FlappyBird
    public FlappyBird(App app) {
        setPreferredSize(new Dimension(frameWidth, frameHeight)); // Mengatur dimensi panel
        setFocusable(true); // Mengatur panel agar dapat menerima input keyboard
        addKeyListener(this); // Menambahkan listener untuk input keyboard
        this.app = app; // Menginisialisasi objek App

        // Memuat gambar-gambar yang diperlukan dari file
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        // Inisialisasi objek player dan ArrayList pipes
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        // Membuat timer untuk mengatur waktu kemunculan pipa baru
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes(); // Memanggil method placePipes() untuk menempatkan pipa baru
            }
        });

        // Membuat timer untuk mengatur pergerakan permainan
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start(); // Memulai timer permainan

        pipesCooldown.start(); // Memulai timer untuk kemunculan pipa baru
    }

    // Method untuk menggambar komponen pada panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Memanggil method paintComponent() dari kelas JPanel
        draw(g); // Memanggil method draw() untuk menggambar komponen permainan
    }

    // Method untuk menggambar komponen permainan
    public void draw(Graphics g) {
        // Menggambar latar belakang
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);

        // Menggambar burung
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        // Menggambar pipa-pipa
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    // Method untuk menggerakkan komponen permainan
    public void move() {
        int berakhir = 0; // Variable untuk menandakan apakah permainan berakhir
        int ngurangiHitbox = 5; // Untuk mengurangi hitbox pipa

        // Mengatur pergerakan vertikal burung dengan gravitasi
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0)); // Batasan agar burung tidak jatuh ke luar layar

        // Iterasi untuk setiap pipa dalam ArrayList pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX()); // Menggeser posisi pipa ke kiri

            // Deteksi tabrakan burung dengan pipa atau keluar dari layar
            if ((player.getPosX() < pipe.getPosX() + pipe.getWidth() - ngurangiHitbox &&
                    player.getPosX() + player.getWidth() > pipe.getPosX() + ngurangiHitbox &&
                    player.getPosY() < pipe.getPosY() + pipe.getHeight() - ngurangiHitbox &&
                    player.getPosY() + player.getHeight() > pipe.getPosY() + ngurangiHitbox) ||
                    (player.getPosY() < 0 || player.getPosY() + player.getHeight() > frameHeight + 50)) {
                berakhir = 1; // Menandakan terjadi tabrakan
                gameLoop.stop(); // Menghentikan permainan
                pipesCooldown.stop(); // Menghentikan timer untuk kemunculan pipa baru
            }

            // Deteksi melewati pipa
            if (player.getPosX() > pipe.getPosX() && player.getPosX() < pipe.getPosX() + pipe.getWidth()) {
                if (!pipe.isPassed()) {
                    skor++; // Skor bertambah
                    app.setSkor(skor); // Memperbarui skor pada label
                    pipe.setPassed(true);
                }
            }
        }

        // Menampilkan pesan Game Over jika terjadi tabrakan
        if (berakhir == 1) {
            JOptionPane.showMessageDialog(null, "Game Over!");
        }
    }

    // Method untuk memulai ulang permainan
    public void RestartGame() {
        player.setPosX(playerStartPosX); // Mengatur posisi X burung ke posisi awal
        player.setPosY(playerStartPosY); // Mengatur posisi Y burung ke posisi awal
        player.setVelocityY(0); // Mengatur kecepatan vertikal burung menjadi nol
        pipes.clear(); // Menghapus semua pipa dari layar
        skor = 0; // Mengatur skor kembali menjadi nol
        app.setSkor(skor); // Memperbarui skor pada label

        gameLoop.start(); // Memulai kembali permainan
        pipesCooldown.start(); // Memulai kembali timer untuk kemunculan pipa baru
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move(); // Memanggil method move() setiap kali timer beraksi
        repaint(); // Memanggil method repaint() untuk menggambar ulang komponen permainan
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10); // Mengatur kecepatan vertikal burung saat tombol SPACE ditekan
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            RestartGame(); // Memulai ulang permainan saat tombol R ditekan
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    // Method untuk menempatkan pipa baru pada layar
    public void placePipes() {
        int randomPipePosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2)); // Posisi Y pipa secara acak
        int openingSpace = frameHeight / 4; // Jarak antara pipa atas dan pipa bawah

        // Menambahkan pipa atas ke dalam ArrayList pipes
        Pipe upperPipe = new Pipe(pipeStartPosX, randomPipePosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        // Menambahkan pipa bawah ke dalam ArrayList pipes
        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPipePosY + pipeHeight + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);
        lowerPipe.setPassed(true); // Menandakan bahwa pipa tersebut sudah dilewati
        pipes.add(lowerPipe);
    }
}
