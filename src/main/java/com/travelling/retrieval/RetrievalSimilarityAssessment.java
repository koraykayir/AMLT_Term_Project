package com.travelling.retrieval;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import com.travelling.library.Attribute;
import com.travelling.library.Case;
import com.travelling.library.Library;

/**
 * Assess the exact similarity score for each of the "promising" cases returned by the indexing.
 * Returns the first k most similar cases to the target case. In order to do that, uses
 * k-nearest neighbor algorithm.
 * 
 * @author Claudia Cardei
 *
 */
public class RetrievalSimilarityAssessment {
	
	private static final int K = 2;
	
	private final Library library;
	private final List<Case> cases;
	private final Case target;
	
	public RetrievalSimilarityAssessment(Library library, List<Case> cases, Case target) {
		this.library = library;
		this.cases = cases;
		this.target = target;
	}
	
	/**
	 * Computes the similarity between the target case and all "promising" cases.
	 * 
	 * @return similarities mapped to each "promising" case
	 */
	public Map<Case, Double> computeSimilarity() {
		Map<Case, Double> similarities = new HashMap<Case, Double>();
		Collection<Attribute<?>> attributes = library.getAttributes().values();
		
		// Compute similarity between the target and each of the library cases for each attribute.
		// The final similarity is the weighted sum of the similarities for each attribute.
		for (Case promisingCase : cases) {
			double similarity = 0.0;
			for (Attribute<?> attribute : attributes) {
				double local = target.getSimilarityForAttribute(
						attribute, promisingCase);
				double weight = attribute.getWeight(); 
				similarity +=  weight * local;
			}
			
			similarities.put(promisingCase, similarity);
		}
		
		return similarities;
	}
	
	/**
	 * Computes the list of the most K similar cases to the target case. 
	 * 
	 * @return list of the most K similar cases
	 */
	public List<Case> getSimilarCases() {
		List<Case> similarCases = new ArrayList<Case>();
		SortedSet<Entry<Case, Double>> sortedCases =
                new TreeSet<Entry<Case, Double>>(new ComparatorBySimilarity());
		sortedCases.addAll(computeSimilarity().entrySet());
		
		int count = 0;
		for (Entry<Case, Double> similarCase : sortedCases) {
			if (count == K) {
				break;
			}
			
			similarCases.add(similarCase.getKey());
			count++;
		}
		
		return similarCases;
	}
	
	/**
	 * Comparator used to sort decreasingly the cases by their similarity.
	 *
	 */
	private static class ComparatorBySimilarity implements Comparator<Entry<Case, Double>> {

		@Override
		public int compare(Entry<Case, Double> arg0, Entry<Case, Double> arg1) {
			return arg1.getValue().compareTo(arg0.getValue());
		}
	}
}
