/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.library;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Stefan
 */
public class Attribute<T extends Comparable<T>> implements Serializable {

	private static final long serialVersionUID = -4794973041324837691L;

	public static final int STOP_CRITERIA = 3;
	
	private T min;
	private T max;
	private double weight;
    
    private String name; 
    private List<Interval> bins = new LinkedList<>();
    
    public Attribute(String name) {
        this.name = name;
    }
    
    @SuppressWarnings("unchecked")
	public List<T> casesToValues(Collection<Case> cases) {
        List<T> values = new LinkedList<>();
        for (Case c : cases) {
            T val = (T)c.getAttributeValue(name);
            if (val != null) values.add(val);
        }
        Collections.sort(values);
        return values;    
    }
    
    public void discretize(List<T> values) {
        bins = binaryDiscretization(values);
    }
    
    private List<Interval> binaryDiscretization(List<T> values) {
        if (values.size() <= STOP_CRITERIA) {
            List<Interval> result = new LinkedList<>();
            result.add(new Interval(null, null));
            return result;
        }
        int m = values.size() / 2;
        int a, b;
        for (a = m - 1; a >= 0; a--)
            if (!values.get(a).equals(values.get(a + 1))) break;
        for (b = m + 1; b < values.size(); b++)
            if (!values.get(b).equals(values.get(b - 1))) break;
        double i1 = 0;
        if (a >= 0) {
            double r = 1. * (a + 1) / values.size();
            i1 = -r * Math.log(r);
        }
        if (values.size() - a - 1 > 0) {
            double r = 1. * (values.size() - a - 1) / values.size();
            i1 += -r * Math.log(r);
        }
        double i2 = 0;
        if (b > 0) {
            double r = 1. * b / values.size();
            i2 = -r * Math.log(r);
        }
        if (values.size() - b > 0) {
            double r = 1. * (values.size() - b) / values.size();
            i2 += -r * Math.log(r);
        }
        if (i1 > i2) {
            if (a + 1 < 1) {
                List<Interval> result = new LinkedList<>();
                result.add(new Interval(null, null));
                return result;
            }
            List<Interval> l1 = binaryDiscretization(values.subList(0, a + 1));
            List<Interval> l2 = binaryDiscretization(values.subList(a + 1, values.size()));
            List<Interval> result = new LinkedList<>(l1);
            result.get(result.size() - 1).b = values.get(m);
            l2.get(0).a = values.get(m);
            result.addAll(l2);
            return result;
        }
        else {
            if (values.size() - b < 1) {
                List<Interval> result = new LinkedList<>();
                result.add(new Interval(null, null));
                return result;
            }
            List<Interval> l1 = binaryDiscretization(values.subList(0, b));
            List<Interval> l2 = binaryDiscretization(values.subList(b, values.size()));
            List<Interval> result = new LinkedList<>(l1);
            result.get(result.size() - 1).b = values.get(m);
            l2.get(0).a = values.get(m);
            result.addAll(l2);
            return result;
        }
    }
    
    public double getInformationGain(List<Case> cases) {
        double result = 0;
        if (cases.isEmpty()) return result;
        Map<Interval, Integer> count = new HashMap<>();
        for (Case c : cases) {
            Interval bin = getBin(c);
            if (!count.containsKey(bin)) count.put(bin, 1);
            else count.put(bin, count.get(bin) + 1);
        }
        for (Integer no : count.values()) {
            if (no == 0) continue;
            double r = 1. * no / cases.size();
            result += -r * Math.log(r);
        }
        return result;
    }
    
    public String getName() {
        return name;
    }
    
    @SuppressWarnings("unchecked")
	public Interval getBin(Case c) {
        T val = (T)c.getAttributeValue(name);
        for (Interval x : bins)
            if (x.inside(val))
                return x;
        return null;
    }
    
    public List<Interval> getBins() {
        return bins;
    }
    
    @SuppressWarnings("unchecked")
	public void computeMinAndMaxValues(Collection<Case> cases) {
    	min = null;
    	max = null;
    	for (Case libraryCase : cases) {
    		T value = (T) libraryCase.getAttributeValue(name);
    		if (min == null) {
    			min = value;
    		} else {
    			if (min.compareTo(value) > 0) {
    				min = value;
    			}
    		}
    		
    		if (max == null) {
    			max = value;
    		} else {
    			if (max.compareTo(value) < 0) {
    				max = value;
    			}
    		}
		}
    }

    @SuppressWarnings("unchecked")
	public Case getMin(Case a, Case b) {
    	T valueA = (T)(a.getAttributeValue(name));
    	T valueB = (T)(b.getAttributeValue(name));
    	return  valueA.compareTo(valueB) < 0 ? a : b; 
    }
    
    @SuppressWarnings("unchecked")
	public Case getMax(Case a, Case b) {
    	T valueA = (T)(a.getAttributeValue(name));
    	T valueB = (T)(b.getAttributeValue(name));
    	return  valueA.compareTo(valueB) > 0 ? a : b; 
    }
    
    @Override
    public String toString() {
        String s = name + "(";
        for (Interval bin : bins) {
            s += bin + "; ";
        }
        s += ")";
        return s;
    }
    
    public T getMin() {
		return min;
	}

	public void setMin(T min) {
		this.min = min;
	}

	public T getMax() {
		return max;
	}

	public void setMax(T max) {
		this.max = max;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public class Interval implements Serializable{
		private static final long serialVersionUID = -2872611430222570045L;
		
		public T a, b;
        
        public Interval(T a, T b) {
            this.a = a;
            this.b = b;
        }
        
        public boolean inside(T x) {
            if (x == null) return false;
            if (a != null && x.compareTo(a) < 0) return false;
            if (b != null && x.compareTo(b) >= 0) return false;
            return true;
        }
        
        @Override
        public String toString() {
            return "[" + a + ", " + b + ")";
        }
    }
}
