package com.example.demo.repository;

import com.example.demo.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class UserRepository {

    private List<User> users;
}
