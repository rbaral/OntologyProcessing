package com.test.uni;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 * 
 * <p>
 * Generated by Protege (http://protege.stanford.edu). <br>
 * Source Class: Person <br>
 * @version generated on Wed Sep 24 17:41:25 EDT 2014 by rbara012
 */

public interface Person extends WrappedIndividual {

    /* ***************************************************
     * Property http://people.cis.fiu.edu/~rbaral/university.owl#first_name
     */
     
    /**
     * Gets all property values for the first_name property.<p>
     * 
     * @returns a collection of values for the first_name property.
     */
    Collection<? extends String> getFirst_name();

    /**
     * Checks if the class has a first_name property value.<p>
     * 
     * @return true if there is a first_name property value.
     */
    boolean hasFirst_name();

    /**
     * Adds a first_name property value.<p>
     * 
     * @param newFirst_name the first_name property value to be added
     */
    void addFirst_name(String newFirst_name);

    /**
     * Removes a first_name property value.<p>
     * 
     * @param oldFirst_name the first_name property value to be removed.
     */
    void removeFirst_name(String oldFirst_name);



    /* ***************************************************
     * Property http://people.cis.fiu.edu/~rbaral/university.owl#last_name
     */
     
    /**
     * Gets all property values for the last_name property.<p>
     * 
     * @returns a collection of values for the last_name property.
     */
    Collection<? extends String> getLast_name();

    /**
     * Checks if the class has a last_name property value.<p>
     * 
     * @return true if there is a last_name property value.
     */
    boolean hasLast_name();

    /**
     * Adds a last_name property value.<p>
     * 
     * @param newLast_name the last_name property value to be added
     */
    void addLast_name(String newLast_name);

    /**
     * Removes a last_name property value.<p>
     * 
     * @param oldLast_name the last_name property value to be removed.
     */
    void removeLast_name(String oldLast_name);



    /* ***************************************************
     * Common interfaces
     */

    OWLNamedIndividual getOwlIndividual();

    OWLOntology getOwlOntology();

    void delete();

}