package m3.uf5.navegador;

import javax.swing.*;
import java.util.Scanner;

/** Classe Main que crida mode GUI o consola i inclou les funcionalitats del mode consola
 * @version 08/11/2020
 * @author Dídac Cumelles Cenzano
 */
public class ProvaNavegador {
    private GUI gui;
    private Navegador navegador;

    /** Inicia amb selecció de mode de visualització
     * @param args es pot afegir per a iniciar amb selecció
     */
    public static void main(String[] args) {
        ProvaNavegador main = new ProvaNavegador();
        boolean active = true;
        while (active) {
            int opcio = main.inputInt("Escull mode de visualització:\n[1] Mode GUI\n[2] Mode Consola");
            switch (opcio) {
                case 1:
                    active = false;
                    System.out.println("Obrint GUI...");
                    main.gui();
                    break;
                case 2:
                    active = false;
                    main.consola();
                    break;
                default:
                    System.out.println("Introdueix 1 o 2.");
                    break;
            }
        }
    }

    /** Inicia mode GUI
     */
    private void gui() {
        gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }

    /** Instancia navegador al main i inicia mode consola
     */
    private void consola() {
        navegador = new Navegador("http://www.itb.cat");
        boolean active = true;
        comprovarActual();
        while (active) {
            try {
                active = menu();
            } catch (PilaBuidaException pb) {
                System.out.println("Pila Buida");
            }
        }
    }

    /** Mostra el menu
     * @return Retorna boolean si s'ha de seguir executant el menu o ha finalitzat
     * @throws PilaBuidaException enrere i endavant comproven piles
     */
    private boolean menu() throws PilaBuidaException {
        String menu = "Escull opció: \n [1] Anar a un lloc web \n [2] Tornar enrere \n [3] Anar endavant \n " +
                "[4] Veure historial \n [5] Veure les més visitades \n [0] Finalitzar programa\n";
        int opcio = inputInt(menu);
        switch (opcio) {
            case 1:
                navegador.anarA(inputStr("Introdueix web: "));
                return true;
            case 2:
                navegador.enrere();
                return true;
            case 3:
                navegador.endavant();
                return true;
            case 4:
                navegador.veureHistorial();
                return true;
            case 5:
                navegador.veureVisitades();
                return true;
            case 0:
                System.out.println("Finalitzant navegador...");
                return false;
            default:
                System.out.println("Opció no vàlida");
        }
        return true;
    }

    /** Imprimeix a consola la pàgina actual
     */
    private void comprovarActual() {
        System.out.println("\nAra ets a: " + navegador.getActual() + "\n");
    }

    /** Classe auxiliar per a introduir String
     * @param message Mostra aquest missatge per a demanar dades
     * @return String introduït
     */
    private String inputStr(String message) {
        Scanner reader = new Scanner(System.in);
        System.out.print(message);
        return reader.nextLine();
    }

    /** Classe auxiliar per a introduir int
     * @param message Mostra aquest missatge per a demanar dades
     * @return int introduït
     */
    private int inputInt(String message) {
        Scanner reader = new Scanner(System.in);
        System.out.println(message);
        int num = 0;
        boolean isNum;
        do {
            try {
                num = reader.nextInt();
                isNum = true;
            } catch (Exception e) {
                System.out.println(message);
                isNum = false;
            }
            reader.nextLine();
        } while (!isNum);
        return num;
    }
}
