import java.awt.*;

public class Pipe {
    private int posX; // Posisi X pipa
    private int posY; // Posisi Y pipa
    private int width; // Lebar pipa
    private int height; // Tinggi pipa
    private Image image; // Gambar pipa
    private int velocityX; // Kecepatan horizontal pipa
    boolean passed = false; // Status apakah pipa sudah dilewati atau belum

    // Constructor untuk kelas Pipe
    public Pipe(int posX, int posY, int width, int height, Image image) {
        this.posX = posX; // Inisialisasi posisi X
        this.posY = posY; // Inisialisasi posisi Y
        this.width = width; // Inisialisasi lebar
        this.height = height; // Inisialisasi tinggi
        this.image = image; // Inisialisasi gambar

        this.velocityX = -3; // Inisialisasi kecepatan horizontal
        this.passed = false; // Inisialisasi status pipa belum dilewati
    }

    // Getter untuk mendapatkan posisi X pipa
    public int getPosX() {
        return posX;
    }

    // Setter untuk mengatur posisi X pipa
    public void setPosX(int posX) {
        this.posX = posX;
    }

    // Getter untuk mendapatkan posisi Y pipa
    public int getPosY() {
        return posY;
    }

    // Setter untuk mengatur posisi Y pipa
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // Getter untuk mendapatkan lebar pipa
    public int getWidth() {
        return width;
    }

    // Setter untuk mengatur lebar pipa
    public void setWidth(int width) {
        this.width = width;
    }

    // Getter untuk mendapatkan tinggi pipa
    public int getHeight() {
        return height;
    }

    // Setter untuk mengatur tinggi pipa
    public void setHeight(int height) {
        this.height = height;
    }

    // Getter untuk mendapatkan gambar pipa
    public Image getImage() {
        return image;
    }

    // Setter untuk mengatur gambar pipa
    public void setImage(Image image) {
        this.image = image;
    }

    // Getter untuk mendapatkan kecepatan horizontal pipa
    public int getVelocityX() {
        return velocityX;
    }

    // Setter untuk mengatur kecepatan horizontal pipa
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    // Getter untuk mendapatkan status apakah pipa sudah dilewati atau belum
    public boolean isPassed() {
        return passed;
    }

    // Setter untuk mengatur status apakah pipa sudah dilewati atau belum
    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
