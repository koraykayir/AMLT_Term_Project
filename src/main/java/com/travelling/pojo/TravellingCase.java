/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.pojo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.travelling.dao.CaseDAO;
import com.travelling.dao.CaseXCategoryDAO;
import com.travelling.dao.CategoryDAO;
import com.travelling.dao.DayDAO;
import com.travelling.entity.CbrCase;
import com.travelling.entity.CbrCaseXCategory;
import com.travelling.entity.CbrCategory;
import com.travelling.entity.CbrDay;
import com.travelling.library.Attribute;
import com.travelling.library.Case;
import com.travelling.retrieval.CaseAttributeMeasures;

/**
 *
 * @author Stefan
 */
public class TravellingCase implements Case{
    private Integer id;
    private Date startTime;
    private Date endTime;
    private Integer money;
    private Integer numberOfDays;
    private Map<CbrCategory, Double> preferences = new HashMap<>();
    private List<Day> days = new LinkedList<>();
    
    public TravellingCase() {
        
    }
    
    public TravellingCase(CbrCase c) {
        id = c.getId();
        startTime = c.getStartTime();
        endTime = c.getEndTime();
        money = c.getMoney();
        numberOfDays = c.getDays();
        
        for (CbrCaseXCategory cxc : CaseXCategoryDAO.instance.findByCase(c)) {
            preferences.put(cxc.getFkCategory(), cxc.getWeight());
        }
        
        for (CbrDay day : DayDAO.instance.findByCase(c)) {
            days.add(new Day(day));
        }
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the money
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * @return the preferences
     */
    public Map<CbrCategory, Double> getPreferences() {
        return preferences;
    }

    /**
     * @param preferences the preferences to set
     */
    public void setPreferences(Map<CbrCategory, Double> preferences) {
        this.preferences = preferences;
    }

    /**
     * @return the days
     */
    public List<Day> getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(List<Day> days) {
        this.days = days;
    }
    
    @Override
    public boolean save() {
        CbrCase c;
        if (id == null) c = new CbrCase();
        else c = CaseDAO.instance.find(id);
        if (c == null) return false;
        c.setStartTime(startTime);
        c.setEndTime(endTime);
        c.setMoney(money);
        c.setDays(getNumberOfDays());
        c.setCbrCaseXCategoryList(new LinkedList<CbrCaseXCategory>());
        
        
        //List<CbrCaseXCategory> cxcList = new LinkedList<>();
        for (Map.Entry<CbrCategory, Double> e : preferences.entrySet()) {
            CbrCaseXCategory cxc = new CbrCaseXCategory();
            cxc.setFkCase(c);
            cxc.setFkCategory(e.getKey());
            cxc.setWeight(e.getValue());
            c.getCbrCaseXCategoryList().add(cxc);
        }
        //CaseXCategoryDAO.instance.createAllIgnoreNullColumns(cxcList);
        
        try {
            c = CaseDAO.instance.update(c);
            id = c.getId();
        }
        catch (Exception ex) {
            return false;
        }
             
        
        int p = 1;
        for (Day day : days) {
            boolean ok = day.save(c, p++);
            if (!ok) return false;
        }
        
        return true;
    }

    /**
     * @return the numberOfDays
     */
    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    /**
     * @param numberOfDays the numberOfDays to set
     */
    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public Comparable<?> getAttributeValue(String name) {
        switch (name.toLowerCase()) {
            case "money":
                return money;
            case "days":
                return numberOfDays;
            case "starttime":
                return startTime;
            case "endtime":
                return endTime;
            case "interval":
                Calendar c1 = new GregorianCalendar();
                c1.setTime(startTime);
                Calendar c2 = new GregorianCalendar();
                c2.setTime(endTime);
                int minutes1 = c1.get(Calendar.HOUR_OF_DAY) * 60 + c1.get(Calendar.MINUTE);
                int minutes2 = c2.get(Calendar.HOUR_OF_DAY) * 60 + c2.get(Calendar.MINUTE);
                return minutes2 - minutes1 + (numberOfDays - 1) * 24 * 60;
            default:
                int categoryId;
                try {
                    categoryId = Integer.parseInt(name);
                }
                catch (NumberFormatException ex) {
                    return null;
                }
                CbrCategory category = CategoryDAO.instance.find(categoryId);
                if (category == null) return null;
                return preferences.get(category);
        }
    }

    @Override
    public void setAttributeValue(String name, Comparable<?> value) throws ClassCastException, IllegalArgumentException {
        switch (name.toLowerCase()) {
            case "money":
                if (!(value instanceof Integer))
                    throw new ClassCastException();
                money = (Integer)value;
                break;
            case "days":
                if (!(value instanceof Integer))
                    throw new ClassCastException();
                numberOfDays = (Integer)value;
                break;
            case "starttime":
                if (!(value instanceof Date))
                    throw new ClassCastException();
                startTime = (Date)value;
                break;
            case "endtime":
                if (!(value instanceof Date))
                    throw new ClassCastException();
                endTime = (Date)value;
                break;
            case "interval":
                break;
            default:
                int categoryId;
                try {
                    categoryId = Integer.parseInt(name);
                }
                catch (NumberFormatException ex) {
                    throw new IllegalArgumentException();
                }
                CbrCategory category = CategoryDAO.instance.find(categoryId);
                if (category == null) 
                    throw new IllegalArgumentException();
                if (!(value instanceof Double))
                    throw new ClassCastException();
                preferences.put(category, (Double)value);
        }
    }

	@Override
	public double getSimilarityForAttribute(Attribute<?> attribute, Case other) {
		return CaseAttributeMeasures.getSimilarity(
				getAttributeValue(attribute.getName()),
				other.getAttributeValue(attribute.getName()),
				attribute.getMax(), attribute.getMin());
	}
}
