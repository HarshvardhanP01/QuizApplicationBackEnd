package com.HNP.Quizers.Dao;

import com.HNP.Quizers.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionsDao extends JpaRepository<Questions,Integer> {
     List<Questions> findBydifficultylevel(String difficultylevel);

     @Modifying
     @Query(value = "delete from Questions q where q.category=:category")
     int deletebycategory(String category);
     @Modifying
     @Query(value = "update Questions q set category=:newCategory where category=:category")
     void findByCategory(String category,String newCategory);
}
