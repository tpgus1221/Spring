package com.example.login.repository;

import com.example.login.entity.SignUp;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SignUpRepository extends CrudRepository<SignUp, String> {
    boolean existsById(String id); // id값이 데이터베이스에 있는지 조회

    Optional<SignUp> findByIdAndPw(String id, String pw);
}
