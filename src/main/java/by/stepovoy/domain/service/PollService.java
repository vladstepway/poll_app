package by.stepovoy.domain.service;

import by.stepovoy.domain.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;
}
