package com.zzteck.bigbwg.bean;

import java.io.Serializable;

/**
 * Created by Jianwei on 2018/4/1.
 *
 */

public class FileBean implements Serializable {


    private String filePath ;

    private boolean isSelect ;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
