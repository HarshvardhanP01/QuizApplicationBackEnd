package com.HNP.Quizers.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuestionsWrapper {
    private String category;
    private String difficultylevel;

    public QuestionsWrapper(String category, String difficultylevel) {
        this.category = category;
        this.difficultylevel = difficultylevel;
    }
}
