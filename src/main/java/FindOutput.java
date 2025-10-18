public class FindOutput {

    public static void main(String[] args) {
        int i = 0;
        outer:
        while (i < 5) {
            i++;
            inner:
            for (int j = 0; j < 5; j++) {
                if (i+j < 5) {
                    break outer; // Continues to the next iteration of the outer loop
                }
                if(i == 3 && j == 2) {
                    continue inner; // Skips the current iteration of the inner loop

                }
                System.out.println(i * j + " ");
            }
        }
    }
}
