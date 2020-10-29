package m3.uf5.navegador;

import java.util.Scanner;

public class ProvaNavegador {
    private Navegador navegador = new Navegador();

    public static void main(String[] args) {

        ProvaNavegador main = new ProvaNavegador();
        boolean active = true;
        while (active) {
            active = main.menu();
        }

    }

    private boolean menu() {
        //Navegador navegador = new Navegador();

        String menu = "Escull opció: \n [1] Anar a un lloc web \n [2] Tornar enrere \n [3] Tornar endavant \n " +
                "[4] Veure historial \n [5] Veure les més visitades \n [0] Finalitzar programa\n";
        int opcio = inputInt(menu);
        switch (opcio) {
            case 1:
                navegador.anarA(inputStr("Introdueix web: "));
                comprovarActual();
                return true;
            case 2:
                navegador.enrere();
                comprovarActual();
                return true;
            case 3:
                navegador.endavant();
                comprovarActual();
                return true;
            case 4:
                navegador.veureHistorial();
                return true;
            case 5:
            case 0:
                System.out.println("Finalitzant navegador...");
                return false;
            default:
                System.out.println("Opció no vàlida");
        }
        return true;
    }

    private void comprovarActual() {
        try {
            System.out.println("\nAra ets a: " +navegador.pila.peek()+"\n");
        } catch (PilaBuidaException pb) {
        }
    }

    private String inputStr(String message) {
        Scanner reader = new Scanner(System.in);
        System.out.print(message);
        return reader.nextLine();
    }

    private int inputInt(String message) {
        Scanner reader = new Scanner(System.in);
        System.out.print(message);

        int num = 0;
        boolean isNum;
        do {
            try {
                num = reader.nextInt();
                isNum = true;
            } catch (Exception e) {
                System.out.print(message);
                isNum = false;
            }
            reader.nextLine();
        }
        while (!isNum);

        return num;
    }


}
