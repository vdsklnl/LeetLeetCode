package easy;

/**
 * @author vdsklnl
 * @create 2022-08-09 13:25
 * @description
 */
public class ImageRender {
    public static void main(String[] args) {
//        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
//        int sr = 1, sc = 1, newColor = 2;
        int[][] image = {{0,0,0},{0,0,0}};
        int sr = 0, sc = 0, newColor = 0;
        floodFill(image, sr, sc, newColor);
        for (int[] row:image) {
            for (int item:row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        fill(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    public static void fill(int[][] image, int sr, int sc, int color, int ima) {
        if (sr < 0 || sc < 0 || sr == image.length || sc == image[0].length
                || image[sr][sc] == color || image[sr][sc] != ima)
            return;
        else {
            image[sr][sc] = color;
            fill(image, sr, sc - 1, color, ima);
            fill(image, sr - 1, sc, color, ima);
            fill(image, sr, sc + 1, color, ima);
            fill(image, sr + 1, sc, color, ima);
        }
        return;
    }

}
