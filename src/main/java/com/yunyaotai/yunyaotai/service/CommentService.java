package com.yunyaotai.yunyaotai.service;

import com.yunyaotai.yunyaotai.dao.AdminDao;
import com.yunyaotai.yunyaotai.dao.CommentDao;
import com.yunyaotai.yunyaotai.dao.UserDao;
import com.yunyaotai.yunyaotai.dto.CommentDto;
import com.yunyaotai.yunyaotai.entity.Admin;
import com.yunyaotai.yunyaotai.entity.Comment;
import com.yunyaotai.yunyaotai.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    UserDao userDao;
    @Autowired
    AdminDao adminDao;
    @Autowired
    CommentDao commentDao;
    public void addComment(CommentDto commentDto) {
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Optional<User> optionalUser=userDao.findById(commentDto.getUserId());
        Optional<Admin> optionalAdmin=adminDao.findById(commentDto.getAdminId());
        commentDao.save(new Comment()
                .setMessage(commentDto.getMessage())
                .setTime(sdf.format(d))
                .setFrom(optionalUser.get())
                .setTo(optionalAdmin.get())
        );
    }

    public Page<Comment> getAllCommentByAdminId(Integer id, Pageable pageable) {
        Optional<Admin> admin=adminDao.findById(id);
        return commentDao.findAll(Example.of(new Comment().setTo(admin.get())),pageable);
    }

    public Page<Comment> getAllCommentByUserId(Integer id, Pageable pageable) {
        Optional<User> user=userDao.findById(id);
        return commentDao.findAll(Example.of(new Comment().setFrom(user.get())),pageable);
    }

    public boolean deleteComment(Integer id) {
        Optional<Comment> comment = commentDao.findById(id);
        if (comment.isEmpty()) return false;
        commentDao.delete(comment.get());
        return true;
    }
}
