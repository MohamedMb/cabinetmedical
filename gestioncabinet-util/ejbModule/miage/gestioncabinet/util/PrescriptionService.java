package miage.gestioncabinet.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.vidal.webservices.interactionservice.ArrayOfInt;
import fr.vidal.webservices.interactionservice.InteractionCouple;
import fr.vidal.webservices.interactionservice.InteractionResult;
import fr.vidal.webservices.interactionservice.InteractionService;
import fr.vidal.webservices.interactionservice.InteractionService_Service;
import fr.vidal.webservices.interactionservice.InteractionSeverityType;
import fr.vidal.webservices.productservice.Product;
import fr.vidal.webservices.productservice.ProductService;
import fr.vidal.webservices.productservice.ProductService_Service;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.PrescriptionServiceInterface;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinetcoredb.ejbmodule.InteractionEntity;
import miage.gestioncabinetcoredb.ejbmodule.ProduitEntity;


@Stateless
@Local(PrescriptionServiceInterface.class)
public class PrescriptionService implements PrescriptionServiceInterface {
	private ProductService mProductService;
	private InteractionService mInteractionService;


	/**
	 * Recherche de produits
	 */
	@Override
	public List<Produit> rechercherProduit(String search) {
		mProductService = new ProductService_Service().getProductServiceHttpPort();
		List<Produit> produits = new ArrayList<Produit>();

		List<Product> products = mProductService.directSearchByName(search).getProduct();
		for(Product p : products){
			Produit produit = (Produit) new ProduitEntity();
			produit.setNom(p.getName());
			if(p.getCis() == null) {
				produit.setCis(String.valueOf(p.getId()));
			} else {
				produit.setCis(p.getCis());
			}
			produits.add(produit);
		}
		return produits;
	}

	/**
	 * Recherche d'interactions
	 */
	@Override
	public List<Interaction> analyser(List<Produit> listProduit) {

		//webservice interaction
		mInteractionService = new InteractionService_Service().getInteractionServiceHttpPort();

		//Tableau de int vidal pour la recherche d'interactions
		ArrayOfInt ints = new ArrayOfInt();
		//Interactions à retourner
		List<Interaction> interactions = new ArrayList<Interaction>();
		
		//Récupération des id depuis vidal
		for(Produit p : listProduit) {
			Product product = mProductService.searchByCis(p.getCis());
			ints.getInt().add(product.getId());
		}

		for(InteractionSeverityType interactionSeverityType : InteractionSeverityType.values()) {
			InteractionResult interactionResult = mInteractionService.getInteractionCouplesForProductIds(ints, interactionSeverityType);
			
			List<InteractionCouple> interactionCouples = interactionResult.getInteractionCoupleList().getInteractionCouple();
			
			for(InteractionCouple interactionCouple : interactionCouples) {
				//instantiation de InteractionEntity et ProduitEntity (dans InteractionEntity
				Interaction interaction = (Interaction) new InteractionEntity();
				Produit prodA = (Produit) new ProduitEntity();
				Produit prodB = (Produit) new ProduitEntity();

				//Ajout des informations vidal product et interaction vers nos produits et interactions
				prodA.setNom(interactionCouple.getProductA().getName());
				prodA.setCis(interactionCouple.getProductA().getCis());
				interaction.setProduitA(prodA);
				prodB.setNom(interactionCouple.getProductB().getName());
				prodB.setCis(interactionCouple.getProductB().getCis());
				interaction.setProduitB(prodB);
				interaction.setRisques(interactionCouple.getRiskComment());
				interaction.setPrecautions(interactionCouple.getPrecautionComment());
				interaction.setSeverite(interactionCouple.getSeverity().value());

				interactions.add(interaction);
			}
		}
		return interactions;
	}
}