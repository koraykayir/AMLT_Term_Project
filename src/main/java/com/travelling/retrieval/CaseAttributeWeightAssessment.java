package com.travelling.retrieval;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.travelling.library.Attribute;
import com.travelling.library.Case;
import com.travelling.utils.Utils;

/**
 * Computes the weights for each attribute using an unsupervised method.
 * 
 * @author Claudia Cardei
 *
 */
public class CaseAttributeWeightAssessment {
	
	private final Collection<Case> cases;
	private Collection<Attribute<?>> attributes;
	
	public CaseAttributeWeightAssessment(
			Collection<Case> cases, Collection<Attribute<?>> attributes) {
		this.cases = cases;
		this.attributes = attributes;
	}
	
	public void computeWeights() {
		for (Attribute<?> attribute : attributes) {
			attribute.setWeight(0.5);
		}
		
		double totalEntropy = computeEntropy(new HashSet<Attribute<?>>(attributes));
		for (int j = 0; j < 6; j++) {
			for (Attribute<?> attribute : attributes) {
				Set<Attribute<?>> newAttributes = new HashSet<Attribute<?>>(attributes);
				newAttributes.remove(attribute);
				double partialEntropy = computeEntropy(newAttributes);
				
				if (partialEntropy < totalEntropy) {
					attribute.setWeight(attribute.getWeight() - 0.1);
				} else {
					attribute.setWeight(attribute.getWeight() + 0.1);
				}
			}
		}
	}
	
	public double getMeanDistance(Set<Attribute<?>> attributes) {
		double distance = 0.0;
		for (Case a : cases) {
			for (Case b : cases) {
				distance += getDistance(attributes, a, b);
			}
		}
		return distance / Math.pow(cases.size(), 2);
	}
	
	public double getDistance(Set<Attribute<?>> attributes, Case a, Case b) {
		double distance = 0.0;
		for (Attribute<?> attribute : attributes) {
			distance += Math.pow(a.getDistanceForAttribute(attribute, b), 2);
		}
		
		return Math.sqrt(distance);
	}
	
	public double getAlpha(Set<Attribute<?>> attributes) {
		return -Math.log(0.5) / getMeanDistance(attributes);
	}
	
	public double computeEntropy(Set<Attribute<?>> attributes) {
		double entropy = 0.0;
		double alpha = getAlpha(attributes);
		for (Case a : cases) {
			for (Case b : cases) {
				double similarity = Math.exp(-alpha * getDistance(attributes, a, b)); 
				entropy += similarity * Utils.log2(similarity) + 
						(1 - similarity) * Utils.log2(1 - similarity);
			}
		}
		
		return -entropy;
	}	
}
