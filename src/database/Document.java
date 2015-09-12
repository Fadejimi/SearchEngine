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
public class Document {
    private int id;
    private String path;
    
    public Document(int id, String p){
        setId(id);
        setPath(p);
    }
    
    public Document(int id){
        setId(id);
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setPath(String p)
    {
        this.path = p;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public String getPath()
    {
        return this.path;
    }
}