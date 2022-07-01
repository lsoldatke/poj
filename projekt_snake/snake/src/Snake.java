public class Snake {
    int size;
    int[] x = new int[Panel.MAX_ELEMENTS]; // Wspolrzedne x weza w tablicy o rozmiarze rownym maksymalnej ilosci elementow / jednostek na ekranie
    int[] y = new int[Panel.MAX_ELEMENTS]; // Wspolrzedne y weza w tablicy o rozmiarze rownym maksymalnej ilosci elementow / jednostek na ekranie
    boolean isMoving = false; // Czy waz powinien sie poruszac
    enum Direction {UP, DOWN, LEFT, RIGHT} // Kierunki w ktorych waz moze sie poruszac
    Direction currentDirection = Direction.RIGHT; // Aktualny kierunek weza

    public void move() {
        for (int i = size; i > 0; i--) { // Zmien polozenie poszczegolnych elementow weza poprzez przypisanie im pozycji x, y poprzedniego elementu
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (currentDirection) { // Zmiana kierunku ruchu weza poprzez dodanie lub odjecie od koordynatow jego glowy rozmiaru pojedynczego elementu
            case UP -> // Gora
                    y[0] = y[0] - Panel.SIZE_OF_ELEMENTS;
            case DOWN -> // Dol
                    y[0] = y[0] + Panel.SIZE_OF_ELEMENTS;
            case LEFT -> // Lewo
                    x[0] = x[0] - Panel.SIZE_OF_ELEMENTS;
            case RIGHT -> // Prawo
                    x[0] = x[0] + Panel.SIZE_OF_ELEMENTS;
        }
    }
}
