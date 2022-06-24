import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        Panel panel = new Panel();

        window.setTitle("Wonsz"); // Tytul okienka
        window.add(panel); // Dodaj panel do ramki
        window.pack(); // Dopasowuje rozmiar ramki do znajdujacych sie w niej elementow
        window.setVisible(true); // Widocznosc okna
        window.setResizable(false); // Zablokowanie zmiany rozmiaru okna podczas gry, aby nie doprowadzic np. do problemow z krawedziami
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zamkniecie program, a nie tylko okna po kliknieciu krzyzyka
    }
}
