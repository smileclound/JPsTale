package org.pstale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.pstale.app.FieldApp;

import com.jme3.system.AppSettings;

public final class Main extends JFrame {

    private static final Logger logger = Logger.getLogger(Main.class);
    private static final long serialVersionUID = 1L;

    private static final String TITLE = "精灵区域管理器";

    /**
     * 服务端的路径
     */
    static String SERVER_ROOT;
    /**
     * 客户端的路径
     */
    static String CLIENT_ROOT;

    // Resource bundle for i18n.
    ResourceBundle resourceBundle = ResourceBundle.getBundle("org.pstale.launcher/SettingsDialog");

    // connection to properties file.
    private final AppSettings source;

    // 显示模式
    private DisplayMode[] modes = null;
    private static final DisplayMode[] windowDefaults = new DisplayMode[] { new DisplayMode(1024, 768, 24, 60),
            new DisplayMode(1280, 720, 24, 60), new DisplayMode(1280, 1024, 24, 60), new DisplayMode(1440, 900, 24, 60),
            new DisplayMode(1680, 1050, 24, 60), };
    private DisplayMode[] windowModes = null;

    // UI components
    private JCheckBox vsyncBox = null;
    private JCheckBox gammaBox = null;
    private JCheckBox fullscreenBox = null;
    private JComboBox<String> displayResCombo = null;
    private JComboBox<String> colorDepthCombo = null;
    private JComboBox<String> displayFreqCombo = null;
    private JComboBox<String> antialiasCombo = null;
    private JLabel icon = null;

    private JCheckBox lightBox = null;
    private JCheckBox serverBox = null;
    private JTextField serverRootTxt = null;
    private JTextField clientRootTxt = null;

    // 程序允许的最低分辨率
    private int minWidth = 800;
    private int minHeight = 600;

    /**
     * 初始化启动器
     */
    public Main() {

        source = new AppSettings(true);
        source.setTitle(TITLE);
        source.setMinWidth(minWidth);
        source.setMinHeight(minHeight);

        setResizable(false);

        /**
         * 读取注册表
         */
        AppSettings registrySettings = new AppSettings(true);
        try {
            registrySettings.load(TITLE);
            source.copyFrom(registrySettings);
        } catch (BackingStoreException ex) {
            logger.warn("Failed to load settings", ex);
        }

        /**
         * 检测屏幕分辨率
         */
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        modes = device.getDisplayModes();
        Arrays.sort(modes, new DisplayModeSorter());

        DisplayMode[] merged = new DisplayMode[modes.length + windowDefaults.length];

        int wdIndex = 0;
        int dmIndex = 0;
        int mergedIndex;

        for (mergedIndex = 0; mergedIndex < merged.length
                && (wdIndex < windowDefaults.length || dmIndex < modes.length); mergedIndex++) {

            if (dmIndex >= modes.length) {
                merged[mergedIndex] = windowDefaults[wdIndex++];
            } else if (wdIndex >= windowDefaults.length) {
                merged[mergedIndex] = modes[dmIndex++];
            } else if (modes[dmIndex].getWidth() < windowDefaults[wdIndex].getWidth()) {
                merged[mergedIndex] = modes[dmIndex++];
            } else if (modes[dmIndex].getWidth() == windowDefaults[wdIndex].getWidth()) {
                if (modes[dmIndex].getHeight() < windowDefaults[wdIndex].getHeight()) {
                    merged[mergedIndex] = modes[dmIndex++];
                } else if (modes[dmIndex].getHeight() == windowDefaults[wdIndex].getHeight()) {
                    merged[mergedIndex] = modes[dmIndex++];
                    wdIndex++;
                } else {
                    merged[mergedIndex] = windowDefaults[wdIndex++];
                }
            } else {
                merged[mergedIndex] = windowDefaults[wdIndex++];
            }
        }

        if (merged.length == mergedIndex) {
            windowModes = merged;
        } else {
            windowModes = Arrays.copyOfRange(merged, 0, mergedIndex);
        }

        createUI();
    }

