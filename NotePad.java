import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.font.TextAttribute;

public class NotePad extends JFrame implements ActionListener, WindowListener {
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    Map<JScrollPane, File> fileMap = new HashMap<>();
    JTextArea textArea;

    public NotePad() {
        Font fnt = new Font("Arial", Font.PLAIN, 15);
        Container con = getContentPane();

        JMenuBar jmb = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        JMenu jmEdit = new JMenu("Edit");
        JMenu jmHelp = new JMenu("Help");
        JMenu jmAlgorithms = new JMenu("Algorithms");

        con.setLayout(new BorderLayout());

        tabbedPane.setFont(fnt);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        JPanel tabbedPanePanel = new JPanel(new BorderLayout());
        tabbedPanePanel.setPreferredSize(new Dimension(tabbedPanePanel.getPreferredSize().width, 25));
        tabbedPanePanel.add(tabbedPane, BorderLayout.CENTER);

        con.add(tabbedPanePanel, BorderLayout.CENTER);

        createMenuItem(jmFile, "New");
        createMenuItem(jmFile, "Open");
        createMenuItem(jmFile, "Save");
        jmFile.addSeparator();
        createMenuItem(jmFile, "Exit");

        createMenuItem(jmEdit, "Copy");
        createMenuItem(jmEdit, "Paste");
        createMenuItem(jmEdit, "Cut");
        jmEdit.addSeparator();
        createMenuItem(jmEdit, "Bold");
        createMenuItem(jmEdit, "Italic");
        createMenuItem(jmEdit, "Underline");
        createMenuItem(jmEdit, "Align Left");
        createMenuItem(jmEdit, "Align Center");
        createMenuItem(jmEdit, "Align Right");
        createMenuItem(jmEdit, "Remove Extra Spaces"); // New menu item

        createMenuItem(jmHelp, "About Notepad");

        createMenuItem(jmAlgorithms, "Sort Numbers");
        createMenuItem(jmAlgorithms, "Count Words");
        createMenuItem(jmAlgorithms, "Find Duplicates");
        createMenuItem(jmAlgorithms, "Word Frequency");
        createMenuItem(jmAlgorithms, "Check Palindrome");
        createMenuItem(jmAlgorithms, "String Search");
        createMenuItem(jmAlgorithms, "Reverse String");

        jmb.add(jmFile);
        jmb.add(jmEdit);
        jmb.add(jmAlgorithms);
        jmb.add(jmHelp);

        setJMenuBar(jmb);
        setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
        addWindowListener(this);
        setSize(800, 600);
        setTitle("Untitled.txt - Notepad");

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        con.add(scrollPane, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createToolBar(), BorderLayout.CENTER);
        con.add(topPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    public JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        addButton(toolBar, "New", "Add File", "New File");
        addButton(toolBar, "Sort Numbers", "Sort Numbers", "Sort Numbers");
        addButton(toolBar, "Count Words", "Count Words", "Count Words");
        addButton(toolBar, "Find Duplicates", "Find Duplicate", "Find Duplicates");
        addButton(toolBar, "Word Frequency", "Word Frequency", "Word Frequency");
        addButton(toolBar, "Check Palindrome", "Check Palindrome", "Check Palindrome");
        addButton(toolBar, "Reverse String", "Reverse String", "Reverse String");
        addButton(toolBar, "String Search", "String Search", "String Search");    
        addButton(toolBar, "Remove Extra Spaces", "Remove Extra Spaces", "Remove Extra Spaces"); // New button

        return toolBar;
    }

    public void addButton(JToolBar toolBar, String actionCommand, String label, String toolTipText) {
        JButton button = new JButton(label);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        button.setToolTipText(toolTipText);
        toolBar.add(button);
    }

    public void createMenuItem(JMenu jm, String txt) {
        JMenuItem jmi = new JMenuItem(txt);
        jmi.addActionListener(this);
        jm.add(jmi);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("New")) {
            newFile("Untitled");
        } else if (command.equals("Open")) {
            openFile();
        } else if (command.equals("Save")) {
            saveFile();
        } else if (command.equals("Exit")) {
            exitApp();
        } else if (command.equals("Copy")) {
            textArea.copy();
        } else if (command.equals("Paste")) {
            textArea.paste();
        } else if (command.equals("Cut")) {
            textArea.cut();
        } else if (command.equals("About Notepad")) {
            JOptionPane.showMessageDialog(this, "Created by: Pankaj Kumar Bind", "Notepad", JOptionPane.INFORMATION_MESSAGE);
        } else if (command.equals("Sort Numbers")) {
            sortNumbers();
        } else if (command.equals("Reverse Text")) {
            reverseText();
        } else if (command.equals("Count Words")) {
            countWords();
        } else if (command.equals("Find Duplicates")) {
            findDuplicates();
        } else if (command.equals("Word Frequency")) {
            wordFrequency();
        } else if (command.equals("Reverse String")) {
            reverseString();
        } else if (command.equals("Check Palindrome")) {
            checkPalindrome();
        } else if (command.equals("String Search")) {
            stringSearch();
        } else if (command.equals("Remove Extra Spaces")) { // Handle remove extra spaces button
            removeExtraSpaces();
        } else if (command.equals("Bold")) {
            Font currentFont = textArea.getFont();
            textArea.setFont(currentFont.deriveFont(Font.BOLD));
        } else if (command.equals("Italic")) {
            Font currentFont = textArea.getFont();
            textArea.setFont(currentFont.deriveFont(Font.ITALIC));
        } else if (command.equals("Underline")) {
            Font currentFont = textArea.getFont();
            Map<TextAttribute, Object> attributes = new HashMap<>(currentFont.getAttributes());
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            textArea.setFont(currentFont.deriveFont(attributes));
        } else if (command.equals("Align Left")) {
            textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        } else if (command.equals("Align Center")) {
            textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        } else if (command.equals("Align Right")) {
            textArea.setAlignmentX(Component.RIGHT_ALIGNMENT);
        }
    }

