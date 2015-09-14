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
public class Topic {
    private int tid;
    private String topic;
    
    public Topic(int t, String top)
    {
        setTopicId(t);
        setTopic(top);
    }
    
    public void setTopicId(int tid)
    {
        this.tid = tid;
    }
    
    public void setTopic(String topic)
    {
        this.topic = topic;
    }
    
    public int getTopicId()
    {
        return tid;
    }
    
    public String getTopic()
    {
        return topic;
    }
}
