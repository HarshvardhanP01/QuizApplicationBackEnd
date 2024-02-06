package com.HNP.Quizers.Services;

import com.HNP.Quizers.Dao.QuestionsDao;
import com.HNP.Quizers.Model.Questions;
import com.HNP.Quizers.Model.QuestionsWrapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionsService {

    @Autowired
    QuestionsDao questionsdao;
    public ResponseEntity<List<Questions>> getQ() {
        try
        {
            return new ResponseEntity<>(questionsdao.findAll(), HttpStatus.OK);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addQuestion(Questions questions) {
        try
        {
            questionsdao.save(questions);
            return new ResponseEntity<>("Inserted",HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Inserted :(",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Optional<Questions>> findByFullQuestionInfoById(Integer id) {
        Optional<Questions> q=questionsdao.findById(id);
        try
        {
                  return new ResponseEntity<>(q,HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Questions>> findByFullQuestionInfoByDifficultyLevel(String difficultylevel) {
        List<Questions> questions=questionsdao.findBydifficultylevel(difficultylevel);
        try
        {
            if(questions!=null) {
                return new ResponseEntity<>(questions, HttpStatus.OK);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
            questionsdao.deleteById(id);
            return new ResponseEntity<>("Deleted " + id + " Successfully",HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<String> deleteQuestionByCategory(String category) {
        int ans=questionsdao.deletebycategory(category);
        if(ans>0) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
            return new ResponseEntity<>("Not Deleted : Error :(", HttpStatus.BAD_REQUEST);
        }

        @Transactional
    public ResponseEntity<Optional<Questions>> updateQuestionById(Integer id, Questions questions) {
        Optional<Questions> que=questionsdao.findById(id);
        que.get().setCategory(questions.getCategory());
        que.get().setDifficultylevel(questions.getDifficultylevel());
        que.get().setQuestiontitle(questions.getQuestiontitle());
        que.get().setOption1(questions.getOption1());
        que.get().setOption2(questions.getOption2());
        que.get().setOption3(questions.getOption3());
        que.get().setOption4(questions.getOption4());
        que.get().setAnswer(questions.getAnswer());
        questionsdao.save(que.get());
        return new ResponseEntity<>(que,HttpStatus.OK);
        }

        @Transactional
    public ResponseEntity<String> updateQuestionsByCategory(String category, String newCategory) {
        questionsdao.findByCategory(category,newCategory);
        return new ResponseEntity<>("Updated " + category + " To " + newCategory,HttpStatus.OK);
    }
@Transactional
    public ResponseEntity<Questions> updateQuestionsByIdWithMoreParameters(Integer id, QuestionsWrapper questionswrapper) {
        Optional<Questions> que=questionsdao.findById(id);
        que.get().setCategory(questionswrapper.getCategory());
        que.get().setDifficultylevel(questionswrapper.getDifficultylevel());
        questionsdao.save(que.get());
        return new ResponseEntity<>(que.get(),HttpStatus.OK);
    }
}
