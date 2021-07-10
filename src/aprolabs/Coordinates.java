package aprolabs;

public class Coordinates {
    private float xP1;
    private float yP1;
    private float xP2;
    private float yP2;

    public Coordinates(float xP1, float yP1, float xP2, float yP2) {
        this.xP1 = xP1;
        this.yP1 = yP1;
        this.xP2 = xP2;
        this.yP2 = yP2;
    }

    public float getxP1() {
        return xP1;
    }

    public float getyP1() {
        return yP1;
    }

    public float getxP2() {
        return xP2;
    }

    public float getyP2() {
        return yP2;
    }
}
