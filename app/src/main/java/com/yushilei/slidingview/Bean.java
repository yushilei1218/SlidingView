package com.yushilei.slidingview;

/**
 * @author by  yushilei.
 * @time 2016/9/8 -18:04.
 * @Desc
 */
public class Bean {
    private String item;
    private boolean isOpen;

    public Bean(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
