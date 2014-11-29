package miage.gestioncabinet.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.PrescriptionServiceInterface;
import miage.gestioncabinet.api.Produit;


@Stateless
@Local(PrescriptionServiceInterface.class)
public class PrescriptionService implements PrescriptionServiceInterface {
	private List<Produit> mProduits;
	private Class<? extends Interaction> interactionClass;
	
	@Override
	public List<Produit> rechercherProduit(String search) {
		List<Produit> returnProduits = new ArrayList<Produit>();
		for(Produit produit : mProduits) {
			if(produit.getNom().toLowerCase().contains(search.toLowerCase())) {
				returnProduits.add(produit);
			}
		}
		return returnProduits;
	}

	@Override
	public /*List<Interaction>*/void analyser(List<Produit> listProduit) {
		// TODO Auto-generated method stub
		//return new ArrayList<InteractionM>;
	}
}