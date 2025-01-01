# NotePad Application

## Overview
This is a Java Swing-based Notepad application designed to provide text editing features along with basic algorithms for text processing. The application allows users to open, edit, save, and manage multiple text files using a tabbed interface. It includes additional functionality like word counting, sorting numbers, checking palindromes, and more.

## Features

### File Operations
- **New**: Create a new tab with a blank text editor.
- **Open**: Open an existing file and display its content in a new tab.
- **Save**: Save the content of the current tab to a file.
- **Exit**: Close the application.

### Text Editing
- **Copy, Paste, Cut**: Standard clipboard operations.
- **Bold, Italic, Underline**: Change the font style of the selected text.
- **Align Left, Center, Right**: Adjust text alignment.
- **Remove Extra Spaces**: Clean up redundant spaces in the text.

### Algorithms
- **Sort Numbers**: Sort numbers in the current text.
- **Count Words**: Display the total word count.
- **Find Duplicates**: Identify duplicate words.
- **Word Frequency**: Count occurrences of each word.
- **Check Palindrome**: Check if the text contains palindromes.
- **Reverse String**: Reverse the text content.
- **String Search**: Search for a specific word.

### Help
- **About Notepad**: Display information about the application and its creator.

## Usage

### Launching the Application
Run the `NotePad` class to start the application.

```bash
javac NotePad.java
java NotePad
```

### User Interface
- **Menu Bar**: The menu bar provides options categorized under "File," "Edit," "Algorithms," and "Help." Each menu contains relevant commands for text editing, file operations, or algorithms.
- **Toolbar**: A set of buttons offering quick access to commonly used features, such as creating a new file, counting words, and checking palindromes.
- **Tabbed Pane**: Allows editing multiple files simultaneously. Each tab represents an individual file or document.
- **Text Area**: The main editing area where users can write or modify their content. Includes support for scrolling.

### Toolbar Buttons
The toolbar provides quick access to frequently used features, such as creating new files, sorting numbers, and counting words.

## Implementation Details

### Core Components
- **JTabbedPane**: Handles multiple tabs for editing.
- **JTextArea**: Provides a text editing area.
- **JScrollPane**: Enables scrolling for text areas.
- **JMenuBar and JToolBar**: Provide menus and toolbar options.

### Algorithms
1. **Sorting Numbers**: Uses merge sort to sort numbers in each line of text.
2. **Word Frequency**: Counts the frequency of words in the text and displays the result.
3. **Palindrome Checker**: Identifies lines that are palindromes.
4. **Duplicate Finder**: Finds duplicate words in the text.

### Custom Features
- Tabbed editing with the ability to close individual tabs.
- Font and alignment customization using `TextAttributes`.
- Error handling for file operations and invalid user inputs.

## Requirements
- **Java Runtime Environment (JRE)** 8 or higher.
- Compatible with major operating systems (Windows, macOS, Linux).

## Output
![image](https://github.com/user-attachments/assets/3ff896d2-acc0-457e-8e13-bd4c9dd775de)

## How to Extend
- Add new algorithms to the **Algorithms** menu.
- Modify the toolbar to include additional buttons.
- Enhance text formatting options using `Font` and `TextAttributes`.
