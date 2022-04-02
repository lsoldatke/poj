public class TrojkatRownoboczny extends TrojkatRownoramienny {


    public TrojkatRownoboczny(double podstawa, double podstawa1) {
        super();
    }

    public TrojkatRownoboczny(int podstawa) {
        super();
    }

    public double wysokosc() {
        return Math.sqrt((ramie * ramie) - (podstawa * podstawa / 4.));
    }
}
