package vv.tp3;

/**
 * Created by Thomas & Amona on 08/10/14.
 */
public class Examples {


    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println("Nested for loops.");
            }
        }

        int i=0;

        while(i<5) {
            System.out.println("while loop.");
            i++;
        }

        while(true) {
            System.out.println("while(true) + break");
            break;
        }

        while(true) {
            System.out.println("while(true) !");
        }
    }

}
