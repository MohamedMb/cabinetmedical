package miage.gestioncabinet.util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.ejb.Singleton;
import javax.ejb.Local;

import miage.gestioncabinet.api.ApplicationServiceInterface;

@Singleton
@Local(ApplicationServiceInterface.class)
public class ApplicationService implements ApplicationServiceInterface {
	private Properties mProperties;
	private final static String PATH_PROPERTIES = "/META-INF/gestioncabinet.properties";
	
	
	public ApplicationService() {}
	
	public Properties getProperties() {
		if(mProperties == null) {
			mProperties = new Properties();
			try {
				mProperties.load(this.getClass().getResourceAsStream(PATH_PROPERTIES));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return mProperties;
	}

}
