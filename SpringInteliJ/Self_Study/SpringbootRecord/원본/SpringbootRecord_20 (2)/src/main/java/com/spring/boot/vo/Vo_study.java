package com.spring.boot.vo;

import lombok.Data;

@Data
public class Vo_study {

    private String keyId;
    private String studyDay;
    private String contents;
    private String regDay;

    /*
    public Vo_study(String KEY_ID, String STUDY_DAY, String CONTENTS, String REG_DAY) {
        this.KEY_ID = KEY_ID;
        this.STUDY_DAY = STUDY_DAY;
        this.CONTENTS = CONTENTS;
        this.REG_DAY = REG_DAY;
    }

    public String getKEY_ID() {
        return KEY_ID;
    }

    public void setKEY_ID(String KEY_ID) {
        this.KEY_ID = KEY_ID;
    }

    public String getSTUDY_DAY() {
        return STUDY_DAY;
    }

    public void setSTUDY_DAY(String STUDY_DAY) {
        this.STUDY_DAY = STUDY_DAY;
    }

    public String getCONTENTS() {
        return CONTENTS;
    }

    public void setCONTENTS(String CONTENTS) {
        this.CONTENTS = CONTENTS;
    }

    public String getREG_DAY() {
        return REG_DAY;
    }

    public void setREG_DAY(String REG_DAY) {
        this.REG_DAY = REG_DAY;
    }
    */
}