    /**
     * <code>showDialog</code> sets this dialog as visble, and brings it to the
     * front.
     */
    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
        toFront();
    }

    /**
     * 界面布局
     */
    private void createUI() {
        GridBagConstraints gbc;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            logger.warn("Could not set native look and feel.");
        }

        JPanel pathPanel = new JPanel(new GridBagLayout());
        pathPanel.setBorder(BorderFactory.createTitledBorder("资源路径"));

        lightBox = new JCheckBox(resourceBundle.getString("checkbox.light"));
        lightBox.setSelected(source.getBoolean("UseLight"));

        serverBox = new JCheckBox(resourceBundle.getString("checkbox.server"));
        serverBox.setSelected(source.getBoolean("CheckServer"));

        SERVER_ROOT = source.getString("ServerRoot");
        if (SERVER_ROOT == null) {
            SERVER_ROOT = "";
        }

        serverRootTxt = new JTextField(50);
        serverRootTxt.setEditable(false);
        serverRootTxt.setText(SERVER_ROOT);
        if (checkServerRoot(SERVER_ROOT)) {
            serverRootTxt.setBackground(new Color(0.8f, 1f, 0.8f));
        } else {
            serverRootTxt.setBackground(new Color(1f, 0.8f, 0.8f));
        }

        CLIENT_ROOT = source.getString("ClientRoot");
        if (CLIENT_ROOT == null)
            CLIENT_ROOT = "";

        clientRootTxt = new JTextField(50);
        clientRootTxt.setEditable(false);
        clientRootTxt.setText(CLIENT_ROOT);
        if (checkClientRoot(CLIENT_ROOT)) {
            clientRootTxt.setBackground(new Color(0.8f, 1f, 0.8f));
        } else {
            clientRootTxt.setBackground(Color.YELLOW);
        }
        JButton serverBtn = new JButton(resourceBundle.getString("button.browser"));
        serverBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupServer();
            }
        });
        JButton clientBtn = new JButton(resourceBundle.getString("button.browser"));
        clientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupClient();
            }
        });
        JButton decodeBtn = new JButton(resourceBundle.getString("button.decode"));
        decodeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkClientRoot(CLIENT_ROOT)) {
                    DecodeDialog dialog = new DecodeDialog(Main.this, CLIENT_ROOT);
                    dialog.setVisible(true);
                }
            }
        });
        JButton encodeBtn = new JButton(resourceBundle.getString("button.encode"));
        encodeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkClientRoot(CLIENT_ROOT)) {
                    EncodeDialog dialog = new EncodeDialog(Main.this, CLIENT_ROOT);
                    dialog.setVisible(true);
                }
            }
        });

        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.insets = new Insets(4, 16, 4, 4);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        pathPanel.add(new JLabel(resourceBundle.getString("label.serverroot")), gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        pathPanel.add(serverRootTxt, gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        pathPanel.add(serverBtn, gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.insets = new Insets(4, 16, 4, 4);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        pathPanel.add(new JLabel(resourceBundle.getString("label.clientroot")), gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        pathPanel.add(clientRootTxt, gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        pathPanel.add(clientBtn, gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        pathPanel.add(serverBox, gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pathPanel.add(lightBox, gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        pathPanel.add(decodeBtn, gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pathPanel.add(encodeBtn, gbc);

        this.getContentPane().add(pathPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("显示配置"));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setTitle(resourceBundle.getString("frame.title"));

        // The buttons...
        JButton ok = new JButton(resourceBundle.getString("button.ok"));
        JButton cancel = new JButton(resourceBundle.getString("button.cancel"));

        KeyListener aListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (verifyAndSaveCurrentSelection()) {
                        startApp();
                        dispose();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }
        };

        displayResCombo = setUpResolutionChooser();
        displayResCombo.addKeyListener(aListener);
        colorDepthCombo = new JComboBox<String>();
        colorDepthCombo.addKeyListener(aListener);
        displayFreqCombo = new JComboBox<String>();
        displayFreqCombo.addKeyListener(aListener);
        antialiasCombo = new JComboBox<String>();
        antialiasCombo.addKeyListener(aListener);
        fullscreenBox = new JCheckBox(resourceBundle.getString("checkbox.fullscreen"));
        fullscreenBox.setSelected(source.isFullscreen());
        fullscreenBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateResolutionChoices();
            }
        });
        vsyncBox = new JCheckBox(resourceBundle.getString("checkbox.vsync"));
        vsyncBox.setSelected(source.isVSync());

        gammaBox = new JCheckBox(resourceBundle.getString("checkbox.gamma"));
        gammaBox.setSelected(source.isGammaCorrection());

        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(fullscreenBox, gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        // gbc.insets = new Insets(4, 16, 0, 4);
        gbc.gridx = 2;
        // gbc.gridwidth = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(vsyncBox, gbc);

        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(gammaBox, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.5;
        mainPanel.add(new JLabel(resourceBundle.getString("label.resolutions")), gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(displayResCombo, gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 16, 4, 4);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel(resourceBundle.getString("label.colordepth")), gbc);
        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(colorDepthCombo, gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel(resourceBundle.getString("label.refresh")), gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(displayFreqCombo, gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 16, 4, 4);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel(resourceBundle.getString("label.antialias")), gbc);
        gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(antialiasCombo, gbc);

        this.getContentPane().add(mainPanel);

        JPanel southPanel = new JPanel(new GridBagLayout());

        // Set the button action listeners. Cancel disposes without saving, OK
        // saves.
        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (verifyAndSaveCurrentSelection()) {
                    startApp();
                    dispose();
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        southPanel.add(ok, gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 16, 4, 4);
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        southPanel.add(cancel, gbc);

        icon = new JLabel(new ImageIcon("docs/bgm.png"));
        if (icon != null) {
            gbc = new GridBagConstraints();
            gbc.gridwidth = 4;
            // mainPanel.add(icon, gbc);
        }

        this.getContentPane().add(southPanel, BorderLayout.SOUTH);

        pack();

        mainPanel.getRootPane().setDefaultButton(ok);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Fill in the combos once the window has opened so that the
                // insets can be read.
                // The assumption is made that the settings window and the
                // display window will have the
                // same insets as that is used to resize the
                // "full screen windowed" mode appropriately.
                updateResolutionChoices();
                if (source.getWidth() != 0 && source.getHeight() != 0) {
                    displayResCombo.setSelectedItem(source.getWidth() + " x " + source.getHeight());
                } else {
                    displayResCombo.setSelectedIndex(displayResCombo.getItemCount() - 1);
                }

                updateAntialiasChoices();
                colorDepthCombo.setSelectedItem(source.getBitsPerPixel() + " bpp");
            }
        });

    }

    /**
     * <code>verifyAndSaveCurrentSelection</code> first verifies that the
     * display mode is valid for this system, and then saves the current
     * selection as a properties.cfg file.
     * 
     * @return if the selection is valid
     */
    private boolean verifyAndSaveCurrentSelection() {

        if (checkClientRoot(CLIENT_ROOT)) {
            source.put("ClientRoot", CLIENT_ROOT);
        } else {
            JOptionPane.showMessageDialog(this, resourceBundle.getString("error.clientrootnull"), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        boolean checkServer = serverBox.isSelected();
        source.putBoolean("CheckServer", checkServer);

        boolean useLight = lightBox.isSelected();
        source.putBoolean("UseLight", useLight);

        String display = (String) displayResCombo.getSelectedItem();
        boolean fullscreen = fullscreenBox.isSelected();
        boolean vsync = vsyncBox.isSelected();
        boolean gamma = gammaBox.isSelected();

        int width = Integer.parseInt(display.substring(0, display.indexOf(" x ")));
        display = display.substring(display.indexOf(" x ") + 3);
        int height = Integer.parseInt(display);

        String depthString = (String) colorDepthCombo.getSelectedItem();
        int depth = -1;
        if (depthString.equals("???")) {
            depth = 0;
        } else {
            depth = Integer.parseInt(depthString.substring(0, depthString.indexOf(' ')));
        }

        String freqString = (String) displayFreqCombo.getSelectedItem();
        int freq = -1;
        if (fullscreen) {
            if (freqString.equals("???")) {
                freq = 0;
            } else {
                freq = Integer.parseInt(freqString.substring(0, freqString.indexOf(' ')));
            }
        }

        String aaString = (String) antialiasCombo.getSelectedItem();
        int multisample = -1;
        if (aaString.equals(resourceBundle.getString("antialias.disabled"))) {
            multisample = 0;
        } else {
            multisample = Integer.parseInt(aaString.substring(0, aaString.indexOf('x')));
        }

        boolean valid = false;

        // test valid display mode when going full screen
        if (!fullscreen) {
            valid = true;
        } else {
            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            valid = device.isFullScreenSupported();
        }

        if (valid) {
            // use the GameSettings class to save it.
            source.setTitle(TITLE);
            source.setWidth(width);
            source.setHeight(height);
            source.setBitsPerPixel(depth);
            source.setFrequency(freq);
            source.setFullscreen(fullscreen);
            source.setVSync(vsync);
            source.setGammaCorrection(gamma);
            // source.setRenderer(renderer);
            source.setSamples(multisample);

            try {
                source.save(TITLE);
            } catch (BackingStoreException ex) {
                logger.warn("Failed to save setting changes", ex);
            }

            if (checkServer) {
                if (checkServerRoot(SERVER_ROOT)) {
                    source.put("ServerRoot", SERVER_ROOT);
                } else {
                    int rVal = JOptionPane.showConfirmDialog(this, resourceBundle.getString("error.serverrootnull"),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return (rVal == JOptionPane.OK_OPTION);
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, resourceBundle.getString("error.unsupportedmode"), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return valid;
    }

    /**
     * <code>setUpChooser</code> retrieves all available display modes and
     * places them in a <code>JComboBox</code>. The resolution specified by
     * GameSettings is used as the default value.
     * 
     * @return the combo box of display modes.
     */
    private JComboBox<String> setUpResolutionChooser() {
        JComboBox<String> resolutionBox = new JComboBox<String>();
        resolutionBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDisplayChoices();
            }
        });
        return resolutionBox;
    }

    /**
     * <code>updateDisplayChoices</code> updates the available color depth and
     * display frequency options to match the currently selected resolution.
     */
    private void updateDisplayChoices() {
        if (!fullscreenBox.isSelected()) {
            // don't run this function when changing windowed settings
            return;
        }
        String resolution = (String) displayResCombo.getSelectedItem();
        String colorDepth = (String) colorDepthCombo.getSelectedItem();
        if (colorDepth == null) {
            colorDepth = source.getBitsPerPixel() + " bpp";
        }
        String displayFreq = (String) displayFreqCombo.getSelectedItem();
        if (displayFreq == null) {
            displayFreq = source.getFrequency() + " Hz";
        }

        // grab available depths
        String[] depths = getDepths(resolution, modes);
        colorDepthCombo.setModel(new DefaultComboBoxModel<String>(depths));
        colorDepthCombo.setSelectedItem(colorDepth);
        // grab available frequencies
        String[] freqs = getFrequencies(resolution, modes);
        displayFreqCombo.setModel(new DefaultComboBoxModel<String>(freqs));
        // Try to reset freq
        displayFreqCombo.setSelectedItem(displayFreq);

        if (!displayFreqCombo.getSelectedItem().equals(displayFreq)) {
            // Cannot find saved frequency in available frequencies.
            // Choose the closest one to 60 Hz.
            displayFreqCombo.setSelectedItem(getBestFrequency(resolution, modes));
        }
    }

    /**
     * 根据用户选择的全屏模式/窗口模式来修改分辨率选项。
     */
    private void updateResolutionChoices() {
        if (!fullscreenBox.isSelected()) {
            displayResCombo.setModel(new DefaultComboBoxModel<String>(getWindowedResolutions(windowModes)));
            if (displayResCombo.getItemCount() > 0) {
                displayResCombo.setSelectedIndex(displayResCombo.getItemCount() - 1);
            }
            colorDepthCombo.setModel(new DefaultComboBoxModel<String>(new String[] { "24 bpp", "16 bpp" }));
            displayFreqCombo.setModel(
                    new DefaultComboBoxModel<String>(new String[] { resourceBundle.getString("refresh.na") }));
            displayFreqCombo.setEnabled(false);
        } else {
            displayResCombo.setModel(
                    new DefaultComboBoxModel<String>(getResolutions(modes, Integer.MAX_VALUE, Integer.MAX_VALUE)));
            if (displayResCombo.getItemCount() > 0) {
                displayResCombo.setSelectedIndex(displayResCombo.getItemCount() - 1);
            }
            displayFreqCombo.setEnabled(true);
            updateDisplayChoices();
        }
    }

    /**
     * 更新抗锯齿选项
     */
    private void updateAntialiasChoices() {
        String[] choices = new String[] { resourceBundle.getString("antialias.disabled"), "2x", "4x", "6x", "8x",
                "16x" };
        antialiasCombo.setModel(new DefaultComboBoxModel<String>(choices));
        antialiasCombo.setSelectedItem(choices[Math.min(source.getSamples() / 2, 5)]);
    }

    /**
     * 返回不低于最小分辨率的所有分辨率。
     */
    private String[] getResolutions(DisplayMode[] modes, int heightLimit, int widthLimit) {
        Insets insets = getInsets();
        heightLimit -= insets.top + insets.bottom;
        widthLimit -= insets.left + insets.right;

        ArrayList<String> resolutions = new ArrayList<String>(modes.length);
        for (int i = 0; i < modes.length; i++) {
            int height = modes[i].getHeight();
            int width = modes[i].getWidth();
            if (width >= minWidth && height >= minHeight) {
                if (height >= heightLimit) {
                    height = heightLimit;
                }
                if (width >= widthLimit) {
                    width = widthLimit;
                }

                String res = width + " x " + height;
                if (!resolutions.contains(res)) {
                    resolutions.add(res);
                }
            }
        }

        String[] res = new String[resolutions.size()];
        resolutions.toArray(res);
        return res;
    }

    /**
     * 返回一组显示模式，要求分辨率不小于最低分辨率，且不超过屏幕最大分辨率。
     */
    private String[] getWindowedResolutions(DisplayMode[] modes) {
        int maxHeight = 0;
        int maxWidth = 0;

        for (int i = 0; i < modes.length; i++) {
            if (maxHeight < modes[i].getHeight()) {
                maxHeight = modes[i].getHeight();
            }
            if (maxWidth < modes[i].getWidth()) {
                maxWidth = modes[i].getWidth();
            }
        }

        return getResolutions(modes, maxHeight, maxWidth);
    }

    /**
     * 返回所有适用于给定分辨率的色深。
     */
    private static String[] getDepths(String resolution, DisplayMode[] modes) {
        ArrayList<String> depths = new ArrayList<String>(4);
        for (int i = 0; i < modes.length; i++) {
            // Filter out all bit depths lower than 16 - Java incorrectly
            // reports
            // them as valid depths though the monitor does not support them
            if (modes[i].getBitDepth() < 16 && modes[i].getBitDepth() > 0) {
                continue;
            }

            String res = modes[i].getWidth() + " x " + modes[i].getHeight();
            String depth = modes[i].getBitDepth() + " bpp";
            if (res.equals(resolution) && !depths.contains(depth)) {
                depths.add(depth);
            }
        }

        if (depths.size() == 1 && depths.contains("-1 bpp")) {
            // add some default depths, possible system is multi-depth
            // supporting
            depths.clear();
            depths.add("24 bpp");
        }

        String[] res = new String[depths.size()];
        depths.toArray(res);
        return res;
    }

    /**
     * 返回所有适用的屏幕刷新率。
     */
    private static String[] getFrequencies(String resolution, DisplayMode[] modes) {
        ArrayList<String> freqs = new ArrayList<String>(4);
        for (int i = 0; i < modes.length; i++) {
            String res = modes[i].getWidth() + " x " + modes[i].getHeight();
            String freq;
            if (modes[i].getRefreshRate() == DisplayMode.REFRESH_RATE_UNKNOWN) {
                freq = "???";
            } else {
                freq = modes[i].getRefreshRate() + " Hz";
            }

            if (res.equals(resolution) && !freqs.contains(freq)) {
                freqs.add(freq);
            }
        }

        String[] res = new String[freqs.size()];
        freqs.toArray(res);
        return res;
    }

    /**
     * Chooses the closest frequency to 60 Hz.
     * 
     * @param resolution
     * @param modes
     * @return
     */
    private static String getBestFrequency(String resolution, DisplayMode[] modes) {
        int closest = Integer.MAX_VALUE;
        int desired = 60;
        for (int i = 0; i < modes.length; i++) {
            String res = modes[i].getWidth() + " x " + modes[i].getHeight();
            int freq = modes[i].getRefreshRate();
            if (freq != DisplayMode.REFRESH_RATE_UNKNOWN && res.equals(resolution)) {
                if (Math.abs(freq - desired) < Math.abs(closest - desired)) {
                    closest = modes[i].getRefreshRate();
                }
            }
        }

        if (closest != Integer.MAX_VALUE) {
            return closest + " Hz";
        } else {
            return null;
        }
    }

    /**
     * Utility class for sorting <code>DisplayMode</code>s. Sorts by resolution,
     * then bit depth, and then finally refresh rate.
     */
    private class DisplayModeSorter implements Comparator<DisplayMode> {

        /**
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(DisplayMode a, DisplayMode b) {
            // Width
            if (a.getWidth() != b.getWidth()) {
                return (a.getWidth() > b.getWidth()) ? 1 : -1;
            }
            // Height
            if (a.getHeight() != b.getHeight()) {
                return (a.getHeight() > b.getHeight()) ? 1 : -1;
            }
            // Bit depth
            if (a.getBitDepth() != b.getBitDepth()) {
                return (a.getBitDepth() > b.getBitDepth()) ? 1 : -1;
            }
            // Refresh rate
            if (a.getRefreshRate() != b.getRefreshRate()) {
                return (a.getRefreshRate() > b.getRefreshRate()) ? 1 : -1;
            }
            // All fields are equal
            return 0;
        }
    }

    // 文件选择器
    private JFileChooser chooser;

    private JFileChooser getChooser() {
        if (chooser == null) {
            chooser = new JFileChooser();
            chooser.setDialogType(JFileChooser.OPEN_DIALOG);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setDialogTitle("请选择文件夹");
        }
        return chooser;
    }

    /**
     * 打开文件选择框，选择一个目录。若用户没有选择，返回null。
     * 
     * @return
     */
    private File getFile() {
        if (getChooser().showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        return chooser.getSelectedFile();
    }

    private void setupServer() {
        File file = getFile();
        if (file != null) {
            SERVER_ROOT = file.getAbsolutePath();
            SERVER_ROOT = SERVER_ROOT.replaceAll("\\\\", "/");

            serverRootTxt.setText(SERVER_ROOT);
            if (checkServerRoot(SERVER_ROOT)) {
                serverRootTxt.setBackground(new Color(0.8f, 1f, 0.8f));
                source.put("ServerRoot", SERVER_ROOT);
            } else {
                serverRootTxt.setBackground(Color.YELLOW);
            }
        }
    }

    private void setupClient() {
        File file = getFile();
        if (file != null) {
            CLIENT_ROOT = file.getAbsolutePath();
            CLIENT_ROOT = CLIENT_ROOT.replaceAll("\\\\", "/");
            clientRootTxt.setText(CLIENT_ROOT);
            if (checkClientRoot(CLIENT_ROOT)) {
                clientRootTxt.setBackground(new Color(0.8f, 1f, 0.8f));
                source.put("ClientRoot", CLIENT_ROOT);
            } else {
                clientRootTxt.setBackground(Color.YELLOW);
            }
        }
    }

    /**
     * 检查客户端文件夹是否都存在
     * 
     * @param folder
     * @return
     */
    private boolean checkClientRoot(String folder) {
        File file = new File(folder);
        if (!file.exists()) {
            return false;
        }

        String[] folders = { "effect", "field", "char", "wav", "sky", "image" };
        for (String subFolder : folders) {
            if (!new File(folder + "/" + subFolder).exists()) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检查服务端文件夹是否都存在
     * 
     * @param folder
     * @return
     */
    private boolean checkServerRoot(String folder) {
        File file = new File(folder);
        if (!file.exists()) {
            return false;
        }

        String[] folders = { "GameServer/Field", "GameServer/Monster", "GameServer/NPC", "GameServer/OpenItem" };
        for (String subFolder : folders) {
            if (!new File(folder + "/" + subFolder).exists()) {
                return false;
            }
        }
        return true;
    }

    private void startApp() {
        new Thread() {
            public void run() {
                final FieldApp app = new FieldApp();
                app.setSettings(source);
                app.setPauseOnLostFocus(false);
                app.setShowSettings(false);
                app.start();
            }
        }.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main dialog = new Main();
                dialog.showDialog();
            }
        });
    }
}