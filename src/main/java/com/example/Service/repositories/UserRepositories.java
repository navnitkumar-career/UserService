package com.example.Service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.Service.entites.User;


public interface UserRepositories extends JpaRepository<User, String>  {

}
