package m3.uf5.navegador;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Mostra la GUI i conté lògica dels botons
 * @version 08/11/2020
 * @author Dídac Cumelles Cenzano
 * @author Montse
 */
public class GUI extends JFrame {
    private String home = "http://www.itb.cat";
    private Navegador navi = new Navegador(home);

    private JFXPanel jfxPanel = new JFXPanel(); //per carregar les webs (urls)
    private JPanel panel = new JPanel(new BorderLayout());
    private JButton btnEnrere = new JButton("<<");
    private JButton btnEndavant = new JButton(">>");
    private JButton hist = new JButton("Historial");
    private JButton mesVisitades = new JButton("Mes Visitades");
    private JTextField txtURL = new JTextField(home);

    private JPanel topBar = new JPanel(new GridLayout(1, 5));

    /** Constructor. Carrega la pagina d'inici
     */
    public GUI() {
        setBounds(100, 100, 800, 600);
        setTitle("Un Navegador de prova");
        btnEndavant.addActionListener(new ListenerEndavant());
        btnEnrere.addActionListener(new ListenerEnrere());
        txtURL.addActionListener(new ListenerGoTo());
        hist.addActionListener(new ListenerHistorial());
        mesVisitades.addActionListener(new ListenerVisitades());

        topBar.add(txtURL);
        topBar.add(btnEnrere);
        topBar.add(btnEndavant);
        topBar.add(hist);
        topBar.add(mesVisitades);

        panel.add(topBar, BorderLayout.NORTH);
        panel.add(jfxPanel, BorderLayout.CENTER);

        add(panel); //al JFrame

        loadURL(); //perque carregui la primera URL, la pagina d'inici
    }

    /** Carrega una nova pagina
     */
    class ListenerGoTo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            navi.anarA(txtURL.getText());
            txtURL.setText(navi.getActual());
            loadURL();
        }
    }

    /** Crida a la funció enrere
     */
    class ListenerEnrere implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (!navi.pilaEnrere.empty()) {
                try {
                    navi.enrere();
                    txtURL.setText(navi.getActual());
                    loadURL();
                } catch (PilaBuidaException e) {
                    System.out.println("No hi ha anteriors.");
                }
            }
        }
    }

    /** Crida a la funció endavant
     */
    class ListenerEndavant implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (!navi.pilaEndavant.empty()) {
                try {

                    navi.endavant();
                    txtURL.setText(navi.getActual());
                    loadURL();
                } catch (PilaBuidaException e) {
                    System.out.println("No hi ha següents.");
                }
            }
        }
    }

    /** Mostra historial per consola
     */
    class ListenerHistorial implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            navi.veureHistorial();
        }
    }

    /** Mostra visitades per consola
     */
    class ListenerVisitades implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            navi.veureVisitades();
        }
    }

    /** Carrega la pàgina al frame
     */
    private void loadURL() {
        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().load(txtURL.getText());
        });
    }
}
