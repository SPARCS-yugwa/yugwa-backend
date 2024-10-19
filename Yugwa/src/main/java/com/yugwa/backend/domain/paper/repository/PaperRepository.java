package com.yugwa.backend.domain.paper.repository;

import com.yugwa.backend.domain.paper.entity.Paper;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    // LIKE를 사용하여 제목에만 부분 검색을 지원하는 쿼리
    @Query(value = "SELECT * FROM paper WHERE title ILIKE %:searchTerm%", nativeQuery = true)
    List<Paper> searchByTitle(@Param("searchTerm") String searchTerm);

}


