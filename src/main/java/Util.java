public class Util {
    public static int[] randomGridLocation (int width, int height, int tileSize) {
        int x = (int) (Math.random() * (width / tileSize)) * tileSize;
        int y = (int) (Math.random() * (height/ tileSize)) * tileSize;
        return new int[] {x, y};
    }
}
