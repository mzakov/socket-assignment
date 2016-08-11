package com.cooksys.socket.assignment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.Student;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException, JAXBException {
      	Unmarshaller unmarshaller = Utils.createJAXBContext().createUnmarshaller();
       	File file = new File("config/config.xml");
       	Config Config =(Config)unmarshaller.unmarshal(file);
   	
    	
    	Socket s = new Socket(Config.getRemote().getHost(), Config.getRemote().getPort());
    	InputStream in = s.getInputStream();
    	
    	Unmarshaller jaxUnmarshaller = Utils.createJAXBContext().createUnmarshaller();
    	Student student = (Student) jaxUnmarshaller.unmarshal(in);
    	System.out.println(student);
    	
    	in.close();
    	s.close();
    	
    }
}
