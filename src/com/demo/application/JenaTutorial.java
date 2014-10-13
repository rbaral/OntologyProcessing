package com.demo.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.shared.DoesNotExistException;

/**
 * https://wiki.csc.calpoly.edu/OntologyTutorial/wiki/AddingRulesToOntologiesWithJena
 * @File: JenaTutorial.java
 * @author Ramesh R. Baral
 * @Version 1.0
 * @since Oct 13, 2014
 */
public class JenaTutorial {
	public static Model m;
	public static BufferedReader br;

	public static void main(String[] args) throws IOException {
		m = ModelFactory.createDefaultModel();

		String in = "";
		while (!in.equals("Q")) {
			try {
				in = getUserInput();
			} catch (DoesNotExistException e) {
				in = "";
			}
			execute(in);
		}
	}

	public static String getUserInput() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;

		System.out.println("Please enter a command");
		System.out.println("[1] Load model");
		System.out.println("[2] Run rules");
		System.out.println("[3] Print all statements");
		System.out.println("[4] Query model");
		System.out.println("[5] Print number of statements");
		System.out.println("[Q] Quit");

		input = br.readLine();

		return input;
	}

	public static void execute(String command) throws IOException {
		if (command.equals("1")) {
			System.out.print("Reading file storeFront.owl ");
			String input = "U:\\Documents\\GitHub\\OntologyProcessing\\owlfiles\\storeFront.owl";//br.readLine();
			File f = new File(input);
			if (f.exists() && f.isFile())
				m.read("file:" + input);
			else
				System.out.println("Bad file location");
		} else if (command.equals("2")) {
			System.out.print("REading rules storeFront.rules ");
			String input = "U:\\Documents\\GitHub\\OntologyProcessing\\owlfiles\\storeFront.rules";//br.readLine();
			if (input == null)
				return;
			File f = new File(input);
			if (f.exists()) {
				List<Rule> rules = Rule.rulesFromURL("file:" + input);
				GenericRuleReasoner r = new GenericRuleReasoner(rules);
				r.setOWLTranslation(true);
				r.setTransitiveClosureCaching(true);

				InfModel infmodel = ModelFactory.createInfModel(r, m);
				m.add(infmodel.getDeductionsModel());
			} else
				System.out.println("That rules file does not exist.");
		} else if (command.equals("3")) {
			StmtIterator si = m.listStatements();
			Statement s = null;
			while (si.hasNext()) {
				s = (Statement)si.next();
				System.out.println(s);
			}
		} else if (command.equals("4")) {
			System.out.print("Enter a pattern to match: ");
			String input = br.readLine();
			String[] pattern = input.split(" ");
			if (pattern.length != 3) {
				System.out.println("Bad query pattern");
				return;
			}
			Resource s = null;
			Property p = null;
			RDFNode o = null;

			if (pattern[0].matches("'.+'"))
				s = getAnonNode(pattern[0].replace("'", ""));
			else if (!pattern[0].equals("?"))
				s = m.getResource(pattern[0]);

			if (pattern[1].matches("'.+'"))
				p = (Property)getAnonNode(pattern[1].replace("'", "")).as(Property.class);
			else if (!pattern[1].equals("?"))
				p = m.getProperty(pattern[1]);

			if (pattern[2].matches("'.+'"))
				o = getAnonNode(pattern[2].replace("'", ""));
			else if (pattern[2].matches("\".+\""))
				o = m.createLiteral(pattern[2].replace("\"", ""));
			else if (!pattern[2].equals("?"))
				o = m.getResource(pattern[2]);

			StmtIterator si = m.listStatements(s, p, o);
			Statement st = null;
			while (si.hasNext()) {
				st = (Statement)si.next();
				System.out.println(st);
			}
		} else if (command.equals("5")) {
			System.out.println(m.size());
		}
	}

	// This isn't pretty, but right now I can't find a better way to get an
	// anonymous node
	private static Resource getAnonNode(String anonId) {
		StmtIterator si = m.listStatements();
		Statement s = null;
		while (si.hasNext()) {
			s = (Statement)si.next();
			Resource node = s.getSubject();
			if (node.isAnon() && node.getId().toString().equals(anonId)) {
				return node;
			}
			node = s.getPredicate();
			if (node.isAnon() && node.getId().toString().equals(anonId)) {
				return node;
			}
			if (s.getObject().canAs(Resource.class)) {
				node = (Resource)s.getObject().as(Resource.class);
				if (node.isAnon() && node.getId().toString().equals(anonId)) {
					return node;
				}
			}
		}
		return null;
	}
}
