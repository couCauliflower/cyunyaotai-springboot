package com.yunyaotai.yunyaotai.dao;

import com.yunyaotai.yunyaotai.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Integer> {
}
