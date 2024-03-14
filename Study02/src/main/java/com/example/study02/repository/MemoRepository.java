package com.example.study02.repository;

import com.example.study02.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    @Transactional
    @Modifying
    @Query("select m from Memo m order by m.mno desc ")
    List<Memo> getListDesc();
    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
   int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);
    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :#{param.memoText} where m.mno = :#{param.mno}")
    int updateMemoText2(@Param("param") Memo memo);
}
