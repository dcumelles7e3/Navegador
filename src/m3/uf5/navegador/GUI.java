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

    private Navegador navi = new Navegador("http://www.itb.cat");

    private JFXPanel jfxPanel = new JFXPanel(); //per carregar les webs (urls)
    private JPanel panel = new JPanel(new BorderLayout());
    private JButton btnEnrere = new JButton("<<");
    private JButton btnEndavant = new JButton(">>");
    private JTextField txtURL = new JTextField("http://www.itb.cat");
    //1 fila i 3 columnes
    private JPanel topBar = new JPanel(new GridLayout(1, 3));

    public GUI() {
        setBounds(100, 100, 800, 600);
        setTitle("Un Navegador de prova");
        btnEndavant.addActionListener(new ListenerEndavant());
        btnEnrere.addActionListener(new ListenerEnrere());
        txtURL.addActionListener(new ListenerGoTo());

        topBar.add(txtURL);
        topBar.add(btnEnrere);
        topBar.add(btnEndavant);

        panel.add(topBar, BorderLayout.NORTH);
        panel.add(jfxPanel, BorderLayout.CENTER);

        add(panel); //al JFrame

        loadURL(); //perquè carregui la primera URL, la pàgina d'inici
    }

    class ListenerGoTo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            loadURL();

        }
    }

    class ListenerEnrere implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                txtURL.setText(navi.enrere());
                loadURL();
            } catch (PilaBuidaException pb) {
                System.out.println("Pila buida");
            }
            //TODO
        }
    }

    class ListenerEndavant implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                txtURL.setText(navi.endavant());
                loadURL();
            } catch (PilaBuidaException pb) {
                System.out.println("Pila buida");
            }
            //TODO
        }
    }

    private void loadURL() {
        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().load(txtURL.getText());
            navi.anarA(txtURL.getText());
        });
    }


}
