package ui;

import javax.swing.*;
import delegates.UserTransactionsDelegate;

import java.awt.*;

/*
 * Represents the panel in which the Window is displayed.
 * After a user logs in, should be populated with their account page.
 */
public class WindowPanel extends JPanel {
    private UserTransactionsDelegate delegate;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;

    public WindowPanel(UserTransactionsDelegate delegate) {
        this.delegate = delegate;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(240, 246, 246, 255));
    }
}
