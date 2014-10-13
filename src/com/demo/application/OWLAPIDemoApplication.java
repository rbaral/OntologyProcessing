package com.demo.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
/*import com.hp.hpl.jena.rdql.QueryEngine;
import com.hp.hpl.jena.rdql.QueryResults;*/
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.RDFIndividual;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import edu.stanford.smi.protegex.owl.model.RDFSClass;
import edu.stanford.smi.protegex.owl.model.RDFSNames;

/**
 * Ref:http://protegewiki.stanford.edu/wiki/BuildingSemanticWebApplications
 * The application can also operate on extensions of these core concepts, e.g., stemming from dynamic extension ontologies 
 * about specific types of activities and destinations. 
 * Then, the application can exploit reasoning engines like Racer or rule engines like SWRL 
 * to expose "intelligent" behavior. All of this is controlled by some logic (in this example it is Java code), 
 * which also interacts with the end user by means of interface technologies like JSPs, Swing applications, 
 * or Web Services.
 * 
 * @File: OWLAPIDemoApplication.java
 * @author Ramesh R. Baral
 * @Version 1.0
 * @since Sep 24, 2014
 */
public class OWLAPIDemoApplication {

	static String univOwl = "http://auriga:8080/Ontology/University/university.owl";
	static String travelOwl="http://protege.cim3.net/file/pub/ontologies/travel/travel.owl";
	

