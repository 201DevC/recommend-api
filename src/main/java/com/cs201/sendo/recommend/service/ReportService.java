package com.cs201.sendo.recommend.service;

import com.cs201.sendo.recommend.model.UserViewCount;
import com.cs201.sendo.recommend.repository.UserViewCountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@Slf4j
public class ReportService {

    private final UserViewCountRepository userViewCountRepository;

    public ReportService(UserViewCountRepository userViewCountRepository) {
        this.userViewCountRepository = userViewCountRepository;
    }

    public Stream<UserViewCount> generateReport() {
        return userViewCountRepository.getAll();
    }

}
