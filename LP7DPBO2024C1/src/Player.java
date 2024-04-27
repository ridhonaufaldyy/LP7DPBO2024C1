import java.awt.*;

public class Player {
    private int posX; // Posisi X burung
    private int posY; // Posisi Y burung
    private int width; // Lebar burung
    private int height; // Tinggi burung
    private Image image; // Gambar burung
    private int velocityY; // Kecepatan vertikal burung

    // Constructor untuk kelas Player
    public Player(int posX, int posY, int width, int height, Image image) {
        this.posX = posX; // Inisialisasi posisi X
        this.posY = posY; // Inisialisasi posisi Y
        this.width = width; // Inisialisasi lebar
        this.height = height; // Inisialisasi tinggi
        this.image = image; // Inisialisasi gambar

        this.velocityY = -0; // Inisialisasi kecepatan vertikal
    }

    // Getter untuk mendapatkan posisi X burung
    public int getPosX() {
        return posX;
    }

    // Setter untuk mengatur posisi X burung
    public void setPosX(int posX) {
        this.posX = posX;
    }

    // Getter untuk mendapatkan posisi Y burung
    public int getPosY() {
        return posY;
    }

    // Setter untuk mengatur posisi Y burung
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // Getter untuk mendapatkan lebar burung
    public int getWidth() {
        return width;
    }

    // Setter untuk mengatur lebar burung
    public void setWidth(int width) {
        this.width = width;
    }

    // Getter untuk mendapatkan tinggi burung
    public int getHeight() {
        return height;
    }

    // Setter untuk mengatur tinggi burung
    public void setHeight(int height) {
        this.height = height;
    }

    // Getter untuk mendapatkan gambar burung
    public Image getImage() {
        return image;
    }

    // Setter untuk mengatur gambar burung
    public void setImage(Image image) {
        this.image = image;
    }

    // Getter untuk mendapatkan kecepatan vertikal burung
    public int getVelocityY() {
        return velocityY;
    }

    // Setter untuk mengatur kecepatan vertikal burung
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}
