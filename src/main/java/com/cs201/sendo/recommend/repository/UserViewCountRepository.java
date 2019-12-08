package com.cs201.sendo.recommend.repository;

import com.cs201.sendo.recommend.model.UserCountViewId;
import com.cs201.sendo.recommend.model.UserViewCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface UserViewCountRepository extends JpaRepository<UserViewCount, UserCountViewId> {

    @Query("select u from UserViewCount u")
    Stream<UserViewCount> getAll();
}
