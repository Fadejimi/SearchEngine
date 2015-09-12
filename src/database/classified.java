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
public class classified {
    private int sentenceId, topicId, documentId;
    private String sentence;
    
    public classified(int sid, int tid, int did, String sent)
    {
        setSentenceId(sid);
        setTopicId(tid);
        setDocumentId(did);
        setSentence(sent);
    }
    
    public void setSentenceId(int id)
    {
        sentenceId = id;
    }
    
    public int getSentenceId()
    {
        return sentenceId;
    }
    
    public void setTopicId(int id)
    {
        topicId = id;
    }
    
    public int getTopicId()
    {
        return topicId;
    }
    
    public void setDocumentId(int id)
    {
        documentId = id;
    }
    
    public int getDocumentId()
    {
        return documentId;
    }
    
    public void setSentence(String sent)
    {
        sentence = sent;
    }
    
    public String getSentence()
    {
        return sentence;
    }
}