	public static void main(String[] args) throws OWLOntologyCreationException, FileNotFoundException {
		OWLModel owlModel;
		try {
			owlModel = ProtegeOWL.createJenaOWLModel();
			owlModel.getNamespaceManager().setDefaultNamespace(
					"http://hello.com#");
			OWLNamedClass worldClass = owlModel.createOWLNamedClass("World");
			System.out.println("Class URI: " + worldClass.getURI());
			//loadModel(travelOwl);
			//loadUniversityOwl(univOwl);
			//loadUniversityJena(univOwl);
			//testInference(null);
			//testReasoningWithRules();
			progOnt();
		} catch (OntologyLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * for examples from the book programming the semantic web by Cardosa J
	 * http://www.academia.edu/2894895/Programming_The_Semantic_Web
	 * @author Ramesh R. Baral
	 * @Version 1.0
	 * @since Oct 10, 2014
	 */
	public static void progOnt(){
		String url="http://auriga:8080/Ontology/";
		String baseURL="http://people.cis.fiu.edu/~rbaral/university.owl#";
		OntModel model=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		//alternate copy of the ontology
		OntDocumentManager dm=model.getDocumentManager();
		dm.addAltEntry(url, "file:\\U:\\scis\\ASCL\\OntologyandSemantics\\university.owl");
		dm.addAltEntry(url, "file:owlfiles//university.owl");
		model.read(url);
		OntClass p=model.getOntClass(baseURL+"Person");
		OntClass r=model.createClass(baseURL+"Employee");
		DatatypeProperty dp=model.createDatatypeProperty(baseURL+"ssn");
		ObjectProperty prop=model.createObjectProperty(baseURL+"email");
		dp.setRange(p);dp.setDomain(p);
		prop.setRange(model.getResource(baseURL+"Person"));
		prop.setDomain(p);
		r.addSuperClass(p);
		//set property of a particular individual
		Resource tClass=model.getResource(baseURL+"Student");
		Individual teacher=model.createIndividual(baseURL+"Jorge",tClass);
		DatatypeProperty fname =model.getDatatypeProperty(baseURL+"first_name");
		teacher.addProperty(fname,"Jorge");
		//DatatypeProperty email =model.getDatatypeProperty(baseURL+"email");
		//teacher.addProperty(email,"jcardoso@uma.pt");
		//manipulate class
		ExtendedIterator exIterator=model.listClasses();
		OntClass ocl;
		Individual ind;
		while(exIterator.hasNext()){
			ocl=(OntClass)exIterator.next();
			System.out.println(ocl.getLocalName()+" has subclass:"+ocl.hasSubClass());
			//if(ocl.hasSubClass()){
				for(ExtendedIterator it=ocl.listInstances();it.hasNext();){
					ind=(Individual)it.next();
					System.out.println("***Individual "+ind.toString());
					System.out.println("Properties:");
					for(StmtIterator j=ind.listProperties();j.hasNext();){
						
						System.out.println(" "+j.next());
						}
				}
			//}

		}
		//RDQL- querying JENA
/*		String queryString="SELECT ?x,?y,?z WHERE(?x,?y,?z)";
		com.hp.hpl.jena.rdql.Query query=new com.hp.hpl.jena.rdql.Query(queryString);
		query.setSource(model);
		QueryResults result=new QueryEngine(query).exec();
		for(Iterator i=result;i.hasNext();){
			System.out.println(i.next());
		}*/
		
		Reasoner reasoner=ReasonerRegistry.getOWLReasoner();
		reasoner.bindSchema(model);
		InfModel infModel=ModelFactory.createInfModel(reasoner,model);
		//validate if ontology is fine
		ValidityReport vr = infModel.validate();
		if (vr.isValid()){
			System.out.println("Valid OWL");
			}
		else {
			System.out.println("Not a valid OWL!");
			for (Iterator i = vr.getReports(); i.hasNext();){
				System.out.println(i.next());
				}
			}
		//get the information related to CS module
		Resource res = infModel.getResource(baseURL+"M201");
		System.out.println("CS103 *:");
		for (StmtIterator i =infModel.listStatements(res,(Property)null,(Resource)null);i.hasNext(); ){
			Statement stmt = i.nextStatement();
			System.out.println(PrintUtil.print(stmt));
			}
		//check for contains relation
		Resource r1 = infModel.getResource(baseURL+"Jorge");
		Resource r2 = infModel.getResource(baseURL+"Person");
		if (infModel.contains(r1, RDF.type, r2)) {
			System.out.println("Jorge is a Person");
			} else {
				System.out.println("Jorge is not a Person");
				}
		
	}

	public static void createModel(String url) throws OntologyLoadException {
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
		    /*darwin.setPropertyValue(stringProperty, "MyString");
		    darwin.setPropertyValue(intProperty, new Integer(42));
		    darwin.setPropertyValue(floatProperty, new Float(4.2));
		    darwin.setPropertyValue(booleanProperty, Boolean.TRUE);*/

		    RDFIndividual holgi = personClass.createRDFIndividual("Holger");
		    holgi.setPropertyValue(childrenProperty, darwin);
		    holgi.setPropertyValue(ageProperty, new Integer(33));
		    
		    // Create subclass (complicated version)
		    OWLNamedClass brotherClass = owlModel.createOWLNamedClass("Brother");
		    brotherClass.addSuperclass(personClass);
		    brotherClass.removeSuperclass(owlModel.getOWLThingClass());
		    OWLIndividual hans = brotherClass.createOWLIndividual("Hans");
		    Collection brothers = brotherClass.getInstances(false);
		    
		    
		    assert (brothers.contains(hans));
		    assert (brothers.size() == 1);
		    //assert (personClass.getInstanceCount(false) == 0);
		    //assert (personClass.getInstanceCount(true) == 0);
		    assert (personClass.getInstances(true).contains(hans));
		    assert (hans.getRDFType().equals(brotherClass));
		    assert (hans.hasRDFType(brotherClass));
		    assert !(hans.hasRDFType(personClass, false));
		    assert (hans.hasRDFType(personClass, true));
		    
		    hans.delete();
		    OWLDatatypeProperty property = owlModel.createOWLDatatypeProperty("name");
		    property.setRange(owlModel.getXSDstring());
		    
		    OWLNamedClass sisterClass = owlModel.createOWLNamedSubclass("Sister", personClass);
		    printClassTree(personClass,"");
		
	}
	
	private static void printClassTree(RDFSClass cls, String indentation) {
		System.out.println(indentation + cls.getName());
        for (Iterator it = cls.getSubclasses(false).iterator(); it.hasNext();) {
            RDFSClass subclass = (RDFSClass) it.next();
            
            printClassTree(subclass, indentation + "    ");
        }
    }
	
	public static void loadModel(String url) throws OntologyLoadException{
		OWLModel owlModel = ProtegeOWL.createJenaOWLModelFromURI(url);
		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
		    OWLNamedClass cls = (OWLNamedClass) it.next();
		    Collection instances = cls.getInstances(false);
		    System.out.println("Class " + cls.getBrowserText() + " (" + instances.size() + ")");
		    for (Iterator jt = instances.iterator(); jt.hasNext();) {
		        OWLIndividual individual = (OWLIndividual) jt.next();
		        System.out.println(" - " + individual.getBrowserText());
		    }
		}
		
		RDFProperty subClassOfProperty = owlModel.getRDFProperty(RDFSNames.Slot.SUB_CLASS_OF);
		OWLNamedClass owlThingClass = owlModel.getOWLNamedClass("Accommodation");//OWLThingClass();
		Collection results = owlModel.getRDFResourcesWithPropertyValue(subClassOfProperty, owlThingClass);
		System.out.println("Subclasses of owl:Thing:");
		for (Iterator it = results.iterator(); it.hasNext();) {
		    RDFResource resource = (RDFResource) it.next();
		    System.out.println(" - " + resource.getBrowserText());
		}
	}

	public static void loadUniversityOwl(String url) throws OWLOntologyCreationException, OntologyLoadException, FileNotFoundException{
		String file="U:\\scis\\ASCL\\OntologyandSemantics\\university.owl";
		OWLOntologyManager manager= OWLManager.createOWLOntologyManager();
		//IRI inputDocumentIRI=IRI.create(file);
		//OWLOntology ontology=manager.loadOntologyFromOntologyDocument(new File(file));
		//UniversityFactory uniFactory=new UniversityFactory(ontology);
		
		OWLModel owlModel=ProtegeOWL.createJenaOWLModelFromInputStream(new FileInputStream(new File(file)));
		//OWLModel owlModel = ProtegeOWL.createJenaOWLModelFromURI(url);
		Collection individuals=owlModel.getUserDefinedOWLNamedClasses();
		
		for (Iterator it = individuals.iterator(); it.hasNext();) {
		    OWLNamedClass cls = (OWLNamedClass) it.next();
		    //OntClass ontClass=(OntClass)cls;
		    Collection instances = cls.getInstances(false);
		    System.out.println("Class " + cls.getBrowserText() + " (" + instances.size() + ")");
		    for (Iterator jt = instances.iterator(); jt.hasNext();) {
		        OWLIndividual individual = (OWLIndividual) jt.next();
		        System.out.println(" - " + individual.getBrowserText());
		    }
		}
		//DefaultLecturer lecturer=(DefaultLecturer) owlModel.getRDFResource("Lecturer1").as(DefaultLecturer.class);
		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		/*for (Iterator it = classes.iterator(); it.hasNext();) {
		    OWLNamedClass cls = (OWLNamedClass) it.next();
		    Collection instances = cls.getInstances(false);
		    System.out.println("Class " + cls.getBrowserText() + " (" + instances.size() + ")");
		    for (Iterator jt = instances.iterator(); jt.hasNext();) {
		        OWLIndividual individual = (OWLIndividual) jt.next();
		        System.out.println(" - " + individual.getBrowserText());
		    }
		}*/
	}
	
	public static void loadUniversityJena(String url) throws OWLOntologyCreationException{
		String file="U:\\scis\\ASCL\\OntologyandSemantics\\university.owl";
		//OWLOntologyManager manager= OWLManager.createOWLOntologyManager();
		//org.semanticweb.owlapi.model.OWLOntology ontology=manager.loadOntologyFromOntologyDocument(new File(file));
		/*OntDocumentManager ontManager=new OntDocumentManager();
		OntModelSpec spec=new OntModelSpec(OntModelSpec.RDFS_MEM);
		spec.setDocumentManager(ontManager);
		OntModel model=ModelFactory.createOntologyModel(spec);
		model.read(file);*/
		
		
		//ref:http://jena.apache.org/documentation/ontology/
		// create the base model
		// Testing OWL file
				InputStream testFileIn = null;
				try {
					testFileIn = FileManager.get().open(
							"U:\\scis\\ASCL\\OntologyandSemantics\\university.owl");
				} catch (IllegalArgumentException ex) {
					System.out.println("File not found");
					System.exit(0);
				}
				// Testing OWL file online
				// Create ontology model using jena
				// Support OWL Full and no reasoning added
				OntModel ontModel = ModelFactory
						.createOntologyModel(OntModelSpec.OWL_MEM);
				System.out.println("ontModel=" + ontModel);
				// Read the input OWL file
				// Locally:
				 ontModel.read( testFileIn, " ");
				 // Online:
				//ontModel.read(testUrlIn);
				// Get the base model
				Model baseModel = ontModel.getBaseModel();
				System.out.println("baseModel=" + baseModel);
				
				//System.out.println("ontModel:"+ontModel);
				Iterator<OntClass> it=ontModel.listClasses();
				OntClass cls;
				Iterator<Individual> inst;

				while(it.hasNext()){
					cls=it.next();
					System.out.println("MODULE "+cls.getLocalName());
					inst=cls.listInstances();
					while(inst.hasNext()){
						Individual ind=inst.next();
						System.out.println("INSTANCES =>"+ind.getLocalName());
						Iterator<Statement> props=ind.listProperties();
						while(props.hasNext()){
							Statement stmt=props.next();
							Triple tp=stmt.asTriple();
							System.out.println(tp.toString());
						}
						for (Iterator<Resource> i = ind.listRDFTypes(false); i.hasNext(); ) {
						    System.out.println( ind.getURI() + " is inferred to be in class " + i.next() );
						}
						
					}
					if(cls.getEquivalentClass()!=null)
						System.out.println("********Equivalent class:"+cls.getEquivalentClass().getLocalName());
					if(cls.getDisjointWith()!=null)
					System.out.println("******Disjoint with "+cls.getDisjointWith().getLocalName());
					if(cls.hasSubClass())
					System.out.println("Subcls=> "+cls.getSubClass().getLocalName());
					if(cls.hasSuperClass())
						System.out.println("Superclas=> "+cls.getSuperClass().getLocalName());
				}
				//testModel();
				 testSparQL(baseModel);
	}
	
	/**
	 * ref:http://www.ibm.com/developerworks/library/j-jena/
	 * @author Ramesh R. Baral
	 * @Version 1.0
	 * @since Sep 29, 2014
	 */
	public static void testModel(){
		String uniUri="http://people.cis.fiu.edu/~rbaral";
		String relationshipUri = "http://purl.org/vocab/relationship/";

		// Create an empty Model
		Model model = ModelFactory.createDefaultModel();

		// Create a Resource for each family member, identified by their URI
		Resource adam = model.createResource(relationshipUri+"adam");
		Resource beth = model.createResource(relationshipUri+"beth");
		Resource chuck = model.createResource(relationshipUri+"chuck");
		Resource dotty = model.createResource(relationshipUri+"dotty");
		Resource fran = model.createResource(relationshipUri+"fran");
		Resource edward = model.createResource(relationshipUri+"edward");
		// and so on for other family members

		// Create properties for the different types of relationship to represent
		Property childOf = model.createProperty(relationshipUri,"childOf");
		Property parentOf = model.createProperty(relationshipUri,"parentOf");
		Property siblingOf = model.createProperty(relationshipUri,"siblingOf");
		Property spouseOf = model.createProperty(relationshipUri,"spouseOf");

		// Add properties to adam describing relationships to other family members
		adam.addProperty(spouseOf,dotty);
		adam.addProperty(parentOf,edward);
		//edward.addProperty(siblingOf,beth);
		edward.addProperty(siblingOf, beth);

		// Can also create statements directly . . .
		Statement statement = model.createStatement(adam,parentOf,fran);

		// but remember to add the created statement to the model
		model.add(statement);
		// List everyone in the model who has a child:
		ResIterator parents = model.listSubjectsWithProperty(parentOf);

		// Because subjects of statements are Resources, the method returned a ResIterator
		while (parents.hasNext()) {

		  // ResIterator has a typed nextResource() method
		  Resource person = parents.nextResource();

		  // Print the URI of the resource
		  System.out.println("parent of:"+person.getLocalName());
		}

		// Can also find all the parents by getting the objects of all "childOf" statements
		// Objects of statements could be Resources or literals, so the Iterator returned
		// contains RDFNodes
		NodeIterator moreParents = model.listObjectsOfProperty(childOf);

		// To find all the siblings of a specific person, the model itself can be queried 
		NodeIterator siblings = model.listObjectsOfProperty(edward, siblingOf);
		  
		// But it's more elegant to ask the Resource directly
		// This method yields an iterator over Statements
		StmtIterator moreSiblings = edward.listProperties(siblingOf);
		while(moreSiblings.hasNext()){
			Statement res=(Statement)moreSiblings.next();
			System.out.println("Sibling: "+res.getObject().toString());
			
		}
	}
	
	public static void testSparQL(Model model){
		String queryString="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "SELECT ?subject ?object	WHERE { ?subject rdfs:subClassOf ?object .}";
		Query query=QueryFactory.create(queryString);
		QueryExecution exec=QueryExecutionFactory.create(query,model);
		try{
			ResultSet rs=exec.execSelect();
			while(rs.hasNext()){
				QuerySolution soln=rs.nextSolution();
				Resource name=soln.getResource("subject");
				System.out.println("query result:"+name.getLocalName());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			exec.close();
		}
	}
	
	public static void testInference(Model model){
		String uniUri="http://people.cis.fiu.edu/~rbaral";
		String relationshipUri = "http://purl.org/vocab/relationship/";
		Model rdfsExample = ModelFactory.createDefaultModel();
		Property p = rdfsExample.createProperty(relationshipUri, "p");
		Property q = rdfsExample.createProperty(relationshipUri, "q");
		rdfsExample.add(p, RDFS.subPropertyOf, q);
		rdfsExample.createResource(relationshipUri+"a").addProperty(p, "foo1");
		
		InfModel inf = ModelFactory.createRDFSModel(rdfsExample);  // creating an inference model
		ValidityReport report=inf.validate();
		System.out.println(report.isValid());
		Resource a = inf.getResource(relationshipUri+"a");
		System.out.println("Statement: " + a.getProperty(q));// retrieves that q also contains foo as a property from the sub-property relation
		
	}
	
	public static void testReasoningWithRules(){
		String file="U:\\scis\\ASCL\\OntologyandSemantics\\university.owl";
		Model instances = ModelFactory.createDefaultModel();
		  instances.read ("http://auriga:8080/Ontology/");
		  String rules="@prefix pre: <http://jena.hpl.hp.com/prefix#> [rule1: (?f pre:father ?a) (?u pre:brother ?f) -> (?u pre:uncle ?a)]";
		  Reasoner reasoner = new GenericRuleReasoner(Rule.rulesFromURL("file:rules.txt"));
		  //Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rules));
		  //reasoner.setDerivationLogging(true);
		  InfModel inf = ModelFactory.createInfModel(reasoner, instances); 
		  
		         //print out the statements in the model 
		  StmtIterator iter = inf.listStatements();
		  while (iter.hasNext()) {
		      Statement stmt      = iter.nextStatement();  
		      Resource  subject   = stmt.getSubject();     
		      Property  predicate = stmt.getPredicate();   
		      RDFNode   object    = stmt.getObject();      

		       System.out.print(subject.toString());
		      System.out.print(" " + predicate.toString() + " ");
		      if (object instanceof Resource) {
		         System.out.print(object.toString());
		      } else {
		          // object is a literal
		          System.out.print(" \"" + object.toString() + "\"");
		      }
		      System.out.println(" .");
		  } 
	}
}
