/*
 * IndefiniteArticle.java v1.0.0, 20/1/2012
 * 
 * @author: Philip Manavopoulos (@manavo)
 * 
 * (The MIT License)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 */ 

package com.manavo.libraries;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndefiniteArticle {

	public static String get(String phrase) {
		Pattern pattern;
		Matcher matcher;
		String word, lowercaseWord;
		
		if (phrase.length() == 0) {
			return "a";
		}
		
		// Getting the first word 
		pattern = Pattern.compile("(\\w+)\\s*.*");
		matcher = pattern.matcher(phrase);
		if(matcher.matches() == true) {
			word = matcher.group(1);
		} else {
			return "an";
		}

		lowercaseWord = word.toLowerCase();
	    
	    // Specific start of words that should be preceded by 'an'
	    String [] altCases = { "euler", "heir", "honest", "hono" };
	    for (String altCase : altCases) {
	        if (lowercaseWord.startsWith(altCase) == true) {
	            return "an";
	        }
	    }
	    
	    if (lowercaseWord.startsWith("hour") == true && lowercaseWord.startsWith("houri") == false) {
	    	return "an";
	    }

	    
	    // Single letter word which should be preceded by 'an'
	    if (lowercaseWord.length() == 1) {
	        if ("aedhilmnorsx".indexOf(lowercaseWord) >= 0) {
	            return "an";
	        } else {
	            return "a";
	        }
	    }
	    
	    // Capital words which should likely be preceded by 'an'
	    if (word.matches("(?!FJO|[HLMNS]Y.|RY[EO]|SQU|(F[LR]?|[HL]|MN?|N|RH?|S[CHKLMNPTVW]?|X(YL)?)[AEIOU])[FHLMNRSX][A-Z]")) {
	        return "an";
	    }
	    
	    // Special cases where a word that begins with a vowel should be preceded by 'a'
	    String [] regexes = { "^e[uw]", "^onc?e\\b", "^uni([^nmd]|mo)", "^u[bcfhjkqrst][aeiou]" };
	    
	    for (String regex : regexes) {
	        if (lowercaseWord.matches(regex+".*") == true) {
	            return "a";
	        }
	    }
	    
	    // Special capital words (UK, UN)
	    if (word.matches("^U[NK][AIEO].*") == true) {
	        return "a";
	    } else if (word == word.toUpperCase()) {
	        if ("aedhilmnorsx".indexOf(lowercaseWord.substring(0, 1)) >= 0) {
	            return "an";
	        } else {
	            return "a";
	        }
	    }
	    
	    // Basic method of words that begin with a vowel being preceded by 'an'
	    if ("aeiou".indexOf(lowercaseWord.substring(0, 1)) >= 0) {
	        return "an";
	    }
	    
	    // Instances where y followed by specific letters is preceded by 'an'
	    if (lowercaseWord.matches("^y(b[lor]|cl[ea]|fere|gg|p[ios]|rou|tt).*")) {
	        return "an";
	    }
	    
		return "a";
	}
	
}