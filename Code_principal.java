import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;




public class Code_principal {


    public static void main(String[] args) {
        int data2 = 0;
        double[][] P = null;
        int data1 = 0;
        int data3 = 0;
        int data4 = 0;
        int data5 = 0;
        Double p = Double.POSITIVE_INFINITY;
        int r = p.intValue();


        int[][] G = null;
        double[][] L = null;
        int i = 0;
        int j = 0;
        int data = 0;
        int n;
        String reponse;


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Entrez le numéro du graphe :");
            n = scanner.nextInt();
            try {
                File myObj = new File("graphe_" + n + ".txt");
                Scanner myReader = new Scanner(myObj);


                int d = myReader.nextInt();
                data = d;

                G = new int[data][data];
                L = new double[data][data];
                P = new double[data][data];
                for (i = 0; i < data; i++) {
                    for (j = 0; j < data; j++) {
                        G[i][j] = 0;
                        if (i == j) {
                            L[i][j] = 0;
                        } else {
                            L[i][j] = p;
                        }
                    }

                }


                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }


            try {
                File myObj = new File("graphe_" + n + ".txt");
                Scanner myReader = new Scanner(myObj);


                data1 = myReader.nextInt();
                data2 = myReader.nextInt();


                while (myReader.hasNextInt()) {
                    data3 = myReader.nextInt();
                    data4 = myReader.nextInt();
                    data5 = myReader.nextInt();


                    G[data3][data4] = 1;
                    L[data3][data4] = data5;


                }


                myReader.close();


            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            for (i = 0; i < data; i++) {
                for (j = 0; j < data; j++) {
                    if (i == j || L[i][j] == p) {

                        P[i][j] = p;
                    }
                    if (i != j && L[i][j] < p) {

                        P[i][j] = i;

                    }

                }
            }

            System.out.println("G =");

            for (i = 0; i < data; i++) {
                for (j = 0; j < data; j++) {
                    System.out.print(Double.toString(G[i][j]).substring(0, 3) + "     ");
                }
                System.out.println();
            }
            System.out.println("P = ");
            for (i = 0; i < data; i++) {
                for (j = 0; j < data; j++) {
                    System.out.print(Double.toString((int) P[i][j]).substring(0, 3) + "     ");
                }
                System.out.println();
            }
            System.out.println("L =");

            for (i = 0; i < data; i++) {
                for (j = 0; j < data; j++) {
                    System.out.print(Double.toString(L[i][j]).substring(0, 3) + "     ");
                }
                System.out.println();
            }
            System.out.println("Inf veut dire qu'on a pas d'arc");


            String vrai = null;
            for (int k = 0; k < data; k++) {
                for (i = 0; i < data; i++) {
                    for (j = 0; j < data; j++) {


                        if (L[i][k] + L[k][j] < L[i][j]) {
                            L[i][j] = L[i][k] + L[k][j];

                            P[i][j] = P[k][j];


                        }
                    }


                }
                System.out.println("k = " + k);
                System.out.println("L = ");
                for (i = 0; i < data; i++) {

                    //DETECTION DE CIRCUIT ABSORBANT
                    if (L[i][i] < 0) {
                        vrai = "CIRCUIT";
                    }
                    for (j = 0; j < data; j++) {


                        System.out.print(Double.toString(L[i][j]).substring(0, 3) + "     ");
                    }
                    System.out.println();
                }
                System.out.println();
                System.out.println("P = ");
                for (i = 0; i < data; i++) {
                    for (j = 0; j < data; j++) {

                        System.out.print(Double.toString(P[i][j]).substring(0, 3) + "     ");
                    }
                    System.out.println();
                }

            }
            if (vrai == "CIRCUIT") {
                System.out.println("Existence de circuit absorbant");
            }
            else{
                System.out.println("Absence de circuit absorbant");
                for (i = 0; i < data; i++) {
                    for (j = 0; j < data; j++) {
                        if (P[i][j] != p) {
                            System.out.println("Le Chemin le plus court de " + i +  " à "  + j + " est:");
                            System.out.println(i + "-" + P[i][j] + "-" + j);
                        }
                    }
                }

            }

            System.out.print("Autre graphe?o/n");
            reponse = scanner.next();

            if (reponse.charAt(0) == 'n') {
                break;
            }
        }
        System.out.println("Au revoir");
        scanner.close();
    }
}



