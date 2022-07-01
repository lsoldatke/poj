import java.util.Random;

public class Apple {
    int posX;
    int posY;

    public void createNew() { // Wylosuj x, y gdzie ma sie pojawic jablko (z zakresu szerokosci i wysokosci okna)
        Random random = new Random();
        posX = random.nextInt(Panel.WINDOW_WIDTH / Panel.SIZE_OF_ELEMENTS) * Panel.SIZE_OF_ELEMENTS;
        posY = random.nextInt(Panel.WINDOW_HEIGHT / Panel.SIZE_OF_ELEMENTS) * Panel.SIZE_OF_ELEMENTS;
    }
}
