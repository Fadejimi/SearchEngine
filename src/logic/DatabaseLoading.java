/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

/**
 *
 * @author Test
 */
import UI.*;
import database.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;

public class DatabaseLoading extends JFrame{
    private LoadDocument userInterface;
    private JList documentList, searchList, sentenceList;
    private JButton searchButton, loadButton, sentenceButton, exitButton;
    private DocumentQuery query;
    private File[] files;
    private HashMap filesMap;
    private DefaultListModel documentModel, sentenceModel;
    
    public DatabaseLoading()
    {
        super("Document Search Engine");
        
        userInterface = new LoadDocument();
        query = new DocumentQuery();
        filesMap = new HashMap<String, String>();
        documentModel = new DefaultListModel();
        sentenceModel = new DefaultListModel();
        
        getContentPane().add(userInterface, BorderLayout.CENTER);
        
        documentList = userInterface.getDocumentList();
        searchList = userInterface.getSearchList();
        sentenceList = userInterface.getSentenceList();
        
        documentList.setModel(documentModel);
        sentenceList.setModel(sentenceModel);
        
        searchButton = userInterface.getSearchButton();
        loadButton = userInterface.getLoadDocumentButton();
        sentenceButton = userInterface.getSentenceButton();
        exitButton = userInterface.getExitButton();
        
        searchButton.setEnabled(false);
        sentenceButton.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        sentenceButtonActionPerformed(e);
                    }
                }
        );
        
        
        loadButton.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        loadButtonActionPerformed(e);
                        searchButton.setEnabled(true);
                    }
                }
        );
        
        exitButton.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        setVisible(false);
                    }
                }
        );
        
        setSize(400, 350);
        setVisible(true);
        setResizable(true);
    }
    
    public void loadButtonActionPerformed(ActionEvent e)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            String folder = chooser.getSelectedFile().getAbsolutePath();
            files = new File(folder).listFiles();
            showFiles(files);
        } else {
            System.out.println("No Selection ");
        }
    }
    
    private void showFiles(File[] files)
    {
        for (File file: files) {
            String fileName = file.getName();
            String filePath = file.getAbsolutePath();
            
            int result = query.insertDocument(filePath);
            if (result == 1)
            {
                System.out.println("Document Inserted");
            }
            else {
                System.out.println("Document not inserted");
            }
            filesMap.put(fileName, filePath);
        }
        
        showFilesList();
    }
    
    private void showFilesList()
    {
        Set set = filesMap.entrySet();
        Iterator iterator = set.iterator();
        
        while(iterator.hasNext())
        {
            Map.Entry fileEntry = (Map.Entry) iterator.next();
            
            documentModel.addElement(fileEntry.getKey());
        }
    }
    
    private void sentenceButtonActionPerformed(ActionEvent e)
    {
        Set set = filesMap.entrySet();
        Iterator iterator = set.iterator();
        
        while(iterator.hasNext())
        {
            Map.Entry fileEntry = (Map.Entry) iterator.next();
            
            String filePath = fileEntry.getValue().toString();
            sentenceModel.addElement(fileEntry.getKey());
            int lines = 0;
            
            try(BufferedReader br = new BufferedReader(new FileReader(fileEntry
                    .getValue().toString())))
            {
                String currentLine = null;
                
                while((currentLine = br.readLine()) != null){
                    lines++;
                    
                    if (lines >= 15)
                    {
                        System.out.println(currentLine);
                        
                        int documentId = query.getDocumentID(filePath);
                        
                        
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new DatabaseLoading();
    }
}
