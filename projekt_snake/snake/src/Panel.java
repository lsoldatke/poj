import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel {
    static final int WINDOW_HEIGHT = 600;
    static final int WINDOW_WIDTH = 600;
    static final int SIZE_OF_ELEMENTS = 20; // Rozmiar elementow / jednostek
    static final int MAX_ELEMENTS = (WINDOW_WIDTH * WINDOW_HEIGHT) / (SIZE_OF_ELEMENTS * SIZE_OF_ELEMENTS); // Maksymalna ilosc elementow / jednostek na ekranie
    static final int TIMER_DELAY = 150; // Opoznienie timera, szybkosc gry - im mniejsza wartosc, tym szybsza gra
    String GAME_OVER_MESSAGE = "KONIEC GRY"; // Napis wyswietlany po przegranej
    int score;
    Apple apple = new Apple();
    Snake snake = new Snake();
    Timer timerClock = new Timer(TIMER_DELAY, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (snake.isMoving) { // Jezeli powinien sie poruszac ...
                snake.move(); // ... ruszaj
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
                    case KeyEvent.VK_UP: // ... w gore, to zmien aktualny kierunek ruchu weza do gory
                            if (snake.currentDirection != Snake.Direction.DOWN) // Zapobiega przerwaniu gry kiedy zmieni sie kierunek na przeciwny
                                snake.currentDirection = Snake.Direction.UP;
                            break;
                    case KeyEvent.VK_DOWN: // ... w dol, to na dol
                            if (snake.currentDirection != Snake.Direction.UP)
                                snake.currentDirection = Snake.Direction.DOWN;
                            break;
                    case KeyEvent.VK_LEFT: // ... w lewo, to na lewo
                            if (snake.currentDirection != Snake.Direction.RIGHT)
                                snake.currentDirection = Snake.Direction.LEFT;
                            break;
                    case KeyEvent.VK_RIGHT: // ... w prawo, to na prawo
                            if (snake.currentDirection != Snake.Direction.LEFT)
                                snake.currentDirection = Snake.Direction.RIGHT;
                            break;
                    case KeyEvent.VK_R: // ... a jesli wcisnieto R to zrestartuj gre
                            newGame();
                            break;
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
        snake.size = 5;
        snake.isMoving = true;
        apple.createNew();
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (snake.isMoving) { // Jezeli ma sie poruszac to ...
            g.setColor(Color.black); // Kolor jablka
            g.fillOval(apple.posX, apple.posY, SIZE_OF_ELEMENTS, SIZE_OF_ELEMENTS); // Narysuj jablko

            for (int i = 0; i < snake.size; i++) {
                if (i == 0) { // x[0], y[0] - glowa weza
                    g.setColor(Color.black); // Kolor glowy weza
                    g.fillRect(snake.x[i], snake.y[i], SIZE_OF_ELEMENTS, SIZE_OF_ELEMENTS); // Rysuj glowe weza
                } else {
                    g.setColor(Color.darkGray); // Kolor reszty weza
                    g.fillRect(snake.x[i], snake.y[i], SIZE_OF_ELEMENTS, SIZE_OF_ELEMENTS); // Rysuje reszte weza
                }
            }

            g.setColor(Color.black); // Kolor wyniku
            g.setFont(new Font("Arial", Font.BOLD, 30)); // Rodzaj czcionki wyniku
            g.drawString("Wynik: " + score, 10, 30); // Rysuj wynik
        } else {
            gameOver(g); // ... a jesli sie nie rusza to koniec
        }
    }

    public void isThereCollision() { // Obsluga kolizji
        for (int i = snake.size; i > 0; i--) {
            if ((snake.x[i] == snake.x[0]) && (snake.y[i] == snake.y[0])) { // Sprawdzenie czy nastapila kolizja glowy z cialem weza ...
                snake.isMoving = false; // ... jesli tak, ma sie zatrzymac
                break;
            }
        }

        if (snake.x[0] < 0) // Sprawdzenie kolizji z lewa krawedzia
            snake.isMoving = false;
        if (snake.x[0] > WINDOW_WIDTH) // ... z prawa
            snake.isMoving = false;
        if (snake.y[0] < 0) // ... z gorna
            snake.isMoving = false;
        if (snake.y[0] > WINDOW_HEIGHT) // ... i z dolna krawedzia
            snake.isMoving = false;
    }

    public void isAppleEaten() { // Obsluga zjedzenia jablka
        if ((apple.posX == snake.x[0]) && (apple.posY == snake.y[0])) { // Jesli glowa weza i jablko znajda sie w tym samym miejscu ...
            score++; // ... zwieksz wynik
            snake.size++; // ... powieksz weza
            apple.createNew(); // ... i stworz nowe jablko
        }
    }
}
