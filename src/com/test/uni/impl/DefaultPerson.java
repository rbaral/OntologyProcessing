package com.test.uni.impl;

import com.test.uni.*;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.protege.owl.codegeneration.inference.CodeGenerationInference;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultPerson <br>
 * @version generated on Wed Sep 24 17:41:25 EDT 2014 by rbara012
 */
public class DefaultPerson extends WrappedIndividualImpl implements Person {

    public DefaultPerson(CodeGenerationInference inference, IRI iri) {
        super(inference, iri);
    }





    /* ***************************************************
     * Data Property http://people.cis.fiu.edu/~rbaral/university.owl#first_name
     */
     
    public Collection<? extends String> getFirst_name() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_FIRST_NAME, String.class);
    }

    public boolean hasFirst_name() {
		return !getFirst_name().isEmpty();
    }

    public void addFirst_name(String newFirst_name) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_FIRST_NAME, newFirst_name);
    }

    public void removeFirst_name(String oldFirst_name) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_FIRST_NAME, oldFirst_name);
    }


    /* ***************************************************
     * Data Property http://people.cis.fiu.edu/~rbaral/university.owl#last_name
     */
     
    public Collection<? extends String> getLast_name() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LAST_NAME, String.class);
    }

    public boolean hasLast_name() {
		return !getLast_name().isEmpty();
    }

    public void addLast_name(String newLast_name) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LAST_NAME, newLast_name);
    }

    public void removeLast_name(String oldLast_name) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LAST_NAME, oldLast_name);
    }


}