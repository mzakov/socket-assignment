package com.cooksys.socket.assignment;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.Student;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Server extends Utils {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the
     * @param jaxb
     * @return
     * @throws JAXBException 
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException {
        
    	Unmarshaller unmarshaller = jaxb.createUnmarshaller();
       	File file = new File(studentFilePath);
       	Student student =(Student)unmarshaller.unmarshal(file);
    	return student;
    	
    	
    	
    }

    public static void main(String[] args) throws JAXBException {
        
    	Unmarshaller unmarshaller = Utils.createJAXBContext().createUnmarshaller();
       	File file = new File("config/config.xml");
       	Config Config =(Config)unmarshaller.unmarshal(file);
    	
    	Student std = loadStudent(Config.getStudentFilePath(),Utils.createJAXBContext());
    	System.out.println(std);
    	
    	try
		(	
				ServerSocket ss =new ServerSocket(Config.getLocal().getPort());
				Socket s = ss.accept();
				//waits here
    			OutputStream out = s.getOutputStream();
				
		){
    		
    		Marshaller jaxMarshaller = Utils.createJAXBContext().createMarshaller();
           	jaxMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true);
        	jaxMarshaller.marshal(std, out);
    		
    		s.close();
    		ss.close();
			
		}catch(IOException e){
			
		}
    	
    	// TODO
    }
}
