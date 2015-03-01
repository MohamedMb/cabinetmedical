package miage.gestioncabinet.api;

import java.util.List;

public interface PrescriptionServiceInterface {
	public List<Produit> rechercherProduit(String search);
	public List<Interaction> analyser(List<Produit> listProduit);

}
