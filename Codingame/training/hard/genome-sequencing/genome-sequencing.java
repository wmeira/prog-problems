/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/hard/genome-sequencing/
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

	private static List<String> subSequences = new ArrayList<String>();

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		for (int i = 0; i < N; i++) {
			String subseq = in.next();
			subSequences.add(subseq);
		}
        sortByLengthSubsequences();

		String sequence = subSequences.get(0);
		for (int i = 0; i < subSequences.size(); i++) {
			String subSequence = subSequences.get(i);
			if (!sequence.contains(subSequence)) {
				String leftEqual = equalPart(sequence, subSequence, false);
				String rightEqual = equalPart(sequence, subSequence, true);

				if(leftEqual.length() < rightEqual.length()) {
					sequence = leftEqual + sequence;
				} else {
					sequence = sequence + rightEqual;
				}
			}
		}
		System.err.println(sequence);
		System.out.println(sequence.length());
	}

	private static void sortByLengthSubsequences() {
	   	Collections.sort(subSequences, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((String) o2).length() - ((String) o1).length();
			}
		});
	}

	private static String equalPart(String sequence, String subSequence, boolean right) {
		String ss = subSequence;
		for (int i = subSequence.length() - 1; i > 0; i--) {
			if (right) {
				ss = subSequence.substring(0, i);
				if (ss.equals(sequence.substring(sequence.length() - i))) {
					return subSequence.substring(i);
				}
			} else {
				ss = subSequence.substring(subSequence.length() - i);
				if (ss.equals(sequence.substring(0, i))) {
					return subSequence.substring(0, subSequence.length() - i);
				}
			}
		}
		return subSequence;

	}
}
