import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;

/**
 * Implements the ViewInterface.
 */
public class View extends JFrame implements ViewInterface {
  /**
   * The load button.
   */
  private JButton loadButton;
  /**
   * The applyFilterButton.
   */
  private JButton applyFilterButton;
  /**
   * The generateImageButton.
   */
  private JButton generateImageButton;
  /**
   * The rainbowButton.
   */
  private JButton rainbowButton;
  /**
   * The checkerboardButton.
   */
  private JButton checkerboardButton;
  /**
   * The batchCommandsButton.
   */
  private JButton batchCommandsButton;
  /**
   * The goButton.
   */
  private JButton goButton;
  /**
   * The saveButton.
   */
  private JButton saveButton;
  /**
   * The innerPanel.
   */
  private JPanel innerPanel;
  /**
   * The imageLabel.
   */
  private JLabel imageLabel;
  /**
   * The comboBox.
   */
  private JComboBox<String> comboBox;
  /**
   * The imageScrollPane.
   */
  private JScrollPane imageScrollPane;
  /**
   * The filterPanel.
   */
  private JPanel filterPanel;
  /**
   * The generatePanel.
   */
  private JPanel generatePanel;
  /**
   * The batchCommandPanel.
   */
  private JPanel batchCommandPanel;
  /**
   * The runBatch Button.
   */
  private JButton runBatch;
  /**
   * The helpButton.
   */
  private JButton helpButton;
  /**
   * The inputCommandTextArea.
   */
  private JTextArea inputComTextArea;

  /**
   * Constructor that creates the GUI.
   */
  public View() {
    super();

    setTitle("Image Filtering Application");
    //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    setSize(1500, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Setting up root pane layout
    this.setLayout(new BorderLayout());


    // North Region
    JPanel northRegion = new JPanel();
    northRegion.setLayout(new FlowLayout());
    loadButton = new JButton("Open");
    applyFilterButton = new JButton("Apply Filter");
    generateImageButton = new JButton("Generate Image");
    batchCommandsButton = new JButton("Batch Commands");
    saveButton = new JButton("Save");

    northRegion.add(loadButton);
    northRegion.add(applyFilterButton);
    northRegion.add(generateImageButton);
    northRegion.add(batchCommandsButton);
    northRegion.add(saveButton);
    northRegion.setVisible(true);
    this.add(northRegion, BorderLayout.NORTH);


    // East Region
    JPanel outerPanel = new JPanel();
    innerPanel = new JPanel();
    imageLabel = new JLabel();

    imageScrollPane = new JScrollPane(innerPanel);
    imageScrollPane.setPreferredSize(new Dimension(500, 500));
    imageScrollPane.setBorder(BorderFactory.createTitledBorder("Current Image"));
    imageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    innerPanel.add(imageLabel);
    innerPanel.setVisible(true);
    outerPanel.add(imageScrollPane);
    this.add(outerPanel, BorderLayout.EAST);

    JLabel imageLabel = new JLabel();
    innerPanel.add(imageLabel);
    innerPanel.setVisible(true); // If no image set to false

    this.add(outerPanel, BorderLayout.EAST);


    // Center Region

    // Batch Commands Panel Start
    batchCommandPanel = new JPanel();
    batchCommandPanel.setLayout(new BoxLayout(batchCommandPanel, BoxLayout.Y_AXIS));
    inputComTextArea = new JTextArea(25, 20);
    JPanel innerText = new JPanel();
    JScrollPane textScrollPane = new JScrollPane(inputComTextArea);
    innerText.add(textScrollPane);
    helpButton = new JButton("Help");
    batchCommandPanel.add(innerText);
    runBatch = new JButton("Run");
    batchCommandPanel.add(runBatch);
    batchCommandPanel.add(helpButton);
    batchCommandPanel.setBorder(BorderFactory.createTitledBorder("Input Batch Commands"));
    batchCommandPanel.setVisible(true);
    //this.add(batchCommandPanel, BorderLayout.WEST);

    // Generate Image panel start
    generatePanel = new JPanel();
    generatePanel.setLayout(new BoxLayout(generatePanel, BoxLayout.Y_AXIS));
    generatePanel.setBorder(BorderFactory.createTitledBorder("Generate an Image"));
    rainbowButton = new JButton("Rainbow");
    checkerboardButton = new JButton("Checkerboard");
    generatePanel.add(rainbowButton);
    generatePanel.add(checkerboardButton);
    generatePanel.setVisible(true);
    //this.add(generatePanel, BorderLayout.CENTER);


    // Apply Filter panel start
    filterPanel = new JPanel();
    filterPanel.setLayout(new FlowLayout());
    filterPanel.setBorder(BorderFactory.createTitledBorder("Select Filter"));

    // Dropdown (JComboBox)
    JPanel comboBoxPanel = new JPanel();
    String[] filterOptions = {"Blur", "Sharpen", "Sepia", "Greyscale", "Dither", "Mosaic"};
    comboBox = new JComboBox<>();


    // nice use of the for each loop!
    for (String option : filterOptions) {
      comboBox.addItem(option);
    }
    //comboBox.getSelectedIndex()); for action listener stuff
    comboBoxPanel.add(comboBox);
    filterPanel.add(comboBoxPanel);

    // Go Button
    goButton = new JButton("GO!");
    goButton.setOpaque(true);
    filterPanel.add(goButton);

    filterPanel.setVisible(true);
    this.add(filterPanel, BorderLayout.CENTER);

    // South Region
    JPanel updatePanel = new JPanel();
    JLabel updateLabel = new JLabel("Processing");
    updatePanel.add(updateLabel);
    updatePanel.setVisible(false);

    this.add(updatePanel, BorderLayout.SOUTH);


    this.pack(); // Fits things into the screen.
    this.setVisible(true); // Must go at the end.
  }

