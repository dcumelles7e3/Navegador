package m3.uf5.navegador;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfície gràfica per utilitzar en la pràctica del Navegador Web
 *
 * @author Montse
 * @version 26/10/2020
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
    //1 fila i 3 columnes
    private JPanel topBar = new JPanel(new GridLayout(1, 3));

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

        loadURL(); //perquè carregui la primera URL, la pàgina d'inici
    }

    class ListenerGoTo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            loadURL();
            navi.anarA(txtURL.getText());
        }
    }

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

    class ListenerHistorial implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            navi.veureHistorial();
        }
    }

    class ListenerVisitades implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            navi.veureVisitades();
        }
    }

    private void loadURL() {
        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().load(txtURL.getText());
        });
    }
}
