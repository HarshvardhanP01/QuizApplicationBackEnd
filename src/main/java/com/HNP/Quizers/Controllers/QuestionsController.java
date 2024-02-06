package com.HNP.Quizers.Controllers;

import com.HNP.Quizers.Model.Questions;
import com.HNP.Quizers.Model.QuestionsWrapper;
import com.HNP.Quizers.Services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionsController {

    @Autowired
    QuestionsService questionsservice;
    @GetMapping("getAllQuestions")
    public ResponseEntity<List<Questions>> getQ()
    {
        return questionsservice.getQ();
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Questions questions)
    {
        return questionsservice.addQuestion(questions);
    }

    @GetMapping("findFQById/{id}")
    public ResponseEntity<Optional<Questions>> findByFullQuestionInfoById(@PathVariable Integer id)
    {
        return questionsservice.findByFullQuestionInfoById(id);
    }

    @GetMapping("findFQByDifficultyLevel/{difficultylevel}")
    public ResponseEntity<List<Questions>> findByFullQuestionInfoByDifficultyLevel(@PathVariable String difficultylevel)
    {
        return questionsservice.findByFullQuestionInfoByDifficultyLevel(difficultylevel);
    }

    @DeleteMapping("deleteQById/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id)
    {
        return questionsservice.deleteQuestionById(id);
    }

    @DeleteMapping("deleteQByCategory/{category}")
    public ResponseEntity<String> deleteQuestionByCategory(@PathVariable String category)
    {
        return questionsservice.deleteQuestionByCategory(category);
    }

    @PatchMapping("updateQById/{id}")
    public ResponseEntity<Optional<Questions>> updateQuestionById(@PathVariable Integer id,@RequestBody Questions questions)
    {
        return questionsservice.updateQuestionById(id,questions);
    }

    @PatchMapping("updateQByCategory/{category}")
    public ResponseEntity<String> updateQuestionsByCategory(@PathVariable String category,@RequestParam String newCategory)
    {
        return questionsservice.updateQuestionsByCategory(category,newCategory);
    }

    @PatchMapping("updateQueById/{id}")
    public ResponseEntity<Questions> updateQuestionsByIdWithMoreParameters(@PathVariable Integer id,@RequestBody QuestionsWrapper questionswrapper)
    {
        return questionsservice.updateQuestionsByIdWithMoreParameters(id,questionswrapper);
    }

}
