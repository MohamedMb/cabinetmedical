package miage.gestioncabinet.coreM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ApplicationService {
	private Properties mProperties;
	private final static String PATH_PROPERTIES = "/META-INF/gestioncabinet.properties";
	
	
	public ApplicationService() {}
	
	public Properties getProperties() {
		if(mProperties == null) {
			mProperties = new Properties();
			try {
				mProperties.load(new FileInputStream(new File(PATH_PROPERTIES)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mProperties;
	}

}
