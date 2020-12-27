package com.yunyaotai.yunyaotai.dao;

import com.yunyaotai.yunyaotai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
}
