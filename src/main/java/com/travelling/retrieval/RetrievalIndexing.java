package com.travelling.retrieval;

import java.util.List;

import com.travelling.library.Case;
import com.travelling.library.Library;
import com.travelling.library.TreeNode;

/**
 * Searches in the case library for a set of "promising" cases.
 * 
 * @author Claudia Cardei
 *
 */
public class RetrievalIndexing {
	
	private static final int NUMBER_CASES_THRESHOLD = 4;
	
	private final Library library;
	private final Case target;
	
	public RetrievalIndexing(Library library, Case target) {
		this.library = library;
		this.target = target;
	}
	
	/**
	 * Traverses the library tree by using the target case attribute values as indexes. Stops
	 * when reaches a leaf or has a sufficient number of cases.
	 * 
	 * @return list of "promising" cases
	 */
	public List<Case> getPromisingCases() {
		TreeNode root = library.getTree();
		
		while (root.hasNext() && root.getNumberOfCases() > NUMBER_CASES_THRESHOLD) {
			root = root.getNext(target);
		}
		
		return root.getCases();
	}
}
