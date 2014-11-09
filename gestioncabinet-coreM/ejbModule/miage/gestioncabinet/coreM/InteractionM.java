package miage.gestioncabinet.coreM;

import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.Interaction;
import javax.resource.cci.InteractionSpec;
import javax.resource.cci.Record;
import javax.resource.cci.ResourceWarning;

public class InteractionM implements Interaction{

	@Override
	public void clearWarnings() throws ResourceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws ResourceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Record execute(InteractionSpec arg0, Record arg1)
			throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean execute(InteractionSpec arg0, Record arg1, Record arg2)
			throws ResourceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceWarning getWarnings() throws ResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}
