package com.gradle.with.fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    Label label;
    @FXML
    ScrollPane scrollPane;
    @FXML
    VBox vBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue((double) newValue));
        setLabelText();
        setNetworks();
    }

    private void setNetworks() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface networkInterface : Collections.list(interfaces)) {
                String text = String.join(", ", getInterfaceInformation(networkInterface));
                HBox hBox = new HBox();
                //hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 10));

                TextFlow textFlow = new TextFlow(new Text(text));
                //textFlow.setPadding(new Insets(5, 10, 5, 10));
                //hBox.getChildren().add(new Text(text));
                hBox.getChildren().add(textFlow);
                vBox.getChildren().add(hBox);
            }
        } catch (Exception e) {

        }
    }

    public void setLabelText() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");

        String text = String.format("Java version: %s - JavaFX version: %s", javaVersion, javafxVersion);

        label.setText(text);
    }

    static List getInterfaceInformation(NetworkInterface networkInterface) throws SocketException {
        List<String> list = new ArrayList<>();
        list.add(networkInterface.getDisplayName());
        list.add(networkInterface.getName());
        list.add(networkInterface.isUp() ? "Up" : "Down");
        list.add(networkInterface.isLoopback() ? "LB: Yes" : "LB: No");
        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
//            System.out.printf("InetAddress: %s\n", inetAddress);
            list.add(inetAddress.toString());
        }

        return list;
    }

    private void getName() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            // drop inactive
            if (!networkInterface.isUp()) {
                continue;
            }

            // smth we can explore
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                System.out.println(String.format("NetInterface: name [%s], ip [%s]", networkInterface.getDisplayName(), addr.getHostAddress()));
            }
        }
    }
}
