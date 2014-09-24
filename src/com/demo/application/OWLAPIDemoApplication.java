package com.demo.application;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.RDFIndividual;

public class OWLAPIDemoApplication {

	static String url = "http://auriga:8080/Ontology/University/university.owl";

	public static void main(String[] args) {
		OWLModel owlModel;
		try {
			owlModel = ProtegeOWL.createJenaOWLModel();
			owlModel.getNamespaceManager().setDefaultNamespace(
					"http://hello.com#");
			OWLNamedClass worldClass = owlModel.createOWLNamedClass("World");
			System.out.println("Class URI: " + worldClass.getURI());
			loadModel(url);
		} catch (OntologyLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void loadModel(String url) throws OntologyLoadException {
		OWLModel owlModel = ProtegeOWL.createJenaOWLModelFromURI(url);
		
		OWLNamedClass personClass = owlModel.createOWLNamedClass("Person");
		
		    OWLDatatypeProperty ageProperty = owlModel.createOWLDatatypeProperty("age");
		    ageProperty.setRange(owlModel.getXSDint());
		    ageProperty.setDomain(personClass);

		    OWLObjectProperty childrenProperty = owlModel.createOWLObjectProperty("children");
		    childrenProperty.setRange(personClass);
		    childrenProperty.setDomain(personClass);

		    RDFIndividual darwin = personClass.createRDFIndividual("Darwin");
		    darwin.setPropertyValue(ageProperty, new Integer(0));

		    RDFIndividual holgi = personClass.createRDFIndividual("Holger");
		    holgi.setPropertyValue(childrenProperty, darwin);
		    holgi.setPropertyValue(ageProperty, new Integer(33));
		
	}
}
