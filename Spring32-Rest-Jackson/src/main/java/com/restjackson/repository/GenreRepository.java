package com.restjackson.repository;


import com.restjackson.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query(value="SELECT COUNT(*) FROM genre g JOIN movie_genre_rel mgr on g.id=mgr.genre_id WHERE g.id=?1", nativeQuery = true)
    Integer countGenresNativeQuery(Long id);
}
