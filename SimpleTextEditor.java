import javax.print.PrintException;
import javax.print.attribute.standard.PrintQuality;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class SimpleTextEditor implements ActionListener {
    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File;
    JMenu Edit;
    JMenu Close;
    JMenuItem NewFile;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    JMenuItem PrintFile;
    JMenuItem Cut;
    JMenuItem Copy;
    JMenuItem Paste;
    JMenuItem CloseEditor;

    SimpleTextEditor()
    {
        //Creating the frame
        frame = new JFrame("Simple Text Editor");
        frame.setBounds(0,0,800,1000);
        //Creating Text Area
        jTextArea = new JTextArea("Welcome to the Editor");
        //Creating Menu Bar
        jMenuBar = new JMenuBar();
        //Different Menus
        File = new JMenu("File");
        Edit = new JMenu("Edit");
        Close = new JMenu("Close");
        //Menu Items in File
        OpenFile = new JMenuItem("Open");
        NewFile = new JMenuItem("New");
        SaveFile = new JMenuItem("Save");
        PrintFile = new JMenuItem("Print");
        //Add action listener to Open, New , Save and Print
        NewFile.addActionListener(this);
        OpenFile.addActionListener(this);
        SaveFile.addActionListener(this);
        PrintFile.addActionListener(this);
        //Menu Items in Edit
        Cut = new JMenuItem("Cut");
        Copy = new JMenuItem("Copy");
        Paste = new JMenuItem("Paste");
        //Add action listener into Copy,paste and cut
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        //Menu Items in Close
        CloseEditor = new JMenuItem("Close");
        //Add action listener to Close menu
        CloseEditor.addActionListener(this);
        //Adding File to Menubar
        jMenuBar.add(File);
        //Adding Menu items into File menu
        File.add(NewFile);
        File.add(OpenFile);
        File.add(SaveFile);
        File.add(PrintFile);
        //Adding Edit menu to menubar
        jMenuBar.add(Edit);
        //Adding Menu items into Edit menu
        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);
        //Adding Close menu to menubar
        jMenuBar.add(Close);
        //Adding menu item into Close menu
        Close.add(CloseEditor);
        //Adding Menubar into frame
        frame.setJMenuBar(jMenuBar);
        //Adding text area into frame
        frame.add(jTextArea);
        //Adding close feature
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Making frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        SimpleTextEditor editor = new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Copy"))
            jTextArea.copy();
        else if(s.equals("Cut"))
            jTextArea.cut();
        else if (s.equals("Paste"))
            jTextArea.paste();
        else if(s.equals("Print"))
        {
            try{
                jTextArea.print();
            } catch (PrinterException ex){
                throw new RuntimeException(ex);
            }

        }
        else if(s.equals("New"))
        {
            jTextArea.setText("");
        }
        else if(s.equals("Close"))
        {
           System.exit(1);
        }
        else if(s.equals("Open"))
        {
            JFileChooser jFileChooser = new JFileChooser("C:");

            int ans = jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION)
            {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1 = "", s2 = "";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while((s1 = bufferedReader.readLine())!=null)
                    {
                        s2 += "\n"+s1;
                    }
                    jTextArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(s.equals("Save"))
        {
            JFileChooser jFileChooser = new JFileChooser("C");
            int ans = jFileChooser.showSaveDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION)
            {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                    writer.write(jTextArea.getText());
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        }


    }
}
