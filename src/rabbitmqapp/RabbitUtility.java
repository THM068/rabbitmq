/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitmqapp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author thandomafela
 */
public class RabbitUtility {
    
    public static byte[] convertToByteArray(Serializable object) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] result = null;
        try {
            out = new ObjectOutputStream(bos);   
            out.writeObject(object);
            result = bos.toByteArray();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        finally {
            closeResource(bos);
            closeResource(out);
        }
        return result;
        
    }
    
    public static Object getObject(byte [] mybytes){
        ByteArrayInputStream bis = new ByteArrayInputStream(mybytes);
        ObjectInput in = null;
        Object result = null;
        try {
            in = new ObjectInputStream(bis);
            result = in.readObject(); 
        } 
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        finally {
           closeResource(bis);
           closeResource(in);
        }
        return result;
        
    }
    
    public static void closeResource(Closeable c) {
        try {
            if (c != null) {
                c.close();
            }
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void closeResource(AutoCloseable c) {
        try {
            if (c != null) {
                c.close();
            }
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
