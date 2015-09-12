/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author Test
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentQuery {
    static final String URL = "jdbc:mysql://localhost/informationdatabase";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    private Connection con;
    
    private PreparedStatement insertDocument = null;
    private PreparedStatement insertSentence = null;
    private PreparedStatement selectAllDocuments = null;
    private PreparedStatement selectAllSentences = null;
    private PreparedStatement selectDocument = null;
    private PreparedStatement selectSentence = null;
    
    public DocumentQuery()
    {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            insertDocument = con.prepareStatement("INSERT INTO document(documentPath) "
                    + "VALUES (?)");
            insertSentence = con.prepareStatement("INSERT INTO classified(topicId, "
                    + "documentId, sentence) VALUES(?, ?, ?)");
            selectAllSentences = con.prepareStatement("SELECT * FROM classified");
            selectAllDocuments = con.prepareStatement("SELECT * FROM document");
            selectDocument = con.prepareStatement("SELECT * FROM document WHERE "
                    + "documentId = ?");
            selectSentence = con.prepareStatement("SELECT * FROM classified WHERE "
                    + "sentenceId = ?");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public int insertDocument(String path)
    {
        int result = 0;
        
        try {
            insertDocument.setString(1, path);
            
            result = insertDocument.executeUpdate();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public int insertSentence(int tid, int did, String sent)
    {
        int result = 0;
        
        try {
            insertSentence.setInt(1, tid);
            insertSentence.setInt(2, did);
            insertSentence.setString(3, sent);
            
            result = insertSentence.executeUpdate();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public List<Document> getAllDocuments()
    {
        List<Document> results = null;
        ResultSet resultSet = null;
        
        try {
            resultSet = selectAllDocuments.executeQuery();
            
            results = new ArrayList<Document>();
            
            while( resultSet.next() )
            {
                results.add( new Document(
                    resultSet.getInt("documentId"),
                    resultSet.getString("documentPath")));
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<classified> getAllSentences()
    {
        List<classified> results = null;
        ResultSet resultSet = null;
        
        try {
            results = new ArrayList<classified>();
            
            resultSet = selectAllSentences.executeQuery();
            
            while(resultSet.next())
            {
                results.add(new classified(
                    resultSet.getInt("sentenceId"),
                    resultSet.getInt("topicId"),
                    resultSet.getInt("documentId"),
                    resultSet.getString("sentence")));
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<Document> getDocument(int id)
    {
        List<Document> results = null;
        ResultSet resultSet = null;
        
        try {
            selectDocument.setInt(1, id);
            
            resultSet = selectDocument.executeQuery();
            
            results = new ArrayList<Document>();
            
            while( resultSet.next() )
            {
                results.add( new Document(
                    resultSet.getInt("documentId"),
                    resultSet.getString("documentPath")));
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<classified> getSentence(int id)
    {
        List<classified> results = null;
        ResultSet resultSet = null;
        
        try {
            results = new ArrayList<classified>();
            
            selectSentence.setInt(1, id);
            resultSet = selectAllSentences.executeQuery();
            
            while(resultSet.next())
            {
                results.add(new classified(
                    resultSet.getInt("sentenceId"),
                    resultSet.getInt("topicId"),
                    resultSet.getInt("documentId"),
                    resultSet.getString("sentence")));
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return results;
    }
}
