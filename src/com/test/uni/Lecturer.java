package com.test.uni;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 * 
 * <p>
 * Generated by Protege (http://protege.stanford.edu). <br>
 * Source Class: Lecturer <br>
 * @version generated on Wed Sep 24 17:41:25 EDT 2014 by rbara012
 */

public interface Lecturer extends Person {

    /* ***************************************************
     * Property http://people.cis.fiu.edu/~rbaral/university.owl#teaches
     */
     
    /**
     * Gets all property values for the teaches property.<p>
     * 
     * @returns a collection of values for the teaches property.
     */
    Collection<? extends Module> getTeaches();

    /**
     * Checks if the class has a teaches property value.<p>
     * 
     * @return true if there is a teaches property value.
     */
    boolean hasTeaches();

    /**
     * Adds a teaches property value.<p>
     * 
     * @param newTeaches the teaches property value to be added
     */
    void addTeaches(Module newTeaches);

    /**
     * Removes a teaches property value.<p>
     * 
     * @param oldTeaches the teaches property value to be removed.
     */
    void removeTeaches(Module oldTeaches);


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
     * Property http://people.cis.fiu.edu/~rbaral/university.owl#staff_id
     */
     
    /**
     * Gets all property values for the staff_id property.<p>
     * 
     * @returns a collection of values for the staff_id property.
     */
    Collection<? extends Integer> getStaff_id();

    /**
     * Checks if the class has a staff_id property value.<p>
     * 
     * @return true if there is a staff_id property value.
     */
    boolean hasStaff_id();

    /**
     * Adds a staff_id property value.<p>
     * 
     * @param newStaff_id the staff_id property value to be added
     */
    void addStaff_id(Integer newStaff_id);

    /**
     * Removes a staff_id property value.<p>
     * 
     * @param oldStaff_id the staff_id property value to be removed.
     */
    void removeStaff_id(Integer oldStaff_id);



    /* ***************************************************
     * Common interfaces
     */

    OWLNamedIndividual getOwlIndividual();

    OWLOntology getOwlOntology();

    void delete();

}
