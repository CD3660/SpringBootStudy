package com.example.study02.repository;

import com.example.study02.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().MemoText("sample..." + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("==============================");

        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }

    }

    @Test
    public void testPageDefault() {
        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2);
        Pageable pageable = PageRequest.of(0,10, sortAll); // 앞쪽이 현재 페이지 정보, 뒤는 페이지 당 개수

        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println(result);

        System.out.println("-----------------------------");
        System.out.println("TotalPage: "+result.getTotalPages());  // 총 페이지 수
        System.out.println("TotalCount: "+result.getTotalElements());  // 전체 레코드 개수
        System.out.println("Page Number: "+result.getNumber()); // 현재 페이지 번호
        System.out.println("Page Size: "+result.getSize());  //페이지 당 데이터 개수
        System.out.println("Has Next Page: "+result.hasNext());  // 다음 페이지 존재 여부
        System.out.println("first page: "+result.isFirst());  // 이전 페이지 존재 여부 == 0페이지인지
        System.out.println("-----------------------------");
        for (Memo memo:result.getContent()){
            System.out.println(memo);
        }
    }




}