    public void removeExtraSpaces() {
        JTextArea currentTextArea = getCurrentTextArea();
        String text = currentTextArea.getText();
        text = text.replaceAll("\\s+", " ").trim(); // Replace multiple spaces with a single space
        currentTextArea.setText(text);
    }
    public void openFile() {
        JFileChooser jfc = new JFileChooser();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                JTextArea newTextArea = new JTextArea();
                newTextArea.read(reader, null);
                reader.close();
                JScrollPane newScrollPane = new JScrollPane(newTextArea);
                tabbedPane.addTab(selectedFile.getName(), newScrollPane);
                tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new ButtonTabComponent(tabbedPane));
                fileMap.put(newScrollPane, selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFile() {
        JTextArea currentTextArea = getCurrentTextArea();
        JScrollPane selectedTab = (JScrollPane) tabbedPane.getSelectedComponent();
        File file = fileMap.get(selectedTab);
        if (file == null) {
            JFileChooser jfc = new JFileChooser();
            int returnValue = jfc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                file = jfc.getSelectedFile();
                setTitle(file.getName() + " - Notepad");
                fileMap.put(selectedTab, file);
            } else {
                return;
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            currentTextArea.write(writer);
            writer.close();
            int selectedIndex = tabbedPane.getSelectedIndex();
            tabbedPane.setTitleAt(selectedIndex, file.getName()); // Update tab title
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newFile(String fileName) {
        JTextArea newTextArea = new JTextArea();
        newTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        newTextArea.setMargin(new Insets(5, 5, 5, 5)); // Adding margins for better appearance
        JScrollPane newScrollPane = new JScrollPane(newTextArea);
        newScrollPane.setPreferredSize(new Dimension(newScrollPane.getPreferredSize().width, 100)); // Decrease the height here
        tabbedPane.addTab(fileName, newScrollPane);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new ButtonTabComponent(tabbedPane));
    }

    public void exitApp() {
        System.exit(0);
    }

    public void sortNumbers() {
        JTextArea currentTextArea = getCurrentTextArea();
        String text = currentTextArea.getText();
        String[] lines = text.split("\n");
        StringBuilder sortedText = new StringBuilder();

        for (String line : lines) {
            String[] numbers = line.trim().split("\\s+");
            int[] intNumbers = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                try {
                    intNumbers[i] = Integer.parseInt(numbers[i]);
                } catch (NumberFormatException ex) {
                    // Skip non-numeric values
                }
            }
            mergeSort(intNumbers, 0, intNumbers.length - 1);
            for (int num : intNumbers) {
                sortedText.append(num).append(" ");
            }
            sortedText.append("\n");
        }

        currentTextArea.setText(sortedText.toString());
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; ++i) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArr[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public void reverseText() {
        JTextArea currentTextArea = getCurrentTextArea();
        String text = currentTextArea.getText();
        StringBuilder reversedText = new StringBuilder(text).reverse();
        currentTextArea.setText(reversedText.toString());
    }

    public void countWords() {
        JTextArea currentTextArea = getCurrentTextArea();
        String text = currentTextArea.getText();
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        JOptionPane.showMessageDialog(this, "Word count: " + wordCount);
    }

    public void findDuplicates() {
        JTextArea currentTextArea = getCurrentTextArea();
        String text = currentTextArea.getText();
        String[] words = text.split("\\s+");
        Set<String> duplicates = new HashSet<>();
        Set<String> seen = new HashSet<>();

        for (String word : words) {
            if (!seen.add(word)) {
                duplicates.add(word);
            }
        }

        if (duplicates.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No duplicates found.");
        } else {
            JOptionPane.showMessageDialog(this, "Duplicates: " + duplicates);
        }
    }

    public void wordFrequency() {
        JTextArea currentTextArea = getCurrentTextArea();
        String text = currentTextArea.getText();
        String[] words = text.split("\\s+");
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        StringBuilder frequencyOutput = new StringBuilder();
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            frequencyOutput.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        JOptionPane.showMessageDialog(this, frequencyOutput.toString());
    }

    public void reverseString() {
        JTextArea currentTextArea = getCurrentTextArea();
        String text = currentTextArea.getText();
        String reversedString = new StringBuilder(text).reverse().toString();
        currentTextArea.setText(reversedString);
    }

    public void checkPalindrome() {
        JTextArea currentTextArea = getCurrentTextArea();
        String text = currentTextArea.getText();
        String[] lines = text.split("\n");
        StringBuilder palindromes = new StringBuilder();

        for (String line : lines) {
            String cleanLine = line.replaceAll("[^a-zA-Z]", "").toLowerCase();
            String reversedLine = new StringBuilder(cleanLine).reverse().toString();
            if (cleanLine.equals(reversedLine)) {
                palindromes.append(line).append("\n");
            }
        }

        if (palindromes.length() > 0) {
            JOptionPane.showMessageDialog(this, "Palindromes found:\n" + palindromes.toString());
        } else {
            JOptionPane.showMessageDialog(this, "No palindromes found.");
        }
    }

    public void stringSearch() {
        JTextArea currentTextArea = getCurrentTextArea();
        String text = currentTextArea.getText();
        String searchWord = JOptionPane.showInputDialog(this, "Enter the word to search for:");
        if (searchWord != null && !searchWord.isEmpty()) {
            int index = text.indexOf(searchWord);
            if (index != -1) {
                JOptionPane.showMessageDialog(this, "The word '" + searchWord + "' is found at index " + index);
            } else {
                JOptionPane.showMessageDialog(this, "The word '" + searchWord + "' is not found.");
            }
        }
    }

    public JTextArea getCurrentTextArea() {
        int index = tabbedPane.getSelectedIndex();
        JScrollPane selectedTab = (JScrollPane) tabbedPane.getComponentAt(index);
        return (JTextArea) selectedTab.getViewport().getView();
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        exitApp();
    }

    public void windowOpened(WindowEvent e) {
    }

    public static void main(String[] args) {
        new NotePad();
    }
}

class ButtonTabComponent extends JPanel {
    private final JTabbedPane pane;

    public ButtonTabComponent(final JTabbedPane pane) {
        // unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        if (pane == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        this.pane = pane;
        setOpaque(false);

        // make JLabel read titles from JTabbedPane
        JLabel label = new JLabel() {
            public String getText() {
                int i = pane.indexOfTabComponent(ButtonTabComponent.this);
                if (i != -1) {
                    return pane.getTitleAt(i);
                }
                return null;
            }
        };

        // Add empty border around the label to create margin
        label.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4)); // Adjust the margins as needed

        add(label);
        // add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        // tab button
        JButton button = new TabButton();
        add(button);
        // add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

    private class TabButton extends JButton implements ActionListener {
        public TabButton() {
            int size = 17;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("Close this tab");
            // Make the button looks the same for all Laf's
            setUI(new BasicButtonUI());
            // Make it transparent
            setContentAreaFilled(false);
            // No need to be focusable
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            // Making nice rollover effect
            // we use the same listener for all buttons
            addMouseListener(buttonMouseListener);
            setRolloverEnabled(true);
            // Close the proper tab by clicking the button
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            int i = pane.indexOfTabComponent(ButtonTabComponent.this);
            if (i != -1) {
                pane.remove(i);
            }
        }

        // we don't want to update UI for this button
        public void updateUI() {
        }

        // paint the cross
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            // shift the image for pressed buttons
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            if (getModel().isRollover()) {
                g2.setColor(Color.RED);
            }
            int delta = 6;
            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            g2.dispose();
        }
    }

    private final static MouseListener buttonMouseListener = new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };
}
