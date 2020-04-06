
/**
* File Name: FibonacciNim.java
* Purpose: Interfaces the game Fibonacci Nim
* Date: 2019-10-07
* Last modified: 2020-10-07
* Author: Josen Pottackal
* Copy right no copyright
* Version: 1.0
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class FibonacciNim {

    private static int heapsCount = 3;
    private static final int defaultHeapsSize = 9;
    private static int[] heaps;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        getHeapCountSelection();
        getHeapSizeSelection();

        int maximumTaken = 2;
        boolean player1 = true;


        while(FibonacciNim.getTotalHeapCount() > 0) {
            FibonacciNim.printHeaps();
            if (player1) {
                System.out.println("Player 1's turn.");
            } else {
                System.out.println("Player 2's turn.");
            }

            // heap logic
            boolean validHeap = false;
            int selectedHeap = 0;

            while(!validHeap) {
                System.out.printf("Choose a heap (1-%x): ", FibonacciNim.heaps.length);
                try {
                    selectedHeap = scanner.nextInt();

                    if ((selectedHeap >=1) && (selectedHeap <= FibonacciNim.heaps.length)) {
                        if (FibonacciNim.heaps[selectedHeap-1] == 0) {
                            System.out.printf("Can't pick heap %x as there is nothing on heap%n", selectedHeap);
                        } else {
                            validHeap = true;
                        }
                    }
                } catch (InputMismatchException exception) {
                    scanner.next();
                }
            }

            // heap count logic
            boolean validHeapCount = false;
            int selectedHeapCount = 0;

            while(!validHeapCount) {


                if (maximumTaken > FibonacciNim.heaps[selectedHeap-1]) {
                    System.out.println("The number of tokens you may take is between 1 and " + FibonacciNim.heaps[selectedHeap-1]);
                } else {
                    System.out.println("The number of tokens you may take is between 1 and " + maximumTaken);
                }


                try {
                    selectedHeapCount = scanner.nextInt();

                    if ((selectedHeapCount <= maximumTaken) && (selectedHeapCount > 0) && (selectedHeapCount <= FibonacciNim.heaps[selectedHeap-1])) {
                        FibonacciNim.heaps[selectedHeap-1] = FibonacciNim.heaps[selectedHeap-1] - selectedHeapCount;

                        if (maximumTaken < (selectedHeapCount * 2)) {
                            maximumTaken = selectedHeapCount * 2;
                        }
                        validHeapCount = true;
                    }
                } catch (InputMismatchException exception) {
                    scanner.next();
                }
            }
            player1 = !player1;

        }

        // print winner
        if (player1) {
            System.out.println("Player 2 wins");
        } else {
            System.out.println("Player 1 wins");
        }

        scanner.close();
    }

    // printing heaps
    private static void printHeaps() {
        for(int i=0;i<FibonacciNim.heaps.length; i++) {
            System.out.println("Heap "+ (i+1) +":"+ FibonacciNim.heaps[i]);
        }
    }

    // total heap count funtion
    private static int getTotalHeapCount() {
        int count = 0;
        for(int i=0;i<FibonacciNim.heaps.length; i++) {
            count += FibonacciNim.heaps[i];
        }
        return count;
    }

    private static void getHeapCountSelection() {
        // heaps count selection
        String heapsCountSelection = "";

        while(!heapsCountSelection.toLowerCase().equals("n") && !heapsCountSelection.toLowerCase().equals("y")) {
            System.out.print("Do you want to change to default number of heaps from 3? (Y/N): ");
            heapsCountSelection = scanner.next();


            boolean validHeapsCount = false;

            if (heapsCountSelection.toLowerCase().equals("y")) {
                while(!validHeapsCount) {
                    System.out.print("Please enter a number greater than 0: ");
                    try {
                        FibonacciNim.heapsCount = scanner.nextInt();

                        if (FibonacciNim.heapsCount > 0) {
                            validHeapsCount = true;
                        }
                    } catch (InputMismatchException exception) {
                        scanner.next();
                    }
                }
            }
        }

        FibonacciNim.heaps = new int[FibonacciNim.heapsCount];
    }

    private static void getHeapSizeSelection() {
        // heaps size selection
        String heapsSizeSelection = "";

        while(!heapsSizeSelection.toLowerCase().equals("n") && !heapsSizeSelection.toLowerCase().equals("y")) {
            System.out.print("Do you want to change to default heap size from 9? (Y/N): ");
            heapsSizeSelection = scanner.next();

            if (heapsSizeSelection.toLowerCase().equals("y")) {

                for(int i=0; i< FibonacciNim.heapsCount; i++) {

                    boolean validHeapsSize = false;

                    while(!validHeapsSize) {
                        System.out.print("Heap "+ (i+1) +": Please enter a number greater than or equal to 0: ");
                        try {
                            int heapSizeSelection = scanner.nextInt();

                            if (heapSizeSelection >= 0) {
                                validHeapsSize = true;
                                FibonacciNim.heaps[i] = heapSizeSelection;
                            }
                        } catch (InputMismatchException exception) {
                            scanner.next();
                        }
                    }

                }
            } else if (heapsSizeSelection.toLowerCase().equals("n")) {
                for(int i=0; i< FibonacciNim.heapsCount; i++) {
                    FibonacciNim.heaps[i] = FibonacciNim.defaultHeapsSize;
                }
            }
        }
    }
}
