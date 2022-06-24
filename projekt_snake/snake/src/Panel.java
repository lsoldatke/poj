import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Panel extends JPanel {
    static final int WINDOW_HEIGHT = 600;
    static final int WINDOW_WIDTH = 600;
    static final int SIZE_OF_ELEMENTS = 20; // Rozmiar elementow / jednostek
    static final int MAX_ELEMENTS = (WINDOW_WIDTH * WINDOW_HEIGHT) / (SIZE_OF_ELEMENTS * SIZE_OF_ELEMENTS); // Maksymalna ilosc elementow / jednostek na ekranie
    static final int TIMER_DELAY = 150; // Opoznienie timera, szybkosc gry - im mniejsza wartosc, tym szybsza gra
    String GAME_OVER_MESSAGE = "KONIEC GRY"; // Napis wyswietlany po przegranej
    int[] x = new int[MAX_ELEMENTS]; // Wspolrzedne x weza w tablicy o rozmiarze rownym maksymalnej ilosci elementow / jednostek na ekranie
    int[] y = new int[MAX_ELEMENTS]; // Wspolrzedne y weza w tablicy o rozmiarze rownym maksymalnej ilosci elementow / jednostek na ekranie
    int score, snakeSize;
    int applePosX, applePosY; // Koordynaty x,y jablka
    boolean isMoving = false; // Czy waz powinien sie poruszac
    char currentDirection = 'R';
    Timer timerClock = new Timer(TIMER_DELAY, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isMoving) { // Jezeli powinien sie poruszac ...
                move(); // ... ruszaj
                isThereCollision(); // ... sprawdz czy nastapila jakas kolizja
                isAppleEaten(); // ... sprawdz czy zjedzono jablko
                repaint(); // ... odswiez / przerysuj
            }
        }
    });

    Panel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)); // Ustaw rozmiary okna na podane wczesniej szerokosc i wysokosc
        setFocusable(true); // Focus na okno
        setBackground(Color.green);
        addKeyListener(new KeyListener() { // Dodaj sluchacza nasluchujacego na wcisniecie odpowiednich klawiszy
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) { // Jezeli wcisnieto klawisz ...
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> // ... w gore, to zmien aktualny kierunek ruchu weza do gory
                            currentDirection = 'U';
                    case KeyEvent.VK_DOWN -> // ... w dol, to na dol
                            currentDirection = 'D';
                    case KeyEvent.VK_LEFT -> // ... w lewo, to na lewo
                            currentDirection = 'L';
                    case KeyEvent.VK_RIGHT -> // ... w prawo, to na prawo
                            currentDirection = 'R';
                    case KeyEvent.VK_R -> // ... a jesli wcisnieto R to zrestartuj gre
                            newGame();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        newGame();
    }

    public void newGame() {
        score = 0;
        snakeSize = 5;
        isMoving = true;
        createApple();
        timerClock.start();
    }

    public void gameOver(Graphics g) { // Wyswietl wynik i napis po przegranej
        timerClock.stop();
        g.setColor(Color.black); // Kolor czcionki wyniku i komunikatu
        g.setFont(new Font("Arial", Font.BOLD, 30)); // Typ czcionki wyniku
        g.drawString("Wynik: " + score, 10, 30); // Wyswietlenie wyniku w lewym gornym rogu okna
        g.setFont(new Font("Arial", Font.BOLD, 75)); // Typ czcionki komunikatu
        FontMetrics fm = getFontMetrics(g.getFont());
        g.drawString(GAME_OVER_MESSAGE, (WINDOW_WIDTH - fm.stringWidth(GAME_OVER_MESSAGE)) / 2, WINDOW_HEIGHT / 2); // Wyswietl komunikat na srodku ekranu
    }

    public void createApple() { // Wylosuj x, y gdzie ma sie pojawic jablko (z zakresu szerokosci i wysokosci okna)
        Random random = new Random();
        applePosX = random.nextInt(WINDOW_WIDTH / SIZE_OF_ELEMENTS) * SIZE_OF_ELEMENTS;
        applePosY = random.nextInt(WINDOW_HEIGHT / SIZE_OF_ELEMENTS) * SIZE_OF_ELEMENTS;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (isMoving) { // Jezeli ma sie poruszac to ...
            g.setColor(Color.black); // Kolor jablka
            g.fillOval(applePosX, applePosY, SIZE_OF_ELEMENTS, SIZE_OF_ELEMENTS); // Narysuj jablko

            for (int i = 0; i < snakeSize; i++) {
                if (i == 0) { // x[0], y[0] - glowa weza
                    g.setColor(Color.black); // Kolor glowy weza
                    g.fillRect(x[i], y[i], SIZE_OF_ELEMENTS, SIZE_OF_ELEMENTS); // Rysuj glowe weza
                } else {
                    g.setColor(Color.darkGray); // Kolor reszty weza
                    g.fillRect(x[i], y[i], SIZE_OF_ELEMENTS, SIZE_OF_ELEMENTS); // Rysuje reszte weza
                }
            }

            g.setColor(Color.black); // Kolor wyniku
            g.setFont(new Font("Arial", Font.BOLD, 30)); // Rodzaj czcionki wyniku
            g.drawString("Wynik: " + score, 10, 30); // Rysuj wynik
        } else {
            gameOver(g); // ... a jesli sie nie rusza to koniec
        }
    }

    public void move() {
        for (int i = snakeSize; i > 0; i--) { // Zmien polozenie poszczegolnych elementow weza poprzez przypisanie im pozycji x, y poprzedniego elementu
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (currentDirection) { // Zmiana kierunku ruchu weza poprzez dodanie lub odjecie od koordynatow jego glowy rozmiaru pojedynczego elementu
            case 'U' -> // Gora
                    y[0] = y[0] - SIZE_OF_ELEMENTS;
            case 'D' -> // Dol
                    y[0] = y[0] + SIZE_OF_ELEMENTS;
            case 'L' -> // Lewo
                    x[0] = x[0] - SIZE_OF_ELEMENTS;
            case 'R' -> // Prawo
                    x[0] = x[0] + SIZE_OF_ELEMENTS;
        }
    }

    public void isThereCollision() { // Obsluga kolizji
        for (int i = snakeSize; i > 0; i--) {
            if ((x[i] == x[0]) && (y[i] == y[0])) { // Sprawdzenie czy nastapila kolizja glowy z cialem weza ...
                isMoving = false; // ... jesli tak, ma sie zatrzymac
                break;
            }
        }

        if (x[0] < 0) // Sprawdzenie kolizji z lewa krawedzia
            isMoving = false;
        if (x[0] > WINDOW_WIDTH) // ... z prawa
            isMoving = false;
        if (y[0] < 0) // ... z gorna
            isMoving = false;
        if (y[0] > WINDOW_HEIGHT) // ... i z dolna krawedzia
            isMoving = false;
    }

    public void isAppleEaten() { // Obsluga zjedzenia jablka
        if ((applePosX == x[0]) && (applePosY == y[0])) { // Jesli glowa weza i jablko znajda sie w tym samym miejscu ...
            score++; // ... zwieksz wynik
            snakeSize++; // ... powieksz weza
            createApple(); // ... i stworz nowe jablko
        }
    }
}