  /**
   * The commands that happen when an event is triggered within the GUI.
   *
   * @param features the actions that are possible that the view can trigger
   */
  @Override
  public void setCommands(Features features) {
    loadButton.addActionListener((event) -> {
      //fileChooser
      JFileChooser chooser = new JFileChooser();
      int result = chooser.showOpenDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
        String fileName = chooser.getSelectedFile().getAbsolutePath();
        System.err.println("File chosen: " + fileName);
        features.loadImage(fileName);
      }
    });

    saveButton.addActionListener((event) -> {
      //fileChooser
      JFileChooser chooser = new JFileChooser();
      int result = chooser.showSaveDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
        String fileName = chooser.getSelectedFile().getAbsolutePath();
        System.err.println("File chosen: " + fileName);
        features.save(fileName);
      }
    });

    applyFilterButton.addActionListener((event) -> {
      replaceCenterSection(batchCommandPanel, filterPanel);
      replaceCenterSection(generatePanel, filterPanel);
      this.pack();
    });


    // Generate Image Stuff
    generateImageButton.addActionListener((event) -> {
      replaceCenterSection(filterPanel, generatePanel);
      replaceCenterSection(batchCommandPanel, generatePanel);
      this.pack();
    });

    checkerboardButton.addActionListener((event) -> {
      JFrame checkerContainer = new JFrame();
      checkerContainer.setLayout(new FlowLayout());
      checkerContainer.setTitle("Enter Individual Checker Square Area");
      SpinnerModel checkerSpinnerModel = new SpinnerNumberModel(0, 0, 50000, 25);
      JSpinner checkerSpinner = new JSpinner(checkerSpinnerModel);
      checkerSpinner.setBorder(BorderFactory.createTitledBorder("Square Area"));
      checkerContainer.add(checkerSpinner);
      JButton checkerboardButton = new JButton("Make an awesome checkerboard!");
      checkerContainer.add(checkerboardButton);
      checkerContainer.setVisible(true);
      checkerContainer.pack();

      checkerboardButton.addActionListener((mosaicButtonClick) -> {
        int squareArea = (Integer) checkerSpinner.getValue();
        features.checkerboard(squareArea);
        checkerContainer.dispose();
        replaceCenterSection(generatePanel, filterPanel);
      });
    });

    rainbowButton.addActionListener((event) -> {
      //Generate Rainbow
      JFrame rainbowContainer = new JFrame();
      rainbowContainer.setLayout(new FlowLayout());
      rainbowContainer.setTitle("Make a Rainbow!");
      SpinnerModel widthSpinnerModel = new SpinnerNumberModel(0, 0, 2000, 100);
      JSpinner widthSpinner = new JSpinner(widthSpinnerModel);
      widthSpinner.setBorder(BorderFactory.createTitledBorder("Width"));
      rainbowContainer.add(widthSpinner);
      JButton rainbowButton = new JButton("Make a beautiful rainbow!");
      rainbowContainer.add(rainbowButton);
      rainbowContainer.setVisible(true);
      rainbowContainer.pack();

      rainbowButton.addActionListener((mosaicButtonClick) -> {
        int width = (Integer) widthSpinner.getValue();
        features.rainbow(width);
        rainbowContainer.dispose();
        replaceCenterSection(generatePanel, filterPanel);
      });
    });

    // Affects for Applying Filters
    goButton.addActionListener((event) -> {
      String action = (String) comboBox.getSelectedItem();
      System.out.println(action);
      switch (action) {
        case "Blur":
          features.blur();
          break;
        case "Sharpen":
          features.sharpen();
          break;
        case "Sepia":
          features.sepia();
          break;
        case "Greyscale":
          features.greyscale();
          break;
        case "Dither":
          features.dither();
          break;
        case "Mosaic":
          JFrame mosaicContainer = new JFrame();
          mosaicContainer.setLayout(new FlowLayout());
          mosaicContainer.setTitle("Select Number of Seeds");
          SpinnerModel seedModel = new SpinnerNumberModel(0, 0, 20000, 500);
          JSpinner spinner = new JSpinner(seedModel);
          mosaicContainer.add(spinner);
          JButton mosaicButton = new JButton("Make my picture a Mosaic!");
          mosaicContainer.add(mosaicButton);
          mosaicContainer.setVisible(true);
          mosaicContainer.pack();

          //TODO: Make this another action event.
          mosaicButton.addActionListener((mosaicButtonClick) -> {
            int seeds = (Integer) spinner.getValue();
            features.mosaic(seeds);
            mosaicContainer.dispose();
          });
          break;

        default:
          System.err.println("Action not supported.");
          break;
      }
    });

    // Batch Commands Stuff
    batchCommandsButton.addActionListener((event) -> {
      replaceCenterSection(filterPanel, batchCommandPanel);
      replaceCenterSection(generatePanel, batchCommandPanel);
      this.pack();
    });

    helpButton.addActionListener((event) -> {
      JFrame helpContainer = new JFrame();
      helpContainer.setSize(new Dimension(400, 400));
      helpContainer.setLayout(new FlowLayout());
      helpContainer.setTitle("Help");
      JTextArea helpText = new JTextArea("# TO LOAD AN IMAGE\n" +
              "load example.(jpg, png, etc.)\n" +
              "\n" +
              "# TO SAVE AN IMAGE\n" +
              "(Must create/generate an Image first)\n" +
              "save affected-example.(jpg, png, etc.)\n" +
              "\n" +
              "# FILTER COMMANDS\n" +
              "(Must have an Image loaded for this to work)\n" +
              "blur\n" +
              "sharpen\n" +
              "greyscale\n" +
              "sepia\n" +
              "dither\n" +
              "mosaic (Enter Number of Seeds)\n" +
              "\n" +
              "# GENERATE IMAGE COMMANDS\n" +
              "rainbow (Enter Number Value for Width)\n" +
              "checkerboard (Enter Individual Checker Square Area)");
      helpText.setLineWrap(true);
      helpText.setColumns(30);
      helpContainer.add(helpText);
      helpContainer.setVisible(true);
      this.add(helpContainer, BorderLayout.WEST);
      helpContainer.pack();
    });

    runBatch.addActionListener((event) -> {
      String commands = inputComTextArea.getText();
      System.out.println(commands);
      try {
        Scanner scanner = new Scanner(commands);

        while (scanner.hasNext()) {
          // Has to start with load
          String command = scanner.next();

          while (scanner.hasNext()) {
            if (command.equals("load")) {
              command = scanner.next();
              features.loadImage(command);
              command = scanner.next();
            } else {
              while (!command.equals("load") && scanner.hasNext()) {
                switch (command) {
                  case "save":
                    command = scanner.next(); // Will be the filename
                    features.save(command);
                    break;
                  case "blur":
                    features.blur();
                    break;
                  case "sharpen":
                    features.sharpen();
                    break;
                  case "greyscale":
                    features.greyscale();
                    break;
                  case "sepia":
                    features.sepia();
                    break;
                  case "dither":
                    features.dither();
                    break;
                  case "mosaic":
                    command = scanner.next();
                    int seeds = Integer.parseInt(command);
                    features.mosaic(seeds);
                    break;
                  case "rainbow":
                    command = scanner.next();
                    int width = Integer.parseInt(command);
                    features.rainbow(width);
                    break;
                  case "checkerboard":
                    command = scanner.next();
                    int checkerSquareArea = Integer.parseInt(command);
                    features.checkerboard(checkerSquareArea);
                    break;

                  default:
                    throw new IOException("Command not supported");
                }
                if (scanner.hasNext()) {
                  command = scanner.next();
                }
              }
            }
          }
        }
      } catch (IOException ex) {
        System.out.println("Input Output Exception Occurred");
      }
    });
  }

  /**
   * Updates the view based on a Buffered Image.
   *
   * @param image image to be shown in the view
   */
  @Override
  public void updateView(BufferedImage image) {
    // TODO add the image to the view
    innerPanel.remove(imageLabel);
    imageLabel = new JLabel(new ImageIcon(image));
    innerPanel.add(imageLabel);
    this.imageScrollPane.revalidate();
    this.imageScrollPane.repaint();
    innerPanel.setPreferredSize(imageLabel.getPreferredSize());

    System.err.println("Updated view!");
  }

  /**
   * Helper method to help replace the center section of the BorderLayoutModel.
   *
   * @param panelToLeave panel that will leave from view
   * @param panelToPlace panel that will show in the view
   */
  private void replaceCenterSection(JPanel panelToLeave, JPanel panelToPlace) {
    this.remove(panelToLeave);
    this.add(panelToPlace, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
  }
}
