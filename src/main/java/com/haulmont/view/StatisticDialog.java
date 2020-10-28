package com.haulmont.view;

/**
 * Class for Statistic Dialog with common Find Intreface
 * @author r.merdeev
 */
public class StatisticDialog extends AbstractLabelDialog {   
    /** Find Interface */
    private final FindInterface find;
    
    /**
     * Constructor
     * @param caption Dialog caption
     * @param tab Tab Interface
     * @param find Find Interface
     */
    public StatisticDialog(String caption, TabInterface tab, FindInterface find) {
        super(caption, tab);
        this.find = find;        
    }
    
    @Override
    protected void label(String text) {   
        super.label(stat());
    }
    
    /**
     * Creates string with statistic
     * @return 
     */
    private String stat() {
        if (tab.get() != null) return tab.get().toString() + " receipts: " + find.find(tab.get()).size();
        return "";
    }
}
