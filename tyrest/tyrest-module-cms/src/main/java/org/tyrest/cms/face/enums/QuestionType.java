package org.tyrest.cms.face.enums;

/**
 * Created by wuqiang on 2016/12/25.
 */
public enum QuestionType {
    hottest,
    latest,
    mostScore,
    mostMoney;

    public static QuestionType getByName(String name){
        QuestionType result = null;
        for(QuestionType e : QuestionType.values()){
            if(e.name().equals(name)){
                result  = e;
                break;
            }
        }
        return result;
    }
}
