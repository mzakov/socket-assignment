package com.cooksys.socket.assignment;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.File;

/**
 * Shared static methods to be used by both the {@link Client} and {@link Server} classes.
 */
public class Utils {
    /**
     * @return a {@link JAXBContext} initialized with the classes in the
     * com.cooksys.socket.assignment.model package
     * @throws JAXBException 
     */
    public static JAXBContext createJAXBContext() throws JAXBException {
  
    	return JAXBContext.newInstance(Student.class, Config.class);
    	
    }

    /**
     * Reads a {@link Config} object from the given file path.
     *
     * @param configFilePath the file path to the config.xml file
     * @param jaxb the JAXBContext to use
     * @return a {@link Config} object that was read from the config.xml file
     * @throws JAXBException 
     */
    public static Config loadConfig(String configFilePath, JAXBContext jaxb) throws JAXBException {
        
    	
    	Unmarshaller unmarshaller = jaxb.createUnmarshaller();
       	File file = new File(configFilePath);
       	Config result =(Config)unmarshaller.unmarshal(file);
    	return result;
    	
    }
}
