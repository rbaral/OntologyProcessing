package com.test.uni;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;

/**
 * Vocabulary class to provide access to the Manchester OWL API representatives for 
 * various entities in the ontology used to generate this code.<p> 
 * 
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: ${javaClass}
 *
 * @version generated on Wed Sep 24 17:41:24 EDT 2014 by rbara012
 */

public class Vocabulary {

	private static final OWLDataFactory factory = OWLManager.createOWLOntologyManager().getOWLDataFactory();

    /* ***************************************************
     * Class http://people.cis.fiu.edu/~rbaral/university.owl#CSModule
     */

    /**
     * A constant to give access to the Manchester OWL api representation of the class CSMODULE.<p>
     * 
     */
    public static final OWLClass CLASS_CSMODULE = factory.getOWLClass(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#CSModule"));

    /* ***************************************************
     * Class http://people.cis.fiu.edu/~rbaral/university.owl#Lecturer
     */

    /**
     * A constant to give access to the Manchester OWL api representation of the class LECTURER.<p>
     * 
     */
    public static final OWLClass CLASS_LECTURER = factory.getOWLClass(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#Lecturer"));

    /* ***************************************************
     * Class http://people.cis.fiu.edu/~rbaral/university.owl#MathModule
     */

    /**
     * A constant to give access to the Manchester OWL api representation of the class MATHMODULE.<p>
     * 
     */
    public static final OWLClass CLASS_MATHMODULE = factory.getOWLClass(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#MathModule"));

    /* ***************************************************
     * Class http://people.cis.fiu.edu/~rbaral/university.owl#Module
     */

    /**
     * A constant to give access to the Manchester OWL api representation of the class MODULE.<p>
     * 
     */
    public static final OWLClass CLASS_MODULE = factory.getOWLClass(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#Module"));

    /* ***************************************************
     * Class http://people.cis.fiu.edu/~rbaral/university.owl#Person
     */

    /**
     * A constant to give access to the Manchester OWL api representation of the class PERSON.<p>
     * 
     */
    public static final OWLClass CLASS_PERSON = factory.getOWLClass(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#Person"));

    /* ***************************************************
     * Class http://people.cis.fiu.edu/~rbaral/university.owl#Student
     */

    /**
     * A constant to give access to the Manchester OWL api representation of the class STUDENT.<p>
     * 
     */
    public static final OWLClass CLASS_STUDENT = factory.getOWLClass(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#Student"));

    /* ***************************************************
     * Object Property http://people.cis.fiu.edu/~rbaral/university.owl#studies
     */
     
    /**
     * A constant to give access to the Manchester OWL API representation of the object property STUDIES.<p>
     * 
     */
    public static final OWLObjectProperty OBJECT_PROPERTY_STUDIES = factory.getOWLObjectProperty(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#studies"));

    /* ***************************************************
     * Object Property http://people.cis.fiu.edu/~rbaral/university.owl#teaches
     */
     
    /**
     * A constant to give access to the Manchester OWL API representation of the object property TEACHES.<p>
     * 
     */
    public static final OWLObjectProperty OBJECT_PROPERTY_TEACHES = factory.getOWLObjectProperty(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#teaches"));

    /* ***************************************************
     * Data Property http://people.cis.fiu.edu/~rbaral/university.owl#first_name
     */
     
    /**
     * A constant to give access to the Manchester OWL API representation of the data property FIRST_NAME.<p>
     * 
     */
    public static final OWLDataProperty DATA_PROPERTY_FIRST_NAME = factory.getOWLDataProperty(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#first_name"));

    /* ***************************************************
     * Data Property http://people.cis.fiu.edu/~rbaral/university.owl#last_name
     */
     
    /**
     * A constant to give access to the Manchester OWL API representation of the data property LAST_NAME.<p>
     * 
     */
    public static final OWLDataProperty DATA_PROPERTY_LAST_NAME = factory.getOWLDataProperty(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#last_name"));

    /* ***************************************************
     * Data Property http://people.cis.fiu.edu/~rbaral/university.owl#staff_id
     */
     
    /**
     * A constant to give access to the Manchester OWL API representation of the data property STAFF_ID.<p>
     * 
     */
    public static final OWLDataProperty DATA_PROPERTY_STAFF_ID = factory.getOWLDataProperty(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#staff_id"));

    /* ***************************************************
     * Data Property http://people.cis.fiu.edu/~rbaral/university.owl#student_id
     */
     
    /**
     * A constant to give access to the Manchester OWL API representation of the data property STUDENT_ID.<p>
     * 
     */
    public static final OWLDataProperty DATA_PROPERTY_STUDENT_ID = factory.getOWLDataProperty(IRI.create("http://people.cis.fiu.edu/~rbaral/university.owl#student_id"));


}
